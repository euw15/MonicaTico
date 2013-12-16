select F.Fecha as Fecha, F.idFactura as Factura,
 ProdCant.Cantidad as Cantidad, ProdCant.PrecioVenta as Precio
from ProductoCantidadFact as ProdCant  inner join Factura as F
 on ProdCant.idFactura=F.idFactura where ProdCant.idProductoFact=1;