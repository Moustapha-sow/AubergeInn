-- mise a jour d'un client
-- Modifier le nom et l’âge du client ayant l’ID 1
UPDATE Client
SET nom = 'Dubreuil', age = 35
WHERE idClient = 1;



--mise a jour d'une chambre 
-- Modifier le prix de base et le type de lit de la chambre 2
UPDATE Chambre
SET prix_base = 180.00, type_lit = 'King+'
WHERE idChambre = 2;



-- mise a jour d'une commodite
-- Changer le prix du Wi-Fi
UPDATE Commodite
SET surplus_prix = 8.00
WHERE description = 'Wi-Fi';



-- mise a jour d'une reservation
-- Corriger les dates et le prix total
UPDATE Reservation
SET date_debut = '2025-07-11', date_fin = '2025-07-13', prix_total = 220.00
WHERE idReservation = 1;



-- Mise à jour des associations (ex: commodités d'une chambre)
-- Remplacer une commodité dans une chambre (ex: chambre 2 n’a plus Jacuzzi mais Wi-Fi seulement)
DELETE FROM Chambre_commodite
WHERE idChambre = 2 AND idCommodite = 2;

  -- Ajouter une nouvelle commodité
INSERT INTO Chambre_commodite (idChambre, idCommodite) VALUES (2, 1);


