import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.redisson.Redisson;
import org.redisson.api.RQueue;
import org.redisson.api.RedissonClient;
import redis.clients.jedis.Jedis;

public class consumer {
    public static void main(String... args) throws IOException {
//        RedissonClient redisson = Redisson.create();
//
//        final RQueue<String> queue = redisson.getQueue("queue#tasks");
//
//        (new Timer()).schedule(new TimerTask() {
//            public void run() {
//                System.out.println(queue.poll());
//            }
//        }, 1000L, 2000L);

        final Jedis jedis = new Jedis("localhost", 6379);

        ScheduledExecutorService ses = Executors.newScheduledThreadPool(1);
        ses.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
            String c = jedis.rpop("queue#task");
            System.out.println(c);
            }
        },1,2, TimeUnit.SECONDS);

    }
}
