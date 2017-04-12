package concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Administrator on 2017/3/10.
 */
public class Resource {

    final Lock lock = new ReentrantLock();
    final Condition notFull = lock.newCondition();//非满
    final Condition notEmpty = lock.newCondition();//非空  --有东西了

    final Integer [] items = new Integer[10000];
    int putIndex, takeIndex, count;
    //生产
    public void yield(){
        lock.lock();
        try {
            while (count == items.length){
                notFull.await();//满了 咱不能再装东西啦
            }
            items[putIndex] = 100;
            if (++putIndex == items.length) putIndex = 0;
            count++;
            System.out.println(Thread.currentThread().getName() + ":我生产了一个商品,仓库还有" + count + "个玩意啦");
            notEmpty.signal();//我有东西啦 可以消费啦
        }catch (Exception e){

        }finally {
            lock.unlock();
        }
    }

    //消费
    public Integer consume(){
        lock.lock();
        try {
            while (count == 0){
                notEmpty.await();//没东西啦 不能消费啦
            }
            Integer x = items[takeIndex];
            if (++takeIndex == items.length) takeIndex = 0;
            --count;
            System.out.println(Thread.currentThread().getName() + ":我消费掉了一个商品,仓库还有" + count + "个玩意啦");
            notFull.signal();//东西我消费掉了起码一个,可以准许生产啦
            return x;
        }catch (Exception e){

        }finally {
            lock.unlock();
        }
        return 0;
    }

    public static void main(String[] args) throws InterruptedException {
        final Resource resource = new Resource();
        Thread thread1 = new Thread(new Runnable() {
            public void run() {
                while(true){
                    resource.yield();
                }
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            public void run() {
                while(true){
                    resource.yield();
                }
            }
        });
        Thread thread3 = new Thread(new Runnable() {
            public void run() {
                while(true){
                    resource.yield();
                }
            }
        });
        Thread thread4 = new Thread(new Runnable() {
            public void run() {
                while(true){
                    resource.consume();
                }
            }
        });
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        Thread.sleep(10000);
    }

}