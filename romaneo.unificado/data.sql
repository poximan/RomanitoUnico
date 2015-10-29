TRUNCATE TABLE ad_user CASCADE;
TRUNCATE TABLE ad_role CASCADE;
TRUNCATE TABLE ad_user_roles CASCADE;
TRUNCATE TABLE provincia CASCADE;
TRUNCATE TABLE partido CASCADE;
TRUNCATE TABLE localidad CASCADE;
TRUNCATE TABLE acondicionador CASCADE;

-- usuarios --
INSERT INTO ad_user(ad_user_id,
            birthday, created, createdby, description, email, 
            isactive, issystemaccess, name, password, phone, phone2, updated, 
            updatedby)
    VALUES (0, '2011-05-16 15:36:38', '2011-05-16 15:36:38', 0, 'usuario hugo', 'hugo@hugo.com', 
            'Y', 'Y', 'hugo', 'hugo', 15432, 2878, '2011-05-16 15:36:38', 
            0),
            (1, '2011-05-16 15:36:38', '2011-05-16 15:36:38', 0, 'usuario felipe', 'felipe@felipe.com', 
            'Y', 'Y', 'felipe', 'felipe', 15432, 2878, '2011-05-16 15:36:38', 
            0),
            (2, '2011-05-16 15:36:38', '2011-05-16 15:36:38', 0, 'usuario jorge', 'jorge@jorge.com', 
            'Y', 'Y', 'jorge', 'jorge', 15432, 2878, '2011-05-16 15:36:38', 
            0);

-- roles --
INSERT INTO ad_role(ad_role_id,
            created, createdby, description, isactive, name, 
            userlevel, supervisor_id)
    VALUES (0, '2011-05-16 15:36:38', 0, 'administrador', 'Y', 'admin', 
            10, 0);

-- tabla muchos a muchos, usuarios y roles --
INSERT INTO ad_user_roles(
            ad_role_id, ad_user_id, created, createdby, isactive, updated, 
            updatedby)
    VALUES (0, 0, '2011-05-16 15:36:38', 0, 'Y', '2011-05-16 15:36:38', 
            0);
            
-- provincias --
INSERT INTO provincia(
            codprov, provincia)
    VALUES (0, 'chubut');
    
-- partido --
INSERT INTO partido(
            codpart, partido, provincia)
    VALUES (0, 'biedma', 0);
    
-- localidad --
INSERT INTO localidad(
            codloc, codpostal, localidad, codpart)
    VALUES (0, 9121, 'puerto madryn', 0);
    
-- acondicionador --
INSERT INTO acondicionador(
            id, address, dni, first_name, last_name, mail, phones, codloc)
    VALUES (0, 'moya y dancor', 30976216, 'ramon', 'valdez', 'donramon@dr.com', 15432, 0);