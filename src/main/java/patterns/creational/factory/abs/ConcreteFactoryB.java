package patterns.creational.factory.abs;

/**
 * Created by Administrator on 2017/3/16.
 */
public class ConcreteFactoryB implements Factory{
    public ProductA createProductA() {
        return new ProductA2();
    }

    public ProductB createProductB() {
        return new ProductB2();
    }
}
