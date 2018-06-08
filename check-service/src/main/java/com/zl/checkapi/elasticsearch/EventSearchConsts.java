package com.zl.checkapi.elasticsearch;

public class EventSearchConsts {

	public static final int SIZE_MAX = 1_000;// 千
	public static final int SIZE_DEFAULT = 100;
	public static final int SIZE_COUNT = 0;
	public static final int FORM_MAX = 1_000_000;// 百万

	public enum SortType {
		ASC, DESC, NO
	}

	public enum EventItem {
		event,rule,strategy
	}

	public enum DateHistogramInterval {
		HOUR, DAY, WEEK, MONTH, QUARTER
	};

}

