package com.lkl.utils;

import cn.hutool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 刘奎亮
 * 第三方接口工具类
 */
@Slf4j
public class IdCardUtil {

    /**
     * 身份证查询
     * @param idcard
     * @return
     */
    public static String getCardToken(String idcard){
        Map<String,Object> param = new HashMap<>();
        // appkey 不允许暴漏在url上
        param.put("appkey","2c7c6073e0a8b8c7");
        param.put("idcard",idcard);
        String tokenStr = "";
        try {
            tokenStr = HttpUtil.post("http://api.jisuapi.com/idcard/query",param);
        }catch (Exception e){
            e.printStackTrace();
            log.error("==========================>{}","第三方接口异常");
        }
        return tokenStr;
    }

    /**
     * 天气预报查询
     */

}
