package com.education.confucius.Service.MyService;

import com.alibaba.fastjson.JSONObject;
import com.education.confucius.Constants.Constants;
import com.education.confucius.Dao.My.FlagMapper;
import com.education.confucius.Dao.My.ItemMapper;
import com.education.confucius.Entity.My.BaseExpend;
import com.education.confucius.Entity.My.Flag.Flag;
import com.education.confucius.Entity.My.Flag.FlagInCome;
import com.education.confucius.Entity.My.Flag.FlagSellingRecord;
import com.education.confucius.Entity.My.Gem.Gem;
import com.education.confucius.Entity.My.Gem.GemRequest;
import com.education.confucius.Entity.My.Items.Item;
import com.education.confucius.Entity.My.Profits;
import com.education.confucius.Factory.GemFactory;
import com.education.confucius.Service.GemService.GemService;
import com.education.confucius.Service.MyBaseService.BaseService;
import com.pangu.Http.response.RestResult;
import com.pangu.Http.response.ResultEnum;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author liuzhaoluliuzhaolu
 * @date 2021/3/17 下午7:54
 * @desc
 * @prd
 * @Modification History:
 * Date         Author          Description
 * ------------------------------------------ *
 */
@Service
public class MyServiceImpl extends BaseService implements MyService {

    @Resource
    public FlagMapper flagMapper;

    @Resource
    public ItemMapper itemMapper;

    @Override
    public JSONObject calculateGemProfits() {
        Map<String, Gem> gemList = GemFactory.getAllGem();
        JSONObject gemProfits = new JSONObject();
        try {
            if(Objects.isNull(gemList)){
                return null;
            } else {
                for(Map.Entry<String, Gem> gemEntry : gemList.entrySet()){
                    Gem gem = gemEntry.getValue();
                    Long profits = gem.calProfits();
                    gemProfits.put("name",gem.name());
                    gemProfits.put("level",gem.level());
                    gemProfits.put("profits",profits);
                }
            }
        } catch (Exception e){
            return null;
        }
        return gemProfits;
    }

    /**
     * 按日期统计收入
     * 1、查询当天已经录入的记录
     * @param flag
     * @return
     */
    @Override
    public JSONObject calculateFlagProfits(Flag flag) {
        JSONObject result = new JSONObject();
        Profits profits = new Profits();
        Long singleProfits = flag.sellingPrice - flag.purchasePrice;
        Long totalMYH = flag.nums * singleProfits;
        profits.setMYH(totalMYH);
        result.put("profits",profits);
        return result;
    }

    @Override
    public Boolean recordSelling(FlagSellingRecord flagSellingRecord) {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        flagSellingRecord.setCreateTime(simpleDateFormat.format(date));
        flagSellingRecord.setUpdateTime(simpleDateFormat.format(date));
        flagSellingRecord.setRecordId(UUID.randomUUID().toString().replace("-", ""));
        return flagMapper.addRecord(flagSellingRecord);
    }

    @Override
    public Boolean recordSellingV2(Item item) {
//        Date date = new Date();
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
////        String createTime = simpleDateFormat.format(date);
////        String recordId = UUID.randomUUID().toString().replace("-", "");
//        item.setCreateTime(simpleDateFormat.format(date));
//        item.setUpdateTime(simpleDateFormat.format(date));
//        item.setRecordId(UUID.randomUUID().toString().replace("-", ""));
//        return mapper.addRecord(item);
        return false;
    }


    @Override
    public JSONObject getSummary(Double hour) {
        JSONObject result = new JSONObject();
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String startTime = simpleDateFormat.format(date).concat(" 00:00:00");
        String endTime = simpleDateFormat.format(date).concat(" 23:59:59");
        List<FlagSellingRecord> records = flagMapper.getRecordList(startTime,endTime);
        JSONObject detail = sellingTotal(records);
        if(detail.containsKey("total")){
            result.put("income", FlagInCome.calculateFlagProfits(detail.getInteger("total")));
        }
        result.put("expend", BaseExpend.expend(hour));
        Long profits = result.getLong("income") - result.getLong("expend");
        result.put("profits",profits);
        result.put("sellingDetail",detail);
        return result;
    }

    public JSONObject sellingTotal(List<FlagSellingRecord> records){
        int red = 0; int green = 0; int yellow = 0; int white = 0;int blue = 0;int total = 0;
        for(FlagSellingRecord record : records){
            red += record.getRedFlagNum();
            green += record.getGreenFlagNum();
            yellow += record.getYellowFlagNum();
            white += record.getWhiteFlagNum();
            blue += record.getBlueFlagNum();
        }
        total = red + green + yellow + white + blue;
        JSONObject result = new JSONObject();
        result.put("total",total);result.put("red",red);result.put("green",green);result.put("yellow",yellow);result.put("white",white);result.put("blue",blue);
        return result;
    }
}
