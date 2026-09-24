package com.permata.appraisal2.dao.common;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.Order;

public class OrderSpecTranslator {
	public static List<Order> asOrders(OrderSpec orderSpec) {
		List<Order> orders = new ArrayList<Order>();
		for (OrderSpec.OrderSpecItem specItem : orderSpec.getSpecItems()) {
			orders.add(specItem.getOrderType() == OrderSpec.OrderType.asc ? Order.asc(specItem.getField())
					: Order.desc(specItem.getField()));

		}
		return orders;
	}

	public static String asString(OrderSpec orderSpec) {
		List<String> stringList = new ArrayList<String>();
		for (OrderSpec.OrderSpecItem specItem : orderSpec.getSpecItems()) {
			StringBuffer buffer = new StringBuffer().append(specItem.getField())
					.append(specItem.getOrderType() == OrderSpec.OrderType.asc ? " ASC " : " DESC ");
			stringList.add(buffer.toString());
		}
		return StringUtils.join(stringList.toArray(new String[] {}), ",");
	}
}
