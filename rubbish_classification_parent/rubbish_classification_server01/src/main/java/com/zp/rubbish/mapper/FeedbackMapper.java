package com.zp.rubbish.mapper;

import com.zp.rubbish.entities.TbFeedback;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

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

    /**
     * 查询所有反馈
     * @return
     */
    public List<TbFeedback> selectAll();

    /**
     * 工具名字删除反馈
     * @param name
     */
    public void deleteFeedbackByName(String name);
}
