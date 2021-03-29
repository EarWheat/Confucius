package com.education.confucius.Dao.My;

import com.education.confucius.Entity.My.Medicine.MedicineSellingRecord;
import org.apache.ibatis.annotations.Param;

/**
 * @author liuzhaoluliuzhaolu
 * @date 2021/3/24 下午5:34
 * @desc
 * @prd
 * @Modification History:
 * Date         Author          Description
 * ------------------------------------------ *
 */
public interface MedicineMapper extends ItemMapper{
    Boolean addRecord(MedicineSellingRecord medicineSellingRecord);
}
