package com.hrz.config;

import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hrz.service.PersonService;
import com.hrz.service.impl.PersonServiceImpl;

/**
 * cxf配置
 */
@Configuration
public class CxfConfig {

    @Autowired
    private PersonService personService;

    @Bean
    public ServletRegistrationBean dispatherServlet() {
        return new ServletRegistrationBean(new CXFServlet(), "/cxf/*");
    }

    @Bean(name = Bus.DEFAULT_BUS_ID)
    public SpringBus springBus() {
        return new SpringBus();
    }

    @Bean
    public Endpoint personSeviceEndPoint() {
        EndpointImpl endpointImpl = new EndpointImpl(springBus(), new PersonServiceImpl());
        endpointImpl.publish("/personService");
        return endpointImpl;
    }
}
