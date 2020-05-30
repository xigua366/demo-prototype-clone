package com.deepexi.demo.test.test11;

import lombok.Data;

@Data
public class SubObjDTO extends ParentObjDTO {

    private Long subId;

    private String subName;

    // private OtherObjDTO otherObj;

}
