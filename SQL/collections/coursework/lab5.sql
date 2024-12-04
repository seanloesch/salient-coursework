# 1.
select ID, name
from student
where ID not in (select ID
				 from student natural join takes
                 where year < 2010
                 );


# 2.
select dept_name, max(salary)
from instructor
group by dept_name;


# 3.
select dept_name, min(max_salary)
from (select dept_name, max(salary) as max_salary
	  from instructor
	  group by dept_name
      ) as T;

      
# 4.
select distinct name 
from student natural join takes
where course_id like '%CS%';


# 5.
select distinct customer_name
from borrower natural join depositor;


# 6.
insert into customer values('Tom', 'Main', 'Rye');

select customer_name 
from customer natural join (select customer_street, customer_city
											   from customer
											   where customer_name = 'Smith'
											   ) as T
where customer_name != 'Smith';


# 7.
select distinct branch_name
from account inner join (select customer_name, account_number
						  from customer natural join depositor
						  where customer_city = 'Harrison'
                          ) as T
on account.account_number = T.account_number;


# 8.
select branch_name 
from branch
where assets > (select max(assets)
				from branch
				where branch_city = 'Brooklyn'
                );
      
      
# 9.
select distinct branch_name 
from (select *
	  from branch natural join loan
	  ) as T
where T.amount > 1000;
      
      
# 10.
select branch_name
from branch outside
where exists (select assets
			  from branch
			  where branch_city = 'Brooklyn' and outside.assets > branch.assets
			  );
