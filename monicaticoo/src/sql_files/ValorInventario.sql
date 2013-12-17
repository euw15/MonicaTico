SELECT P.idProducto,P.Nombre,P.Precio,I.Cantidad,P.Precio*I.Cantidad AS ValorVenta
FROM inventario AS I,producto AS P,ubicacionproducto AS U
WHERE P.idProducto=I.idProducto AND P.Estado='A' AND I.idUbicacionProducto=U.idUbicacionProducto
AND U.Lugar=?;