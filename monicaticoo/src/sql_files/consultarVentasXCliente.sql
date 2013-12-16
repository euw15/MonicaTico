

select F.Fecha as Fecha, F.Nota as Descripcion ,F.TotalFacturado as Monto 
from Factura as F where F.idCliente=?  union select D.Fecha as Fecha ,D.Nota 
as Descripcion, D.TotalFacturado as Monto from Devolucion as D where D.idClienteDev=?  ;