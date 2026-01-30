CREATE DATABASE IF NOT EXISTS clashboard;
USE clashboard;

CREATE TABLE IF NOT EXISTS players (
  player_id INT AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(50) UNIQUE,
  email VARCHAR(100),
  password VARCHAR(100),
  townhall_lvl INT,
  trophies INT,
  clan_id INT,
  role ENUM('Leader','Co-Leader','Elder','Member') DEFAULT 'Member'
);

CREATE TABLE IF NOT EXISTS clans (
  clan_id INT AUTO_INCREMENT PRIMARY KEY,
  clan_name VARCHAR(100) UNIQUE,
  description VARCHAR(255),
  leader_id INT
);

CREATE TABLE IF NOT EXISTS wars (
  war_id INT AUTO_INCREMENT PRIMARY KEY,
  clan_id INT,
  opponent_clan VARCHAR(100),
  war_date DATE,
  result ENUM('Win','Loss','Pending') DEFAULT 'Pending'
);

CREATE TABLE IF NOT EXISTS donations (
  donation_id INT AUTO_INCREMENT PRIMARY KEY,
  donor_id INT,
  receiver_id INT,
  troop VARCHAR(50),
  quantity INT
);
