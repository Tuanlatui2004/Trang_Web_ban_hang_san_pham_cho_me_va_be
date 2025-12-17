
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
    PRIMARY KEY (`id`),
    KEY `user_id` (`user_id`),
    CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
    ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

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
                              PRIMARY KEY (`id`),
                              KEY `category_id` (`id`),
                              CONSTRAINT `fk_category_id` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

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
                           PRIMARY KEY (`id`),
                           KEY `image_id` (`image_id`),
                           CONSTRAINT `banners_ibfk_1` FOREIGN KEY (`image_id`) REFERENCES `image` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--  -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --

--
--  Table structure for table `brands`
--

DROP TABLE IF EXISTS `brands`;
CREATE TABLE IF NOT EXISTS `brands` (
                                        `id` int NOT NULL AUTO_INCREMENT,
                                        `name` varchar(191) default null,
    PRIMARY KEY (`id`)
    ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--  -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --

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
                        PRIMARY KEY (`id`),
                        KEY `user_id` (`user_id`),
                        CONSTRAINT `fk_card` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--  -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --

--
--  Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
CREATE TABLE `categories` (
                              `id` int(11) NOT NULL AUTO_INCREMENT,
                              `name` varchar(255) NOT NULL,
                              PRIMARY KEY (`id`),
                              UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--  -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --



--  -- -- -- -- -- -- -- -- -- -- -- -- -- --
--  Table structure for image
--  -- -- -- -- -- -- -- -- -- -- -- -- -- --
DROP TABLE IF EXISTS `image`;
CREATE TABLE `image` (
                         `id` int(11) NOT NULL AUTO_INCREMENT,
                         `url` text NOT NULL,
                         PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--  -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --

--

--

--  -- -- -- -- -- -- -- -- -- -- -- -- -- --
--  Table structure for option_variant_value
--  -- -- -- -- -- -- -- -- -- -- -- -- -- --


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
                                  PRIMARY KEY (`id`),
                                  KEY `option_variant_fk_1` (`product_id`),
                                  CONSTRAINT `option_variant_fk_1` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT    CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
--  Table structure for table `orders`
--

--  -- -- -- -- -- -- -- -- -- -- -- -- -- --
--  Table structure for orders
--  -- -- -- -- -- -- -- -- -- -- -- -- -- --
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
                          `id` int(11) NOT NULL AUTO_INCREMENT,
                          `create_at` date NOT NULL,
                          `payment_status` varchar(50) NOT NULL,
                          `order_status` varchar(50) NOT NULL,
                          `user_id` int(11) NOT NULL,
                          `address_id` int(11) NOT NULL,
                          `card_id` int(11) DEFAULT NULL,
                          `isCOD` tinyint(4) DEFAULT NULL,
                          PRIMARY KEY (`id`),
                          KEY `order_fk1` (`user_id`),
                          KEY `order_fk2` (`address_id`),
                          KEY `order_fk3` (`card_id`),
                          CONSTRAINT `order_fk1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
                          CONSTRAINT `order_fk2` FOREIGN KEY (`address_id`) REFERENCES `address` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
                          CONSTRAINT `order_fk3` FOREIGN KEY (`card_id`) REFERENCES `card` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--  -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --

- -- -- -- -- -- -- -- -- -- -- -- -- -- --
--  Table structure for order_detail
--  -- -- -- -- -- -- -- -- -- -- -- -- -- --
DROP TABLE IF EXISTS `order_detail`;
CREATE TABLE `order_detail` (
                                `id` int(11) NOT NULL AUTO_INCREMENT,
                                `order_id` int(11) NOT NULL,
                                `product_id` int(11) NOT NULL,
                                `quantity` int(11) NOT NULL,
                                `total` decimal(10,2) DEFAULT NULL,
                                PRIMARY KEY (`id`),
                                KEY `order_id` (`order_id`),
                                KEY `product_id` (`product_id`),
                                CONSTRAINT `order_detail_fk_1` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
                                CONSTRAINT `order_detail_fk_2` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--  -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --

--  -- -- -- -- -- -- -- -- -- -- -- -- -- --
--  Table structure for product_images
--  -- -- -- -- -- -- -- -- -- -- -- -- -- --
DROP TABLE IF EXISTS `product_images`;
CREATE TABLE `product_images` (
                                  `id` int(11) NOT NULL AUTO_INCREMENT,
                                  `product_id` int(11) NOT NULL,
                                  `image_id` int(11) NOT NULL,
                                  PRIMARY KEY (`id`),
                                  KEY `product_id` (`product_id`),
                                  KEY `image_id` (`image_id`),
                                  CONSTRAINT `product_images_fk_1` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`) ON DELETE CASCADE,
                                  CONSTRAINT `product_images_fk_2` FOREIGN KEY (`image_id`) REFERENCES `image` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--  -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --

--
--  Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
CREATE TABLE `products` (
                            `id` int(11) NOT NULL AUTO_INCREMENT,
                            `name` varchar(255) NOT NULL,
                            `sku` varchar(50) NOT NULL,
                            `description` text DEFAULT NULL,
                            `is_active` tinyint(1) DEFAULT 1,
                            `brand_id` int(11) DEFAULT NULL,
                            `category_id` int(11) DEFAULT NULL,
                            `no_of_views` int(11) DEFAULT 0,
                            `no_of_sold` int(11) DEFAULT 0,
                            `image_id` int(11) DEFAULT NULL,
                            PRIMARY KEY (`id`),
                            KEY `products_fk_1` (`category_id`),
                            KEY `product_fk_2` (`brand_id`),
                            KEY `products_fk3` (`image_id`),
                            CONSTRAINT `product_fk_2` FOREIGN KEY (`brand_id`) REFERENCES `brands` (`id`),
                            CONSTRAINT `products_fk_3` FOREIGN KEY (`image_id`) REFERENCES `image` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
                            CONSTRAINT `products_fk_1` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--  -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --


--  -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --

--
--  Table structure for table `product_reviews`
--

DROP TABLE IF EXISTS `product_reviews`;
CREATE TABLE IF NOT EXISTS `product_reviews` (
                                                 `id` int(11) NOT NULL AUTO_INCREMENT,
    `user_id` int(11) NOT NULL,
    `product_id` int(11) NOT NULL,
    `rating` int(11) NOT NULL,
    `description` text DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `user_id` (`user_id`),
    KEY `product_id` (`product_id`),
    CONSTRAINT `review_fk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE,
    CONSTRAINT `review_fk_2` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`) ON DELETE CASCADE
    ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--  -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --



--  -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --



--
--  Table structure for table `users`
--


-- ecommerce
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
                         `id` int(11) NOT NULL AUTO_INCREMENT,
                         `fullName` varchar(255) NOT NULL,
                         `displayName` varchar(255) DEFAULT NULL,
                         `dOB` date DEFAULT NULL,
                         `gender` varchar(255) DEFAULT NULL,
                         `phone_number` varchar(12) DEFAULT NULL,
                         `email` varchar(255) NOT NULL,
                         `password_UserName` varchar(255) NOT NULL,
                         `avatar_id` int(11) DEFAULT NULL,
                         `status` enum('PENDING','ACTIVE','BANNED','DEACTIVE') NOT NULL DEFAULT 'PENDING',
                         `confirmationToken` varchar(255) DEFAULT NULL,
                         PRIMARY KEY (`id`),
                         UNIQUE KEY `email` (`email`),
                         KEY `user_fk_1` (`avatar_id`),
                         CONSTRAINT `user_fk_1` FOREIGN KEY (`avatar_id`) REFERENCES `image` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT  CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- them cot role vao users
ALTER TABLE users
    ADD COLUMN role enum('USER','ADMIN') NOT NULL DEFAULT 'USER';

--  -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --

--
--  Table structure for table `variant_attributes`
--

--  -- -- -- -- -- -- -- -- -- -- -- -- -- --
--  Table structure for variant
--  -- -- -- -- -- -- -- -- -- -- -- -- -- --
DROP TABLE IF EXISTS `variant`;
CREATE TABLE `variant` (
                           `id` int(11) NOT NULL AUTO_INCREMENT,
                           `category_id` int(11) DEFAULT NULL,
                           `name` varchar(50) DEFAULT NULL,
                           `value` varchar(100) DEFAULT NULL,
                           PRIMARY KEY (`id`),
                           KEY `fk_category` (`category_id`),
                           CONSTRAINT `fk_category` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

ALTER TABLE variant
ADD COLUMN `option_id` int(11) DEFAULT NULL;
ALTER TABLE variant
ADD CONSTRAINT `fk_option` FOREIGN KEY(`option_id`) REFERENCES `option_variant` (`id`);
--  -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- 



/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
