package com.zp.rubbish.controller;

import com.zp.rubbish.entities.Rubbish;
import com.zp.rubbish.entities.TbRubbish;
import com.zp.rubbish.utils.EasyUIDataGridResult;
import com.zp.rubbish.utils.MorseZP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;

@RestController
public class RubbishClassController {

    @Value("${RESTURI}")
    private String RESTURI;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/rubbish/{name}")
    public Rubbish getRubbishClass(@PathVariable("name") String name){
        Rubbish rubbish=restTemplate.getForObject(RESTURI+"/rubbish/"+name, Rubbish.class);
        if (rubbish.getStatus()==1){
            String classz=rubbish.getRubbishClass();
            try {
                classz= MorseZP.morseZpEncode(classz);
            } catch (UnsupportedEncodingException e) {
                rubbish.setStatus(2);
                classz="服务器繁忙，请稍后再试";
                e.printStackTrace();
            }
            rubbish.setRubbishClass(classz);
        }
        return  rubbish;
    }

    @GetMapping("/rubbish/list")
    public EasyUIDataGridResult<TbRubbish> getFeedbackList(Integer page, Integer rows){
        if (page == null)
            page = 1;
        if (rows == null)
            rows = 30;

        EasyUIDataGridResult<TbRubbish> dataGridResult = restTemplate.getForObject(RESTURI+"/rubbish/list?page="+page+"&rows="+rows, EasyUIDataGridResult.class);
        return dataGridResult;
    }


}
