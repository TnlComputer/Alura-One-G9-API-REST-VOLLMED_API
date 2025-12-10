CREATE TABLE pacientes (
    id BIGINT NOT NULL AUTO_INCREMENT,
    activo TINYINT NOT NULL DEFAULT 1,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    documento_identidad VARCHAR(14) NOT NULL UNIQUE,
    telefono VARCHAR(20) NOT NULL,
    calle VARCHAR(100) NOT NULL,
    numero VARCHAR(100),
    complemento VARCHAR(100),
    barrio VARCHAR(100) NOT NULL,
    ciudad VARCHAR(100) NOT NULL,
    estado VARCHAR(100) NOT NULL,
    codigo_postal VARCHAR(9) NOT NULL,

    PRIMARY KEY(id)
);