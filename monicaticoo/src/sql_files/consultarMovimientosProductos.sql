select M.Fecha as Fecha,P.Nombre as Nombre, TP.Tipo as Tipo, M.Detalle as Detalle, M.CantidadMovida as Cantidad,
M.Balance as Balance, UbicProd.Lugar as Lugar
from movimientos as M
join tipomovimiento as TP on TP.idTipoMovimiento=M.idTipoMovimiento
join ubicacionproducto as UbicProd on UbicProd.idUbicacionProducto=M.idLugarMovimiento
join producto as P on P.idProducto=M.idProductoMovimiento
where P.idProducto=? and P.estado='I';
