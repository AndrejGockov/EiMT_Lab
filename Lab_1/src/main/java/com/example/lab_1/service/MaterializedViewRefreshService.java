package com.example.lab_1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service

@RequiredArgsConstructor
public class MaterializedViewRefreshService {
    private final JdbcTemplate jdbcTemplate;

//     once a day at 2AM
    @Scheduled(cron = "0 0 2 * * *")
//    @Scheduled(cron = "0 * * * * *")
    public void refreshAccommodationStatsView() throws SQLException {
        jdbcTemplate.execute("REFRESH MATERIALIZED VIEW accommodation_stats_mv");
    }
}