/*  
    Það þarf að uppfæra hvernig við ætlum að halda utanum prev flights og bookings
    UsierID er int fyrir Kt. spurning hvort að við viljum halda því eða gera random
    string id.
*/
CREATE TABLE User (
    user_id INT,
    name VARCHAR(30),
    prev_flights VARCHAR(30),
    prev_bookings VARCHAR(30)
);

/* Gæti þurft að laga date */
/* Tumi: tók út seats úr single flight, setti frekar Seat upp með primary key sem notar flight_id og position, þannig hægt að reffa það frekar*/
CREATE TABLE Single_flight (
    flight_id VARCHAR(30) PRIMARY KEY,
    departure_address VARCHAR(50),
    arrival_address VARCHAR(50),
    departure_time DATE,
    arrival_time DATE,
    price INT
);

/*Tumi: svipað hér og með Single_flight, tók út vísun í Seat num, þá getum við haft mörg sæti fyrir hverja bókun, vitnar bara í Seat.booking_id til að fá upp öll sæti tengd þeirri bókun*/
CREATE TABLE Bookings (
    userid VARCHAR(30) REFERENCES User(id),
    flight_id VARCHAR(30) REFERENCES Single_flight(flight_id),
    booking_id VARCHAR(30),
    seat_id VARCHAR(3) /* T.d. 32A */
);

CREATE TABLE Seat (
    f_id VARCHAR(30) REFERENCES Single_flight(flight_id), 
    position VARCHAR(3),
    reserved BOOLEAN,
    booking_id VARCHAR(30) REFERENCES Bookings(booking_id),
    PRIMARY KEY (f_id, position)
);

