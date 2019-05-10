# 一、基本介绍
当前项目是一个基于原型模式的对象属性拷贝示例工程，底层克隆机制使用net.sf.cglib.beans.BeanCopier实现。

# 

# 二、能力清单
## 特别说明：
<B>浅度克隆：</B>
浅度克隆是指只克隆一个领域对象中的自己本身的字段，比如有一个对应的订单对象OrderDTO，然后有Long id, String orderNo这两个自己本身的对象，但由于订单与订单详情一对一的关系，在OrderDTO类里面也定义了一个OrderDetailDTO orderDetail订单详情字段（OrderDetailDTO对象也是一个领域对象，不是订单对象OrderDTO的的本身字段），那么浅度克隆时就只会克隆Long id, String orderNo这两个字段，不会处理OrderDetailDTO orderDetail这个订单详情字段。
<br/><br/>
<B>深度克隆：</B>
深度克隆是指在前面浅度克隆的前提下，会将OrderDetailDTO orderDetail订单详情对象也克隆对目标对象中去。使用深度克隆时需要指定克隆的方向，目前约定好一个正向与反向两个情况（见com.yx.demo.core.CloneDirection.java中的注释描述），实际开发中应该视具体业务场景使用浅度克隆或深度克隆，再只需要浅度克隆就能完成需求的就不要使用深度克隆。

## （1）浅度克隆
### 单个对象间的浅度克隆
测试示例代码见com.yx.demo.test.Test01.java
### 集合对象间的浅度克隆
测试示例代码见com.yx.demo.test.Test02.java
### 分页Page对象间的浅度克隆
实现方式跟集合对象间的浅度克隆类似，测试示例代码未提供。（使用ObjectCloneUtils中的convertPage(
			Page<? extends AbstractObject> sourcePage, Class<T> targetClazz)方法即可）


## （2）深度克隆
### 单个对象间的深度克隆
测试示例代码见
com.yx.demo.test.Test03.java（正向）
com.yx.demo.test.Test04.java（反向）


### java.util.List集合对象间的深度克隆
测试示例代码见：
com.yx.demo.test.Test05.java（正向）
com.yx.demo.test.Test06.java（反向）


## com.github.pagehelper.Page分页集合对象深度克隆
测试示例代码见：
com.yx.demo.test.Test07.java（正向）
com.yx.demo.test.Test08.java（反向）


# 三、使用方式
（1）所有需要克隆的领域对象都需要实现com.yx.demo.core.AbstractObject类。<br/>
（2）如果是单个对象克隆，直接使用对象名调用clone()方法，指定目标对象类型与是否深度克隆即可，比如order.clone(OrderDO.class);<br/>
（3）如果是集合对象或分页对象克隆，需要使用ObjectCloneUtils工具类调用里面的方法来实现。<br/>
