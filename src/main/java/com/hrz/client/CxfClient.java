package com.hrz.client;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

/**
 * @author Administrator
 * @since 2019-12-29 13:30
 */
public class CxfClient {

    public static void invok() throws Exception {

        JaxWsDynamicClientFactory factory = JaxWsDynamicClientFactory.newInstance();
        Client client = factory.createClient("http://127.0.0.1:8088/cxf/personService?wsdl");

        Object[] objects = client.invoke("getPersonName", Long.valueOf(1L));
        System.out.println(objects[0]);
    }

    public static void main(String[] args) throws Exception {
        CxfClient.invok();
    }
}
