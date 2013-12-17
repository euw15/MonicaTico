SELECT F.Fecha,F.idFactura,P.idProducto,P.Nombre,PCF.Cantidad
FROM Producto AS P, ProductoCantidadFact As PCF, Factura AS F
WHERE P.idCategoriaProd = ? AND P.Estado="A" AND 
P.idProducto = PCF.idProductoFact AND PCF.idFactura = F.idFactura
AND F.Fecha BETWEEN ? AND ?;