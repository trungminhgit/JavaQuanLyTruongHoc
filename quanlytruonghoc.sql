-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 13, 2024 at 09:48 AM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `quanlytruonghoc`
--

-- --------------------------------------------------------

--
-- Table structure for table `comment`
--

CREATE TABLE `comment` (
  `comment_id` int(11) NOT NULL,
  `create_date` datetime DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  `room_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `comment`
--

INSERT INTO `comment` (`comment_id`, `create_date`, `description`, `user_id`, `room_id`) VALUES
(1, '2024-03-20 12:27:15', 'Phòng học toáng mát sạch sẽ', 3, 11),
(2, '2024-03-20 12:27:15', 'Phòng đẹp, rộng', 4, 13),
(3, '2024-03-20 12:27:15', 'Phòng họp đẹp, rộng, điều hòa mát lạnh', 3, 14),
(4, '2024-03-20 12:27:15', 'Phòng chức năng tương đối đầy đủ dụng cụ', 3, 15),
(6, '2024-03-20 12:27:15', 'Phòng học toáng mát sạch sẽ', 4, 11),
(7, '2024-04-19 00:00:00', 'Add new comment for roooommmmmmm', 2, 12),
(8, '2024-04-28 10:48:32', 'Rất tuyệt vời', 3, 14),
(9, '2024-04-28 10:48:32', 'Phòng học toáng mát sạch sẽ', 3, 17),
(10, '2024-04-29 00:04:18', 'Phòng rất đẹp, thoáng mát, sạch sẽ.\n', 2, 15),
(11, '2024-04-29 00:10:55', 'Phòng đẹp', 2, 15),
(12, '2024-04-29 00:04:18', 'Phòng LAP xịn xò, phục vụ tốt nhu cầu thực hành', 3, 22),
(13, '2024-04-29 00:10:55', 'Phòng chức năng tương đối đầy đủ dụng cụ', 4, 23),
(14, '2024-04-29 00:10:55', 'Phòng chức năng tương đối đầy đủ dụng cụ', 6, 24),
(15, '2024-04-29 00:10:55', 'Phòng LAP xịn xò, phục vụ tốt nhu cầu thực hành', 6, 25),
(16, '2024-04-29 00:10:55', 'Phòng LAP xịn xò, phục vụ tốt nhu cầu thực hành', 4, 26),
(17, '2024-04-29 00:10:55', 'Phòng học toáng mát sạch sẽ', 3, 27),
(23, '2024-04-29 00:10:55', 'Phòng học đẹp, mùi dễ chịu, đầy đủ tiện nghi', 3, 57),
(24, '2024-04-29 00:10:55', 'Phòng học đẹp, mùi dễ chịu, đầy đủ tiện nghi', 4, 21),
(28, '2024-04-29 00:10:55', 'Phòng họp sạch sẽ, phù hợp với yêu cầu', 3, 30),
(29, '2024-04-29 00:10:55', 'Phòng hiện đại', 3, 29),
(30, '2024-04-29 00:10:55', 'Phòng tốt, sẽ đặt phòng thêm nhiều lần nữa', 3, 14),
(31, '2024-04-29 00:10:55', 'Phòng học hiện đại', 6, 28);

-- --------------------------------------------------------

--
-- Table structure for table `receipt`
--

CREATE TABLE `receipt` (
  `receipt_id` int(11) NOT NULL,
  `create_date` datetime NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `receipt`
--

INSERT INTO `receipt` (`receipt_id`, `create_date`, `user_id`) VALUES
(1, '2024-03-20 11:27:15', 3),
(2, '2024-03-25 07:35:15', 4),
(3, '2024-03-29 12:15:30', 4),
(4, '2024-04-20 11:30:15', 3),
(31, '2024-04-20 17:51:10', 3),
(32, '2024-04-30 11:42:38', 2),
(33, '2024-04-30 11:58:12', 2),
(35, '2024-04-30 15:12:01', 2),
(38, '2024-04-30 17:24:24', 3),
(39, '2024-04-30 20:08:39', 3),
(40, '2024-04-30 20:41:40', 3),
(41, '2024-05-08 08:36:00', 3),
(42, '2024-05-10 03:51:59', 3),
(43, '2024-05-10 03:55:49', 3),
(44, '2024-05-10 03:55:52', 3),
(45, '2024-05-10 04:12:36', 3),
(46, '2024-05-10 04:17:42', 3),
(47, '2024-05-10 04:27:23', 3),
(48, '2024-05-10 04:31:50', 3),
(49, '2024-05-10 04:34:45', 3),
(50, '2024-05-10 04:40:37', 3),
(51, '2024-05-10 04:42:25', 3),
(52, '2024-05-10 04:43:48', 3),
(53, '2024-05-10 04:45:46', 3);

-- --------------------------------------------------------

--
-- Table structure for table `receipt_detail`
--

CREATE TABLE `receipt_detail` (
  `receipt_detail_id` int(11) NOT NULL,
  `start_time` datetime NOT NULL,
  `finish_time` datetime DEFAULT NULL,
  `price` decimal(10,0) NOT NULL,
  `num` int(11) NOT NULL,
  `room_id` int(11) NOT NULL,
  `receipt_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `receipt_detail`
--

INSERT INTO `receipt_detail` (`receipt_detail_id`, `start_time`, `finish_time`, `price`, `num`, `room_id`, `receipt_id`) VALUES
(1, '2024-03-20 12:27:15', '2024-03-20 14:27:15', 200000, 0, 11, 1),
(2, '2024-03-25 13:00:00', '2024-03-25 15:00:00', 400000, 0, 13, 2),
(3, '2024-03-29 13:00:00', '2024-03-29 14:00:00', 150000, 0, 12, 3),
(4, '2024-04-20 12:30:15', '2024-04-20 15:30:15', 600000, 0, 15, 4),
(7, '2024-04-20 10:00:00', '2024-04-20 12:00:00', 300000, 2, 12, 31),
(8, '2024-04-30 15:00:00', '2024-04-30 17:00:00', 300000, 2, 12, 32),
(9, '2024-04-30 15:00:00', '2024-04-30 17:00:00', 300000, 2, 12, 33),
(10, '2024-04-30 15:00:00', '2024-04-30 17:00:00', 300000, 2, 12, 35),
(11, '2024-04-30 15:00:00', '2024-04-30 17:00:00', 300000, 2, 15, 35),
(12, '2024-04-30 18:24:00', '2024-04-30 19:24:00', 300000, 5, 29, 38),
(13, '2024-04-30 23:10:00', '2024-04-30 23:59:00', 245000, 2, 29, 39),
(14, '2024-04-30 22:41:00', '2024-04-30 23:41:00', 300000, 9, 29, 40),
(15, '2024-05-10 08:29:00', '2024-05-10 11:28:00', 447500, 9, 26, 41),
(16, '2024-05-08 08:09:00', '2024-05-08 11:09:00', 900000, 5, 30, 41),
(17, '2024-06-01 08:28:00', '2024-06-01 11:28:00', 450000, 7, 57, 41),
(18, '2024-05-10 04:51:00', '2024-05-10 07:51:00', 900000, 5, 30, 42),
(19, '2024-05-10 03:55:00', '2024-05-10 05:55:00', 600000, 5, 30, 43),
(20, '2024-05-10 03:55:00', '2024-05-10 05:55:00', 600000, 5, 30, 44),
(21, '2024-05-10 05:12:00', '2024-05-10 06:12:00', 300000, 5, 30, 45),
(22, '2024-05-11 04:17:00', '2024-05-11 09:17:00', 500000, 2, 28, 46),
(23, '2024-05-17 07:16:00', '2024-05-17 09:17:00', 605000, 9, 29, 46),
(24, '2024-05-10 07:16:00', '2024-05-10 08:16:00', 150000, 5, 57, 46),
(25, '2024-05-10 11:26:00', '2024-05-10 14:40:00', 970000, 8, 29, 47),
(26, '2024-05-10 06:26:00', '2024-05-10 08:26:00', 600000, 9, 30, 47),
(27, '2024-05-10 07:31:00', '2024-05-10 10:32:00', 603333, 8, 24, 48),
(28, '2024-05-10 06:32:00', '2024-05-10 10:32:00', 600000, 9, 25, 48),
(29, '2024-05-10 05:34:00', '2024-05-10 07:35:00', 605000, 8, 29, 49),
(30, '2024-05-10 06:35:00', '2024-05-10 07:35:00', 150000, 7, 57, 49),
(31, '2024-05-10 06:40:00', '2024-05-10 07:41:00', 305000, 8, 14, 50),
(32, '2024-05-10 06:41:00', '2024-05-10 07:41:00', 300000, 4, 30, 50),
(33, '2024-05-10 05:42:00', '2024-05-10 06:42:00', 150000, 8, 57, 51),
(34, '2024-05-10 06:44:00', '2024-05-10 08:44:00', 300000, 7, 57, 52),
(35, '2024-05-11 07:45:00', '2024-05-11 09:45:00', 300000, 8, 26, 53);

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE `role` (
  `role_id` int(11) NOT NULL,
  `role_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`role_id`, `role_name`) VALUES
(1, 'ROLE_ADMIN'),
(2, 'ROLE_USER');

-- --------------------------------------------------------

--
-- Table structure for table `room`
--

CREATE TABLE `room` (
  `room_id` int(11) NOT NULL,
  `room_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `price` decimal(10,0) NOT NULL,
  `seats` int(11) NOT NULL,
  `room_image` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `utilities` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `room_type_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `room`
--

INSERT INTO `room` (`room_id`, `room_name`, `price`, `seats`, `room_image`, `utilities`, `description`, `room_type_id`) VALUES
(11, 'A201', 100000, 50, 'https://res.cloudinary.com/dchkh7d18/image/upload/v1714445274/phonghoc_r5rbvy.jpg', 'Máy lạnh, máy chiếu, quạt trần', 'Phòng học thoáng mát, tiện nghi, đáp ứng đầy đủ nhu cầu trang thiết bị cho học tập ', 1),
(12, 'LAP-01', 150000, 50, 'https://res.cloudinary.com/dchkh7d18/image/upload/v1714445279/phongLAP_mckx5w.jpg', 'Máy lạnh, máy chiếu, quạt trần', 'Phòng thực hành trang bị đầy đủ trang thiết bị cho sinh viên thực hành, với hệ thống máy tính cấu hình cao ', 2),
(13, 'B203', 200000, 100, 'https://res.cloudinary.com/dchkh7d18/image/upload/v1714445274/phonghoc_r5rbvy.jpg', 'Máy lạnh, máy chiếu, quạt trần', 'Phòng học thoáng mát, tiện nghi, đáp ứng đầy đủ nhu cầu trang thiết bị cho học tập ', 1),
(14, 'HOP-01', 300000, 30, 'https://res.cloudinary.com/dchkh7d18/image/upload/v1714445276/phonghop_zzwhgc.jpg', 'Bàn họp, Máy lạnh, máy chiếu, quạt trần', 'Phòng họp hiện đại, đáp ứng nhu cầu cho các thầy cô, sinh viên có một cuộc họp thoải mái nhất', 3),
(15, 'CN-01', 200000, 20, 'https://res.cloudinary.com/dchkh7d18/image/upload/v1714445273/nhadanang_kuz91g.jpg', 'Sân cầu lông, bàn bóng bàn, hồ bơi', 'Phòng chức năng với đầy đủ dụng cụ đáp ứng nhu cầu chơi các môn thể thao của sinh viên', 4),
(17, 'LAP-02', 400000, 40, 'https://res.cloudinary.com/dchkh7d18/image/upload/v1714445279/phongLAP_mckx5w.jpg', 'Máy lạnh, máy chiếu, quạt trần', 'Mô tả ', 2),
(21, 'C407', 300000, 30, 'https://res.cloudinary.com/dchkh7d18/image/upload/v1714579374/iokdg3lepnk0ruexskde.jpg', 'Máy lạnh, máy chiếu, quạt trần', 'Nhận xét tốt 123456789 4566', 1),
(22, 'LAP-02', 150000, 50, 'https://res.cloudinary.com/dchkh7d18/image/upload/v1714445279/phongLAP_mckx5w.jpg', 'Máy lạnh, máy chiếu, quạt trần', 'Phòng thực hành trang bị đầy đủ trang thiết bị cho sinh viên thực hành, với hệ thống máy tính cấu hình cao ', 2),
(23, 'DN-02', 200000, 20, 'https://res.cloudinary.com/dchkh7d18/image/upload/v1714445273/nhadanang_kuz91g.jpg', 'Sân cầu lông, bàn bóng bàn, hồ bơi', 'Phòng chức năng với đầy đủ dụng cụ đáp ứng nhu cầu chơi các môn thể thao của sinh viên', 4),
(24, 'CN-03', 200000, 20, 'https://res.cloudinary.com/dchkh7d18/image/upload/v1714445273/nhadanang_kuz91g.jpg', 'Sân cầu lông, bàn bóng bàn, hồ bơi', 'Phòng chức năng với đầy đủ dụng cụ đáp ứng nhu cầu chơi các môn thể thao của sinh viên', 4),
(25, 'LAP-03', 150000, 40, 'https://res.cloudinary.com/dchkh7d18/image/upload/v1714445279/phongLAP_mckx5w.jpg', 'Máy lạnh, máy chiếu, quạt trần', 'Phòng thực hành trang bị đầy đủ trang thiết bị cho sinh viên thực hành, với hệ thống máy tính cấu hình cao ', 2),
(26, 'LAP-04', 150000, 40, 'https://res.cloudinary.com/dchkh7d18/image/upload/v1714445279/phongLAP_mckx5w.jpg', 'Máy lạnh, máy chiếu, quạt trần', 'Phòng thực hành trang bị đầy đủ trang thiết bị cho sinh viên thực hành, với hệ thống máy tính cấu hình cao ', 2),
(27, 'C503', 100000, 50, 'https://res.cloudinary.com/dchkh7d18/image/upload/v1714579432/xprlfbz1oztwpmb12o0o.jpg', 'Máy lạnh, máy chiếu, quạt trần', 'Phòng học thoáng mát, tiện nghi, đáp ứng đầy đủ nhu cầu trang thiết bị cho học tập ', 1),
(28, 'C504', 100000, 50, 'https://res.cloudinary.com/dchkh7d18/image/upload/v1714445274/phonghoc_r5rbvy.jpg', 'Máy lạnh, máy chiếu, quạt trần', 'Phòng học thoáng mát, tiện nghi, đáp ứng đầy đủ nhu cầu trang thiết bị cho học tập ', 1),
(29, 'HOP-03', 300000, 30, 'https://res.cloudinary.com/dchkh7d18/image/upload/v1714445276/phonghop_zzwhgc.jpg', 'Bàn họp, Máy lạnh, máy chiếu, quạt trần', 'Phòng họp hiện đại, đáp ứng nhu cầu cho các thầy cô, sinh viên có một cuộc họp thoải mái nhất', 3),
(30, 'HOP-04', 300000, 30, 'https://res.cloudinary.com/dchkh7d18/image/upload/v1714445276/phonghop_zzwhgc.jpg', 'Bàn họp, Máy lạnh, máy chiếu, quạt trần', 'Phòng họp hiện đại, đáp ứng nhu cầu cho các thầy cô, sinh viên có một cuộc họp thoải mái nhất', 3),
(57, 'HOC-05', 150000, 30, 'https://res.cloudinary.com/dchkh7d18/image/upload/v1714579333/hiuk77xzixobist49vjl.jpg', 'Máy lạnh, máy chiếu, quạt trần', 'Phòng học hiện đại đáp ứng nhu cầu cần thiết cho sinh viên', 1);

-- --------------------------------------------------------

--
-- Table structure for table `room_type`
--

CREATE TABLE `room_type` (
  `room_type_id` int(11) NOT NULL,
  `room_type_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `room_type`
--

INSERT INTO `room_type` (`room_type_id`, `room_type_name`) VALUES
(1, 'Phòng học'),
(2, 'Phòng LAP'),
(3, 'Phòng họp'),
(4, 'Phòng chức năng');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `user_id` int(11) NOT NULL,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `last_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `first_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `phone` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `avatar` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `role_id` int(11) NOT NULL DEFAULT 2
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`user_id`, `username`, `password`, `last_name`, `first_name`, `phone`, `email`, `avatar`, `role_id`) VALUES
(2, 'dangtrungminh', '$2a$12$CboiqS6zHca62NQUzZo9euDRGZ7iy.5HP1raCdtOlxHeGyIQgrdNq', 'Đặng Trung', 'Minh', '0364403341', 'dangtrngminh@gmail.com', 'https://res.cloudinary.com/dchkh7d18/image/upload/v1714586661/nam1_liwqst_debo4w.png', 1),
(3, 'dangtrunglong', '$2a$10$etH8z/l2LtMu544vS6x3CusotZ6P9VULnd40TrRPf9FwkB1UgXPWS', 'Đặng Trung ', 'Long', '0123456789', '1851010073minh@ou.edu.vn', 'https://res.cloudinary.com/dchkh7d18/image/upload/v1715287866/n1jnrio4ldpff7m5szfx.png', 2),
(4, 'nguyenphuhao', '$2a$10$kxOCEHOn118mHEKkY0J5Se9hub.ibRQmbbgGOnyaUKmmleNpIninO', 'Nguyễn Thành', 'Luân', '0987654321', 'nguyenthanhluan@gmail.com', 'https://res.cloudinary.com/dchkh7d18/image/upload/v1714640058/bxbjnvpe7x2gpbtd6nfb.png', 2),
(6, 'dangtrungphuc', '$2a$12$CboiqS6zHca62NQUzZo9euDRGZ7iy.5HP1raCdtOlxHeGyIQgrdNq', 'Đặng Trung', 'Phúc', '0123456789', 'phuc@gmail.com', 'https://res.cloudinary.com/dchkh7d18/image/upload/v1714586661/nam1_liwqst_debo4w.png', 2),
(13, 'nguyenthid', '$2a$12$CboiqS6zHca62NQUzZo9euDRGZ7iy.5HP1raCdtOlxHeGyIQgrdNq', 'Nguyễn Thị ', 'Dung', '0123456789', 'nguyenthid@gmail.com', 'https://res.cloudinary.com/dchkh7d18/image/upload/v1714586661/nu1_tg88vd_yx1cgp.png', 2),
(15, 'ngothanhlam', '$2a$10$rNA9dTBEitXUYaKT.I89kuwvmVMBd.VMOO.orPk.B1wCYhqIaz6PG', 'Ngô Thanh', 'Lâm', '0123456789', 'thanhlam@gmail.com', 'https://res.cloudinary.com/dchkh7d18/image/upload/v1714586661/nam1_liwqst_debo4w.png', 2),
(16, 'nguyenphuongngan', '$2a$10$vjfNVBOp0/JtlroPMynq6ujG.FpfB0C8Rxghf2/BfMzo30wLityuS', 'Nguyễn Phương', 'Ngân', '0123456789', 'nguyenphuongngan@gmail.com', 'https://res.cloudinary.com/dchkh7d18/image/upload/v1714586927/bbeperiscxvuaiz6hjtz.png', 2),
(17, 'tranvannghia', '$2a$10$vjfNVBOp0/JtlroPMynq6ujG.FpfB0C8Rxghf2/BfMzo30wLityuS', 'Trần Văn ', 'Nghĩa', '0123456789', 'nghia@gmail.com', 'https://res.cloudinary.com/dchkh7d18/image/upload/v1714586661/nam1_liwqst_debo4w.png', 2),
(21, 'nguyenanhtuyet', '$2a$10$ngtifhusSOz3KJXxSbDrwuBm3HC4bNyyXv2OVfwkBk.E1lljN1KCK', 'Nguyễn Ánh', 'Tuyết', '0123456789', 'tuyetmai@gmail.com', 'https://res.cloudinary.com/dchkh7d18/image/upload/v1714658382/rcqabop7jubyvnwow48j.png', 2),
(22, 'minhthunguyen', '$2a$10$cgioDaaeILfMvqJMEKLZYuxt5ZalsKTzWgraECNa/7/GXqdLmvTQu', 'Nguyễn Thị Minh', 'Thư', '0123456789', 'minhthu@gmail.com', 'https://res.cloudinary.com/dchkh7d18/image/upload/v1714666464/ncxeztphf3qofhbhry6b.png', 2),
(23, 'nguyenvantuan', '$2a$10$OilI9FqutCl5HSgJNAxk..KrLDEQ38hTbAv3shcLocRa5LHXNR0bO', 'Nguyễn Văn ', 'Tuấn', '0123456789', 'vantuan@gmail.com', 'https://res.cloudinary.com/dchkh7d18/image/upload/v1715132567/omludwxwnn1eg13avrpy.png', 2);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `comment`
--
ALTER TABLE `comment`
  ADD PRIMARY KEY (`comment_id`),
  ADD KEY `FK_COMMENT_USER` (`user_id`),
  ADD KEY `FK_COMMENT_ROOM` (`room_id`);

--
-- Indexes for table `receipt`
--
ALTER TABLE `receipt`
  ADD PRIMARY KEY (`receipt_id`),
  ADD KEY `FK_RECEIPT_USER` (`user_id`);

--
-- Indexes for table `receipt_detail`
--
ALTER TABLE `receipt_detail`
  ADD PRIMARY KEY (`receipt_detail_id`),
  ADD KEY `FK_RECEIPTDETAIL_ROOM` (`room_id`),
  ADD KEY `FK_RECEIPTDETAIL_RECEIPT` (`receipt_id`);

--
-- Indexes for table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`role_id`);

--
-- Indexes for table `room`
--
ALTER TABLE `room`
  ADD PRIMARY KEY (`room_id`),
  ADD KEY `FK_ROOM_ROOMTYPE` (`room_type_id`);

--
-- Indexes for table `room_type`
--
ALTER TABLE `room_type`
  ADD PRIMARY KEY (`room_type_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`user_id`),
  ADD KEY `FK_USER_ROLE` (`role_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `comment`
--
ALTER TABLE `comment`
  MODIFY `comment_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;

--
-- AUTO_INCREMENT for table `receipt`
--
ALTER TABLE `receipt`
  MODIFY `receipt_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=54;

--
-- AUTO_INCREMENT for table `receipt_detail`
--
ALTER TABLE `receipt_detail`
  MODIFY `receipt_detail_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=36;

--
-- AUTO_INCREMENT for table `role`
--
ALTER TABLE `role`
  MODIFY `role_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `room`
--
ALTER TABLE `room`
  MODIFY `room_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=58;

--
-- AUTO_INCREMENT for table `room_type`
--
ALTER TABLE `room_type`
  MODIFY `room_type_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `comment`
--
ALTER TABLE `comment`
  ADD CONSTRAINT `FK_COMMENT_ROOM` FOREIGN KEY (`room_id`) REFERENCES `room` (`room_id`),
  ADD CONSTRAINT `FK_COMMENT_USER` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`);

--
-- Constraints for table `receipt`
--
ALTER TABLE `receipt`
  ADD CONSTRAINT `FK_RECEIPT_USER` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`);

--
-- Constraints for table `receipt_detail`
--
ALTER TABLE `receipt_detail`
  ADD CONSTRAINT `FK_RECEIPTDETAIL_RECEIPT` FOREIGN KEY (`receipt_id`) REFERENCES `receipt` (`receipt_id`),
  ADD CONSTRAINT `FK_RECEIPTDETAIL_ROOM` FOREIGN KEY (`room_id`) REFERENCES `room` (`room_id`);

--
-- Constraints for table `room`
--
ALTER TABLE `room`
  ADD CONSTRAINT `FK_ROOM_ROOMTYPE` FOREIGN KEY (`room_type_id`) REFERENCES `room_type` (`room_type_id`);

--
-- Constraints for table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `FK_USER_ROLE` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
