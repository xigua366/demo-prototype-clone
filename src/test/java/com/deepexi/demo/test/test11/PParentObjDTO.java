package com.deepexi.demo.test.test11;

import com.deepexi.demo.core.AbstractObject;
import lombok.Data;

import java.util.List;

@Data
public class PParentObjDTO extends AbstractObject {

    private Long ppid;

    private String ppname;

    private ParentOtherDTO pparentOther;

    private List<ParentOtherDTO> pparentOthers;
}
