SELECT P.idProducto,P.Nombre,P.Precio
FROM producto AS P, inventarioproducto AS Inv, inventario AS I
WHERE I.Descripcion = ? AND P.idProducto = Inv.idProducto
AND Inv.idInventarioProducto= I.idInventario AND P.idCategoria IS NULL ;
