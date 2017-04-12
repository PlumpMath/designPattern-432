package patterns.behavioral.strategy.price;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 一次性消费 针对单次消费的有效区间注解，可以给策略添加有效区间的设置
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface OnceValidRegion {
    ValidRegion value() default @ValidRegion;
}
