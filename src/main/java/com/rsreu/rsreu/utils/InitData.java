package com.rsreu.rsreu.utils;

import com.rsreu.rsreu.data.entity.RoleInfo;
import com.rsreu.rsreu.data.entity.School;
import com.rsreu.rsreu.enums.RoleEnum;
import com.rsreu.rsreu.enums.SchoolTypeEnum;

import java.util.ArrayList;
import java.util.List;

public class InitData {

    public static List<School> getInitData() {
        List<School> data = new ArrayList<>();
        data.add(new School(1L, "Школа №3", SchoolTypeEnum.GENERAL, 300, 20));
        data.add(new School(2L, "Гимназия №1", SchoolTypeEnum.GYMNASIUM, 500, 50));
        data.add(new School(3L, "Начальная школа им. В.Ф. Уткина", SchoolTypeEnum.JUNIOR, 200, 30));
        data.add(new School(4L, "Лицей №5", SchoolTypeEnum.LYCEUM, 100, 20));
        return data;
    }

    public static List<RoleInfo> getInitRoleData() {
        List<RoleInfo> roles = new ArrayList<>();
        roles.add(new RoleInfo(1L, RoleEnum.ADMIN));
        roles.add(new RoleInfo(2L, RoleEnum.USER));
        return roles;
    }
}
