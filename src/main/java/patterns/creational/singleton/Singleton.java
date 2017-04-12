package patterns.creational.singleton;

/**
 * Created by Administrator on 2017/3/13.
 */
public class Singleton {


    private Singleton(){

    }

    static Singleton getInstance(){
        return SingletonInstance.instance;
    }

    //第一次调用时才初始化 -- 相比于饿汉式的好处，不用直接实例化
    static class SingletonInstance{

        static Singleton instance = new Singleton();

    }

}
