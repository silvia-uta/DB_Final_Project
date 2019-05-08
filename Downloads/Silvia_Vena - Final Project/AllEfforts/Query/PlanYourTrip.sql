-- Plan your trip based on the list: 
-- Enter travel date
-- List the museums that feature the treasures in the list, provide visitInfo based on the date entered.

-- user input: list_id, month of date(need to extract month from date to use in query)
-- select the museum that contain the treasure in th list
-- select 
select * 
from visitInfo 
join (select distinct museum_id
from contain
where museum_id in (select museum_id from addto, contain where list_id = ? and addto.treasure_id = contain.treasure_id )) as dest
on visitInfo.museum_id = dest.museum_id
where (? > start_season  and ? < end_season ) or (?> end_season or ?<  start_season);


-- test
select * 
from visitInfo 
join (select distinct museum_id
from contain
where museum_id in (select museum_id from addto, contain where list_id = 1 and addto.treasure_id = contain.treasure_id )) as dest
on visitInfo.museum_id = dest.museum_id
where (5 > start_season  and 5 < end_season ) or (5> start_season or 5<  end_season);




