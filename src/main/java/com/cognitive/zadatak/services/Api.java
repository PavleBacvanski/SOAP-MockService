package com.cognitive.zadatak.services;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
//import org.junit.Test;

public class Api {


    public void getMethod() throws Exception{

        File soapRequestFile = new File(".\\request1.xml"); //body from soup ui
        String SOAPAction = "getCustomerDetails";

        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost request = new HttpPost("http://localhost:8088/mockCustomerInformationBinding" + SOAPAction);
        request.addHeader("Content-Type","text/xml");
        request.setEntity(new InputStreamEntity(new FileInputStream(soapRequestFile)));


        CloseableHttpResponse response = client.execute(request);

        //Checking HTTP response status code
        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println("Status code: " +statusCode);

        String content = EntityUtils.toString(response.getEntity());
        System.out.println(content);

	    //Create xml file
        try {
            FileWriter fw = new java.io.FileWriter("customerXML.xml");
            fw.write(content);
            fw.close();
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }

    }
}