SELECT P.idProducto,P.Nombre,P.Precio,P.Costo,P.Categoria
FROM producto AS P,inventarioproducto AS Inv,inventario AS I
WHERE P.idProducto=Inv.idProducto AND Inv.idInventarioProducto=I.idInventario
AND I.Descripcion =?;
