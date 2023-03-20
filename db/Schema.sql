/*
    Svo að þetta sé ekki one hit wonder
*/
DROP TABLE IF EXISTS Users;
DROP TABLE IF EXISTS Flight;
DROP TABLE IF EXISTS Bookings;
DROP TABLE IF EXISTS Seats;

/*
    Það þarf að uppfæra hvernig við ætlum að halda utanum prev flights og bookings
    UsierID er int fyrir Kt. spurning hvort að við viljum halda því eða gera random
    string id.
*/
CREATE TABLE Users (
    userId CHAR(10) PRIMARY KEY,
    name VARCHAR(30)
);

/* Gæti þurft að laga date */
/* Tumi: tók út seats úr single flight, setti frekar Seat upp með primary key sem notar flight_id og position, þannig hægt að reffa það frekar*/
CREATE TABLE Flight (
    flightId VARCHAR(30) PRIMARY KEY,
    departureAddress VARCHAR(50),
    arrivalAddress VARCHAR(50),
    departureTime DATETIME,
    arrivalTime DATETIME,
    price INT
);

/*Tumi: svipað hér og með Single_flight, tók út vísun í Seat num, þá getum við haft mörg sæti fyrir hverja bókun, vitnar bara í Seat.booking_id til að fá upp öll sæti tengd þeirri bókun*/
CREATE TABLE Bookings (
    userId VARCHAR(30) REFERENCES User(id),
    flightId VARCHAR(30) REFERENCES Single_flight(flight_id),
    bookingId VARCHAR(30)
);

CREATE TABLE Seats (
    flightId VARCHAR(30) REFERENCES Single_flight(flight_id),
    position VARCHAR(3),
    reserved BOOLEAN,
    bookingId VARCHAR(30) REFERENCES Bookings(booking_id),
    PRIMARY KEY (flightId, position)
);
