package com.education.confucius.Factory;

import com.education.confucius.Entity.My.Flag.FlagSellingRecord;
import com.education.confucius.Entity.My.Items.Item;
import com.education.confucius.Entity.My.Items.ItemEnum;
import com.education.confucius.Entity.My.Medicine.MedicineSellingRecord;

/**
 * @author liuzhaoluliuzhaolu
 * @date 2021/3/24 下午4:03
 * @desc 物品工厂类
 * @prd
 * @Modification History:
 * Date         Author          Description
 * ------------------------------------------ *
 */
public class ItemSellingFactory {
    public static Item getItem(ItemEnum item){
        if(item.equals(ItemEnum.Flag)){
            return new FlagSellingRecord();
        } else if(item.equals(ItemEnum.Medicine)){
            return new MedicineSellingRecord();
        }
        return null;
    }
}
