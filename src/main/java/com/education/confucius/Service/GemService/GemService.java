package com.education.confucius.Service.GemService;

import com.alibaba.fastjson.JSONObject;


import java.util.Map;

/**
 * @author liuzhaoluliuzhaolu
 * @date 2021/2/22 下午4:43
 * @desc
 * @prd
 * @Modification History:
 * Date         Author          Description
 * ------------------------------------------ *
 */
public interface GemService {
    String name();
    Map<String, JSONObject> profitsSummary();
}
