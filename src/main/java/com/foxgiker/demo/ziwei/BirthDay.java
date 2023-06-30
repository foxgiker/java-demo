package com.foxgiker.demo.ziwei;

import cn.hutool.core.date.ChineseDate;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.foxgiker.demo.ziwei.node.CircleNode;
import com.foxgiker.demo.ziwei.value.Constant;
import lombok.Data;

@Data
public class BirthDay {

    private String yearM;

    private String yearB;

    private String  year;

    private Integer yearInt;


    private String monthM;

    private String monthB;

    private String month;

    private Integer monthInt;


    private String dayM;

    private String dayB;

    private String day;

    private Integer dayInt;

    private String  hourM;

    private String hourB;

    private String hour;

    private Integer hourInt;


    private DateTime date;

    public DateTime getDate(){ return date;}

    BirthDay(Integer y,Integer m, Integer d, Integer h){
        date = DateUtil.parseDate(String.format("%d-%d-%d %d:00:00", y, m, d,h));
        ChineseDate chineseDate = new ChineseDate(date);
        String ymd = chineseDate.getCyclicalYMD();

        yearM = ymd.substring(0,1);
        yearB = ymd.substring(1,2);
        year = ymd.substring(0,2);

        monthM = ymd.substring(3,4);
        monthB = ymd.substring(4,5);
        month = ymd.substring(3,5);

        dayM = ymd.substring(6,7);
        dayB = ymd.substring(7,8);
        day = ymd.substring(6,8);

        hourB = calcHourBranch(h);
        hour = calcHourStemBranch(dayM,hourB);
        hourM = hour.substring(0,1);

        yearInt = chineseDate.getChineseYear();
        monthInt = chineseDate.getMonth();
        dayInt =  chineseDate.getDay();
        hourInt = h;
    }

    public Integer getYearInt(){ return yearInt;}
    public Integer getMonthInt(){ return monthInt;}
    public Integer getDayInt(){ return dayInt;}
    public Integer getHourInt(){ return hourInt;}

    private String calcHourBranch(Integer h){
        if(h>=1 && h<3){return "丑";}
        if(h>=3 && h<5){return "寅";}
        if(h>=5 && h<7){return "卯";}
        if(h>=7 && h<9){return "辰";}
        if(h>=9 && h<11){return "巳";}
        if(h>=11 && h<13){return "午";}
        if(h>=13 && h<15){return "未";}
        if(h>=15 && h<17){return "申";}
        if(h>=17 && h<19){return "酉";}
        if(h>=19 && h<21){return "戌";}
        if(h>=21 && h<23){return "亥";}
        return "子";
    }

    /**
     * 计算时辰的干支：甲己还加甲，乙庚丙作初；丙辛从戊起，丁壬庚子居；戊癸何方发，壬子是真途
     * @param dayStem  日干
     * @param hourBranch 时支
     * @return
     */
    private String calcHourStemBranch(String dayStem, String hourBranch){
        String s = "";
        if("甲乙".contains(dayStem)){ s = "甲";}
        if("乙庚".contains(dayStem)){ s = "丙";}
        if("丙辛".contains(dayStem)){ s = "戊";}
        if("丁壬".contains(dayStem)){ s = "庚";}
        if("戊癸".contains(dayStem)){ s = "壬";}
        CircleNode branch = Constant.BRANCHES.getFirst();
        CircleNode stem = Constant.STEMS.getByData(s);
        while (!branch.equals(hourBranch)){
            branch = branch.next();
            stem = stem.next();
        }
        return stem.data() + hourBranch;
    }

}
