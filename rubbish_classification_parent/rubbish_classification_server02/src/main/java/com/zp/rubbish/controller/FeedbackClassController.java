package com.zp.rubbish.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.zp.rubbish.entities.TbFeedback;
import com.zp.rubbish.entities.TbRubbish;
import com.zp.rubbish.service.FeedbackService;
import com.zp.rubbish.service.RubbishClassService;
import com.zp.rubbish.utils.EasyUIDataGridResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class FeedbackClassController {
    @Autowired
    private FeedbackService feedbackService;

    @Autowired
    private RubbishClassService rubbishClassService;
    /**
     * 查询垃圾反馈列表
     * @param page
     * @param rows
     * @return
     */
    @GetMapping("/feedback/list")
    public EasyUIDataGridResult<TbFeedback> getFeedbackList(Integer page,Integer rows){
        EasyUIDataGridResult<TbFeedback> dataGridResult = feedbackService.selectAll(page, rows);
        return dataGridResult;
    }

    /**
     * 保存反馈垃圾到垃圾分类库
     */
    @PostMapping("/saveRubbish")
    @HystrixCommand(fallbackMethod = "saveRubbishError")
    public Map saveRubbish(@RequestBody TbRubbish tbRubbish){
        Map map=new HashMap();
        if (tbRubbish.getRubbishName()==null){
            map.put("status", 2);
            map.put("message", "fail");
        }else{
            feedbackService.deleteFeedbackByName(tbRubbish.getRubbishName());
            rubbishClassService.saveRbuuish(tbRubbish);
            map.put("status", 1);
            map.put("message", "success");
        }
        return  map;
    }

    public Map saveRubbishError(@RequestBody TbRubbish tbRubbish){
        Map map=new HashMap();

        map.put("status", 3);
        map.put("message", "服务器错误");
        return  map;
    }
}
