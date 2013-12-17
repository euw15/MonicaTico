select F.Fecha as Fecha, F.idFactura as Factura,
 ProdCant.Cantidad as Cantidad, ProdCant.PrecioVenta as Precio
from productocantidadfact as ProdCant  
inner join factura as F on ProdCant.idFactura=F.idFactura 
where F.TipoPago=?;