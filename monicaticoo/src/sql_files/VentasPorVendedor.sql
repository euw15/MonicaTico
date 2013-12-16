SELECT F.idFactura,F.Fecha,C.Nombre,F.Descuento,F.TotalFacturado
FROM factura AS F,persona AS C
WHERE F.idVendedor=? AND F.idCliente=C.idPersona; 

