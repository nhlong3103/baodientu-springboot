-- Drop the database if it already exists
DROP DATABASE IF EXISTS baodientu_springboot;
-- Create database
CREATE DATABASE IF NOT EXISTS baodientu_springboot;
USE baodientu_springboot;

-- Create table Danhmuc
DROP TABLE IF EXISTS 	DanhMuc;
CREATE TABLE IF NOT EXISTS DanhMuc (
	id 						INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	ten_danh_muc 			VARCHAR(30) NOT NULL UNIQUE KEY,
    so_luong_bai_viet		INT NOT NULL DEFAULT 0,
    updated_date			DATETIME DEFAULT NOW(),
    created_date			DATETIME DEFAULT NOW()
);

INSERT INTO DanhMuc(ten_danh_muc) 
VALUES 					('danhmuc1'),
						('danhmuc2'),
                        ('danhmuc3'),
                        ('danhmuc4'),
                        ('danhmuc5');

-- Create table Baibao
DROP TABLE IF EXISTS 	BaiBao;
CREATE TABLE IF NOT EXISTS BaiBao (
	id 						INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	tieu_de 				VARCHAR(255) NOT NULL UNIQUE KEY,
    gioi_thieu_ngan			VARCHAR(110) NOT NULL,
    anh_tieu_de				VARCHAR(255) NOT NULL,
    noi_dung				LONGTEXT NOT NULL,
    danhmuc_id				INT UNSIGNED NOT NULL,
    updated_date			DATETIME DEFAULT NOW(),
    created_date			DATETIME DEFAULT NOW()
);

-- create table: Account
DROP TABLE IF EXISTS `Account`;
CREATE TABLE `Account`(
	id						INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    user_name				VARCHAR(50) NOT NULL UNIQUE KEY,
	`email`					VARCHAR(50) NOT NULL UNIQUE KEY,
	`password` 				VARCHAR(100) NOT NULL,
    `role` 					ENUM('ADMIN','CUSTOMER') NOT NULL DEFAULT'CUSTOMER',
    `status`				TINYINT DEFAULT(0) -- 0: chưa kích hoạt, 1 đã kích hoạt
);

-- INSERT INTO `Account`(user_name,`email`,`password`,`role`,`status`)
-- VALUES 				('admin1','email1@gmail.com','$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi','ADMIN',1);

DROP TABLE IF EXISTS Registration_Account_Token;
CREATE TABLE IF NOT EXISTS Registration_Account_Token(
	id						INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    token					VARCHAR(50) NOT NULL UNIQUE,
	account_id				INT UNSIGNED NOT NULL,
    expiry_date				DATETIME NOT NULL
);

DROP TABLE IF EXISTS Reser_Password_Token;
CREATE TABLE IF NOT EXISTS Reser_Password_Token(
	id						INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    token					VARCHAR(50) NOT NULL UNIQUE,
	account_id				INT UNSIGNED NOT NULL,
    expiry_date				DATETIME NOT NULL
);
    
SELECT * FROM DanhMuc;
SELECT * FROM BaiBao;
SELECT * FROM `Account`;
SELECT * FROM Registration_Account_Token;

-- =============================================
-- INSERT DATA 
-- =============================================

-- $2a$10$TsTMXD/vJjvCUrZSyXgB4.4SNpVmExM5G/t0r4Mw5tV1NXPHnZDnm
