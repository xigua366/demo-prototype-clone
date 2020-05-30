package com.deepexi.demo.test.test11;

import lombok.Data;

import java.util.List;

@Data
public class ParentObjDTO extends PParentObjDTO {

    private Long id;

    private String name;

    private ParentOtherDTO parentOther;

    private List<ParentOtherDTO> parentOthers;

}
