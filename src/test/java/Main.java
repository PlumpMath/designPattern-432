import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.*;

/**
 * Created by Administrator on 2017/3/20.
 */
public class Main {


    @Test
    public void testList4JDK7(){
        int max = 1000000;
        List<String> values = new ArrayList<String>(max);
        for (int i = 0; i < max; i++) {
            UUID uuid = UUID.randomUUID();
            values.add(uuid.toString());
        }

        long t0 = System.nanoTime();

        long count = values.parallelStream().sorted().count();
        System.out.println(count);

        long t1 = System.nanoTime();

        long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
        System.out.println(String.format("parallel sort took: %d ms", millis));

    }

    private static final long COUNT = 10000000;
    private static final int THREADS = 2;

    public static void main(String[] args) {
//        System.out.println( "Shared Random" );
//        testRandom(THREADS, COUNT);
  System.out.println("ThreadLocal<Random>");
  testTL_Random(THREADS, COUNT);
//  System.out.println("ThreadLocalRandom");
//  testTLRandom(THREADS, COUNT);
//        System.out.println("Shared Random end");
//  System.out.println("Shared Random[] with no padding");
//  testRandomArray(THREADS, COUNT, 1);
//  System.out.println("Shared Random[] with padding");
//  testRandomArray(THREADS, COUNT, 2);
    }

    //runner for all tests
    private static class RandomTask implements Runnable
    {
        private final Random rnd;
        protected final int id;
        private final long cnt;
        private final CountDownLatch latch;

        private RandomTask(Random rnd, int id, long cnt, CountDownLatch latch) {
            this.rnd = rnd;
            this.id = id;
            this.cnt = cnt;
            this.latch = latch;
        }

        protected Random getRandom()
        {
            return rnd;
        }

        public void run() {
            try {
                final Random r = getRandom();
                final long start = System.currentTimeMillis();
                int sum = 0;
                for ( long j = 0; j < cnt; ++j )
                {
                    sum += r.nextInt();
                }
                final long time = System.currentTimeMillis() - start;
                System.out.println( "Thread #" + id + " Time = " + time / 1000.0 + " sec, sum = " + sum );
            }finally {
                latch.countDown();
            }
        }
    }

    private static void testRandom( final int threads, final long cnt )
    {
        final CountDownLatch latch = new CountDownLatch( threads );
        final Random r = new Random( 100 );
        for ( int i = 0; i < threads; ++i )
        {
            final Thread thread = new Thread( new RandomTask( r, i, cnt, latch ) );
            thread.start();
        }

        try {
            latch.await();//主线程
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private static void testRandomArray( final int threads, final long cnt, final int padding )
    {
        final CountDownLatch latch = new CountDownLatch( threads );
        final Random[] rnd = new Random[threads * padding];
        for ( int i = 0; i < threads * padding; ++i ) //allocate together
            rnd[ i ] = new Random( 100 );
        for ( int i = 0; i < threads; ++i )
        {
            final Thread thread = new Thread( new RandomTask( rnd[ i * padding ], i, cnt, latch ) );
            thread.start();
        }
        try {
            latch.await();//主线程
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void testTLRandom( final int threads, final long cnt )
    {
        final CountDownLatch latch = new CountDownLatch( threads );

        CyclicBarrier cyclicBarrier = new CyclicBarrier(threads);

        for ( int i = 0; i < threads; ++i )
        {
            final Thread thread = new Thread( new RandomTask( null, i, cnt, latch ) {
                @Override
                protected Random getRandom() {
                    return ThreadLocalRandom.current();
                }
            } );
            thread.start();
        }
        try {
            cyclicBarrier.await();
//            latch.await();//主线程
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    private static void testTL_Random( final int threads, final long cnt )
    {
        final CountDownLatch latch = new CountDownLatch( threads );
        final ThreadLocal<Random> rnd = new ThreadLocal<Random>() {
            @Override
            protected Random initialValue() {
                return new Random( 300 );
            }
        };
        for ( int i = 0; i < threads; ++i )
        {
            final Thread thread = new Thread( new RandomTask( null, i, cnt, latch ) {
                @Override
                protected Random getRandom() {
                    return rnd.get();
                }
            } );
            thread.start();
        }
        try {
            latch.await();//主线程
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
