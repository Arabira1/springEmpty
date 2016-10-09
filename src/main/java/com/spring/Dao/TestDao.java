package com.spring.Dao;

import com.spring.Entity.NoteEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Arabira on 2016/9/22.
 */
@Repository
public interface TestDao {
    public void add(NoteEntity myEntity);

    public  NoteEntity find(int id);
}