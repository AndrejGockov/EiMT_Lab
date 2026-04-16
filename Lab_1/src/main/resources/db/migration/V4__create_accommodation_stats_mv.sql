-- V4__create_accommodation_stats_mv.sql
CREATE MATERIALIZED VIEW accommodation_stats_mv AS
SELECT
    category,
    COUNT(*) AS total_accommodations,
    SUM(num_rooms) AS total_rooms,
    AVG(num_rooms) AS avg_rooms_per_accommodation
FROM accommodation
GROUP BY category;

-- Optional index for faster refresh
CREATE UNIQUE INDEX idx_accommodation_stats_mv_category ON accommodation_stats_mv (category);