-- phpMyAdmin SQL Dump
-- version 4.8.0.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Dec 06, 2019 at 06:43 AM
-- Server version: 10.1.32-MariaDB
-- PHP Version: 7.2.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_pharma`
--

-- --------------------------------------------------------

--
-- Table structure for table `tbl_bill`
--

CREATE TABLE `tbl_bill` (
  `id` bigint(20) NOT NULL,
  `bill_no` bigint(20) NOT NULL,
  `billed_date` date NOT NULL,
  `doctor_name` varchar(50) NOT NULL,
  `patient_name` varchar(50) NOT NULL,
  `patient_address` varchar(100) NOT NULL,
  `gender` char(2) NOT NULL,
  `contact_no` bigint(20) NOT NULL,
  `medicines` text NOT NULL,
  `quantities` text NOT NULL,
  `rates` text NOT NULL,
  `totals` text NOT NULL,
  `discount` float NOT NULL,
  `grand_total` float NOT NULL,
  `soldby` varchar(50) NOT NULL,
  `file` longblob NOT NULL,
  `file_name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_bill`
--

INSERT INTO `tbl_bill` (`id`, `bill_no`, `billed_date`, `doctor_name`, `patient_name`, `patient_address`, `gender`, `contact_no`, `medicines`, `quantities`, `rates`, `totals`, `discount`, `grand_total`, `soldby`, `file`, `file_name`) VALUES
(1, 0, '1111-11-11', 'test', 'test test', 'test address', 'M', 0, '', '', '', '', 0, 0, '', '', '');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_pharmacist`
--

CREATE TABLE `tbl_pharmacist` (
  `id` int(11) NOT NULL,
  `full_name` varchar(50) NOT NULL,
  `email` varchar(100) NOT NULL,
  `contact_address` varchar(100) NOT NULL,
  `gender` char(1) NOT NULL,
  `contact_no` bigint(11) NOT NULL,
  `user_name` varchar(50) NOT NULL,
  `user_pass` varchar(50) NOT NULL,
  `registered_date` date NOT NULL,
  `retired_date` date NOT NULL,
  `retired_status` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_pharmacy_info`
--

CREATE TABLE `tbl_pharmacy_info` (
  `id` int(11) NOT NULL,
  `pharmacy_name` varchar(100) NOT NULL,
  `registered_date` date NOT NULL,
  `pan_no` int(11) NOT NULL,
  `telephone` bigint(20) NOT NULL,
  `address` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_pharmacy_info`
--

INSERT INTO `tbl_pharmacy_info` (`id`, `pharmacy_name`, `registered_date`, `pan_no`, `telephone`, `address`) VALUES
(1, 'Mansuwa', '2012-08-06', 123456, 9849675658, 'Kathmandu');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_product`
--

CREATE TABLE `tbl_product` (
  `id` int(11) NOT NULL,
  `generic_name` varchar(255) NOT NULL,
  `brand_name` varchar(255) NOT NULL,
  `company_name` varchar(200) NOT NULL,
  `distributor` varchar(255) NOT NULL,
  `manufacture_date` date NOT NULL,
  `expire_date` date NOT NULL,
  `cost_price` double NOT NULL,
  `selling_price` double NOT NULL,
  `delete_status` tinyint(1) NOT NULL,
  `quantity` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_user`
--

CREATE TABLE `tbl_user` (
  `id` int(11) NOT NULL,
  `user_pass` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `role` varchar(15) NOT NULL,
  `user_name` varchar(50) NOT NULL,
  `invalid_count` smallint(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_user`
--

INSERT INTO `tbl_user` (`id`, `user_pass`, `email`, `role`, `user_name`, `invalid_count`) VALUES
(1, 'admin', 'myselfsujan67@gmail.com', 'Administrator', 'admin', 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tbl_bill`
--
ALTER TABLE `tbl_bill`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_pharmacist`
--
ALTER TABLE `tbl_pharmacist`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_pharmacy_info`
--
ALTER TABLE `tbl_pharmacy_info`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_product`
--
ALTER TABLE `tbl_product`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_user`
--
ALTER TABLE `tbl_user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tbl_bill`
--
ALTER TABLE `tbl_bill`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `tbl_pharmacist`
--
ALTER TABLE `tbl_pharmacist`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `tbl_pharmacy_info`
--
ALTER TABLE `tbl_pharmacy_info`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `tbl_product`
--
ALTER TABLE `tbl_product`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `tbl_user`
--
ALTER TABLE `tbl_user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
