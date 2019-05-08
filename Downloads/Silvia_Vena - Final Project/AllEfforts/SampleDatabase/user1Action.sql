-- Act as User

-- searched key term bronze
insert into Lists values(1, "Bronzes", now(), now());

-- Search(search_id,search_content,search_time)
-- SeachViewed(search_id,treasure_id)
 -- Addto(list_id ,treasure_id,date_added )

insert into Search values(1, "Bronze", now());
insert into Clicked values (1,1);
insert into AddTo values(1, 1, now());
-- UPDATE Lists SET `count` = `count` +1, date_modified= now() WHERE  list_id = 1;
 
insert into Search values(2, "Bronze", now());
insert into Clicked values (2,21);
 insert into AddTo values (1, 21, now());
-- UPDATE Lists SET `count` = `count` +1, date_modified= now() WHERE  list_id = 1;
 
insert into Search values(3, "Bronze", now());
insert into Clicked values (3,24);
 insert into AddTo values (1, 24, now());
 -- UPDATE Lists SET `count` = `count` +1, date_modified= now() WHERE  list_id = 1;


-- Search(search_id,search_content,search_time)
-- SeachViewed(search_id,treasure_id)
 -- Addto(list_id ,treasure_id,date_added )
 
insert into Lists values(2, "Project 1", now(), now());

insert into Search values(4, "Bamboo", now());
insert into Clicked values (4,9); -- 14,20
insert into AddTo values (2, 9, now());
-- UPDATE Lists SET `count` = `count` +1, date_modified= now() WHERE  list_id = 2;

insert into Search values(5, "Flower", now());
insert into Clicked values (5,14); -- 14,20

insert into Search values(6, "Flower", now());
insert into Clicked values (6,20); -- 14,20

-- 1, 4, 12(jia)
insert into Search values(7, "Plum Orchid Lotus", now());
insert into Clicked values (7,1); 

insert into Search values(8, "Plum Orchid Lotus", now());
insert into Clicked values (8,12);
insert into AddTo values (2, 12, now());
-- UPDATE Lists SET `count` = `count` +1, date_modified= now() WHERE  list_id = 2;
 
 insert into Search values(9, "Pine", now());
insert into Clicked values (9,9);
insert into AddTo values (2, 9, now());
-- UPDATE Lists SET `count` = `count` +1, date_modified= now() WHERE  list_id = 2;

 insert into Search values(10, "Pine", now());
insert into Clicked values (10,13);
insert into AddTo values (2, 13, now());

 insert into Search values(11, "Rock", now());
insert into Clicked values (11,12);
insert into AddTo values (2, 12, now());

 insert into Search values(12, "Rock", now());
insert into Clicked values (12,32);
insert into AddTo values (2, 32, now());


