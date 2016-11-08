package com.egou.utils;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;



import com.egou.utils.basic.PropertyFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;



public class RedisUtil {

	private static Log logger = LogFactory.getLog(RedisUtil.class);

	// Redis鏈嶅姟鍣↖P
	private static String ADDR_ARRAY = PropertyFactory.getPropertyValue("redis", "redis.ip");

	// Redis鐨勭鍙ｅ彿
	private static int PORT = Integer.parseInt(PropertyFactory.getPropertyValue("redis", "redis.port"));

	// 璁块棶瀵嗙爜
	private static String AUTH = PropertyFactory.getPropertyValue("redis", "redis.auth");

	// 鍙敤杩炴帴瀹炰緥鐨勬渶澶ф暟鐩紝榛樿鍊间负8锟�?
	// 濡傛灉璧嬶拷?锟戒负-1锛屽垯琛ㄧず涓嶉檺鍒讹紱濡傛灉pool宸茬粡鍒嗛厤浜唌axActive涓猨edis瀹炰緥锛屽垯姝ゆ椂pool鐨勭姸鎬佷负exhausted(鑰楀敖)锟�?
	private static int MAX_ACTIVE = Integer.parseInt(PropertyFactory.getPropertyValue("redis", "maxActive"));;

	// 鎺у埗锟�?涓猵ool锟�?澶氭湁澶氬皯涓姸鎬佷负idle(绌洪棽锟�?)鐨刯edis瀹炰緥锛岄粯璁わ拷?锟戒篃锟�?8锟�?
	private static int MAX_IDLE = Integer.parseInt(PropertyFactory.getPropertyValue("redis", "maxIdle"));;

	// 绛夊緟鍙敤杩炴帴鐨勬渶澶ф椂闂达紝鍗曚綅姣锛岄粯璁わ拷?锟戒负-1锛岃〃绀烘案涓嶈秴鏃讹拷?锟藉鏋滆秴杩囩瓑寰呮椂闂达紝鍒欑洿鎺ユ姏鍑篔edisConnectionException锟�?
	private static int MAX_WAIT = Integer.parseInt(PropertyFactory.getPropertyValue("redis", "maxWait"));;

	// 瓒呮椂鏃堕棿
	private static int TIMEOUT = Integer.parseInt(PropertyFactory.getPropertyValue("redis", "timeOut"));;

	// 鍦╞orrow锟�?涓猨edis瀹炰緥鏃讹紝鏄惁鎻愬墠杩涜validate鎿嶄綔锛涘鏋滀负true锛屽垯寰楀埌鐨刯edis瀹炰緥鍧囨槸鍙敤鐨勶紱
	private static boolean TEST_ON_BORROW = Boolean.valueOf(PropertyFactory.getPropertyValue("redis", "testOnBorrow"));

	// 鍦╞orrow锟�?涓猨edis瀹炰緥鏃讹紝鏄惁鎻愬墠杩涜validate鎿嶄綔锛涘鏋滀负true锛屽垯寰楀埌鐨刯edis瀹炰緥鍧囨槸鍙敤鐨勶紱
	private static boolean TEST_ON_RETURN = Boolean.valueOf(PropertyFactory.getPropertyValue("redis", "testOnReturn"));

	private static JedisPool jedisPool = null;

	/**
	 * redis杩囨湡鏃堕棿,浠ョ涓哄崟锟�?
	 */
	public final static int EXRP_HOUR = 60 * 60; // 锟�?灏忔椂
	public final static int EXRP_DAY = 60 * 60 * 24; // 锟�?锟�?
	public final static int EXRP_MONTH = 60 * 60 * 24 * 30; // 锟�?涓湀

	/**
	 * 鍒濆鍖朢edis杩炴帴锟�?
	 */
	private static void initialPool() {

			JedisPoolConfig config = new JedisPoolConfig();
			config.setMaxActive(MAX_ACTIVE);
			config.setMaxIdle(MAX_IDLE);
			config.setMaxWait(MAX_WAIT);
			config.setTestOnBorrow(TEST_ON_BORROW);
			config.setTestOnReturn(TEST_ON_RETURN);
			try{
				jedisPool = new JedisPool(config, ADDR_ARRAY, PORT, TIMEOUT);
				Jedis js=jedisPool.getResource();
				jedisPool.returnResource(js);
			}
			catch(Exception ex)
			{
				jedisPool = new JedisPool(config, ADDR_ARRAY, PORT, TIMEOUT, AUTH);
			}
		
		
	}

	/**
	 * 鍦ㄥ绾跨▼鐜鍚屾鍒濆锟�?
	 */
	private static synchronized void poolInit() {
		if (jedisPool == null) {
			initialPool();
		}
	}

	/**
	 * 鍚屾鑾峰彇Jedis瀹炰緥
	 * 
	 * @return Jedis
	 */
	public synchronized static Jedis getJedis() {
		try {
			if (jedisPool == null) {
				poolInit();
			}
			return jedisPool.getResource();
		} catch (Exception e) {
			logger.error("Get jedis error : " + e);
			return null;
		}
	}

	/**
	 * 閲婃斁jedis璧勬簮
	 * 
	 * @param jedis
	 */
	public static void returnResource(final Jedis jedis) {
		if (jedis != null && jedisPool != null) {
			jedisPool.returnResource(jedis);
		}
	}

	/**
	 * 
	 * @Title: setString
	 * @Description: 璁剧疆string
	 * @param @param key
	 * @param @param value
	 * @return void
	 * @throws
	 */
	public static void setString(String key, String value) {
		value = StringUtils.isEmpty(value) ? "" : value;
		Jedis jedis = getJedis();
		try {
			jedis.set(key, value);
		} catch (Exception e) {
//			logger.error("setString(key,value) error : " + e);
		} finally {
			if (jedis != null)
				returnResource(jedis);
		}
	}

	/**
	 * 
	 * @Title: setString
	 * @Description: 璁剧疆string鍙婅繃鏈熸椂锟�?
	 * @param @param key
	 * @param @param seconds
	 * @param @param value
	 * @return void
	 * @throws
	 */
	public static void setString(String key, String value, int seconds) {
		value = StringUtils.isEmpty(value) ? "" : value;
		Jedis jedis = getJedis();
		try {
			jedis.setex(key, seconds, value);
		} catch (Exception e) {
//			logger.error("setString(key,seconds,value) error : " + e);
		} finally {
			if (jedis != null)
				returnResource(jedis);
		}
	}

	/**
	 * 
	 * @Title: getString
	 * @Description: 鑾峰彇String锟�?
	 * @param @param key
	 * @param @return
	 * @return String
	 * @throws
	 */
	public static String getString(String key) {
		Jedis jedis = getJedis();
		try {
			String value = jedis.get(key);
			return value;
		} catch (Exception e) {
//			logger.error("getString(key) error : " + e);
			return null;
		} finally {
			if (jedis != null)
				returnResource(jedis);
		}
	}

	public static void setByte(byte[] key, byte[] value) {
		Jedis jedis = getJedis();
		try {
			jedis.set(key, value);
		} catch (Exception e) {
//			logger.error("setByte(byte,value) error : " + e);
		} finally {
			if (jedis != null)
				returnResource(jedis);
		}
	}

	public static void setByte(byte[] key, byte[] value, int seconds) {
		Jedis jedis = getJedis();
		try {
			jedis.setex(key, seconds, value);
		} catch (Exception e) {
//			logger.error("setByte(byte,value,seconds) error : " + e);
		} finally {
			if (jedis != null)
				returnResource(jedis);
		}
	}

	public static byte[] getByte(byte[] key) {
		Jedis jedis = getJedis();
		try {
			byte[] value = jedis.get(key);
			return value;
		} catch (Exception e) {
//			logger.error("getByte(key) error : " + e);
			return null;
		} finally {
			if (jedis != null)
				returnResource(jedis);
		}
	}

	public static void setObject(String key, Object value) {
		if(value==null)
			return;
		Jedis jedis = getJedis();
		try {
			jedis.set(key.getBytes(), SerializeUtil.serialize(value));
		} catch (Exception e) {
//			logger.error("setObject(byte,value) error : " + e);
		} finally {
			if (jedis != null)
				returnResource(jedis);
		}
	}

	public static void setObject(String key, Object value, int seconds) {
		if(value==null)
			return;
		Jedis jedis = getJedis();
		try {
			jedis.setex(key.getBytes(), seconds, SerializeUtil.serialize(value));
		} catch (Exception e) {
//			logger.error("setObject(byte,value,seconds) error : " + e);
		} finally {
			if (jedis != null)
				returnResource(jedis);
		}
	}

	public static Object getObject(String key) {
		Jedis jedis = getJedis();
		try {
			byte[] object = jedis.get(key.getBytes());
			return (Object) SerializeUtil.unserialize(object);
		} catch (Exception ex) {
			return null;
		} finally {
			if (jedis != null)
				returnResource(jedis);
		}
	}

	public static Long delete(String key) {
		Jedis jedis = getJedis();
		try {
			return jedis.del(key);
		} catch (Exception e) {
//			logger.error("delString(key) error : " + e);
			return null;
		} finally {
			if (jedis != null)
				returnResource(jedis);
		}
	}

	public static Long delete(byte[] key) {
		Jedis jedis = getJedis();
		try {
			return jedis.del(key);
		} catch (Exception e) {
//			logger.error("delString(key) error : " + e);
			return null;
		} finally {
			if (jedis != null)
				returnResource(jedis);
		}
	}

	public static void setExpire(String key, int secondes) {
		Jedis jedis = getJedis();
		try {
			Object obj = new Object();
			try {
				byte[] object = jedis.get(key.getBytes());
				obj = (Object) SerializeUtil.unserialize(object);
			} catch (Exception e) {
				obj = null;
			}
			if (obj != null)
				jedis.expire(key.getBytes(), secondes);
		} catch (Exception e) {
//			logger.error("setExpire(key) error : " + e);
		}
		// 閲婃斁杩炴帴
		finally {
			if (jedis != null)
				returnResource(jedis);
		}
	}

	public static void setExpire(byte[] key, int secondes) {
		Jedis jedis = getJedis();
		try {
			if (key != null)
				jedis.expire(key, secondes);
		} catch (Exception e) {
//			logger.error("setExpire(key) error : " + e);
		}
		// 閲婃斁杩炴帴
		finally {
			if (jedis != null)
				returnResource(jedis);
		}
	}

}
