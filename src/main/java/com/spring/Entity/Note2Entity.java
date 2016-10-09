package com.spring.Entity;

import org.apache.ibatis.type.Alias;

import java.sql.Date;

/**
 * Created by Arabira on 2016/9/27.
 */
//用户自定义数据类型在使用包扫描的时候必须加的注解
@Alias("note2")
public class Note2Entity {
    private String id;
    private int note2;
    private Date date;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getNote2() {
        return note2;
    }

    public void setNote2(int note2) {
        this.note2 = note2;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
