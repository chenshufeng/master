package com.csf.config;

import com.csf.constant.DataBaseProperty;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author chenshf
 * @date: 18/8/26 11:56
 * @description:
 */
public class Configer {

    private static ClassLoader loader = Configer.class.getClassLoader();
    private static String driveClass;
    private static String url;
    private static String userName;
    private static String password;

    private static List<Function> list;
    private static String interfaceName;

    static {
        System.out.println("开始加载数据库配置################");
        loadXml("config.xml");
        System.out.println("结束加载数据库配置################");
    }

    /**
     * 加载并解析xml
     *
     * @param xml
     */
    public static void loadXml(String xml) {
        //加载xml
        InputStream inputStream = loader.getResourceAsStream(xml);
        SAXReader saxReader = new SAXReader();
        try {
            Document document = saxReader.read(inputStream);
            elvElement(document);

        } catch (DocumentException e) {
            e.printStackTrace();
        }

    }

    /**
     * 解析xml
     *
     * @param doc
     */
    private static void elvElement(Document doc) {
        Element rootElement = doc.getRootElement();
        if ("database".equals(rootElement.getName())) {
            List<Element> elements = doc.getRootElement().elements("property");
            for (Element element : elements) {
                String name = element.attributeValue("name");
                String value = element.attributeValue("value");
                if (DataBaseProperty.DRIVECLASS.getName().equals(name)) {
                    driveClass = value;
                }
                if (DataBaseProperty.URL.getName().equals(name)) {
                    url = value;
                }
                if (DataBaseProperty.USERNAME.getName().equals(name)) {
                    userName = value;
                }
                if (DataBaseProperty.PASSWORD.getName().equals(name)) {
                    password = value;
                }

            }
        }
        if ("mapper".equals(rootElement.getName())) {
            interfaceName = rootElement.attributeValue("namespace");
            Iterator iterator = rootElement.elementIterator();
            list = new LinkedList<>();
            Function function = null;
            try {
                while (iterator.hasNext()) {
                    function = new Function();
                    Element element = (Element) iterator.next();
                    function.setSqlType(element.getName());
                    function.setFuncName(element.attributeValue("id"));
                    function.setSql(element.getText());
                    String resultType = element.attributeValue("resultType");
                    function.setResultType(Class.forName(resultType).newInstance());
                    list.add(function);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 获得数据库连接
     *
     * @return
     */
    public Connection getConn() {
        Connection conn = null;
        try {
            Class.forName(driveClass);
            conn = DriverManager.getConnection(url, userName, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 根据xml加载mapper
     * @param xmlPath
     * @return
     */
    public MapperBean getMapperBean(String xmlPath) {
        loadXml(xmlPath);
        MapperBean mapperBean = new MapperBean();
        mapperBean.setList(list);
        mapperBean.setInterfaceName(interfaceName);
        return mapperBean;
    }
}
