USE master
DROP DATABASE Pizzeria

CREATE DATABASE Pizzeria
GO

USE Pizzeria
GO

--drop table "address"
CREATE TABLE "address"
(
    id INT PRIMARY KEY IDENTITY,
    street NVARCHAR(100),
    apartment_number INT,
	city NVARCHAR(100),
	district NVARCHAR(100)
);

--drop table client
CREATE TABLE client
(
    id INT PRIMARY KEY IDENTITY,
    first_name NVARCHAR(100),
    last_name NVARCHAR(100),
	"login" NVARCHAR(100),
	email NVARCHAR(100),
	phone_number NVARCHAR(12),
	address_id INT FOREIGN KEY REFERENCES Address(id),
	amount_of_orders INT DEFAULT 0
);


CREATE TABLE deliverer
(
    id INT PRIMARY KEY IDENTITY NOT NULL,
    first_name NVARCHAR(100) NOT NULL,
    last_name NVARCHAR(100) NOT NULL,
);

CREATE TABLE cook
(
    id INT PRIMARY KEY IDENTITY NOT NULL,
    first_name NVARCHAR(100) NOT NULL,
    last_name NVARCHAR(100) NOT NULL,
);

CREATE TABLE "order"
(
    id INT PRIMARY KEY IDENTITY NOT NULL,
	client_id INT FOREIGN KEY REFERENCES Client(id),
	delivery_time INT DEFAULT 60,
	deliverer_id INT FOREIGN KEY REFERENCES Deliverer(id),
	cook_id INT FOREIGN KEY REFERENCES Cook(id),
	discount INT CHECK (discount BETWEEN 0 AND 100) DEFAULT 0, -- TODO 
	completed BIT DEFAULT 0,
);

drop table dish_menu
drop table ordered_dish

--drop table dish_menu
CREATE TABLE dish_menu
(
    id INT PRIMARY KEY IDENTITY NOT NULL,
	"name" NVARCHAR(100) NOT NULL,
	price MONEY NOT NULL,
	quantity INT NOT NULL,
	last_modification INT DEFAULT 1
);
--drop table ordered_dish
CREATE TABLE ordered_dish
(
    id INT PRIMARY KEY IDENTITY NOT NULL,
	order_id INT FOREIGN KEY REFERENCES "Order"(Id),
	dish_menu_id INT FOREIGN KEY REFERENCES Dish_Menu(id)
);

CREATE TABLE ingredient
(
    id INT PRIMARY KEY IDENTITY NOT NULL,
	"name" NVARCHAR(100) NOT NULL,
	category NVARCHAR(100) NOT NULL, -- TODO
	price MONEY NOT NULL,
	quantity_in_storeroom INT NOT NULL

);

CREATE TABLE additionalIngredient
(
    id INT PRIMARY KEY IDENTITY NOT NULL,
	ordered_dish_id INT FOREIGN KEY REFERENCES OrderedDish(id),
	ingredient_id INT FOREIGN KEY REFERENCES Ingredient(id)
);

CREATE TABLE basicIngredient
(
    id INT PRIMARY KEY IDENTITY NOT NULL,
	dish_menu_id INT FOREIGN KEY REFERENCES DishMenu(id),
	ingredient_id INT FOREIGN KEY REFERENCES Ingredient(id),
	quantity INT NOT NULL
);



--------------- UZUPE£NIENIE BAZY -----------------------
INSERT INTO "Address" (street, apartment_number, city, district)  
VALUES 
( 'Os. Boles³awa Chrobrego', '2', 'Poznañ', 'Pi¹tkowo'),  
( 'Wojska Polskiego', '55', 'Poznañ', 'Rataje')

INSERT INTO Client (first_name, last_name, email, phone_number, address_id )  
VALUES 
( 'John', 'Musician', 'john.musician@gmail.com', '+48123456789', 1)
INSERT INTO Client (first_name, last_name, email, phone_number, address_id )  
VALUES 
( 'Charlie', 'Watson', 'charlie.watson@gmail.com', '123456789', 2)
INSERT INTO Client (first_name, last_name, email, phone_number, address_id )  
VALUES 
( 'John', 'Doe', 'mymail@gmail.com', '111222333', 1)
INSERT INTO Client (first_name, last_name, email, phone_number, address_id )  
VALUES 
( 'Sarah', 'Jane', 'sa.ja@yahoo.com', '191919191', 2)



INSERT INTO Dish_Menu( "name", price,quantity)  
VALUES 
('NajlepszeDanie2', 11, 10)

INSERT INTO Deliverer(first_name, last_name)  
VALUES 
( 'Dostawca', 'Dostawczyk')

INSERT INTO Cook(first_name, last_name)  
VALUES 
( 'Kucharz', 'Kucharzyk')

INSERT INTO "Order"(client_id, delivery_time, deliverer_id, cook_id, discount)  
VALUES 
( 5, 30, 1, 1, 0)

INSERT INTO orderedDish(order_id, dish_menu_id)  
VALUES 
( 10, 1)

INSERT INTO Ingredient("name", category, price, quantity_in_storeroom)  
VALUES 
( 'Skladnik', 'Owoce', 2.99, 13)

INSERT INTO AdditionalIngredient(ordered_dish_id, ingredient_id)  
VALUES 
( 1, 1)

INSERT INTO BasicIngredient(dish_menu_id, Ingredient_id, Quantity)  
VALUES 
( 1, 1, 13)

DBCC CHECKIDENT ('[order]', RESEED, 0);
GO

DELETE "order"
delete "Address"
delete Client
delete ordered_dish
delete dish_menu
select * from Client
select * from "Address"
select * from cook
select * from deliverer
select * from "order"
select * from dish_Menu
select * from ordered_dish


delete dish_menu
where id = 1




