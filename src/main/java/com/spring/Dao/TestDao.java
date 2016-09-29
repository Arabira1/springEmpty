package com.spring.Dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.spring.Entity.*;

/**
 * Created by Arabira on 2016/9/29.
 */
@Repository
public class TestDao {
    @Autowired
    private SessionFactory sessionFactory;

    private String hql;

    public boolean addUser(String userName, String password) {
        TestEntity entity = new TestEntity();
        entity.setUserName(userName);
        entity.setPassword(password);
        sessionFactory.getCurrentSession().save(entity);
        return true;
    }

    public TestEntity findUser(int id) {
        hql = "from TestEntity t where t.id = ?";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(hql);
        query.setInteger(0, id);
        return (TestEntity) query.uniqueResult();
    }

    public TestEntity findUser(String userName, String password) {
        hql = "from TestEntity t where t.userName = ? and t.password = ?";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(hql);
        query.setString(0, userName);
        query.setString(1, password);
        return (TestEntity) query.uniqueResult();
    }
}
