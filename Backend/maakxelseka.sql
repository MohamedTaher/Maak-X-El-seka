-- phpMyAdmin SQL Dump
-- version 4.0.10.12
-- http://www.phpmyadmin.net
--
-- Host: 127.4.190.2:3306
-- Generation Time: Jan 29, 2017 at 07:02 AM
-- Server version: 5.5.52
-- PHP Version: 5.3.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `maakxelseka`
--

-- --------------------------------------------------------

--
-- Table structure for table `confirm`
--

CREATE TABLE IF NOT EXISTS `confirm` (
  `confirm_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `request_id` int(11) NOT NULL,
  `state` int(11) NOT NULL,
  `from_lat` varchar(45) NOT NULL,
  `from_lng` varchar(45) NOT NULL,
  `to_lat` varchar(45) NOT NULL,
  `to_lng` varchar(45) NOT NULL,
  PRIMARY KEY (`confirm_id`),
  UNIQUE KEY `unique_index` (`user_id`,`request_id`),
  KEY `request_id` (`request_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `confirm`
--

INSERT INTO `confirm` (`confirm_id`, `user_id`, `request_id`, `state`, `from_lat`, `from_lng`, `to_lat`, `to_lng`) VALUES
(2, 2, 3, 1, '30.0007091', '31.1659828', '30.001970497841587', '31.168939098715782');

-- --------------------------------------------------------

--
-- Table structure for table `request`
--

CREATE TABLE IF NOT EXISTS `request` (
  `req_id` int(11) NOT NULL AUTO_INCREMENT,
  `from_lat` varchar(45) NOT NULL,
  `from_lng` varchar(45) NOT NULL,
  `to_lat` varchar(45) NOT NULL,
  `to_lng` varchar(45) NOT NULL,
  `description` varchar(45) NOT NULL,
  `state` varchar(2) NOT NULL,
  `data` varchar(11) NOT NULL,
  `time` varchar(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `user_id2` int(11) DEFAULT NULL,
  `user_id3` int(11) DEFAULT NULL,
  `user_id4` int(11) DEFAULT NULL,
  PRIMARY KEY (`req_id`),
  KEY `user_id` (`user_id`),
  KEY `user_id2` (`user_id2`),
  KEY `user_id3` (`user_id3`),
  KEY `user_id4` (`user_id4`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `request`
--

INSERT INTO `request` (`req_id`, `from_lat`, `from_lng`, `to_lat`, `to_lng`, `description`, `state`, `data`, `time`, `user_id`, `user_id2`, `user_id3`, `user_id4`) VALUES
(1, '30.0009478', '31.1663829', '30.000039639070547', '31.167817935347554', 'srcToDes', '0', '29-10-2016', '0:38', 1, NULL, NULL, NULL),
(2, '30.0009519', '31.166454', '30.029884483604906', '31.21086034923792', 'home to fci', '0', '29-10-2016', '6:0', 1, NULL, NULL, NULL),
(3, '29.9958182', '31.1637008', '29.997813442940938', '31.17067951709032', 'New request', '0', '29-10-2016', '21:20', 1, 2, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `mobile_num` varchar(20) NOT NULL,
  `national_id` varchar(15) NOT NULL,
  `rate_total` varchar(15) NOT NULL,
  `positive_rate` varchar(15) NOT NULL,
  `image` varchar(45) DEFAULT NULL,
  `email` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `national_id` (`national_id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`user_id`, `name`, `mobile_num`, `national_id`, `rate_total`, `positive_rate`, `image`, `email`, `password`) VALUES
(1, 'Taher', '01286100578', '12345678912', '0', '0', '', 'taher@hotmail.com', '123456'),
(2, 'Maram', '01234567891', '12345678912345', '0', '0', '', 'm@hotmail.com', '123456');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `confirm`
--
ALTER TABLE `confirm`
  ADD CONSTRAINT `confirm_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  ADD CONSTRAINT `confirm_ibfk_2` FOREIGN KEY (`request_id`) REFERENCES `request` (`req_id`);

--
-- Constraints for table `request`
--
ALTER TABLE `request`
  ADD CONSTRAINT `request_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  ADD CONSTRAINT `request_ibfk_2` FOREIGN KEY (`user_id2`) REFERENCES `user` (`user_id`),
  ADD CONSTRAINT `request_ibfk_3` FOREIGN KEY (`user_id3`) REFERENCES `user` (`user_id`),
  ADD CONSTRAINT `request_ibfk_4` FOREIGN KEY (`user_id4`) REFERENCES `user` (`user_id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
