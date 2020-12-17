-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : jeu. 17 déc. 2020 à 23:50
-- Version du serveur :  10.4.11-MariaDB
-- Version de PHP : 7.4.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `youcode_talent`
--

-- --------------------------------------------------------

--
-- Structure de la table `administrator`
--

CREATE TABLE `administrator` (
  `id` bigint(20) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `phone` varchar(100) NOT NULL,
  `password` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `administrator`
--

INSERT INTO `administrator` (`id`, `first_name`, `last_name`, `email`, `phone`, `password`) VALUES
(15970010, 'Ahmed', 'Mahmoud', 'ahmed.mahmoud.admin@gmail.com', '+212 6 00 00 00 00', '123456');

-- --------------------------------------------------------

--
-- Structure de la table `admin_session`
--

CREATE TABLE `admin_session` (
  `id` bigint(20) NOT NULL,
  `id_administrator` bigint(20) NOT NULL,
  `is_connected` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `admin_session`
--

INSERT INTO `admin_session` (`id`, `id_administrator`, `is_connected`) VALUES
(1, 15970010, 0);

-- --------------------------------------------------------

--
-- Structure de la table `category`
--

CREATE TABLE `category` (
  `id` bigint(11) NOT NULL,
  `name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `category`
--

INSERT INTO `category` (`id`, `name`) VALUES
(1, 'La dance'),
(2, 'En chantant'),
(3, 'La scène'),
(4, 'La Comédie'),
(5, 'Calcul mental'),
(6, 'Le cube de Rubik');

-- --------------------------------------------------------

--
-- Structure de la table `participation`
--

CREATE TABLE `participation` (
  `id_user` bigint(11) NOT NULL,
  `id_category` bigint(20) NOT NULL,
  `description` text NOT NULL,
  `show_start_time` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `show_end_time` date NOT NULL,
  `attached_file` varchar(23) NOT NULL,
  `is_accepted` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `participation`
--

INSERT INTO `participation` (`id_user`, `id_category`, `description`, `show_start_time`, `show_end_time`, `attached_file`, `is_accepted`) VALUES
(434751060, 3, 'sggsfgfg', '2020-12-12 04:12:12', '2020-12-12', 'C://filess', 0),
(723916067, 3, 'helooooo', '2020-12-12 04:12:11', '2020-12-12', 'C://fileee', 0);

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE `user` (
  `id` bigint(11) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `phone` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`id`, `first_name`, `last_name`, `email`, `phone`) VALUES
(1, 'achraf', 'achraf', 'achraf@achraf.com', '097268398'),
(23108741, 'gouriani', 'gouriani', 'gouriani@gouriani.com', '0677833921'),
(257093950, 'achraf', 'achraf', 'achraf@achraf.com', '0687888261'),
(319513516, 'achraf', 'gouriani', 'achraf@achraf.com', '0677366251'),
(349282074, 'Achraf', 'achraf', 'achraf@achraf.com', '0677833623'),
(431050561, 'achraf', 'achraf', 'achraf@achraf.com', '0677833927'),
(434751060, 'gouriani', 'gouriiani', 'gouriani@gmail.com', '0677388292'),
(529692314, 'achraf', 'gouriani', 'achraf@achraf.com', '0688733621'),
(617736391, 'achraf', 'achraf', 'achraf@gmail.com', '0677833928'),
(711440264, 'achraf', 'achraf', 'achraf@achraf.com', '0677833928'),
(723916067, 'achraf', 'gouriani', 'cra.achraf7@gmail.com', '0688977625'),
(913604470, 'achraf', 'achraf', 'achraf@achraf.com', '0688733826'),
(963113340, 'achraf', 'gouriani', 'gouriani@gouriani.com', '0677822736'),
(993690194, 'achraf', 'gouriani', 'achraf@achraf.com', '0677839273');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `administrator`
--
ALTER TABLE `administrator`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `admin_session`
--
ALTER TABLE `admin_session`
  ADD KEY `id_administrator` (`id_administrator`);

--
-- Index pour la table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `participation`
--
ALTER TABLE `participation`
  ADD KEY `id_user` (`id_user`),
  ADD KEY `id_category` (`id_category`);

--
-- Index pour la table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `admin_session`
--
ALTER TABLE `admin_session`
  ADD CONSTRAINT `admin_session_ibfk_1` FOREIGN KEY (`id_administrator`) REFERENCES `administrator` (`id`);

--
-- Contraintes pour la table `participation`
--
ALTER TABLE `participation`
  ADD CONSTRAINT `participation_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `participation_ibfk_2` FOREIGN KEY (`id_category`) REFERENCES `category` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
