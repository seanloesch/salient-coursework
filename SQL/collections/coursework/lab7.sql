# PROBLEM A:

#1.

create view lab7q1 as
(
select ID, name, country
from Bands
);

insert into lab7q1
values (8, 'Sub Zero Project', 'Netherland');

#2.

create view lab7q2 as
(
select ID, name, formedIn, country
from bands left join albums
on ID = bandId
where title is null
);

select * from lab7q2;

insert into lab7q2
values (9, 'C418', 2006, 'UK');

# Values cannot be inserted because the data the view represents isn't an existing table
# in the schema.

#3.

select * from musicians;
select * from bands;

create view lab7q3 as
(
select mId, musicians.name, startYear, endYear, country
from musicians join bands
on ID = bandId
where bands.name = 'Pink Floyd'
);

select * from lab7q3;

insert into lab7q3
values (9, 'John Doe', 1, 1967, 1972);

# Values cannot be inserted because the data the view represents is an inner join of
# two different tables in the schema.


# PROBLEM B:


#4.

select tblCustomers.customerID
from tblCustomers left join tblOrders
on tblCustomers.customerID = tblOrders.customerID
where orderID is null;

#5.

select customerID, productName
from (tblCustomers natural join tblOrders as a)
	  natural join
     (tblOrderDetails natural join tblProducts as b)
where customerID = 'GREAL';
     
#6.

# theoretical answer

select * from tblShippers;
select * from tblOrders;
select * from tblOrderDetails;

select distinct tblSupplier.name, ShipperID
from (tblShippers join tblOrders
	  on ShipperID = ShipVia)
	  natural join
      tblOrderDetails
      natural join
      (tblProducts natural join tblSupplier)
where name = 'Supplier L';

#7.

select customerID, sum(UnitPrice * Quantity * (1 - Discount)) as Revenue
from (tblCustomers natural join tblOrders)
	  natural join tblOrderDetails
      natural join tblProducts
group by customerID;

#8.

select distinct productName
from tblOrderDetails right join tblProducts
on tblOrderDetails.productID = tblProducts.productID
where unitsOnOrder = 0;

#9.

select distinct customerID
from (tblCustomers natural join tblOrders)
	  natural join
      tblOrderDetails
where quantity = (select max(quantity) 
				  from tblOrderDetails);
                  
#10.

select * from tblSupplier;

select supplierID from
	(select supplierID, sum(UnitsOnOrder) as numProductsSupplied
	 from tblSupplier natural join tblProducts
	 group by name) as root
where numProductsSupplied = 0;
