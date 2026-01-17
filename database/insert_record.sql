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


INSERT INTO `categories` (`name`) VALUES
                                      ('Sữa bột cao cấp'), -- 1
                                      ('Bỉm & Tã giấy'), -- 2
                                      ('Ăn dặm & Dinh dưỡng'), -- 3
                                      ('Bình sữa & Phụ kiện'), -- 4
                                      ('Thời trang bé trai'), -- 5
                                      ('Thời trang bé gái'), -- 6
                                      ('Xe đẩy & Đai địu'), -- 7
                                      ('Máy hút sữa & Máy tiệt trùng'), -- 8
                                      ('Vitamin & Sức khỏe'), -- 9
                                      ('Đồ chơi an toàn'); -- 10

INSERT INTO `brands` (`name`) VALUES
                                  ('Meiji'), -- 1
                                  ('Merries'), -- 2
                                  ('Pigeon'), -- 3
                                  ('Moony'), -- 4
                                  ('Blackmores'), -- 5
                                  ('Aptamil'), -- 6
                                  ('Philips Avent'), -- 7
                                  ('Combi'), -- 8
                                  ('Nous'), -- 9 (Thời trang)
                                  ('BioGaia'); -- 1

-- 4. Thêm dữ liệu bảng `users` (Bao gồm 1 Admin và 9 User thường)
INSERT INTO `users` (`fullName`, `displayName`, `dOB`, `gender`, `phoneNumber`, `email`, `passwordUserName`, `avatarId`, `status`, `role`) VALUES
                                                                                                                                               ('Nguyễn Văn Quản Trị', 'Admin', '1990-01-01', 'Male', '0909000000', 'admin@momkid.vn', 'hash_pass_admin', 1, 'ACTIVE', 'ADMIN'),
                                                                                                                                               ('Trần Thị Mẹ Bỉm', 'MeBimSua', '1995-05-15', 'Female', '0912345678', 'mebim@gmail.com', 'hash_pass_1', 2, 'ACTIVE', 'USER'),
                                                                                                                                               ('Lê Văn Bố', 'BoTeo', '1992-08-20', 'Male', '0987654321', 'boteo@gmail.com', 'hash_pass_2', NULL, 'ACTIVE', 'USER'),
                                                                                                                                               ('Phạm Thị Lan', 'LanPham', '1998-12-12', 'Female', '0933111222', 'lanpham@yahoo.com', 'hash_pass_3', NULL, 'ACTIVE', 'USER'),
                                                                                                                                               ('Hoàng Thu Trang', 'TrangHoang', '1996-03-10', 'Female', '0977888999', 'tranghoang@gmail.com', 'hash_pass_4', NULL, 'PENDING', 'USER'),
                                                                                                                                               ('Đỗ Minh Khôi', 'KhoiDo', '1991-07-25', 'Male', '0944555666', 'khoido@gmail.com', 'hash_pass_5', NULL, 'ACTIVE', 'USER'),
                                                                                                                                               ('Ngô Thị Mai', 'MaiNgo', '2000-02-28', 'Female', '0966777888', 'maingo@outlook.com', 'hash_pass_6', NULL, 'BANNED', 'USER'),
                                                                                                                                               ('Vũ Đức Thắng', 'ThangVu', '1989-11-11', 'Male', '0911222333', 'thangvu@gmail.com', 'hash_pass_7', NULL, 'ACTIVE', 'USER'),
                                                                                                                                               ('Bùi Thị Hương', 'HuongBui', '1994-09-09', 'Female', '0999000111', 'huongbui@gmail.com', 'hash_pass_8', NULL, 'ACTIVE', 'USER'),
                                                                                                                                               ('Lý Tiểu Long', 'BruceLy', '1993-06-01', 'Male', '0901234567', 'brucely@gmail.com', 'hash_pass_9', NULL, 'DEACTIVE', 'USER');

-- 5. Thêm dữ liệu bảng `address` (Địa chỉ VN thực tế)
INSERT INTO `address` (`userId`, `addressType`, `fullName`, `phoneNumber`, `street`, `city`, `state`, `country`, `isDefault`) VALUES
                                                                                                                                  (2, 'shipping', 'Trần Thị Mẹ Bỉm', '0912345678', '123 Nguyễn Văn Linh, P. Tân Phong, Q.7', 'Hồ Chí Minh', 'Hồ Chí Minh', 'Việt Nam', 1),
                                                                                                                                  (2, 'billing', 'Trần Thị Mẹ Bỉm', '0912345678', 'Tòa nhà Bitexco, Q.1', 'Hồ Chí Minh', 'Hồ Chí Minh', 'Việt Nam', 0),
                                                                                                                                  (3, 'shipping', 'Lê Văn Bố', '0987654321', '456 Cầu Giấy', 'Hà Nội', 'Hà Nội', 'Việt Nam', 1),
                                                                                                                                  (4, 'shipping', 'Phạm Thị Lan', '0933111222', '789 Lê Duẩn', 'Đà Nẵng', 'Đà Nẵng', 'Việt Nam', 1),
                                                                                                                                  (5, 'shipping', 'Hoàng Thu Trang', '0977888999', 'Số 10, Ngõ 5, Đường Láng', 'Hà Nội', 'Hà Nội', 'Việt Nam', 1),
                                                                                                                                  (6, 'shipping', 'Đỗ Minh Khôi', '0944555666', 'Khu Công Nghiệp VSIP', 'Bình Dương', 'Bình Dương', 'Việt Nam', 1),
                                                                                                                                  (8, 'shipping', 'Vũ Đức Thắng', '0911222333', 'Chung cư Ecopark', 'Hưng Yên', 'Hưng Yên', 'Việt Nam', 1),
                                                                                                                                  (9, 'shipping', 'Bùi Thị Hương', '0999000111', 'Xã Đàn, Đống Đa', 'Hà Nội', 'Hà Nội', 'Việt Nam', 1),
                                                                                                                                  (9, 'billing', 'Công ty TNHH Hương Bùi', '0999000111', 'Xã Đàn, Đống Đa', 'Hà Nội', 'Hà Nội', 'Việt Nam', 0),
                                                                                                                                  (1, 'shipping', 'Admin Warehouse', '0909000000', 'Kho tổng Củ Chi', 'Hồ Chí Minh', 'Hồ Chí Minh', 'Việt Nam', 1);

-- 6. Thêm dữ liệu bảng `card`
INSERT INTO `card` (`userId`, `duration`, `type`, `isDefault`) VALUES
                                                                   (2, '2028-12-31', 'Visa', 1),
                                                                   (2, '2026-05-30', 'MasterCard', 0),
                                                                   (3, '2027-10-15', 'Visa', 1),
                                                                   (4, '2025-11-20', 'JCB', 1),
                                                                   (5, '2029-01-01', 'Visa', 1),
                                                                   (6, '2026-08-08', 'Amex', 1),
                                                                   (8, '2027-02-14', 'MasterCard', 1),
                                                                   (9, '2030-06-30', 'Visa', 1),
                                                                   (9, '2025-12-12', 'Domestic ATM', 0),
                                                                   (1, '2099-12-31', 'Corporate', 1);

-- 7. Thêm dữ liệu bảng `products` (Sản phẩm đặc thù Mẹ & Bé)
INSERT INTO `products` (`name`, `sku`, `description`, `isActive`, `brandId`, `categoryId`, `noOfViews`, `noOfSold`, `imageId`) VALUES
                                                                                                                                   ('Sữa bột Meiji Nội địa Nhật số 0 (0-1 tuổi)', 'MEIJI-0', 'Sữa mát, giúp bé tăng cân và phát triển trí não.', 1, 1, 1, 1500, 500, 3),
                                                                                                                                   ('Sữa bột Meiji Nội địa Nhật số 9 (1-3 tuổi)', 'MEIJI-9', 'Bổ sung sắt và canxi cho bé giai đoạn tập đi.', 1, 1, 1, 1200, 300, 3),
                                                                                                                                   ('Bỉm dán Merries Size NB90', 'MER-NB90', 'Siêu mềm mại, bảo vệ làn da sơ sinh.', 1, 2, 2, 2000, 800, 4),
                                                                                                                                   ('Bỉm quần Merries Size L44', 'MER-L44', 'Thoáng khí, chống hăm, chun lưng mềm.', 1, 2, 2, 1800, 600, 4),
                                                                                                                                   ('Bình sữa Pigeon Nội địa Nhật 160ml', 'PIG-160', 'Nhựa PPSU an toàn, núm ti siêu mềm.', 1, 3, 4, 900, 150, 5),
                                                                                                                                   ('Xe đẩy Combi Nhật Bản siêu nhẹ', 'COM-XE1', 'Gấp gọn bằng 1 tay, nặng chỉ 4.5kg.', 1, 8, 7, 500, 20, 6),
                                                                                                                                   ('Vitamin BioGaia Protectis', 'BIO-GAIA', 'Men vi sinh cải thiện hệ tiêu hóa cho bé.', 1, 10, 9, 3000, 1000, 9),
                                                                                                                                   ('Sữa Blackmores số 1 Úc', 'BLK-1', 'Dòng sữa tăng cân nổi tiếng của Úc.', 1, 5, 1, 1100, 400, 3),
                                                                                                                                   ('Set 5 bộ Body chip Nous', 'NOUS-BODY', 'Vải sợi tre kháng khuẩn, mềm mát.', 1, 9, 5, 800, 200, 7),
                                                                                                                                   ('Máy hâm sữa Philips Avent', 'AVENT-HAM', 'Hâm nóng đều, giữ trọn dinh dưỡng.', 1, 7, 8, 600, 50, 5);

-- 8. Thêm dữ liệu bảng `attributes` (Định nghĩa các loại lọc)
INSERT INTO `attributes` (`categoryId`, `name`) VALUES
                                                    (1, 'Độ tuổi'), -- Sữa
                                                    (1, 'Trọng lượng'), -- Sữa
                                                    (2, 'Kích cỡ (Size)'), -- Bỉm
                                                    (2, 'Loại tã'), -- Bỉm
                                                    (5, 'Chất liệu'), -- Thời trang
                                                    (5, 'Size quần áo'), -- Thời trang
                                                    (4, 'Dung tích'), -- Bình sữa
                                                    (7, 'Tải trọng'), -- Xe đẩy
                                                    (9, 'Dạng bào chế'), -- Vitamin
                                                    (8, 'Chức năng'); -- Máy móc

-- 9. Thêm dữ liệu bảng `option_variant` (Giá và Tồn kho cho từng SKU)
-- Lưu ý: Sản phẩm có thể có giá khác nhau tùy option, ở đây giả lập 1-1 cho đơn giản
INSERT INTO `option_variant` (`productId`, `price`, `stock`) VALUES
                                                                 (1, 550000, 100), -- Meiji số 0
                                                                 (2, 450000, 150), -- Meiji số 9
                                                                 (3, 385000, 200), -- Merries NB
                                                                 (4, 385000, 200), -- Merries L
                                                                 (5, 320000, 50),  -- Bình Pigeon
                                                                 (6, 8500000, 10), -- Xe đẩy Combi
                                                                 (7, 295000, 300), -- BioGaia
                                                                 (8, 580000, 80),  -- Blackmores
                                                                 (9, 450000, 60),  -- Nous
                                                                 (10, 1200000, 25); -- Avent

-- 10. Thêm dữ liệu bảng `variant` (Giá trị thuộc tính cụ thể cho từng Option)
-- Liên kết optionId (vừa tạo ở bảng option_variant) với thuộc tính
INSERT INTO `variant` (`categoryId`, `name`, `value`, `optionId`) VALUES
                                                                      (1, 'Độ tuổi', '0-12 Tháng', 1), -- Meiji 0
                                                                      (1, 'Trọng lượng', '800g', 1),
                                                                      (1, 'Độ tuổi', '1-3 Tuổi', 2), -- Meiji 9
                                                                      (1, 'Trọng lượng', '800g', 2),
                                                                      (2, 'Kích cỡ (Size)', 'Newborn (<5kg)', 3), -- Merries NB
                                                                      (2, 'Kích cỡ (Size)', 'L (9-14kg)', 4), -- Merries L
                                                                      (4, 'Dung tích', '160ml', 5), -- Pigeon
                                                                      (7, 'Tải trọng', '15kg', 6), -- Xe đẩy
                                                                      (9, 'Dạng bào chế', 'Giọt', 7), -- BioGaia
                                                                      (5, 'Size quần áo', '3-6M', 9); -- Nous

-- 11. Thêm dữ liệu bảng `product_images` (Ảnh phụ cho sản phẩm)
INSERT INTO `product_images` (`productId`, `imageId`) VALUES
                                                          (1, 3), (1, 8), -- Ảnh thêm cho Meiji
                                                          (2, 3),
                                                          (3, 4), (3, 9), -- Ảnh thêm cho Merries
                                                          (4, 4),
                                                          (5, 5),
                                                          (6, 6),
                                                          (7, 9),
                                                          (9, 7);


-- 12. Thêm dữ liệu bảng `banners`
INSERT INTO `banners` (`imageId`, `status`, `startDate`, `endDate`) VALUES
                                                                        (8, 'ACTIVE', '2023-10-01', '2023-10-10'), -- Sale 10.10
                                                                        (9, 'ACTIVE', '2024-01-01', '2024-02-15'), -- Sale Tết
                                                                        (3, 'INACTIVE', '2023-01-01', '2023-01-31'),
                                                                        (4, 'ACTIVE', '2023-11-01', '2023-11-30'),
                                                                        (5, 'ACTIVE', '2023-12-01', '2023-12-25'),
                                                                        (6, 'DEACTIVE', '2023-06-01', '2023-06-30'),
                                                                        (7, 'ACTIVE', '2023-09-02', '2023-09-05'),
                                                                        (8, 'PENDING', '2024-03-08', '2024-03-08'),
                                                                        (9, 'PENDING', '2024-04-30', '2024-05-01'),
                                                                        (9, 'ACTIVE', '2023-08-01', '2023-08-31');

-- 13. Thêm dữ liệu bảng `orders`
INSERT INTO `orders` (`createAt`, `paymentStatus`, `orderStatus`, `userId`, `addressId`, `cardId`, `isCOD`) VALUES
                                                                                                                ('2023-10-05', 'PAID', 'DELIVERED', 2, 1, 1, 0), -- Đơn thẻ
                                                                                                                ('2023-10-06', 'PENDING', 'PROCESSING', 3, 3, NULL, 1), -- Đơn COD
                                                                                                                ('2023-10-07', 'PAID', 'SHIPPING', 4, 4, 4, 0),
                                                                                                                ('2023-10-08', 'REFUNDED', 'CANCELLED', 2, 1, 1, 0),
                                                                                                                ('2023-10-09', 'PAID', 'DELIVERED', 5, 5, 5, 0),
                                                                                                                ('2023-10-10', 'PENDING', 'PENDING', 6, 6, NULL, 1),
                                                                                                                ('2023-10-11', 'PAID', 'DELIVERED', 8, 7, 7, 0),
                                                                                                                ('2023-10-12', 'PAID', 'PROCESSING', 9, 8, 8, 0),
                                                                                                                ('2023-10-13', 'PENDING', 'CONFIRMED', 3, 3, NULL, 1),
                                                                                                                ('2023-10-14', 'PAID', 'DELIVERED', 4, 4, 4, 0);

-- 14. Thêm dữ liệu bảng `order_detail` (Chi tiết mua gì)
INSERT INTO `order_detail` (`orderId`, `productId`, `quantity`, `total`) VALUES
                                                                             (1, 1, 2, 1100000), -- Mua 2 hộp Meiji
                                                                             (1, 3, 1, 385000),  -- Kèm 1 bịch bỉm
                                                                             (2, 6, 1, 8500000), -- Mua xe đẩy
                                                                             (3, 7, 3, 885000),  -- Mua 3 lọ BioGaia
                                                                             (4, 2, 1, 450000),
                                                                             (5, 9, 2, 900000),
                                                                             (6, 4, 4, 1540000), -- Mua tích trữ bỉm
                                                                             (7, 5, 2, 640000),
                                                                             (8, 10, 1, 1200000),
                                                                             (10, 8, 6, 3480000); -- Mua cả thùng sữa

-- 15. Thêm dữ liệu bảng `product_reviews` (Feedback thực tế)
INSERT INTO `product_reviews` (`userId`, `productId`, `rating`, `description`) VALUES
                                                                                   (2, 1, 5, 'Sữa chuẩn nội địa, check mã vạch ra ngay. Bé nhà mình uống hợp, output đẹp.'),
                                                                                   (3, 6, 5, 'Xe đẩy nhẹ thật, vợ mình gấp 1 tay ngon ơ. Đáng tiền.'),
                                                                                   (4, 7, 4, 'BioGaia dùng tốt nhưng giá hơi cao. Shop đóng gói cẩn thận.'),
                                                                                   (5, 3, 5, 'Merries thì khỏi bàn, mềm mại, không hăm tí nào.'),
                                                                                   (2, 4, 3, 'Giao hàng hơi chậm so với dự kiến, nhưng hàng chuẩn.'),
                                                                                   (8, 5, 5, 'Núm ti mềm, bé chịu hợp tác ngay lần đầu.'),
                                                                                   (9, 9, 5, 'Vải Nous sờ sướng tay, mặc mùa hè cho bé rất mát.'),
                                                                                   (6, 1, 4, 'Date xa, đóng gói kỹ. Sẽ ủng hộ tiếp.'),
                                                                                   (4, 8, 5, 'Sữa Blackmores giúp bé tăng cân tốt, trộm vía.'),
                                                                                   (3, 10, 4, 'Máy hâm nóng nhanh, tiện lợi cho bố bỉm sữa chăm con đêm.');

