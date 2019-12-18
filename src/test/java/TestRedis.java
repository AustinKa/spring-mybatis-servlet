import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class TestRedis {


    @Test
    public void testRedis() {

        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.set("username","zhangsan");
        String s = jedis.get("username");
        System.out.println(s);
        jedis.close();


    }


    @Test
    public void testRedisPool() {

        JedisPool jedisPool = new JedisPool("127.0.0.1", 6379);
        Jedis jedis = jedisPool.getResource();
        jedis.set("username","lisi");
        String s = jedis.get("username");
        System.out.println(s);
        jedis.close();
        jedisPool.close();


    }


}
