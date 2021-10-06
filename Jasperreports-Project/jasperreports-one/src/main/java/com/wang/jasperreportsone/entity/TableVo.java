package com.wang.jasperreportsone.entity;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @auther Jeffrey
 * @date 2020/6/12 10:43
 */
public class TableVo {
    private Integer id;
    private String name;
    private Integer age;
    private String sex;
    private String hobby;
    private Timestamp birthday;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Timestamp birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "TableVo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", hobby='" + hobby + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
