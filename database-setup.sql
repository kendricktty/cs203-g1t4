CREATE TABLE users (
	userid int not null primary key,
    username varchar(30) not null,
    passwordHash varchar(50) not null,
    salt varchar(10) not null,
    email varchar(50) not null,
    firstName varchar(40) not null,
    lastName varchar(40) not null,
    age int not null,
    heightCM float not null,
    weightKG float not null
);

CREATE TABLE allergies (
	userid int not null,
    allergy varchar(15) not null,
    constraint allergies_pk primary key(userid, allergy),
    constraint user_pk foreign key(userid) references users(userid)
);

CREATE TABLE ingredient (
	ingredientid int not null primary key,
    name varchar(30) not null,
    ingredientType varchar(9)
);

CREATE TABLE inventory (
    userid int not null,
    ingredientid int not null,
    quantity int not null,
    constraint inventory_pk primary key(userid, ingredientid),
    constriant inventory_user_fk foreign key(userid) references users(userid),
    constraint inventory_ingredient_fk foreign key(ingredientid) references ingredient(ingredientid)
);

CREATE TABLE recipe(
    recipeid int not null primary key,
    data json not null,
    nutrition_information json not null
);

CREATE TABLE recipe_ingredients(
    recipeid int not null,
    ingredientid int not null,
    constraint recipe_ingredients_pk primary key(recipeid, ingredientid),
    constraint recipe_fk foreign key(recipeid) references recipe(recipeid),
    constraint ingredient_fk foreign key(ingredientid) references ingredient(ingredientid)
);

CREATE TABLE favourite_recipes(
    userid int not null,
    recipeid int not null,
    constraint favourite_recipes_pk primary key(userid, recipeid),
    constraint favourite_recipes_user_fk foreign key(userid) references users(userid),
    constraint favourite_recipes_recipe_fk foreign key(recipeid) references recipe(recipeid)
);

/*INSERT INTO ingredient TABLE*/
INSERT INTO ingredient VALUES
(101, "meats", "Beef"),
(102, "meats", "Chicken"),
(103, "meats", "Duck"),
(104, "meats", "Pork"),
(105, "meats", "Lamb"),
(106, "meats", "Venison"),

(201, "seafoods", "Salmon"),
(202, "seafoods", "Tuna"),
(203, "seafoods", "Mackerel"),
(204, "seafoods", "Cod"),
(205, "seafoods", "Squid"),
(206, "seafoods", "Prawn"),

(301, "vegetables", "Asparagus"),
(302, "vegetables", "Bean"),
(303, "vegetables", "Broccoli"),
(304, "vegetables", "Cauliflower"),
(305, "vegetables", "Cabbage"),
(306, "vegetables", "Egg Plant"),
(307, "vegetables", "Onion"),
(308, "vegetables", "Potato"),
(309, "vegetables", "Tomato"),
(310, "vegetables", "Kimchi"),

(401, "dairies", "Butter"),
(402, "dairies", "Cheese"),
(403, "dairies", "Cream"),
(404, "dairies", "Milk"),
(405, "dairies", "Yoghurt"),

(501, "fruits", "Apple"),
(502, "fruits", "Avocado"),
(503, "fruits", "Banana"),
(504, "fruits", "Blackberry"),
(505, "fruits", "Blueberry"),
(506, "fruits", "Grapefruit"),
(507, "fruits", "Orange"),
(508, "fruits", "Pineapple"),
(509, "fruits", "Lemon"),
(510, "fruits", "Lime"),
(511, "fruits", "Strawberry"),

(601, "others", "Miso"),
(602, "others", "Egg");