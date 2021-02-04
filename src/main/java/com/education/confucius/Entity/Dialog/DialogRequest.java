package com.education.confucius.Entity.Dialog;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NonNull;

/**
 * @author liuzhaoluliuzhaolu
 * @date 2021/2/4 下午4:12
 * @desc 聊天请求
 * @prd
 * @Modification History:
 * Date         Author          Description
 * ------------------------------------------ *
 */
@Data
public class DialogRequest {
    @JSONField
    @NonNull
    private Request request;

    @JSONField
    private String token;

    public DialogRequest() {
    }
}
