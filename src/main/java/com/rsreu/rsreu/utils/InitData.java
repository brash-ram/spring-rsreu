package com.rsreu.rsreu.utils;

import com.rsreu.rsreu.data.entity.School;
import com.rsreu.rsreu.enums.SchoolType;

import java.util.ArrayList;
import java.util.List;

public class InitData {

    public static List<School> getInitData() {
        List<School> data = new ArrayList<>();
        data.add(new School(1L, "Школа №3", SchoolType.GENERAL, 300, 20));
        data.add(new School(2L, "Гимназия №1", SchoolType.GYMNASIUM, 500, 50));
        data.add(new School(3L, "Начальная школа им. В.Ф. Уткина", SchoolType.JUNIOR, 200, 30));
        data.add(new School(4L, "Лицей №5", SchoolType.LYCEUM, 100, 20));
        return data;
    }
}
