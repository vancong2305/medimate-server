-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th4 05, 2024 lúc 07:42 PM
-- Phiên bản máy phục vụ: 10.4.32-MariaDB
-- Phiên bản PHP: 8.1.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `medimateserver`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `address`
--

CREATE TABLE `address` (
  `id` int(11) NOT NULL,
  `phone` text NOT NULL,
  `street` text NOT NULL,
  `district` text NOT NULL,
  `province` text NOT NULL,
  `type` text NOT NULL,
  `is_default` tinyint(1) NOT NULL,
  `status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `category`
--

CREATE TABLE `category` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `coupon`
--

CREATE TABLE `coupon` (
  `id` int(11) NOT NULL,
  `code` varchar(40) NOT NULL,
  `description` text NOT NULL,
  `point` int(11) NOT NULL,
  `discount_percent` int(11) NOT NULL,
  `expiration_time` int(11) NOT NULL,
  `image` text NOT NULL,
  `status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `coupon_detail`
--

CREATE TABLE `coupon_detail` (
  `id` int(11) NOT NULL,
  `id_coupon` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `id_oder` int(11) NOT NULL,
  `time_start` date NOT NULL,
  `time_end` date NOT NULL,
  `status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `order`
--

CREATE TABLE `order` (
  `id` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `code` text NOT NULL,
  `payment_method` int(11) NOT NULL,
  `discount_coupon` int(11) NOT NULL,
  `delivery_fee` int(11) NOT NULL,
  `order_time` date NOT NULL,
  `note` text NOT NULL,
  `point` int(11) NOT NULL,
  `status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `order_detail`
--

CREATE TABLE `order_detail` (
  `id_oder` int(11) NOT NULL,
  `id_product` int(11) NOT NULL,
  `discount_price` int(11) NOT NULL,
  `quantity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `product`
--

CREATE TABLE `product` (
  `id` int(11) NOT NULL,
  `id_category` int(11) NOT NULL,
  `id_unit` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` varchar(2000) NOT NULL,
  `discount_percent` int(11) NOT NULL,
  `price` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `image` text NOT NULL,
  `status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `role`
--

CREATE TABLE `role` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `role`
--

INSERT INTO `role` (`id`, `name`, `status`) VALUES
(1, 'admin', 1),
(2, 'user', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `token`
--

CREATE TABLE `token` (
  `id_user` int(11) NOT NULL,
  `access_token` text NOT NULL,
  `refresh_token` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `token`
--

INSERT INTO `token` (`id_user`, `access_token`, `refresh_token`) VALUES
(16, 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ7XCJpZFwiOjE2LFwiaWRSb2xlXCI6MSxcInBob25lXCI6XCIwMTkyOTIyXCIsXCJwYXNzd29yZFwiOlwiMVwiLFwicmFua1wiOlwixJDhu5NuZ1wiLFwicG9pbnRcIjowLFwic3RhdHVzXCI6MX0iLCJpYXQiOjE3MTIzMzgzNTYsImV4cCI6MTcxMjQyNDc1Nn0.kHg6GA6vzjr_RGpKJ0N0Ss1rv0I0rE6mEG1nL03uhic', 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ7XCJpZFwiOjE2LFwiaWRSb2xlXCI6MSxcInBob25lXCI6XCIwMTkyOTIyXCIsXCJwYXNzd29yZFwiOlwiMVwiLFwicmFua1wiOlwixJDhu5NuZ1wiLFwicG9pbnRcIjowLFwic3RhdHVzXCI6MX0iLCJpYXQiOjE3MTIzMzgzNTYsImV4cCI6MTcxMjk0MzE1Nn0.7mDkDeWkK_DfiY-sCM7uEDRxoeR99mCsnfioxeBN_y4');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `unit`
--

CREATE TABLE `unit` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `id_address` int(11) DEFAULT NULL,
  `id_role` int(11) NOT NULL DEFAULT 2,
  `phone` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `first_name` text DEFAULT NULL,
  `last_name` text DEFAULT NULL,
  `rank` text NOT NULL DEFAULT 'Đồng',
  `point` int(11) NOT NULL DEFAULT 0,
  `birthday` date DEFAULT NULL,
  `gender` int(11) DEFAULT NULL,
  `image` text DEFAULT NULL,
  `status` int(11) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `user`
--

INSERT INTO `user` (`id`, `id_address`, `id_role`, `phone`, `password`, `first_name`, `last_name`, `rank`, `point`, `birthday`, `gender`, `image`, `status`) VALUES
(12, NULL, 2, 'a', '1', NULL, NULL, 'Đồng', 0, NULL, NULL, NULL, 1),
(14, NULL, 1, '019292', '1', NULL, NULL, 'Đồng', 0, NULL, NULL, NULL, 1),
(16, NULL, 1, '0192922', '1', NULL, NULL, 'Đồng', 0, NULL, NULL, NULL, 1);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `address`
--
ALTER TABLE `address`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `coupon`
--
ALTER TABLE `coupon`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `code` (`code`);

--
-- Chỉ mục cho bảng `coupon_detail`
--
ALTER TABLE `coupon_detail`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_coupon_detail_user` (`id_coupon`),
  ADD KEY `fk_coupont_detail_user` (`id_user`),
  ADD KEY `fk_coupon_detail_order` (`id_oder`);

--
-- Chỉ mục cho bảng `order`
--
ALTER TABLE `order`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `code` (`code`) USING HASH,
  ADD KEY `fk_order_user` (`id_user`);

--
-- Chỉ mục cho bảng `order_detail`
--
ALTER TABLE `order_detail`
  ADD KEY `fk_order_detail_order` (`id_oder`),
  ADD KEY `fk_order_product` (`id_product`);

--
-- Chỉ mục cho bảng `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_product_category` (`id_category`),
  ADD KEY `fk_product_unit` (`id_unit`);

--
-- Chỉ mục cho bảng `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `token`
--
ALTER TABLE `token`
  ADD KEY `fk_token_user` (`id_user`);

--
-- Chỉ mục cho bảng `unit`
--
ALTER TABLE `unit`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `phone` (`phone`),
  ADD KEY `fk_user_role` (`id_role`),
  ADD KEY `fk_user_address` (`id_address`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `category`
--
ALTER TABLE `category`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT cho bảng `coupon_detail`
--
ALTER TABLE `coupon_detail`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT cho bảng `order`
--
ALTER TABLE `order`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT cho bảng `product`
--
ALTER TABLE `product`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT cho bảng `role`
--
ALTER TABLE `role`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT cho bảng `unit`
--
ALTER TABLE `unit`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT cho bảng `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `coupon_detail`
--
ALTER TABLE `coupon_detail`
  ADD CONSTRAINT `fk_coupon_detail_coupon` FOREIGN KEY (`id_coupon`) REFERENCES `coupon` (`id`),
  ADD CONSTRAINT `fk_coupon_detail_order` FOREIGN KEY (`id_oder`) REFERENCES `order` (`id`),
  ADD CONSTRAINT `fk_coupon_detail_user` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`);

--
-- Các ràng buộc cho bảng `order`
--
ALTER TABLE `order`
  ADD CONSTRAINT `fk_order_user` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`);

--
-- Các ràng buộc cho bảng `order_detail`
--
ALTER TABLE `order_detail`
  ADD CONSTRAINT `fk_order_detail_order` FOREIGN KEY (`id_oder`) REFERENCES `order` (`id`),
  ADD CONSTRAINT `fk_order_product` FOREIGN KEY (`id_product`) REFERENCES `product` (`id`);

--
-- Các ràng buộc cho bảng `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `fk_product_category` FOREIGN KEY (`id_category`) REFERENCES `category` (`id`),
  ADD CONSTRAINT `fk_product_unit` FOREIGN KEY (`id_unit`) REFERENCES `unit` (`id`);

--
-- Các ràng buộc cho bảng `token`
--
ALTER TABLE `token`
  ADD CONSTRAINT `fk_token_user` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`);

--
-- Các ràng buộc cho bảng `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `fk_user_address` FOREIGN KEY (`id_address`) REFERENCES `address` (`id`),
  ADD CONSTRAINT `fk_user_role` FOREIGN KEY (`id_role`) REFERENCES `role` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
