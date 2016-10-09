package com.spring.Controller;

import com.spring.Entity.Note2Entity;
import com.spring.Entity.NoteEntity;
import com.spring.Service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Arabira on 2016/9/8.
 */
@Controller
public class TestController {
    @Autowired
    private TestService service;
    private Map<String, Object> map = new HashMap<String, Object>();

    @ResponseBody
    @RequestMapping("/add")
    public Map add(NoteEntity noteEntity) {
        service.addToTest(noteEntity);
        map.put("status", "ok");
        map.remove("description");
        return map;
    }

    @ResponseBody
    @RequestMapping("/find1")
    public Map find1(int id) {
        NoteEntity noteEntity = service.findFromTest(id);
        if (noteEntity == null || noteEntity.getNote().equals("")) {
            map.put("status", "error");
            map.remove("description");
        }
        else {
            map.put("status", "ok");
            map.put("description", noteEntity);
        }
        return map;
    }

    @ResponseBody
    @RequestMapping("find2")
    public Map find2(int id) {
        Note2Entity[] note2Entity = service.findFromTest2(id);
        if (note2Entity.length < 1) {
            map.put("status", "error");
            map.remove("description");
        }
        else {
            map.put("status", "ok");
            map.put("description", note2Entity);
        }
        return map;
    }
}
