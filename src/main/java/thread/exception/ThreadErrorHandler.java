package thread.exception;

/**
 * Created by Administrator on 2017/3/20.
 */
public class ThreadErrorHandler implements Thread.UncaughtExceptionHandler{


    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("This is:" + t.getName() + ",Message:"
                + e.getMessage());
//        e.printStackTrace();
    }
}
