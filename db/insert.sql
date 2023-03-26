INSERT INTO Users (
  userId,
  name
) VALUES ( "1010012260", "Tumi"), ("2002203459", "Gunnar"), ("2404016960", "Hákon"), ("3002223010", "Ari Gunnar");

INSERT INTO Flight (
    flightId,
    departureAddress,
    arrivalAddress,
    departureTime,
    arrivalTime,
    price
) VALUES ("F123", "EGS", "AKR", "2023-02-05 17:00", "2023-02-05 18:00", 30000),
         ("F321", "RVK", "ÍSF", "2023-04-05 12:00", "2023-04-05 14:00", 25000);

INSERT INTO Bookings (
    userId,
    flightId,
    bookingId
) VALUES (1010012260, "F123", "Book1");

INSERT INTO Seats (
    flightId,
    position,
    reserved,
    bookingId
) VALUES ("F123", "A3", "True", "Book1");
