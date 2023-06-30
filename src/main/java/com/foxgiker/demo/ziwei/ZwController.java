package com.foxgiker.demo.ziwei;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/zw")
public class ZwController {

    @GetMapping
    public String index(Model model){
        return "ziweidoushu";
    }


    @PostMapping("/calc")
    @ResponseBody
    public DestinyDto calc(@RequestParam() Integer year,
                           @RequestParam() Integer month,
                           @RequestParam() Integer day,
                           @RequestParam() Integer hour,
                           @RequestParam(required = false) Integer type){
        Destiny destiny = new Destiny(year, month, day, hour);
        return DestinyDto.from(destiny);
    }

}
