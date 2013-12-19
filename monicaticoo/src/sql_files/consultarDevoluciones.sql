
select D.idDevolucion as Devolucion, D.Fecha as Fecha,
P.Nombre as Cliente, D.Nota as Termino, 
D.TotalFacturado as Total from devolucion as D
join persona as P on P.idPersona=D.idClienteDev;