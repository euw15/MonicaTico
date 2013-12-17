SELECT P.idProducto,P.Nombre,P.Precio,M.Fecha
FROM producto AS P,movimientos AS M,inventario AS I,ubicacionproducto AS U
WHERE P.idProducto=I.idProducto AND P.Estado='A' 
AND I.idUbicacionProducto=U.idUbicacionProducto AND U.Lugar=?
AND P.idProducto=M.idProductoMovimiento AND M.Detalle LIKE 'Fact%'AND M.Fecha BETWEEN ? AND ?; 
