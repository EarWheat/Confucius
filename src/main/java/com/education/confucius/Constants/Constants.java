package com.education.confucius.Constants;

import com.education.confucius.Entity.My.Flag.FlagSellingRecord;
import com.education.confucius.Entity.My.Items.Item;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liuzhaoluliuzhaolu
 * @date 2021/1/15 下午2:52
 * @desc 常量类
 * @prd
 * @Modification History:
 * Date         Author          Description
 * ------------------------------------------ *
 */
public class Constants {
    public static final Integer HAVE_DUTY = 1;
    public static final Integer NO_DUTY = 0;
    public static final String BAIDU_GET_AUTH_TOKEN_URL = "https://aip.baidubce.com/oauth/2.0/token";
    public static final String BAIDU_GRANT_TYPE = "client_credentials";
    public static final String BAIDU_DIALOG_URL = "https://aip.baidubce.com/rpc/2.0/unit/service/chat";
    public static final String BAIDU_DIALOG_SERVICE_ID = "S46282";
    public static final String BAIDU_DIALOG_SESSION_ID = "session_id";
    public static final String DIALOG_ANSWER_REVEAL = "抱歉，我还不知道该怎么回答你。您可以换一种问法";    // 问题兜底
    public static final String BAIDU_DIALOG_ANSWER_RESULT_LIST = "response_list";
    public static final String BAIDU_DIALOG_ANSWER_ACTION_LIST = "action_list";
    public static final String BAIDU_DIALOG_ANSWER_NEWS_LIST = "news_list";
    public static final String BAIDU_DIALOG_ANSWER_SAY = "say";
    public static final String BAIDU_DIALOG_ANSWER_ACTION_ID = "action_id";
    public static final String BAIDU_DIALOG_ANSWER_NEWS = "news";
    public static final String BAIDU_DIALOG_ANSWER_NEWS_TITLE = "title";
    public static final String BAIDU_DIALOG_ANSWER_CUSTOM_REPLAY = "custom_reply";
    public static final String BAIDU_DIALOG_ANSWER_RESULT = "result";
    public static final String BAIDU_DIALOG_RESPONSE_SESSION = "baidu_response_session";
    public static final String SESSION_CONNECT_TIME ="sessionConnectTimes";
    public static final String DIALOG_REDIS_KEY = "dialog_redis_";
    public static final Long FLAG_SELLING_PRICE = 76666L;
    public static final Long FLAG_PURCHASE_PRICE = 56000L;
    public static final Long CAR_VALUE = 13000L;
}
