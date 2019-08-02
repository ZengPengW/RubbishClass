package com.zp.rubbish.service;

import com.zp.rubbish.entities.Rubbish;
import com.zp.rubbish.entities.TbRubbish;
import com.zp.rubbish.utils.EasyUIDataGridResult;

public interface RubbishClassService {
    public Rubbish selectRubbishClassByName(String name) throws Exception;

    public void  saveRbuuish(TbRubbish tbRubbish);

    public EasyUIDataGridResult<TbRubbish> selectAll(Integer page, Integer rows);
}
