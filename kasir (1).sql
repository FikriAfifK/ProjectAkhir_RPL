-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 11, 2023 at 03:00 AM
-- Server version: 10.4.21-MariaDB
-- PHP Version: 8.0.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `kasir`
--

-- --------------------------------------------------------

--
-- Table structure for table `barang`
--

CREATE TABLE `barang` (
  `id_barang` int(11) NOT NULL,
  `nama_barang` varchar(100) NOT NULL,
  `harga_barang` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `kadaluarsa` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `barang`
--

INSERT INTO `barang` (`id_barang`, `nama_barang`, `harga_barang`, `quantity`, `kadaluarsa`) VALUES
(3, 'Nasi Kucing', 3000, 17, '2022-05-12'),
(8, 'Milo 3 in 1', 3000, 7, '2023-05-20'),
(17, 'Tempe', 2000, 14, '2023-05-30'),
(19, 'Milo', 3000, 53, '2023-05-31'),
(30, 'Milo', 3000, 26, '2024-06-11'),
(31, 'Hilo', 3000, 17, '2023-06-24'),
(32, 'Milo', 3000, 50, '2024-06-10');

-- --------------------------------------------------------

--
-- Table structure for table `cart`
--

CREATE TABLE `cart` (
  `id_cart` int(11) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `harga` int(11) NOT NULL,
  `banyak` int(11) NOT NULL,
  `total` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `history`
--

CREATE TABLE `history` (
  `id_history` int(11) NOT NULL,
  `tanggal_waktu` datetime NOT NULL,
  `nama` varchar(100) NOT NULL,
  `harga` int(11) NOT NULL,
  `banyak` int(11) NOT NULL,
  `sub_total` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `history`
--

INSERT INTO `history` (`id_history`, `tanggal_waktu`, `nama`, `harga`, `banyak`, `sub_total`) VALUES
(1, '2023-05-24 07:39:54', 'Milo', 3000, 2, 6000),
(2, '2023-05-24 12:33:21', 'Milo', 3000, 1, 3000),
(3, '2023-05-24 12:34:32', 'Milo', 3000, 1, 3000),
(4, '2023-05-24 14:32:11', 'Hilo', 2000, 2, 4000),
(5, '2023-05-24 14:32:11', 'Tempe', 2000, 1, 2000),
(6, '2023-05-24 15:00:56', 'Milo', 3000, 1, 3000),
(7, '2023-05-24 15:00:56', 'Milo', 3000, 1, 3000),
(8, '2023-05-24 15:00:56', 'Hilo', 2000, 2, 4000),
(9, '2023-05-24 15:33:17', 'Milo', 3000, 2, 6000),
(10, '2023-05-24 15:33:17', 'Hilo', 2000, 3, 6000),
(11, '2023-05-24 15:33:17', 'Hilo', 2000, 2, 4000),
(12, '2023-05-24 15:33:17', 'Milo', 3000, 2, 6000),
(13, '2023-05-25 10:25:16', 'Milo', 3000, 1, 3000),
(14, '2023-05-25 10:25:16', 'Nasi Kucing', 3000, 3, 9000),
(15, '2023-05-25 10:42:06', 'Hilo', 2000, 2, 4000),
(16, '2023-05-29 13:45:03', 'Tempe', 2000, 2, 4000),
(17, '2023-05-29 13:45:03', 'Milo', 3000, 1, 3000),
(18, '2023-05-29 13:45:03', 'Hilo', 2000, 1, 2000),
(19, '2023-06-11 07:23:37', 'Milo', 3000, 2, 6000),
(20, '2023-06-11 07:23:37', 'Hilo', 3000, 3, 9000),
(21, '2023-06-11 07:55:48', 'Milo', 3000, 2, 6000);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `barang`
--
ALTER TABLE `barang`
  ADD PRIMARY KEY (`id_barang`);

--
-- Indexes for table `cart`
--
ALTER TABLE `cart`
  ADD PRIMARY KEY (`id_cart`);

--
-- Indexes for table `history`
--
ALTER TABLE `history`
  ADD PRIMARY KEY (`id_history`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `barang`
--
ALTER TABLE `barang`
  MODIFY `id_barang` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;

--
-- AUTO_INCREMENT for table `cart`
--
ALTER TABLE `cart`
  MODIFY `id_cart` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `history`
--
ALTER TABLE `history`
  MODIFY `id_history` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
