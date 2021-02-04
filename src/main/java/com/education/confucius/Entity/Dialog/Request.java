package com.education.confucius.Entity.Dialog;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NonNull;

/**
 * @author liuzhaoluliuzhaolu
 * @date 2021/2/4 下午3:39
 * @desc 聊天request
 * @prd
 * @Modification History:
 * Date         Author          Description
 * ------------------------------------------ *
 */
@Data
public class Request {
    @JSONField
    @NonNull
    private String query;

    @JSONField
    @NonNull
    private String user_id;

    public Request() {

    }
}
