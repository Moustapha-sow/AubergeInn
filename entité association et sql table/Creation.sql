CREATE TABLE Client (
	idClient SERIAL PRIMARY KEY,
	nom      VARCHAR(255) NOT NULL,
	prenom   VARCHAR(255) NOT NULL,
	age      INTEGER  CHECK (age >= 0)	
);

CREATE TABLE Chambre (
	idChambre   SERIAL  PRIMARY KEY,
	nom_chambre  varchar(255) NOT NULL,
	prix_base    DECIMAL      NOT NULL,
	type_lit     VARCHAR(255) NOT NULL
);

CREATE TABLE Commodite (
	idCommodite SERIAL  PRIMARY KEY,
	description VARCHAR(255) NOT NULL,
	surplus_prix DECIMAL    NOT NULL
);

CREATE TABLE Reservation (
	idReservation   SERIAL PRIMARY KEY,
	date_debut    DATE   NOT NULL,
	date_fin      DATE   NOT NULL,
	prix_total  DECIMAL  NOT NULL,
	idClient Integer     NOT NULL,
	idChambre INTEGER    NOT NULL,
	CONSTRAINT fkClient FOREIGN KEY (idClient) REFERENCES Client(idClient),
	CONSTRAINT fkChambre FOREIGN KEY (idChambre) REFERENCES Chambre(idChambre)
	
);

CREATE TABLE Chambre_commodite (
	idChambre INTEGER,
	idCommodite INTEGER,
	PRIMARY KEY(idChambre, idCommodite),
	CONSTRAINT fk_idChambre FOREIGN KEY (idChambre) REFERENCES Chambre (idChambre),
	CONSTRAINT fk_idCommodite FOREIGN KEY (idCommodite) REFERENCES Commodite (idCommodite)

	
);