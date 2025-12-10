package med.voll.api.domain.consulta.validaciones.cancelamiento;

import org.springframework.stereotype.Component;

import med.voll.api.domain.consulta.DatosCancelamientoConsulta;

@Component
public class ValidadorCancelamientoHorario implements ValidadorDeCancelamiento {

    @Override
    public void validar(DatosCancelamientoConsulta datos) {
        System.out.println("Validando horario antes de cancelar...");
    }
}
