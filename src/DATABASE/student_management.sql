-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 05, 2024 at 05:51 PM
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
  `NamHocid` varchar(20) NOT NULL DEFAULT '',
  `Diem1` double(10,2) NOT NULL DEFAULT 0.00,
  `Diem2` double(10,2) NOT NULL DEFAULT 0.00,
  `Diem3` double(10,2) NOT NULL DEFAULT 0.00,
  `dtbMon` double(10,2) NOT NULL DEFAULT 0.00,
  PRIMARY KEY (`HocSinhid`,`MonHocid`,`HocKyid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `chitietdiem`
--

INSERT INTO `chitietdiem` (`HocSinhid`, `MonHocid`, `HocKyid`, `NamHocid`, `Diem1`, `Diem2`, `Diem3`, `dtbMon`) VALUES
('HSK241', 'MH1', 1, '20242025', 0.00, 0.00, 0.00, 0.00),
('HSK241', 'MH2', 1, '20242025', 8.00, 6.00, 5.00, 5.83),
('HSK242', 'MH1', 1, '20242025', 0.00, 0.00, 0.00, 0.00),
('HSK242', 'MH2', 1, '20242025', 0.00, 0.00, 0.00, 0.00),
('HSK243', 'MH1', 1, '20242025', 0.00, 0.00, 0.00, 0.00),
('HSK243', 'MH2', 1, '20242025', 0.00, 0.00, 0.00, 0.00);

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
('DD', 'CN1', '0'),
('DD', 'CN2', '0'),
('DD', 'CN3', '0');

-- --------------------------------------------------------

--
-- Table structure for table `chucnang`
--

CREATE TABLE IF NOT EXISTS `chucnang` (
  `machucnang` varchar(50) NOT NULL,
  `tenchucnang` varchar(50) NOT NULL,
  `img` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `chucnang`
--

INSERT INTO `chucnang` (`machucnang`, `tenchucnang`, `img`) VALUES
('CN1', 'QL Học Sinh', ':Shop_20px.png:Shop_20px_active.png'),
('CN2', 'QL Giáo Viên', ':Shop_20px.png:Shop_20px_active.png'),
('CN3', 'QL Môn Học', ':Shop_20px.png:Shop_20px_active.png'),
('CN4', 'QL Năm Học', ':Shop_20px.png:Shop_20px_active.png'),
('CN5', 'QL Phân Công', ':Shop_20px.png:Shop_20px_active.png'),
('CN6', 'QL Tài Khoản', ':Shop_20px.png:Shop_20px_active.png'),
('CN9', 'Thanh Toán HP', ':Shop_20px.png:Shop_20px_active.png'),
('CN8', 'Xem Ý Kiến', ':Shop_20px.png:Shop_20px_active.png'),
('CN7', 'QL Điểm', ':Shop_20px.png:Shop_20px_active.png'),
('CN10', 'Thống Kê', ':Shop_20px.png:Shop_20px_active.png'),
('CN11', 'Thông Báo HS/GV', ':Shop_20px.png:Shop_20px_active.png'),
('CN12', 'Danh sách HS', ':Shop_20px.png:Shop_20px_active.png'),
('CN13', 'Nhập Điểm', ':Shop_20px.png:Shop_20px_active.png'),
('CN14', 'Thông Tin GV', ':Shop_20px.png:Shop_20px_active.png'),
('CN15', 'Gửi Thông Báo', ':Shop_20px.png:Shop_20px_active.png'),
('CN16', 'Nhận Thông Báo', ':Shop_20px.png:Shop_20px_active.png'),
('CN17', 'Đổi mật khấu', ':Shop_20px.png:Shop_20px_active.png'),
('CN18', 'Xem Điểm', ':Shop_20px.png:Shop_20px_active.png'),
('CN19', 'Góp Ý Kiến', ':Shop_20px.png:Shop_20px_active.png'),
('CN20', 'Thông Tin HS', ':Shop_20px.png:Shop_20px_active.png'),
('CN21', 'Phân Quyền', ':Shop_20px.png:Shop_20px_active.png'),
('CN22', 'HS NHậnTB', ':Shop_20px.png:Shop_20px_active.png');

-- --------------------------------------------------------

--
-- Table structure for table `diemtbhocky`
--

CREATE TABLE IF NOT EXISTS `diemtbhocky` (
  `HocSinhid` varchar(10) NOT NULL,
  `HocKyid` int(11) NOT NULL,
  `NamHocid` varchar(20) NOT NULL,
  `DiemTrungBinh` double DEFAULT NULL,
  PRIMARY KEY (`HocSinhid`,`HocKyid`,`NamHocid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `diemtbhocky`
--

INSERT INTO `diemtbhocky` (`HocSinhid`, `HocKyid`, `NamHocid`, `DiemTrungBinh`) VALUES
('HSK241', 1, '20242025', 0),
('HSK241', 2, '20242025', 0),
('HSK242', 1, '20242025', 0),
('HSK242', 2, '20242025', 0),
('HSK243', 1, '20242025', 0),
('HSK243', 2, '20242025', 0);

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
  `PhanMon` varchar(50) NOT NULL,
  `IMG` text DEFAULT NULL,
  `isSubmit` tinyint(1) NOT NULL DEFAULT 0,
  `enable` tinyint(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`GiaoVienid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `giaovien`
--

INSERT INTO `giaovien` (`GiaoVienid`, `TenGiaoVien`, `GioiTinh`, `NamSinh`, `DiaChi`, `DienThoai`, `PhanMon`, `IMG`, `isSubmit`, `enable`) VALUES
('GV1', 'GV test1 toán', 'Nam', '22/10/2024', 'ffefef', '0789654123', 'Toán', '', 0, 1);

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

--
-- Dumping data for table `hocsinh`
--

INSERT INTO `hocsinh` (`HocSinhid`, `HoVaTen`, `GioiTinh`, `NgaySinh`, `DienThoai`, `DiaChi`, `HocPhi`, `IMG`, `enable`) VALUES
('HSK241', 'HS Test 1', 'Nam', '25/10/2002', '0323656326', 'stntmjtj', 'Chưa thanh toán', '', 1),
('HSK242', 'd@', 'Nam', '10/10/1996', '0123456789', '@d', 'Chưa thanh toán', 'Screenshot 2024-10-01 120343.png', 1),
('HSK243', 'test 1', 'Nam', '13/10/2006', '03232312546', 'rwgwrg', 'Chưa thanh toán', 'Screenshot 2024-10-01 120343.png', 1);

-- --------------------------------------------------------

--
-- Table structure for table `kqhocsinhcanam`
--

CREATE TABLE IF NOT EXISTS `kqhocsinhcanam` (
  `HocSinhid` varchar(10) NOT NULL,
  `NamHocid` varchar(50) NOT NULL,
  `HocLuc` varchar(50) DEFAULT NULL,
  `HanhKiem` varchar(10) DEFAULT 'Tốt',
  `Diemtb` double DEFAULT NULL,
  `KetQua` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`HocSinhid`,`NamHocid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `kqhocsinhcanam`
--

INSERT INTO `kqhocsinhcanam` (`HocSinhid`, `NamHocid`, `HocLuc`, `HanhKiem`, `Diemtb`, `KetQua`) VALUES
('HSK241', '20242025', '', 'Tốt', 0, ''),
('HSK242', '20242025', '', 'Tốt', 0, ''),
('HSK243', '20242025', '', 'Tốt', 0, '');

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
(101, '10A1'),
(102, '10A2'),
(111, '11A1'),
(112, '11A2'),
(121, '12A1'),
(122, '12A2');

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
('MH1', 'Toán'),
('MH2', 'Văn');

-- --------------------------------------------------------

--
-- Table structure for table `namhoc`
--

CREATE TABLE IF NOT EXISTS `namhoc` (
  `NamHocid` varchar(50) NOT NULL,
  `NamBatDau` int(5) NOT NULL,
  `NamKetThuc` int(5) NOT NULL,
  `HocKy` varchar(1) NOT NULL,
  `enable` int(1) NOT NULL,
  PRIMARY KEY (`NamHocid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `namhoc`
--

INSERT INTO `namhoc` (`NamHocid`, `NamBatDau`, `NamKetThuc`, `HocKy`, `enable`) VALUES
('20242025', 2024, 2025, '1', 1);

-- --------------------------------------------------------

--
-- Table structure for table `phancong`
--

CREATE TABLE IF NOT EXISTS `phancong` (
  `GiaoVienid` varchar(5) NOT NULL DEFAULT 'GV',
  `Lopid` int(11) NOT NULL,
  `enable` int(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `phancong`
--

INSERT INTO `phancong` (`GiaoVienid`, `Lopid`, `enable`) VALUES
('GV1', 122, 1),
('GV1', 121, 1),
('GV1', 101, 1);

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
('HSK241', 122, '20242025'),
('HSK242', 101, '20242025'),
('HSK243', 101, '20242025');

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
('Học Sinh', 'HS', 1),
('Học Sinh', 'DD', 0);

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
('admin', '', '', '2024-10-02', 'HS'),
('admin', '', '', '2024-10-02', 'HS'),
('admin', '', '', '2024-10-02', 'GV'),
('admin', '', 'nbhnbhb', '2024-10-02', 'HS'),
('admin', '', 'nbhnbhb', '2024-10-02', 'GV'),
('GV1', 'h', '', '2024-10-02', '122'),
('admin', 'nghi thu 2', '', '2024-10-02', 'HS'),
('admin', 'nghi t3', '', '2024-10-02', 'GV'),
('admin', 'nghi t4', '', '2024-10-02', 'HS'),
('admin', 'nghi t4', '', '2024-10-02', 'GV');

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
('HSK241', '12345', 'HS', 1),
('GV1', '123', 'GV', 1),
('HSK242', '0123456789', 'HS', 1),
('HSK243', '03232312546', 'HS', 1);

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

--
-- Dumping data for table `ykien`
--

INSERT INTO `ykien` (`idnguoigui`, `tieudeyk`, `noidungyk`, `thoigianyk`, `tenhs`, `trangthai`) VALUES
('HSK241', '', 'ghn', '2024-10-02', 'HS Test 1', 'Chưa xem');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
