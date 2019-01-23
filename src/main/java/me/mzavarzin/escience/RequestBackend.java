package me.mzavarzin.escience;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;

public class RequestBackend {

	public static JedisPool pool = new JedisPool(new JedisPoolConfig(),
			System.getenv().containsKey("REDIS_HOST") ? System.getenv("REDIS_HOST") : "localhost");
	public static String oneuuid = UUID.randomUUID().toString();

}