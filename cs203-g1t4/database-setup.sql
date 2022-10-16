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