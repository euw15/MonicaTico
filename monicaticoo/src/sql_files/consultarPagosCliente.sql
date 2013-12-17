select P.Fecha as Fecha, P.TipoPago as Descripcion, P.MontoDePago as Pagado
from pagosfactpendientes as P inner join facturaspendientes as F on F.idFactura=P.idFacturaPendiente
inner join factura as Fact on Fact.idFactura=F.idFactura
where Fact.idCliente=?;
