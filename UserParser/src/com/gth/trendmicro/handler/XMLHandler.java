package com.gth.trendmicro.handler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.gth.trendmicro.model.User;

public class XMLHandler {

	/* the CRLF characters are achieved from system */
	final public static String LN = System.getProperty("line.separator");

	/* parse an element to object by reflection */
	public static <T> T parseObject(Element element, Class<T> cls)
			throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchFieldException, InstantiationException {

		T obj = cls.newInstance();

		@SuppressWarnings("unchecked")
		Iterator<Element> it = element.elementIterator();

		while (it.hasNext()) {

			Element userFieldElement = it.next();
			String fieldName = userFieldElement.getName();
			Field field = cls.getDeclaredField(fieldName);
			/* setter methods are used */
			String setMethodName = "set" + Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);
			Method method = cls.getDeclaredMethod(setMethodName, field.getType());
			String stringValue = userFieldElement.getStringValue();
			Object value = null;

			/* for basic data types */
			if (field.getType().equals(Integer.class) || field.getType().equals(int.class))
				value = Integer.parseInt(stringValue);
			else if (field.getType().equals(Short.class) || field.getType().equals(short.class))
				value = Short.parseShort(stringValue);
			else if (field.getType().equals(Long.class) || field.getType().equals(long.class))
				value = Long.parseLong(stringValue);
			else if (field.getType().equals(Byte.class) || field.getType().equals(byte.class))
				value = Byte.parseByte(stringValue);
			else if (field.getType().equals(Character.class) || field.getType().equals(char.class))
				value = stringValue.charAt(0);
			else if (field.getType().equals(Boolean.class) || field.getType().equals(boolean.class))
				value = Boolean.parseBoolean(stringValue);
			else if (field.getType().equals(Float.class) || field.getType().equals(float.class))
				value = Float.parseFloat(stringValue);
			else if (field.getType().equals(Double.class) || field.getType().equals(double.class))
				value = Double.parseDouble(stringValue);
			else if (field.getType().equals(String.class))
				value = stringValue;

			method.invoke(obj, value);
		}
		return obj;
	}

	/* write the whole collection to the output stream */
	public static <T> BufferedWriter outputObject(Collection<T> collection, OutputStream os, Class<T> cls)
			throws IOException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException {

		/* a buffered writer is used */
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));

		/* full class name with package name, eg. com.gth.trendmicro.model.User */
		String className = cls.getName();
		String[] splitedClassNames = className.split("\\.");
		String simpleClassName = splitedClassNames[splitedClassNames.length - 1];

		bw.write("<" + simpleClassName + "s>" + LN);
		for (T member : collection) {
			bw.write("<" + simpleClassName + ">" + LN);
			for (Field field : member.getClass().getDeclaredFields()) {
				String fieldName = field.getName();
				bw.write("<" + fieldName + ">");
				Object value = member.getClass()
						.getDeclaredMethod("get" + Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1))
						.invoke(member);
				bw.write(value == null ? "null" : value.toString());
				bw.write("</" + fieldName + ">" + LN);
			}
			bw.write("</" + simpleClassName + ">" + LN);
		}
		bw.write("</" + simpleClassName + "s>" + LN);
		bw.flush();
		return bw;
	}

	/* main work to remove repeated elements */
	public boolean run(String xmlFilepath, String outputFilePath) {

		SAXReader reader = new SAXReader();
		Document doc = null;

		try {
			doc = reader.read(new File(xmlFilepath));
		} catch (DocumentException e1) {
			e1.printStackTrace();
		}
		if (doc == null)
			return false;

		Element root = doc.getRootElement();
		@SuppressWarnings("unchecked")
		Iterator<Element> it = root.elementIterator();

		/* a hash set is used */
		Set<User> set = new HashSet<User>();

		while (it.hasNext()) {
			Element userElement = it.next();
			User user = null;
			try {
				user = parseObject(userElement, User.class);
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
			if (user != null)
				set.add(user);
		}

		try {
			outputObject(set, new FileOutputStream(outputFilePath), User.class);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public static void main(String[] args) throws DocumentException {
		boolean r = new XMLHandler().run("resources\\userinfo1.txt", "resources\\output.txt");
		System.out.println(r ? "success" : "failure");
	}
}
