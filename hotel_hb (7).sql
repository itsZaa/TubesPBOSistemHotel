-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 22, 2023 at 03:34 AM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.1.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `hotel_hb`
--

-- --------------------------------------------------------

--
-- Table structure for table `favorite`
--

CREATE TABLE `favorite` (
  `favorite_id` int(11) NOT NULL,
  `room_type` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `fnb_menu`
--

CREATE TABLE `fnb_menu` (
  `menu_name` varchar(255) NOT NULL,
  `menu_type` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `fnb_menu`
--

INSERT INTO `fnb_menu` (`menu_name`, `menu_type`, `price`) VALUES
('Asparagus Soup', 'Appetizer', 25000),
('Caesar Salad', 'Appetizer', 28000),
('French Fries', 'Side Dish', 15000),
('Fried Rice', 'Main Course', 35000),
('Iced Tea', 'Beverage', 10000),
('Margherita Pizza', 'Main Course', 60000),
('Mashed Potatoes', 'Side Dish', 25000),
('Panna Cotta', 'Dessert', 20000),
('Spaghetti Bolognese', 'Main Course', 30000),
('Sushi Platter', 'Main Course', 75000);

-- --------------------------------------------------------

--
-- Table structure for table `fnb_order`
--

CREATE TABLE `fnb_order` (
  `order_id` int(11) NOT NULL,
  `fnb_transaction_id` varchar(255) NOT NULL,
  `menu_name` varchar(255) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `price` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `fnb_order`
--

INSERT INTO `fnb_order` (`order_id`, `fnb_transaction_id`, `menu_name`, `quantity`, `price`) VALUES
(1, 'FNB_210723220748_19882', 'Iced Tea', 1, 10000);

-- --------------------------------------------------------

--
-- Table structure for table `fnb_transaction`
--

CREATE TABLE `fnb_transaction` (
  `fnb_transaction_id` varchar(255) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `room_number` int(11) DEFAULT NULL,
  `status` enum('waiting','delivered') DEFAULT NULL,
  `total_price` double DEFAULT NULL,
  `payment_method` varchar(255) DEFAULT NULL,
  `transaction_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `fnb_transaction`
--

INSERT INTO `fnb_transaction` (`fnb_transaction_id`, `username`, `room_number`, `status`, `total_price`, `payment_method`, `transaction_date`) VALUES
('FNB_210723220748_19882', 'otong123', 0, 'waiting', 10000, 'BCA', '2023-07-21 15:08:56');

-- --------------------------------------------------------

--
-- Table structure for table `hotel_facility`
--

CREATE TABLE `hotel_facility` (
  `hotel_facility_id` int(11) NOT NULL,
  `facility` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `laundry`
--

CREATE TABLE `laundry` (
  `laundry_name` varchar(255) NOT NULL,
  `price` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `laundry`
--

INSERT INTO `laundry` (`laundry_name`, `price`) VALUES
('Express', 20000),
('Standard', 15000);

-- --------------------------------------------------------

--
-- Table structure for table `laundry_transaction`
--

CREATE TABLE `laundry_transaction` (
  `laundry_transaction_id` varchar(255) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `room_number` int(11) DEFAULT NULL,
  `status` enum('waiting','delivered') DEFAULT NULL,
  `total_price` double DEFAULT NULL,
  `payment_method` varchar(255) DEFAULT NULL,
  `date_order` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `date_delivered` timestamp NULL DEFAULT NULL,
  `laundry_name` varchar(255) DEFAULT NULL,
  `estimation_done` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `laundry_transaction`
--

INSERT INTO `laundry_transaction` (`laundry_transaction_id`, `username`, `room_number`, `status`, `total_price`, `payment_method`, `date_order`, `date_delivered`, `laundry_name`, `estimation_done`) VALUES
('LAUNDRY_14072023073447_95946', 'nico.js', 306, 'delivered', 45000, 'BCA', '2023-07-14 02:11:32', '2023-07-14 02:11:32', 'Standard', '2023-07-15 00:34:48'),
('LAUNDRY_14072023074321_74122', 'otong123', 301, 'delivered', 40000, 'OVO', '2023-07-14 02:06:54', '2023-07-14 02:06:54', 'Express', '2023-07-14 10:43:21'),
('LAUNDRY_14072023080727_41279', 'marcelandrean', 105, 'delivered', 80000, 'BCA', '2023-07-14 02:11:16', '2023-07-14 02:11:16', 'Express', '2023-07-14 11:07:27');

-- --------------------------------------------------------

--
-- Table structure for table `member`
--

CREATE TABLE `member` (
  `member_id` int(11) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `point` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `merchandise`
--

CREATE TABLE `merchandise` (
  `Merchandise_id` int(11) NOT NULL,
  `Merchandise_name` varchar(255) NOT NULL,
  `Point_required` int(11) NOT NULL,
  `Strock_merch` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `payment_method`
--

CREATE TABLE `payment_method` (
  `payment_method_id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `payment_method`
--

INSERT INTO `payment_method` (`payment_method_id`, `name`) VALUES
(1, 'BCA'),
(2, 'OVO');

-- --------------------------------------------------------

--
-- Table structure for table `redeem_merchandise`
--

CREATE TABLE `redeem_merchandise` (
  `redeem_id` int(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `merchandise` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `room`
--

CREATE TABLE `room` (
  `room_number` int(11) NOT NULL,
  `room_type` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `occupied_length` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `room_facility`
--

CREATE TABLE `room_facility` (
  `room_facility_id` int(11) NOT NULL,
  `room_type` varchar(255) NOT NULL,
  `facility_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `room_order`
--

CREATE TABLE `room_order` (
  `room_order_id` int(11) NOT NULL,
  `room_transaction_id` varchar(255) DEFAULT NULL,
  `room_number` int(11) DEFAULT NULL,
  `room_type` varchar(255) NOT NULL,
  `quantity` int(11) NOT NULL,
  `price` double NOT NULL,
  `date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `room_order`
--

INSERT INTO `room_order` (`room_order_id`, `room_transaction_id`, `room_number`, `room_type`, `quantity`, `price`, `date`) VALUES
(2, 'ROOM_090723184741_23289', NULL, 'Standart', 0, 600000, NULL),
(3, 'ROOM_090723184741_23289', NULL, 'Deluxe', 0, 1500000, NULL),
(4, 'ROOM_090723194443_56996', NULL, 'Standart', 0, 800000, '2023-07-09'),
(5, 'ROOM_090723194443_56996', NULL, 'Standart', 0, 800000, '2023-07-10'),
(6, 'ROOM_090723195051_22200', NULL, 'Standart', 4, 800000, '2023-07-09'),
(7, 'ROOM_090723195051_22200', NULL, 'Suite', 10, 5000000, '2023-07-09'),
(8, 'ROOM_090723195051_22200', NULL, 'Standart', 4, 800000, '2023-07-10'),
(9, 'ROOM_090723195051_22200', NULL, 'Suite', 10, 5000000, '2023-07-10'),
(10, 'ROOM_090723195502_09386', NULL, 'Standart', 2, 400000, '2023-07-09'),
(11, 'ROOM_090723195502_09386', NULL, 'Standart', 2, 400000, '2023-07-10'),
(12, 'ROOM_090723200126_77655', NULL, 'Suite', 2, 1000000, '2023-07-23'),
(13, 'ROOM_090723200126_77655', NULL, 'Suite', 2, 1000000, '2023-07-24'),
(14, 'ROOM_090723200126_77655', NULL, 'Suite', 2, 1000000, '2023-07-25'),
(15, 'ROOM_090723202148_85686', NULL, 'Standart', 2, 400000, '2023-07-13'),
(16, 'ROOM_090723202148_85686', NULL, 'Standart', 2, 400000, '2023-07-13'),
(17, 'ROOM_090723202148_85686', NULL, 'Standart', 2, 400000, '2023-07-14'),
(18, 'ROOM_090723202148_85686', NULL, 'Standart', 2, 400000, '2023-07-14'),
(19, 'ROOM_090723202333_44115', NULL, 'Deluxe', 3, 750000, '2023-07-06'),
(20, 'ROOM_090723202333_44115', NULL, 'Deluxe', 3, 750000, '2023-07-06'),
(21, 'ROOM_090723202559_56633', NULL, 'Standart', 3, 600000, '2023-07-25'),
(22, 'ROOM_090723202559_56633', NULL, 'Standart', 3, 600000, '2023-07-25'),
(23, 'ROOM_090723203137_84172', NULL, 'Standart', 2, 400000, '2023-07-20'),
(24, 'ROOM_090723203137_84172', NULL, 'Suite', 2, 1000000, '2023-07-20'),
(25, 'ROOM_090723203137_84172', NULL, 'Standart', 2, 400000, '2023-07-21'),
(26, 'ROOM_090723203137_84172', NULL, 'Suite', 2, 1000000, '2023-07-21'),
(27, 'ROOM_090723223621_16476', NULL, 'Suite', 5, 2500000, '2023-08-01'),
(28, 'ROOM_090723223621_16476', NULL, 'Suite', 5, 2500000, '2023-08-02'),
(29, 'ROOM_090723224215_80260', NULL, 'Suite', 4, 2000000, '2023-08-27'),
(30, 'ROOM_090723224215_80260', NULL, 'Suite', 4, 2000000, '2023-08-28'),
(31, 'ROOM_090723224245_22109', NULL, 'Suite', 1, 500000, '2023-08-27'),
(32, 'ROOM_090723225122_42858', NULL, 'Suite', 1, 500000, '2023-08-20'),
(33, 'ROOM_090723225122_42858', NULL, 'Suite', 1, 500000, '2023-08-21'),
(34, 'ROOM_090723225205_42027', NULL, 'Suite', 3, 1500000, '2023-07-20'),
(35, 'ROOM_090723225334_30600', NULL, 'Junior Suite', 7, 2450000, '2023-07-09'),
(36, 'ROOM_090723225334_30600', NULL, 'Junior Suite', 7, 2450000, '2023-07-10'),
(37, 'ROOM_210723220530_76988', NULL, 'Standart', 1, 200000, '2023-07-22'),
(38, 'ROOM_210723220530_76988', NULL, 'Deluxe', 1, 250000, '2023-07-22'),
(39, 'ROOM_210723220530_76988', NULL, 'Junior Suite', 1, 350000, '2023-07-22'),
(40, 'ROOM_210723220530_76988', NULL, 'Suite', 5, 2500000, '2023-07-22');

-- --------------------------------------------------------

--
-- Table structure for table `room_transaction`
--

CREATE TABLE `room_transaction` (
  `room_transaction_id` varchar(255) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `date_check_in` date NOT NULL,
  `stay_duration` int(11) NOT NULL,
  `status` enum('booked','check_in','check_out') DEFAULT NULL,
  `time_booked` timestamp NULL DEFAULT current_timestamp(),
  `time_check_in` timestamp NULL DEFAULT current_timestamp(),
  `time_check_out` timestamp NULL DEFAULT current_timestamp(),
  `payment_method` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `room_transaction`
--

INSERT INTO `room_transaction` (`room_transaction_id`, `username`, `date_check_in`, `stay_duration`, `status`, `time_booked`, `time_check_in`, `time_check_out`, `payment_method`) VALUES
('1', 'nico.js', '2023-07-05', 2, 'booked', '2023-07-05 01:31:41', '2023-07-05 07:00:00', '2023-07-07 05:00:00', 'BCA'),
('2', 'otong123', '2023-07-06', 1, 'booked', '2023-07-05 01:36:27', '2023-07-06 07:00:00', '2023-07-07 05:00:00', 'OVO'),
('ROOM_090723151515_74453', 'Username1', '2023-07-09', 0, 'booked', '2023-07-09 08:15:19', NULL, NULL, 'BCA'),
('ROOM_090723152024_64234', 'Username1', '2023-07-11', 0, 'booked', '2023-07-09 08:20:28', NULL, NULL, 'BCA'),
('ROOM_090723152129_50859', 'Username1', '2023-07-09', 0, 'booked', '2023-07-09 08:21:32', NULL, NULL, 'BCA'),
('ROOM_090723152736_32411', 'Username1', '2023-07-15', 0, 'booked', '2023-07-09 08:27:39', NULL, NULL, 'BCA'),
('ROOM_090723152945_77562', 'Username1', '2023-07-09', 0, 'booked', '2023-07-09 08:29:49', NULL, NULL, 'BCA'),
('ROOM_090723153130_08491', 'Username1', '2023-07-09', 0, 'booked', '2023-07-09 08:31:33', NULL, NULL, 'BCA'),
('ROOM_090723160534_39910', 'Username1', '2023-07-09', 0, 'booked', '2023-07-09 09:05:37', NULL, NULL, 'BCA'),
('ROOM_090723183752_74854', 'Username1', '2023-07-13', 0, 'booked', '2023-07-09 11:37:58', NULL, NULL, 'BCA'),
('ROOM_090723184447_30650', 'Username1', '2023-07-09', 0, 'booked', '2023-07-09 11:44:51', NULL, NULL, 'BCA'),
('ROOM_090723184741_23289', 'Username1', '2023-07-26', 0, 'booked', '2023-07-09 11:47:44', NULL, NULL, 'BCA'),
('ROOM_090723192659_14614', 'Username1', '2023-07-14', 0, 'booked', '2023-07-09 12:27:02', NULL, NULL, 'BCA'),
('ROOM_090723192809_47038', 'Username1', '2023-07-09', 0, 'booked', '2023-07-09 12:28:14', NULL, NULL, 'BCA'),
('ROOM_090723193127_56111', 'Username1', '2023-07-09', 0, 'booked', '2023-07-09 12:31:29', NULL, NULL, 'BCA'),
('ROOM_090723193750_45507', 'Username1', '2023-07-09', 0, 'booked', '2023-07-09 12:37:55', NULL, NULL, 'BCA'),
('ROOM_090723193907_24229', 'Username1', '2023-07-09', 0, 'booked', '2023-07-09 12:39:10', NULL, NULL, 'BCA'),
('ROOM_090723194145_99043', 'Username1', '2023-07-09', 0, 'booked', '2023-07-09 12:41:49', NULL, NULL, 'BCA'),
('ROOM_090723194443_56996', 'Username1', '2023-07-09', 2, 'booked', '2023-07-09 12:44:48', NULL, NULL, 'BCA'),
('ROOM_090723194917_60044', 'Username1', '2023-07-31', 2, 'booked', '2023-07-09 12:49:19', NULL, NULL, 'BCA'),
('ROOM_090723194948_05867', 'Username1', '2023-07-09', 2, 'booked', '2023-07-09 12:49:52', NULL, NULL, 'BCA'),
('ROOM_090723195051_22200', 'Username1', '2023-07-09', 2, 'booked', '2023-07-09 12:50:55', NULL, NULL, 'BCA'),
('ROOM_090723195502_09386', 'Username1', '2023-07-09', 2, 'booked', '2023-07-09 12:55:15', NULL, NULL, 'BCA'),
('ROOM_090723200126_77655', 'Username1', '2023-07-23', 3, 'booked', '2023-07-09 13:01:29', NULL, NULL, 'BCA'),
('ROOM_090723202148_85686', 'Username1', '2023-07-13', 2, 'booked', '2023-07-09 13:21:52', NULL, NULL, 'BCA'),
('ROOM_090723202333_44115', 'Username1', '2023-07-06', 2, 'booked', '2023-07-09 13:23:43', NULL, NULL, 'BCA'),
('ROOM_090723202559_56633', 'Username1', '2023-07-25', 2, 'booked', '2023-07-09 13:26:09', NULL, NULL, 'BCA'),
('ROOM_090723203137_84172', 'Username1', '2023-07-20', 2, 'booked', '2023-07-09 13:31:45', NULL, NULL, 'BCA'),
('ROOM_090723223621_16476', 'Username1', '2023-08-01', 2, 'booked', '2023-07-09 15:36:26', NULL, NULL, 'BCA'),
('ROOM_090723224215_80260', 'Username1', '2023-08-27', 2, 'booked', '2023-07-09 15:42:18', NULL, NULL, 'BCA'),
('ROOM_090723224245_22109', 'Username1', '2023-08-27', 1, 'booked', '2023-07-09 15:42:48', NULL, NULL, 'BCA'),
('ROOM_090723225122_42858', 'Username1', '2023-08-20', 2, 'booked', '2023-07-09 15:51:30', NULL, NULL, 'BCA'),
('ROOM_090723225205_42027', 'Username1', '2023-07-20', 1, 'booked', '2023-07-09 15:52:09', NULL, NULL, 'BCA'),
('ROOM_090723225334_30600', 'Username1', '2023-07-09', 2, 'booked', '2023-07-09 15:53:37', NULL, NULL, 'BCA'),
('ROOM_140723080515_000', 'marcelandrean', '2023-07-14', 1, 'booked', '2023-07-12 01:05:24', '2023-07-14 01:05:24', '2023-07-15 01:05:24', 'BCA'),
('ROOM_210723220530_76988', 'otong123', '2023-07-22', 1, 'booked', '2023-07-21 15:05:35', NULL, NULL, 'BCA');

-- --------------------------------------------------------

--
-- Table structure for table `room_type`
--

CREATE TABLE `room_type` (
  `room_type_id` int(11) NOT NULL,
  `type_name` varchar(255) NOT NULL,
  `price` double NOT NULL,
  `number_of_room` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `room_type`
--

INSERT INTO `room_type` (`room_type_id`, `type_name`, `price`, `number_of_room`) VALUES
(1, 'Standart', 200000, 20),
(2, 'Deluxe', 250000, 10),
(3, 'Junior Suite', 350000, 7),
(4, 'Suite', 500000, 5);

-- --------------------------------------------------------

--
-- Table structure for table `staff`
--

CREATE TABLE `staff` (
  `staff_id` int(11) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `NIK` varchar(255) DEFAULT NULL,
  `salary` double DEFAULT NULL,
  `type` enum('receptionist','staff_fnb','staff_laundry','manager') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `staff`
--

INSERT INTO `staff` (`staff_id`, `username`, `NIK`, `salary`, `type`) VALUES
(1, 'staff_laundry', '1234567890123456', 3000000, 'staff_laundry'),
(2, 'epen', '1212121', 23333333, 'receptionist');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `username` varchar(255) NOT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `gender` enum('male','female') DEFAULT NULL,
  `phone_number` varchar(20) DEFAULT NULL,
  `type` enum('CUSTOMER','RECEPTIONIST','STAFF_FNB','STAFF_LAUNDRY','MANAGER') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`username`, `full_name`, `email`, `password`, `address`, `gender`, `phone_number`, `type`) VALUES
('epen', 'steven', 'steven@gmail.com', '202cb962ac59075b964b07152d234b70', 'tki 1', 'male', '089522331', 'STAFF_FNB'),
('marcelandrean', 'Marcel Andrean', 'marcel@gmail.com', '202cb962ac59075b964b07152d234b70', 'Taman Rahayu', 'male', '08798123445', 'CUSTOMER'),
('nico.js', 'nico.js', 'niconew@gmail.com', '202cb962ac59075b964b07152d234b70', ' tki 1', 'male', '08123456789', 'CUSTOMER'),
('otong123', 'otong surotong', 'tong@gmail.com', '202cb962ac59075b964b07152d234b70', 'BABI', 'male', '0812', 'CUSTOMER'),
('receptionist_ganteng', 'pokoknya ganteng deh', 'receptionist@gmail.com', '202cb962ac59075b964b07152d234b70', 'kepo dih', 'male', '087xxxxxx', 'RECEPTIONIST'),
('ril', 'ngabril', 'ngab@gmail.com', '202cb962ac59075b964b07152d234b70', 'tki11', 'male', '1234567', 'CUSTOMER'),
('staff_laundry', 'Ujang Bujang', 'ujang@staff.hotelHB.ac.id', '202cb962ac59075b964b07152d234b70', 'null', 'male', '0812345678', 'STAFF_LAUNDRY'),
('Username1', 'fullname', 'email@gmail.com', '123', 'adress123', 'male', '085xxxxxx', 'CUSTOMER');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `favorite`
--
ALTER TABLE `favorite`
  ADD PRIMARY KEY (`favorite_id`),
  ADD KEY `room_type` (`room_type`),
  ADD KEY `username` (`username`);

--
-- Indexes for table `fnb_menu`
--
ALTER TABLE `fnb_menu`
  ADD PRIMARY KEY (`menu_name`);

--
-- Indexes for table `fnb_order`
--
ALTER TABLE `fnb_order`
  ADD PRIMARY KEY (`order_id`),
  ADD KEY `fnb_transaction_id` (`fnb_transaction_id`),
  ADD KEY `menu_name` (`menu_name`);

--
-- Indexes for table `fnb_transaction`
--
ALTER TABLE `fnb_transaction`
  ADD PRIMARY KEY (`fnb_transaction_id`),
  ADD KEY `fnb_transaction_ibfk_1` (`username`),
  ADD KEY `payment_method_ibfk_3` (`payment_method`);

--
-- Indexes for table `hotel_facility`
--
ALTER TABLE `hotel_facility`
  ADD PRIMARY KEY (`hotel_facility_id`);

--
-- Indexes for table `laundry`
--
ALTER TABLE `laundry`
  ADD PRIMARY KEY (`laundry_name`);

--
-- Indexes for table `laundry_transaction`
--
ALTER TABLE `laundry_transaction`
  ADD PRIMARY KEY (`laundry_transaction_id`),
  ADD KEY `laundry_transaction_ibfk_1` (`username`),
  ADD KEY `payment_method_ibfk_2` (`payment_method`),
  ADD KEY `laundry_name` (`laundry_name`);

--
-- Indexes for table `member`
--
ALTER TABLE `member`
  ADD PRIMARY KEY (`member_id`),
  ADD KEY `username` (`username`);

--
-- Indexes for table `merchandise`
--
ALTER TABLE `merchandise`
  ADD PRIMARY KEY (`Merchandise_id`),
  ADD UNIQUE KEY `Merchandise_name` (`Merchandise_name`);

--
-- Indexes for table `payment_method`
--
ALTER TABLE `payment_method`
  ADD PRIMARY KEY (`payment_method_id`),
  ADD UNIQUE KEY `name` (`name`);

--
-- Indexes for table `redeem_merchandise`
--
ALTER TABLE `redeem_merchandise`
  ADD PRIMARY KEY (`redeem_id`),
  ADD KEY `merchandise` (`merchandise`),
  ADD KEY `username` (`username`);

--
-- Indexes for table `room`
--
ALTER TABLE `room`
  ADD PRIMARY KEY (`room_number`);

--
-- Indexes for table `room_facility`
--
ALTER TABLE `room_facility`
  ADD PRIMARY KEY (`room_facility_id`),
  ADD KEY `room_type` (`room_type`);

--
-- Indexes for table `room_order`
--
ALTER TABLE `room_order`
  ADD PRIMARY KEY (`room_order_id`),
  ADD KEY `room_transaction_id` (`room_transaction_id`),
  ADD KEY `room_number` (`room_number`),
  ADD KEY `room_order_ibfk_3` (`room_type`);

--
-- Indexes for table `room_transaction`
--
ALTER TABLE `room_transaction`
  ADD PRIMARY KEY (`room_transaction_id`),
  ADD KEY `username` (`username`),
  ADD KEY `payment_method` (`payment_method`);

--
-- Indexes for table `room_type`
--
ALTER TABLE `room_type`
  ADD PRIMARY KEY (`room_type_id`),
  ADD UNIQUE KEY `type_name` (`type_name`),
  ADD UNIQUE KEY `type_name_2` (`type_name`),
  ADD UNIQUE KEY `type_name_3` (`type_name`);

--
-- Indexes for table `staff`
--
ALTER TABLE `staff`
  ADD PRIMARY KEY (`staff_id`),
  ADD KEY `username` (`username`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `favorite`
--
ALTER TABLE `favorite`
  MODIFY `favorite_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `fnb_order`
--
ALTER TABLE `fnb_order`
  MODIFY `order_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `hotel_facility`
--
ALTER TABLE `hotel_facility`
  MODIFY `hotel_facility_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `member`
--
ALTER TABLE `member`
  MODIFY `member_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `merchandise`
--
ALTER TABLE `merchandise`
  MODIFY `Merchandise_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `payment_method`
--
ALTER TABLE `payment_method`
  MODIFY `payment_method_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `redeem_merchandise`
--
ALTER TABLE `redeem_merchandise`
  MODIFY `redeem_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `room`
--
ALTER TABLE `room`
  MODIFY `room_number` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `room_facility`
--
ALTER TABLE `room_facility`
  MODIFY `room_facility_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `room_order`
--
ALTER TABLE `room_order`
  MODIFY `room_order_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=41;

--
-- AUTO_INCREMENT for table `room_type`
--
ALTER TABLE `room_type`
  MODIFY `room_type_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `staff`
--
ALTER TABLE `staff`
  MODIFY `staff_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `favorite`
--
ALTER TABLE `favorite`
  ADD CONSTRAINT `favorite_ibfk_1` FOREIGN KEY (`room_type`) REFERENCES `room_type` (`type_name`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `favorite_ibfk_2` FOREIGN KEY (`username`) REFERENCES `users` (`username`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `fnb_order`
--
ALTER TABLE `fnb_order`
  ADD CONSTRAINT `fnb_order_ibfk_1` FOREIGN KEY (`fnb_transaction_id`) REFERENCES `fnb_transaction` (`fnb_transaction_id`),
  ADD CONSTRAINT `fnb_order_ibfk_2` FOREIGN KEY (`menu_name`) REFERENCES `fnb_menu` (`menu_name`) ON DELETE CASCADE;

--
-- Constraints for table `fnb_transaction`
--
ALTER TABLE `fnb_transaction`
  ADD CONSTRAINT `fnb_transaction_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `payment_method_ibfk_3` FOREIGN KEY (`payment_method`) REFERENCES `payment_method` (`name`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `laundry_transaction`
--
ALTER TABLE `laundry_transaction`
  ADD CONSTRAINT `laundry_transaction_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `laundry_transaction_ibfk_2` FOREIGN KEY (`laundry_name`) REFERENCES `laundry` (`laundry_name`),
  ADD CONSTRAINT `payment_method_ibfk_2` FOREIGN KEY (`payment_method`) REFERENCES `payment_method` (`name`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `member`
--
ALTER TABLE `member`
  ADD CONSTRAINT `member_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `redeem_merchandise`
--
ALTER TABLE `redeem_merchandise`
  ADD CONSTRAINT `redeem_merchandise_ibfk_1` FOREIGN KEY (`merchandise`) REFERENCES `merchandise` (`Merchandise_name`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `redeem_merchandise_ibfk_2` FOREIGN KEY (`username`) REFERENCES `users` (`username`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `room_facility`
--
ALTER TABLE `room_facility`
  ADD CONSTRAINT `room_facility_ibfk_2` FOREIGN KEY (`room_type`) REFERENCES `room_type` (`type_name`);

--
-- Constraints for table `room_order`
--
ALTER TABLE `room_order`
  ADD CONSTRAINT `room_order_ibfk_1` FOREIGN KEY (`room_transaction_id`) REFERENCES `room_transaction` (`room_transaction_id`),
  ADD CONSTRAINT `room_order_ibfk_2` FOREIGN KEY (`room_number`) REFERENCES `room` (`room_number`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `room_order_ibfk_3` FOREIGN KEY (`room_type`) REFERENCES `room_type` (`type_name`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `room_transaction`
--
ALTER TABLE `room_transaction`
  ADD CONSTRAINT `fk_room_transaction_username` FOREIGN KEY (`username`) REFERENCES `users` (`username`),
  ADD CONSTRAINT `payment_method` FOREIGN KEY (`payment_method`) REFERENCES `payment_method` (`name`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `room_transaction_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `staff`
--
ALTER TABLE `staff`
  ADD CONSTRAINT `staff_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
