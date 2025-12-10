package med.voll.api.domain.paciente;

import med.voll.api.domain.direccion.Direccion;

public record DatosDetallePaciente(Paciente paciente) {


    public record DatosDetalleMedico(
            Long id,
            String nombre,
            String email,
            String documento,
            String telefono,

            Direccion Direccion
    ) {
        public DatosDetalleMedico(Paciente paciente){
            this(
                    paciente.getId(),
                    paciente.getNombre(),
                    paciente.getEmail(),
                    paciente.getDocumentoIdentidad(),
                    paciente.getTelefono(),
                    paciente.getDireccion()
            );
        }
    }

}
