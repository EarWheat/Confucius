package com.education.confucius.Entity.My.Flag;

import com.education.confucius.Entity.My.BaseExpend;
import lombok.Data;

/**
 * @author liuzhaoluliuzhaolu
 * @date 2021/3/15 下午6:14
 * @desc 导标棋请求类
 * @prd
 * @Modification History:
 * Date         Author          Description
 * ------------------------------------------ *
 */
@Data
public class FlagRequest extends BaseExpend {
    public String CA;
    public String AL;
    public String CS;
    public String ZZ;
}
