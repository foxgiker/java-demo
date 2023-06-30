package com.foxgiker.demo.ziwei;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.foxgiker.demo.ziwei.value.Constant;

public class DemoApp {
    public static void main(String[] args) throws JsonProcessingException {

        Destiny destiny = new Destiny(1987, 5, 18, 4);
        System.out.println(destiny.toJsonString());

        // 查找五行局
        String stem = "癸";
        String branch = "卯";
        String stemParam = "";
        String branchParam ="";
        if("甲乙".contains(stem)){stemParam="甲乙";}
        if("丙丁".contains(stem)){stemParam="丙丁";}
        if("戊己".contains(stem)){stemParam="戊己";}
        if("庚辛".contains(stem)){stemParam="庚辛";}
        if("壬癸".contains(stem)){stemParam="壬癸";}

        if("子丑".contains(branch)){branchParam ="子丑";}
        if("寅卯".contains(branch)){branchParam ="寅卯";}
        if("辰巳".contains(branch)){branchParam ="辰巳";}
        if("午未".contains(branch)){branchParam ="午未";}
        if("申酉".contains(branch)){branchParam ="申酉";}
        if("戌亥".contains(branch)){branchParam ="戌亥";}

        String destinyEnv = Constant.FIVE_ELEMENT_ENV_DICT.get(stemParam).get(branchParam);


        System.out.println(destinyEnv);


    }
}