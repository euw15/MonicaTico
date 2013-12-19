select F.Fecha as Fecha, F.Nota as Descripcion ,F.TotalFacturado as Monto 
from factura as F 
where F.Fecha BETWEEN ? and ? 
union
 select D.Fecha as Fecha ,D.Nota as Descripcion, D.TotalFacturado as Monto 
from devolucion as D where D.Fecha BETWEEN ? and ? ;

