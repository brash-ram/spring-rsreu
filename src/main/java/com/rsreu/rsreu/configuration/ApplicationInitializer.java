package com.rsreu.rsreu.configuration;

import com.rsreu.rsreu.data.entity.School;
import com.rsreu.rsreu.data.entity.UserInfo;
import com.rsreu.rsreu.data.repository.RoleInfoRepository;
import com.rsreu.rsreu.data.repository.UserRepository;
import com.rsreu.rsreu.enums.RoleEnum;
import com.rsreu.rsreu.utils.InitData;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class ApplicationInitializer implements CommandLineRunner {

    private final JdbcTemplate jdbcTemplate;

    private final RoleInfoRepository roleInfoRepository;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        for (School school : InitData.getInitData()) {
            insertSchool(school);
        }
//        roleInfoRepository.saveAll(InitData.getInitRoleData());
        userRepository.save(
                new UserInfo(
                        null,
                        "admin",
                        passwordEncoder.encode("admin"),
                        Set.of(InitData.getInitRoleData().get(0))
                )
        );
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
