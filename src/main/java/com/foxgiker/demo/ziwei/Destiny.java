package com.foxgiker.demo.ziwei;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.foxgiker.demo.ziwei.node.CircleLinkedList;
import com.foxgiker.demo.ziwei.node.CircleNode;
import com.foxgiker.demo.ziwei.value.Constant;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Destiny {

    private CircleLinkedList<CircleNode<Palace>> palaces;

    private String eightWord;

    // 五行局
    private String fiveElementEnv;


    @JsonIgnore
    private BirthDay birthDay;


    public String getFiveElementEnv(){return fiveElementEnv;}
    public String getEightWord(){
        return eightWord;
    }

    public Destiny(){};
    Destiny(Integer year,Integer month, Integer day, Integer hour){
        birthDay = new BirthDay(year,month,day,hour);
        eightWord = String.format("%s%s%s%s",birthDay.getYear(),birthDay.getMonth(),birthDay.getDay(),birthDay.getHour());
        initPalaces();
        // 配宫干
        setPalaceStem();
        // 确定十二宫
        fixTwelvePalace();
        // 设置身宫
        fixBodyPalace();
        // 查找五行局
        fixFiveElementEnv();
        // 设置紫微6星、天府8星
        fixZwAnTfStars();

    }

    public BirthDay getBirthDay(){ return birthDay;}

    public CircleLinkedList<CircleNode<Palace>> getPalaces(){ return palaces;}

    private void initPalaces(){
        CircleNode[] ps = new CircleNode[]{
                new CircleNode(Palace.builder().palaceBranch("寅").build()),
                new CircleNode(Palace.builder().palaceBranch("卯").build()),
                new CircleNode(Palace.builder().palaceBranch("辰").build()),
                new CircleNode(Palace.builder().palaceBranch("巳").build()),
                new CircleNode(Palace.builder().palaceBranch("午").build()),
                new CircleNode(Palace.builder().palaceBranch("未").build()),
                new CircleNode(Palace.builder().palaceBranch("申").build()),
                new CircleNode(Palace.builder().palaceBranch("酉").build()),
                new CircleNode(Palace.builder().palaceBranch("戌").build()),
                new CircleNode(Palace.builder().palaceBranch("亥").build()),
                new CircleNode(Palace.builder().palaceBranch("子").build()),
                new CircleNode(Palace.builder().palaceBranch("丑").build())
        };
        palaces = CircleLinkedList.of(ps);
    }
    /**
     * 解析汉字数字
     */
    private Integer parseChineseNumber(String w){
        Integer r = 0;
        switch (w){
            case "一":
                r = 1;
                break;
            case "二":
                r=2;
                break;
            case "三":
                r=3;
                break;
            case "四":
                r=4;
                break;
            case "五":
                r=5;
                break;
            case "六":
                r=6;
                break;
            default:
                r=0;
                break;
        }
        return r;
    }


    /**
     * 根据宫干 查找宫
     * @param b
     * @return
     */
    private CircleNode<Palace> findPalaceByBranch(String b){
        LinkedList<CircleNode<Palace>> list = palaces.getList();
        CircleNode<Palace> first = null;
        for(CircleNode<Palace> node: list){
            var p = node.data();
            if( null != p.getPalaceBranch() && p.getPalaceBranch().equals(b)){
                first = node;
                break;
            }
        }
        return first;
    }

    /**
     * 五虎循月定宫干： 以年干为判断依据， 以寅位为正月
     * 甲己丙作首，乙庚戊为头；
     *
     * 丙辛从庚起，丁壬壬行流；
     *
     * 戊癸何处覓，甲寅好追求。
     */
    private void setPalaceStem(){
        //定正月
        CircleNode<Palace> node = findPalaceByBranch("寅");
        Palace palace = (Palace)node.data();
        // 设正月宫干
        if("甲乙".contains(birthDay.getYearM())){palace.setPalaceStem("丙");}
        if("乙庚".contains(birthDay.getYearM())){palace.setPalaceStem("戊");}
        if("丙辛".contains(birthDay.getYearM())){palace.setPalaceStem("庚");}
        if("丁壬".contains(birthDay.getYearM())){palace.setPalaceStem("壬");}
        if("戊癸".contains(birthDay.getYearM())){palace.setPalaceStem("甲");}
        palace.setStemBranch(palace.getPalaceStem()+ palace.getPalaceBranch());
        // 循环设置剩下的11个宫干
        int i= 2; // 从2月开始
        CircleNode stemNode = Constant.STEMS.getByData(palace.getPalaceStem());
        while(i<13){
            node = node.next();
            stemNode = stemNode.next();
            
            Palace p = node.data();
            p.setPalaceStem(stemNode.data().toString());
            p.setStemBranch(String.format("%s%s",p.getPalaceStem(),p.getPalaceBranch()));
            ++i;
        }
    }

    /**
     * 确定十二宫
     * 确定命宫：
     * 1、找到左下角的寅宫，从寅宫起正月，顺时针方向数到出生月之宫为止；
     * 2、从该宫起子时，逆时针方向数到出生时之宫为止，安命宫。
     */
    private void fixTwelvePalace(){
        //定子时
        CircleNode<Palace> zNode = findPalaceByBranch("寅");
        // 顺时针数到出生月之宫 作为子时
        for (int i = 1; i <birthDay.getMonthInt(); i++) {
            zNode = zNode.next();
        }

        //逆时针从子时 数到时柱地支
        CircleNode zb = Constant.BRANCHES.getFirst();  // 子时
        while(!zb.data().equals(birthDay.getHourB())){
            zb = zb.next();
            zNode = zNode.prev();
        }
        // 设置命宫
        Palace destinyPalace = zNode.data();
        CircleNode nameNode = Constant.PALACE_NAMES.getFirst();
        destinyPalace.setName(nameNode.data().toString());



        // 设置其他宫 : 逆时针
        for (CircleNode<String> t: Constant.PALACE_NAMES.getList()){
            nameNode = t.next();
            zNode = zNode.prev();

            Palace data = zNode.data();
            data.setName(nameNode.data().toString());
        }
    }


    /**
     * 确定身宫
     * 1、找到左下角的寅宫，从寅宫起正月，顺时针方向数到出生月之宫为止；
     * 2、从该宫起子时，顺时针方向数到出生时之宫为止，安命宫。
     */
    private void fixBodyPalace(){
        //定子时
        CircleNode<Palace> zNode = findPalaceByBranch("寅");
        // 顺时针数到出生月之宫 作为子时
        for (int i = 1; i <birthDay.getMonthInt(); i++) {
            zNode = zNode.next();
        }
        zNode.data().setIsBirthPalace(false);

        // 顺时针从子时 数到时柱地支
        CircleNode zb = Constant.BRANCHES.getFirst();  // 子时
        CircleNode bn = Constant.BRANCHES.getFirst();
        for (int i = 2; i <=12; i++) {
            zNode = zNode.next();
            bn = bn.next();
            if(bn.data().equals(birthDay.getHourB())){
                zNode.data().setIsBirthPalace(true);
            }else{
                zNode.data().setIsBirthPalace(false);
            }
        }
    }

    /**
     * 设置五行局
     */
    private void fixFiveElementEnv(){
        String stem ="" ;
        String branch="";
        //找到命宫
        for(CircleNode<Palace> node: palaces.getList()){
            if(node.data().getName().equals("命宫")){
                stem = node.data().getPalaceStem();
                branch = node.data().getPalaceBranch();
            }
        }

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
        fiveElementEnv = Constant.FIVE_ELEMENT_ENV_DICT.get(stemParam).get(branchParam);
    }

    /**
     * 设置紫微6星、天府8星
     * 生日被局数除，如整除，以寅宫起 1，顺数至商数，所落官位即为紫微星所在宫位
     */
    public void fixZwAnTfStars(){
        Star zw = Star.builder().name("紫薇").build();
        Star tj = Star.builder().name("天机").build();
        Star ty = Star.builder().name("太阳").build();
        Star wq = Star.builder().name("武曲").build();
        Star tt = Star.builder().name("天同").build();
        Star lz = Star.builder().name("廉贞").build();


        Integer dayInt = birthDay.getDayInt();
        int pos = 0;
        int divisor = parseChineseNumber(fiveElementEnv.substring(1,2));

        // 先找到寅宫
        CircleNode<Palace> yinNode = null;
        for(CircleNode<Palace> node: palaces.getList()){
            Palace p = node.data();
            if(p.getPalaceBranch().equals("寅")){
                yinNode = node;
                break;
            }
        }
        // 设置紫微星
        CircleNode<Palace> zwNode = null;
        if((dayInt % divisor) ==0){
            pos = dayInt / divisor;
        }else{
            // 如果不能整除，则循环给生日加上一个数
            int step = 0;
            while((dayInt % divisor) !=0){
                ++dayInt;
                ++step;
            }

            if((step % 2)==0){
                // 双数
                pos= (dayInt / divisor) + step;
            }else{
                // 单数
               pos= (dayInt / divisor) - step;
            }

            if(pos ==0){
                // 在丑宫
                for(CircleNode<Palace> node: palaces.getList()){
                    Palace p = node.data();
                    if(p.getPalaceBranch().equals("丑")){
                        p.getStars().add(zw);
                        zwNode = node;
                        break;
                    }
                }
            }else if(pos >0){
                //以寅宫为1 顺时针
                for (int i = 1; i < pos; i++) {
                    yinNode = yinNode.next();
                }
                zwNode = yinNode;
                zwNode.data().getStars().add(zw);
            }else if(pos <0){
                //以寅宫为1 逆时针
                for (int i = -1; i > step; i--) {
                    yinNode = yinNode.prev();
                }
                zwNode = yinNode;
                zwNode.data().getStars().add(zw);
            }
        }

        zwNode = zwNode.prev();
        zwNode.data().getStars().add(tj);

        zwNode = zwNode.prev();
        zwNode = zwNode.prev();
        zwNode.data().getStars().add(ty);

        zwNode = zwNode.prev();
        zwNode.data().getStars().add(wq);

        zwNode = zwNode.prev();
        zwNode.data().getStars().add(tt);

        zwNode = zwNode.prev();
        zwNode = zwNode.prev();
        zwNode = zwNode.prev();
        zwNode.data().getStars().add(lz);

        // 廉贞星后第4个 又回到紫微星
        zwNode = zwNode.prev();
        zwNode = zwNode.prev();
        zwNode = zwNode.prev();
        zwNode = zwNode.prev();
        // 紫微星的对宫即为 天府星
        CircleNode<Palace> tfPalace = findOpposePalace(zwNode);

        Star tfStar = Star.builder().name("天府").build();
        Star tyStar = Star.builder().name("太阴").build();
        Star tlStar = Star.builder().name("贪狼").build();
        Star jmStar = Star.builder().name("巨门").build();
        Star txStar = Star.builder().name("天相").build();
        Star tlgStar = Star.builder().name("天梁").build();
        Star qsStar = Star.builder().name("七杀").build();
        Star pjStar = Star.builder().name("破军").build();

        tfPalace.data().getStars().add(tfStar);
        tfPalace = tfPalace.next();
        tfPalace.data().getStars().add(tyStar);
        tfPalace = tfPalace.next();
        tfPalace.data().getStars().add(tlStar);
        tfPalace = tfPalace.next();
        tfPalace.data().getStars().add(jmStar);
        tfPalace = tfPalace.next();
        tfPalace.data().getStars().add(txStar);
        tfPalace = tfPalace.next();
        tfPalace.data().getStars().add(tlgStar);
        tfPalace = tfPalace.next();
        tfPalace.data().getStars().add(qsStar);
        tfPalace = tfPalace.next();
        tfPalace = tfPalace.next();
        tfPalace = tfPalace.next();
        tfPalace = tfPalace.next();
        tfPalace.data().getStars().add(pjStar);
    }

    /**
     * 寻找对宫
     * @param p
     * @return
     */
    private CircleNode<Palace> findOpposePalace(CircleNode<Palace> p){
        Map<Integer,CircleNode<Palace>> dict = new HashMap<>();
        int pos = 0;
        for (int i = 0; i < palaces.getList().size(); i++) {
            dict.put(i,palaces.getList().get(i));
            String palaceBranch = palaces.getList().get(i).data().getPalaceBranch();
            if(palaceBranch.equals(p.data().getPalaceBranch())){ pos = i;}
        }
        if(pos == 0 || pos == 6){
            return dict.get(pos);   // 甲申 同宫
        }else{
            return dict.get(12-pos);
        }
    }

    /**
     * 根据紫微星 设置天府八星
     * 天府星在紫薇星的对宫
     */
    private void fixTfStars(CircleNode<Palace> zwPalace){

    }

    public String toJsonString() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(DestinyDto.from(this));
    }
}
