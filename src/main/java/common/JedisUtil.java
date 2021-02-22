package common;

import org.apache.commons.lang3.StringUtils;

import dictionary.ServerTypeEnum;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;


public class JedisUtil {
    private static int MAX_ACTIVE = 100;//连接实例的最大连接数
    private static int MAX_IDLE = 100;//控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
    private static int MAX_WAIT = -1;//等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException
    private static int TIMEOUT = 2000;//连接超时的时间
    private static boolean TEST_ON_BORROW = true;// 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
    private static JedisPool jedisPool = null;
    
    /**
     * 获取测试环境Jedis实例
     */
    public synchronized static Jedis getJedis() {
        try {
            if (jedisPool != null) {
                Jedis resource = jedisPool.getResource();
                return resource;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static void init(int serverType) {
        PropertiesUtil.loadFile("jedis.properties");
        String server = null;
        String port = null;
        String auth = null;
        if (serverType == ServerTypeEnum.DEV.getValue()) {
            server = PropertiesUtil.getPropertyValue("dev.redis.server");
            port = PropertiesUtil.getPropertyValue("dev.redis.port");
            auth = PropertiesUtil.getPropertyValue("dev.redis.auth");
        } else if (serverType == ServerTypeEnum.REAL.getValue()) {
            server = PropertiesUtil.getPropertyValue("real.redis.server");
            port = PropertiesUtil.getPropertyValue("real.redis.port");
            auth = PropertiesUtil.getPropertyValue("real.redis.auth");
        }
        if (StringUtils.isNotBlank(server) && StringUtils.isNumeric(port) && StringUtils.isNotBlank(server)) {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(MAX_ACTIVE);
            config.setMaxIdle(MAX_IDLE);
            config.setMaxWaitMillis(MAX_WAIT);
            config.setTestOnBorrow(TEST_ON_BORROW);
            jedisPool = new JedisPool(config, server, Integer.parseInt(port), TIMEOUT, auth);
        }
    }
    
    /***
     * 
     * 释放资源
     */
    public static void close(final Jedis jedis) {
        if(jedis != null) {
            jedis.close();
        }
    }
}
