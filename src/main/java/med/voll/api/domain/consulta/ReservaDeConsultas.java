package med.voll.api.domain.consulta;

import jakarta.validation.ValidationException;
import med.voll.api.domain.ValidacionException;
import med.voll.api.domain.consulta.validaciones.cancelamiento.ValidadorDeCancelamiento;
import med.voll.api.domain.consulta.validaciones.reserva.ValidadorDeConsultas;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservaDeConsultas {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private List<ValidadorDeConsultas> validadores;

    @Autowired
    private List<ValidadorDeCancelamiento> validadoresCancelamiento;


    public DatosDetalleConsulta reservar(DatosReservaConsulta datos) {

        if (!pacienteRepository.existsById(datos.idPaciente())) {
            throw new ValidacionException("No existe un paciente con el id informado");
        }
        if (datos.idMedico() != null && !medicoRepository.existsById(datos.idMedico())) {
            throw new ValidacionException("No existe un médico con el id informado");
        }

        // Validadores
        validadores.forEach(v -> v.validar(datos));

        var medico = elegirMedico(datos);
        if (medico == null ) {
            throw new ValidacionException("No existe un médico disponible en ese horario");
        }
        var paciente = pacienteRepository.findById(datos.idPaciente()).get();
        var consulta = new Consulta(null, medico, paciente, datos.fecha(), null);
        consultaRepository.save(consulta);
        return new DatosDetalleConsulta(consulta);
    }

    private Medico elegirMedico(DatosReservaConsulta datos) {

        if (datos.idMedico() != null) {
            pacienteRepository.getReferenceById(datos.idPaciente());
        }
        if (datos.especialidad() == null){
            throw new ValidationException("Es necesario elegir una especialidad cuando no se elige un médico");
        }

        return medicoRepository.elegirMedicoAleatorioDisponibleEnLaFecha(datos.especialidad(), datos.fecha());
    }

    public void cancelar(DatosCancelamientoConsulta datos) {
        if (!consultaRepository.existsById(datos.idConsulta())) {
            throw new ValidacionException("Id de la consulta informado no existe!");
        }
        var consulta = consultaRepository.getReferenceById(datos.idConsulta());
        consulta.cancelar(datos.motivo());
    }

}
