package com.zp.rubbish.controller;

import com.alibaba.fastjson.JSONObject;
import com.zp.rubbish.entities.TbFeedback;
import com.zp.rubbish.entities.TbRubbish;
import com.zp.rubbish.utils.EasyUIDataGridResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
public class FeedBackController {

    @Value("${RESTURI}")
    private String RESTURI;

    @Autowired
    private RestTemplate restTemplate;


    @PostMapping("/saveRubbish")
    public String saveRubbish( TbRubbish tbRubbish){
        System.out.println("dadad");
        String json=JSONObject.toJSONString(tbRubbish);
        HttpEntity<String> requestEntity=new HttpEntity<>(json);
        Map map= restTemplate.postForObject(RESTURI+"/saveRubbish", requestEntity, Map.class);
        String message= (String) map.get("message");
        return  "<h1>"+message+"</h1>";
    }


    @GetMapping("/feedback/list")
    public EasyUIDataGridResult<TbFeedback> getFeedbackList(Integer page, Integer rows){
        if (page == null)
            page = 1;
        if (rows == null)
            rows = 30;

        EasyUIDataGridResult<TbFeedback> dataGridResult = restTemplate.getForObject(RESTURI+"/feedback/list?page="+page+"&rows="+rows, EasyUIDataGridResult.class);
        return dataGridResult;
    }
}
