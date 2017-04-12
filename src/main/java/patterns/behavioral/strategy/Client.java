package patterns.behavioral.strategy;

/**
 *就是有一系列的可相互替换的算法的时候，我们就可以使用策略模式将这些算法做成接口的实现，并让我们依赖于算法的类依赖于抽象的算法接口，这样可以彻底消除类与具体算法之间的耦合
 */
public class Client {

    public static void main(String[] args) {
        Context context = new Context();
        context.setStrategy(new ConcreteStrategyA());
        context.method();
    }

}
