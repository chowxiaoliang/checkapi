package com.zl.checkapi.elasticsearch;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EventUtil {

    public static final String EVENT_TEMPLATE_NAME = "his_event";
    public static final String EVENT_SORT_FIELD = "sys_sort_time";
    public static final String EVENT_DATE_FIELD = "sys_date_time";
    public final static DateTimeFormatter DAY_FORMAT = DateTimeFormat.forPattern("yyyyMMdd");
	public final static String RULE_TYPE = "event";
	public final static String STRATEGY_TYPE = "event";

    public static String[] getIndices(EventSearchConsts.EventItem item, String partnerId, long startTime, long endTime){

    		switch (item) {
    		case event:
                return new String[]{getEventIndices(partnerId)};
    		case rule:
    			return getRuleIndices(partnerId, startTime, endTime);
    		case strategy:
    			return getStrategyIndices(partnerId, startTime, endTime);
    		default:
    			break;
    		}
    		return null;

    }
    public static String getEventIndices(String partnerId){
        return EVENT_TEMPLATE_NAME+"_"+partnerId;
    }

    /**
     * elasticsearch 6.x版本之后，事件历史index不再进行按周分表，仅按照合作方进行分表
     * @param partnerId
     * @param startTime
     * @param endTime
     * @return
     */
//    public static String[] getEventIndices(String partnerId, long startTime, long endTime){
//    	String[] weeks = getRangeWeeks(startTime, endTime);
//		if(weeks == null) return new String[]{};
//
//		String[] indeices = new String[weeks.length];
//		for (int i = 0; i < weeks.length; i++) {
//			indeices[i] = getEventIndex(partnerId, weeks[i]);
//		}
//		return indeices;
//    }
    public static String[] getRuleIndices(String partnerId, long startTime, long endTime){
		String[] days = getRangeDays(startTime, endTime);
		if(days == null) return new String[]{};
		String[] indices = new String[days.length];
		for (int i = 0; i < days.length; i++) {
			indices[i] = getRuleIndexDay( days[i]);
		}
		return indices;
    }
    public static String[] getStrategyIndices(String partnerId, long startTime, long endTime){
		String[] days = getRangeDays(startTime, endTime);
		if(days == null) return new String[]{};
		String[] indices = new String[days.length];
		for (int i = 0; i < days.length; i++) {
			indices[i] = getStrategyDay( days[i]);
		}
		return indices;
    }

    public static String[] getRangeWeeks(long startMill,long endMill){

        if(startMill > endMill){
            return null;
        }
        DateTime start = new DateTime(startMill);
        DateTime end = new DateTime(endMill);
        List<String> list = new ArrayList<>();

        String week = null;
        do {
            week = getWeekYear(start);
            list.add(week);
            start = start.plusWeeks(1);
        } while (start.isBefore(end));

        String endWeek = getWeekYear(end);
        if(!endWeek.equals(week)){
            list.add(getWeekYear(end));
        }
        return list.toArray(new String[]{});
    }
    public static String[] getRangeDays(long startMill, long endMill) {

		if (startMill > endMill) {
			return null;
		}
		DateTime start = new DateTime(startMill);
		DateTime end = new DateTime(endMill);
		List<String> list = new ArrayList<>();

		String day = null;
		do {
			day = start.toString(DAY_FORMAT);
			list.add(day);
			start = start.plusDays(1);
		} while (start.isBefore(end));

		String endDay = end.toString(DAY_FORMAT);
		if (!endDay.equals(day)) {
			list.add(endDay);
		}
		return list.toArray(new String[] {});
	}
    public static String getYearMonthDay(DateTime time) {
		return time.toString(DAY_FORMAT);
	}

    public static String getWeekYear(DateTime time){
        return time.getWeekyear()+"_"+time.getWeekOfWeekyear();
    }
    //    public static String getEventIndex(String partnerId,DateTime time){
//        return getEventIndex(partnerId,getWeekYear(time));
//    }
//    public static String getEventIndex(String partnerId,String weekYear){
//        return EVENT_TEMPLATE_NAME+"_"+partnerId+"_"+weekYear;
//    }
    public static String getRuleIndex(String partnerId,DateTime time){
        return getRuleIndex(partnerId,getWeekYear(time));
    }
    public static String getRuleIndex(String partnerId,String weekYear){
        return EVENT_TEMPLATE_NAME+"_rule_"+partnerId+"_"+weekYear;
    }
    public static String getStrategyIndex(String partnerId,DateTime time){
        return getStrategyIndex(partnerId,getWeekYear(time));
    }
    public static String getStrategyIndex(String partnerId,String weekYear){
        return EVENT_TEMPLATE_NAME+"_strategy_"+partnerId+"_"+weekYear;
    }

    public static String getRuleIndexDay(DateTime time) {
		return getRuleIndexDay(getYearMonthDay(time));
	}

	public static String getRuleIndexDay(String yearMonthDay) {
		return EVENT_TEMPLATE_NAME + "_rule_" + yearMonthDay;
	}

	public static String getStrategyDay(DateTime time) {
		return getStrategyDay(getYearMonthDay(time));
	}

	public static String getStrategyDay(String yearMonthDay) {
		return EVENT_TEMPLATE_NAME + "_strategy_"  + yearMonthDay;
	}
    /**
     * 只支持基本类型和String，其他的类型都转化为String
     * @param params
     * @param key
     * @param value
     */
    public static void putMapNotNull(Map<String,Object> params,String key,Object value){

        if(value!=null){

            Class<?> type = value.getClass();
            if(type == BigDecimal.class){
                //会被ES存为String类型
                params.put(key, Double.valueOf(value.toString()));
            }else{
                params.put(key, value);
            }
        }
    }

}
