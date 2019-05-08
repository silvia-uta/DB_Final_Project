-- D. Updating Lists and addto

-- Create a list
insert into Lists(list_name, date_created, date_modified)
values(?, now(), now());

-- Remove a list
delete from Lists
where list_id = ?;

-- rename list
UPDATE Lists SET list_name = ? WHERE list_id =?;

-- Add a treasure to a list
insert into Addto values(?,?, now());
update Lists set date_modified = now() where list_id = ?;

-- Delete a treasure in a list
delete from addto where list_id = ? and treasure_id = ?;
update Lists set date_modified = now() where list_id = ?;

-- test code
-- insert into Lists(list_name, date_created, date_modified) values("test", now(), now());
-- UPDATE Lists SET list_name = "test2" WHERE list_id =?;
-- insert into Addto values(?,1, now());
-- update Lists set date_modified = now() where list_id = ?;
-- insert into Addto values(?,2, now());
-- update Lists set date_modified = now() where list_id = ?;
-- delete from Lists where list_id = ?;
-- delete from addto where list_id = ? and treasure_id = 1;




