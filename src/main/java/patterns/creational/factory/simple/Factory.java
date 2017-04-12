package patterns.creational.factory.simple;

/**
 * 简单工厂模式
 * key：一种产品是否可根据某个参数决定它的种类
 * 常用场景：需要在一堆产品中选择其中一个产品
 */
public class Factory {


    public static Product getInstance(String name){
        if ("ProductA".equals(name)){
            return new ProductA();
        }
        if ("ProductB".equals(name)){
            return new ProductB();
        }
        return null;
    }

}
