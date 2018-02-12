-- phpMyAdmin SQL Dump
-- version 4.7.3
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Feb 13, 2018 at 12:45 AM
-- Server version: 5.6.35
-- PHP Version: 7.1.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Database: `stage-time`
--

-- --------------------------------------------------------

--
-- Table structure for table `comedy_show`
--

CREATE TABLE `comedy_show` (
  `id` int(11) NOT NULL,
  `venue` varchar(25) NOT NULL,
  `name` varchar(25) NOT NULL,
  `user_uid` int(11) DEFAULT NULL,
  `uid` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `comedy_show`
--

INSERT INTO `comedy_show` (`id`, `venue`, `name`, `user_uid`, `uid`) VALUES
(1, 'Art Bar', 'PIcnic Time', NULL, 1),
(2, 'Foam', 'Coffee Break', NULL, 1),
(3, 'Crack Fox', 'Impolite Company', NULL, 2);

-- --------------------------------------------------------

--
-- Table structure for table `performance`
--

CREATE TABLE `performance` (
  `id` int(11) NOT NULL,
  `name` varchar(25) NOT NULL,
  `position` int(11) NOT NULL,
  `time_allotted` int(11) NOT NULL,
  `comedy_show_id` int(11) DEFAULT NULL,
  `show_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `performance`
--

INSERT INTO `performance` (`id`, `name`, `position`, `time_allotted`, `comedy_show_id`, `show_id`) VALUES
(1, 'Justin Luke', 1, 0, 1, 1),
(2, 'Andrew Mihalevich', 2, 0, 1, 1),
(3, 'Justin Luke', 3, 0, 2, 2),
(4, 'Kenny Kinds', 4, 0, 2, 2),
(5, 'Amy Milton', 1, 0, 2, 2),
(6, 'Stryker Spurlock', 5, 0, 2, 2),
(7, 'Ken Warner', 2, 0, 2, 2);

-- --------------------------------------------------------

--
-- Table structure for table `performer`
--

CREATE TABLE `performer` (
  `id` int(11) NOT NULL,
  `name` varchar(25) NOT NULL,
  `position` int(11) NOT NULL,
  `time_allotted` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `performer`
--

INSERT INTO `performer` (`id`, `name`, `position`, `time_allotted`) VALUES
(1, 'Andrew Mihalevich', 1, 0);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `uid` int(11) NOT NULL,
  `pw_hash` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`uid`, `pw_hash`, `username`) VALUES
(1, '$2a$10$uimsIUpkUp4mRuY3TGmY7uCKVcsCqWo4JO8yTTrw4m/WaJaNiI5z2', 'apmihal'),
(2, '$2a$10$PHMJmDHSbWsrZcZTz6QifOpPTxtjfGN8lbZkMBkSLRzD6E53WbFXq', 'showtime');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `comedy_show`
--
ALTER TABLE `comedy_show`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK9x5fnhojsdtv3a9fd0q696ysl` (`user_uid`),
  ADD KEY `FKl3yam88slwa1v1f4x97m21u6c` (`uid`);

--
-- Indexes for table `performance`
--
ALTER TABLE `performance`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK33uy9uodsihc8b5sm2mrmor6u` (`comedy_show_id`),
  ADD KEY `FKacoixwve8yxtp0bn7xwc35mgl` (`show_id`);

--
-- Indexes for table `performer`
--
ALTER TABLE `performer`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`uid`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `comedy_show`
--
ALTER TABLE `comedy_show`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `performance`
--
ALTER TABLE `performance`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT for table `performer`
--
ALTER TABLE `performer`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `uid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `comedy_show`
--
ALTER TABLE `comedy_show`
  ADD CONSTRAINT `FK9x5fnhojsdtv3a9fd0q696ysl` FOREIGN KEY (`user_uid`) REFERENCES `user` (`uid`),
  ADD CONSTRAINT `FKl3yam88slwa1v1f4x97m21u6c` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`);

--
-- Constraints for table `performance`
--
ALTER TABLE `performance`
  ADD CONSTRAINT `FK33uy9uodsihc8b5sm2mrmor6u` FOREIGN KEY (`comedy_show_id`) REFERENCES `comedy_show` (`id`),
  ADD CONSTRAINT `FKacoixwve8yxtp0bn7xwc35mgl` FOREIGN KEY (`show_id`) REFERENCES `comedy_show` (`id`);
