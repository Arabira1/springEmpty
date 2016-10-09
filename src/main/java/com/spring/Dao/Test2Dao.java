package com.spring.Dao;

import com.spring.Entity.Note2Entity;
import org.springframework.stereotype.Repository;

/**
 * Created by Arabira on 2016/9/27.
 */
@Repository
public interface Test2Dao {
    public Note2Entity[] find2(int id );
}
