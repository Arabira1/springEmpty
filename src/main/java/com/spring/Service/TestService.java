package com.spring.Service;

import com.spring.Dao.TestDao;
import com.spring.Entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Arabira on 2016/9/29.
 */
@Service
@Transactional
public class TestService {
    @Autowired
    private TestDao dao;

    public boolean addUser(TestEntity entity) {

            boolean result = dao.addUser(entity.getUserName(), entity.getPassword());
            return result;
    }

    public TestEntity findUser(int id) {
        return dao.findUser(id);
    }
}
