package com.zp.rubbish.service;

import com.zp.rubbish.entities.Rubbish;

public interface RubbishClassService {
    public Rubbish selectRubbishClassByName(String name) throws Exception;
}
