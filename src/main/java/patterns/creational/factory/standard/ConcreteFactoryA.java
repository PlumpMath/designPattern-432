package patterns.creational.factory.standard;

/**
 * Created by Administrator on 2017/3/16.
 */
public class ConcreteFactoryA implements Factory{


    public Product createProduct() {
        return new ProductA();
    }
}
