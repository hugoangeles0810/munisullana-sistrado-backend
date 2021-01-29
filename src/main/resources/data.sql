

INSERT INTO oficina_unidad (id, codigo, nombre) VALUES (NEXT VALUE FOR seq_oficinaunidad_id, 'O00001', 'Oficina de Presupuesto');
INSERT INTO oficina_unidad (id, codigo, nombre) VALUES (NEXT VALUE FOR seq_oficinaunidad_id, 'O00002', 'Oficina de Planeamiento');

INSERT INTO rol (id, codigo, nombre) VALUES (NEXT VALUE FOR seq_rol_id, 'R000000001', 'Secretaria de unidad');
INSERT INTO rol (id, codigo, nombre) VALUES (NEXT VALUE FOR seq_rol_id, 'R000000002', 'Jefe de unidad');
INSERT INTO rol (id, codigo, nombre) VALUES (NEXT VALUE FOR seq_rol_id, 'R000000003', 'Jefe de unidad');

INSERT INTO tramite_tipo (id, codigo, nombre) VALUES (NEXT VALUE FOR seq_tramitetipo_id, 'TIPO000001', 'TUPA');

INSERT INTO tramite (id, codigo, nombre, tipoid, oficinaid, descripcion, indicaciones)
VALUES (NEXT VALUE FOR seq_tramite_id, 'T000000001', 'Tramite 1', 1, 1, 'Descripción de trámite 1', 'Estas son las indicaciones');

INSERT INTO requisito (id, tramiteid, nombre, descripcion, indicaciones, tipo_adjunto)
VALUES (NEXT VALUE FOR seq_tramite_id, 1, 'Requisito 1', 'Descripcion 1', 'Indicacion 1', 'pdf');


