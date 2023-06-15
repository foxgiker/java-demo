package com.foxgiker.demo.sqltoy.service.impl;


import com.foxgiker.demo.sqltoy.service.TreeStudentService;
import org.sagacity.sqltoy.dao.SqlToyLazyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class TreeStudentServiceImpl implements TreeStudentService {

    @Autowired
    private SqlToyLazyDao dao;

    @Override
    public List<HashMap> queryAllTreeStudents() {
        List<HashMap> queryAllTreeStudents = dao.findBySql("queryAllTreeStudents", null, HashMap.class);
        return queryAllTreeStudents;
    }
}
