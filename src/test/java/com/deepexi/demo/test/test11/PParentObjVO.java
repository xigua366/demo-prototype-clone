package com.deepexi.demo.test.test11;

import com.deepexi.demo.core.AbstractObject;
import lombok.Data;

import java.util.List;

@Data
public class PParentObjVO extends AbstractObject {

    private Long ppid;

    private String ppname;

    private ParentOtherVO pparentOther;

    private List<ParentOtherVO> pparentOthers;
}
