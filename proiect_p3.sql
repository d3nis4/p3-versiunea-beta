-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3308
-- Generation Time: Dec 21, 2023 at 09:06 AM
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
-- Database: `proiect_p3`
--

-- --------------------------------------------------------

--
-- Table structure for table `medici`
--

CREATE TABLE `medici` (
  `id` int(11) NOT NULL,
  `name` varchar(191) NOT NULL,
  `specializare` varchar(191) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `medici`
--

INSERT INTO `medici` (`id`, `name`, `specializare`) VALUES
(1, 'ion pop', 'cardiolog'),
(2, 'mariana vasile', 'dermatolog'),
(4, 'medic1', 'cardiolog');

-- --------------------------------------------------------

--
-- Table structure for table `orar_medici`
--

CREATE TABLE `orar_medici` (
  `id` int(11) NOT NULL,
  `id_medic` int(10) NOT NULL,
  `zi` varchar(20) NOT NULL,
  `ora_inceput` int(11) NOT NULL,
  `ora_sfarsit` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `orar_medici`
--

INSERT INTO `orar_medici` (`id`, `id_medic`, `zi`, `ora_inceput`, `ora_sfarsit`) VALUES
(1, 1, 'Miercuri', 16, 21),
(2, 1, 'Luni', 10, 16),
(3, 2, 'Luni-Vineri', 15, 20),
(6, 4, 'luni', 12, 20),
(7, 4, 'vineri', 8, 16);

-- --------------------------------------------------------

--
-- Table structure for table `pacienti`
--

CREATE TABLE `pacienti` (
  `id` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `name` varchar(150) NOT NULL,
  `telefon` int(15) NOT NULL,
  `data_nasterii` varchar(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `programari_medic1`
--

CREATE TABLE `programari_medic1` (
  `id` int(11) NOT NULL,
  `nume_pacient` varchar(100) NOT NULL,
  `zi` varchar(10) NOT NULL,
  `ora` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `email` varchar(120) NOT NULL,
  `password` varchar(120) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `email`, `password`) VALUES
(1, 'ionpo@mail.com', '1234'),
(4, 'miau@mail.com', '1234564432'),
(5, 'miau@mai.com', '123444'),
(6, 'adrian@mail.com', '12343');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `medici`
--
ALTER TABLE `medici`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `orar_medici`
--
ALTER TABLE `orar_medici`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `pacienti`
--
ALTER TABLE `pacienti`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `programari_medic1`
--
ALTER TABLE `programari_medic1`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `medici`
--
ALTER TABLE `medici`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `orar_medici`
--
ALTER TABLE `orar_medici`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `pacienti`
--
ALTER TABLE `pacienti`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `programari_medic1`
--
ALTER TABLE `programari_medic1`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
