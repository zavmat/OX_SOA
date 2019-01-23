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

	private static final String STRING = "}";
	public static JedisPool pool = new JedisPool(new JedisPoolConfig(),
			System.getenv().containsKey("REDIS_HOST") ? System.getenv("REDIS_HOST") : "localhost");
	public static String oneuuid = UUID.randomUUID().toString();



	/*
	 * Instantiate system with example data
	 */
	public RequestBackend(){

	

		try (Jedis jedis = pool.getResource()) {
			jedis.set(oneuuid, "" + "{'name':'DNS Sequencing'," + "'userid':'3df7af2b-c56a-4eaf-b209-ef7bc31cbaf2',"
			+ "'containerId':'f28c726f-193b-4389-9585-77c565a395e6'," + "'datasetId':'91a6a332-35ac-4ae8-b009-fb68f1931b1b'," 
			+ "'jobId':'1'," + "'resultId':'2'"+"'status':'running'"+STRING);
		}
	}

}

