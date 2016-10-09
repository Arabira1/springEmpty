package com.spring.Entity;

import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Component;

/**
 * Created by Arabira on 2016/9/22.
 */
//用户自定义数据类型在使用包扫描的时候必须加的注解
@Alias("note")
public class NoteEntity {
    private int id;
    private String note;
    //private Note2Entity entity;
    private Note2Entity[] entities;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Note2Entity[] getEntities() {
        return entities;
    }

    public void setEntities(Note2Entity[] entities) {
        this.entities = entities;
    }
}
