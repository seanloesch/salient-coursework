# PROBLEM A

#1
create view band as
select ID, name, country
from Bands;

insert into band values(2, "beatles","UK");

select ID, name, country
from Band;

#2
create view noalbums as
select b.id, b.name, b.country
from Bands b left join Albums a
on b.ID = a.bandId
where a.bandID is null;

insert into noalbums values(8, "Taylor Swift", "USA");
-- The insert spit out an error that said noalbums is not insertable to. I believe it is because the view selected values from a joined table. --

select *
from noalbums;

#3
create view pinkfloyd_members as
select m.mid, m.name, m.startyear, m.endyear, b.country
from Musicians m left join Bands b
on b.ID = m.bandId
where m.bandId = 1;

insert into pinkfloyd_members values(3,"Harry Styles", 2010, 2020, "Canada");
-- The insert spit out an error that said pinkfloyd_members is not insertable to. I believe it is because the view selected values from a joined table. --

select *
from pinkfloyd_members;





# PROBLEM B 

#4
select distinct contactname
from tblcustomers left outer join tblorders on tblcustomers.customerid = tblorders.customerid
where orderid is NULL;

#5
select p.ProductName
from (tblOrders o left join tblOrderDetails od
on o.orderid = od.orderid) left join tblProducts p
on od.ProductID = p.ProductID
where o.customerid = "GREAL";

#6
select distinct sh.companyname
from (((tblShippers sh left join tblOrders o
on sh.ShipperID = o.ShipVia) left join tblOrderDetails od
on o.orderid = od.orderid) left join tblProducts p
on od.productid = p.productid) left join tblSupplier s
on p.supplierid = s.supplierid
where s.name = "Supplier L";

#7
select o.customerid, sum((od.unitprice*od.quantity*(1-od.discount))) as Revenue
from tblOrders o left join tblOrderDetails od
on o.orderid = od.orderid
group by o.customerid;

#8
select p.productname
from tblproducts p right join tblorderdetails od
on p.productid = od.productid
where od.orderid is null;

#10
select s.supplierid
from tblsupplier s left join tblproducts p
on s.supplierid = p.supplierid
where p.productid is null;