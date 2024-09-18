-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Sep 18, 2024 at 11:52 AM
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

-- --------------------------------------------------------

--
-- Table structure for table `lop`
--

CREATE TABLE IF NOT EXISTS `lop` (
  `Lopid` int(11) NOT NULL,
  `TenLop` varchar(50) NOT NULL,
  PRIMARY KEY (`Lopid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `monhoc`
--

CREATE TABLE IF NOT EXISTS `monhoc` (
  `MonHocid` varchar(11) NOT NULL,
  `TenMonHoc` varchar(50) NOT NULL,
  PRIMARY KEY (`MonHocid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

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
('', '03212225552', 'user', 1),
('', '0255555555', 'user', 1);

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
