package com.example.elasticsearch.controller;

import com.example.elasticsearch.service.EnvTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.Map;

/**
 * @Description 数据接口
 * @Author qinweisi
 * @Date 2019/10/10 10:06
 **/
@RestController
@RequestMapping("/data")
public class DataController {

    @Autowired
    private EnvTestService envTestService;

    @PostMapping(value = "/getDataTem")
    public Map<String, Object> getDataTem(String start, String end, String[] cardId) throws ParseException {
        return envTestService.getDiagram(start, end, cardId);
    }

}
