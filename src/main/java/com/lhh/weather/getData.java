//package com.lhh.weather;
//
//
//import java.io.FileNotFoundException;
//
//import java.io.IOException;
//
//import java.io.InputStream;
//
//import java.io.FileInputStream;
//
//import java.net.MalformedURLException;
//
//import java.net.URL;
//
//import java.util.HashMap;
//
//import java.util.Map;
//
//import javax.xml.parsers.DocumentBuilder;
//
//import javax.xml.parsers.DocumentBuilderFactory;
//
//import javax.xml.parsers.ParserConfigurationException;
//
//import org.w3c.dom.Document;
//
//import org.w3c.dom.Element;
//
//import org.w3c.dom.Node;
//
//import org.w3c.dom.NodeList;
//
//import org.xml.sax.SAXException;
//
//
//
//public class getData {
//    /**
//
//     * 解析xml文档，包括本地文档和url
//
//     * @author cyxl
//
//     * @version 1.0 2012-05-24
//
//     * @since 1.0
//
//     *
//
//     */
//}
//
//
//
//
//
//        4)测试代码如下
//
//        publicstaticvoidmain(String[] args) {
//        String link = "http://php.weather.sina.com.cn/xml.php?city=%D6%D8%C7%EC&password=DJOYnieT8234jlsK&day=0";
//
//        URL url;
//
//        String path = "test.xml";
//
//        try{
//        url = newURL(link);
//
//        System.out.println(url);
//
//// InputStream inStream= url.openStream();
//
//// InputStream inStream=new FileInputStream(new File("test.xml"));
//
//        XmlParser parser = newXmlParser(url);
//
//        String[] nodes = {"status1","temperature1","temperature2"};
//
//        Map map = parser.getValue(nodes);
//
//        System.out.println(map.get(nodes[0]));
//
//        } catch(MalformedURLException e) {
//        e.printStackTrace();
//
//        }
//
//        }
//        ————————————————
//        版权声明：本文为CSDN博主「mjchen404」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
//        原文链接：https://blog.csdn.net/weixin_42524226/article/details/114733300packagecom.quickmanager.util;
//
//        importjava.io.FileNotFoundException;
//
//        importjava.io.IOException;
//
//        importjava.io.InputStream;
//
//        importjava.io.FileInputStream;
//
//        importjava.net.MalformedURLException;
//
//        importjava.net.URL;
//
//        importjava.util.HashMap;
//
//        importjava.util.Map;
//
//        importjavax.xml.parsers.DocumentBuilder;
//
//        importjavax.xml.parsers.DocumentBuilderFactory;
//
//        importjavax.xml.parsers.ParserConfigurationException;
//
//        importorg.w3c.dom.Document;
//
//        importorg.w3c.dom.Element;
//
//        importorg.w3c.dom.Node;
//
//        importorg.w3c.dom.NodeList;
//
//        importorg.xml.sax.SAXException;
//
///**
//
// * 解析xml文档，包括本地文档和url
//
// * @author cyxl
//
// * @version 1.0 2012-05-24
//
// * @since 1.0
//
// *
//
// */
//
//        publicclassXmlParser {
//        InputStream inStream;
//
//        Element root;
//
//        publicInputStream getInStream() {
//        returninStream;
//
//        }
//
//        publicvoidsetInStream(InputStream inStream) {
//        this.inStream = inStream;
//
//        }
//
//        publicElement getRoot() {
//        returnroot;
//
//        }
//
//        publicvoidsetRoot(Element root) {
//        this.root = root;
//
//        }
//
//        publicXmlParser() {
//        }
//
//        publicXmlParser(InputStream inStream) {
//        if(inStream !=null) {
//        this.inStream = inStream;
//
//        DocumentBuilderFactory domfac = DocumentBuilderFactory
//
//        .newInstance();
//
//        try{
//        DocumentBuilder domBuilder = domfac.newDocumentBuilder();
//
//        Document doc = domBuilder.parse(inStream);
//
//        root = doc.getDocumentElement();
//
//        } catch(ParserConfigurationException e) {
//        e.printStackTrace();
//
//        } catch(SAXException e) {
//        e.printStackTrace();
//
//        } catch(IOException e) {
//        e.printStackTrace();
//
//        }
//
//        }
//
//        }
//
//        publicXmlParser(String path) {
//        InputStream inStream = null;
//
//        try{
//        inStream = newFileInputStream(path);
//
//        } catch(FileNotFoundException e1) {
//        e1.printStackTrace();
//
//        }
//
//        if(inStream !=null) {
//        this.inStream = inStream;
//
//        DocumentBuilderFactory domfac = DocumentBuilderFactory
//
//        .newInstance();
//
//        try{
//        DocumentBuilder domBuilder = domfac.newDocumentBuilder();
//
//        Document doc = domBuilder.parse(inStream);
//
//        root = doc.getDocumentElement();
//
//        } catch(ParserConfigurationException e) {
//        e.printStackTrace();
//
//        } catch(SAXException e) {
//        e.printStackTrace();
//
//        } catch(IOException e) {
//        e.printStackTrace();
//
//        }
//
//        }
//
//        }
//
//        publicXmlParser(URL url) {
//        InputStream inStream = null;
//
//        try{
//        inStream = url.openStream();
//
//        } catch(IOException e1) {
//        e1.printStackTrace();
//
//        }
//
//        if(inStream !=null) {
//        this.inStream = inStream;
//
//        DocumentBuilderFactory domfac = DocumentBuilderFactory
//
//        .newInstance();
//
//        try{
//        DocumentBuilder domBuilder = domfac.newDocumentBuilder();
//
//        Document doc = domBuilder.parse(inStream);
//
//        root = doc.getDocumentElement();
//
//        } catch(ParserConfigurationException e) {
//        e.printStackTrace();
//
//        } catch(SAXException e) {
//        e.printStackTrace();
//
//        } catch(IOException e) {
//        e.printStackTrace();
//
//        }
//
//        }
//
//        }
//
///**
//
// *
//
// * @param nodes
//
// * @return 单个节点多个值以分号分隔
//
// */
//
//        publicMap getValue(String[] nodes) {
//        if(inStream ==null|| root==null) {
//        returnnull;
//
//        }
//
//        Map map = newHashMap();
//
//// 初始化每个节点的值为null
//
//        for(inti =0; i
//
//        map.put(nodes[i], null);
//
//        }
//
//// 遍历第一节点
//
//        NodeList topNodes = root.getChildNodes();
//
//        if(topNodes !=null) {
//        for(inti =0; i
//
//        Node book = topNodes.item(i);
//
//        if(book.getNodeType() == Node.ELEMENT_NODE) {
//        for(intj =0; j
//
//        for(Node node = book.getFirstChild(); node !=null; node = node
//
//        .getNextSibling()) {
//        if(node.getNodeType() == Node.ELEMENT_NODE) {
//        if(node.getNodeName().equals(nodes[j])) {
////String val=node.getFirstChild().getNodeValue();
//
//        String val = node.getTextContent();
//
//        System.out.println(nodes[j] + ":"+ val);
//
//// 如果原来已经有值则以分号分隔
//
//        String temp = map.get(nodes[j]);
//
//        if(temp !=null&& !temp.equals("")) {
//        temp = temp + ";"+ val;
//
//        } else{
//        temp = val;
//
//        }
//
//        map.put(nodes[j], temp);
//
//        }
//
//        }
//
//        }
//
//        }
//
//        }
//
//        }
//
//        }
//
//        returnmap;
//
//        }
//
//        }
//
//        4)测试代码如下
//
//        publicstaticvoidmain(String[] args) {
//        String link = "http://php.weather.sina.com.cn/xml.php?city=%D6%D8%C7%EC&password=DJOYnieT8234jlsK&day=0";
//
//        URL url;
//
//        String path = "test.xml";
//
//        try{
//        url = newURL(link);
//
//        System.out.println(url);
//
//// InputStream inStream= url.openStream();
//
//// InputStream inStream=new FileInputStream(new File("test.xml"));
//
//        XmlParser parser = newXmlParser(url);
//
//        String[] nodes = {"status1","temperature1","temperature2"};
//
//        Map map = parser.getValue(nodes);
//
//        System.out.println(map.get(nodes[0]));
//
//        } catch(MalformedURLException e) {
//        e.printStackTrace();
//
//        }
//        }