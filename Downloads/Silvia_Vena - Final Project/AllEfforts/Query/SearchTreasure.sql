-- Search treasure (return treasure_id) by era, artist, category, museum, keyword
select treasure_id
from Treasure
where era like ?;

select treasure_id
from Treasure
where artist = ?;

select treasure_id
from Treasure
where category = ?;

select treasure.treasure_id
from Treasure
Join  Contain On Treasure.treasure_id = contain.treasure_id
Join Museum On contain.museum_id = museum.museum_id
where Museum.museum_name = ?;

select treasure_id 
from treasure
where treasure_name like ? 
	or artist like ?;


-- Give a list of treasure that belongs to the same museum/category/artist/era with an input treasure
set @selectedMuseum := (select museum_id from contain where treasure_id = ?);
Select treasure.treasure_id
from Treasure
Join Contain on Treasure.treasure_id = contain.treasure_id  and treasure.treasure_id != ?
where contain.museum_id = @selectedMuseum;

-- based on category
set @selectedCategory := (select category from treasure where treasure_id = ?);
Select treasure.treasure_id
from Treasure
where treasure.treasure_id != ? and category= @selectedCategory;

-- based on creator
set @selectedCreator := (select creator from treasure where treasure_id = ? and creator !="");
Select treasure.treasure_id
from Treasure
where  treasure.treasure_id != ? and creator = @selectedCreator;

-- select based on creator v2
Select treasure.treasure_id
from Treasure
where  treasure.treasure_id != ? and creator in (select creator from treasure where treasure_id = ? and creator !="");

-- based on era
SET @eraQuery := (SELECT CONCAT('%', (select era from treasure where treasure_id = ?), '%'));
Select treasure_id
from Treasure
where  treasure.treasure_id != ?  and era like @eraQuery;

-- Suggested museum: Give the museum that has the most number of the treasure of era/artist/category (based on search)
create view searched as
select treasure_id from treasure where category = ?;

create view counts as
select contain.museum_id, count(*) as num
from contain
	join searched on contain.treasure_id = searched.treasure_id
group by contain.museum_id;

create view getMax as
select max(num) as maxCount
from counts;

select museum_id
from counts
Join getMax on num =maxCount;

drop view searched;
drop view counts;
drop view getMax;


-- Insert into clicked table as long as the user open a treasure’s detail.

-- button.setOnClickListener {
    -- // execute query } 
insert into Clicked
values(?,?,now());

-- Search History:
-- Create View SearchHistory
create view SearchHistory as 
select * from Clicked
where search_id > ?;

create view SearchHistory as 
select * from Clicked
where search_time > ?;

-- Insert into SearchHistory
--  
-- Drop view when clear history
-- create new view after deletion




-- ------------------ Test------------------------------
select treasure.treasure_id
from Treasure
Join  Contain On Treasure.treasure_id = contain.treasure_id
Join Museum On contain.museum_id = museum.museum_id
where Museum.museum_name = "The Palace Museum";

set @selectedMuseum := (select museum_id from contain where treasure_id = 13);
Select treasure.treasure_id
from Treasure
Join Contain on Treasure.treasure_id = contain.treasure_id  and treasure.treasure_id != 13
where contain.museum_id = @selectedMuseum;

Select treasure.treasure_id
from Treasure
where treasure.treasure_id != 13 and category in (select category from treasure where treasure_id = 13);

select category from treasure where treasure_id =13;
select treasure_id from treasure where category = "Painting";

Select treasure.treasure_id
from Treasure
where  treasure.treasure_id != 1 and creator in (select creator from treasure where treasure_id = 1 and creator !="");

Select treasure.treasure_id
from Treasure
where  treasure.treasure_id != 1  and era like (select era from treasure where treasure_id = 1);

insert into treasure values (37, "test","","Bronze", "", "Spring and Autumn", "",0);
delete from treasure where treasure_id = 37;

SET @eraQuery := (SELECT CONCAT('%', (select era from treasure where treasure_id = 37), '%'));
select treasure_id from treasure where era like @eraQuery;

-- --------------------------------------------------------------------------------------------------------
create view searched as
select treasure_id from treasure where category = "Bronze";

create view counts as
select contain.museum_id, count(*) as num
from contain
	join searched on contain.treasure_id = searched.treasure_id
group by contain.museum_id;

create view getMax as
select max(num) as maxCount
from counts;

select museum_id
from counts
Join getMax on num =maxCount;

drop view searched;
drop view counts;
drop view getMax;

-- -----------------------------------------------------
-- Search History:
-- Create View SearchHistory
create view SearchHistory
as select * from Clicked
where search_id > 10;

drop view SearchHistory;