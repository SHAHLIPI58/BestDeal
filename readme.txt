In order to run the entire application, mysql and mongodb should be up and running.

To run the project:
1.	Install Apache tomcat with correct configurations
2.	Copy the project in Apache tomcat folder 
3.	Install and configure MYSQL and mongo DB
4.	Fire the below My SQl and Mongo DB queries

MySQL Queries in workbench Mysql:
Show databases;
use exampledatabase;

create table Registration 
(
username varchar(40),password varchar(40),repassword varchar(40),
usertype varchar(40)
);

Create table Productdetails
(
ProductType varchar(20),
Id varchar(20),
productName varchar(40),
productPrice double,
productImage varchar(40),
productManufacturer varchar(40),
productCondition varchar(40),
productDiscount double,
Primary key(Id) );
Create table CustomerOrders
(
OrderId integer NOT NULL AUTO_INCREMENT,
userName varchar(40),
orderName varchar(40),
orderPrice double,
userAddress varchar(40),
creditCardNo varchar(40),
Primary key(OrderId,userName,orderName)
);

CREATE TABLE Product_accessories 
(
     productName varchar(20),
    accessoriesName  varchar(20),
    FOREIGN KEY (productName) REFERENCES Productdetails(Id) ON DELETE SET NULL
        ON UPDATE CASCADE,
    FOREIGN KEY (accessoriesName) REFERENCES Productdetails(Id) ON DELETE SET NULL
        ON UPDATE CASCADE );

Alter the existing table:

alter table productdetails
add column onsale varchar(15) after productdiscount;
alter table productdetails
add column rebate varchar(15) after onsale;
alter table productdetails
add column quantity integer after productdiscount;


Mongo Commands:
1.	Go to mongoDb bin folder using command prompt.
2.	Run=>  mongod.exe
3.	Double click on mongo.exe and open mongo shell.
4.	Run the following command for mongo

Use CustomerReviews
Show dbs
db.createCollections(“myReviews”)  
show collections
db .myReviews.find()
