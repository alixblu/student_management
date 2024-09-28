-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Sep 28, 2024 at 09:03 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

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

CREATE TABLE `chitietdiem` (
  `HocSinhid` varchar(10) NOT NULL,
  `MonHocid` varchar(11) NOT NULL,
  `HocKyid` int(11) NOT NULL,
  `HeSoid` int(11) NOT NULL,
  `NamHocid` varchar(20) NOT NULL DEFAULT '',
  `Diem` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `chitietquyen`
--

CREATE TABLE `chitietquyen` (
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
('admin', 'CN21', '1');

-- --------------------------------------------------------

--
-- Table structure for table `chucnang`
--

CREATE TABLE `chucnang` (
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

CREATE TABLE `diemtbhocky` (
  `HocSinhid` varchar(10) NOT NULL,
  `HocKyid` int(11) NOT NULL,
  `NamHocid` varchar(20) NOT NULL,
  `DiemTrungBinh` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `giaovien`
--

CREATE TABLE `giaovien` (
  `GiaoVienid` varchar(5) NOT NULL DEFAULT 'GV',
  `TenGiaoVien` varchar(50) NOT NULL,
  `GioiTinh` varchar(5) NOT NULL,
  `NamSinh` varchar(10) NOT NULL,
  `DiaChi` varchar(50) NOT NULL,
  `DienThoai` varchar(11) NOT NULL DEFAULT '',
  `PhanMon` varchar(50) NOT NULL,
  `IMG` text DEFAULT NULL,
  `enable` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `giaovien`
--

INSERT INTO `giaovien` (`GiaoVienid`, `TenGiaoVien`, `GioiTinh`, `NamSinh`, `DiaChi`, `DienThoai`, `PhanMon`, `IMG`, `enable`) VALUES
('GV1', 'GV A', 'Nam', '11/09/1998', 'grgrw', '02222222587', 'Toán', 'yh', 1);

-- --------------------------------------------------------

--
-- Table structure for table `hocky`
--

CREATE TABLE `hocky` (
  `HocKyid` int(11) NOT NULL,
  `TenHocKy` varchar(50) NOT NULL
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

CREATE TABLE `hocphi` (
  `idhs` varchar(10) NOT NULL,
  `idnh` varchar(22) NOT NULL,
  `thoigian` varchar(50) NOT NULL,
  `status` int(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `hocsinh`
--

CREATE TABLE `hocsinh` (
  `HocSinhid` varchar(10) NOT NULL DEFAULT 'HS',
  `HoVaTen` varchar(50) NOT NULL,
  `GioiTinh` varchar(50) NOT NULL,
  `NgaySinh` varchar(50) NOT NULL,
  `DienThoai` varchar(11) DEFAULT '0',
  `DiaChi` text NOT NULL,
  `HocPhi` varchar(20) NOT NULL DEFAULT 'Chưa thanh toán',
  `IMG` text DEFAULT NULL,
  `enable` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `hocsinh`
--

INSERT INTO `hocsinh` (`HocSinhid`, `HoVaTen`, `GioiTinh`, `NgaySinh`, `DienThoai`, `DiaChi`, `HocPhi`, `IMG`, `enable`) VALUES
('HSK241', 'fcvaedfve', 'Nam', '17/09/2008', '02222222222', 'sFAEGE', 'Chưa thanh toán', '', 1),
('HSK2410', 'nammm', 'Nam', '12/09/2007', '1234567890', 'ádasasdfasdf', 'Chưa thanh toán', '', 1),
('HSK2411', 'vyyy', 'Nam', '11/09/2007', '1234567890', 'adsfsaf', 'Chưa thanh toán', '', 1),
('HSK2412', 'lklkk', 'Nam', '20/09/2007', '1234567890', 'àasfasf', 'Chưa thanh toán', '', 1),
('HSK2413', 'ádfasfasfasfasf', 'Nam', '14/09/2005', '1234567890', '1234asdfasfa', 'Chưa thanh toán', '', 1),
('HSK2414', 'dsdadsadsaadsadsa', 'Nam', '12/09/2002', '1234567895', 'adsad', 'Chưa thanh toán', '', 1),
('HSK2415', 'tttttttt', 'Nam', '16/09/2003', '1234567890', 'tttttt', 'Chưa thanh toán', '', 1),
('HSK2416', 'tâtdsaaaâ', 'Nam', '14/09/2004', '1234567890', 'adsfasdasf', 'Chưa thanh toán', '', 1),
('HSK2417', 'kkkkkk', 'Nam', '05/09/2007', '1234567890', 'àasfasfasd', 'Chưa thanh toán', '', 1),
('HSK2418', 'dgdfgfgfg', 'Nam', '02/09/2004', '0123456789', 'fdfdfdf', 'Chưa thanh toán', '', 1),
('HSK2419', 'aaaaaaaaaaaaaaaaaaaa', 'Nam', '07/09/2004', '1234567890', 'aaaaaaaaaaaaaaa', 'Chưa thanh toán', '', 1),
('HSK242', 'aaaaaaaaaaaaaa', 'Nam', '13/09/2006', '1234567890', 'aaaaaaaaaaaa', 'Chưa thanh toán', '', 1),
('HSK243', 'bbbbb', 'Nam', '06/09/2007', '1234567890', 'bbbb', 'Chưa thanh toán', '', 1),
('HSK244', 'nammmm', 'Nam', '10/09/2008', '1234567890', 'adsafasfasf', 'Chưa thanh toán', '', 1),
('HSK245', 'ccccc', 'Nam', '12/09/2006', '1234567890', 'cccc', 'Chưa thanh toán', '', 1),
('HSK246', 'hhhhh', 'Nam', '10/09/2007', '1234567890', 'hhhh', 'Chưa thanh toán', '', 1),
('HSK247', 'ccccc', 'Nam', '11/09/2008', '1234567890', 'cccccccc', 'Chưa thanh toán', '', 1),
('HSK248', 'tran hoài nam', 'Nam', '05/09/2007', '1234567890', 'ádfasfasfas', 'Chưa thanh toán', '', 1),
('HSK249', 'fdfdfdfd', 'Nam', '02/09/2004', '0123456777', 'fdfdff', 'Chưa thanh toán', '', 1);

-- --------------------------------------------------------

--
-- Table structure for table `kqhocsinhcanam`
--

CREATE TABLE `kqhocsinhcanam` (
  `HocSinhid` varchar(10) NOT NULL,
  `NamHocid` varchar(50) NOT NULL,
  `HocLuc` varchar(50) DEFAULT NULL,
  `HanhKiem` varchar(10) DEFAULT 'Tốt',
  `Diemtb` float DEFAULT NULL,
  `KetQua` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `lop`
--

CREATE TABLE `lop` (
  `Lopid` int(11) NOT NULL,
  `TenLop` varchar(50) NOT NULL
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

CREATE TABLE `monhoc` (
  `MonHocid` varchar(11) NOT NULL,
  `TenMonHoc` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `monhoc`
--

INSERT INTO `monhoc` (`MonHocid`, `TenMonHoc`) VALUES
('TOAN', 'Toán'),
('VAN', 'Văn');

-- --------------------------------------------------------

--
-- Table structure for table `namhoc`
--

CREATE TABLE `namhoc` (
  `NamHocid` varchar(50) NOT NULL,
  `NamBatDau` int(5) NOT NULL,
  `NamKetThuc` int(5) NOT NULL,
  `HocKy` varchar(1) NOT NULL,
  `enable` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `namhoc`
--

INSERT INTO `namhoc` (`NamHocid`, `NamBatDau`, `NamKetThuc`, `HocKy`, `enable`) VALUES
('20242025', 2024, 2025, '2', 1);

-- --------------------------------------------------------

--
-- Table structure for table `phancong`
--

CREATE TABLE `phancong` (
  `GiaoVienid` varchar(5) NOT NULL DEFAULT 'GV',
  `Lopid` int(11) NOT NULL,
  `enable` int(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `phancong`
--

INSERT INTO `phancong` (`GiaoVienid`, `Lopid`, `enable`) VALUES
('GV1', 10, 1),
('GV1', 10, 1),
('GV1', 11, 1),
('GV1', 11, 1);

-- --------------------------------------------------------

--
-- Table structure for table `phanlop`
--

CREATE TABLE `phanlop` (
  `HocSinhid` varchar(10) NOT NULL,
  `Lopid` int(11) NOT NULL,
  `NamHocid` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `phanlop`
--

INSERT INTO `phanlop` (`HocSinhid`, `Lopid`, `NamHocid`) VALUES
('HSK2410', 10, '20242025'),
('HSK2411', 10, '20242025'),
('HSK2412', 10, '20242025'),
('HSK2413', 10, '20242025'),
('HSK2414', 10, '20242025'),
('HSK2415', 10, '20242025'),
('HSK2416', 10, '20242025'),
('HSK2417', 10, '20242025'),
('HSK2418', 10, '20242025'),
('HSK2419', 10, '20242025'),
('HSK248', 11, '20242025'),
('HSK249', 11, '20242025');

-- --------------------------------------------------------

--
-- Table structure for table `quyen`
--

CREATE TABLE `quyen` (
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

CREATE TABLE `thongbao` (
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

CREATE TABLE `user` (
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
('nhhhyh', '02222222587', 'user', 1),
('GV1', 'GV1@', 'GV', 1),
('null', 'null', 'HS', 1),
('HSK2418', '0123456789', 'HS', 1),
('HSK2419', '1234567890', 'HS', 1);

-- --------------------------------------------------------

--
-- Table structure for table `ykien`
--

CREATE TABLE `ykien` (
  `idnguoigui` varchar(10) NOT NULL,
  `tieudeyk` text DEFAULT NULL,
  `noidungyk` text DEFAULT NULL,
  `thoigianyk` varchar(50) DEFAULT NULL,
  `tenhs` varchar(50) DEFAULT NULL,
  `trangthai` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `chitietdiem`
--
ALTER TABLE `chitietdiem`
  ADD PRIMARY KEY (`HocSinhid`,`MonHocid`,`HocKyid`,`HeSoid`) USING BTREE;

--
-- Indexes for table `diemtbhocky`
--
ALTER TABLE `diemtbhocky`
  ADD PRIMARY KEY (`HocSinhid`,`HocKyid`);

--
-- Indexes for table `giaovien`
--
ALTER TABLE `giaovien`
  ADD PRIMARY KEY (`GiaoVienid`);

--
-- Indexes for table `hocky`
--
ALTER TABLE `hocky`
  ADD PRIMARY KEY (`HocKyid`);

--
-- Indexes for table `hocsinh`
--
ALTER TABLE `hocsinh`
  ADD PRIMARY KEY (`HocSinhid`);

--
-- Indexes for table `kqhocsinhcanam`
--
ALTER TABLE `kqhocsinhcanam`
  ADD PRIMARY KEY (`HocSinhid`,`NamHocid`);

--
-- Indexes for table `lop`
--
ALTER TABLE `lop`
  ADD PRIMARY KEY (`Lopid`);

--
-- Indexes for table `monhoc`
--
ALTER TABLE `monhoc`
  ADD PRIMARY KEY (`MonHocid`);

--
-- Indexes for table `namhoc`
--
ALTER TABLE `namhoc`
  ADD PRIMARY KEY (`NamHocid`);

--
-- Indexes for table `phanlop`
--
ALTER TABLE `phanlop`
  ADD PRIMARY KEY (`HocSinhid`,`Lopid`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
