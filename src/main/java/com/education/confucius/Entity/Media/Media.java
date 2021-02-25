package com.education.confucius.Entity.Media;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * @author liuzhaoluliuzhaolu
 * @date 2021/2/24 下午10:49
 * @desc
 * @prd
 * @Modification History:
 * Date         Author          Description
 * ------------------------------------------ *
 */
@Data
public class Media implements Serializable {
    private static final long serialVersionUID = -5096481192351766311L;
    @JSONField
    public String userName;
    @JSONField
    public String mediaId;
    @JSONField
    public String resource;
    @JSONField
    private String createTime;
    @JSONField
    private String updateTime;
}
