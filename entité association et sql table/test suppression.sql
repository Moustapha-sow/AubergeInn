-- Supprimer les tables dans le bon ordre pour éviter les erreurs de contraintes
DROP TABLE IF EXISTS Chambre_commodite CASCADE;
DROP TABLE IF EXISTS Reservation CASCADE;
DROP TABLE IF EXISTS Commodite CASCADE;
DROP TABLE IF EXISTS Chambre CASCADE;
DROP TABLE IF EXISTS Client CASCADE;
