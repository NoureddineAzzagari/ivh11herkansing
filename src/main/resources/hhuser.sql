
CREATE DATABASE `moviedb`;

-- hhuser aanmaken
CREATE USER 'myuser'@'localhost' IDENTIFIED BY 'boem';

-- geef in een keer alle rechten - soort administrator!
GRANT ALL ON `moviedb`.* TO 'myuser'@'localhost';
