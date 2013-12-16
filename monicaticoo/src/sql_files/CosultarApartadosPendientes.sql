SELECT F.idFactura, FP.Saldo,FP.FechaVencimiento,F.TotalFacturado,F.idCliente
FROM Factura AS F,FacturaPendientes AS FP
WHERE FP.Saldo <>0;
