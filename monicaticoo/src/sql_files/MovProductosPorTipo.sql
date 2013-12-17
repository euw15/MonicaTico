SELECT M.Fecha,P.idProducto,P.Nombre,M.Detalle
FROM producto AS P,movimientos AS M,inventario AS I,ubicacionproducto AS U
WHERE P.idProducto=I.idProducto AND P.Estado='A' 
AND I.idUbicacionProducto=U.idUbicacionProducto AND U.Lugar=?
AND P.idProducto=M.idProductoMovimiento AND M.Fecha BETWEEN ? AND ?
ORDER BY M.idProductoMovimiento,M.Fecha,M.Detalle;
 
