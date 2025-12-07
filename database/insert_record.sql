-- tạo 1 insert
-- IMAGE
INSERT INTO image (url)
VALUES ('https://cdn.hstatic.net/products/200000178477/anh-web-75cbc1e98a36414f92147be13374b33f_07f684ae33f3432cb9fb68245956bf3d_grande.jpg');
INSERT INTO image (url)
VALUES ('https://cdn1.concung.com/2022/03/48181-84162-large_mobile/sua-uong-dinh-duong-vinamilk-yoko-gold-180ml-loc-4.jpg');
INSERT INTO image (url)
VALUES ('https://cdn1.concung.com/2024/12/69246-116959-large_mobile/sua-uong-dinh-duong-optimum-gold-110ml-loc-4-hop-new.png');
INSERT INTO image (url)
VALUES ('https://cdn1.concung.com/2024/12/62336-116946-large_mobile/sua-uong-dinh-duong-dielac-grow-plus-110ml-sua-non-loc-4-hop.png');
INSERT INTO image (url)
VALUES ('https://cdn1.concung.com/2025/12/74447-132057-large_mobile/bodysuit-be-trai-dui-bst-cute-dino-animo-vd0825051-0-3m-xanh-nhat-nn02.jpg');
INSERT INTO image (url)
VALUES ('https://cdn1.concung.com/2025/02/69816-118140-large_mobile/bodysuit-rayon-jacquard-dai-animo-kv1124018-0-3m-xanh.jpg');
INSERT INTO image (url)
VALUES ('https://cdn1.concung.com/2025/01/69551-117730-large_mobile/bo-modal-khang-khuan-dai-animo-vd1024050-0-3m-cam-nhat.jpg');
INSERT INTO image (url)
VALUES ('https://cdn1.concung.com/2022/06/57899-89665-large_mobile/bodysuit-tinh-nang-dai-vai-luoi-animo-i0322034-0-3m-xam.jpg');
INSERT INTO image (url)
VALUES ('https://cdn1.concung.com/2025/01/69729-117976-large_mobile/bo-yem-be-trai-ngan-bst-t-rex-animo-hn1224004-1y-xanh.jpg');


-- BRANDS
INSERT INTO brands (name) VALUES ('Abbott Grow');
INSERT INTO brands (name) VALUES ('Vinamilk');
INSERT INTO brands (name) VALUES ('meiji');
INSERT INTO brands (name) VALUES ('Ensure');
INSERT INTO brands (name) VALUES ('Nuti Food');
INSERT INTO brands (name) VALUES ('Cetaphil');
INSERT INTO brands (name) VALUES ('Rohto');
INSERT INTO brands (name) VALUES ('Johnsons baby');
INSERT INTO brands (name) VALUES ('Friso');
INSERT INTO brands (name) VALUES ('Pigeon');
INSERT INTO brands (name) VALUES ('Bobby');
INSERT INTO brands (name) VALUES ('Huggies');
INSERT INTO brands (name) VALUES ('molfix');
INSERT INTO brands (name) VALUES ('Caryn');
INSERT INTO brands (name) VALUES ('Merries');
INSERT INTO brands (name) VALUES ('Animo');

-- CATEGORIES
INSERT INTO categories (name)
VALUES ('Sữa công thức');
INSERT INTO categories (name)
VALUES ('Thời trang cho bé');
INSERT INTO categories (name)
VALUES ('Bỉm tả');


-- USERS
INSERT INTO users (fullName, displayName, dOB, gender, phone_number, email, password_UserName, avatar_id, status, role)
VALUES 
('Nguyễn Thị Mai', 'MeMai', '1995-04-10', 'Nữ', '0912345678','mai@gmail.com', '123456', 1, 'ACTIVE', 'USER'),
('Trần Minh Huy', 'HuyTran', '1998-06-12', 'Male', '0901234567', 'huytran@example.com', 'pass123', NULL, 'ACTIVE', NULL, 'USER'),
('Nguyễn Thị Thu', 'ThuNguyen', '1997-03-22', 'Female', '0902345678', 'thunguyen@example.com', 'thu123', NULL, 'ACTIVE', NULL, 'USER'),
('Phạm Quốc Bảo', 'BaoPham', '2000-11-05', 'Male', '0913456789', 'baopham@example.com', 'bao123', NULL, 'PENDING', NULL, 'USER'),
('Lê Mỹ Duyên', 'MyDuyen', '1999-02-14', 'Female', '0934567891', 'myduyen@example.com', 'duyen123', NULL, 'ACTIVE', NULL, 'USER'),
('Hoàng Gia Hân', 'GiaHan', '2001-07-30', 'Female', '0945678912', 'giahann@example.com', 'han123', NULL, 'ACTIVE', NULL, 'USER');

-- ADDRESS
INSERT INTO address (user_id, address_type, full_name, phone_number, street, city, state, country, is_default)
VALUES 
(1, 'shipping', 'Nguyễn Thị Mai', '0912345678', '12 Nguyễn Văn Bảo', 'Thủ Đức', 'Hồ Chí Minh', 'Việt Nam', 1);

-- PRODUCTS
INSERT INTO products (name, sku, description, brand_id, category_id, image_id) VALUES ('Sữa Aptamil số 2 (6–12 tháng)', 'MIK001', 'Sữa công thức cho trẻ 6–12 tháng', 1, 1, 1);
INSERT INTO products (name, sku, description, brand_id, category_id, image_id) VALUES ('Bộ yếm bé trai ngắn BST T-Rex Animo HN1224004', 'CLT001', '	Bé từ 1 đến 4 tuổi', 16, 2, 2);
INSERT INTO products (name, sku, description, brand_id, category_id, image_id) VALUES ('Bodysuit rayon jacquard dài Animo KV1124018', 'CLT002', '90% Rayon, 10% Spandex, 200gsm', 13, 2, 3);
INSERT INTO products (name, sku, description, brand_id, category_id, image_id) VALUES ('Sữa uống dinh dưỡng Vinamilk Yoko Gold 180ml', 'MIK002', 'Bảo quản nơi khô ráo và thoáng mát', 2, 1, 4);
INSERT INTO products (name, sku, description, brand_id, category_id, image_id) VALUES ('Sữa uống dinh dưỡng Optimum Gold 110ml', 'MIK003', 'Lốc 4 hộp', 2, 1, 5);
INSERT INTO products (name, sku, description, brand_id, category_id, image_id) VALUES ('Sữa uống dinh dưỡng Dielac Grow Plus 110ml (Sữa Non)', 'MIK004', 'Độ tuổi phù hợp	trẻ em trên 1 tuổi', 2, 1, 6);

-- PRODUCT_IMAGES
INSERT INTO product_images (product_id, image_id) VALUES (1, 1);
INSERT INTO product_images (product_id, image_id) VALUES (2, 8);
INSERT INTO product_images (product_id, image_id) VALUES (3, 5);
INSERT INTO product_images (product_id, image_id) VALUES (4, 9);
INSERT INTO product_images (product_id, image_id) VALUES (5, 2);
INSERT INTO product_images (product_id, image_id) VALUES (6, 2);

-- OPTION_VARIANT
INSERT INTO option_variant (product_id, price, stock) VALUES (1, 721000, 30);
INSERT INTO option_variant (product_id, price, stock) VALUES (2, 219000, 40);
INSERT INTO option_variant (product_id, price, stock) VALUES (3, 119000, 60);
INSERT INTO option_variant (product_id, price, stock) VALUES (4, 79000, 200);
INSERT INTO option_variant (product_id, price, stock) VALUES (5, 39000, 300);


-- ATTRIBUTES
INSERT INTO attributes (category_id, name) VALUES (1, 'Dung tích (ml)');
INSERT INTO attributes (category_id, name) VALUES (2, 'Size');
INSERT INTO attributes (category_id, name) VALUES (1, 'Dung tích (g)');


-- VARIANT
INSERT INTO variant (category_id, name, value) VALUES (1, 'Dung tích (ml)', '250ml');
INSERT INTO variant (category_id, name, value) VALUES (1, 'Dung tích (ml)', '180ml');
INSERT INTO variant (category_id, name, value) VALUES (1, 'Dung tích (ml)', '400ml');
INSERT INTO variant (category_id, name, value) VALUES (1, 'Dung tích (g)', '600g');
INSERT INTO variant (category_id, name, value) VALUES (1, 'Dung tích (g)', '800g');

INSERT INTO variant (category_id, name, value) VALUES (2, 'Size', '0-3M');
INSERT INTO variant (category_id, name, value) VALUES (2, 'Size', '6-9M');
INSERT INTO variant (category_id, name, value) VALUES (2, 'Size', '9-12M');
-- cái này để copy paste cho tiện
INSERT INTO variant (category_id, name, value) VALUES (2, 'Size', '');  

-- CARD
INSERT INTO card (user_id, duration, type, is_default)
VALUES (1, '2025-12-31', 'VISA', 1);

-- ORDERS
INSERT INTO orders (create_at, payment_status, order_status, user_id, address_id, card_id, isCOD)
VALUES
('2025-12-01', 'PAID', 'DELIVERED', 1, 1, 1, 0);

-- ORDER DETAIL
INSERT INTO order_detail (order_id, product_id, quantity, total)
VALUES (1, 1, 2, 1120000);

-- PRODUCT REVIEWS
INSERT INTO product_reviews (user_id, product_id, rating, description)
VALUES (1, 1, 5, 'Sữa rất hợp với bé, tăng cân tốt.');

-- BANNER
INSERT INTO banners(image_id, `status`, startDate, endDate)
VALUES (1,'display', '2025-09-09','2025-12-12');
