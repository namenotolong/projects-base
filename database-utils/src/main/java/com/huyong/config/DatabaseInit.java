package com.huyong.config;

import com.huyong.dao.mapper.DataConnectionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DatabaseInit {

    @Autowired
    private DataConnectionMapper dataConnectionMapper;


    @PostConstruct
    public void init() {
        dataConnectionMapper.init();
    }
}
