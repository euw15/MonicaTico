SELECT P.idProducto,P.Nombre,P.Precio,P.Costo,C.Descripcion
FROM (((producto AS P JOIN inventarioproducto AS Inv ON P.idProducto=Inv.idProducto)
JOIN inventario AS I ON Inv.idInventarioProducto=I.idInventario) 
LEFT OUTER JOIN categoria AS C
ON P.idCategoria=C.idCategoria) 
WHERE I.Descripcion =? 
ORDER BY C.Descripcion;