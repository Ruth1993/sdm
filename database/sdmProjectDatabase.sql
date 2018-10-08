-- phpMyAdmin SQL Dump
-- version 3.3.9
-- http://www.phpmyadmin.net
--
-- Serveur: localhost
-- Généré le : Lun 08 Octobre 2018 à 16:12
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
  `id` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `companies`
--


-- --------------------------------------------------------

--
-- Structure de la table `employments`
--

CREATE TABLE IF NOT EXISTS `employments` (
  `id_employer` varchar(255) NOT NULL,
  `id_company` varchar(255) NOT NULL,
  `id_employee` varchar(255) NOT NULL,
  KEY `id_employer` (`id_employer`),
  KEY `id_company` (`id_company`),
  KEY `id_employee` (`id_employee`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `employments`
--


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


-- --------------------------------------------------------

--
-- Structure de la table `health_clubs_patients`
--

CREATE TABLE IF NOT EXISTS `health_clubs_patients` (
  `id_patient` varchar(255) NOT NULL,
  `id_health_club` varchar(255) NOT NULL,
  KEY `id_health_club` (`id_health_club`),
  KEY `id_patient` (`id_patient`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `health_clubs_patients`
--


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
  `last_physical` varchar(255) NOT NULL,
  `hours_spent_health_clubs` varchar(255) NOT NULL,
  PRIMARY KEY (`id_patient`),
  KEY `id_family_doctor` (`id_family_doctor`),
  KEY `last_physical` (`last_physical`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `patients_basic_health_info`
--


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
  `address` text NOT NULL,
  `phone_number` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `persons_basic_info`
--


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


--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `employments`
--
ALTER TABLE `employments`
  ADD CONSTRAINT `employments_ibfk_3` FOREIGN KEY (`id_company`) REFERENCES `companies` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `employments_ibfk_1` FOREIGN KEY (`id_employer`) REFERENCES `persons_roles` (`id_role`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `employments_ibfk_2` FOREIGN KEY (`id_employee`) REFERENCES `persons_roles` (`id_role`) ON DELETE CASCADE ON UPDATE CASCADE;

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
  ADD CONSTRAINT `hospitals_doctors_ibfk_2` FOREIGN KEY (`id_doctor`) REFERENCES `persons_roles` (`id_role`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `hospitals_doctors_ibfk_1` FOREIGN KEY (`id_hospital`) REFERENCES `hospitals` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `insurances_patients`
--
ALTER TABLE `insurances_patients`
  ADD CONSTRAINT `insurances_patients_ibfk_3` FOREIGN KEY (`id_patient`) REFERENCES `persons_roles` (`id_role`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `insurances_patients_ibfk_2` FOREIGN KEY (`id_insurance`) REFERENCES `insurances` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `patients_basic_health_info`
--
ALTER TABLE `patients_basic_health_info`
  ADD CONSTRAINT `patients_basic_health_info_ibfk_3` FOREIGN KEY (`last_physical`) REFERENCES `patients_visits` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `patients_basic_health_info_ibfk_1` FOREIGN KEY (`id_patient`) REFERENCES `persons_roles` (`id_role`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `patients_basic_health_info_ibfk_2` FOREIGN KEY (`id_family_doctor`) REFERENCES `persons_roles` (`id_role`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `patients_medicines`
--
ALTER TABLE `patients_medicines`
  ADD CONSTRAINT `patients_medicines_ibfk_1` FOREIGN KEY (`id_visit`) REFERENCES `patients_visits` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `patients_visits`
--
ALTER TABLE `patients_visits`
  ADD CONSTRAINT `patients_visits_ibfk_2` FOREIGN KEY (`id_patient`) REFERENCES `persons_roles` (`id_role`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `patients_visits_ibfk_1` FOREIGN KEY (`id_hospital_doctors`) REFERENCES `hospitals_doctors` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `persons_roles`
--
ALTER TABLE `persons_roles`
  ADD CONSTRAINT `persons_roles_ibfk_1` FOREIGN KEY (`id_person`) REFERENCES `persons_basic_info` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
