package com.foxgiker.demo.ziwei.value;

import com.foxgiker.demo.ziwei.node.CircleLinkedList;
import com.foxgiker.demo.ziwei.node.CircleNode;

import java.util.HashMap;
import java.util.Map;

public class Constant {

    // 十二宫，以命宫为起点，逆时针按照顺排布
    public static final CircleLinkedList<CircleNode> PALACE_NAMES = CircleLinkedList.of(new CircleNode[]{
            new CircleNode("命宫"),
            new CircleNode("兄弟宫"),
            new CircleNode("夫妻宫"),
            new CircleNode("子女宫"),
            new CircleNode("财帛宫"),
            new CircleNode("疾厄宫"),
            new CircleNode("迁移宫"),
            new CircleNode("交友宫"),
            new CircleNode("事业宫"),
            new CircleNode("田宅宫"),
            new CircleNode("福德宫"),
            new CircleNode("父母宫")
    });



            //{"命宫","兄弟宫","夫妻宫","子女宫","财帛宫","疾厄宫","迁移宫","交友宫","事业宫","田宅宫","福德宫","父母宫"};

    // 天干
    public static final CircleLinkedList STEMS = CircleLinkedList.of(new CircleNode[]{
            new CircleNode("甲"),
            new CircleNode("乙"),
            new CircleNode("丙"),
            new CircleNode("丁"),
            new CircleNode("午"),
            new CircleNode("己"),
            new CircleNode("庚"),
            new CircleNode("辛"),
            new CircleNode("壬"),
            new CircleNode("癸"),

    });

    // 地支
    public static final CircleLinkedList BRANCHES = CircleLinkedList.of(new CircleNode[]{
            new CircleNode("子"),
            new CircleNode("丑"),
            new CircleNode("寅"),
            new CircleNode("卯"),
            new CircleNode("辰"),
            new CircleNode("巳"),
            new CircleNode("午"),
            new CircleNode("未"),
            new CircleNode("申"),
            new CircleNode("酉"),
            new CircleNode("戌"),
            new CircleNode("亥")
    });


    public static final Map<String, Map<String,String>> FIVE_ELEMENT_ENV_DICT = new HashMap<>(){{
        put("甲乙", new HashMap<>(){{
            put("子丑","金四局");
            put("寅卯","水二局");
            put("辰巳","火六局");
            put("午未","金四局");
            put("申酉","水二局");
            put("戌亥","火六局");
        }});
        put("丙丁", new HashMap<>(){{
            put("子丑","水二局");
            put("寅卯","火六局");
            put("辰巳","土五局");
            put("午未","水二局");
            put("申酉","火六局");
            put("戌亥","土五局");
        }});
        put("戊己", new HashMap<>(){{
            put("子丑","火六局");
            put("寅卯","土五局");
            put("辰巳","木三局");
            put("午未","火六局");
            put("申酉","土五局");
            put("戌亥","木三局");
        }});
        put("庚辛", new HashMap<>(){{
            put("子丑","土五局");
            put("寅卯","木三局");
            put("辰巳","金四局");
            put("午未","土五局");
            put("申酉","木三局");
            put("戌亥","金四局");
        }});
        put("壬癸", new HashMap<>(){{
            put("子丑","木三局");
            put("寅卯","金四局");
            put("辰巳","水二局");
            put("午未","木三局");
            put("申酉","金四局");
            put("戌亥","水二局");
        }});

    }};

}
