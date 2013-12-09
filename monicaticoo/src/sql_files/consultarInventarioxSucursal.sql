
select I.idProducto,P.Nombre,I.cantidad,
P.Precio,P.Costo,P.Categoria from inventarioproducto as I
inner join producto as P on P.idProducto=?;