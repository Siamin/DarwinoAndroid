package com.ahmad.darwino.network.mdoels;

import java.util.Date;
import java.util.List;

public class Course {

    public int id, categoryId, price, discount, oldPrice, imageId;
    public String name, nameEn, description, descriptionEn, shortDescription, shortDescriptionEn, Teachers;
    public String startingTime, createdAt, LinkePage="";
    public List<Teacher> teachers;


    public String getTeachers() {
        String resualt = teachers.get(0).fullName;
        for (int i = 1; i < teachers.size(); i++) {
            resualt += ", " + teachers.get(i).fullName;
        }
        return resualt;
    }

}

