package com.permata.appraisal2.common;

import java.util.Comparator;

public class LabelValue<V, L> implements Comparable<LabelValue>{

	private L label;
	private V value;

	public LabelValue(V value, L label) {
		super();
		this.label = label;
		this.value = value;
	}

	public L getLabel() {
		return label;
	}

	public void setLabel(L label) {
		this.label = label;
	}

	public V getValue() {
		return value;
	}

	public void setValue(V value) {
		this.value = value;
	}
	// // TODO: 1/8/2018 notes add order, easier to find
	@Override
	public int compareTo(LabelValue o) {
		return 0;
	}

	public static Comparator<LabelValue> valueComparator = new Comparator<LabelValue>() {

		public int compare(LabelValue lv1, LabelValue lv2) {

			String value1 = lv1.getValue().toString().toUpperCase();
			String value2 = lv2.getValue().toString().toUpperCase();

			//ascending order
			return value1.compareTo(value2);

			//descending order
			//return fruitName2.compareTo(fruitName1);
		}

	};
	// // 1/8/2018 Added for sorting
}