package thread;

import thread.exception.ThreadErrorHandler;

/**
 * Created by Administrator on 2017/3/20.
 */
public class Main {

    public static void main(String[] args) {
//        double i = 12 / 0;

        Thread a = new Thread(new Runnable() {
            public void run() {
                try {
                    double i = 12 / 0;// 抛出异常的地方
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }

            }
        });
//        a.setUncaughtExceptionHandler(new ThreadErrorHandler());
        a.start();


    }

}
