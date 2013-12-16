select F.Fecha as Fecha, F.idFactura as Factura,
 ProdCant.Cantidad as Cantidad, ProdCant.PrecioVenta as Precio,
sum(ProdCant.Cantidad) as TotalVendidos, sum(ProdCant.PrecioVenta) as PrecioTotal
from Factura as F inner join ProductoCantidadFact as ProdCant
 on ProdCant.idFactura=F.idFactura;