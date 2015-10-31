-- provincia --
INSERT INTO provincia(provincia)

    VALUES
    ('chubut'),
    ('santa cruz'),
    ('neuquen');

-- partido --
INSERT INTO partido(nombre, provincia)

    VALUES
    ('atlantico', 1),
    ('biedma', 1),
    ('cushamen', 1),
    ('escalante', 1),
    ('florentino ameghino', 1),
    ('futaleufu', 1),
    ('gaiman', 1),
    ('gastre', 1),
    ('languineo', 1),
    ('martires', 1),
    ('paso de indios', 1),
    ('rawson', 1),
    ('rio senguer', 1),
    ('sarmiento', 1),
    ('tehuelches', 1),
    ('telsen', 1);
    
-- localidad --
INSERT INTO localidad(cod_postal, nombre_localidad, id_partido)

    VALUES (9120, 'puerto madryn', 1);

-- acondicionador -- 
INSERT INTO acondicionador(apellido, direccion, dni, email, nombre, telefono, id_localidad)

    VALUES
    ('donato', 'moya y dancor', 30976216, 'donatohugo13@yahoo.com', 'hugo', 154322878, 1),
    ('valdez', 'la vecindad ¿?', 123546, 'donramon@dr.com', 'ramon', 15432, 1),    
    ('florinda', 'la vecindad ¿?', 789456, 'doniaflorinda@df.com', 'donia', 2878, 1);

-- usuario --
INSERT INTO usuario(acceso_sistema, esta_activo, creado_por, descripcion, email, 
            fecha_creacion, fecha_modificacion, fecha_nacimiento, modificado_por, 
            nombre, password, telefono)   
            
    VALUES
    ('Y', 'Y', 1, 'usuario hugo', 'hugo@hugo.com',
    '2011-05-16 15:36:38', '2011-05-16 15:36:38', '2011-05-16 15:36:38', 1,
    'hugo', 'hugo', 154322878),
    ('Y', 'Y', 1, 'usuario jorge', 'jorge@jorge.com',
    '2011-05-16 15:36:38', '2011-05-16 15:36:38', '2011-05-16 15:36:38', 1,
    'jorge', 'jorge', 154322878),
    ('Y', 'Y', 1, 'usuario felipe', 'felipe@felipecom',
    '2011-05-16 15:36:38', '2011-05-16 15:36:38', '2011-05-16 15:36:38', 1,
    'felipe', 'felipe', 154322878);

-- roles --
INSERT INTO rol(esta_activo, creado_por, descripcion, fecha_creacion, nombre)       

    VALUES 
    ('Y', 1, 'administrador', '2011-05-16 15:36:38', 'admin');
            

-- tabla muchos a muchos, usuarios y roles --
INSERT INTO usuario_rol(id_rol, id_usuario, esta_activo, creado_por, fecha_creacion)
    
    VALUES
    (1, 1, 'Y', 1, '2011-05-16 15:36:38');