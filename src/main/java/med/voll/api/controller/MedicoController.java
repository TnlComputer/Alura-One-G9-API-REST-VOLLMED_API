package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.voll.api.domain.medico.*;
import med.voll.api.domain.paciente.DatosActualizarPaciente;
import med.voll.api.domain.paciente.DatosDetallePaciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/medicos")
@SecurityRequirement(name = "bearer-key")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @Transactional
    @PostMapping()
    public ResponseEntity registrar(@RequestBody @Valid DatosRegistroMedico datos, UriComponentsBuilder uriComponentsBuilder) {
        var medico = new Medico(datos);
        repository.save(medico);
        var uri = uriComponentsBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DatosDetalleMedico(medico));
    }

    @GetMapping()
    public ResponseEntity<Page<DatosListaMedico>> listar(@PageableDefault(size = 5, sort = {"nombre"}) Pageable paginacion) {
        var page = repository.findByActivoTrue(paginacion).map(DatosListaMedico::new);
        return ResponseEntity.ok(page);

//    @GetMapping
//    public PagedModel<EntityModel<DatosListaMedico>> listar(@PageableDefault(size = 10, sort = {"nombre"}) Pageable paginacion)
//    {Page<DatosListaMedico> página = repository.findAll(paginacion).map(DatosListaMedico::new);
//        // Usamos el pagedResourcesAssembler y el datosListaMedicoModelAssembler para convertir la Page en un PagedModel.
//        // Esto garantiza que cada objeto DatosListaMedico sea envuelto en un EntityModel, proporcionando una estructura JSON estable y permitiendo añadir links adicionales.
//        Return pagedResourcesAssembler.toModel(página, datosListaMedicoModelAssembler);}

        // Paginación¿http://localhost:8080/medicos?size=1&page=3 se realiza mediante el url
        // en donde le decimos con? Size la cantidad de registros a mostrar por página y con &page
        // y un número que la primera página es un 0 y la segunda 1.
        // Para ordenarlos alfabéticamente se hace http://localhost:8080/medicos?sort=nombre lo hace
        // ascendente y http://localhost:8080/medicos?sort=nombre,desc lo hace descendiente, si necesitamos
        // mostrar por otro campo cambiamos nombre por otro de los campos.
        // Para dejar estos parámetros por default podemos hacerlo antes de Pageable poniendo
        // public Page<DatosListaMedico> listar(@PageableDefault(size=10, sort={"nombre"}) Pageable paginacion)
        // si igual queremos manipular la url, lo que pongamos en la url deja sin efecto lo que tenemos en
        // default
    }

    @Transactional
    @PutMapping()
    public ResponseEntity actualizar(@RequestBody @Valid DatosActualizacionMedico datos) {
        var medico = repository.getReferenceById(datos.id());
        medico.actualizarInformaciones(datos);
        return ResponseEntity.ok(new DatosDetalleMedico(medico));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity eliminar(@PathVariable Long id) {
        var medico = repository.getReferenceById(id);
        medico.eliminar();
        // repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    //    @GetMapping("/{id}")
//    public ResponseEntity detallar(@PathVariable Long id) {
//        var medico = repository.getReferenceById(id);
//        return ResponseEntity.ok(new DatosDetalleMedico(medico));
//    }

    @GetMapping("/{id}")
    public ResponseEntity detallar(@PathVariable Long id) {
        var medico = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Médico no encontrado"));
        return ResponseEntity.ok(new DatosDetalleMedico(medico));
    }
}