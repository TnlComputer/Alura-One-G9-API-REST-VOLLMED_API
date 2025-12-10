package med.voll.api.domain.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.direccion.DatosDireccion;

/*
 import lombok.NonNull;
 import org.springframework.hateoas.EntityModel;
 import org.springframework.hateoas.server.RepresentationModelAssembler;
 import org.springframework.stereotype.Component;

 @Component // La anotación @Component indica que esta clase es un bean de Spring y se registrará automáticamente en el contexto de la aplicación.
 public class DatosListaMedicoModelAssembler implements RepresentationModelAssembler<DatosListaMedico, EntityModel<DatosListaMedico>> {

    // El método toModel convierte una instancia de DatosListaMedico en un EntityModel,
    // que es una representación envolvente que proporciona una estructura estable para el JSON y puede incluir links adicionales.
    @Override
    @NonNull
    public EntityModel<DatosListaMedico> toModel(@NonNull DatosListaMedico datosListaMedico) {
        return EntityModel.of(datosListaMedico);
    }
 }

*/

public record DatosRegistroMedico(

        /*
         Usado en el curso - modificado por la documentación del curso
          @NotBlank String nombre,
          @NotBlank @Email String email,
          @NotBlank String telefono,
          @NotBlank @Pattern(regexp = "\\d{7,9}") String documento,
          @NotNull Especialidad especialidad,
          @NotNull @Valid DatosDireccion direccion
        */

        @NotBlank(message = "Nombre es obligatorio")
        String nombre,

        @NotBlank(message = "Email es obligatorio")
        @Email(message = "Formato de email es inválido")
        String email,

        @NotBlank(message = "Teléfono es obligatorio")
        String telefono,

        @NotBlank(message = "Documento es obligatorio")
        @Pattern(regexp = "\\d{7,9}", message = "Formato do Documento es inválido")
        String documento,

        @NotNull(message = "Especialidad es obligatorio")
        Especialidad especialidad,

        @NotNull(message = "Datos de dirección son obligatorios")
        @Valid DatosDireccion direccion) {
}

