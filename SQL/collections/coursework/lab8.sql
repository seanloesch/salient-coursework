# 1.

DELIMITER $$
create procedure GetAlbums(IN bandName varchar(20))
	BEGIN
		select title
		from bands join albums
		on bands.ID = albums.bandID
        where name = "Pink Floyd";
	END $$
DELIMITER ;

call GetAlbums("Pink Floyd");


# 2.

DELIMITER $$
create procedure GetMusicians(IN albumName varchar(20))
	BEGIN
		select name
        from musicians natural join albums
        where title = albumName;
	END $$
DELIMITER ;


# 3.

select * from musicians;
select name, startYear
			from musicians;


DELIMITER $$
create procedure yearMusicians(IN startYearIn INT, OUT musicianCount INT, OUT musicianNames varchar(50))
	BEGIN
		DECLARE rowName varchar(20) DEFAULT "";
        
        -- declare cursor for musician name and start year
		DECLARE curRow CURSOR for
			select name
			from musicians
            where startYear = startYearIn;
        
        -- decalre NOT FOUND handler
        DECLARE EXIT HANDLER FOR NOT FOUND select musicianCount, musicianNames;
        
        -- assigning musicianNames a value so it can be concatenated with
        set musicianNames = "";
        
        -- assigning value for the number of musicians with a matching starting year to the input
        Select COUNT(name) into musicianCount
        from Musicians
        where startYear = startYearIn;
        
        -- iterating over list of matching names and adding them to the result
        OPEN curRow;
        getCount: LOOP
					FETCH curRow INTO rowName;
						SET musicianNames = CONCAT(musicianNames, rowName, ", ");
				  END LOOP
		getCount;
        CLOSE curRow;
	END $$
DELIMITER ;

call yearMusicians(1968, @musicianCount, @musicianNames);

# 4.

DELIMITER $$
create procedure LoopDemoRepeatUntil()
	BEGIN
		DECLARE x INT;
        DECLARE str VARCHAR(255);
        SET x = 1;
        SET str = "";
        
        -- repeat... while loop
        REPEAT
			set x = x + 1;
			IF MOD(x, 2) = 0 THEN
            SET str = CONCAT(str, x, ",");
            END IF;
		UNTIL x = 10
        END REPEAT;
        
        select str;
	END $$
DELIMITER ;

DELIMITER $$
create procedure LoopDemoWhile()
	BEGIN
		DECLARE x INT;
        DECLARE str VARCHAR(255);
        SET x = 1;
        SET str = "";
        
        -- while loop
        while (x <= 10) DO
			set x = x + 1;
			IF MOD(x, 2) = 0 THEN
            SET str = CONCAT(str, x, ",");
            END IF;
		END while;
        
        select str;
	END $$
DELIMITER ;

call LoopDemo();
call LoopDemoRepeatUntil();
call LoopDemoWhile();


# 5.

select * from instructor;

DELIMITER $$
create procedure salaryData(IN departmentIn varchar(20), OUT max DOUBLE, OUT min DOUBLE, OUT avg DOUBLE)
	BEGIN
		Select MAX(salary) into max
        from instructor
        where dept_name = departmentIn;
        
        select MIN(salary) into min
        from instructor
        where dept_name = departmentIn;
        
        select AVG(salary) into avg
        from instructor
        where dept_name = departmentIn;
        
        select max, min, avg;
	END $$
DELIMITER ;

call salaryData("Physics", @max, @min, @avg);