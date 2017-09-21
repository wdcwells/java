package com.wdc.learnning.xml;

import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.junit.Test;

public class BeanXMLMapping {
    @Test
    public void testBeanToXml() throws Exception{
        MyBean myBean = new MyBean(1, "str1", 2, new ObjectInner(3));
        JacksonXmlModule module = new JacksonXmlModule();
// and then configure, for example:
        module.setDefaultUseWrapper(false);
        XmlMapper xmlMapper = new XmlMapper(module);
// and you can also configure AnnotationIntrospectors etc here:
        System.out.println(xmlMapper.writeValueAsString(myBean));
    }
}
class MyBean {
    private int int1;
    private String str1;
    private Integer integer1;
    private ObjectInner objectInner;

    public MyBean() {
    }

    public MyBean(int int1, String str1, Integer integer1, ObjectInner objectInner) {
        this.int1 = int1;
        this.str1 = str1;
        this.integer1 = integer1;
        this.objectInner = objectInner;
    }

    public int getInt1() {
        return int1;
    }

    public void setInt1(int int1) {
        this.int1 = int1;
    }

    public String getStr1() {
        return str1;
    }

    public void setStr1(String str1) {
        this.str1 = str1;
    }

    public Integer getInteger1() {
        return integer1;
    }

    public void setInteger1(Integer integer1) {
        this.integer1 = integer1;
    }

    public ObjectInner getObjectInner() {
        return objectInner;
    }

    public void setObjectInner(ObjectInner objectInner) {
        this.objectInner = objectInner;
    }
}

class ObjectInner {
    private int id;

    public ObjectInner() {
    }

    public ObjectInner(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
