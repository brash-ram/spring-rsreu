package com.rsreu.rsreu.configuration;

import com.rsreu.rsreu.data.entity.School;
import com.rsreu.rsreu.utils.InitData;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class ApplicationInitializer implements CommandLineRunner {

    private final JdbcTemplate jdbcTemplate;
    @Override
    public void run(String... args) throws Exception {
        for (School school : InitData.getInitData()) {
            insertSchool(school);
        }
    }

    public School insertSchool(School school) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("schools");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("ID", school.getId());
        parameters.put("NAME_SCHOOL", school.getName());
        parameters.put("TYPE_SCHOOL", school.getType().ordinal());
        parameters.put("NUMBER_PLACES_STUDENTS", school.getNumberPlacesStudents());
        parameters.put("NUMBER_PLACES_TEACHERS", school.getNumberPlacesTeachers());

        simpleJdbcInsert.execute(parameters);
        return school;
    }
}
