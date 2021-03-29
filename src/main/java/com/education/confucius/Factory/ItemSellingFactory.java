package com.education.confucius.Factory;

import com.education.confucius.Dao.My.ItemMapper;
import com.education.confucius.Dao.My.MedicineMapper;
import com.education.confucius.Entity.My.Flag.FlagSellingRecord;
import com.education.confucius.Entity.My.Items.Item;
import com.education.confucius.Entity.My.Items.ItemEnum;
import com.education.confucius.Entity.My.Medicine.MedicineSellingRecord;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author liuzhaoluliuzhaolu
 * @date 2021/3/24 下午4:03
 * @desc 物品工厂类
 * @prd
 * @Modification History:
 * Date         Author          Description
 * ------------------------------------------ *
 */
@Component
public class ItemSellingFactory {
    public static Item getItem(String type){
        if(type.equals(ItemEnum.Flag.name())){
            return new FlagSellingRecord();
        } else if(type.equals(ItemEnum.Medicine.name())){
            return new MedicineSellingRecord();
        }
        return null;
    }
}
