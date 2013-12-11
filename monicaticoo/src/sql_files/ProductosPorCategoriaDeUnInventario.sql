SELECT P.idProducto,P.Nombre,P.Precio
FROM producto AS P, inventarioproducto AS Inv, inventario AS I,Categoria AS C
WHERE C.Descripcion = ? AND I.Descripcion = ? AND P.idProducto = Inv.idProducto
AND Inv.idInventarioProducto= I.idInventario AND P.idCategoria=C.idCategoria;
