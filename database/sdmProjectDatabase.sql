-- phpMyAdmin SQL Dump
-- version 3.3.9
-- http://www.phpmyadmin.net
--
-- Serveur: localhost
-- Généré le : Mar 02 Octobre 2018 à 15:55
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
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `companies`
--


-- --------------------------------------------------------

--
-- Structure de la table `employments`
--

CREATE TABLE IF NOT EXISTS `employments` (
  `id_person` varchar(255) NOT NULL,
  `id_company` varchar(255) NOT NULL
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
  `name` varchar(255) NOT NULL
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
  `id_health_club` varchar(255) NOT NULL
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
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `hospitals`
--


-- --------------------------------------------------------

--
-- Structure de la table `hostpitals_doctors`
--

CREATE TABLE IF NOT EXISTS `hostpitals_doctors` (
  `id` varchar(255) NOT NULL,
  `id_hospital` varchar(255) DEFAULT NULL,
  `id_doctor` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `hostpitals_doctors`
--


-- --------------------------------------------------------

--
-- Structure de la table `insurances`
--

CREATE TABLE IF NOT EXISTS `insurances` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL
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
  `id_insurance` varchar(255) NOT NULL
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
  `physical_exam` varchar(255) NOT NULL,
  `id_family_doctor` varchar(255) DEFAULT NULL
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
  `id_visit` varchar(255) NOT NULL
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
  `id_hospital_doctors` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `persons_visits`
--

