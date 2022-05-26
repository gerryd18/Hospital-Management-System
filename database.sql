-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 26, 2022 at 12:17 PM
-- Server version: 10.4.20-MariaDB
-- PHP Version: 8.0.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `hms`
--

-- --------------------------------------------------------

--
-- Table structure for table `channel`
--

CREATE TABLE `channel` (
  `channelNo` int(10) NOT NULL,
  `doctorName` varchar(255) NOT NULL,
  `patientName` varchar(255) NOT NULL,
  `roomNo` varchar(255) NOT NULL,
  `date` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `channel`
--

INSERT INTO `channel` (`channelNo`, `doctorName`, `patientName`, `roomNo`, `date`) VALUES
(1, 'jesslyn', 'julian', '101', '15-05-2022'),
(2, 'jesslyn', 'audie', '102', '15-05-2022'),
(4, 'gerry', 'ferry', '102', '22-05-2022');

-- --------------------------------------------------------

--
-- Table structure for table `drug`
--

CREATE TABLE `drug` (
  `drugID` int(10) NOT NULL,
  `drugname` varchar(255) NOT NULL,
  `price` int(10) NOT NULL,
  `qty` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `drug`
--

INSERT INTO `drug` (`drugID`, `drugname`, `price`, `qty`) VALUES
(1, 'OBH', 5000, 5),
(4, 'Panadol biru', 2000, 50),
(5, 'Panadol hijau', 1000, 200),
(6, 'Panadol pink', 1000, 150);

-- --------------------------------------------------------

--
-- Table structure for table `patient`
--

CREATE TABLE `patient` (
  `patientID` varchar(255) NOT NULL,
  `patientName` varchar(255) NOT NULL,
  `patientNumber` varchar(255) NOT NULL,
  `patientAddress` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `patient`
--

INSERT INTO `patient` (`patientID`, `patientName`, `patientNumber`, `patientAddress`) VALUES
('PA134', 'audie', '081264547282', 'Jembatan lima'),
('PA419', 'julian', '081284849822', 'tanjung duren'),
('PA864', 'ferry', '081763847262', 'Taman Palem');

-- --------------------------------------------------------

--
-- Table structure for table `prescription`
--

CREATE TABLE `prescription` (
  `prescriptionID` int(10) NOT NULL,
  `channelID` int(10) NOT NULL,
  `deseaseType` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `prescription`
--

INSERT INTO `prescription` (`prescriptionID`, `channelID`, `deseaseType`, `description`) VALUES
(1, 2, 'fever', 'Panadol ijo broh'),
(2, 2, 'pilek', 'kebanyakan main hape kata emak'),
(3, 4, 'demam', 'paracetamol'),
(4, 4, 'batuk', 'obat batuk\n'),
(5, 4, 'pilek', 'obat pilek');

-- --------------------------------------------------------

--
-- Table structure for table `salesinventory`
--

CREATE TABLE `salesinventory` (
  `salesID` int(10) NOT NULL,
  `prescriptionID` int(10) NOT NULL,
  `drugCode` int(10) NOT NULL,
  `drugName` varchar(255) NOT NULL,
  `qty` int(10) NOT NULL,
  `price` int(10) NOT NULL,
  `totalPrice` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `salesinventory`
--

INSERT INTO `salesinventory` (`salesID`, `prescriptionID`, `drugCode`, `drugName`, `qty`, `price`, `totalPrice`) VALUES
(1, 5, 6, 'Panadol pink', 2, 1000, 2000),
(2, 4, 1, 'OBH', 5, 5000, 25000),
(3, 3, 1, 'OBH', 5, 5000, 25000),
(4, 5, 6, 'Panadol pink', 50, 1000, 50000);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `userID` int(10) NOT NULL,
  `username` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`userID`, `username`, `email`, `password`, `role`) VALUES
(1, 'gerry', 'gerry@gmail.com', '123', 'Doctor'),
(2, 'jesslyn', 'jesslyn@gmail.com', '123', 'Doctor'),
(3, 'admin', 'admin@gmail.com', '123', 'admin'),
(4, 'receptionist', 'receptionist@gmail.com', '123', 'Receptionist'),
(5, 'ferry', 'ferry@gmail.com', '123', 'Pharmacist');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `channel`
--
ALTER TABLE `channel`
  ADD PRIMARY KEY (`channelNo`);

--
-- Indexes for table `drug`
--
ALTER TABLE `drug`
  ADD PRIMARY KEY (`drugID`);

--
-- Indexes for table `patient`
--
ALTER TABLE `patient`
  ADD PRIMARY KEY (`patientID`);

--
-- Indexes for table `prescription`
--
ALTER TABLE `prescription`
  ADD PRIMARY KEY (`prescriptionID`),
  ADD KEY `channelID` (`channelID`);

--
-- Indexes for table `salesinventory`
--
ALTER TABLE `salesinventory`
  ADD PRIMARY KEY (`salesID`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`userID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `drug`
--
ALTER TABLE `drug`
  MODIFY `drugID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `prescription`
--
ALTER TABLE `prescription`
  MODIFY `prescriptionID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `salesinventory`
--
ALTER TABLE `salesinventory`
  MODIFY `salesID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `userID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
