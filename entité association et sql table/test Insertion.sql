-- Clients
INSERT INTO Client (nom, prenom, age) VALUES ('Dupont', 'Jean', 32);
INSERT INTO Client (nom, prenom, age) VALUES ('Martin', 'Claire', 28);


-- Chambres
INSERT INTO Chambre (nom_chambre, prix_base, type_lit) VALUES ('Chambre Bleue', 100.00, 'Queen');
INSERT INTO Chambre (nom_chambre, prix_base, type_lit) VALUES ('Suite Verte', 150.00, 'King');

-- Commodités
INSERT INTO Commodite (description, surplus_prix) VALUES ('Wi-Fi', 10.00);
INSERT INTO Commodite (description, surplus_prix) VALUES ('Jacuzzi', 25.00);


-- Association commodités et chambres
INSERT INTO Chambre_commodite (idChambre, idCommodite) VALUES (1, 1);  -- Wi-Fi pour Chambre Bleue
INSERT INTO Chambre_commodite (idChambre, idCommodite) VALUES (2, 1);  -- Wi-Fi pour Suite Verte
INSERT INTO Chambre_commodite (idChambre, idCommodite) VALUES (2, 2);  -- Jacuzzi pour Suite Verte

-- Réservation
INSERT INTO Reservation (date_debut, date_fin, prix_total, idClient, idChambre)
VALUES ('2025-07-10', '2025-07-12', 200.00, 1, 1);  -- 2 nuits pour Jean

