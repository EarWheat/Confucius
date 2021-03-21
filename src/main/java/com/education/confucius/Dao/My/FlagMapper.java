package com.education.confucius.Dao.My;

import com.education.confucius.Entity.My.Flag.Flag;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author liuzhaoluliuzhaolu
 * @date 2021/3/21 下午4:00
 * @desc 导标棋Mapper类
 * @prd
 * @Modification History:
 * Date         Author          Description
 * ------------------------------------------ *
 */
@Mapper
public interface FlagMapper {
    List<Flag> getRecordByCreateTime();
    boolean addRecord();
}
