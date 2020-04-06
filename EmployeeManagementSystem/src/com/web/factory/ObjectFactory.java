package com.web.factory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class ObjectFactory {
	static Map<String, Object> beans = new HashMap<String, Object>();
	
	static {
		SAXReader reader = new SAXReader();
		try {
			Document document = reader.read(ObjectFactory.class.getClassLoader().getResourceAsStream("bean.xml"));
			build(document);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
	public static void build(Document document) {
		Element rootElement = document.getRootElement();
		try {
			createBeans(rootElement.element("dao"));
			createBeans(rootElement.element("trans"));
			createBeans(rootElement.element("service"));
			createBeans(rootElement.element("proxy"));
			createBeans(rootElement.element("action"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void createBeans(Element element) throws Exception {
		List<Element> elements = element.elements("bean");
		for (Element ele : elements) {
			Class clazz = Class.forName(ele.attributeValue("class"));
		//	System.out.println("ele.attributeValue(class):"+ele.attributeValue("class"));
			Object bean = clazz.newInstance();
			List<Element> properties = ele.elements("property");
			for (Element property : properties) {
				String id = property.attributeValue("id");
			//	System.out.println("id:::"+id);
				String ref = property.attributeValue("ref");
				Object refObject = beans.get(ref);
			//	System.out.println("refObject:"+refObject);
				Field field = clazz.getDeclaredField(id);
				Method method = clazz.getMethod("set"+id.substring(0,1).toUpperCase()+id.substring(1), 
						field.getType());
				method.invoke(bean, refObject);
			}
//			System.out.println("放进去的:"+ele.attributeValue("id"));
//			System.out.println("--------------");
			beans.put(ele.attributeValue("id"), bean);
		}
	}
	
	public static Object getBean(String name) {
		return beans.get(name);
	}
}
