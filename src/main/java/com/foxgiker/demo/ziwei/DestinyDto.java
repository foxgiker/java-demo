package com.foxgiker.demo.ziwei;

import lombok.Data;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class DestinyDto {

    private List<Palace> palaces;

    private String eightWord;

    private String fiveElementEnv;


    public static DestinyDto from(Destiny d){
        DestinyDto dto = new DestinyDto();
        List<Palace> collect = d.getPalaces().getList().stream().map(t -> t.data()).collect(Collectors.toList());
        dto.setPalaces(collect);
        dto.setEightWord(d.getEightWord());
        dto.setFiveElementEnv(d.getFiveElementEnv());

        return dto;
    }

}
