USE Pizzeria
GO


-- Statystyki klienta
-- drop procedure client_statistics
CREATE PROCEDURE client_statistics
(	
	@client_id INT
)
AS
BEGIN
	DECLARE @overall_value MONEY;
	SET @overall_value = (SELECT price FROM client_value WHERE client_value.client_Id = @client_id);

	DECLARE @number_of_orders INT;
	SET @number_of_orders = 
	(
		SELECT COUNT(o.id) FROM "order" o
		JOIN client c ON c.id = o.client_id
		WHERE c.id = @client_id
	)

	DECLARE @MIN_order_value MONEY;
	SET @MIN_order_value = 
	(
		SELECT MIN(ov.price) FROM "order" o
		JOIN client c ON c.id = o.client_id
		JOIN order_value ov ON ov.order_id = o.id
		WHERE c.id = @client_id
	)

	DECLARE @MAX_order_value MONEY;
	SET @MAX_order_value = 
	(
		SELECT MAX(ov.price) FROM "order" o
		JOIN client c ON c.id = o.client_id
		JOIN order_value ov ON ov.order_id = o.id
		WHERE c.id = @client_id
	)

	SELECT c.first_name, c.last_name, @number_of_orders number_of_orders, @overall_value overall_value, @overall_value/@number_of_orders Value_per_order, @MAX_order_value MAX_order_value, @MIN_order_value MIN_order_value FROM client c
	WHERE c.id = @client_id

END
GO

-- Zwraca cen� pojedynczego zam�wienia.
CREATE PROCEDURE order_price
(	
	@order_id INT
)
AS
BEGIN
	SELECT price FROM order_value
	WHERE order_id = @order_id
END
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

-- Sprawdzanie numeru zam�wienia klienta. Je�li trzecie z kolei to naliczana zni�ka 20%, je�li pierwsze zam�wienie to 10%.
--drop trigger insert_order
CREATE TRIGGER insert_order
ON "Order"
AFTER INSERT
AS
  BEGIN
	DECLARE @id INT
	DECLARE @client_id INT
	DECLARE @discount INT

    SELECT @id = id, @client_id = client_id, @discount = discount
    FROM INSERTED

	IF ((SELECT amount_of_orders FROM Client WHERE id = @client_id) = 0 AND @discount = 0)
			SET @discount = 10
	ELSE IF ((SELECT amount_of_orders FROM Client WHERE id = @client_id) % 3 = 0 AND @discount = 0)
			SET @discount = 20


	UPDATE "order"
	SET discount = @discount
	WHERE id = @id

  END
GO

-- Wstawianie nowego adresu. Sprawdzenie czy adres jest z Poznania.
--drop trigger insert_address_trigger
CREATE TRIGGER insert_address_trigger
ON "Address"
AFTER INSERT
AS
BEGIN
	DECLARE @city NVARCHAR(100) = (SELECT City FROM "Address" WHERE id = @@IDENTITY)
	IF NOT (@city = 'Pozna�')			
	BEGIN
		ROLLBACK TRANSACTION;
		THROW 51000, 'Obs�ugujemy tylko miasto Pozna�. Przepraszamy :(', 1
	END
END
GO

-- Po wybraniu dania zmiejszamy ilo�� sk�adnik�w w magazynie.
--drop trigger insert_ordered_dish_decrease_ingredients
CREATE TRIGGER insert_ordered_dish_decrease_ingredients
ON ordered_dish
AFTER INSERT
AS
	BEGIN
		DECLARE @dish_menu_id INT = (SELECT dish_menu_id FROM INSERTED)
		DECLARE @ingredient_id INT

		DECLARE db_cursor CURSOR FOR 
		SELECT i.id FROM ingredient i
		JOIN basic_ingredient bi ON i.id = bi.ingredient_id
		WHERE bi.dish_menu_id = @dish_menu_id

	
	OPEN db_cursor  
	FETCH NEXT FROM db_cursor INTO @ingredient_id

	WHILE @@FETCH_STATUS = 0  
	BEGIN  
		DECLARE @name NVARCHAR(100)
		SELECT @name = "name" FROM ingredient WHERE id = @ingredient_id

		DECLARE @msg NVARCHAR(1000) = 'Brak sk�adnika: ' + @name + '. Przepraszamy.'
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

-- Po dodaniu dodatkowych sk�adnik�w, zmiejszmay ilo�� sk�adnik�w na magazynie.
--drop trigger insert_additional_igredients_decrease_ingredients
CREATE TRIGGER insert_additional_igredients_decrease_ingredients
ON additional_ingredient
AFTER INSERT
AS
	BEGIN
		DECLARE @ordered_dish_id INT = (SELECT ordered_dish_id FROM INSERTED)

		DECLARE @ingredient_id INT

		DECLARE db_cursor CURSOR FOR 
			SELECT i.id FROM ingredient i
			JOIN additional_ingredient ai ON ai.ingredient_id = i.id
			WHERE ai.ordered_dish_id = @ordered_dish_id
	
		OPEN db_cursor  
		FETCH NEXT FROM db_cursor INTO @ingredient_id

		WHILE @@FETCH_STATUS = 0  
		BEGIN  
			DECLARE @name NVARCHAR(100)
			SELECT @name = "name" FROM ingredient WHERE id = @ingredient_id

			DECLARE @msg NVARCHAR(1000) = 'Brak sk�adnika: ' + @name + '. Przepraszamy.'
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
