package com.deepexi.demo.test.test11;

import lombok.Data;

import java.util.List;

@Data
public class ParentObjVO extends PParentObjVO {

    private Long id;

    private String name;

    private ParentOtherVO parentOther;

    private List<ParentOtherVO> parentOthers;

}
