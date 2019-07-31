package com.zp.rubbish.entities;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class TbRubbish {
    private  Integer id;
    private  String rubbishName;
    private  Integer rubbishClass;
}
