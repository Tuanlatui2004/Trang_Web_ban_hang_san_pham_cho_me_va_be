DROP TABLE IF EXISTS `address`;
CREATE TABLE IF NOT EXISTS `address` (
    `id` int NOT NULL AUTO_INCREMENT,
    `user_id` int NOT NULL,
    `address_type` enum('shipping','billing') COLLATE utf8mb4_unicode_ci DEFAULT 'shipping',
    `full_name` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
    `phone_number` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
    `street` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL,
    `city` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
    `state` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
    `country` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
    `is_default` tinyint(1) NOT NULL,
    PRIMARY KEY (`address_id`),
    KEY `user_id` (`user_id`),
    CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--  -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --

-- thêm mới từ e-commerce
--  -- -- -- -- -- -- -- -- -- -- -- -- -- --
--  Table structure for attribute
--  -- -- -- -- -- -- -- -- -- -- -- -- -- --
DROP TABLE IF EXISTS `attribute`;
CREATE TABLE `attributes` (
                              `id` int(11) NOT NULL AUTO_INCREMENT,
                              `category_id` int(11) DEFAULT NULL,
                              `name` varchar(255) DEFAULT NULL,
                              PRIMARY KEY (`attribute_id`),
                              KEY `category_id` (`category_id`),
                              CONSTRAINT `fk_category_id` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
--  Table structure for table `banners`
--
--
-- bảng ecommerce
DROP TABLE IF EXISTS `banners`;
CREATE TABLE `banners` (
                           `id` int(11) NOT NULL AUTO_INCREMENT,
                           `image_id` int(11) NOT NULL,
                           `status` varchar(50) NOT NULL,
                           `startDate` date NOT NULL,
                           `endDate` date NOT NULL,
                           PRIMARY KEY (`banner_id`),
                           KEY `image_id` (`image_id`),
                           CONSTRAINT `banners_ibfk_1` FOREIGN KEY (`image_id`) REFERENCES `image` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
--  -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --

--
--  Table structure for table `brands`
--

DROP TABLE IF EXISTS `brands`;
CREATE TABLE IF NOT EXISTS `brands` (
    `id` int NOT NULL AUTO_INCREMENT,
    `name` varchar(191) default null,
    PRIMARY KEY (`brand_id`)
    ) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
