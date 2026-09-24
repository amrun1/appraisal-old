package com.permata.appraisal2.dao.common;

import org.hibernate.criterion.Example;

import java.util.LinkedList;
import java.util.List;

public class ExampleHelper {
	public static Example excludeProperties(Example ex, String... excludedProperties) {
		for (String property : excludedProperties) {
			ex.excludeProperty(property);
		}
		return ex;
	}

	public static Example createExample(Object example, String... excludedProperties) {
		return excludeProperties(Example.create(example), excludedProperties);
	}

	public static String[] stripProperties(String root, String[] prop) {
		List<String> list = new LinkedList<String>();
		String path = root + ".";
		for (String p : prop) {
			int idx = p.indexOf(path);
			if (0 <= idx)
				list.add(p.substring(idx + path.length()));
		}
		return list.toArray(new String[] {});
	}
}