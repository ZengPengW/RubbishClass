package com.zp.rubbish.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.zp.rubbish.entities.Rubbish;
import com.zp.rubbish.entities.TbRubbish;
import com.zp.rubbish.service.RubbishClassService;
import com.zp.rubbish.utils.EasyUIDataGridResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RubbishClassController {
    @Autowired
    private RubbishClassService rubbishClassService;


    @GetMapping("/rubbish/{name}")
    @HystrixCommand(fallbackMethod = "getRubbishClassError")
    public Rubbish getRubbishClass(@PathVariable("name") String name) throws Exception {
        if(name==null||name.trim().length()==0){
            Rubbish rubbish=new Rubbish();
            rubbish.setStatus(2);
            return  rubbish;
        }
        Rubbish rubbish = null;
        rubbish = rubbishClassService.selectRubbishClassByName(name);
        if (rubbish!=null)rubbish.setStatus(1);
        else {
            rubbish=new Rubbish();
            rubbish.setStatus(2);
        }
        return rubbish;
    }

    @GetMapping("/rubbish/list")
    public EasyUIDataGridResult<TbRubbish> getRubbishList(Integer page, Integer rows){
        return  rubbishClassService.selectAll(page,rows);
    }




    public  Rubbish getRubbishClassError(@PathVariable("name") String name){
        Rubbish rubbish=new Rubbish();
        rubbish.setStatus(2);
        rubbish.setRubbishClass("服务器炸了，请稍等！");
        rubbish.setRubbishName("触发熔断");
        return rubbish;
    }



}
