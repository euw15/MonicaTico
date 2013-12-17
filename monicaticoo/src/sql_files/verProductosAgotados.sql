SELECT P.idProducto,P.Nombre,P.Precio
FROM inventario AS I,producto AS P,ubicacionproducto AS U
WHERE P.idProducto=I.idProducto AND P.Estado='A' AND I.idUbicacionProducto=U.idUbicacionProducto
AND U.Lugar=? AND I.Cantidad<=0;