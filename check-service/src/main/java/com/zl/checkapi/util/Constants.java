/*
 * Copyright (C) 2016-2020 BQS Information Technology Co. Ltd.
 *
 * All right reserved.
 *
 * This software is the confidential and proprietary information of BQS Company of China.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the contract agreement you entered into with BQS inc.
 *
 * Create on 2016年2月26日 上午11:31:33
 */
package com.zl.checkapi.util;

/**
 * 类说明：
 *
 * 详细描述：
 *
 * @author DD.Choi
 * @since 2016年2月26日
 */
public interface Constants {

	int ENABLE = 1;
	int DISABLE = 0;
	String ENABLE_STR = "1";
	String DISABLE_STR = "0";
	String HIT = "1";
	String NOT_HIT = "0";
	String Y = "1";
	String N = "0";
	Integer YES = 1;
	Integer NO = 0;
	String NOT_EXIST = "2";
	String SYS_ERR = "500";
	String TRUE = "true";
	String FALSE = "false";
	String HBASE_QUERY_API_CALLL="hbasequeryapi.call";
	
	public static interface Params{
		
		String FLOW_NO = "flowNo";
		String PARTNER_ID = "partnerId";
		String APP_ID = "appId";
		String VERIFY_KEY = "verifyKey";
		String TOKEN_KEY = "tokenKey";
		String EVNET_TYPE = "eventType";
		String REVIEW_TYPE = "reviewType";
		String REVIEW_RESULT = "reviewResult";
		String LIST_ID = "listId";
		String LIST_DATA = "listData";
		String FAILURE_TIME = "failureTime";
		String OPERATION = "operation";
		String MEMO = "memo";
		String LIST_TYPE = "list";
	}

	public interface contactParams{
		String UNDERLINE = "_";
		String TABLE = "U_PHONE_CONTACT_INFO";
		String TABLE_CONTACT_RELATION_INFO = "U_CONTACT_PHONE_RELATION_INFO";
		String COLUMN_FAMILY_INFO = "INFO";
		String COLUMN_FAMILY_RELATION = "RELATION";
		String CONTACT_LIST = "contactList";
		String NAME = "name";
		String MOBILE = "mobile";
		String CONTACTS = "contacts";
		String CONTACTS_NAME = "contactsName";
		String CONTACTS_MOBILE = "contactsMobile";
		int CONTACTS_NUM = 1000;
		String KAFKA_UPLOAD_CONTACTS_DATA = "kafkaUploadContactsData";

	}

	public interface EventReivew{
		String EVENTREVIEW = "1";
		String EVENTAFTERVIEW = "2";
	}

	//合作方请求业务参数
	interface DataBatchFeedbackReq{
		String EXT_PARAM = "extParam";
		//文件编码类型
		String FILE_CHARSET = "fileCharset";
		//文件数据记录条数
		String RECORDS = "records";
		//主键数据数据列
		String PRIMARY_KEY_COLUMNS = "primaryKeyColumns";
		//扩展参数
		String BIZ_EXT_PARAMS = "bizExtParams";
		//文件
		String FILE = "file";
		//单条数据数据列
		String COLUMNS = "columns";
	}

	/**
	 * 数据反馈审核状态
	 * 数据反馈来源方式（01页面方式上传，02接口方式上传）
	 */
	interface FeedBackReviewStatus{
		Integer REVIEWED = 1;
		Integer UNREVIEWED = 0;
		String FEEDBACK_WEB = "01";
		String FEEDBACK_UNLINE = "02";
	}

	/**
	 * 事件历史高级搜索
	 * eq等于，ne不等于，in包含，not_in不包含，prefix前缀，postfix后缀，lt小于，lte小于等于，gt大于，gte大于等于
	 */
	interface EventHistoryTermsType{
		String EQ = "eq";
		String NE = "ne";
		String IN = "in";
		String NOT_IN = "not_in";
		String PREFIX = "prefix";
		String POSTFIX = "postfix";
		String LT = "lt";
		String LTE = "lte";
		String GT = "gt";
		String GTE = "gte";
	}

	/**
	 * 高级搜索前端条件
	 */
	interface EventHistoryParams{
		String EXTRA_CONDITIONS = "extraConditions";
		String CONDITIONS = "condition";
		String FIELD_NAME = "fieldName";
		String FIELD_VALUE = "fieldValue";
	}

	interface Redis{
		String SET = "collections/set";
		String GET = "get";
	}
}
