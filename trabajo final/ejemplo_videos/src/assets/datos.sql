CREATE TABLE IF NOT EXISTS personajes (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    nombre TEXT NOT NULL UNIQUE,
                    ubicacion_inicial TEXT NOT NULL,
                    color_tropa_gratis TEXT,
                    tipo_habilidad TEXT NOT NULL,
                    descripcion TEXT,
                    imagen TEXT
                );

                
INSERT OR IGNORE INTO personajes (nombre, ubicacion_inicial, color_tropa_gratis, tipo_habilidad, descripcion, imagen) VALUES
('Frodo y Sam', 'La Comarca', NULL, 'PORTADOR', 'Apoyo de Elrond, Ayuda de Sam, Ponerse el Anillo', 'src/assets/personajes/frodo_sam.png'),
('Gandalf', 'Tharbad', NULL, 'MAGO', 'Mithrandir (+1 tropa), Sombragris (viaja 2), Luz y Llama', 'src/assets/personajes/gandalf.png'),
('Aragorn', 'Colinas de los Vientos', NULL, 'LIDER', 'Montaraz del Norte, Capitán del Oeste, Andúril', 'src/assets/personajes/aragorn.png'),
('Legolas', 'Reino de los Bosques', NULL, 'EXPLORADOR', 'Caminar en Silencio, Disparo Certero, Vista Aguda', 'src/assets/personajes/legolas.png'),
('Arwen', 'Rivendel', 'Verde', 'NOBLEZA', 'Estrella de la Tarde, Enviar Ayuda, Consejo', 'src/assets/personajes/arwen.png'),
('Merry y Pippin', 'La Comarca', NULL, 'SOPORTE', 'Amigos Leales, Distracción, ¡Cantaos Algo!', 'src/assets/personajes/merry_pippin.png'),
('Gollum', 'Moria', NULL, 'CORRUPTO', 'Guía (-3 dados de búsqueda), Fisgón, Astuto', 'src/assets/personajes/gollum.png');


CREATE TABLE IF NOT EXISTS ubicaciones (
    id INTEGER PRIMARY KEY,
    nombre TEXT NOT NULL UNIQUE,
    region TEXT NOT NULL,
    es_refugio INTEGER DEFAULT 0,
    es_fortaleza INTEGER DEFAULT 0,
    tropas_sombra INTEGER DEFAULT 0,
    tropas_aliadas INTEGER DEFAULT 0,
    tipo_tropa_aliada TEXT,
    pos_x INTEGER DEFAULT 0,
    pos_y INTEGER DEFAULT 0
);

CREATE TABLE IF NOT EXISTS conexiones (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    origen_id INTEGER NOT NULL,
    destino_id INTEGER NOT NULL,
    tipo_ruta TEXT NOT NULL,
    simbolo_coste TEXT,
    FOREIGN KEY(origen_id) REFERENCES ubicaciones(id),
    FOREIGN KEY(destino_id) REFERENCES ubicaciones(id)
);

INSERT OR IGNORE INTO ubicaciones (id, nombre, region, es_refugio, es_fortaleza, tropas_sombra, tropas_aliadas, tipo_tropa_aliada, pos_x, pos_y) VALUES
(1, 'La Comarca', 'Eriador', 1, 0, 0, 0, NULL, 280, 220),
(2, 'Rivendel', 'Rhudaur', 1, 0, 0, 1, 'Elfos', 470, 195),
(3, 'Colinas de los Vientos', 'Rhudaur', 0, 0, 0, 0, NULL, 370, 210),
(4, 'Tharbad', 'Eriador', 0, 0, 0, 0, NULL, 360, 310),
(5, 'Tierras Brunas', 'Enedwaith', 0, 0, 1, 0, NULL, 435, 340),
(6, 'Isengard', 'Enedwaith', 0, 1, 1, 0, NULL, 460, 410),
(7, 'Moria', 'Montañas Nubladas', 0, 1, 2, 0, NULL, 510, 290),
(8, 'Lórien', 'Rhovanion', 1, 0, 0, 1, 'Elfos', 555, 305),
(9, 'Reino de los Bosques', 'Rhovanion', 1, 0, 0, 1, 'Elfos', 635, 140),
(10, 'Erebor', 'Rhovanion', 1, 0, 0, 1, 'Enanos', 705, 130),
(11, 'Colinas de Hierro', 'Rhovanion', 0, 0, 0, 1, 'Enanos', 790, 125),
(12, 'Dol Guldur', 'Rhovanion', 0, 1, 1, 0, NULL, 620, 265),
(13, 'El Abismo de Helm', 'Rohan', 1, 0, 0, 1, 'Rohirrim', 510, 435),
(14, 'Edoras', 'Rohan', 1, 0, 0, 1, 'Rohirrim', 550, 455),
(15, 'Estemnet', 'Rohan', 0, 0, 0, 1, 'Rohirrim', 610, 420),
(16, 'Minas Tirith', 'Gondor', 1, 0, 0, 2, 'Gondor', 720, 520),
(17, 'Dol Amroth', 'Gondor', 1, 0, 0, 2, 'Gondor', 560, 570),
(18, 'Pelargir', 'Gondor', 0, 0, 0, 1, 'Gondor', 685, 580),
(19, 'Minas Morgul', 'Mordor', 0, 1, 2, 0, NULL, 765, 525),
(20, 'Barad-dûr', 'Mordor', 0, 1, 2, 0, NULL, 840, 510),
(21, 'Monte del Destino', 'Mordor', 0, 1, 0, 0, NULL, 800, 480);

INSERT OR IGNORE INTO conexiones (origen_id, destino_id, tipo_ruta, simbolo_coste) VALUES
(1, 3, 'NORMAL', NULL), (3, 1, 'NORMAL', NULL),  -- La Comarca <-> Colinas Vientos
(3, 2, 'NORMAL', NULL), (2, 3, 'NORMAL', NULL),  -- Colinas Vientos <-> Rivendel
(1, 4, 'NORMAL', NULL), (4, 1, 'NORMAL', NULL),  -- La Comarca <-> Tharbad
(3, 4, 'NORMAL', NULL), (4, 3, 'NORMAL', NULL),  -- Colinas Vientos <-> Tharbad
(4, 5, 'NORMAL', NULL), (5, 4, 'NORMAL', NULL),  -- Tharbad <-> Tierras Brunas
(5, 6, 'NORMAL', NULL), (6, 5, 'NORMAL', NULL),  -- Tierras Brunas <-> Isengard
(2, 7, 'NORMAL', NULL), (7, 2, 'NORMAL', NULL),  -- Rivendel <-> Moria
(7, 8, 'NORMAL', NULL), (8, 7, 'NORMAL', NULL),  -- Moria <-> Lórien
(8, 12, 'NORMAL', NULL), (12, 8, 'NORMAL', NULL), -- Lórien <-> Dol Guldur
(9, 10, 'NORMAL', NULL), (10, 9, 'NORMAL', NULL), -- Bosques <-> Erebor
(10, 11, 'NORMAL', NULL), (11, 10, 'NORMAL', NULL),-- Erebor <-> Colinas de Hierro
(9, 12, 'NORMAL', NULL), (12, 9, 'NORMAL', NULL), -- Bosques <-> Dol Guldur
(6, 13, 'NORMAL', NULL), (13, 6, 'NORMAL', NULL), -- Isengard <-> Helm
(13, 14, 'NORMAL', NULL), (14, 13, 'NORMAL', NULL),-- Helm <-> Edoras
(14, 15, 'NORMAL', NULL), (15, 14, 'NORMAL', NULL),-- Edoras <-> Estemnet
(14, 16, 'NORMAL', NULL), (16, 14, 'NORMAL', NULL),-- Edoras <-> Minas Tirith
(14, 17, 'NORMAL', NULL), (17, 14, 'NORMAL', NULL),-- Edoras <-> Dol Amroth
(16, 18, 'NORMAL', NULL), (18, 16, 'NORMAL', NULL),-- Minas Tirith <-> Pelargir
(17, 18, 'NORMAL', NULL), (18, 17, 'NORMAL', NULL),-- Dol Amroth <-> Pelargir
(16, 19, 'NORMAL', NULL), (19, 16, 'NORMAL', NULL),-- Minas Tirith <-> Minas Morgul
(19, 21, 'NORMAL', NULL), (21, 19, 'NORMAL', NULL),-- Minas Morgul <-> Monte Destino
(21, 20, 'NORMAL', NULL), (20, 21, 'NORMAL', NULL);-- Monte Destino <-> Barad-dûr

INSERT OR IGNORE INTO conexiones (origen_id, destino_id, tipo_ruta, simbolo_coste) VALUES
(7, 2, 'LINEA_BATALLA', NULL),   -- Moria avanza hacia Rivendel
(6, 13, 'LINEA_BATALLA', NULL),  -- Isengard avanza hacia el Abismo de Helm
(19, 16, 'LINEA_BATALLA', NULL); -- Minas Morgul avanza hacia Minas Tirith