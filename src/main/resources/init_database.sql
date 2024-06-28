-- Tabla de usuarios
CREATE TABLE IF NOT EXISTS usuarios (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nombre TEXT NOT NULL,
    apellido TEXT NOT NULL,
    contrasena TEXT NOT NULL,
    fecha_registro DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- Tabla de detectives
CREATE TABLE IF NOT EXISTS detectives (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    id_usuario INTEGER NOT NULL,
    rango TEXT NOT NULL,
    experiencia INTEGER DEFAULT 0,
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id)
);

-- Tabla de secuaces
CREATE TABLE IF NOT EXISTS secuaces (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nombre TEXT NOT NULL,
    habilidad TEXT NOT NULL,
    peligrosidad INTEGER NOT NULL
);

-- Tabla de casos
CREATE TABLE IF NOT EXISTS casos (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    titulo TEXT NOT NULL,
    descripcion TEXT,
    dificultad INTEGER NOT NULL,
    id_secuaz INTEGER,
    FOREIGN KEY (id_secuaz) REFERENCES secuaces(id)
);

-- Tabla de pistas
CREATE TABLE IF NOT EXISTS pistas (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    id_caso INTEGER NOT NULL,
    contenido TEXT NOT NULL,
    orden INTEGER NOT NULL,
    FOREIGN KEY (id_caso) REFERENCES casos(id)
);

-- Tabla de partidas
CREATE TABLE IF NOT EXISTS partidas (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    id_detective INTEGER NOT NULL,
    id_caso INTEGER NOT NULL,
    fecha_inicio DATETIME DEFAULT CURRENT_TIMESTAMP,
    fecha_fin DATETIME,
    estado TEXT NOT NULL,
    FOREIGN KEY (id_detective) REFERENCES detectives(id),
    FOREIGN KEY (id_caso) REFERENCES casos(id)
);

-- Índices para mejorar el rendimiento de las consultas
CREATE INDEX idx_detectives_usuario ON detectives(id_usuario);
CREATE INDEX idx_casos_secuaz ON casos(id_secuaz);
CREATE INDEX idx_pistas_caso ON pistas(id_caso);
CREATE INDEX idx_partidas_detective ON partidas(id_detective);
CREATE INDEX idx_partidas_caso ON partidas(id_caso);
