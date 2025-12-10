package med.voll.api.infra.exceptions;

import jakarta.persistence.EntityNotFoundException;
import med.voll.api.domain.ValidacionException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GestorDeErrores {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity gestionarError404(){
        return ResponseEntity.notFound().build();
    }

//
//     @ExceptionHandler(MethodArgumentNotValidException.class)
//     public ResponseEntity gestionarError400(MethodArgumentNotValidException ex){
//      var errores = ex.getFieldError();
//      return ResponseEntity.badRequest().body(errores.stream().map(DatosErrorValidacion::new).toList());
//     }
//

    // Para resolver el problema del .stream() lo realice de la siguiente forma.
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity gestionarError400(MethodArgumentNotValidException ex) {
        var errores = ex.getFieldErrors();
        var datosErrores = errores.stream().map(DatosErrorValidacion::new).toList();
        return ResponseEntity.badRequest().body(datosErrores);
    }

    @ExceptionHandler(ValidacionException.class)
    public ResponseEntity tratarErrorDeValidacion(ValidacionException ex) {

        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    public record DatosErrorValidacion(String campo, String mensaje){
        public DatosErrorValidacion(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }

    }
}
