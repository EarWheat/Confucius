package com.education.confucius.Factory;

import com.education.confucius.Entity.CancelEvent.CancelEvent;
import com.education.confucius.Entity.Event.Event;
import com.education.confucius.Entity.Event.EventEnum;
import com.education.confucius.Entity.My.Gem.Gem;
import com.education.confucius.Entity.My.Gem.GemEnum;
import com.education.confucius.Entity.RejectEvent.RejectEvent;

/**
 * @author liuzhaoluliuzhaolu
 * @date 2021/2/22 下午4:47
 * @desc 宝石工厂类
 * @prd
 * @Modification History:
 * Date         Author          Description
 * ------------------------------------------ *
 */
public class GemFactory {
    public static Gem getGem(String gemName){
        if(gemName.equals(GemEnum.BlackGem.name)){
            return new Gem(GemEnum.BlackGem.name,GemEnum.BlackGem.purchasePrice);
        } else if(gemName.equals(GemEnum.RedGem.name)){
            return new Gem(GemEnum.RedGem.name,GemEnum.RedGem.purchasePrice);
        }
        return null;
    }
}
