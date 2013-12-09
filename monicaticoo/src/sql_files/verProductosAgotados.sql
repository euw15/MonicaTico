SELECT P.idProducto,P.Nombre,P.Precio
FROM producto AS P,inventarioproducto AS I
WHERE P.idProducto=I.idProducto AND I.Cantidad<=0;