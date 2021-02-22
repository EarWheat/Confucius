package com.education.confucius.Service.GemService;

import com.education.confucius.Entity.My.Gem.GemRequest;

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
    Long getProfits(String name, Long sellingPrice, Long level);
}
