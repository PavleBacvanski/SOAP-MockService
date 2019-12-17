package com.cognitive.zadatak.services;

import org.testng.annotations.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class ReadXml {

    public void ImportXml() {

        try {

            //Database connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/customer", "root", "root");
            Statement stm=con.createStatement();

            //saved response xml file
            File xmlFile = new File("customerXML.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

            NodeList nList = doc.getElementsByTagName("getCustomerDetailsResponse");

            for (int i = 0; i < nList.getLength(); i++) {

                Node nNode = nList.item(i);

                System.out.println("\nCurrent Element :" + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    String name = eElement.getElementsByTagName("firstName").item(0).getTextContent();
                    String lastName = eElement.getElementsByTagName("lastName").item(0).getTextContent();
                    String contactEmail = eElement.getElementsByTagName("contactEmail").item(0).getTextContent();
                    String contactPhone = eElement.getElementsByTagName("contactPhone").item(0).getTextContent();
                    String age = eElement.getElementsByTagName("age").item(0).getTextContent();
                    String maritalStatus = eElement.getElementsByTagName("martialStatus").item(0).getTextContent();
                    String address =  eElement.getElementsByTagName("address").item(0).getTextContent();
                    String city = eElement.getElementsByTagName("city").item(0).getTextContent();

                    //Checking elements
                    System.out.println("First Name : " + name);
                    System.out.println("Last Name : " + lastName);
                    System.out.println("Contact Email : " + contactEmail);
                    System.out.println("Contact Phone : " + contactPhone);
                    System.out.println("Age : " + age);
                    System.out.println("Martial status : " + maritalStatus);
                    System.out.println("Address : " + address);
                    System.out.println("City : " + city);

                    //Insert strings into table information
                    stm.executeUpdate("insert into information(firstName, lastName, address, city, age, martialStatus, contactEmail, contactPhone )" +
                            " values('"+name+"','"+lastName+"','"+address+"', '"+city+"','"+age+"','"+maritalStatus+"','"+contactEmail+"','"+contactPhone+"')");

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
