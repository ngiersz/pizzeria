USE Pizzeria
GO

-- Wartoœæ ka¿dego zamówienia.
CREATE VIEW order_value AS
(
	SELECT orders_price.order_id, SUM(orders_price.price) price FROM
	(
		SELECT od.order_id , i.price FROM additional_ingredient ai
		JOIN ingredient i ON i.id = ai.ingredient_id
		JOIN ordered_dish od ON od.id = ai.ordered_dish_id
		UNION ALL
		SELECT od.order_id, dm.price FROM ordered_dish od
		JOIN dish_menu dm ON dm.id = od.dish_menu_id
	) AS orders_price
	GROUP BY order_id
)
GO

-- Wydane pieni¹dze przez klientów.
--drop view client_value
CREATE VIEW client_value AS
(
	SELECT TOP 10 ROW_NUMBER() OVER( ORDER BY SUM(order_value.price) DESC) AS id ,c.id client_id, c.first_name, c.last_name, SUM(order_value.price) price FROM order_value
	JOIN "order" o ON o.id = order_value.order_id
	JOIN client c ON c.id = o.client_id
	GROUP BY c.id, c.first_name, c.last_name
	ORDER BY price DESC
)
GO

-- Najczêœciej kupowane pizze.
-- drop view most_popular_pizza
CREATE VIEW most_popular_pizza AS
(
	SELECT ROW_NUMBER()  OVER( ORDER BY top_dishes.quantity DESC) as id, top_dishes.dish_menu_id, dm."name", top_dishes.quantity FROM 
	(
		SELECT TOP(3) od.dish_menu_id, COUNT(od.dish_menu_id) quantity FROM ordered_dish od
		GROUP BY od.dish_menu_id
		ORDER BY quantity DESC
	) AS top_dishes
	JOIN dish_menu dm ON dm.id = top_dishes.dish_menu_id
)
GO

-- Najczêœciej kupuj¹cy klient.
CREATE VIEW most_popular_client AS
(
	SELECT top_clients.client_id, c.first_name, c.last_name, top_clients.quantity FROM 
	(
		SELECT TOP(1) o.client_id, count(o.client_id) quantity FROM "order" o
		GROUP BY o.client_id
		ORDER BY quantity DESC
	) AS top_clients
	JOIN client c ON c.id = top_clients.client_id
)
GO

-- Statystyki klienta
--DECLARE @number_of_clients INT;
--SET @number_of_clients = (SELECT COUNT(client.id) FROM client)
--SELECT	SUM(order_value.price) Overall_Value, 
--		MIN(order_value.price) MIN_Value,
--		MAX(order_value.price) MAX_Value, 
--		SUM(order_value.price)/@number_of_clients Average_Value_for_client 
--FROM order_value
--GO



-- Najmniej popularny sk³adnik. NIE DZIA£A
CREATE VIEW less_popular_ingredient AS
(
	SELECT i.name, SUM(sold_igredients.sold_number) sold_number FROM 
	(
		SELECT bi.ingredient_id, COUNT(i.id) sold_number FROM ordered_dish od
		JOIN basic_ingredient bi ON bi.dish_menu_id = od.dish_menu_id
		JOIN ingredient i ON i.id = bi.ingredient_id
		GROUP BY bi.ingredient_id
		UNION ALL
		SELECT ai.ingredient_id, COUNT(ai.id) sold_number FROM additional_ingredient ai
		JOIN ingredient i ON i.id = ai.ingredient_id
		JOIN ordered_dish od ON od.dish_menu_id = ai.ordered_dish_id
		GROUP BY ai.ingredient_id
	) AS sold_igredients
	JOIN ingredient i ON i.id = sold_igredients.ingredient_id
	GROUP BY sold_igredients.ingredient_id, i.name
	ORDER BY sold_number
)
GO



-- Wartoœæ zamówieñ klientów

	--SELECT c.id client_id, SUM(orders_price.order_price) price FROM
	--(
	--	SELECT orders_ingredients_price.order_id, SUM(orders_ingredients_price.price_for_ingredients) order_price FROM
	--	(
	--		SELECT	orders_ingredients.order_id, 
	--				SUM(orders_ingredients.ingredient_quantity*orders_ingredients.ingredient_price) price_for_ingredients, 
	--				orders_ingredients.ingredient_price 
	--		FROM
	--		(
	--			SELECT od.order_id, COUNT(i.id) ingredient_quantity, i.id ingredient_id, i.price ingredient_price FROM ordered_dish od
	--			JOIN basic_ingredient bi ON bi.dish_menu_id = od.dish_menu_id
	--			JOIN ingredient i ON i.id = bi.ingredient_id
	--			GROUP BY od.order_id, i.id, i.price
	--		) AS orders_ingredients
	--		GROUP BY orders_ingredients.order_id, orders_ingredients.ingredient_id, orders_ingredients.ingredient_price
	--	) AS orders_ingredients_price
	--	GROUP BY orders_ingredients_price.order_id

	--	UNION

	--	SELECT od.order_id , SUM(i.price) price FROM additional_ingredient ai
	--	JOIN ingredient i ON i.id = ai.ingredient_id
	--	JOIN ordered_dish od ON od.dish_menu_id = ai.ordered_dish_id
	--	GROUP BY od.order_id
	
	--) AS orders_price
	--JOIN "order" o ON o.id = orders_price.order_id
	--JOIN client c ON c.id = o.client_id
	--GROUP BY c.id
 
