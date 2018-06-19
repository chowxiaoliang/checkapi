/*
 * Copyright (C) 2014-2017 LS Information Technology Co. Ltd.
 *
 * All right reserved.
 *
 * This software is the confidential and proprietary information of LS Company of China.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the contract agreement you entered into with LS inc.
 *
 * $Id: Constants.java  2015-1-14  ljn $
 *
 * Create on 2015-1-14 下午3:40:03
 *
 * Description:
 *
 */
package com.zl.checkapi.phoenix;

/**
 * 常量类
 */
public interface Constants {

    String CHARSET_UTF8 = "UTF-8";

    /**
     * 白骑士内部异常码规范：
     共8位，英文字母大写
     一位大类系统字母+三位子系统英文缩写+四位数字异常码（具体可以根据内部模块定义）
     如：
     风控系统-决策引擎-认证不通过：RENG1001（1001中1代表一个模块，001代表一个错误码）
     */
    String SYSTEM_NAME = "BHBS";
    String RESULTCODE_SUCESS = SYSTEM_NAME + "0000";
    String RESULTMSG_SUCESS = "查询成功";
    String RESULTCODE_EMPTY = SYSTEM_NAME + "0001";
    String RESULTMSG_EMPTY = "查询结果为空";
    String RESULTCODE_FAILED = SYSTEM_NAME + "0002";
    String RESULTMSG_FAILED = "查询失败";
    String RESULTCODE_INVALID = SYSTEM_NAME + "0003";
    String RESULTMSG_INVALID = "参数校验失败";

    String RESULTCODE_SUCCESS_DATA_LOST = SYSTEM_NAME + "0004";
    String RESULTMSG_SUCCESS_DATA_LOST = "查询成功但是有部分数据没有找到";

    String ATTR_TYPE_FINAL = "final";
    String ATTR_TYPE_SELF = "self";
    String ATTR_TYPE_RELATED = "related";

    String SUCCESS="SUCCESS";
    String FAILED="FAILED";

    /*之前的查询返回码，坑啊*/
    String QUERY_SUCCESS = "1";
    String QUERY_FAILED = "-1";

}
