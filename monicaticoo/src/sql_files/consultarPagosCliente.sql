select P.Fecha as Fecha, P.FormaPago as Descripcion, P.MontoDePago as Pagado
from PagosFactPendientes as P inner join FacturasPendientes as F on F.idFacturasPendientes=P.idFacturaPendiente
inner join Factura as Fact on Fact.idFactura=F.idFacturasPendientes
where Fact.idCliente=?;
