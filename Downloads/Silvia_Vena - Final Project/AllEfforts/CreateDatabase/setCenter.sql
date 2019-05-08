-- Gugong
set @id := (select treasure_id from Treasure where treasure_name = "Yaxu Bronze Rectangular Vessel");
update treasure set is_centerpiece = 1 where treasure_id = @id;
set @id := (select treasure_id from Treasure where treasure_name = "Red-glazed Lute-shaped Zun Vessel, Lang Ware");
update treasure set is_centerpiece = 1 where treasure_id = @id;
set @id := (select treasure_id from Treasure where treasure_name = "Cloisonné Enamel Burner with Lotus Decoration and Elephant-trunk Handles");
update treasure set is_centerpiece = 1 where treasure_id = @id;
-- Shanghai
set @id := (select treasure_id from Treasure where treasure_name = "Da Ke Ding (food vessel)");
update treasure set is_centerpiece = 1 where treasure_id = @id;
set @id := (select treasure_id from Treasure where treasure_name = "Bells of Marquis Su of Jin");
update treasure set is_centerpiece = 1 where treasure_id = @id;
set @id := (select treasure_id from Treasure where treasure_name = "Vase with Famille Rose Design of Peaches and Bates, Jingdezhen ware");
update treasure set is_centerpiece = 1 where treasure_id = @id;

-- Hunan
set @id := (select treasure_id from Treasure where treasure_name = "T-Shaped Painting on Silk");
update treasure set is_centerpiece = 1 where treasure_id = @id;
set @id := (select treasure_id from Treasure where treasure_name = "Plain Gauze Gown");
update treasure set is_centerpiece = 1 where treasure_id = @id;
set @id := (select treasure_id from Treasure where treasure_name = "Shrimps Playing Around (hanging scroll)");
update treasure set is_centerpiece = 1 where treasure_id = @id;

-- Henan
set @id := (select treasure_id from treasure where treasure_name = "Bronze Jin with cloud design");
update treasure set is_centerpiece = 1 where treasure_id = @id;
set @id := (select treasure_id from treasure where treasure_name = "\"Fuhao\" Owl-shaped bronze Zun, wine container");
update treasure set is_centerpiece = 1 where treasure_id = @id;
set @id := (select treasure_id from treasure where treasure_name = "Sky blue glaze Ru Kiln vase with incised and applied decoration");
update treasure set is_centerpiece = 1 where treasure_id = @id;

-- Shanxi
set @id := (select treasure_id from treasure where treasure_name = "Bronze Tiger-Shaped Tally of Du County");
update treasure set is_centerpiece = 1 where treasure_id = @id;
set @id := (select treasure_id from treasure where treasure_name = "The Empress' Jade Seal Inscribed with Characters \"Huang Hou Zhi Xi\"");
update treasure set is_centerpiece = 1 where treasure_id = @id;
set @id := (select treasure_id from treasure where treasure_name = "Silver Perfume Fumigator");
update treasure set is_centerpiece = 1 where treasure_id = @id;

-- Hubei
set @id := (select treasure_id from treasure where treasure_name = "The sword of Goujian");
update treasure set is_centerpiece = 1 where treasure_id = @id;
set @id := (select treasure_id from treasure where treasure_name = "Chime-bells from the Tomb of Marquis Yi of the Zeng State");
update treasure set is_centerpiece = 1 where treasure_id = @id;
set @id := (select treasure_id from treasure where treasure_name = "Blue and White plum vase of the four loves in Yuan Dynasty");
update treasure set is_centerpiece = 1 where treasure_id = @id;

-- Zhejiang
set @id := (select treasure_id from treasure where treasure_name = "Jade statue of standing Pilgrim Sudhana");
update treasure set is_centerpiece = 1 where treasure_id = @id;
set @id := (select treasure_id from treasure where treasure_name = "Pottery bowl with pig motif");
update treasure set is_centerpiece = 1 where treasure_id = @id;
set @id := (select treasure_id from treasure where treasure_name = "Dish-shaped ivory object adorned with two birds worshipping the sun");
update treasure set is_centerpiece = 1 where treasure_id = @id;

-- liaoning
set @id := (select treasure_id from treasure where treasure_name = "Large Round Tripod (cooking vessel) with Taotie Design");
update treasure set is_centerpiece = 1 where treasure_id = @id;
set @id := (select treasure_id from treasure where treasure_name = "Celadon Flying Fish-shaped Jar (Yu, a broad-mouthed receptacle for holding water)");
update treasure set is_centerpiece = 1 where treasure_id = @id;
set @id := (select treasure_id from treasure where treasure_name = "Facsimile of Zhang Xuan’s Lady Guoguo's Outing in Spring");
update treasure set is_centerpiece = 1 where treasure_id = @id;

-- suzhou
set @id := (select treasure_id from treasure where treasure_name = "Fenqing With Flower-Petal-Shaped Rim-glazed Pomegranate-shaped Zun-vessel");
update treasure set is_centerpiece = 1 where treasure_id = @id;
set @id := (select treasure_id from treasure where treasure_name = "Bamboo Brush Holder Carved with a Lady in Reverie");
update treasure set is_centerpiece = 1 where treasure_id = @id;
set @id := (select treasure_id from treasure where treasure_name = "Square Seal of Yellow and White Shoushan Stone in Lions Knob Design");
update treasure set is_centerpiece = 1 where treasure_id = @id;
-- anhui
set @id := (select treasure_id from treasure where treasure_name = "Rectangular Ding with Pheonix design");
update treasure set is_centerpiece = 1 where treasure_id = @id;
set @id := (select treasure_id from treasure where treasure_name = "Blue Glazed Plate with White Floral Design");
update treasure set is_centerpiece = 1 where treasure_id = @id;
set @id := (select treasure_id from treasure where treasure_name = "Jian (basin) of Prince Guang of Wu");
update treasure set is_centerpiece = 1 where treasure_id = @id;


