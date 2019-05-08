-- C. Sorting
-- Sort lists by date_created,date_modified, count, name

-- Sort lists by date_created,
select list_id 
from Lists
order by date_created desc;

select list_id 
from Lists
order by date_created asc;

-- Sort lists by date_modified,
select list_id 
from Lists
order by date_modified desc;

select list_id 
from Lists
order by date_modified asc;

-- Sort lists by list_name
select list_id 
from Lists
order by list_name desc;

select list_id 
from Lists
order by list_name asc;

-- Sort list by count
select Lists.list_id 
from Lists
Join (select list_id, count(*) as counts from Addto group by list_id) as list_count
on Lists.list_id = list_count.list_id
order by counts desc;

select Lists.list_id 
from Lists
Join (select list_id, count(*) as counts from Addto group by list_id) as list_count
on Lists.list_id = list_count.list_id
order by counts asc;


-- Sort treasures in list by date_added, name
-- sort treasure by date_added
select treasure_id
from addto
order by date_added desc;

select treasure_id
from addto
order by date_added asc;

-- sort treasure by name
select addto.treasure_id
from addto, treasure
where addto.treasure_id = treasure.treasure_id
order by treasure_name desc;

select addto.treasure_id
from addto, treasure
where addto.treasure_id = treasure.treasure_id
order by treasure_name asc;
