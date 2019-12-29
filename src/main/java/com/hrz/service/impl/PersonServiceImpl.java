package com.hrz.service.impl;

import javax.jws.WebService;

import org.springframework.stereotype.Service;

import com.hrz.service.PersonService;

/**
 * @author Administrator
 * @since 2019-12-29 10:49
 */
@Service
@WebService(serviceName = "PersonService", targetNamespace = "http://service.hrz.com",
    endpointInterface = "com.hrz.service.PersonService")
public class PersonServiceImpl implements PersonService {

    @Override
    public String getPersonName(Long id) {

        return "xiaoming";

    }
}
