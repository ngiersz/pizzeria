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
	district NVARCHAR(100),
	rv rowversion
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
	amount_of_orders INT DEFAULT 0,
	rv rowversion
);


CREATE TABLE deliverer
(
    id INT PRIMARY KEY IDENTITY NOT NULL,
    first_name NVARCHAR(100) NOT NULL,
    last_name NVARCHAR(100) NOT NULL,
	rv rowversion
);

CREATE TABLE cook
(
    id INT PRIMARY KEY IDENTITY NOT NULL,
    first_name NVARCHAR(100) NOT NULL,
    last_name NVARCHAR(100) NOT NULL,
	rv rowversion
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
	rv rowversion
);

--drop table dish_menu
CREATE TABLE dish_menu
(
    id INT PRIMARY KEY IDENTITY NOT NULL,
	"name" NVARCHAR(100) NOT NULL,
	price MONEY NOT NULL,
	rv rowversion
);

--drop table ordered_dish
CREATE TABLE ordered_dish
(
    id INT PRIMARY KEY IDENTITY NOT NULL,
	order_id INT FOREIGN KEY REFERENCES "Order"(Id),
	dish_menu_id INT FOREIGN KEY REFERENCES Dish_Menu(id),
	rv rowversion
);

CREATE TABLE ingredient
(
    id INT PRIMARY KEY IDENTITY NOT NULL,
	"name" NVARCHAR(100) NOT NULL,
	price MONEY NOT NULL,
	quantity_in_storeroom INT NOT NULL,
	rv rowversion

);

-- drop table additional_ingredient
CREATE TABLE additional_ingredient
(
    id INT PRIMARY KEY IDENTITY NOT NULL,
	ordered_dish_id INT FOREIGN KEY REFERENCES ordered_dish(id),
	ingredient_id INT FOREIGN KEY REFERENCES ingredient(id),
	rv rowversion
);

-- drop table basic_Ingredient
CREATE TABLE basic_ingredient
(
    id INT PRIMARY KEY IDENTITY NOT NULL,
	dish_menu_id INT FOREIGN KEY REFERENCES dish_menu(id),
	ingredient_id INT FOREIGN KEY REFERENCES ingredient(id),
	rv rowversion
);



