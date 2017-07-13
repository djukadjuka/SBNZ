-- Buyer = 0
-- Seller = 1
-- Manager = 2

insert into base_user 	(id		,name	,surname	,username	,password	,role	,registry_date	)
values					(null	,'Milan','Djurisic'	,'djuka123'	,'123'		,2		,342140400000);

insert into base_user 	(id		,name	,surname	,username	,password	,role	,registry_date	)
values					(null	,'Donald','Draper'	,'donMMan'	,'123'		,1		,360280800000);

insert into base_user 	(id		,name	,surname	,username	,password	,role	,registry_date	)
values					(null	,'Pamela','Griffin'	,'barfly'	,'barfly'	,0		,263167200000);

insert into base_user 	(id		,name	,surname	,username	,password	,role	,registry_date	)
values					(null	,'Tina','Gibson'	,'commando'	,'commando'	,0		,399938400000);

insert into base_user 	(id		,name	,surname	,username	,password	,role	,registry_date	)
values					(null	,'Shelly','Hicks'	,'qqqqqqq'	,'qqqqqqq'	,0		,457567200000);

insert into base_user 	(id		,name	,surname	,username	,password	,role	,registry_date	)
values					(null	,'Terry','Curtis'	,'storm1'	,'storm1'	,0		,449618400000);

--buyer category insterts
insert into buyer_category 	(buyer_category_id	,category_name	,points_percentage	,spending_bound_max	,spending_bound_min)
values 						(1					,'GRAY'			,0					,0					,0);

insert into buyer_category 	(buyer_category_id	,category_name	,points_percentage	,spending_bound_max	,spending_bound_min)
values 						(2					,'BRONZE'		,10					,3000				,1500);

insert into buyer_category 	(buyer_category_id	,category_name	,points_percentage	,spending_bound_max	,spending_bound_min)
values 						(3					,'SILVER'		,20					,5000				,1000);

insert into buyer_category 	(buyer_category_id	,category_name	,points_percentage	,spending_bound_max	,spending_bound_min)
values 						(4					,'GOLD'			,30					,7000				,500);

--buyer inserts
insert into buyer 	(id	,address			,reward_points	,b_category_id)
values 				(3	,'1616 E North St'	,100			,1);
insert into buyer 	(id	,address			,reward_points	,b_category_id)
values 				(4	,'5266 W Adele St'	,240			,2);
insert into buyer 	(id	,address			,reward_points	,b_category_id)
values 				(5	,'2975 Sunset Blvd'	,202			,3);
insert into buyer 	(id	,address			,reward_points	,b_category_id)
values 				(6	,'1102 Camden Ave'	,1004			,4);



--product categories
--BASIC
insert into product_category	(id	,category_name		,max_discount	,parent_category_id)
values 							(1	,'Home Electronics'	,20				,null);

insert into product_category	(id	,category_name		,max_discount	,parent_category_id)
values 							(2	,'Television Sets'	,30				,1);

insert into product_category	(id	,category_name		,max_discount	,parent_category_id)
values 							(3	,'Laptop Computers'	,35				,1);

--FIREARMS
insert into product_category	(id	,category_name	,max_discount	,parent_category_id)
values							(4	,'Firearms'		,20				,null);

--RIFLES
insert into product_category	(id	,category_name	,max_discount	,parent_category_id)
values							(5	,'Rifles'		,25				,4);

insert into product_category	(id	,category_name				,max_discount	,parent_category_id)
values							(6	,'Bolt-Action Rifles'		,30				,5);

insert into product_category	(id	,category_name				,max_discount	,parent_category_id)
values							(7	,'Semi-Automatic Rifles'	,30				,5);

insert into product_category	(id	,category_name				,max_discount	,parent_category_id)
values							(8	,'Automatic Rifles'			,30				,5);

--HANDGUNS
insert into product_category	(id	,category_name	,max_discount	,parent_category_id)
values							(9	,'Handguns'		,20				,4);

insert into product_category	(id	,category_name				,max_discount	,parent_category_id)
values							(10	,'Single-Shot Handguns'		,25				,9);

insert into product_category	(id	,category_name				,max_discount	,parent_category_id)
values							(11	,'Revolvers'				,25				,9);

insert into product_category	(id	,category_name				,max_discount	,parent_category_id)
values							(12	,'Semi-Automatic Handguns'	,35				,9);

insert into product_category	(id	,category_name				,max_discount	,parent_category_id)
values							(13	,'Machine-Pistols'			,35				,9);

--products
----television
insert into product (id, name, amount_in_stock, archived, date_created, min_amount_in_stock, price, restock, p_category_id)
values (null, '65-inch: Samsung UE65KS9500', 50, 0, 1496933218962, 10, 1499.99, 0, 2);

insert into product (id, name, amount_in_stock, archived, date_created, min_amount_in_stock, price, restock, p_category_id)
values (null, '55-inch: LG OLED55E7', 60, 0, 1496933218962, 10, 1200.00, 0, 2);

insert into product (id, name, amount_in_stock, archived, date_created, min_amount_in_stock, price, restock, p_category_id)
values (null, '50-inch: Panasonic TX-50DX802B', 90, 0, 1496933218962, 10, 1399.99, 0, 2);
----laptops
insert into product (id, name, amount_in_stock, archived, date_created, min_amount_in_stock, price, restock, p_category_id)
values (null, 'Dell Inspiron i7559-2512BLK 15.6 Inch FHD Laptop', 90, 0, 1496933218962, 30, 375.99, 0, 3);

insert into product (id, name, amount_in_stock, archived, date_created, min_amount_in_stock, price, restock, p_category_id)
values (null, 'Intel Quad Core i7-6700HQ 2.6 GHz Processor', 100, 0, 1496933218962, 30, 860.99, 0, 3);

insert into product (id, name, amount_in_stock, archived, date_created, min_amount_in_stock, price, restock, p_category_id)
values (null, 'HP 15-F222WM 15.6" Touch Screen Laptop', 120, 0, 1496933218962, 30, 600.99, 0, 3);

--rifles
--bolt-action-rifles
insert into product (	id	,name,									amount_in_stock,archived,	date_created, 	min_amount_in_stock, 	price,	restock,p_category_id)
values				(	null,'Gewehr 98/Karabiner 98k',				1200,			0,			315529200000,	200,					550,	0,		6),
					(	null,'M1903 Springfield',					1000,			0,			315529200000,	300,					540,	0,		6),
					(	null,'M1917 Enfield',						1100,			0,			315529200000,	500,					650,	0,		6),
					(	null,'M48 Mauser',							900,			0,			315529200000,	200,					550,	0,		6),
--semi-automatic-rifles
					(	null,'Mauser M1916',						890,			0,			315529200000,	100,					560,	0,		7),
					(	null,'M1 Garand',							240,			0,			315529200000,	20,						650,	0,		7),
					(	null,'Gewehr 43',							1020,			0,			315529200000,	40,						700,	0,		7),
--automatic rifles
					(	null,'Glorious* AK-47',						3000,			0,			315529200000,	500,					250,	0,		8),
					(	null,'FN SCAR',								1200,			0,			315529200000,	500,					400,	0,		8),
					(	null,'M4A1',								1000,			0,			315529200000,	100,					500,	0,		8),
					(	null,'M16',									1000,			0,			315529200000,	200,					600,	0,		8),
					(	null,'Heckler & Koch G36',					1000,			0,			315529200000,	205,					560,	0,		8),
--single-shot handguns
					(	null,'Queen Anne pistol',					20,				0,			315529200000,	10,						900,	0,		10),
					(	null,'Remington Rider Single Shot Pistol',	35,				0,			315529200000,	10,						990,	0,		10),
					(	null,'Sedgley OSS .38',						40,				0,			315529200000,	10,						800,	0,		10),
--revolvers
					(	null,'Glorious* Colt Anaconda',				5000,			0,			315529200000,	1000,					1200,	0,		11),
					(	null,'Glorious* Colt Diamondback',			3500,			0,			315529200000,	1000,					1000,	0,		11),
					(	null,'Glorious* Colt Python',				3000,			0,			315529200000,	1000,					900,	0,		11),
					(	null,'M1917 revolver',						200,			0,			315529200000,	100,					340,	0,		11),
--semi automatic pistols
					(	null,'AMT Hardballer',						340,			0,			315529200000,	100,					600,	0,		12),
					(	null,'Beretta 92',							500,			0,			315529200000,	100,					650,	0,		12),
					(	null,'Desert Eagle',						600,			0,			315529200000,	100,					750,	0,		12),
					(	null,'Dan Wesson M1911 ACP pistol',			500,			0,			315529200000,	100,					400,	0,		12),
--machine pistols
					(	null,'Heckler & Koch MP7',					500,			0,			315529200000,	100,					1200,	0,		13),
					(	null,'Kel-Tec PLR-16',						500,			0,			315529200000,	100,					1300,	0,		13),
					(	null,'Ruger MP9',							300,			0,			315529200000,	100,					1400,	0,		13);
					
--sales events
insert into sales_event (id		,start_date		,end_date		,event_discount	,event_name)
values					(null	,975711600000	,951865200000	,7				,'Dec-Mar Sales Event'),--12/2/2000 - 3/1/2000
						(null	,951951600000	,959810400000	,8				,'Mar-Jun Sales Event'),--3/2/2000 - 6/1/2000
						(null	,959896800000	,967759200000	,4				,'Jun-Sep Sales Event'),--6/2/2000 - 9/1/2000
						(null	,967845600000	,975625200000	,5				,'Sep-Dec Sales Event'),--9/2/2000 - 12/1/2000
						(null	,977353200000	,978217200000	,10				,'Merry Chrismas Sales Event'), -- 12/21/2000 - 12/31/2000
						(null	,968536800000	,968709600000	,20				,'9-11 Sales Event'),	-- 9/10/2000 - 9/12/2000
						(null	,946681200000	,975625200000	,2				,'FOREVER');	

insert into product_category_relation 	(	event_id	,product_category_id)
values 									(	1			,1),
										(	2			,1),
										(	3			,1),
										(	4			,1),
										(	5			,1),
										(	6			,1),
										(	7			,1),
--set of 6
										(	1			,2),
										(	2			,2),
										(	3			,2),
										(	4			,2),
										(	5			,2),
										(	6			,2),
										(	7			,2),
--set of 6
										(	1			,3),
										(	2			,3),
										(	3			,3),
										(	4			,3),
										(	5			,3),
										(	6			,3),
										(	7			,3),
--set of 6
										(	1			,4),
										(	2			,4),
										(	3			,4),
										(	4			,4),
										(	5			,4),
										(	6			,4),
										(	7			,4),
--set of 6
										(	1			,5),
										(	2			,5),
										(	3			,5),
										(	4			,5),
										(	5			,5),
										(	6			,5),
										(	7			,5),
--set of 6
										(	1			,6),
										(	2			,6),
										(	3			,6),
										(	4			,6),
										(	5			,6),
										(	6			,6),
										(	7			,6),
--set of 6
										(	1			,7),
										(	2			,7),
										(	3			,7),
										(	4			,7),
										(	5			,7),
										(	6			,7),
										(	7			,7),
--set of 6
										(	1			,8),
										(	2			,8),
										(	3			,8),
										(	4			,8),
										(	5			,8),
										(	6			,8),
										(	7			,8),
--set of 6
										(	1			,9),
										(	2			,9),
										(	3			,9),
										(	4			,9),
										(	5			,9),
										(	6			,9),
										(	7			,9),
--set of 6
										(	1			,10),
										(	2			,10),
										(	3			,10),
										(	4			,10),
										(	5			,10),
										(	6			,10),
										(	7			,10),
--set of 6
										(	1			,11),
										(	2			,11),
										(	3			,11),
										(	4			,11),
										(	5			,11),
										(	6			,11),
										(	7			,11),
--set of 6
										(	1			,12),
										(	2			,12),
										(	3			,12),
										(	4			,12),
										(	5			,12),
										(	6			,12),
										(	7			,12),
--set of 6
										(	1			,13),
										(	2			,13),
										(	3			,13),
										(	4			,13),
										(	5			,13),
										(	6			,13),
										(	7			,13);