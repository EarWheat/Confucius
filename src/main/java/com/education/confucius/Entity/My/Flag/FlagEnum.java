package com.education.confucius.Entity.My.Flag;

/**
 * @author liuzhaoluliuzhaolu
 * @date 2021/3/15 下午5:21
 * @desc
 * @prd
 * @Modification History:
 * Date         Author          Description
 * ------------------------------------------ *
 */
public enum FlagEnum {
    CA("RED","CA"),
    CS("GREEN","CS"),
    AL("YELLOW","AL"),
    ZZ("WHITE","ZZ");

    public String color;
    public String location;
    FlagEnum(String color, String location){
        this.color = color;
        this.location = location;
    }

}
