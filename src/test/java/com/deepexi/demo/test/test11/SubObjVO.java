package com.deepexi.demo.test.test11;

import lombok.Data;

@Data
public class SubObjVO extends ParentObjVO {

    private Long subId;

    private String subName;

    private OtherObjVO otherObj;

}
