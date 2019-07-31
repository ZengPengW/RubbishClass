package com.zp.rubbish.mapper;

import com.zp.rubbish.entities.TbFeedback;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FeedbackMapper {
    /**
     * 反馈垃圾名字
     * @param name
     */
    public void saveFeedback(String name);

    /**
     * 根据名字查询反馈表
     * @param name
     * @return
     */
    public TbFeedback selectFeedbackByName(String name);
}
