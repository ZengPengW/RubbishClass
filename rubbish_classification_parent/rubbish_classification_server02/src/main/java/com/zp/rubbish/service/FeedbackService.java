package com.zp.rubbish.service;

import com.zp.rubbish.entities.TbFeedback;
import com.zp.rubbish.utils.EasyUIDataGridResult;

public interface FeedbackService {
    /**
     * 分页查询
     * @param page
     * @param rows
     * @return
     */
    public EasyUIDataGridResult<TbFeedback> selectAll(Integer page, Integer rows);

    /**
     * 工具名字删除反馈
     * @param name
     */
    public void deleteFeedbackByName(String name);
}
