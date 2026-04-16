CREATE OR REPLACE VIEW accommodation_details_view AS
SELECT
    a.id AS accommodation_id,
    a.name AS accommodation_name,
    a.category,
    a.num_rooms,
    CONCAT(h.name, ' ', h.surname) AS host_full_name,
    c.name AS country_name
FROM accommodation a
         JOIN host h ON a.host_id = h.id
         JOIN country c ON h.country_id = c.id;