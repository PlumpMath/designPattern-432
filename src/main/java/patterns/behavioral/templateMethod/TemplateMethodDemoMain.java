package patterns.behavioral.templateMethod;

/**
 * 解释：
 *      模板方法模式，一般是为了统一子类的算法实现步骤，所使用的一种手段或者说是方式。它在父类中定义一系列算法的步骤，而将具体的实现都推迟到子类
 * 典型形式：
 *      一个接口，一个抽象父类，父类中会有一系列的抽象方法，而在子类中去一一实现这些方法
 * 作用场景：
 *      通常情况下，模板方法模式用于定义构建某个对象的步骤与顺序，或者定义一个算法的骨架。
 *      --在某些场景中，可以直接将父类中提供骨架的方法声明为final类型
 *      --为了不强制子类实现不必要的抽象方法，但又不剥夺子类自由选择的权利，我们在父类提供一个默认的空实现，来让子类自由选择是否要覆盖掉这些方法
 *      ClassLoader
 */
public class TemplateMethodDemoMain {

    public static void main(String[] args) {
        PageBuilder pageBuilder = new MyPageBuilder();
        System.out.println(pageBuilder.buildHtml());
    }

}
