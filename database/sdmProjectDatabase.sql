-- phpMyAdmin SQL Dump
-- version 3.3.9
-- http://www.phpmyadmin.net
--
-- Serveur: localhost
-- Généré le : Mar 02 Octobre 2018 à 16:18
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
-- Structure de la table `companies_persons`
--

CREATE TABLE IF NOT EXISTS `companies_persons` (
  `id_person` varchar(255) NOT NULL,
  `id_company` varchar(255) NOT NULL,
  KEY `id_person` (`id_person`),
  KEY `id_company` (`id_company`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `companies_persons`
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
-- Structure de la table `health_clubs_persons`
--

CREATE TABLE IF NOT EXISTS `health_clubs_persons` (
  `id_person` varchar(255) NOT NULL,
  `id_health_club` varchar(255) NOT NULL,
  KEY `id_person` (`id_person`),
  KEY `id_health_club` (`id_health_club`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `health_clubs_persons`
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
-- Structure de la table `insurances_persons`
--

CREATE TABLE IF NOT EXISTS `insurances_persons` (
  `id_person` varchar(255) NOT NULL,
  `id_insurance` varchar(255) NOT NULL,
  KEY `id_person` (`id_person`),
  KEY `id_insurance` (`id_insurance`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `insurances_persons`
--


-- --------------------------------------------------------

--
-- Structure de la table `persons_basic_info`
--

CREATE TABLE IF NOT EXISTS `persons_basic_info` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `birth_date` varchar(255) NOT NULL,
  `gender` varchar(255) NOT NULL,
  `blood_type` varchar(255) NOT NULL,
  `emergency_contact` varchar(255) NOT NULL,
  `id_family_doctor` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_family_doctor` (`id_family_doctor`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `persons_basic_info`
--


-- --------------------------------------------------------

--
-- Structure de la table `persons_medicines`
--

CREATE TABLE IF NOT EXISTS `persons_medicines` (
  `id_patients` varchar(255) NOT NULL,
  `medicine_name` varchar(255) NOT NULL,
  `dosage` varchar(255) NOT NULL,
  `date_start` varchar(255) NOT NULL,
  `date_end` varchar(255) NOT NULL,
  `id_visit` varchar(255) NOT NULL,
  KEY `id_patients` (`id_patients`),
  KEY `id_visit` (`id_visit`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `persons_medicines`
--


-- --------------------------------------------------------

--
-- Structure de la table `persons_visits`
--

CREATE TABLE IF NOT EXISTS `persons_visits` (
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
-- Contenu de la table `persons_visits`
--


--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `companies_persons`
--
ALTER TABLE `companies_persons`
  ADD CONSTRAINT `companies_persons_ibfk_2` FOREIGN KEY (`id_company`) REFERENCES `companies` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `companies_persons_ibfk_1` FOREIGN KEY (`id_person`) REFERENCES `persons_basic_info` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `health_clubs_persons`
--
ALTER TABLE `health_clubs_persons`
  ADD CONSTRAINT `health_clubs_persons_ibfk_2` FOREIGN KEY (`id_health_club`) REFERENCES `health_clubs` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `health_clubs_persons_ibfk_1` FOREIGN KEY (`id_person`) REFERENCES `persons_basic_info` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `hospitals_doctors`
--
ALTER TABLE `hospitals_doctors`
  ADD CONSTRAINT `hospitals_doctors_ibfk_2` FOREIGN KEY (`id_doctor`) REFERENCES `persons_basic_info` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `hospitals_doctors_ibfk_1` FOREIGN KEY (`id_hospital`) REFERENCES `hospitals` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `insurances_persons`
--
ALTER TABLE `insurances_persons`
  ADD CONSTRAINT `insurances_persons_ibfk_2` FOREIGN KEY (`id_insurance`) REFERENCES `insurances` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `insurances_persons_ibfk_1` FOREIGN KEY (`id_person`) REFERENCES `persons_basic_info` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `persons_basic_info`
--
ALTER TABLE `persons_basic_info`
  ADD CONSTRAINT `persons_basic_info_ibfk_1` FOREIGN KEY (`id_family_doctor`) REFERENCES `hospitals_doctors` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `persons_medicines`
--
ALTER TABLE `persons_medicines`
  ADD CONSTRAINT `persons_medicines_ibfk_2` FOREIGN KEY (`id_visit`) REFERENCES `persons_visits` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `persons_medicines_ibfk_1` FOREIGN KEY (`id_patients`) REFERENCES `persons_basic_info` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `persons_visits`
--
ALTER TABLE `persons_visits`
  ADD CONSTRAINT `persons_visits_ibfk_2` FOREIGN KEY (`id_hospital_doctors`) REFERENCES `hospitals_doctors` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `persons_visits_ibfk_1` FOREIGN KEY (`id_patient`) REFERENCES `persons_basic_info` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
