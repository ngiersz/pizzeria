USE Pizzeria
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



INSERT INTO Dish_Menu( "name", price)  
VALUES 
('Basic', 17),
('Miêsna', 23),
('Wegetariañska', 10)

INSERT INTO Deliverer(first_name, last_name)  
VALUES 
( 'Dostawca', 'Dostawczyk')

INSERT INTO Cook(first_name, last_name)  
VALUES 
( 'Kucharz', 'Kucharzyk')

INSERT INTO ingredient("name", price, quantity_in_storeroom)  
VALUES 
( 'Pomidor', 2, 20),
( 'Ser', 2, 20),
( 'Sos pomidorowy', 2, 20),
( 'Szynka', 3, 20),
( 'Boczek', 2.5, 20),
( 'Tuñczyk', 5, 20),
( 'Ananas', 2.5, 5),
( 'Cebula', 1.5, 20),
( 'Pieczarki', 2, 20)



INSERT INTO additional_ingredient(ordered_dish_id, ingredient_id)  
VALUES 
( 1, 2)

INSERT INTO basic_ingredient(dish_menu_id, ingredient_id)  
VALUES 
(1,3),
(1,2),
(1,4),

(2,3),
(2,5),
(2,4),
(2,2),
(2,9),

(3,3),
(3,7),
(3,8),
(3,1),
(3,9),
(3,2)

INSERT INTO "Order"(client_id, delivery_time, deliverer_id, cook_id, discount)  
VALUES 
( 1, 0, 1, 1, 0)

INSERT INTO ordered_dish(order_id, dish_menu_id)  
VALUES 
( 1, 1)


-- DELETE "order"
-- delete "Address"
-- delete Client
-- delete ordered_dish
-- delete dish_menu
select * from ingredient
select * from Client
select * from "Address"
select * from cook
select * from deliverer
select * from "order"
select * from dish_Menu
select * from ordered_dish
select * from basic_ingredient
select * from additional_ingredient


select * from dish_menu d
JOIN basic_ingredient bi on d.id = bi.dish_menu_id
JOIN ingredient i on i.id = bi.ingredient_id

