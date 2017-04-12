package patterns.creational.factory.abs;

/**
 * 抽象工厂模式
 * 常用场景：需要一个接口可以提供一个产品族，且不必知道产品的具体种类
 * key：产品族是否需要一起提供，且是否有一致的接口
 */
public interface Factory {

    ProductA createProductA();//产品族1

    ProductB createProductB();//产品族2

}
