SELECT P.idProducto,P.Nombre,P.Precio
FROM producto AS P,inventario AS I,categoria AS C,ubicacionproducto AS U
WHERE P.Estado='A' AND C.Descripcion=? AND U.Lugar=? AND P.idProducto=I.idProducto
AND U.idUbicacionProducto=I.idUbicacionProducto AND P.idCategoriaProd=C.idCategoria;
