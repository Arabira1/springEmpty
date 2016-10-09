package com.spring.Service;

import com.spring.Dao.Test2Dao;
import com.spring.Dao.TestDao;
import com.spring.Entity.Note2Entity;
import com.spring.Entity.NoteEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Arabira on 2016/9/22.
 */
@Service
@Transactional
public class TestService {
    @Autowired
    private Test2Dao dao2;
    @Autowired
    private TestDao dao;

    public void addToTest(NoteEntity noteEntity) {
        dao.add(noteEntity);
    }

    public NoteEntity findFromTest(int id) {
        return dao.find(id);
    }

    public Note2Entity[] findFromTest2(int id) {
        return dao2.find2(id);
    }

}
