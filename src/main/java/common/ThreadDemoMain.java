package common;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Administrator on 2017/3/10.
 */
public class ThreadDemoMain {

    private volatile static boolean lock;//标识符

    public static void main(String[] args) throws InterruptedException {
        testSingleCondition();
    }

    public static void testHoldsLock(){
        final ReentrantLock reentrantLock = new ReentrantLock();
        Thread thread1 = new Thread(new Runnable() {
            public void run() {
                try {
                    reentrantLock.lock();
                    System.out.println("线程1 开始执行");
                    System.out.println("线程1是否获取到锁:" + Thread.holdsLock(reentrantLock));

                }finally {
                    reentrantLock.unlock();
                }
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            public void run() {
                try {
                    reentrantLock.lock();
                    System.out.println("线程2 开始执行");
                    System.out.println("线程2是否获取到锁:" + Thread.holdsLock(reentrantLock));

                }finally {
                    reentrantLock.unlock();
                }
            }
        });
        thread1.start();
        thread2.start();
    }

    //单个条件 使用一个线程在object对象上等待另外一个线程的通知
    public static void testSingleCondition() throws InterruptedException {
        final Object object = new Object();
        //线程间的协作关系 使用一个线程在object对象上等待另外一个线程的通知 wait,notify和notifyAll方法在使用前，必须获取到当前对象的锁
        Thread thread1 = new Thread(new Runnable() {
            public void run() {
                System.out.println("等待开启通知");
                synchronized (object){
                    System.out.println("我拿到了锁1");
                    while (!lock){
                        try {
                            System.out.println("线程1是否获取到锁:" + Thread.holdsLock(object));
                            object.wait();//这里的线程被挂起了
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("我释放锁1");
                }
                System.out.println("已被通知");
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            public void run() {
                System.out.println("马上开启通知");
                synchronized (object){
                    System.out.println("线程2是否获取到锁:" + Thread.holdsLock(lock));
                    System.out.println("我拿到了锁2");
                    object.notify();
                    lock = true;
                    System.out.println("我释放锁2");
                }
                System.out.println("收到通知");
            }
        });
        thread1.start();
        thread2.start();
        Thread.sleep(1000);
    }

    //多条件 线程1等待线程2通知 线程2等待线程3通知 线程3等待线程1通知
    public static void testMultiCondition() throws InterruptedException {
        final Object object1 = new Object();
        final Object object2 = new Object();
        Thread thread1 = new Thread(new Runnable() {
            public void run() {
                try {
                    System.out.println("等待object1被通知！");
                    synchronized (object1) {
                        object1.wait();
                    }
                    System.out.println("object1已被通知，马上开始通知object2！");
                    synchronized (object2) {
                        object2.notify();
                    }
                    System.out.println("通知object2完毕！");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            public void run() {
                try {
                    System.out.println("马上开始通知object1！");
                    synchronized (object1) {
                        object1.notify();
                    }
                    System.out.println("通知object1完毕，等待object2被通知！");
                    synchronized (object2) {
                        object2.wait();
                    }
                    System.out.println("object2已被通知！");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread1.start();
        Thread.sleep(1000);
        thread2.start();
    }

    public static void testCondition() throws InterruptedException {
        final ReentrantLock reentrantLock = new ReentrantLock();
        final Condition condition1 = reentrantLock.newCondition();
        final Condition condition2 = reentrantLock.newCondition();
        final Condition condition3 = reentrantLock.newCondition();

        Thread thread1 = new Thread(new Runnable() {
            public void run() {
               reentrantLock.lock();
                System.out.println("等待condition1被通知");
                try {

                    condition1.await();
                    System.out.println("condition1已经被通知");

                    condition3.signal();
                    System.out.println("通知condition3");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    reentrantLock.unlock();
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            public void run() {
                reentrantLock.lock();
                System.out.println("等待condition2被通知");
                try {
                    condition2.signal();
                    System.out.println("condition2已经被通知");
                    condition2.await();
                    System.out.println("通知condition1完毕");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    reentrantLock.unlock();
                }
            }
        });

        Thread thread3 = new Thread(new Runnable() {
            public void run() {
                reentrantLock.lock();
                System.out.println("等待condition2被通知");
                try {
                    condition2.await();
                    System.out.println("condition3已经被通知");
                    System.out.println("通知condition1完毕");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    reentrantLock.unlock();
                }
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();
        Thread.sleep(10000);
    }

    //帮助猿友们方便的实现一个这样的场景，就是某一个线程需要等待其它若干个线程完成某件事以后才能继续进行
    public static void testCountDown() throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            final int number = i + 1;
            Runnable runnable = new Runnable() {
                public void run() {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {}
                    System.out.println("执行任务[" + number + "]");
                    countDownLatch.countDown();
                    System.out.println("完成任务[" + number + "]");
                }
            };
            Thread thread = new Thread(runnable);
            thread.start();
        }
        System.out.println("主线程开始等待...");
        countDownLatch.await();
        System.out.println("主线程执行完毕...");
    }

    //帮助猿友们方便的实现多个线程一起启动的场景，就像赛跑一样，只要大家都准备好了，那就开始一起冲
    public static void testCyclicBarrier(){
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(10);
        for (int i = 0; i < 10; i++) {
            final int number = i + 1;
            Runnable runnable = new Runnable() {
                public void run() {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {}
                    System.out.println("等待执行任务[" + number + "]");
                    try {
                        cyclicBarrier.await();
                    } catch (InterruptedException e) {
                    } catch (BrokenBarrierException e) {
                    }
                    System.out.println("开始执行任务[" + number + "]");
                }
            };
            Thread thread = new Thread(runnable);
            thread.start();
        }
    }

    //帮助猿友们方便的实现控制数量的场景，可以是线程数量或者任务数量等等
    public static void testSemaphore() throws InterruptedException {
        //设定了总数为10，却开了100个线程，但是最终只有10个线程获取到了信号量，如果这10个线程不主动调用release方法的话，那么其余90个线程将一起挂死
        final Semaphore semaphore = new Semaphore(10);
        final AtomicInteger number = new AtomicInteger();
        for (int i = 0; i < 100; i++) {
            Runnable runnable = new Runnable() {
                public void run() {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {}
                    try {
                        semaphore.acquire();
                        number.incrementAndGet();
                    } catch (InterruptedException e) {}
                }
            };
            Thread thread = new Thread(runnable);
            thread.start();
        }
        Thread.sleep(1000);
        System.out.println("共" + number.get() + "个线程获得到信号");
        System.exit(0);
    }

    //帮助猿友们方便的实现两个线程交换数据的场景
    public static void testExchanger(){
        //两个线程在只有一个线程调用exchange方法的时候调用方会被挂起，当都调用完毕时，双方会交换数据。在任何一方没调用exchange之前，线程都会处于挂起状态
        final Exchanger<String> exchanger = new Exchanger<String>();
        Thread thread1 = new Thread(new Runnable() {
            public void run() {
                try {
                    System.out.println("线程1等待接受");
                    String content = exchanger.exchange("thread1");
                    System.out.println("线程1收到的为：" + content);
                } catch (InterruptedException e) {}
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            public void run() {
                try {
                    System.out.println("线程2等待接受并沉睡3秒");
                    Thread.sleep(3000);
                    String content = exchanger.exchange("thread2");
                    System.out.println("线程2收到的为：" + content);
                } catch (InterruptedException e) {}
            }
        });
        thread1.start();
        thread2.start();
    }

}
