package com.example.Redis;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;


public interface FactoryInterface {


    void filter(String ip)throws Exception;


}
