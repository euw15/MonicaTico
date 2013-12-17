SELECT P.idProducto,P.Nombre,P.Precio
FROM producto AS P,inventario AS I,ubicacionproducto AS U
WHERE U.Lugar=? AND I.idUbicacionProducto=U.idUbicacionProducto AND P.idProducto =I.idProducto
AND P.idCategoriaProd IS NULL AND P.Estado='A';
