package patterns.creational.singleton;

/**
 * Created by Administrator on 2017/3/20.
 */
public class SynchronizedSingleton {

    private static SynchronizedSingleton synchronizedSingleton;

    private SynchronizedSingleton(){

    }

    //双重锁设计
    public static SynchronizedSingleton getInstance(){
        if (synchronizedSingleton == null){
            synchronized (SynchronizedSingleton.class){
                if (synchronizedSingleton == null){
                    synchronizedSingleton = new SynchronizedSingleton();
                }
            }
        }
        return synchronizedSingleton;
    }

}
