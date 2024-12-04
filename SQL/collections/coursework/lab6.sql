select * from bands;
select * from albums;
select * from musicians;

#1.
select name
from bands left join albums
on ID = bandId
where title is null;

#2.
select title
from bands right join albums
on ID = bandID
where name <> 'Pink Floyd';

#3.
select musicians.name
from bands join musicians
on ID = bandID
where bands.name = 'Can';

#4.
select musicians.name
from bands join musicians
on ID = bandID
where bands.name = 'Pink Floyd'
	and startYear <= 1971;
    
#5.
select musicians.name
from bands inner join musicians
on ID = bandID
where formedIn = startYear;

#6.
select musicians.name
from musicians join albums
on musicians.bandID = albums.bandID
where title = 'Meddle'
	and startYear <= year
    and year <= endYear;

#7.
select distinct bands.name
from bands join musicians
on ID = bandId
where musicians.name <> 'Irmin Schmidt';

#8.
select title
	from musicians join albums
	on musicians.bandId = albums.bandId
	where endYear < 1972;

#9.
select distinct title
from bands join (albums natural join musicians as d)
on bands.ID = d.bandId
where bands.name = 'King Crimson'
	and albums.year < (select startYear
					   from musicians
                       where name = 'Adrian Belew');
                       
#10.
select title, bands.name, year
from bands join (albums natural join musicians as d)
on bands.ID = d.bandId
where d.name = 'Lou Reed';



