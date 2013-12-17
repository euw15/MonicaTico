SELECT P.idProducto,P.Nombre,P.Precio,P.Costo,C.Descripcion
FROM (producto AS P JOIN inventario AS I ON P.idProducto=I.idProducto)
LEFT OUTER JOIN categoria AS C ON P.idCategoriaProd=C.idCategoria
WHERE I.idUbicacionProducto=? AND P.Estado='A'
ORDER BY C.Descripcion;