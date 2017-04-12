package common.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * Created by Administrator on 2017/3/20.
 */
public class ForkJoinTest extends RecursiveTask<Long> {

    public final static int THRESHOLD = 5000;//每次处理的量

    private Integer start;
    private Integer end;


    public ForkJoinTest(Integer start, Integer end){
        this.start = start;
        this.end = end;
    }


    @Override
    protected Long compute() {
        long sum = 0l;
        boolean devide = (end - start) <= 5000;
        if (devide){
            for (int i = start;i<end;i++){
                sum += i;
            }
        }else{
            int mid = (start + end ) >> 1;
            ForkJoinTest leftTask = new ForkJoinTest(start,mid);
            ForkJoinTest rightTest = new ForkJoinTest(mid+1,end);
            leftTask.fork();
            rightTest.fork();
            sum += leftTask.join();
            sum += rightTest.join();
        }
        return sum;
    }

    private long cal(int start, int end) {
        long sum = 0L;
        for (int i=start; i<=end; i++) {
            sum += i;
        }
        return sum;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int end = 999999999;
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTest task = new ForkJoinTest(1,end);
        Future<Long> future = forkJoinPool.submit(task);
        if(task.isCompletedAbnormally())
        {
            System.out.println(task.getException());
        }
        long t1 = System.currentTimeMillis();
        future.get();
        long t2 = System.currentTimeMillis();
        task.cal(1, end);
        long t3 = System.currentTimeMillis();
    }
}
