package com.foxgiker.demo.ziwei;


import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class Palace {

    private String name;

    // 宫干
    private String palaceStem;

    // 宫支
    private String palaceBranch;

    // 干支
    private String stemBranch;

    // 是否身宫
    @Builder.Default
    private Boolean isBirthPalace = false;

    // 各种星
    @Builder.Default
    private List<Star> stars = new ArrayList<>();
}
