-- Countries
INSERT INTO country (name, continent)
VALUES ('North Macedonia', 'Europe'),
       ('Germany', 'Europe'),
       ('USA', 'North America');

-- Hosts
INSERT INTO host (name, surname, country_id)
VALUES ('John', 'Doe', 1),
       ('Jane', 'Smith', 2),
       ('Bob', 'Johnson', 3);

-- Accommodations
INSERT INTO accommodation (name, category, condition, rented, num_rooms, host_id)
VALUES ('Cozy Room in Skopje', 'ROOM', 'GOOD', false, 1, 1),
       ('Beautiful Flat in Berlin', 'FLAT', 'GOOD', false, 3, 2),
       ('Rustic House in Montana', 'HOUSE', 'BAD', false, 5, 3);