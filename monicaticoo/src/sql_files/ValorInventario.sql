SELECT P.idProducto,P.Nombre,P.Precio,I.Cantidad,P.Precio*I.Cantidad AS ValorVenta
FROM inventario AS Inv,inventarioproducto AS I,producto AS P
WHERE I.idInventarioProducto=Inv.idInventario AND P.idProducto=I.idProducto
AND Inv.Descripcion=?;