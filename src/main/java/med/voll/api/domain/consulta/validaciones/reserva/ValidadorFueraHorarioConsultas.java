package med.voll.api.domain.consulta.validaciones.reserva;

import med.voll.api.domain.ValidacionException;
import med.voll.api.domain.consulta.DatosReservaConsulta;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidadorFueraHorarioConsultas implements ValidadorDeConsultas {

    public void validar(DatosReservaConsulta datos){
        var fechaConsulta = datos.fecha();
        // Verifico si es domingo
        var domingo = fechaConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        // Verifico si es antes de las 7 y después de las 18
        var horarioAntesDeAperturaClinica = fechaConsulta.getHour()<7;
        var horarioAntesDeCierreClinica = fechaConsulta.getHour()>18;
        if (domingo || horarioAntesDeCierreClinica || horarioAntesDeAperturaClinica){
            throw new ValidacionException("Horario seleccionado fuera del horario de atención");
        }
    }
}
