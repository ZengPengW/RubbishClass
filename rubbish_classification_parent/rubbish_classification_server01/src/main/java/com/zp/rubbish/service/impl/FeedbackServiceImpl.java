package com.zp.rubbish.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zp.rubbish.entities.TbFeedback;
import com.zp.rubbish.mapper.FeedbackMapper;
import com.zp.rubbish.service.FeedbackService;
import com.zp.rubbish.utils.EasyUIDataGridResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackMapper feedbackMapper;

    @Override
    public  EasyUIDataGridResult<TbFeedback> selectAll(Integer page, Integer rows) {
        if (page == null)
            page = 1;
        if (rows == null)
            rows = 30;
        PageHelper.startPage(page, rows);
        List<TbFeedback> list = feedbackMapper.selectAll();
        PageInfo<TbFeedback> pageInfo = new PageInfo<TbFeedback>(list);
        EasyUIDataGridResult<TbFeedback> dataGridResult = new EasyUIDataGridResult<>();
        dataGridResult.setRows(list);
        dataGridResult.setTotal(pageInfo.getTotal());
        return dataGridResult;
    }

    @Override
    public void deleteFeedbackByName(String name) {
        feedbackMapper.deleteFeedbackByName(name);
    }
}
