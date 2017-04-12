package patterns.creational.factory.standard;

/**
 * 简单工厂模式  相对于简单工厂来说 好处是不用参数绑定 能够解耦
 * 场景：
 *   一种场景是希望工厂与产品的种类对客户端保持透明，给客户端提供一致的操作;
 *   另外一种是不同的工厂和产品可以提供客户端不同的服务或功能
 * key:
 * 工厂类和产品类是否是同生同灭的关系
 */
public interface Factory {


    Product createProduct();



}
