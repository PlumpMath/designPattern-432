package common;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程安全共享线程池
 */
public class ThreadPoolFactory{

    private static final int LIMIT = 50;// 缓冲队列默认长度
    private static final BlockingQueue<Runnable> bq = new LinkedBlockingQueue<Runnable>(2 * LIMIT);//阻塞队列
    private ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 3, 150000, TimeUnit.SECONDS,bq);

    private static ThreadPoolFactory threadPoolFactory = new ThreadPoolFactory();

    private ThreadPoolFactory(){
    }




    public static ThreadPoolFactory getInstance(){
        return threadPoolFactory;
    }

    public static void main(String[] args) {
        for (int i =0 ;i < 5; i++){
            ThreadPoolFactory.getInstance().threadPoolExecutor.execute(new Runnable() {
                public void run() {
                    Long result = 0l;
                    for (long j = 0;j < 1000000000L;j++){
                        result+=j;
                    }
                    System.out.println("当前线程:" + Thread.currentThread().getName() + "的计算结果是" + result);
                }
            });
        }
    }

}
