

INSERT INTO oficina_unidad (id, codigo, nombre) VALUES (nextval('seq_oficinaunidad_id'), 'O00001', 'Oficina de Presupuesto');
INSERT INTO oficina_unidad (id, codigo, nombre) VALUES (nextval('seq_oficinaunidad_id'), 'O00002', 'Oficina de Planeamiento');

INSERT INTO rol (id, codigo, nombre) VALUES (nextval('seq_rol_id'), 'R000000001', 'Secretaria de unidad');
INSERT INTO rol (id, codigo, nombre) VALUES (nextval('seq_rol_id'), 'R000000002', 'Jefe de unidad');
INSERT INTO rol (id, codigo, nombre) VALUES (nextval('seq_rol_id'), 'R000000003', 'Jefe de unidad');

INSERT INTO tramite_tipo (id, codigo, nombre) VALUES (nextval('seq_tramitetipo_id'), 'TIPO000001', 'TUPA');
INSERT INTO tramite_tipo (id, codigo, nombre) VALUES (nextval('seq_tramitetipo_id'), 'TIPO000002', 'NO TUPA');

INSERT INTO tramite (id, codigo, nombre, tipoid, oficinaid, descripcion, indicaciones)
VALUES (nextval('seq_tramite_id'), 'T000000001', 'Tramite 1', 1, 1, 'Descripción de trámite 1', 'Estas son las indicaciones');

INSERT INTO requisito (id, tramiteid, nombre, descripcion, indicaciones, tipo_adjunto)
VALUES (nextval('seq_requisito_id'), 1, 'Requisito 1', 'Descripcion 1', 'Indicacion 1', 'pdf');

-- Usuario del sistema
INSERT INTO usuario(id, nombre, ape_paterno, ape_materno, email, clave, fecha_creacion, fecha_modificacion, oficinaid, roleid)
VALUES (nextval('seq_usuario_id'), 'Admin', '', '', 'hugoangeles0810@gmail.com', '$2a$10$97qOqX8CEQbvdAVUao5E5.iWJNjKwtlvYBjhdWpbD7Q2e0dFi0UUa', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 1);

