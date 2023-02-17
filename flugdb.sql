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
