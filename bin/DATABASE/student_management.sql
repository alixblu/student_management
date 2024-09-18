-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Sep 11, 2024 at 06:55 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `student_management`
--
CREATE DATABASE IF NOT EXISTS `student_management` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `student_management`;

-- --------------------------------------------------------

--
-- Table structure for table `chitietdiem`
--

CREATE TABLE IF NOT EXISTS `chitietdiem` (
  `HocSinhid` varchar(10) NOT NULL,
  `MonHocid` varchar(11) NOT NULL,
  `HocKyid` int(11) NOT NULL,
  `HeSoid` int(11) NOT NULL,
  `NamHocid` varchar(20) NOT NULL DEFAULT '',
  `Diem` float DEFAULT NULL,
  PRIMARY KEY (`HocSinhid`,`MonHocid`,`HocKyid`,`HeSoid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `chitietdiem`
--

INSERT INTO `chitietdiem` (`HocSinhid`, `MonHocid`, `HocKyid`, `HeSoid`, `NamHocid`, `Diem`) VALUES
('HS1', 'ANH', 1, 1, 'giapthin', 9),
('HS1', 'ANH', 1, 2, 'giapthin', 10),
('HS1', 'ANH', 1, 3, 'giapthin', 7),
('HS1', 'ANH', 2, 1, 'giapthin', 10),
('HS1', 'ANH', 2, 2, 'giapthin', 9),
('HS1', 'ANH', 2, 3, 'giapthin', 10),
('HS1', 'HOA', 1, 1, 'giapthin', 5),
('HS1', 'HOA', 1, 2, 'giapthin', 9),
('HS1', 'HOA', 1, 3, 'giapthin', 10),
('HS1', 'HOA', 2, 1, 'giapthin', 9),
('HS1', 'HOA', 2, 2, 'giapthin', 7),
('HS1', 'HOA', 2, 3, 'giapthin', 8),
('HS1', 'LY', 1, 1, 'giapthin', 7),
('HS1', 'LY', 1, 2, 'giapthin', 6),
('HS1', 'LY', 1, 3, 'giapthin', 5),
('HS1', 'LY', 2, 1, 'giapthin', 10),
('HS1', 'LY', 2, 2, 'giapthin', 10),
('HS1', 'LY', 2, 3, 'giapthin', 10),
('HS1', 'TOAN', 1, 1, 'giapthin', 6),
('HS1', 'TOAN', 1, 2, 'giapthin', 9),
('HS1', 'TOAN', 1, 3, 'giapthin', 9),
('HS1', 'TOAN', 2, 1, 'giapthin', 7),
('HS1', 'TOAN', 2, 2, 'giapthin', 5),
('HS1', 'TOAN', 2, 3, 'giapthin', 9),
('HS10', 'ANH', 1, 1, 'giapthin', 9),
('HS10', 'ANH', 1, 2, 'giapthin', 8),
('HS10', 'ANH', 1, 3, 'giapthin', 7),
('HS10', 'ANH', 2, 1, 'giapthin', 7),
('HS10', 'ANH', 2, 2, 'giapthin', 8),
('HS10', 'ANH', 2, 3, 'giapthin', 9),
('HS10', 'HOA', 1, 1, 'giapthin', 9),
('HS10', 'HOA', 1, 2, 'giapthin', 8),
('HS10', 'HOA', 1, 3, 'giapthin', 7),
('HS10', 'HOA', 2, 1, 'giapthin', 9),
('HS10', 'HOA', 2, 2, 'giapthin', 8),
('HS10', 'HOA', 2, 3, 'giapthin', 8),
('HS10', 'LY', 1, 1, 'giapthin', 9),
('HS10', 'LY', 1, 2, 'giapthin', 8),
('HS10', 'LY', 1, 3, 'giapthin', 7),
('HS10', 'LY', 2, 1, 'giapthin', 7),
('HS10', 'LY', 2, 2, 'giapthin', 8),
('HS10', 'LY', 2, 3, 'giapthin', 9),
('HS10', 'TOAN', 1, 1, 'giapthin', 9),
('HS10', 'TOAN', 1, 2, 'giapthin', 8),
('HS10', 'TOAN', 1, 3, 'giapthin', 7),
('HS10', 'TOAN', 2, 1, 'giapthin', 9),
('HS10', 'TOAN', 2, 2, 'giapthin', 8),
('HS10', 'TOAN', 2, 3, 'giapthin', 7),
('HS11', 'ANH', 1, 1, 'giapthin', 9),
('HS11', 'ANH', 1, 2, 'giapthin', 9),
('HS11', 'ANH', 1, 3, 'giapthin', 8),
('HS11', 'ANH', 2, 1, 'giapthin', 7),
('HS11', 'ANH', 2, 2, 'giapthin', 7),
('HS11', 'ANH', 2, 3, 'giapthin', 7),
('HS11', 'HOA', 1, 1, 'giapthin', 8),
('HS11', 'HOA', 1, 2, 'giapthin', 7),
('HS11', 'HOA', 1, 3, 'giapthin', 9),
('HS11', 'HOA', 2, 1, 'giapthin', 7),
('HS11', 'HOA', 2, 2, 'giapthin', 7),
('HS11', 'HOA', 2, 3, 'giapthin', 7),
('HS11', 'LY', 1, 1, 'giapthin', 5),
('HS11', 'LY', 1, 2, 'giapthin', 9),
('HS11', 'LY', 1, 3, 'giapthin', 9),
('HS11', 'LY', 2, 1, 'giapthin', 7),
('HS11', 'LY', 2, 2, 'giapthin', 7),
('HS11', 'LY', 2, 3, 'giapthin', 7),
('HS11', 'TOAN', 1, 1, 'giapthin', 9),
('HS11', 'TOAN', 1, 2, 'giapthin', 10),
('HS11', 'TOAN', 1, 3, 'giapthin', 6),
('HS11', 'TOAN', 2, 1, 'giapthin', 7),
('HS11', 'TOAN', 2, 2, 'giapthin', 7),
('HS11', 'TOAN', 2, 3, 'giapthin', 7),
('HS12', 'ANH', 1, 1, 'giapthin', 9),
('HS12', 'ANH', 1, 2, 'giapthin', 9),
('HS12', 'ANH', 1, 3, 'giapthin', 9),
('HS12', 'ANH', 2, 1, 'giapthin', 10),
('HS12', 'ANH', 2, 2, 'giapthin', 10),
('HS12', 'ANH', 2, 3, 'giapthin', 10),
('HS12', 'HOA', 1, 1, 'giapthin', 7),
('HS12', 'HOA', 1, 2, 'giapthin', 8),
('HS12', 'HOA', 1, 3, 'giapthin', 9),
('HS12', 'HOA', 2, 1, 'giapthin', 10),
('HS12', 'HOA', 2, 2, 'giapthin', 10),
('HS12', 'HOA', 2, 3, 'giapthin', 10),
('HS12', 'LY', 1, 1, 'giapthin', 10),
('HS12', 'LY', 1, 2, 'giapthin', 5),
('HS12', 'LY', 1, 3, 'giapthin', 10),
('HS12', 'LY', 2, 1, 'giapthin', 10),
('HS12', 'LY', 2, 2, 'giapthin', 10),
('HS12', 'LY', 2, 3, 'giapthin', 10),
('HS12', 'TOAN', 1, 1, 'giapthin', 8),
('HS12', 'TOAN', 1, 2, 'giapthin', 7),
('HS12', 'TOAN', 1, 3, 'giapthin', 9),
('HS12', 'TOAN', 2, 1, 'giapthin', 10),
('HS12', 'TOAN', 2, 2, 'giapthin', 9),
('HS12', 'TOAN', 2, 3, 'giapthin', 10),
('HS13', 'ANH', 1, 1, 'giapthin', 10),
('HS13', 'ANH', 1, 2, 'giapthin', 9),
('HS13', 'ANH', 1, 3, 'giapthin', 9),
('HS13', 'ANH', 2, 1, 'giapthin', 9),
('HS13', 'ANH', 2, 2, 'giapthin', 9),
('HS13', 'ANH', 2, 3, 'giapthin', 9),
('HS13', 'HOA', 1, 1, 'giapthin', 8),
('HS13', 'HOA', 1, 2, 'giapthin', 9),
('HS13', 'HOA', 1, 3, 'giapthin', 7),
('HS13', 'HOA', 2, 1, 'giapthin', 8),
('HS13', 'HOA', 2, 2, 'giapthin', 8),
('HS13', 'HOA', 2, 3, 'giapthin', 8),
('HS13', 'LY', 1, 1, 'giapthin', 5),
('HS13', 'LY', 1, 2, 'giapthin', 4),
('HS13', 'LY', 1, 3, 'giapthin', 9),
('HS13', 'LY', 2, 1, 'giapthin', 7),
('HS13', 'LY', 2, 2, 'giapthin', 7),
('HS13', 'LY', 2, 3, 'giapthin', 7),
('HS13', 'TOAN', 1, 1, 'giapthin', 10),
('HS13', 'TOAN', 1, 2, 'giapthin', 9),
('HS13', 'TOAN', 1, 3, 'giapthin', 8),
('HS13', 'TOAN', 2, 1, 'giapthin', 6),
('HS13', 'TOAN', 2, 2, 'giapthin', 6),
('HS13', 'TOAN', 2, 3, 'giapthin', 6),
('HS14', 'ANH', 1, 1, 'giapthin', 7),
('HS14', 'ANH', 1, 2, 'giapthin', 8),
('HS14', 'ANH', 1, 3, 'giapthin', 7),
('HS14', 'ANH', 2, 1, 'giapthin', 9),
('HS14', 'ANH', 2, 2, 'giapthin', 7),
('HS14', 'ANH', 2, 3, 'giapthin', 10),
('HS14', 'HOA', 1, 1, 'giapthin', 9),
('HS14', 'HOA', 1, 2, 'giapthin', 8),
('HS14', 'HOA', 1, 3, 'giapthin', 10),
('HS14', 'HOA', 2, 1, 'giapthin', 9),
('HS14', 'HOA', 2, 2, 'giapthin', 7),
('HS14', 'HOA', 2, 3, 'giapthin', 8),
('HS14', 'LY', 1, 1, 'giapthin', 9),
('HS14', 'LY', 1, 2, 'giapthin', 7),
('HS14', 'LY', 1, 3, 'giapthin', 10),
('HS14', 'LY', 2, 1, 'giapthin', 9),
('HS14', 'LY', 2, 2, 'giapthin', 7),
('HS14', 'LY', 2, 3, 'giapthin', 8),
('HS14', 'TOAN', 1, 1, 'giapthin', 9),
('HS14', 'TOAN', 1, 2, 'giapthin', 10),
('HS14', 'TOAN', 1, 3, 'giapthin', 8),
('HS14', 'TOAN', 2, 1, 'giapthin', 7),
('HS14', 'TOAN', 2, 2, 'giapthin', 8),
('HS14', 'TOAN', 2, 3, 'giapthin', 6),
('HS15', 'ANH', 1, 1, 'giapthin', 10),
('HS15', 'ANH', 1, 2, 'giapthin', 8),
('HS15', 'ANH', 1, 3, 'giapthin', 9),
('HS15', 'ANH', 2, 1, 'giapthin', 7),
('HS15', 'ANH', 2, 2, 'giapthin', 7),
('HS15', 'ANH', 2, 3, 'giapthin', 7),
('HS15', 'HOA', 1, 1, 'giapthin', 8),
('HS15', 'HOA', 1, 2, 'giapthin', 7),
('HS15', 'HOA', 1, 3, 'giapthin', 9),
('HS15', 'HOA', 2, 1, 'giapthin', 8),
('HS15', 'HOA', 2, 2, 'giapthin', 7),
('HS15', 'HOA', 2, 3, 'giapthin', 5),
('HS15', 'LY', 1, 1, 'giapthin', 6),
('HS15', 'LY', 1, 2, 'giapthin', 9),
('HS15', 'LY', 1, 3, 'giapthin', 10),
('HS15', 'LY', 2, 1, 'giapthin', 7),
('HS15', 'LY', 2, 2, 'giapthin', 6),
('HS15', 'LY', 2, 3, 'giapthin', 7),
('HS15', 'TOAN', 1, 1, 'giapthin', 9),
('HS15', 'TOAN', 1, 2, 'giapthin', 8),
('HS15', 'TOAN', 1, 3, 'giapthin', 10),
('HS15', 'TOAN', 2, 1, 'giapthin', 9),
('HS15', 'TOAN', 2, 2, 'giapthin', 9),
('HS15', 'TOAN', 2, 3, 'giapthin', 7),
('HS16', 'ANH', 1, 1, 'giapthin', 7),
('HS16', 'ANH', 1, 2, 'giapthin', 7),
('HS16', 'ANH', 1, 3, 'giapthin', 7),
('HS16', 'ANH', 2, 1, 'giapthin', 7),
('HS16', 'ANH', 2, 2, 'giapthin', 7),
('HS16', 'ANH', 2, 3, 'giapthin', 6),
('HS16', 'HOA', 1, 1, 'giapthin', 5),
('HS16', 'HOA', 1, 2, 'giapthin', 8),
('HS16', 'HOA', 1, 3, 'giapthin', 7),
('HS16', 'HOA', 2, 1, 'giapthin', 8),
('HS16', 'HOA', 2, 2, 'giapthin', 5),
('HS16', 'HOA', 2, 3, 'giapthin', 7),
('HS16', 'LY', 1, 1, 'giapthin', 7),
('HS16', 'LY', 1, 2, 'giapthin', 8),
('HS16', 'LY', 1, 3, 'giapthin', 8),
('HS16', 'LY', 2, 1, 'giapthin', 3),
('HS16', 'LY', 2, 2, 'giapthin', 8),
('HS16', 'LY', 2, 3, 'giapthin', 6),
('HS16', 'TOAN', 1, 1, 'giapthin', 8),
('HS16', 'TOAN', 1, 2, 'giapthin', 9),
('HS16', 'TOAN', 1, 3, 'giapthin', 8),
('HS16', 'TOAN', 2, 1, 'giapthin', 5),
('HS16', 'TOAN', 2, 2, 'giapthin', 7),
('HS16', 'TOAN', 2, 3, 'giapthin', 5),
('HS17', 'ANH', 1, 1, 'giapthin', 8),
('HS17', 'ANH', 1, 2, 'giapthin', 9),
('HS17', 'ANH', 1, 3, 'giapthin', 8),
('HS17', 'ANH', 2, 1, 'giapthin', 9),
('HS17', 'ANH', 2, 2, 'giapthin', 7),
('HS17', 'ANH', 2, 3, 'giapthin', 9),
('HS17', 'HOA', 1, 1, 'giapthin', 7),
('HS17', 'HOA', 1, 2, 'giapthin', 9),
('HS17', 'HOA', 1, 3, 'giapthin', 7),
('HS17', 'HOA', 2, 1, 'giapthin', 9),
('HS17', 'HOA', 2, 2, 'giapthin', 7),
('HS17', 'HOA', 2, 3, 'giapthin', 6),
('HS17', 'LY', 1, 1, 'giapthin', 7),
('HS17', 'LY', 1, 2, 'giapthin', 6),
('HS17', 'LY', 1, 3, 'giapthin', 9),
('HS17', 'LY', 2, 1, 'giapthin', 9),
('HS17', 'LY', 2, 2, 'giapthin', 7),
('HS17', 'LY', 2, 3, 'giapthin', 9),
('HS17', 'TOAN', 1, 1, 'giapthin', 8),
('HS17', 'TOAN', 1, 2, 'giapthin', 7),
('HS17', 'TOAN', 1, 3, 'giapthin', 9),
('HS17', 'TOAN', 2, 1, 'giapthin', 9),
('HS17', 'TOAN', 2, 2, 'giapthin', 6),
('HS17', 'TOAN', 2, 3, 'giapthin', 8),
('HS18', 'ANH', 1, 1, 'giapthin', 9),
('HS18', 'ANH', 1, 2, 'giapthin', 8),
('HS18', 'ANH', 1, 3, 'giapthin', 9),
('HS18', 'ANH', 2, 1, 'giapthin', 6),
('HS18', 'ANH', 2, 2, 'giapthin', 8),
('HS18', 'ANH', 2, 3, 'giapthin', 8),
('HS18', 'HOA', 1, 1, 'giapthin', 8),
('HS18', 'HOA', 1, 2, 'giapthin', 8),
('HS18', 'HOA', 1, 3, 'giapthin', 9),
('HS18', 'HOA', 2, 1, 'giapthin', 6),
('HS18', 'HOA', 2, 2, 'giapthin', 7),
('HS18', 'HOA', 2, 3, 'giapthin', 8),
('HS18', 'LY', 1, 1, 'giapthin', 9),
('HS18', 'LY', 1, 2, 'giapthin', 8),
('HS18', 'LY', 1, 3, 'giapthin', 9),
('HS18', 'LY', 2, 1, 'giapthin', 6),
('HS18', 'LY', 2, 2, 'giapthin', 5),
('HS18', 'LY', 2, 3, 'giapthin', 8),
('HS18', 'TOAN', 1, 1, 'giapthin', 8),
('HS18', 'TOAN', 1, 2, 'giapthin', 9),
('HS18', 'TOAN', 1, 3, 'giapthin', 8),
('HS18', 'TOAN', 2, 1, 'giapthin', 8),
('HS18', 'TOAN', 2, 2, 'giapthin', 9),
('HS18', 'TOAN', 2, 3, 'giapthin', 7),
('HS19', 'ANH', 1, 1, 'giapthin', 7),
('HS19', 'ANH', 1, 2, 'giapthin', 7),
('HS19', 'ANH', 1, 3, 'giapthin', 7),
('HS19', 'ANH', 2, 1, 'giapthin', 7),
('HS19', 'ANH', 2, 2, 'giapthin', 7),
('HS19', 'ANH', 2, 3, 'giapthin', 7),
('HS19', 'HOA', 1, 1, 'giapthin', 7),
('HS19', 'HOA', 1, 2, 'giapthin', 7),
('HS19', 'HOA', 1, 3, 'giapthin', 7),
('HS19', 'HOA', 2, 1, 'giapthin', 7),
('HS19', 'HOA', 2, 2, 'giapthin', 7),
('HS19', 'HOA', 2, 3, 'giapthin', 7),
('HS19', 'LY', 1, 1, 'giapthin', 7),
('HS19', 'LY', 1, 2, 'giapthin', 7),
('HS19', 'LY', 1, 3, 'giapthin', 7),
('HS19', 'LY', 2, 1, 'giapthin', 7),
('HS19', 'LY', 2, 2, 'giapthin', 7),
('HS19', 'LY', 2, 3, 'giapthin', 7),
('HS19', 'TOAN', 1, 1, 'giapthin', 7),
('HS19', 'TOAN', 1, 2, 'giapthin', 7),
('HS19', 'TOAN', 1, 3, 'giapthin', 7),
('HS19', 'TOAN', 2, 1, 'giapthin', 7),
('HS19', 'TOAN', 2, 2, 'giapthin', 7),
('HS19', 'TOAN', 2, 3, 'giapthin', 7),
('HS2', 'ANH', 1, 1, 'giapthin', 8),
('HS2', 'ANH', 1, 2, 'giapthin', 9),
('HS2', 'ANH', 1, 3, 'giapthin', 8),
('HS2', 'ANH', 2, 1, 'giapthin', 5),
('HS2', 'ANH', 2, 2, 'giapthin', 8),
('HS2', 'ANH', 2, 3, 'giapthin', 9),
('HS2', 'HOA', 1, 1, 'giapthin', 8),
('HS2', 'HOA', 1, 2, 'giapthin', 7),
('HS2', 'HOA', 1, 3, 'giapthin', 9),
('HS2', 'HOA', 2, 1, 'giapthin', 8),
('HS2', 'HOA', 2, 2, 'giapthin', 7),
('HS2', 'HOA', 2, 3, 'giapthin', 6),
('HS2', 'LY', 1, 1, 'giapthin', 10),
('HS2', 'LY', 1, 2, 'giapthin', 9),
('HS2', 'LY', 1, 3, 'giapthin', 9),
('HS2', 'LY', 2, 1, 'giapthin', 5),
('HS2', 'LY', 2, 2, 'giapthin', 4),
('HS2', 'LY', 2, 3, 'giapthin', 10),
('HS2', 'TOAN', 1, 1, 'giapthin', 8),
('HS2', 'TOAN', 1, 2, 'giapthin', 9),
('HS2', 'TOAN', 1, 3, 'giapthin', 9),
('HS2', 'TOAN', 2, 1, 'giapthin', 9),
('HS2', 'TOAN', 2, 2, 'giapthin', 10),
('HS2', 'TOAN', 2, 3, 'giapthin', 6),
('HS20', 'ANH', 1, 1, 'giapthin', 8),
('HS20', 'ANH', 1, 2, 'giapthin', 8),
('HS20', 'ANH', 1, 3, 'giapthin', 8),
('HS20', 'ANH', 2, 1, 'giapthin', 8),
('HS20', 'ANH', 2, 2, 'giapthin', 8),
('HS20', 'ANH', 2, 3, 'giapthin', 8),
('HS20', 'HOA', 1, 1, 'giapthin', 8),
('HS20', 'HOA', 1, 2, 'giapthin', 8),
('HS20', 'HOA', 1, 3, 'giapthin', 8),
('HS20', 'HOA', 2, 1, 'giapthin', 8),
('HS20', 'HOA', 2, 2, 'giapthin', 8),
('HS20', 'HOA', 2, 3, 'giapthin', 8),
('HS20', 'LY', 1, 1, 'giapthin', 8),
('HS20', 'LY', 1, 2, 'giapthin', 8),
('HS20', 'LY', 1, 3, 'giapthin', 8),
('HS20', 'LY', 2, 1, 'giapthin', 8),
('HS20', 'LY', 2, 2, 'giapthin', 8),
('HS20', 'LY', 2, 3, 'giapthin', 8),
('HS20', 'TOAN', 1, 1, 'giapthin', 8),
('HS20', 'TOAN', 1, 2, 'giapthin', 8),
('HS20', 'TOAN', 1, 3, 'giapthin', 8),
('HS20', 'TOAN', 2, 1, 'giapthin', 8),
('HS20', 'TOAN', 2, 2, 'giapthin', 8),
('HS20', 'TOAN', 2, 3, 'giapthin', 8),
('HS3', 'ANH', 1, 1, 'giapthin', 9),
('HS3', 'ANH', 1, 2, 'giapthin', 8),
('HS3', 'ANH', 1, 3, 'giapthin', 9),
('HS3', 'ANH', 2, 1, 'giapthin', 5),
('HS3', 'ANH', 2, 2, 'giapthin', 6),
('HS3', 'ANH', 2, 3, 'giapthin', 5),
('HS3', 'HOA', 1, 1, 'giapthin', 9),
('HS3', 'HOA', 1, 2, 'giapthin', 7),
('HS3', 'HOA', 1, 3, 'giapthin', 9),
('HS3', 'HOA', 2, 1, 'giapthin', 6),
('HS3', 'HOA', 2, 2, 'giapthin', 8),
('HS3', 'HOA', 2, 3, 'giapthin', 9),
('HS3', 'LY', 1, 1, 'giapthin', 9),
('HS3', 'LY', 1, 2, 'giapthin', 8),
('HS3', 'LY', 1, 3, 'giapthin', 6),
('HS3', 'LY', 2, 1, 'giapthin', 10),
('HS3', 'LY', 2, 2, 'giapthin', 10),
('HS3', 'LY', 2, 3, 'giapthin', 10),
('HS3', 'TOAN', 1, 1, 'giapthin', 7),
('HS3', 'TOAN', 1, 2, 'giapthin', 8),
('HS3', 'TOAN', 1, 3, 'giapthin', 9),
('HS3', 'TOAN', 2, 1, 'giapthin', 10),
('HS3', 'TOAN', 2, 2, 'giapthin', 8),
('HS3', 'TOAN', 2, 3, 'giapthin', 7),
('HS4', 'ANH', 1, 1, 'giapthin', 10),
('HS4', 'ANH', 1, 2, 'giapthin', 9),
('HS4', 'ANH', 1, 3, 'giapthin', 9),
('HS4', 'ANH', 2, 1, 'giapthin', 10),
('HS4', 'ANH', 2, 2, 'giapthin', 8),
('HS4', 'ANH', 2, 3, 'giapthin', 9),
('HS4', 'HOA', 1, 1, 'giapthin', 6),
('HS4', 'HOA', 1, 2, 'giapthin', 8),
('HS4', 'HOA', 1, 3, 'giapthin', 8),
('HS4', 'HOA', 2, 1, 'giapthin', 10),
('HS4', 'HOA', 2, 2, 'giapthin', 9),
('HS4', 'HOA', 2, 3, 'giapthin', 8),
('HS4', 'LY', 1, 1, 'giapthin', 6),
('HS4', 'LY', 1, 2, 'giapthin', 3),
('HS4', 'LY', 1, 3, 'giapthin', 10),
('HS4', 'LY', 2, 1, 'giapthin', 9),
('HS4', 'LY', 2, 2, 'giapthin', 8),
('HS4', 'LY', 2, 3, 'giapthin', 9),
('HS4', 'TOAN', 1, 1, 'giapthin', 8),
('HS4', 'TOAN', 1, 2, 'giapthin', 10),
('HS4', 'TOAN', 1, 3, 'giapthin', 9),
('HS4', 'TOAN', 2, 1, 'giapthin', 9),
('HS4', 'TOAN', 2, 2, 'giapthin', 6),
('HS4', 'TOAN', 2, 3, 'giapthin', 10),
('HS5', 'ANH', 1, 1, 'giapthin', 6),
('HS5', 'ANH', 1, 2, 'giapthin', 8),
('HS5', 'ANH', 1, 3, 'giapthin', 8),
('HS5', 'ANH', 2, 1, 'giapthin', 9),
('HS5', 'ANH', 2, 2, 'giapthin', 9),
('HS5', 'ANH', 2, 3, 'giapthin', 9),
('HS5', 'HOA', 1, 1, 'giapthin', 7),
('HS5', 'HOA', 1, 2, 'giapthin', 5),
('HS5', 'HOA', 1, 3, 'giapthin', 5),
('HS5', 'HOA', 2, 1, 'giapthin', 9),
('HS5', 'HOA', 2, 2, 'giapthin', 9),
('HS5', 'HOA', 2, 3, 'giapthin', 9),
('HS5', 'LY', 1, 1, 'giapthin', 9),
('HS5', 'LY', 1, 2, 'giapthin', 8),
('HS5', 'LY', 1, 3, 'giapthin', 6),
('HS5', 'LY', 2, 1, 'giapthin', 9),
('HS5', 'LY', 2, 2, 'giapthin', 9),
('HS5', 'LY', 2, 3, 'giapthin', 9),
('HS5', 'TOAN', 1, 1, 'giapthin', 9),
('HS5', 'TOAN', 1, 2, 'giapthin', 8),
('HS5', 'TOAN', 1, 3, 'giapthin', 10),
('HS5', 'TOAN', 2, 1, 'giapthin', 9),
('HS5', 'TOAN', 2, 2, 'giapthin', 9),
('HS5', 'TOAN', 2, 3, 'giapthin', 9),
('HS6', 'ANH', 1, 1, 'giapthin', 8),
('HS6', 'ANH', 1, 2, 'giapthin', 8),
('HS6', 'ANH', 1, 3, 'giapthin', 8),
('HS6', 'ANH', 2, 1, 'giapthin', 7),
('HS6', 'ANH', 2, 2, 'giapthin', 7),
('HS6', 'ANH', 2, 3, 'giapthin', 7),
('HS6', 'HOA', 1, 1, 'giapthin', 8),
('HS6', 'HOA', 1, 2, 'giapthin', 8),
('HS6', 'HOA', 1, 3, 'giapthin', 8),
('HS6', 'HOA', 2, 1, 'giapthin', 7),
('HS6', 'HOA', 2, 2, 'giapthin', 7),
('HS6', 'HOA', 2, 3, 'giapthin', 7),
('HS6', 'LY', 1, 1, 'giapthin', 8),
('HS6', 'LY', 1, 2, 'giapthin', 8),
('HS6', 'LY', 1, 3, 'giapthin', 8),
('HS6', 'LY', 2, 1, 'giapthin', 7),
('HS6', 'LY', 2, 2, 'giapthin', 7),
('HS6', 'LY', 2, 3, 'giapthin', 7),
('HS6', 'TOAN', 1, 1, 'giapthin', 8),
('HS6', 'TOAN', 1, 2, 'giapthin', 8),
('HS6', 'TOAN', 1, 3, 'giapthin', 8),
('HS6', 'TOAN', 2, 1, 'giapthin', 7),
('HS6', 'TOAN', 2, 2, 'giapthin', 7),
('HS6', 'TOAN', 2, 3, 'giapthin', 7),
('HS7', 'ANH', 1, 1, 'giapthin', 6),
('HS7', 'ANH', 1, 2, 'giapthin', 6),
('HS7', 'ANH', 1, 3, 'giapthin', 6),
('HS7', 'ANH', 2, 1, 'giapthin', 8),
('HS7', 'ANH', 2, 2, 'giapthin', 8),
('HS7', 'ANH', 2, 3, 'giapthin', 8),
('HS7', 'HOA', 1, 1, 'giapthin', 6),
('HS7', 'HOA', 1, 2, 'giapthin', 6),
('HS7', 'HOA', 1, 3, 'giapthin', 6),
('HS7', 'HOA', 2, 1, 'giapthin', 8),
('HS7', 'HOA', 2, 2, 'giapthin', 8),
('HS7', 'HOA', 2, 3, 'giapthin', 8),
('HS7', 'LY', 1, 1, 'giapthin', 6),
('HS7', 'LY', 1, 2, 'giapthin', 6),
('HS7', 'LY', 1, 3, 'giapthin', 6),
('HS7', 'LY', 2, 1, 'giapthin', 8),
('HS7', 'LY', 2, 2, 'giapthin', 8),
('HS7', 'LY', 2, 3, 'giapthin', 8),
('HS7', 'TOAN', 1, 1, 'giapthin', 6),
('HS7', 'TOAN', 1, 2, 'giapthin', 6),
('HS7', 'TOAN', 1, 3, 'giapthin', 6),
('HS7', 'TOAN', 2, 1, 'giapthin', 8),
('HS7', 'TOAN', 2, 2, 'giapthin', 8),
('HS7', 'TOAN', 2, 3, 'giapthin', 8),
('HS8', 'ANH', 1, 1, 'giapthin', 10),
('HS8', 'ANH', 1, 2, 'giapthin', 10),
('HS8', 'ANH', 1, 3, 'giapthin', 10),
('HS8', 'ANH', 2, 1, 'giapthin', 10),
('HS8', 'ANH', 2, 2, 'giapthin', 10),
('HS8', 'ANH', 2, 3, 'giapthin', 10),
('HS8', 'HOA', 1, 1, 'giapthin', 10),
('HS8', 'HOA', 1, 2, 'giapthin', 10),
('HS8', 'HOA', 1, 3, 'giapthin', 10),
('HS8', 'HOA', 2, 1, 'giapthin', 10),
('HS8', 'HOA', 2, 2, 'giapthin', 10),
('HS8', 'HOA', 2, 3, 'giapthin', 10),
('HS8', 'LY', 1, 1, 'giapthin', 10),
('HS8', 'LY', 1, 2, 'giapthin', 10),
('HS8', 'LY', 1, 3, 'giapthin', 10),
('HS8', 'LY', 2, 1, 'giapthin', 10),
('HS8', 'LY', 2, 2, 'giapthin', 10),
('HS8', 'LY', 2, 3, 'giapthin', 10),
('HS8', 'TOAN', 1, 1, 'giapthin', 10),
('HS8', 'TOAN', 1, 2, 'giapthin', 10),
('HS8', 'TOAN', 1, 3, 'giapthin', 10),
('HS8', 'TOAN', 2, 1, 'giapthin', 10),
('HS8', 'TOAN', 2, 2, 'giapthin', 10),
('HS8', 'TOAN', 2, 3, 'giapthin', 10),
('HS9', 'ANH', 1, 1, 'giapthin', 6),
('HS9', 'ANH', 1, 2, 'giapthin', 5),
('HS9', 'ANH', 1, 3, 'giapthin', 5),
('HS9', 'ANH', 2, 1, 'giapthin', 7),
('HS9', 'ANH', 2, 2, 'giapthin', 8),
('HS9', 'ANH', 2, 3, 'giapthin', 9),
('HS9', 'HOA', 1, 1, 'giapthin', 4),
('HS9', 'HOA', 1, 2, 'giapthin', 10),
('HS9', 'HOA', 1, 3, 'giapthin', 7),
('HS9', 'HOA', 2, 1, 'giapthin', 7),
('HS9', 'HOA', 2, 2, 'giapthin', 8),
('HS9', 'HOA', 2, 3, 'giapthin', 9),
('HS9', 'LY', 1, 1, 'giapthin', 7),
('HS9', 'LY', 1, 2, 'giapthin', 6),
('HS9', 'LY', 1, 3, 'giapthin', 5),
('HS9', 'LY', 2, 1, 'giapthin', 7),
('HS9', 'LY', 2, 2, 'giapthin', 8),
('HS9', 'LY', 2, 3, 'giapthin', 9),
('HS9', 'TOAN', 1, 1, 'giapthin', 5),
('HS9', 'TOAN', 1, 2, 'giapthin', 8),
('HS9', 'TOAN', 1, 3, 'giapthin', 9),
('HS9', 'TOAN', 2, 1, 'giapthin', 7),
('HS9', 'TOAN', 2, 2, 'giapthin', 8),
('HS9', 'TOAN', 2, 3, 'giapthin', 9);

-- --------------------------------------------------------

--
-- Table structure for table `chitietquyen`
--

CREATE TABLE IF NOT EXISTS `chitietquyen` (
  `maquyen` varchar(50) NOT NULL,
  `machucnang` varchar(50) NOT NULL,
  `enable` varchar(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `chitietquyen`
--

INSERT INTO `chitietquyen` (`maquyen`, `machucnang`, `enable`) VALUES
('admin', 'CN1', '1'),
('admin', 'CN2', '1'),
('admin', 'CN3', '1'),
('admin', 'CN4', '1'),
('admin', 'CN5', '1'),
('admin', 'CN6', '1'),
('admin', 'CN7', '1'),
('admin', 'CN8', '1'),
('admin', 'CN9', '1'),
('admin', 'CN10', '1'),
('admin', 'CN11', '1'),
('GV', 'CN12', '1'),
('GV', 'CN13', '1'),
('GV', 'CN14', '1'),
('GV', 'CN15', '1'),
('GV', 'CN16', '1'),
('GV', 'CN17', '1'),
('HS', 'CN16', '1'),
('HS', 'CN17', '1'),
('HS', 'CN18', '1'),
('HS', 'CN19', '1'),
('HS', 'CN20', '1'),
('admin', 'CN21', '1'),
('FFFFF', 'CN1', '0'),
('FFFFF', 'CN2', '0'),
('FFFFF', 'CN3', '0'),
('FFFFF', 'CN4', '0'),
('FFFFF', 'CN5', '0'),
('FFFFF', 'CN6', '0'),
('FFFFF', 'CN1', '1'),
('FFFFF', 'CN2', '1'),
('FFFFF', 'CN3', '1'),
('FFFFF', 'CN4', '1'),
('FFFFF', 'CN5', '1'),
('FFFFF', 'CN6', '1'),
('FFFFF', 'CN8', '1'),
('FFFFF', 'CN10', '1'),
('FFFFF', 'CN11', '1');

-- --------------------------------------------------------

--
-- Table structure for table `chucnang`
--

CREATE TABLE IF NOT EXISTS `chucnang` (
  `machucnang` varchar(50) NOT NULL,
  `tenchucnang` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `chucnang`
--

INSERT INTO `chucnang` (`machucnang`, `tenchucnang`) VALUES
('CN1', 'QL Học Sinh'),
('CN2', 'QL Giáo Viên'),
('CN3', 'QL Môn Học'),
('CN4', 'QL Năm Học'),
('CN5', 'QL Phân Công'),
('CN6', 'QL Tài Khoản'),
('CN9', 'Thanh Toán HP'),
('CN8', 'Xem Ý Kiến'),
('CN7', 'QL Điểm'),
('CN10', 'Thống Kê'),
('CN11', 'Thông Báo HS/GV'),
('CN12', 'Danh sách HS'),
('CN13', 'Nhập Điểm'),
('CN14', 'Thông Tin GV'),
('CN15', 'Gửi Thông Báo'),
('CN16', 'Nhận Thông Báo'),
('CN17', 'Đổi mật khấu'),
('CN18', 'Xem Điểm'),
('CN19', 'Góp Ý Kiến'),
('CN20', 'Thông Tin HS'),
('CN21', 'Phân Quyền'),
('CN22', 'HS NHậnTB');

-- --------------------------------------------------------

--
-- Table structure for table `diemtbhocky`
--

CREATE TABLE IF NOT EXISTS `diemtbhocky` (
  `HocSinhid` varchar(10) NOT NULL,
  `HocKyid` int(11) NOT NULL,
  `NamHocid` varchar(20) NOT NULL,
  `DiemTrungBinh` float DEFAULT NULL,
  PRIMARY KEY (`HocSinhid`,`HocKyid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `diemtbhocky`
--

INSERT INTO `diemtbhocky` (`HocSinhid`, `HocKyid`, `NamHocid`, `DiemTrungBinh`) VALUES
('HS1', 1, 'giapthin', 7.83),
('HS1', 2, 'giapthin', 7.95),
('HS10', 1, 'giapthin', 7.67),
('HS10', 2, 'giapthin', 8),
('HS11', 1, 'giapthin', 7.1),
('HS11', 2, 'giapthin', 7),
('HS12', 1, 'giapthin', 8.46),
('HS12', 2, 'giapthin', 9.95),
('HS13', 1, 'giapthin', 8.1),
('HS13', 2, 'giapthin', 7.5),
('HS14', 1, 'giapthin', 8.52),
('HS14', 2, 'giapthin', 7.83),
('HS15', 1, 'giapthin', 8.8),
('HS15', 2, 'giapthin', 6.95),
('HS16', 1, 'giapthin', 7.5),
('HS16', 2, 'giapthin', 6.2),
('HS17', 1, 'giapthin', 7.96),
('HS17', 2, 'giapthin', 7.75),
('HS18', 1, 'giapthin', 8.54),
('HS18', 2, 'giapthin', 7.375),
('HS19', 1, 'giapthin', 7),
('HS19', 2, 'giapthin', 7),
('HS2', 1, 'giapthin', 8.625),
('HS2', 2, 'giapthin', 7.42),
('HS20', 1, 'giapthin', 8),
('HS20', 2, 'giapthin', 8),
('HS3', 1, 'giapthin', 8.125),
('HS3', 2, 'giapthin', 7.83),
('HS4', 1, 'giapthin', 8.25),
('HS4', 2, 'giapthin', 8.67),
('HS5', 1, 'giapthin', 7.33),
('HS5', 2, 'giapthin', 9),
('HS6', 1, 'giapthin', 8),
('HS6', 2, 'giapthin', 7),
('HS7', 1, 'giapthin', 6),
('HS7', 2, 'giapthin', 8),
('HS8', 1, 'giapthin', 10),
('HS8', 2, 'giapthin', 10),
('HS9', 1, 'giapthin', 6.58),
('HS9', 2, 'giapthin', 8.3);

-- --------------------------------------------------------

--
-- Table structure for table `giaovien`
--

CREATE TABLE IF NOT EXISTS `giaovien` (
  `GiaoVienid` varchar(5) NOT NULL DEFAULT 'GV',
  `TenGiaoVien` varchar(50) NOT NULL,
  `GioiTinh` varchar(5) NOT NULL,
  `NamSinh` varchar(10) NOT NULL,
  `DiaChi` varchar(50) NOT NULL,
  `DienThoai` varchar(11) NOT NULL DEFAULT '',
  `IMG` text DEFAULT NULL,
  `enable` tinyint(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`GiaoVienid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `giaovien`
--

INSERT INTO `giaovien` (`GiaoVienid`, `TenGiaoVien`, `GioiTinh`, `NamSinh`, `DiaChi`, `DienThoai`, `IMG`, `enable`) VALUES
('GV1', 'Nguyễn Quốc Liêm', 'nam', '28/07/1997', '455/40 An Dương Vương', '0982134576', '001.jpg', 1),
('GV2', 'Phạm Thị Loan', 'nữ', '13/12/1989', 'Phường 2, Gò Vấp', '0831054319', '002.jpg', 1),
('GV3', 'Huỳnh Văn Hiền', 'nam', '11/06/1985', 'Trảng Bàng', '0957461238', '003.jpg', 1),
('GV4', 'Nguyen Dinh Hung', 'Nam', '11/06/1985', 'Trảng Bàng', '0957461238', '003.jpg', 1);

-- --------------------------------------------------------

--
-- Table structure for table `hocky`
--

CREATE TABLE IF NOT EXISTS `hocky` (
  `HocKyid` int(11) NOT NULL,
  `TenHocKy` varchar(50) NOT NULL,
  PRIMARY KEY (`HocKyid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `hocky`
--

INSERT INTO `hocky` (`HocKyid`, `TenHocKy`) VALUES
(1, 'Học Kỳ 1'),
(2, 'Học Kỳ 2');

-- --------------------------------------------------------

--
-- Table structure for table `hocphi`
--

CREATE TABLE IF NOT EXISTS `hocphi` (
  `idhs` varchar(10) NOT NULL,
  `idnh` varchar(22) NOT NULL,
  `thoigian` varchar(50) NOT NULL,
  `status` int(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `hocphi`
--

INSERT INTO `hocphi` (`idhs`, `idnh`, `thoigian`, `status`) VALUES
('HS21', 'giapthin', '21-5-2024 09:41:41', 1),
('HS11', 'giapthin', '21-5-2024 12:05:32', 1),
('HS1', 'giapthin', '\r\n21-5-2024 12:05:32', 1),
('HS10', 'giapthin', '\r\n22-5-2024 11:07:44', 1);

-- --------------------------------------------------------

--
-- Table structure for table `hocsinh`
--

CREATE TABLE IF NOT EXISTS `hocsinh` (
  `HocSinhid` varchar(10) NOT NULL DEFAULT 'HS',
  `HoVaTen` varchar(50) NOT NULL,
  `GioiTinh` varchar(50) NOT NULL,
  `NgaySinh` varchar(50) NOT NULL,
  `DienThoai` varchar(11) DEFAULT '0',
  `DiaChi` text NOT NULL,
  `HocPhi` varchar(20) NOT NULL DEFAULT 'Chưa thanh toán',
  `IMG` text DEFAULT NULL,
  `enable` tinyint(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`HocSinhid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `hocsinh`
--

INSERT INTO `hocsinh` (`HocSinhid`, `HoVaTen`, `GioiTinh`, `NgaySinh`, `DienThoai`, `DiaChi`, `HocPhi`, `IMG`, `enable`) VALUES
('HS1', 'Trương Anh Đào', 'Nữ', '12/3/2006', '0987654321', '123/4 Lê Lợi, Phường 7, Quận 1, TP.HCM', 'Đã thanh toán', 'anhavt1.jpg', 1),
('HS10', 'Nguyễn Thị Ngọc Trinh', 'Nữ', '25/11/2007', '0987654321', '678/90 Nguyễn Tri Phương, Phường 11, Quận 5, TP.HCM', 'Đã thanh toán', 'anhavt2.jpg', 1),
('HS11', 'Lê Thị Ánh Tuyết', 'Nữ', '7/8/2007', '0965432198', '345/67 Lê Lợi, Phường 12, Quận 10, TP.HCM', 'Đã thanh toán', 'anhavt3.jpg', 1),
('HS12', 'Trần Văn Minh', 'Nam', '3/4/2007', '0912345678', '890/12 Nguyễn Văn Cừ, Phường 9, Quận Bình Thạnh, TP.HCM', 'Chưa thanh toán', 'anhavt4.jpg', 1),
('HS13', ' Phạm Văn Hưng', 'Nam', '16/9/2007', '0909876543', '456/78 Phan Xích Long, Phường 6, Quận Phú Nhuận, TP.HCM', 'Chưa thanh toán', 'anhavt5.jpg', 1),
('HS14', 'Đỗ Thị Ngọc Lan', 'Nữ', '29/6/2007', '0987654321', '123/45 Đặng Thùy Trâm, Phường 8, Quận 4, TP.HCM', 'Chưa thanh toán', 'anhavt6.jpg', 1),
('HS15', 'Bùi Văn Hùng', 'Nam', '8/11/2007', '0976543210', ' 789/32 Hồng Hà, Phường 15, Quận 10, TP.HCM', 'Chưa thanh toán', 'anhavt7.jpg', 1),
('HS16', 'Nguyễn Thị Thu Hà', 'Nữ', '20/3/2007', '0932109876', '567/89 Lê Văn Sỹ, Phường 14, Quận 3, TP.HCM', 'Chưa thanh toán', 'anhavt9.jpg', 1),
('HS17', 'Trần Văn Đức', 'Nam', '12/12/2007', '0943216789', '345/67 Nguyễn Văn Linh, Phường Tân Thuận Tây, Quận 7, TP.HCM', 'Chưa thanh toán', 'anhavt8.jpg', 1),
('HS18', 'Lê Thị Mai Anh', 'Nam', '05/06/2008', '0909876543', '234/56 Võ Văn Tần, Phường 1, Quận 10, TP.HCM', 'Chưa thanh toán', '21613.jpg', 1),
('HS19', 'Nguyễn Văn Nam', 'Nam', '18/9/2008', '0976543210', '567/89 Phan Chu Trinh, Phường 2, Quận Bình Thạnh, TP.HCM', 'Chưa thanh toán', '', 1),
('HS2', 'Lê Văn Nam', 'Nam', '5/6/2006', '0901234567', '456/78 Điện Biên Phủ, Phường 5, Quận 3, TP.HCM', 'Chưa thanh toán', '', 1),
('HS20', 'Phạm Thị Hồng', 'Nữ', '20/7/2008', '0943216789', '678/90 Nguyễn Văn Quá, Phường Tân Hưng, Quận 8, TP.HCM', 'Chưa thanh toán', '', 1),
('HS21', 'Trần Văn Tuấn', 'Nam', '11/4/2008', '0932109876', '890/12 Bình Long, Phường 3, Quận Gò Vấp, TP.HCM', 'Đã thanh toán', '', 1),
('HS22', 'Lý Thị Minh Khai', 'Nữ', ' 25/10/2008', '0923456789', '345/67 Lê Hồng Phong, Phường 4, Quận Tân Bình, TP.HCM', 'Chưa thanh toán', '', 1),
('HS23', ' Nguyễn Văn Thắng', 'Nam', '8/11/2008', '0912345678', '456/78 Điện Biên Phủ, Phường 5, Quận 11, TP.HCM', 'Chưa thanh toán', '', 1),
('HS24', 'Phan Thị Ngọc Trâm', 'Nữ', '14/2/2008', '0987654321', '789/32 Lê Lợi, Phường 6, Quận 9, TP.HCM', 'Chưa thanh toán', '', 1),
('HS25', ' Hoàng Văn Hưng', 'Nam', '30/6/2008', '0954321098', '890/12 Phạm Ngọc Thạch, Phường 7, Quận 12, TP.HCM', 'Chưa thanh toán', '', 1),
('HS26', 'Đặng Văn Hoàng', 'Nam', '27/12/2008', '0965432198', '123/45 Trần Hưng Đạo, Phường 8, Quận 4, TP.HCM', 'Chưa thanh toán', '', 1),
('HS27', 'Trần Thị Hằng', 'Nữ', '3/5/2008', '0912345678', '456/78 Nguyễn Văn Quá, Phường 10, Quận Tân Bình, TP.HCM', 'Chưa thanh toán', '', 1),
('HS28', 'Huỳnh Gia Vĩ', 'Nam', '27/12/2008', '0965432198', '123/45 Trần Hưng Đạo, Phường 8, Quận 4, TP.HCM', 'Chưa thanh toán', '', 1),
('HS29', 'Lê Thị Ánh Vĩ', 'Nữ', '07/08/2007', 'Bình Định', '0965432198', 'Chưa thanh toán', 'anhavt3.jpg', 0),
('HS3', 'Trần Thị Mai', 'Nữ', '20/9/2006', '0978123456', '789/10 Nguyễn Huệ, Phường 2, Quận 10, TP.HCM', 'Chưa thanh toán', '', 1),
('HS30', 'Huỳnh Vĩ', 'Nam', '14/02/2008', '0987654321', '789/32 Lê Lợi, Phường 6, Quận 9, TP.HCM', 'Chưa thanh toán', '', 0),
('HS4', 'Hoàng Văn Tuấn', 'Nam', '15/12/2006', '0865432198', '321/54 Trần Hưng Đạo, Phường 9, Quận 5, TP.HCM', 'Chưa thanh toán', '', 1),
('HS5', 'Nguyễn Thị Lan', 'Nữ', '8/4/2006', '0912345678', '987/65 Đường số 10, Phường 8, Quận 7, TP.HCM', 'Chưa thanh toán', '', 1),
('HS6', 'Lý Văn Hưng', 'Nam', '23/10/2006', '0854321098', '456/32 Lý Thường Kiệt, Phường 6, Quận 11, TP.HCM', 'Chưa thanh toán', '', 1),
('HS7', 'Trần Văn Hòa', 'Nam', '17/7/2006', '0932109876', '789/32 Nguyễn Thị Minh Khai, Phường 3, Quận 1, TP.HCM,', 'Chưa thanh toán', '', 1),
('HS8', ' Phan Thị Hằng', 'Nữ', '30/5/2006', '0945671234', '567/89 Trần Não, Phường Bình An, Quận 2, TP.HCM', 'Chưa thanh toán', '', 1),
('HS9', 'Võ Văn Đức', 'Nam', '10/02/2007', '0978965432', '234/56 Cách Mạng Tháng 8, Phường 10, Quận 3, TP.HCM', 'Chưa thanh toán', '', 1);

-- --------------------------------------------------------

--
-- Table structure for table `kqhocsinhcanam`
--

CREATE TABLE IF NOT EXISTS `kqhocsinhcanam` (
  `HocSinhid` varchar(10) NOT NULL,
  `NamHocid` varchar(50) NOT NULL,
  `HocLuc` varchar(50) DEFAULT NULL,
  `HanhKiem` varchar(10) DEFAULT 'Tốt',
  `Diemtb` float DEFAULT NULL,
  `KetQua` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`HocSinhid`,`NamHocid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `kqhocsinhcanam`
--

INSERT INTO `kqhocsinhcanam` (`HocSinhid`, `NamHocid`, `HocLuc`, `HanhKiem`, `Diemtb`, `KetQua`) VALUES
('HS1', 'giapthin', 'Khá', 'Tốt', 7.91, 'Lên Lớp'),
('HS10', 'giapthin', 'Khá', 'Tốt', 7.89, 'Lên Lớp'),
('HS11', 'giapthin', 'Khá', 'Tốt', 7, 'Lên Lớp'),
('HS12', 'giapthin', 'Khá', 'Tốt', 9.45, 'Lên Lớp'),
('HS13', 'giapthin', 'Khá', 'Tốt', 7.7, 'Lên Lớp'),
('HS14', 'giapthin', 'Giỏi', 'Tốt', 8.06, 'Lên Lớp'),
('HS15', 'giapthin', 'Khá', 'Tốt', 7.5, 'Lên Lớp'),
('HS16', 'giapthin', 'Khá', 'Tốt', 6.6, 'Lên Lớp'),
('HS17', 'giapthin', 'Khá', 'Tốt', 7.82, 'Lên Lớp'),
('HS18', 'giapthin', 'Khá', 'Tốt', 7.76, 'Lên Lớp'),
('HS19', 'giapthin', 'Khá', 'Tốt', 7, 'Lên Lớp'),
('HS2', 'giapthin', 'Khá', 'Tốt', 7.8, 'Lên Lớp'),
('HS20', 'giapthin', 'Giỏi', 'Tốt', 8, 'Lên Lớp'),
('HS3', 'giapthin', 'Khá', 'Tốt', 7.9, 'Lên Lớp'),
('HS4', 'giapthin', 'Giỏi', 'Tốt', 8.53, 'Lên Lớp'),
('HS5', 'giapthin', 'Giỏi', 'Tốt', 8.4, 'Lên Lớp'),
('HS6', 'giapthin', 'Khá', 'Tốt', 7.3, 'Lên Lớp'),
('HS7', 'giapthin', 'Khá', 'Tốt', 7.3, 'Lên Lớp'),
('HS8', 'giapthin', 'Giỏi', 'Tốt', 10, 'Lên Lớp'),
('HS9', 'giapthin', 'Khá', 'Tốt', 7.72, 'Lên Lớp');

-- --------------------------------------------------------

--
-- Table structure for table `lop`
--

CREATE TABLE IF NOT EXISTS `lop` (
  `Lopid` int(11) NOT NULL,
  `TenLop` varchar(50) NOT NULL,
  PRIMARY KEY (`Lopid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `lop`
--

INSERT INTO `lop` (`Lopid`, `TenLop`) VALUES
(10, '10A1'),
(11, '11A1'),
(12, '12A1');

-- --------------------------------------------------------

--
-- Table structure for table `monhoc`
--

CREATE TABLE IF NOT EXISTS `monhoc` (
  `MonHocid` varchar(11) NOT NULL,
  `TenMonHoc` varchar(50) NOT NULL,
  PRIMARY KEY (`MonHocid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `monhoc`
--

INSERT INTO `monhoc` (`MonHocid`, `TenMonHoc`) VALUES
('ANH', 'Anh Văn'),
('HOA', 'Hóa Học'),
('LY', 'Vật Lý'),
('TOAN', 'Toán');

-- --------------------------------------------------------

--
-- Table structure for table `namhoc`
--

CREATE TABLE IF NOT EXISTS `namhoc` (
  `NamHocid` varchar(50) NOT NULL,
  `NamBatDau` int(50) NOT NULL,
  `NamKetThuc` int(50) NOT NULL,
  PRIMARY KEY (`NamHocid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `namhoc`
--

INSERT INTO `namhoc` (`NamHocid`, `NamBatDau`, `NamKetThuc`) VALUES
('giapthin', 2024, 2025);

-- --------------------------------------------------------

--
-- Table structure for table `phancong`
--

CREATE TABLE IF NOT EXISTS `phancong` (
  `GiaoVienid` varchar(5) NOT NULL DEFAULT 'GV',
  `Lopid` int(11) NOT NULL,
  `MonHocid` varchar(11) NOT NULL,
  `enable` int(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`GiaoVienid`,`Lopid`,`MonHocid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `phancong`
--

INSERT INTO `phancong` (`GiaoVienid`, `Lopid`, `MonHocid`, `enable`) VALUES
('GV1', 10, 'TOAN', 1),
('GV1', 11, 'TOAN', 1),
('GV1', 12, 'TOAN', 1),
('GV2', 10, 'LY', 1),
('GV2', 11, 'LY', 1),
('GV2', 12, 'LY', 1),
('GV3', 10, 'HOA', 1),
('GV3', 11, 'HOA', 1),
('GV3', 12, 'HOA', 1),
('GV4', 10, 'ANH', 1),
('GV4', 11, 'ANH', 1),
('GV4', 12, 'ANH', 1);

-- --------------------------------------------------------

--
-- Table structure for table `phanlop`
--

CREATE TABLE IF NOT EXISTS `phanlop` (
  `HocSinhid` varchar(10) NOT NULL,
  `Lopid` int(11) NOT NULL,
  `NamHocid` varchar(20) NOT NULL,
  PRIMARY KEY (`HocSinhid`,`Lopid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `phanlop`
--

INSERT INTO `phanlop` (`HocSinhid`, `Lopid`, `NamHocid`) VALUES
('HS1', 12, 'giapthin'),
('HS10', 11, 'giapthin'),
('HS11', 11, 'giapthin'),
('HS12', 11, 'giapthin'),
('HS13', 11, 'giapthin'),
('HS14', 11, 'giapthin'),
('HS15', 11, 'giapthin'),
('HS16', 11, 'giapthin'),
('HS17', 11, 'giapthin'),
('HS18', 10, 'giapthin'),
('HS19', 10, 'giapthin'),
('HS2', 12, 'giapthin'),
('HS20', 10, 'giapthin'),
('HS21', 10, 'giapthin'),
('HS22', 10, 'giapthin'),
('HS23', 10, 'giapthin'),
('HS24', 10, 'giapthin'),
('HS25', 10, 'giapthin'),
('HS26', 10, 'giapthin'),
('HS27', 10, 'giapthin'),
('HS3', 12, 'giapthin'),
('HS4', 12, 'giapthin'),
('HS5', 12, 'giapthin'),
('HS6', 12, 'giapthin'),
('HS7', 12, 'giapthin'),
('HS8', 12, 'giapthin'),
('HS9', 11, 'giapthin');

-- --------------------------------------------------------

--
-- Table structure for table `quyen`
--

CREATE TABLE IF NOT EXISTS `quyen` (
  `tenquyen` varchar(50) NOT NULL,
  `maquyen` varchar(50) NOT NULL,
  `enable` int(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `quyen`
--

INSERT INTO `quyen` (`tenquyen`, `maquyen`, `enable`) VALUES
('admin', 'admin', 1),
('Giáo Viên', 'GV', 1),
('Học Sinh', 'HS', 1);

-- --------------------------------------------------------

--
-- Table structure for table `thongbao`
--

CREATE TABLE IF NOT EXISTS `thongbao` (
  `idnguoigui` varchar(11) NOT NULL,
  `tieudetb` text DEFAULT NULL,
  `noidungtb` text DEFAULT NULL,
  `thoigiantb` varchar(50) DEFAULT NULL,
  `loaitb` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `thongbao`
--

INSERT INTO `thongbao` (`idnguoigui`, `tieudetb`, `noidungtb`, `thoigiantb`, `loaitb`) VALUES
('admin', 'thong bao den HS GV', 'noi dung thong bao ', '2024-05-21', 'HS'),
('admin', 'thong bao den HS GV', 'noi dung thong bao ', '2024-05-21', 'GV');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `role` varchar(50) NOT NULL,
  `enable` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`username`, `password`, `role`, `enable`) VALUES
('admin', 'admin', 'admin', 1),
('GV1', 'GV1@', 'GV', 1),
('GV2', 'GV2@', 'GV', 1),
('GV3', 'GV3@', 'GV', 1),
('GV4', 'GV4@', 'GV', 1),
('GV5', 'GV5@', 'GV', 1),
('GV6', 'GV6@', 'GV', 1),
('HS1', 'HS1@', 'HS', 1),
('HS2', 'HS2@', 'HS', 1),
('HS3', 'HS3@', 'HS', 1),
('HS4', 'HS4@', 'HS', 1),
('HS5', 'HS5@', 'HS', 1),
('HS6', 'HS6@', 'HS', 1),
('HS7', 'HS7@', 'HS', 1),
('HS8', 'HS8@', 'HS', 1),
('HS9', 'HS9@', 'HS', 1),
('HS10', 'HS10@', 'HS', 1),
('HS11', 'HS11@', 'HS', 1),
('HS12', 'HS12@', 'HS', 1),
('HS13', 'HS13@', 'HS', 1),
('HS14', 'HS14@', 'HS', 1),
('HS15', 'HS15@', 'HS', 1),
('HS16', 'HS16@', 'HS', 1),
('HS17', 'HS17@', 'HS', 1),
('HS18', 'HS18@', 'HS', 1),
('HS19', 'HS19@', 'HS', 1),
('HS20', 'HS20@', 'HS', 1),
('HS21', 'HS21@', 'HS', 1),
('HS22', 'HS22@', 'HS', 1),
('HS23', 'HS23@', 'HS', 1),
('HS24', 'HS24@', 'HS', 1),
('HS25', 'HS25@', 'HS', 1);

-- --------------------------------------------------------

--
-- Table structure for table `ykien`
--

CREATE TABLE IF NOT EXISTS `ykien` (
  `idnguoigui` varchar(10) NOT NULL,
  `tieudeyk` text DEFAULT NULL,
  `noidungyk` text DEFAULT NULL,
  `thoigianyk` varchar(50) DEFAULT NULL,
  `tenhs` varchar(50) DEFAULT NULL,
  `trangthai` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
