package patterns.structural.decorator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

/**
 * 装饰器：
 *    UML:
 *    被装饰父类(接口)  ——— 被装饰父类子类 (继承关系)
 *       |
 *       | (关联关系 -- 也可以继承或实现接口用于功能复用)
 *       |
 *    装饰类基类  ——— 装饰类子类(继承关系)
 *    作用：
 *       拓展类的作用，替代继承
 *    优点：
 *       装饰类和被装饰类可以独立发展，不会相互耦合，装饰模式是继承的一个替代模式，装饰模式可以动态扩展一个实现类的功能
 *    对比：在拓展类时,一般会使用子类继承；但当子类各自特色，同时不同的子类可以被多个类装饰的时候 -- 就是解决组合的问题
 *        比如 子类
 *           A.FileInputStream 提供文件读写操作
 *           B.ByteArrayInputStream 提供byte读写操作
 *           C.ObjectInputStream
 *           拓展功能
 *             BufferedInputStream 提供缓冲区
 *             DataInputStream 提供data处理
 *             PushbakInputStream 提供pushback处理
 *           无论是A,B 都可以被下面的进行处理；代价只是 父类型对应即可
 *
 *           Java I/O库需要很多性能的各种组合，如果说这些性能的组合是通过继承方式来实现的话，
 *           那么每一种组合都需要一个类，这样就会出现大量重复性问题的出现，从而使类数目“爆炸”。
 *           而如果采用装饰模式，那么不仅类的数目大减少了，性能的重复也可以减至到最少.
 */
public class DecoratorDemoMain {

    public static void main(String[] args) {

        Shape shape = new CircleShape();

        RedShapeDecorator redShapeDecorator = new RedShapeDecorator(shape);

        shape.draw();

        redShapeDecorator.draw();



    }

}
