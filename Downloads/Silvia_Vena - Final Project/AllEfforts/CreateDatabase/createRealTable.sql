create database FinalProject;
use FinalProject;

create table Museum(
museum_id int not null,
museum_name varchar(30) not null,
street_adress varchar(50),
district varchar(30),
city varchar(30),
zipcode char(6),
province varchar(30),
website varchar(255),
`description` longtext,
theme_color char(7),
year_founded year,
primary key(museum_id)
);

create table VisitInfo(
museum_id int not null,
start_season char(2),
end_season char(2),
opening_hour time,
closing_hour time, 
last_entry time,
ticket_price int,
primary key(museum_id, start_season),
foreign key(museum_id) references Museum(museum_id)
);

create table Treasure(
treasure_id int not null,
treasure_name varchar(255) not null,
treasure_link varchar(255),
category varchar(30),
img varchar(255),
era varchar(80),
creator varchar(100),
is_centerpiece boolean,
primary key(treasure_id)
);

create table Contain(
museum_id int not null,
treasure_id int not null,
primary key(treasure_id),
foreign key(museum_id) references Museum(museum_id),
foreign key(treasure_id) references Treasure(treasure_id)
);

create table Lists(
list_id int not null auto_increment,
list_name varchar(30) unique,
date_created datetime default now(),
date_modified datetime,
primary key(list_id)
);

create table AddTo(
list_id int not null,
treasure_id int not null,
date_added datetime default now(),
primary key(list_id, treasure_id),
foreign key(list_id) references Lists(list_id) ON DELETE CASCADE,
foreign key(treasure_id) references Treasure(treasure_id)
);

create table Search(
search_id int NOT NULL AUTO_INCREMENT,
search_content varchar(50) not null,
search_time datetime not null default now(),
primary key(search_id)
);

create table Clicked(
search_id int not null,
treasure_id int not null,
primary key(search_id),
foreign key(search_id) references Search(search_id),
foreign key(treasure_id) references Treasure(treasure_id)
);
