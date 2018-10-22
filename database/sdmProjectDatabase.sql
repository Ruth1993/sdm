-- phpMyAdmin SQL Dump
-- version 3.3.9
-- http://www.phpmyadmin.net
--
-- Serveur: localhost
-- Généré le : Lun 22 Octobre 2018 à 11:54
-- Version du serveur: 5.5.8
-- Version de PHP: 5.2.17

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données: `sdmproject`
--

-- --------------------------------------------------------

--
-- Structure de la table `companies`
--

CREATE TABLE IF NOT EXISTS `companies` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `companies`
--

INSERT INTO `companies` (`id`, `name`) VALUES
(1, 'Company 1');

-- --------------------------------------------------------

--
-- Structure de la table `employments`
--

CREATE TABLE IF NOT EXISTS `employments` (
  `id_company` int(11) NOT NULL,
  `id_employee` int(11) NOT NULL,
  KEY `id_company` (`id_company`),
  KEY `id_employee` (`id_employee`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `employments`
--

INSERT INTO `employments` (`id_company`, `id_employee`) VALUES
(1, 2);

-- --------------------------------------------------------

--
-- Structure de la table `health_clubs`
--

CREATE TABLE IF NOT EXISTS `health_clubs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `health_clubs`
--

INSERT INTO `health_clubs` (`id`, `name`) VALUES
(1, 'Health Club 1');

-- --------------------------------------------------------

--
-- Structure de la table `health_clubs_patients`
--

CREATE TABLE IF NOT EXISTS `health_clubs_patients` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_patient` int(11) NOT NULL,
  `id_health_club` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_patient` (`id_patient`),
  KEY `id_health_club` (`id_health_club`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `health_clubs_patients`
--

INSERT INTO `health_clubs_patients` (`id`, `id_patient`, `id_health_club`) VALUES
(1, 2, 1);

-- --------------------------------------------------------

--
-- Structure de la table `hospitals`
--

CREATE TABLE IF NOT EXISTS `hospitals` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `hospitals`
--

INSERT INTO `hospitals` (`id`, `name`) VALUES
(1, 'Hospital 1');

-- --------------------------------------------------------

--
-- Structure de la table `hospitals_doctors`
--

CREATE TABLE IF NOT EXISTS `hospitals_doctors` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_hospital` int(11) DEFAULT NULL,
  `id_doctor` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_hospital` (`id_hospital`),
  KEY `id_doctor` (`id_doctor`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `hospitals_doctors`
--

INSERT INTO `hospitals_doctors` (`id`, `id_hospital`, `id_doctor`) VALUES
(1, 1, 1);

-- --------------------------------------------------------

--
-- Structure de la table `insurances`
--

CREATE TABLE IF NOT EXISTS `insurances` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `insurances`
--

INSERT INTO `insurances` (`id`, `name`) VALUES
(1, 'Insurance 1');

-- --------------------------------------------------------

--
-- Structure de la table `insurances_patients`
--

CREATE TABLE IF NOT EXISTS `insurances_patients` (
  `id_patient` int(11) NOT NULL,
  `id_insurance` int(11) NOT NULL,
  KEY `id_patient` (`id_patient`),
  KEY `id_insurance` (`id_insurance`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `insurances_patients`
--

INSERT INTO `insurances_patients` (`id_patient`, `id_insurance`) VALUES
(2, 1);

-- --------------------------------------------------------

--
-- Structure de la table `patients_basic_health_info`
--

CREATE TABLE IF NOT EXISTS `patients_basic_health_info` (
  `id_patient` int(11) NOT NULL,
  `blood_type` varchar(255) NOT NULL,
  `weight` varchar(255) NOT NULL,
  `height` varchar(255) NOT NULL,
  `emergency_contact` varchar(255) NOT NULL,
  `id_family_doctor` varchar(255) NOT NULL,
  PRIMARY KEY (`id_patient`),
  KEY `id_family_doctor` (`id_family_doctor`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `patients_basic_health_info`
--

INSERT INTO `patients_basic_health_info` (`id_patient`, `blood_type`, `weight`, `height`, `emergency_contact`, `id_family_doctor`) VALUES
(2, 'Blood type 1', 'Weight 1', 'Height 1', 'Emergency contact 1', '1');

-- --------------------------------------------------------

--
-- Structure de la table `patients_health_clubs_visits`
--

CREATE TABLE IF NOT EXISTS `patients_health_clubs_visits` (
  `id_patient_healthclub` int(11) NOT NULL,
  `date` varchar(255) NOT NULL,
  `duration` varchar(255) NOT NULL,
  `reasons` varchar(255) NOT NULL,
  `results` varchar(255) NOT NULL,
  `comments` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_patient_healthclub`),
  KEY `date` (`date`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `patients_health_clubs_visits`
--

INSERT INTO `patients_health_clubs_visits` (`id_patient_healthclub`, `date`, `duration`, `reasons`, `results`, `comments`) VALUES
(1, 'Date 1', 'Duration 1', 'Reasons 1', 'Reasults 1', 'Comments 1');

-- --------------------------------------------------------

--
-- Structure de la table `patients_medicines`
--

CREATE TABLE IF NOT EXISTS `patients_medicines` (
  `medicine_name` varchar(255) NOT NULL,
  `dosage` varchar(255) NOT NULL,
  `date_start` varchar(255) NOT NULL,
  `date_end` varchar(255) NOT NULL,
  `id_visit` int(11) NOT NULL,
  PRIMARY KEY (`id_visit`),
  KEY `medicine_name` (`medicine_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `patients_medicines`
--

INSERT INTO `patients_medicines` (`medicine_name`, `dosage`, `date_start`, `date_end`, `id_visit`) VALUES
('Medicine 1', 'Dosage 1', 'Start 1', 'End 1', 1);

-- --------------------------------------------------------

--
-- Structure de la table `patients_visits`
--

CREATE TABLE IF NOT EXISTS `patients_visits` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_patient` int(11) NOT NULL,
  `date_start` varchar(255) NOT NULL,
  `date_end` varchar(255) NOT NULL,
  `reason` varchar(255) NOT NULL,
  `results` varchar(255) NOT NULL,
  `id_hospital_doctors` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_patient` (`id_patient`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `patients_visits`
--

INSERT INTO `patients_visits` (`id`, `id_patient`, `date_start`, `date_end`, `reason`, `results`, `id_hospital_doctors`) VALUES
(1, 2, 'Start 1', 'End 1', 'Reason 1', 'Results 1', '1');

-- --------------------------------------------------------

--
-- Structure de la table `persons_basic_info`
--

CREATE TABLE IF NOT EXISTS `persons_basic_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `birth_date` varchar(255) NOT NULL,
  `birth_place` varchar(255) NOT NULL,
  `gender` varchar(255) NOT NULL,
  `nationality` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `phone_number` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Contenu de la table `persons_basic_info`
--

INSERT INTO `persons_basic_info` (`id`, `name`, `birth_date`, `birth_place`, `gender`, `nationality`, `address`, `phone_number`) VALUES
(1, 'Name 1', 'Birth date 1', 'Birth place 1', 'Gender 1', 'Nationality 1', 'Address 1', 'Phone number 1'),
(2, 'Name 2', 'Birth date 2', 'Birth place 2', 'Gender 2', 'Nationality 2', 'Address 2', 'Phone number 2');

-- --------------------------------------------------------

--
-- Structure de la table `persons_roles`
--

CREATE TABLE IF NOT EXISTS `persons_roles` (
  `id_role` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(255) NOT NULL,
  `id_person` int(11) NOT NULL,
  PRIMARY KEY (`id_role`),
  KEY `id_person` (`id_person`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Contenu de la table `persons_roles`
--

INSERT INTO `persons_roles` (`id_role`, `role`, `id_person`) VALUES
(1, 'doctor', 1),
(2, 'patient', 2);

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `employments`
--
ALTER TABLE `employments`
  ADD CONSTRAINT `employments_ibfk_2` FOREIGN KEY (`id_employee`) REFERENCES `persons_roles` (`id_role`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `employments_ibfk_1` FOREIGN KEY (`id_company`) REFERENCES `companies` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `health_clubs_patients`
--
ALTER TABLE `health_clubs_patients`
  ADD CONSTRAINT `health_clubs_patients_ibfk_2` FOREIGN KEY (`id_health_club`) REFERENCES `health_clubs` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `health_clubs_patients_ibfk_1` FOREIGN KEY (`id_patient`) REFERENCES `persons_roles` (`id_role`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `hospitals_doctors`
--
ALTER TABLE `hospitals_doctors`
  ADD CONSTRAINT `hospitals_doctors_ibfk_2` FOREIGN KEY (`id_doctor`) REFERENCES `persons_roles` (`id_role`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `hospitals_doctors_ibfk_1` FOREIGN KEY (`id_hospital`) REFERENCES `hospitals` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `insurances_patients`
--
ALTER TABLE `insurances_patients`
  ADD CONSTRAINT `insurances_patients_ibfk_2` FOREIGN KEY (`id_insurance`) REFERENCES `insurances` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `insurances_patients_ibfk_1` FOREIGN KEY (`id_patient`) REFERENCES `persons_roles` (`id_role`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `patients_basic_health_info`
--
ALTER TABLE `patients_basic_health_info`
  ADD CONSTRAINT `patients_basic_health_info_ibfk_1` FOREIGN KEY (`id_patient`) REFERENCES `persons_roles` (`id_role`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `patients_health_clubs_visits`
--
ALTER TABLE `patients_health_clubs_visits`
  ADD CONSTRAINT `patients_health_clubs_visits_ibfk_1` FOREIGN KEY (`id_patient_healthclub`) REFERENCES `health_clubs_patients` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `patients_medicines`
--
ALTER TABLE `patients_medicines`
  ADD CONSTRAINT `patients_medicines_ibfk_1` FOREIGN KEY (`id_visit`) REFERENCES `patients_visits` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `patients_visits`
--
ALTER TABLE `patients_visits`
  ADD CONSTRAINT `patients_visits_ibfk_1` FOREIGN KEY (`id_patient`) REFERENCES `persons_roles` (`id_role`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `persons_roles`
--
ALTER TABLE `persons_roles`
  ADD CONSTRAINT `persons_roles_ibfk_1` FOREIGN KEY (`id_person`) REFERENCES `persons_basic_info` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
