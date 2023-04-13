INSERT INTO Users (
  userId,
  name
  ) VALUES ( '0609013170', 'Tumi'), ('2002203459', 'Gunnar'), ('2404016960', 'Hákon'), ('3002223010', 'Ari Gunnar');

INSERT INTO Flights (
    flightId,
    departureAddress,
    arrivalAddress,
    departureTime,
    arrivalTime,
    price
) VALUES 
  ('F-000', 'Egilsstaðir', 'Akureyri', '2023-02-05', '2023-02-05',15000),
  ('F-001', 'Reykjavík', 'Ísafjörður', '2023-02-05', '2023-02-05', 16000),
  ('F-002', 'Keflavík', 'Vestmannaeyjar', '2023-02-05', '2023-02-05', 17000),
  ('F-003', 'Akureyri', 'Egilsstaðir', '2023-02-06', '2023-02-06', 18000),
  ('F-004', 'Ísafjörður', 'Reykjavík', '2023-02-06', '2023-02-06', 19000),
  ('F-005', 'Vestmannaeyjar', 'Keflavík', '2023-02-06', '2023-02-06', 20000);

INSERT INTO Bookings (
    userId,
    flightId,
    bookingId
) VALUES ("0609013170", 'F-002', 'B-1010012260-000'), ("0609013170", 'F-001', 'B-1010012260-000');

INSERT INTO Seats (
    flightId,
    position,
    reserved
  ) 
  VALUES 
    ('F-000', 'A1', false), ('F-000', 'A2', false), ('F-000', 'B1', false), ('F-000', 'B2', false), ('F-000', 'C1', false), ('F-000', 'C2', false),
    ('F-001', 'A1', false), ('F-001', 'A2', false), ('F-001', 'B1', false), ('F-001', 'B2', false), ('F-001', 'C1', false), ('F-001', 'C2', false),
    ('F-002', 'A1', false), ('F-002', 'A2', false), ('F-002', 'B1', false), ('F-002', 'B2', false), ('F-002', 'C1', false), ('F-002', 'C2', false),
    ('F-003', 'A1', false), ('F-003', 'A2', false), ('F-003', 'B1', false), ('F-003', 'B2', false), ('F-003', 'C1', false), ('F-003', 'C2', false),
    ('F-004', 'A1', false), ('F-004', 'A2', false), ('F-004', 'B1', false), ('F-004', 'B2', false), ('F-004', 'C1', false), ('F-004', 'C2', false),
    ('F-005', 'A1', false), ('F-005', 'A2', false), ('F-005', 'B1', false), ('F-005', 'B2', false), ('F-005', 'C1', false), ('F-005', 'C2', false);