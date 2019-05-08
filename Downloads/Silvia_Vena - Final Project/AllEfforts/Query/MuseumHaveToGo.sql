-- Display the museum name(s) that the most frequently searched treasures belong to.
-- Sort searched treasure based on number of times added to lists, then by alphabetical

use finalprojectsample;
create view topMuseum as 
Select museum_name,sum(num_searched) as total_searched
from (select treasure_id, count(*) as num_searched
	from Clicked
	group by treasure_id) as searched
	Join Contain ON searched.treasure_id = contain.treasure_id
    Join (select museum_id, museum_name from Museum) as M_info 
		ON M_info.museum_id = Contain.museum_id  
group by M_info.museum_id
order by total_searched desc, museum_name;

-- choose the first
select museum_name
from topMuseum ,(select max(total_searched) as max_search from topMuseum) as most
where total_searched = max_search;


