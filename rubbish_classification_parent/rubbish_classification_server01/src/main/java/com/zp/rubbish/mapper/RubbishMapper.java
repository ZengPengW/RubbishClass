package com.zp.rubbish.mapper;

import com.zp.rubbish.entities.Rubbish;
import com.zp.rubbish.entities.TbRubbish;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RubbishMapper {
    /**
     * 通过名字查询垃圾
     * @param rubbish_name
     * @return
     */
    public Rubbish selectRubbileByName(String rubbish_name);

    /**
     * 垃圾保存到数据库
     * @param tbRubbish
     */
    public void save(TbRubbish tbRubbish);

    /**
     * 反馈垃圾名字
     * @param name
     */
    public void saveFeedback(String name);
}
