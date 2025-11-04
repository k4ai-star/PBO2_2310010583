-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 04, 2025 at 05:28 AM
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
-- Database: `pbo2_2310010583`
--

-- --------------------------------------------------------

--
-- Table structure for table `jenis_alat`
--

CREATE TABLE `jenis_alat` (
  `ID_Alat` int(11) NOT NULL,
  `Nama_Alat` varchar(100) DEFAULT NULL,
  `Deskripsi` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `jenis_alat`
--

INSERT INTO `jenis_alat` (`ID_Alat`, `Nama_Alat`, `Deskripsi`) VALUES
(1, 'asd', 'adas'),
(2, 'kucing', 'asdsda');

-- --------------------------------------------------------

--
-- Table structure for table `jenis_produk`
--

CREATE TABLE `jenis_produk` (
  `ID_JenisProduk` int(11) NOT NULL,
  `Jenis_Produk` varchar(100) DEFAULT NULL,
  `Ukuran` varchar(50) DEFAULT NULL,
  `Harga` decimal(15,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `jenis_produk`
--

INSERT INTO `jenis_produk` (`ID_JenisProduk`, `Jenis_Produk`, `Ukuran`, `Harga`) VALUES
(1, 'makanan', 'L', 20000.00),
(2, 'bujang', 'XL', 100000.00),
(4, 'minuman', 'S', 3000000.00);

-- --------------------------------------------------------

--
-- Table structure for table `ketersediaan_alat`
--

CREATE TABLE `ketersediaan_alat` (
  `ID_Status` int(11) NOT NULL,
  `ID_Alat` int(11) DEFAULT NULL,
  `Status` varchar(50) DEFAULT NULL,
  `Tanggal_Pemasukan` date DEFAULT NULL,
  `Tanggal_Selesai` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `ketersediaan_alat`
--

INSERT INTO `ketersediaan_alat` (`ID_Status`, `ID_Alat`, `Status`, `Tanggal_Pemasukan`, `Tanggal_Selesai`) VALUES
(1, 1, 'asda', '2020-10-11', '2020-10-12'),
(2, 1, 'aktif', '2020-12-13', '2020-12-14');

-- --------------------------------------------------------

--
-- Table structure for table `produk`
--

CREATE TABLE `produk` (
  `ID_Produk` int(11) NOT NULL,
  `ID_JenisProduk` int(11) DEFAULT NULL,
  `ID_Wilayah` int(11) DEFAULT NULL,
  `Tanggal_Produksi` date DEFAULT NULL,
  `Jumlah_Produksi` int(11) DEFAULT NULL,
  `Nama_Produk` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `produk`
--

INSERT INTO `produk` (`ID_Produk`, `ID_JenisProduk`, `ID_Wilayah`, `Tanggal_Produksi`, `Jumlah_Produksi`, `Nama_Produk`) VALUES
(1, 2, 2, '2020-10-11', 211, 'wqdq'),
(3, 2, 2, '2020-10-11', 11, 'asds');

-- --------------------------------------------------------

--
-- Table structure for table `wilayah`
--

CREATE TABLE `wilayah` (
  `ID_Wilayah` int(11) NOT NULL,
  `Nama_Wilayah` varchar(100) DEFAULT NULL,
  `Wilayah_Penempatan` varchar(100) DEFAULT NULL,
  `Tanggal_Pembentukan` date DEFAULT NULL,
  `Tanggal_Rehabilitasi` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `wilayah`
--

INSERT INTO `wilayah` (`ID_Wilayah`, `Nama_Wilayah`, `Wilayah_Penempatan`, `Tanggal_Pembentukan`, `Tanggal_Rehabilitasi`) VALUES
(1, 'banjar', 'diponegoro', '2025-08-19', '2025-08-17'),
(2, 'banjar', 'diponegoro', '2025-08-19', '2025-08-17'),
(8, 'sada', 'asdad', '2020-10-11', '2020-10-09');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `jenis_alat`
--
ALTER TABLE `jenis_alat`
  ADD PRIMARY KEY (`ID_Alat`);

--
-- Indexes for table `jenis_produk`
--
ALTER TABLE `jenis_produk`
  ADD PRIMARY KEY (`ID_JenisProduk`);

--
-- Indexes for table `ketersediaan_alat`
--
ALTER TABLE `ketersediaan_alat`
  ADD PRIMARY KEY (`ID_Status`),
  ADD KEY `ID_Alat` (`ID_Alat`);

--
-- Indexes for table `produk`
--
ALTER TABLE `produk`
  ADD PRIMARY KEY (`ID_Produk`),
  ADD KEY `ID_JenisProduk` (`ID_JenisProduk`),
  ADD KEY `ID_Wilayah` (`ID_Wilayah`);

--
-- Indexes for table `wilayah`
--
ALTER TABLE `wilayah`
  ADD PRIMARY KEY (`ID_Wilayah`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `ketersediaan_alat`
--
ALTER TABLE `ketersediaan_alat`
  ADD CONSTRAINT `ketersediaan_alat_ibfk_1` FOREIGN KEY (`ID_Alat`) REFERENCES `jenis_alat` (`ID_Alat`);

--
-- Constraints for table `produk`
--
ALTER TABLE `produk`
  ADD CONSTRAINT `produk_ibfk_1` FOREIGN KEY (`ID_JenisProduk`) REFERENCES `jenis_produk` (`ID_JenisProduk`),
  ADD CONSTRAINT `produk_ibfk_2` FOREIGN KEY (`ID_Wilayah`) REFERENCES `wilayah` (`ID_Wilayah`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
