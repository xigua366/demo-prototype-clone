package com.deepexi.demo.test.test11;

import com.deepexi.demo.core.CloneDirection;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试某个类的父类中还有应用对象类型字段时的深度克隆
 */
public class Test11 {

  public static void main(String[] args) {

      List<ParentOtherVO> parentOtherVOList = new ArrayList<>();
      ParentOtherVO parentOtherVO = new ParentOtherVO();
      parentOtherVO.setId(10L);
      parentOtherVO.setName("aaa");
      parentOtherVOList.add(parentOtherVO);

      ParentOtherVO parentOtherVO2 = new ParentOtherVO();
      parentOtherVO2.setId(10L);
      parentOtherVO2.setName("aaa");

      List<ParentOtherVO> pparentOtherVOList = new ArrayList<>();
      ParentOtherVO pparentOtherVO = new ParentOtherVO();
      pparentOtherVO.setId(100L);
      pparentOtherVO.setName("pp");
      pparentOtherVOList.add(pparentOtherVO);

      ParentOtherVO pparentOtherVO2 = new ParentOtherVO();
      pparentOtherVO2.setId(101L);
      pparentOtherVO2.setName("pap");


      OtherObjVO otherObjVO = new OtherObjVO();
      otherObjVO.setId(1L);
      otherObjVO.setName("haha");


      SubObjVO subObjVO = new SubObjVO();
      subObjVO.setId(2L);
      subObjVO.setName("pname");
      subObjVO.setSubId(3L);
      subObjVO.setSubName("sname");

      subObjVO.setPpid(1111L);
      subObjVO.setPpname("ppname");

      subObjVO.setParentOther(parentOtherVO2);
      subObjVO.setParentOthers(parentOtherVOList);

      subObjVO.setPparentOther(pparentOtherVO2);
      subObjVO.setPparentOthers(pparentOtherVOList);

      subObjVO.setOtherObj(otherObjVO);


      SubObjDTO subObjDTO = subObjVO.clone(SubObjDTO.class, CloneDirection.FORWARD);
      System.out.println(subObjDTO);
  }
}
