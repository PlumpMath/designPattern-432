package patterns.behavioral.strategy.price;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 增加一个基类策略 -> 用于嵌套策略
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidRegion {
    //为了简单，我们让区间只支持整数
    int max() default Integer.MAX_VALUE;
    int min() default Integer.MIN_VALUE;

    int order() default 0;//策略排序 -> 策略也是有优先级的
}
