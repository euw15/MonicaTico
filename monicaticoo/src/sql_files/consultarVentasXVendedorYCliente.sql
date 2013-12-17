select F.Fecha as Fecha, F.Nota as Descripcion ,F.TotalFacturado as Monto, 
Ven.Nombre as Vendedor, Cli.Nombre as Cliente
from factura as F  
join persona as Ven on Ven.idPersona=F.idVendedor
join persona as Cli on Cli.idPersona=F.idCliente
where F.idVendedor=?;