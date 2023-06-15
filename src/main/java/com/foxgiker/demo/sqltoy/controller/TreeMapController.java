package com.foxgiker.demo.sqltoy.controller;


import com.foxgiker.demo.sqltoy.service.TreeStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TreeMapController {

    @Autowired
    TreeStudentService treeStudentService;

    @GetMapping("/treemap")
    public List<HashMap> testTreeMap(){
        return treeStudentService.queryAllTreeStudents();
    }
}
