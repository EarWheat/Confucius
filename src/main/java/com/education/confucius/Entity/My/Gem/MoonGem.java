package com.education.confucius.Entity.My.Gem;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author liuzhaoluliuzhaolu
 * @date 2021/4/20 下午4:53
 * @desc
 * @prd
 * @Modification History:
 * Date         Author          Description
 * ------------------------------------------ *
 */
@Data
@Component
@ConfigurationProperties(prefix = "my.gem.moon")
public class MoonGem extends Gem {
    @JSONField(name = "name")
    public String name;

    @JSONField(name =  "sellingPrice")
    public Long sellingPrice;

    @JSONField(name = "purchasePrice")
    public Long purchasePrice;

    @JSONField(name = "level")
    public Long level;

}
