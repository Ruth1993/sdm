-- phpMyAdmin SQL Dump
-- version 3.3.9
-- http://www.phpmyadmin.net
--
-- Serveur: localhost
-- Généré le : Jeu 18 Octobre 2018 à 16:59
-- Version du serveur: 5.5.8
-- Version de PHP: 5.2.17

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */
/*!40101 SET NAMES utf8 */

--
-- Base de données: `sdmproject`
--

-- --------------------------------------------------------

--
-- Structure de la table `companies`
--

CREATE TABLE IF NOT EXISTS `companies` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `companies`
--

INSERT INTO `companies` (`id`, `name`) VALUES
('CO01', 'PVL'),
('CO02', 'Starbucks');

-- --------------------------------------------------------

--
-- Structure de la table `employments`
--

CREATE TABLE IF NOT EXISTS `employments` (
  `id_company` varchar(255) NOT NULL,
  `id_employee` varchar(255) NOT NULL,
  KEY `id_company` (`id_company`),
  KEY `id_employee` (`id_employee`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `employments`
--

INSERT INTO `employments` (`id_company`, `id_employee`) VALUES
('CO01', 'EE01');

-- --------------------------------------------------------

--
-- Structure de la table `health_clubs`
--

CREATE TABLE IF NOT EXISTS `health_clubs` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `health_clubs`
--

INSERT INTO `health_clubs` (`id`, `name`) VALUES
('HC01', 'Health Club First');

-- --------------------------------------------------------

--
-- Structure de la table `health_clubs_patients`
--

CREATE TABLE IF NOT EXISTS `health_clubs_patients` (
  `id` varchar(255) NOT NULL,
  `id_patient` varchar(255) NOT NULL,
  `id_health_club` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_health_club` (`id_health_club`),
  KEY `id_patient` (`id_patient`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `health_clubs_patients`
--

INSERT INTO `health_clubs_patients` (`id`, `id_patient`, `id_health_club`) VALUES
('HP01', 'PA01', 'HC01');

-- --------------------------------------------------------

--
-- Structure de la table `hospitals`
--

CREATE TABLE IF NOT EXISTS `hospitals` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `hospitals`
--

INSERT INTO `hospitals` (`id`, `name`) VALUES
('HO01', 'Hospital Premier');

-- --------------------------------------------------------

--
-- Structure de la table `hospitals_doctors`
--

CREATE TABLE IF NOT EXISTS `hospitals_doctors` (
  `id` varchar(255) NOT NULL,
  `id_hospital` varchar(255) DEFAULT NULL,
  `id_doctor` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_hospital` (`id_hospital`),
  KEY `id_doctor` (`id_doctor`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `hospitals_doctors`
--

INSERT INTO `hospitals_doctors` (`id`, `id_hospital`, `id_doctor`) VALUES
('HD01', 'HO01', 'DR01');

-- --------------------------------------------------------

--
-- Structure de la table `insurances`
--

CREATE TABLE IF NOT EXISTS `insurances` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `insurances`
--

INSERT INTO `insurances` (`id`, `name`) VALUES
('IN01', 'HealthCare');

-- --------------------------------------------------------

--
-- Structure de la table `insurances_patients`
--

CREATE TABLE IF NOT EXISTS `insurances_patients` (
  `id_patient` varchar(255) NOT NULL,
  `id_insurance` varchar(255) NOT NULL,
  KEY `id_insurance` (`id_insurance`),
  KEY `id_patient` (`id_patient`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `insurances_patients`
--

INSERT INTO `insurances_patients` (`id_patient`, `id_insurance`) VALUES
('PA01', 'IN01');

-- --------------------------------------------------------

--
-- Structure de la table `patients_basic_health_info`
--

CREATE TABLE IF NOT EXISTS `patients_basic_health_info` (
  `id_patient` varchar(255) NOT NULL,
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
('PA01', 'O-', '62', '178', '3102469578', 'DR01');

-- --------------------------------------------------------

--
-- Structure de la table `patients_health_clubs_visits`
--

CREATE TABLE IF NOT EXISTS `patients_health_clubs_visits` (
  `id_patient_healthclub` varchar(255) NOT NULL,
  `date` varchar(255) NOT NULL,
  `duration` varchar(255) NOT NULL,
  `reasons` varchar(255) NOT NULL,
  `results` varchar(255) NOT NULL,
  `comments` varchar(255) DEFAULT NULL,
  KEY `id_patient_healthclub` (`id_patient_healthclub`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `patients_health_clubs_visits`
--

INSERT INTO `patients_health_clubs_visits` (`id_patient_healthclub`, `date`, `duration`, `reasons`, `results`, `comments`) VALUES
('HP01', '2018-10-18', '2 hours', 'back pain', 'good', 'none');

-- --------------------------------------------------------

--
-- Structure de la table `patients_medicines`
--

CREATE TABLE IF NOT EXISTS `patients_medicines` (
  `medicine_name` varchar(255) NOT NULL,
  `dosage` varchar(255) NOT NULL,
  `date_start` varchar(255) NOT NULL,
  `date_end` varchar(255) NOT NULL,
  `id_visit` varchar(255) NOT NULL,
  KEY `id_visit` (`id_visit`),
  KEY `medicine_name` (`medicine_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `patients_medicines`
--

INSERT INTO `patients_medicines` (`medicine_name`, `dosage`, `date_start`, `date_end`, `id_visit`) VALUES
('Vitamins', '1 every morning', '2018_10_9', '2018_11_9', 'PV01');

-- --------------------------------------------------------

--
-- Structure de la table `patients_visits`
--

CREATE TABLE IF NOT EXISTS `patients_visits` (
  `id` varchar(255) NOT NULL,
  `id_patient` varchar(255) NOT NULL,
  `date_start` varchar(255) NOT NULL,
  `date_end` varchar(255) NOT NULL,
  `reason` varchar(255) NOT NULL,
  `results` varchar(255) NOT NULL,
  `id_hospital_doctors` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_patient` (`id_patient`),
  KEY `id_hospital_doctors` (`id_hospital_doctors`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `patients_visits`
--

INSERT INTO `patients_visits` (`id`, `id_patient`, `date_start`, `date_end`, `reason`, `results`, `id_hospital_doctors`) VALUES
('PV01', 'PA01', '2018_10_9', '2018_10_9', 'Check-up', 'Good', 'HD01');

-- --------------------------------------------------------

--
-- Structure de la table `persons_basic_info`
--

CREATE TABLE IF NOT EXISTS `persons_basic_info` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `birth_date` varchar(255) NOT NULL,
  `birth_place` varchar(255) NOT NULL,
  `gender` varchar(255) NOT NULL,
  `nationality` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `phone_number` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `persons_basic_info`
--

INSERT INTO `persons_basic_info` (`id`, `name`, `birth_date`, `birth_place`, `gender`, `nationality`, `address`, `phone_number`) VALUES
('PE01', 'Sophie Martin', '1992_12_05', 'Enschede', 'Female', 'French_Dutch', 'not communicated', '0123456789'),
('PE02', 'Alan Tews', '1985_05_13', 'London', 'Male', 'Dutch', 'Random Street 23, 4562LJ City', '2134567089'),
('PE03', 'Alice L', '1975_06_19', 'Berlin', 'Female', 'German', 'Uni street 645, 1578KL Town', '5873469210');

-- --------------------------------------------------------

--
-- Structure de la table `persons_roles`
--

CREATE TABLE IF NOT EXISTS `persons_roles` (
  `id_role` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL,
  `id_person` varchar(255) NOT NULL,
  PRIMARY KEY (`id_role`),
  KEY `id_person` (`id_person`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `persons_roles`
--

INSERT INTO `persons_roles` (`id_role`, `role`, `id_person`) VALUES
('DR01', 'doctor', 'PE02'),
('EE01', 'employee', 'PE01'),
('ER01', 'employer', 'PE03'),
('PA01', 'patient', 'PE01');

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `employments`
--
ALTER TABLE `employments`
  ADD CONSTRAINT `employments_ibfk_2` FOREIGN KEY (`id_employee`) REFERENCES `persons_roles` (`id_role`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `employments_ibfk_3` FOREIGN KEY (`id_company`) REFERENCES `companies` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `health_clubs_patients`
--
ALTER TABLE `health_clubs_patients`
  ADD CONSTRAINT `health_clubs_patients_ibfk_1` FOREIGN KEY (`id_patient`) REFERENCES `persons_roles` (`id_role`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `health_clubs_patients_ibfk_2` FOREIGN KEY (`id_health_club`) REFERENCES `health_clubs` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `hospitals_doctors`
--
ALTER TABLE `hospitals_doctors`
  ADD CONSTRAINT `hospitals_doctors_ibfk_1` FOREIGN KEY (`id_hospital`) REFERENCES `hospitals` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `hospitals_doctors_ibfk_2` FOREIGN KEY (`id_doctor`) REFERENCES `persons_roles` (`id_role`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `insurances_patients`
--
ALTER TABLE `insurances_patients`
  ADD CONSTRAINT `insurances_patients_ibfk_2` FOREIGN KEY (`id_insurance`) REFERENCES `insurances` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `insurances_patients_ibfk_3` FOREIGN KEY (`id_patient`) REFERENCES `persons_roles` (`id_role`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `patients_basic_health_info`
--
ALTER TABLE `patients_basic_health_info`
  ADD CONSTRAINT `patients_basic_health_info_ibfk_1` FOREIGN KEY (`id_patient`) REFERENCES `persons_roles` (`id_role`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `patients_basic_health_info_ibfk_2` FOREIGN KEY (`id_family_doctor`) REFERENCES `persons_roles` (`id_role`) ON DELETE CASCADE ON UPDATE CASCADE;

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
  ADD CONSTRAINT `patients_visits_ibfk_1` FOREIGN KEY (`id_hospital_doctors`) REFERENCES `hospitals_doctors` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `patients_visits_ibfk_2` FOREIGN KEY (`id_patient`) REFERENCES `persons_roles` (`id_role`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `persons_roles`
--
ALTER TABLE `persons_roles`
  ADD CONSTRAINT `persons_roles_ibfk_1` FOREIGN KEY (`id_person`) REFERENCES `persons_basic_info` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
