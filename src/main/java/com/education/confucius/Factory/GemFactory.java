package com.education.confucius.Factory;

import com.education.confucius.Entity.CancelEvent.CancelEvent;
import com.education.confucius.Entity.Event.Event;
import com.education.confucius.Entity.Event.EventEnum;
import com.education.confucius.Entity.My.Gem.Gem;
import com.education.confucius.Entity.My.Gem.GemEnum;
import com.education.confucius.Entity.RejectEvent.RejectEvent;
import com.education.confucius.Service.GemService.GemService;
import com.pangu.Base.Context.SpringApplicationContext;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author liuzhaoluliuzhaolu
 * @date 2021/2/22 下午4:47
 * @desc 宝石工厂类，仿造的别人的代码，还是厉害。
 * 1、双重锁的单例模式
 * 2、stream流配置
 * @prd
 * @Modification History:
 * Date         Author          Description
 * ------------------------------------------ *
 */
public class GemFactory {

    private volatile static Map<String, Gem> gemMap;

    public static <T extends Gem> T getGem(String gemName){
        // double check !
        if (Objects.isNull(gemMap)) {

            synchronized (GemFactory.class) {

                if (Objects.isNull(gemMap)) {

                    gemMap = SpringApplicationContext.getBeans(Gem.class)
                            .values()
                            .stream()
                            .collect(Collectors.toMap(Gem::name, Function.identity()));
                }
            }

        }

        return (T) gemMap.get(gemName);
    }

    public static <T extends Gem> Map<String, Gem> getAllGem(){
        if(Objects.isNull(gemMap)){
            synchronized (GemFactory.class){
                if(Objects.isNull(gemMap)){
                    gemMap = SpringApplicationContext.getBeans(Gem.class)
                            .values()
                            .stream()
                            .collect(Collectors.toMap(Gem::name, Function.identity()));
                }
            }
        }

        return gemMap;
    }
}
