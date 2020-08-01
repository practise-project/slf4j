package com.example.test.controller;

import com.example.test.service.OraganizeService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ClowLAY
 * create date 2020/7/14
 */
@RestController
public class OrganizeController {

    @Autowired
    private OraganizeService oraganizeService;

    @RequestMapping("/around")
    public JsonNode around(){

        return oraganizeService.around("小明");
    }

    @RequestMapping("/before")
    public JsonNode before(){

        return oraganizeService.before();
    }
}
