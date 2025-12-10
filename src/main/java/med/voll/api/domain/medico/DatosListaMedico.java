package med.voll.api.domain.medico;

import jakarta.validation.constraints.NotNull;

public record DatosListaMedico(
        @NotNull Long id,
        String nombre,
        String email,
        String telefono,
        String documento,
        Especialidad especialidad

) {
    public DatosListaMedico(Medico medico) {
        this(
                medico.getId(),
                medico.getNombre(),
                medico.getEmail(),
                medico.getTelefono(),
                medico.getDocumento(),
                medico.getEspecialidad());
    }
}
