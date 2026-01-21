
DROP TABLE IF EXISTS `address`;
CREATE TABLE IF NOT EXISTS `address` (
    `id` int NOT NULL AUTO_INCREMENT,
    `userId` int NOT NULL,
    `addressType` enum('shipping','billing') COLLATE utf8mb4_unicode_ci DEFAULT 'shipping',
    `fullName` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
    `phoneNumber` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
    `street` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL,
    `city` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
    `state` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
    `country` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
    `isDefault` tinyint(1) NOT NULL,
    PRIMARY KEY (`id`),
    KEY `userId` (`userId`),
    CONSTRAINT `fk_address_1` FOREIGN KEY (`userId`) REFERENCES `users` (`id`)
    ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--  -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --

-- thêm mới từ e-commerce
--  -- -- -- -- -- -- -- -- -- -- -- -- -- --
--  Table structure for attribute
--  -- -- -- -- -- -- -- -- -- -- -- -- -- --
DROP TABLE IF EXISTS `attribute`;
CREATE TABLE `attributes` (
                              `id` int(11) NOT NULL AUTO_INCREMENT,
                              `categoryId` int(11) DEFAULT NULL,
                              `name` varchar(255) DEFAULT NULL,
                              PRIMARY KEY (`id`),
                              KEY `categoryId` (`categoryId`),
                              CONSTRAINT `fk_attribute_1` FOREIGN KEY (`categoryId`) REFERENCES `categories` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
--  Table structure  for table `banners`
--
--
-- bảng ecommerce
DROP TABLE IF EXISTS `banners`;
CREATE TABLE `banners` (
                           `id` int(11) NOT NULL AUTO_INCREMENT,
                           `imageId` int(11) NOT NULL,
                           `status` varchar(50) NOT NULL,
                           `startDate` date NOT NULL,
                           `endDate` date NOT NULL,
                           PRIMARY KEY (`id`),
                           KEY `imageId` (`imageId`),
                           CONSTRAINT `fk_banners_1` FOREIGN KEY (`imageId`) REFERENCES `image` (`id`) ON DELETE CASCADE
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
                        `userId` int(11) DEFAULT NULL,
                        `duration` date DEFAULT NULL,
                        `type` varchar(50) DEFAULT NULL,
                        `isDefault` tinyint(4) DEFAULT NULL,
                        PRIMARY KEY (`id`),
                        KEY `fk_card_1` (`userId`),
                        CONSTRAINT `fk_card_1` FOREIGN KEY (`userId`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
ALTER TABLE categories
    ADD COLUMN isActive tinyint(4) DEFAULT 1;
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
                                  `productId` int(11) DEFAULT NULL,
                                  `price` int(11) DEFAULT NULL,
                                  `stock` int(11) DEFAULT NULL,
                                  PRIMARY KEY (`id`),
                                  KEY `fk_option_variant_1` (`productId`),
                                  CONSTRAINT `fk_option_variant_1` FOREIGN KEY (`productId`) REFERENCES `products` (`id`)
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
                          `createAt` date NOT NULL,
                          `paymentStatus` varchar(50) NOT NULL,
                          `orderStatus` varchar(50) NOT NULL,
                          `userId` int(11) NOT NULL,
                          `addressId` int(11) NOT NULL,
                          `cardId` int(11) DEFAULT NULL,
                          `isCOD` tinyint(4) DEFAULT NULL,
                          PRIMARY KEY (`id`),
                          KEY `fk_order_1` (`userId`),
                          KEY `fk_order_2` (`addressId`),
                          KEY `fk_order_3` (`cardId`),
                          CONSTRAINT `fk_order_1` FOREIGN KEY (`userId`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
                          CONSTRAINT `fk_order_2` FOREIGN KEY (`addressId`) REFERENCES `address` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
                          CONSTRAINT `fk_order_3` FOREIGN KEY (`cardId`) REFERENCES `card` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
ALTER TABLE orders
    MODIFY COLUMN orderStatus enum('DELIVERED','DELIVERY','PAID') COLLATE utf8mb4_unicode_ci DEFAULT 'DELIVERY';
--  -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --

- -- -- -- -- -- -- -- -- -- -- -- -- -- --
--  Table structure for order_detail
--  -- -- -- -- -- -- -- -- -- -- -- -- -- --
DROP TABLE IF EXISTS `order_detail`;
CREATE TABLE `order_detail` (
                                `id` int(11) NOT NULL AUTO_INCREMENT,
                                `orderId` int(11) NOT NULL,
                                `productId` int(11) NOT NULL,
                                `quantity` int(11) NOT NULL,
                                `total` decimal(10,2) DEFAULT NULL,
                                PRIMARY KEY (`id`),
                                KEY `fk_order_detail_1` (`orderId`),
                                KEY `fk_product_detail_2` (`productId`),
                                CONSTRAINT `fk_product_detail_1` FOREIGN KEY (`orderId`) REFERENCES `orders` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
                                CONSTRAINT `fk_product_detail_2` FOREIGN KEY (`productId`) REFERENCES `products` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--  -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --

--  -- -- -- -- -- -- -- -- -- -- -- -- -- --
--  Table structure for product_images
--  -- -- -- -- -- -- -- -- -- -- -- -- -- --
DROP TABLE IF EXISTS `product_images`;
CREATE TABLE `product_images` (
                                  `id` int(11) NOT NULL AUTO_INCREMENT,
                                  `productId` int(11) NOT NULL,
                                  `imageId` int(11) NOT NULL,
                                  PRIMARY KEY (`id`),
                                  KEY `product_images_fk_1` (`productId`),
                                  KEY `product_images_fk_2` (`imageId`),
                                  CONSTRAINT `product_images_fk_1` FOREIGN KEY (`productId`) REFERENCES `products` (`id`) ON DELETE CASCADE,
                                  CONSTRAINT `product_images_fk_2` FOREIGN KEY (`imageId`) REFERENCES `image` (`id`) ON DELETE CASCADE
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
                            `isActive` tinyint(1) DEFAULT 1,
                            `brandId` int(11) DEFAULT NULL,
                            `categoryId` int(11) DEFAULT NULL,
                            `noOfViews` int(11) DEFAULT 0,
                            `noOfSold` int(11) DEFAULT 0,
                            `imageId` int(11) DEFAULT NULL,
                            PRIMARY KEY (`id`),
                            KEY `products_fk_1` (`categoryId`),
                            KEY `product_fk_2` (`brandId`),
                            KEY `products_fk3` (`imageId`),
                            CONSTRAINT `product_fk_2` FOREIGN KEY (`brandId`) REFERENCES `brands` (`id`),
                            CONSTRAINT `products_fk_3` FOREIGN KEY (`imageId`) REFERENCES `image` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
                            CONSTRAINT `products_fk_1` FOREIGN KEY (`categoryId`) REFERENCES `categories` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--  -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --


--  -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --

--
--  Table structure for table `product_reviews`
--

DROP TABLE IF EXISTS `product_reviews`;
CREATE TABLE IF NOT EXISTS `product_reviews` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `userId` int(11) NOT NULL,
    `productId` int(11) NOT NULL,
    `rating` int(11) NOT NULL,
    `description` text DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `review_fk_1` (`userId`),
    KEY `review_fk_2` (`productId`),
    CONSTRAINT `review_fk_1` FOREIGN KEY (`userId`) REFERENCES `users` (`id`) ON DELETE CASCADE,
    CONSTRAINT `review_fk_2` FOREIGN KEY (`productId`) REFERENCES `products` (`id`) ON DELETE CASCADE
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
                         `phoneNumber` varchar(12) DEFAULT NULL,
                         `email` varchar(255) NOT NULL,
                         `passwordUserName` varchar(255) NOT NULL,
                         `avatarId` int(11) DEFAULT NULL,
                         `status` enum('PENDING','ACTIVE','BANNED','DEACTIVE') NOT NULL DEFAULT 'PENDING',
                         `confirmationToken` varchar(255) DEFAULT NULL,
                         PRIMARY KEY (`id`),
                         UNIQUE KEY `email` (`email`),
                         KEY `user_fk_1` (`avatarId`),
                         CONSTRAINT `user_fk_1` FOREIGN KEY (`avatarId`) REFERENCES `image` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
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
                           `categoryId` int(11) DEFAULT NULL,
                           `name` varchar(50) DEFAULT NULL,
                           `value` varchar(100) DEFAULT NULL,
                           PRIMARY KEY (`id`),
                           KEY `fk_variant_1` (`categoryId`),
                           CONSTRAINT `fk_variant_1` FOREIGN KEY (`categoryId`) REFERENCES `categories` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

ALTER TABLE variant
ADD COLUMN `optionId` int(11) DEFAULT NULL;
ALTER TABLE variant
ADD CONSTRAINT `fk_variant_2` FOREIGN KEY(`optionId`) REFERENCES `option_variant` (`id`);
--  -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- 



/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
