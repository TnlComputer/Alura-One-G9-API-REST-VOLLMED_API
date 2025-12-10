package med.voll.api.domain.paciente;

import jakarta.validation.constraints.NotNull;

public record DatosListaPaciente(
        @NotNull
        Long id,
        String nombre,
        String email,
        String telefono,
        String documentoIdentidad

) {
    public DatosListaPaciente(Paciente paciente) {
        this(
                paciente.getId(),
                paciente.getNombre(),
                paciente.getEmail(),
                paciente.getTelefono(),
                paciente.getDocumentoIdentidad());
    }
}