SELECT F.idFactura, FP.Saldo,FP.FechaVencimiento,F.TotalFacturado,C.Nombre,
F.Tipopago 
FROM Factura AS F,FacturasPendientes AS FP, Persona AS C
WHERE F.idFactura = FP.idFactura AND 
F.idCliente=C.idPersona  AND F.TipoPago="Credito";
