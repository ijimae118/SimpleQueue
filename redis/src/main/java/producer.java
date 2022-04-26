import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class producer {
    public static void main(String... args) throws IOException {
//        RedissonClient redisson = Redisson.create();
//
//        final RQueue<String> queue = redisson.getQueue("myQueue");
//
//        (new Timer()).schedule(new TimerTask() {
//            public void run() {
////                Calendar.getInstance().getTime().toString()
//                queue.add(Integer.toString(new Date().hashCode()));
//                System.out.println(queue);
//            }
//        }, 0L, 1000L);

        final Jedis jedis = new Jedis("localhost", 6379);

        ScheduledExecutorService ses = Executors.newScheduledThreadPool(1);
        ses.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
            Long c = jedis.lpush("queue#task", Integer.toString(new Date().hashCode()));
            System.out.println(c);

            }
        },0,1,TimeUnit.SECONDS);


    }
}
