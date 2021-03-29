package com.education.confucius.Entity.My.Medicine;

import com.alibaba.fastjson.annotation.JSONField;
import com.education.confucius.Dao.My.ItemMapper;
import com.education.confucius.Entity.My.Items.Item;
import lombok.Data;
import org.apache.ibatis.annotations.Mapper;

import java.io.Serializable;

/**
 * @author liuzhaoluliuzhaolu
 * @date 2021/3/24 下午4:14
 * @desc
 * @prd
 * @Modification History:
 * Date         Author          Description
 * ------------------------------------------ *
 */
@Data
public class MedicineSellingRecord extends Item implements Serializable {
    private static final long serialVersionUID = 4675969175963782844L;
    @JSONField
    public Integer num;

}
