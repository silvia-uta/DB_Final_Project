-- Sample Database
use FinalProjectSample;

-- Insert Sample data into Museum 
Insert into Museum
Values(1, "The Palace Museum", "4 Jingshan Qianjie", "Dongcheng", "Beijing", 100009,"",
 "https://en.dpm.org.cn/", "Established in 1925, the Palace Museum is located in the imperial palace of the consecutive Ming (1368-1644) and Qing (1644-1911) dynasties. The magnificent architectural complex, also known as the Forbidden City, and the vast holdings of paintings, calligraphy, ceramics, and antiquities of the imperial collections make it one of the most prestigious museums in China and the world. In 1961, the State Council designated the former imperial residence as one of China's foremost-protected cultural heritage sites, and in 1987 it was listed as a UNESCO World Heritage site.",
 "#f44250", "1925");
 
 Insert into Museum
 Values(2,"Shanghai Museum","201 Renmin Da Dao","Huangpu","Shanghai",200003,"",
 "https://www.shanghaimuseum.net/museum/frontend/en/","As a large museum of ancient Chinese art, the Shanghai Museum boasts a collection of 140,000 precious relics, featuring bronzes, ceramics, paintings and calligraphy. The abundance and quality of the collection have enjoyed a high reputation both at home and abroad. The Shanghai Museum was founded in 1952，originally located at the old Shanghai Race Club, 325 West Nanjing Road, since when it has steadily developed. The museum was relocated in the old Zhonghui Building, 16 South Henan Road in October 1959. In 1992, the Shanghai Municipal Government decided to allocate People's Square, the very center of the city, as the new site of Shanghai Museum.",
 "#135ec1",1952);
 
 Insert into Museum
 Values(3, "Second Museum of Shanghai","202 Renmin Da Dao","Huangpu","Shanghai",200003,"",
 "https://www.shanghaimuseum.net/museum/frontend/en/","As a large museum of ancient Chinese art, the Shanghai Museum boasts a collection of 140,000 precious relics, featuring bronzes, ceramics, paintings and calligraphy. The abundance and quality of the collection have enjoyed a high reputation both at home and abroad. The Shanghai Museum was founded in 1952，originally located at the old Shanghai Race Club, 325 West Nanjing Road, since when it has steadily developed. The museum was relocated in the old Zhonghui Building, 16 South Henan Road in October 1959. In 1992, the Shanghai Municipal Government decided to allocate People's Square, the very center of the city, as the new site of Shanghai Museum.",
 "#136ec1",1952);
 Insert into Museum
 Values(4, "Third Museum of Shanghai", "203 Renmin Da Dao","Huangpu","Shanghai",200003,"",
 "https://www.shanghaimuseum.net/museum/frontend/en/","As a large museum of ancient Chinese art, the Shanghai Museum boasts a collection of 140,000 precious relics, featuring bronzes, ceramics, paintings and calligraphy. The abundance and quality of the collection have enjoyed a high reputation both at home and abroad. The Shanghai Museum was founded in 1952，originally located at the old Shanghai Race Club, 325 West Nanjing Road, since when it has steadily developed. The museum was relocated in the old Zhonghui Building, 16 South Henan Road in October 1959. In 1992, the Shanghai Municipal Government decided to allocate People's Square, the very center of the city, as the new site of Shanghai Museum.",
 "#137ec1",1952);
 
 
  -- ---------- Insert Sample Data of VisitInfo
    -- museum_id ,start_season ,end_season ,opening_hour ,closing_hour, last_entry ,ticket_price,);
  Insert into VisitInfo
  Values(1,"11","03", '08:30:00','16:30','15:40',60);
  
Insert into VisitInfo
  Values(1,"4","10", '08:30:00','17:00','16:10',40);
  
  Insert into VisitInfo
  Values(2,"1","12", '09:00:00','17:00','16:00',0);
  
Insert into VisitInfo
  Values(3,"6","8", '09:00:00','17:00','16:00',20);
  
Insert into VisitInfo
  Values(3,"9","5", '010:00:00','14:00','13:00',10);
  
  Insert into VisitInfo
  Values(4,"3","12", '08:00:00','18:00','17:00',40);
    Insert into VisitInfo
  Values(4,"1","2", '09:00:00','17:00','16:00',20);
 
 -- ---------- Insert Sample Data of Treasure
-- Treasure(treasure_id, treasure_name, treasure_link, category, img, era, artist, centerpiece_of)
-- insert with jdbc
INSERT INTO Treasure VALUES (1, "Rectangular Vessel with Crane and Lotus Designs","https://en.dpm.org.cn/collections/collections/2009-10-15/504.html","Bronze","http://en.dpm.org.cn/files/image/8831/2007/0984/img0004[400PX].jpg","late Spring and Autumn Period","",0); 
INSERT INTO Treasure VALUES (2, "Two Letters in Running Script","https://en.dpm.org.cn/collections/collections/2014-08-12/1457.html","Calligraphy","http://en.dpm.org.cn/files/image/8831/2009/0363/img0012[400PX].jpg","Northern Song","Fan Zhongyan ",0); 
INSERT INTO Treasure VALUES (3, "Red-glazed Lute-shaped Zun Vessel","https://en.dpm.org.cn/collections/collections/2009-10-19/780.html","Ceramic","http://en.dpm.org.cn/files/image/8831/2008/0370/img0015[400PX].jpg","Qing","Lang kilns ",1); 
INSERT INTO Treasure VALUES (4, "Cloisonn?? Enamel Burner with Lotus Decoration and Elephant-trunk Handles","https://en.dpm.org.cn/collections/collections/2009-10-16/1175.html","Enamel","http://en.dpm.org.cn/files/image/8831/2007/1265/img0004[400PX].jpg","Yuan","",1); 
INSERT INTO Treasure VALUES (5, "Blue Glass Beaker Vase","https://en.dpm.org.cn/collections/collections/2012-11-08/1815.html","Glass","http://en.dpm.org.cn/files/image/8831/2007/0811/img0006[400PX].jpg","Qing","",0); 
INSERT INTO Treasure VALUES (6, "After Rain after Wang Xizhi","https://en.dpm.org.cn/collections/collections/2011-06-10/644.html","Calligraphy","http://en.dpm.org.cn/files/image/8831/2008/1180/img0014[400PX].jpg","Northern Song","Anonymous",0); 
INSERT INTO Treasure VALUES (7, "A Pair of Green Jade Incense Burners with Gilt Pagoda Finials","https://en.dpm.org.cn/collections/collections/2012-02-14/1640.html","Jade","http://en.dpm.org.cn/files/image/8831/2007/1051/img0001[400PX].jpg","Qing","",0); 
INSERT INTO Treasure VALUES (8, "Black Lacquer Plate with Golden Wisteria Decoration","https://en.dpm.org.cn/collections/collections/2009-10-16/75.html","Lacquer","","Qing","",0); 
INSERT INTO Treasure VALUES (9, "Bamboo and Pine Trees","https://en.dpm.org.cn/collections/collections/2015-03-27/1182.html","Painting","http://en.dpm.org.cn/files/image/8831/2009/1263/img0007[400PX].jpg","Qing","Yun Shouping",0); 
INSERT INTO Treasure VALUES (10, "Reading in a Fishing Vessel","https://en.dpm.org.cn/collections/collections/2013-04-23/1199.html","Painting","http://en.dpm.org.cn/files/image/8831/2007/2205/img0005[400PX].jpg","Ming","Jiang Song ",0); 
INSERT INTO Treasure VALUES (11, "Portrait of Du Fu","https://en.dpm.org.cn/collections/collections/2017-08-17/5352.html","Painting","/d/file/collections/collections/2017-08-17/76ab051a49e18ee849ff8efede6d9566.jpg","Yuan","Zhao Mengfu",0); 
INSERT INTO Treasure VALUES (12, "Plum Tree and Rocks","https://en.dpm.org.cn/collections/collections/2009-11-13/71.html","Painting","http://en.dpm.org.cn/files/image/8831/2008/2607/img0006[400PX].jpg","Ming","Chen Hongshou",0); 
INSERT INTO Treasure VALUES (13, "Listening to the Zither under Pines with Signature of Zhao Mengfu","https://en.dpm.org.cn/collections/collections/2017-08-29/5367.html","Painting","/d/file/collections/collections/2017-08-29/318324f8ede22f954b2a09a6a1f040d4.jpg","Yuan","Zhao Mengfu",0); 
INSERT INTO Treasure VALUES (14, "Oblated Flask with Two Ears and Underglaze Blue Design of Flowers","https://www.shanghaimuseum.net/museum/frontend/en/articles/CI00004194.html","Ceramic","//www.shanghaimuseum.net/resource/museum_files/resource_files/20150409/1428561202180_162.jpg","Ming","",0); 
INSERT INTO Treasure VALUES (15, "Robe with Python Design","https://www.shanghaimuseum.net/museum/frontend/en/articles/CI00004119.html","Others","//www.shanghaimuseum.net/resource/museum_files/resource_files/20150424/1429843661202_162.jpg","Qing","",0); 
INSERT INTO Treasure VALUES (16, "Fish and Ducks","https://www.shanghaimuseum.net/museum/frontend/en/articles/CI00004163.html","Painting","//www.shanghaimuseum.net/resource/museum_files/resource_files/20150506/1430893662389_162.jpg","QIng","Zhu?Da",0); 
INSERT INTO Treasure VALUES (17, "Stem Bowl with Underglaze Blue and Overglaze Red Design of Sea and Animals","https://www.shanghaimuseum.net/museum/frontend/en/articles/CI00004196.html","Ceramic","//www.shanghaimuseum.net/resource/museum_files/resource_files/20150424/1429853944617_162.jpg","Ming","Jingdezhen ware",0); 
INSERT INTO Treasure VALUES (18, "Misty River and Layered Peaks","https://www.shanghaimuseum.net/museum/frontend/en/articles/CI00004393.html","Painting","//www.shanghaimuseum.net/resource/museum_files/resource_files/20150424/1429844614609_162.jpg","Northern Song","Wang?Shen",0); 
INSERT INTO Treasure VALUES (19, "Shanglin Sanguan Wuzhu","https://www.shanghaimuseum.net/museum/frontend/en/articles/CI00004040.html","Coin","//www.shanghaimuseum.net/resource/museum_files/resource_files/20150410/1428644352626_162.jpg","Western Han","",0); 
INSERT INTO Treasure VALUES (20, "Rhinoceros Horn Cup with Openwork Flower and Dragon Design","https://www.shanghaimuseum.net/museum/frontend/en/articles/CI00004432.html","Others","//www.shanghaimuseum.net/resource/museum_files/resource_files/20150409/1428558685304_162.jpg","Ming","",0); 
INSERT INTO Treasure VALUES (21, "Da Ke Ding (food vessel)","https://www.shanghaimuseum.net/museum/frontend/en/articles/CI00004290.html","Bronze","//www.shanghaimuseum.net/resource/museum_files/resource_files/20150410/1428630108517_162.jpg","Western Zhou","",1); 
INSERT INTO Treasure VALUES (22, "Gu (wine vessel) with Three Chi-dragons Design","https://www.shanghaimuseum.net/museum/frontend/en/articles/CI00004318.html","Jade","//www.shanghaimuseum.net/resource/museum_files/resource_files/20150408/1428480476898_162.jpg","Qing","",0); 
INSERT INTO Treasure VALUES (23, "Long Vest Adorned with Shell Beadwork","https://www.shanghaimuseum.net/museum/frontend/en/articles/CI00004337.html","Others","//www.shanghaimuseum.net/resource/museum_files/resource_files/20150409/1428549900850_162.jpg","20th centry","",0); 
INSERT INTO Treasure VALUES (24, "Gong Fu Yi Gong (wine vessel)","https://www.shanghaimuseum.net/museum/frontend/en/articles/CI00004285.html","Bronze","","Shang","",0); 
INSERT INTO Treasure VALUES (25, "Long Table with Recessed Legs","https://www.shanghaimuseum.net/museum/frontend/en/articles/CI00004134.html","Others","//www.shanghaimuseum.net/resource/museum_files/resource_files/20150409/1428563414231_162.jpg","Qing","",0); 
INSERT INTO Treasure VALUES (26, "Zhi (scabbard slide) with Dragon and Phoenix Design","https://www.shanghaimuseum.net/museum/frontend/en/articles/CI00004309.html","Jade","//www.shanghaimuseum.net/resource/museum_files/resource_files/20150409/1428541760398_162.jpg","Warring States","",1); 
INSERT INTO Treasure VALUES (27, "Seal with Inscription Qi Wang Guo Si Yin","https://www.shanghaimuseum.net/museum/frontend/en/articles/CI00004350.html","Seal","//www.shanghaimuseum.net/resource/museum_files/resource_files/20150409/1428566849869_162.jpg","Tang","",0); 
INSERT INTO Treasure VALUES (28, "Xi (seal) with Whorl Pattern","https://www.shanghaimuseum.net/museum/frontend/en/articles/CI00004348.html","Seal","//www.shanghaimuseum.net/resource/museum_files/resource_files/20150409/1428566339182_162.jpg","Western Zhou","",0); 
INSERT INTO Treasure VALUES (29, "Square Plaque with Chi-dragon Design","https://www.shanghaimuseum.net/museum/frontend/en/articles/CI00004316.html","Jade","//www.shanghaimuseum.net/resource/museum_files/resource_files/20160427/1461720466984_162.jpg","Ming","",0); 
INSERT INTO Treasure VALUES (30, "Ge-type Bowl","https://www.shanghaimuseum.net/museum/frontend/en/articles/CI00004198.html","Ceramic","//www.shanghaimuseum.net/resource/museum_files/resource_files/20150424/1429852560443_162.jpg","Ming","Jingdezhen ware",1); 
INSERT INTO Treasure VALUES (31, "White Glazed Pot with Handles and Stringing Holes","https://www.shanghaimuseum.net/museum/frontend/en/articles/CI00004213.html","Ceramic","//www.shanghaimuseum.net/resource/museum_files/resource_files/20150424/1429844385259_162.jpg","Five Dynasties","Xing ware",0); 
INSERT INTO Treasure VALUES (32, "Two Birds Sitting on Rock","https://www.shanghaimuseum.net/museum/frontend/en/articles/CI00004462.html","Painting","//www.shanghaimuseum.net/resource/museum_files/resource_files/20150414/1429000038060_162.jpg","Qing","Zhu Da",0); 
INSERT INTO Treasure VALUES (33, "Snow Scenes","https://www.shanghaimuseum.net/museum/frontend/en/articles/CI00004154.html","Painting","//www.shanghaimuseum.net/resource/museum_files/resource_files/20150417/1429245480923_162.jpg","Southern Song","",0); 
INSERT INTO Treasure VALUES (34, "Statue of Prince Siddhartha Made for Daochang","https://www.shanghaimuseum.net/museum/frontend/en/articles/CI00004506.html","Sculpture","//www.shanghaimuseum.net/resource/museum_files/resource_files/20150410/1428632981637_162.jpg","Northern Qi","",0); 
INSERT INTO Treasure VALUES (35, "Baby Carrier with Horsehair Embroidery","https://www.shanghaimuseum.net/museum/frontend/en/articles/CI00004341.html","Others","//www.shanghaimuseum.net/resource/museum_files/resource_files/20150424/1429842080807_162.jpg","20th centry","",0); 
INSERT INTO Treasure VALUES (36, "Ode to Peony","https://www.shanghaimuseum.net/museum/frontend/en/articles/CI00004016.html","Calligraphy","//www.shanghaimuseum.net/resource/museum_files/resource_files/20150504/1430719712778_162.jpg","Ming","Zhu Yunming",1); 


-- ----------------Insert Sample Contain
-- contain(museum_id, treasure_id)
-- insert with jdbc
INSERT INTO Contain VALUES (1,1); 
INSERT INTO Contain VALUES (1,2); 
INSERT INTO Contain VALUES (1,3); 
INSERT INTO Contain VALUES (1,4); 
INSERT INTO Contain VALUES (1,5); 
INSERT INTO Contain VALUES (1,6); 
INSERT INTO Contain VALUES (1,7); 
INSERT INTO Contain VALUES (1,8); 
INSERT INTO Contain VALUES (1,9); 
INSERT INTO Contain VALUES (1,10); 
INSERT INTO Contain VALUES (1,11); 
INSERT INTO Contain VALUES (1,12); 
INSERT INTO Contain VALUES (1,13); 
INSERT INTO Contain VALUES (2,14); 
INSERT INTO Contain VALUES (2,15); 
INSERT INTO Contain VALUES (2,16); 
INSERT INTO Contain VALUES (2,17); 
INSERT INTO Contain VALUES (2,18); 
INSERT INTO Contain VALUES (2,19); 
INSERT INTO Contain VALUES (2,20); 
INSERT INTO Contain VALUES (2,21); 
INSERT INTO Contain VALUES (2,22); 
INSERT INTO Contain VALUES (2,23); 
INSERT INTO Contain VALUES (2,24); 
INSERT INTO Contain VALUES (3,25); 
INSERT INTO Contain VALUES (3,26); 
INSERT INTO Contain VALUES (3,27); 
INSERT INTO Contain VALUES (3,28); 
INSERT INTO Contain VALUES (3,29); 
INSERT INTO Contain VALUES (4,30); 
INSERT INTO Contain VALUES (4,31); 
INSERT INTO Contain VALUES (4,32); 
INSERT INTO Contain VALUES (4,33); 
INSERT INTO Contain VALUES (4,34); 
INSERT INTO Contain VALUES (4,35); 
INSERT INTO Contain VALUES (4,36);



 -- ---------- Insert Sample List
-- Lists(list_id,list_name ,date_created ,date_modified ,`count` ,);
-- Default 
insert into Lists
values(0, "My Favoraites", now(),now(),0);

