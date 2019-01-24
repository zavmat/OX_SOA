package me.mzavarzin.escience;

import java.io.IOException;
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

/**
 * Example JSON of Request
 */
	String jsonExampleData = 
			    "{"
				  +"\"id\" : \"ff64acd5-1af4-4f1f-b9b7-7fa6c422373\","
				  +"\"name\" : \"DNS Sequencing\","
				  +"\"userId\" : \"94b90ab9-661a-4fca-ad7e-99fd7b51c1a7\","
				  +"\"containerId\" : \"520705b5-39ba-40a7-9b5b-32b0500315e2\","
				  +"\"datasetId\" : \"ab525811-6027-4fef-97e0-f7d062173e61\","
				  +"\"jobId\" : \"0f19b1da-6bc9-4149-a038-d7694bae6831\","
				  +"\"resultId\" : \"3a55a4b0-0dba-4065-b597-1e4f40366ed2\","
				  +"\"status\" : \"created\","
			   +"}";


	/*
	 * Instantiate system with example data
	 */
	public RequestBackend(){

	

		try (Jedis jedis = pool.getResource()) {
			jedis.set(oneuuid, jsonExampleData);
		}
	}

	public String createRequest(String request) throws IOException {
		String uuid = UUID.randomUUID().toString();
		System.out.println("1-Backend create request construct new request from input json");
		Request entry = new Request(request);
		System.out.println("3-Adding request to redis");
		putRequestToRedis(uuid, entry.toJSON().toString());
		System.out.println("4-Done request in redis");
		return uuid;
	}

	public void putRequestToRedis(String k, String v) throws IOException{
		try (Jedis jedis = pool.getResource()) {
			jedis.set(k,v);
		}

	}

	public String getRequest(String id) throws NotFoundException, JSONException, IOException {
		if (!isKeyInRedis(id))
			throw new NotFoundException();

		System.out.println("Get request from redis by key: "+ id);
		String entry = getRequestFromRedis(id);
		
			return entry;
	}

	public String getRequestFromRedis(String uuid) throws NotFoundException, IOException {
		try (Jedis jedis = pool.getResource()) {
			String json = jedis.get(uuid);
			if (json == null) {
				throw new NotFoundException();
			}

			return json;
		}
	}

	public boolean isOrderInRedis(String uuid) {
		try (Jedis jedis = pool.getResource()) {
			return jedis.exists(uuid + ":json");
		}
	}

	/* Check if key exists in Redis */
	public boolean isKeyInRedis(String uuid) {
		try (Jedis jedis = pool.getResource()) {
			return jedis.exists(uuid);
		}
	}
}

