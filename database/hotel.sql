-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 05, 2018 at 08:25 AM
-- Server version: 10.1.29-MariaDB
-- PHP Version: 7.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `hotel`
--

-- --------------------------------------------------------

--
-- Table structure for table `billdet`
--

CREATE TABLE `billdet` (
  `itemno` int(20) NOT NULL,
  `itemname` varchar(50) NOT NULL,
  `itshort` varchar(15) NOT NULL,
  `qty` int(30) NOT NULL,
  `price` int(30) NOT NULL,
  `tblno` int(20) NOT NULL,
  `serno` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `emp`
--

CREATE TABLE `emp` (
  `id` int(20) NOT NULL,
  `name` varchar(50) NOT NULL,
  `Aadhar` varchar(50) NOT NULL,
  `phone` int(20) NOT NULL,
  `place` varchar(30) NOT NULL,
  `doj` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `itemlist`
--

CREATE TABLE `itemlist` (
  `sno` int(20) NOT NULL,
  `itemname` varchar(50) NOT NULL,
  `cost` float NOT NULL,
  `itshort` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `itemlist`
--

INSERT INTO `itemlist` (`sno`, `itemname`, `cost`, `itshort`) VALUES
(1, 'Aloo Parotta', 33, 'A.Parotta'),
(2, 'American Chicken Chopsuey', 150, 'A.Ch.Chop'),
(3, 'American Egg Chopsuey', 140, 'A.Eg.Chop'),
(4, 'American Veg Chopsuey', 130, 'A.Vg.Chop'),
(5, 'Butter Naan', 35, 'Butr.Naan'),
(6, 'Butter Roti', 30, 'Butr.Roti'),
(7, 'Bun Parotta', 20, 'B.Parotta'),
(8, 'Balaji Special Chicken', 150, 'B.Sp.Chik'),
(9, 'Balaji Spl.Chicken Gravy', 150, 'B.Sp.Chk.G'),
(10, 'Butter Chicken', 130, 'Butr.Chik'),
(11, 'Baby Corn Chilli', 60, 'BbCn.Chli'),
(12, 'Baby corn Pepper & Salt', 70, 'Bcn.Pr.St'),
(13, 'Chicken Briyani', 90, 'C.Briyani'),
(14, 'Chicken Fried Rice', 80, 'Ck.Fr.Rce');

-- --------------------------------------------------------

--
-- Table structure for table `login`
--

CREATE TABLE `login` (
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `login`
--

INSERT INTO `login` (`username`, `password`) VALUES
('admin', 'admin'),
('abc', '123');

-- --------------------------------------------------------

--
-- Table structure for table `sales`
--

CREATE TABLE `sales` (
  `billno` int(50) NOT NULL,
  `serviceno` int(20) NOT NULL,
  `total` int(30) NOT NULL,
  `date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `stock`
--

CREATE TABLE `stock` (
  `name` varchar(30) NOT NULL,
  `quantity` int(3) NOT NULL,
  `unit` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `stock`
--

INSERT INTO `stock` (`name`, `quantity`, `unit`) VALUES
('Rice', 45, 'kg');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `emp`
--
ALTER TABLE `emp`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `itemlist`
--
ALTER TABLE `itemlist`
  ADD PRIMARY KEY (`sno`);

--
-- Indexes for table `sales`
--
ALTER TABLE `sales`
  ADD PRIMARY KEY (`billno`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `emp`
--
ALTER TABLE `emp`
  MODIFY `id` int(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `itemlist`
--
ALTER TABLE `itemlist`
  MODIFY `sno` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `sales`
--
ALTER TABLE `sales`
  MODIFY `billno` int(50) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
