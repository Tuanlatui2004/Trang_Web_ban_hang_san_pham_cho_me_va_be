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
--
--  Table structure for table `cart_items`
--


DROP TABLE IF EXISTS `card`;
CREATE TABLE `card` (
                        `id` int(11) NOT NULL AUTO_INCREMENT,
                        `user_id` int(11) DEFAULT NULL,
                        `duration` date DEFAULT NULL,
                        `type` varchar(50) DEFAULT NULL,
                        `is_default` tinyint(4) DEFAULT NULL,
                        PRIMARY KEY (`card_id`),
                        KEY `user_id` (`user_id`),
                        CONSTRAINT `fk_card` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
--  -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --

--
--  Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
CREATE TABLE `categories` (
                              `id` int(11) NOT NULL AUTO_INCREMENT,
                              `name` varchar(255) NOT NULL,
                              PRIMARY KEY (`category_id`),
                              UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--  -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --



--  -- -- -- -- -- -- -- -- -- -- -- -- -- --
--  Table structure for image
--  -- -- -- -- -- -- -- -- -- -- -- -- -- --
DROP TABLE IF EXISTS `image`;
CREATE TABLE `image` (
                         `id` int(11) NOT NULL AUTO_INCREMENT,
                         `url` text NOT NULL,
                         PRIMARY KEY (`image_id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--  -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --

--

--

--  -- -- -- -- -- -- -- -- -- -- -- -- -- --
--  Table structure for option_variant_value
--  -- -- -- -- -- -- -- -- -- -- -- -- -- --
DROP TABLE IF EXISTS `option_variant_value`;
CREATE TABLE `option_variant_value` (
                                        `id` int(11) NOT NULL AUTO_INCREMENT,
                                        `option_id` int(11) DEFAULT NULL,
                                        `variant_id` int(11) DEFAULT NULL,
                                        PRIMARY KEY (`option_variant_value_id`),
                                        KEY `fk_option_variant_value_1` (`option_id`),
                                        KEY `fk_option_variant_value_2` (`variant_value_id`),
                                        CONSTRAINT `fk_option_variant_value_1` FOREIGN KEY (`option_id`) REFERENCES `option_variant` (`id`),
                                        CONSTRAINT `fk_option_variant_value_2` FOREIGN KEY (`variant_id`) REFERENCES `variant_value` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--  -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --

--  -- -- -- -- -- -- -- -- -- -- -- -- -- --
--  Table structure for option_variant
--  -- -- -- -- -- -- -- -- -- -- -- -- -- --
DROP TABLE IF EXISTS `option_variant`;
CREATE TABLE `option_variant` (
                                  `id` int(11) NOT NULL AUTO_INCREMENT,
                                  `product_id` int(11) DEFAULT NULL,
                                  `price` int(11) DEFAULT NULL,
                                  `stock` int(11) DEFAULT NULL,
                                  PRIMARY KEY (`option_id`),
                                  KEY `option_variant_fk_1` (`product_id`),
                                  CONSTRAINT `option_variant_fk_1` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT    CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

