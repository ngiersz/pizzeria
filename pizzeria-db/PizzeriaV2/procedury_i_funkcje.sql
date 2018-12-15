USE Pizzeria
GO

--drop procedure insert_client
--CREATE PROCEDURE insert_client
--(	
--    @first_name NVARCHAR(100),
--    @last_name NVARCHAR(100),
--	@email NVARCHAR(100),
--	@phone_number NVARCHAR(12),
--	@address_id INT
--)
--AS
--BEGIN
--	INSERT INTO Client (first_name, last_name, "login", email, phone_number, address_id )  
--	VALUES 
--	(@first_name, @last_name, 'tempLogin', @email, @phone_number, @address_id)	

--	--UPDATE Client
--	--SET "login" = first_name + "last_name" + CONVERT(NVARCHAR(255),id)
--	--WHERE id = @@IDENTITY
--END
--GO

--exec insert_client 'Imie', 'Nazwisko', 'agafgdf@sdfsdf.com', '123456789', 1

--drop PROCEDURE insert_address
--CREATE PROCEDURE insert_address
--(	
--    @street NVARCHAR(100),
--    @apartment_number INT,
--	@city NVARCHAR(100),
--	@district NVARCHAR(100)
--)
--AS
--BEGIN
--	INSERT INTO "Address" (street, apartment_number, city, district )  
--	VALUES 
--	(@street, @apartment_number, @city, @district )  	
--END
----exec insert_address 'Os. Boles³awa Œmia³ego', 111, 'Poznañ', 'Pi¹tkowo'
--GO

--drop PROCEDURE update_client

--CREATE PROCEDURE update_client
--(	
--	@id INT,
--    @first_name NVARCHAR(100),
--    @last_name NVARCHAR(100),
--	@email NVARCHAR(100),
--	@phone_number NVARCHAR(12),
--	@address_id INT

--)
--AS
--BEGIN
--      UPDATE Client
--      SET 
--	  first_name = @first_name,
--	  last_name = @last_name,
--	  email = @email,
--	  phone_number = @phone_number,
--	  address_id = @address_id

--	  WHERE id = @id
--END
----exec update_client 36,'Imie4', 'Nazwisko', 'cit@gmail.comm', '123123123', 1
--GO

--drop PROCEDURE create_login

--CREATE PROCEDURE create_login
--AS
--  BEGIN
--  	  PRINT @@IDENTITY
--      UPDATE Client
--      SET "login" = first_name + "last_name" + CONVERT(NVARCHAR(255),id)
--	  WHERE id = @@IDENTITY
--  END  
--GO

--CREATE FUNCTION count_orders
--(	
--    @id INT
--)
--RETURNS INT
--AS
--BEGIN
--	RETURN(SELECT COUNT(id) FROM "Order" 
--	WHERE client_id = @id)
--END
--GO

--CREATE PROCEDURE increment_amount_of_orders (@client_id INT)
--AS
--	BEGIN
--		UPDATE Client
--		SET amount_of_orders = amount_of_orders + 1
--		WHERE id = @client_id;
--	END
--GO
