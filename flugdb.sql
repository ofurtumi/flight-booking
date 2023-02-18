/*  
    Það þarf að uppfæra hvernig við ætlum að halda utanum prev flights og bookings
    UsierID er int fyrir Kt. spurning hvort að við viljum halda því eða gera random
    string id.
*/
CREATE TABLE User(
    Userid INT,
    name VARCHAR(30),
    prev_flights VARCHAR(30),
    prev_bookings VARCHAR(30)
)

CREATE TABLE Seat(
    id VARCHAR(3),
    reserved BOOLEAN
)
/* Gæti þurft að laga date */
CREATE TABLE SingleFlight(
    fid VARCHAR(30) PRIMARY KEY,
    seats VARCHAR(3) REFERENCES Seats(seats),
    depAddress VARCHAR(50),
    arriAddress VARCHAR(50),
    depTime DATE,
    arriTime DATE,
    price INT
)

CREATE TABLE Bookings(
    userid VARCHAR(30) REFERENCES User(id),
    flightid VARCHAR(30) REFERENCES SingleFlight(fid),
    seatnum VARCHAR(3) REFERENCES Seat(id),
    bookingid VARCHAR(30),
    passenger INT
)