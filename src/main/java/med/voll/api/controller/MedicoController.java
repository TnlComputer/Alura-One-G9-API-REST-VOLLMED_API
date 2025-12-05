package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
//import org.springframework.data.web.PagedResourcesAssembler;
//import org.springframework.hateoas.EntityModel;
//import org.springframework.hateoas.PagedModel;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    // @Autowired
    // PagedResourcesAssembler se usa para convertir una Page en un PagedModel.
    // private PagedResourcesAssembler<DatosListaMedico> pagedResourcesAssembler;
    // @Autowired
    // Inyectamos nuestro DatosListaMedicoModelAssembler para convertir DatosListaMedico en EntityModel.
    // private DatosListaMedicoModelAssembler datosListaMedicoModelAssembler;

    @Transactional
    @PostMapping()
    public void registrar(@RequestBody @Valid DatosResgistroMedico datos) {
        repository.save(new Medico(datos));
    }

    @GetMapping()
    public Page<DatosListaMedico> listar(@PageableDefault(size=5, sort={"nombre"}) Pageable paginacion) {
        return repository.findAllByActivoTrue(paginacion).map(DatosListaMedico::new);

//    @GetMapping
//    public PagedModel<EntityModel<DatosListaMedico>> listar(@PageableDefault(size = 10, sort = {"nombre"}) Pageable paginacion) {
//        Page<DatosListaMedico> pagina = repository.findAll(paginacion).map(DatosListaMedico::new);
//        // Usamos el pagedResourcesAssembler y el datosListaMedicoModelAssembler para convertir la Page en un PagedModel.
//        // Esto garantiza que cada objeto DatosListaMedico sea envuelto en un EntityModel, proporcionando una estructura JSON estable y permitiendo añadir links adicionales.
//        return pagedResourcesAssembler.toModel(pagina, datosListaMedicoModelAssembler);

        // paginacion http://localhost:8080/medicos?size=1&page=3 se realiza mediante el url
        // en donde le decimos con ?size la cantidad de registros a mostrar por pagina y con &page
        // y un numero que la primera pagina es un 0 y la segunda 1.
        // para ordenarlos alfabeticamente se hace http://localhost:8080/medicos?sort=nombre lo hace
        // ascendente y http://localhost:8080/medicos?sort=nombre,desc lo hace desendente, si necesitamos
        // mostrar por otro campo cambiamos nombre por otro de los campos.
        // Para dejar estos parametros por default podemos hacerlo antes de Pageable poniendo
        // public Page<DatosListaMedico> listar(@PageableDefault(size=10, sort={"nombre"}) Pageable paginacion)
        // si igual queremos manipular la url, lo que pongamos en la url deja sin efecto lo que tenemos en
        // default
    }

    @Transactional
    @PutMapping()
    public void actualizar(@RequestBody @Valid DatosActualizacionMedico datos) {
        var medico = repository.getReferenceById(datos.id());
        medico.actualizarInformaciones(datos);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id){
        var medico = repository.getReferenceById(id);
        medico.eliminar();
        // repository.deleteById(id);
    }
}
