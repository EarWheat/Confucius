package com.education.confucius.Service.MyBaseService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author liuzhaoluliuzhaolu
 * @date 2021/3/15 下午8:07
 * @desc
 * @prd
 * @Modification History:
 * Date         Author          Description
 * ------------------------------------------ *
 */
@Service
public class BaseService {

    @Value("${my.card.value:}")
    public Long cardValue;
    /**
     * 计算点卡消耗
     * @param hour
     * @return
     */
    public Long hourExpend(Long hour){
        if(cardValue == 0L || hour == 0L){
            return 0L;
        }
        return cardValue * hour;
    }

    public Long getPhysical(Long physical, Long hour){
        Long perPhysical = physical / 100 + 2;  // 每5分钟
        return perPhysical * 12 * hour;
    }

    public Long getVitality(Long physical, Long hour){
        Long perPhysical = physical / 100 + 2;  // 每5分钟
        return perPhysical * 12 * hour;
    }
}
