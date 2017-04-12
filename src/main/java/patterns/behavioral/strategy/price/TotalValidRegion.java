package patterns.behavioral.strategy.price;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 总消费 --这是我们的有效区间注解，可以给策略添加有效区间的设置 -> 用来解决总金额判断的问题
 */
@Target(ElementType.TYPE)//针对类
@Retention(RetentionPolicy.RUNTIME)//滞留策略为 运行时可用
public @interface TotalValidRegion {
   ValidRegion value() default @ValidRegion;
}
