TRUNCATE TABLE usuario CASCADE;
TRUNCATE TABLE rol CASCADE;
TRUNCATE TABLE usuario_rol CASCADE;
TRUNCATE TABLE provincia CASCADE;
TRUNCATE TABLE partido CASCADE;
TRUNCATE TABLE localidad CASCADE;
TRUNCATE TABLE acondicionador CASCADE;

-- usuarios --
INSERT INTO usuario(
            id, acceso_sistema, esta_activo, creado_por, descripcion, email, fecha_creacion, 
            fecha_modificacion, fecha_nacimiento, modificado_por, nombre, password, 
            telefono)    
            
    VALUES
    (0, 'Y', 'Y', 0, 'usuario hugo', 'hugo@hugo.com', '2011-05-16 15:36:38',
    '2011-05-16 15:36:38', '2011-05-16 15:36:38', 0, 'hugo', 'hugo',
    154322878),
    (1, 'Y', 'Y', 0, 'usuario felipe', 'felipe@felipe.com', '2011-05-16 15:36:38',
    '2011-05-16 15:36:38', '2011-05-16 15:36:38', 0, 'felipe', 'felipe',
    154322878),
    (2, 'Y', 'Y', 0, 'usuario jorge', 'jorge@jorge.com', '2011-05-16 15:36:38',
    '2011-05-16 15:36:38', '2011-05-16 15:36:38', 0, 'jorge', 'jorge',
    154322878);

-- roles --
INSERT INTO rol(
            id, esta_activo, creado_por, descripcion, fecha_creacion, nombre)       

    VALUES 
    (0, 'Y', 0, 'administrador', '2011-05-16 15:36:38', 'admin');
            

-- tabla muchos a muchos, usuarios y roles --
INSERT INTO usuario_rol(
            id_rol, id_usuario, esta_activo, creado_por, fecha_creacion)
    
    VALUES
    (0, 0, 'Y', 0, '2011-05-16 15:36:38');
            
-- provincias --
INSERT INTO provincia(
            id, provincia)
            
    VALUES
    (0, 'chubut');
    
-- partido --
INSERT INTO partido(
            id, nombre, provincia)
            
    VALUES
    (0, 'biedma', 0);

    
-- localidad --
INSERT INTO localidad(
            id, cod_postal, localidad, id_partido)
    
    VALUES
    (0, 9120, 'puerto madryn', 0);
    
-- acondicionador --
INSERT INTO acondicionador(
            id, apellido, direccion, dni, email, nombre, telefono, id_localidad)
    
    VALUES
    (0, 'ramon', 'moya y dancor', 30976216, 'donramon@dr.com', 'valdez', 15432, 0),    
    (1, 'donia', 'moya y dancor', 30216976, 'doniaflorinda@df.com', 'florinda', 2878, 0);