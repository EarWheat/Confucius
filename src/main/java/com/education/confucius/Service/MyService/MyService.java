package com.education.confucius.Service.MyService;

import com.alibaba.fastjson.JSONObject;
import com.education.confucius.Entity.My.Flag.Flag;
import com.education.confucius.Entity.My.Flag.FlagRequest;
import com.education.confucius.Entity.My.Gem.GemRequest;
import com.education.confucius.Entity.My.Profits;
import com.education.confucius.Entity.My.Summary;
import org.springframework.stereotype.Service;

import java.util.Properties;

/**
 * @author liuzhaoluliuzhaoluy
 * @date 2021/3/17 下午7:53
 * @desc 梦幻西游服务类
 * @prd
 * @Modification History:
 * Date         Author          Description
 * ------------------------------------------ *
 */
@Service
public interface MyService {
    JSONObject calculateGemProfits(GemRequest gemRequest);
    JSONObject calculateFlagProfits(Flag flag);
    Boolean recordSelling(FlagRequest flagRequest);
}
