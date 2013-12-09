SELECT S.Nombre,S.Direccion,T.Nombre
FROM sucursal AS S,tienda AS T
WHERE S.idTienda = T.idTienda;
