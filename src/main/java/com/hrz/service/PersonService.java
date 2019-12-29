package com.hrz.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(targetNamespace = "http://service.hrz.com")
public interface PersonService {

    /**
     * @Author Administrator
     * @Description //TODO
     * @Date 11:18 2019-12-29
     * @Param id
     * @return name
     **/
    @WebMethod
    String getPersonName(@WebParam(name = "id") Long id);

}
