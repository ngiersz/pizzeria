USE Pizzeria
GO


--drop trigger insert_client_create_login
CREATE TRIGGER insert_client_create_login
ON Client 
AFTER INSERT
AS
  BEGIN
	--ALTER TABLE Client DISABLE TRIGGER insert_update_client

		UPDATE Client
		SET 
		"login" = first_name + "last_name" + CONVERT(NVARCHAR(255),id),
		amount_of_orders = 0
		WHERE id = @@IDENTITY

	--ALTER TABLE Client ENABLE TRIGGER insert_update_client
  END
GO

--drop trigger insert_order
CREATE TRIGGER insert_order
ON "Order"
AFTER INSERT
AS
  BEGIN
	DECLARE @id INT
	DECLARE @client_id INT
    DECLARE @delivery_time INT
    DECLARE @deliverer_id INT
	DECLARE @cook_id INT
	DECLARE @discount INT
	DECLARE @completed BIT

    SELECT @id = id, @client_id = client_id, @delivery_time = delivery_time,
    @deliverer_id = deliverer_id, @cook_id = cook_id, @discount = discount, @completed = completed 
    FROM INSERTED

	---- sprawdzanie poprawnoœci danych nie wykonuje siê tutaj
	--IF NOT EXISTS (SELECT id FROM Client WHERE id = @client_id )
	--	THROW 51000, 'The Client with specified ID does not exist!', 1
	--ELSE IF NOT EXISTS (SELECT id FROM Deliverer WHERE id = @deliverer_id )
	--	THROW 51000, 'The Deliverer with specified ID does not exist!', 1
	--ELSE IF NOT EXISTS (SELECT id FROM Cook WHERE id = @cook_id )
	--	THROW 51000, 'The Cook with specified ID does not exist!', 1
	--ELSE IF (@delivery_time < 20 )
	--	THROW 51000, 'Delivery time is too short! Minimum 20 minutes.', 1
	-- ELSE 
	IF ((SELECT amount_of_orders FROM Client WHERE id = @client_id) = 0 AND @discount = 0)
			SET @discount = 10
	ELSE IF ((SELECT amount_of_orders FROM Client WHERE id = @client_id) % 3 = 0 AND @discount = 0)
			SET @discount = 20

	UPDATE "order"
	SET discount = @discount
	WHERE id = @id

	UPDATE client
	SET amount_of_orders = amount_of_orders+1
	WHERE id = @client_id

  END
GO


--drop trigger insert_address_trigger
CREATE TRIGGER insert_address_trigger
ON "Address"
AFTER INSERT
AS
BEGIN
	DECLARE @city NVARCHAR(100) = (SELECT City FROM "Address" WHERE id = @@IDENTITY)
	IF NOT (@city = 'Poznañ')			
	BEGIN
		ROLLBACK TRANSACTION;
		THROW 51000, 'Obs³ugujemy tylko miasto Poznañ. Przepraszamy :(', 1
	END
END
GO

--drop trigger insert_order_decrease_ingredients
CREATE TRIGGER insert_ordered_dish_decrease_ingredients
ON ordered_dish
AFTER INSERT
AS
	BEGIN
		DECLARE @dish_menu_id INT = (SELECT dish_menu_id FROM INSERTED)
		DECLARE @ingredient_id INT
		DECLARE db_cursor CURSOR FOR 
		SELECT  i.id FROM ingredient i
		JOIN basic_ingredient bi
		ON i.id = bi.id 
		WHERE dish_menu_id = @dish_menu_id

	
	OPEN db_cursor  
	FETCH NEXT FROM db_cursor INTO @ingredient_id

	WHILE @@FETCH_STATUS = 0  
	BEGIN  
		DECLARE @name NVARCHAR(100)
		SELECT @name = "name" FROM ingredient WHERE id = @ingredient_id

		DECLARE @msg NVARCHAR(1000) = 'Brak sk³adnika: ' + @name + '. Przepraszamy.'
		if ((SELECT quantity_in_storeroom FROM ingredient WHERE id = @ingredient_id) = 0)
			THROW 51002, @msg, 1

		UPDATE ingredient
		SET quantity_in_storeroom = quantity_in_storeroom - 1
		WHERE id = @ingredient_id

		FETCH NEXT FROM db_cursor INTO @ingredient_id
	END 

	CLOSE db_cursor  
	DEALLOCATE db_cursor 

	END
GO



--drop trigger insert_update_client
--CREATE TRIGGER insert_update_client
--ON Client 
--AFTER INSERT, UPDATE
--AS
--  BEGIN
--    DECLARE @id int
--    DECLARE @first_name NVARCHAR(100)
--    DECLARE @last_name NVARCHAR(100)
--	DECLARE @email NVARCHAR(100)
--	DECLARE @phone_number NVARCHAR(12)
--	DECLARE @address_id INT

--  SELECT @id = id, @first_name = first_name, @last_name = last_name, @email = email, @phone_number = phone_number, @address_id = address_id 
--  FROM INSERTED

--  DECLARE @msg NVARCHAR(1000) = 'One of the parameters is null: ' + 
--	'first_name = ' + @first_name + ', ' + 
--	'last_name = ' + @last_name + ', ' + 
--	'email = ' + @email + ', ' + 
--	'phone_number = ' + @phone_number
	
	
--	IF NOT EXISTS (SELECT id FROM Client WHERE id = @id )
--		BEGIN
--			ROLLBACK TRANSACTION;
--			THROW 51000, 'The Client with specified ID does not exist!', 1
--		END	
--	ELSE IF (@first_name = 'null' OR @last_name = 'null' OR @email = 'null' OR @phone_number = 'null') 
--		BEGIN
--			ROLLBACK TRANSACTION;
--			THROW 51001, @msg, 1
--		END
--	ELSE IF NOT(@email LIKE '%_@__%.__%')
--		BEGIN
--			ROLLBACK TRANSACTION;
--			THROW 51002, 'Bad format of email address!', 1
--		END
--	ELSE IF NOT(@phone_number LIKE '+[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]' OR @phone_number LIKE '[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]')
--		BEGIN
--			ROLLBACK TRANSACTION;
--			THROW 51003, 'Bad format of phone number (e.g. +48123456789 or 123456789)!', 1
--		END
--	ELSE IF NOT EXISTS (SELECT id FROM "Address" WHERE id = @address_id )
--		BEGIN
--			ROLLBACK TRANSACTION;
--			THROW 51004, 'The record with specified addressId does not exist!', 1
--		END	

--  END  
--GO
--drop trigger insert_ordered_dish
--CREATE TRIGGER insert_ordered_dish
--ON ordered_dish
--INSTEAD OF INSERT
--AS
--	BEGIN
--		DECLARE 
--			@dish_menu_id INT,
--			@order_id INT

--		SELECT @dish_menu_id = dish_menu_id, @order_id = order_id 
--		FROM INSERTED

--		IF NOT EXISTS (SELECT id FROM dish_menu WHERE id = @dish_menu_id )
--			BEGIN
--				ROLLBACK TRANSACTION;
--				THROW 51000, 'The Dish with specified ID does not exist!', 1
--			END
--		ELSE IF NOT EXISTS (SELECT id FROM "order" WHERE id = @order_id )
--			BEGIN
--				ROLLBACK TRANSACTION;
--				THROW 51000, 'The Order with specified ID does not exist!', 1
--			END	
--	END
--GO