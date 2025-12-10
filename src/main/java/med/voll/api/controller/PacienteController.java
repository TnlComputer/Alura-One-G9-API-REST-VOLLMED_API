package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.voll.api.domain.paciente.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/pacientes")
@SecurityRequirement(name = "bearer-key")
public class PacienteController {

    @Autowired
    private PacienteRepository repository;

    @Transactional
    @PostMapping
    public ResponseEntity registrar(@RequestBody @Valid DatosRegistroPaciente datos, UriComponentsBuilder uriComponentsBuilder) {
        var paciente = new Paciente(datos);
        repository.save(paciente);
        var uri = uriComponentsBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();
        return ResponseEntity.created(uri).body(new DatosDetallePaciente(paciente));
    }

    @GetMapping
    public ResponseEntity<Page<DatosListaPaciente>> listar(@PageableDefault(size = 5, sort = {"nombre"}) Pageable paginacion) {
        var page = repository.findByActivoTrue(paginacion).map(DatosListaPaciente::new);
        return ResponseEntity.ok(page);
    }

    @Transactional
    @PutMapping
    public ResponseEntity actualizar(@RequestBody @Valid DatosActualizarPaciente datos) {
        var pacienteOpt = repository.findById(datos.id());
        if (pacienteOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var paciente = pacienteOpt.get();
        paciente.actualizarInformaciones(datos);
        return ResponseEntity.ok(new DatosDetallePaciente(paciente));
    }


    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> desactivar(@PathVariable Long id) {
        var paciente = repository.getReferenceById(id);
        paciente.desactivar();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detallar(@PathVariable Long id) {
        var paciente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
        return ResponseEntity.ok(new DatosDetallePaciente(paciente));
    }
}
