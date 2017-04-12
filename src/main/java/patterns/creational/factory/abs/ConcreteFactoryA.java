package patterns.creational.factory.abs;

/**
 * Created by Administrator on 2017/3/16.
 */
public class ConcreteFactoryA implements Factory{
    public ProductA createProductA() {
        return new ProductA1();
    }

    public ProductB createProductB() {
        return new ProductB1();
    }
}
