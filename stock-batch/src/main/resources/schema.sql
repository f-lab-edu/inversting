DROP TABLE IF EXISTS stock;

CREATE TABLE `stock` (
    `id`   BIGINT PRIMARY KEY UNIQUE ,
    `code` VARCHAR (20),
    `short_code` VARCHAR(10),
    `name` VARCHAR(80),
    `short_name`	varchar(40),
    `create_date`	VARCHAR(255) default CURRENT_TIMESTAMP,
    `modified_date`	VARCHAR(255) default CURRENT_TIMESTAMP,
    `status` VARCHAR(50),
    `price` INT
);