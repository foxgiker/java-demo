package com.foxgiker.demo.ziwei;


import com.foxgiker.demo.ziwei.value.StarCateGoryEnum;
import lombok.Builder;
import lombok.Data;
import com.foxgiker.demo.ziwei.value.StarLuckEnum;

@Data
@Builder
public class Star {

    private String name;

    // 类型： 灾星、吉星
    private StarLuckEnum luck;

    // 种类： 紫薇、天府、年干、年支、日系、时系统
    private StarCateGoryEnum category;


}
