package com.zp.rubbish.entities;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Rubbish {
    @JSONField(serialize=false)
    private  Integer id;
    private  String rubbishName;
    private  String rubbishClass;
    private  Integer status;
}
