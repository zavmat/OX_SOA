package me.mzavarzin.escience;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RequestBackend {

	public static JedisPool pool = new JedisPool(new JedisPoolConfig(),
			System.getenv().containsKey("REDIS_HOST") ? System.getenv("REDIS_HOST") : "localhost");
	public static String oneuuid = UUID.randomUUID().toString();

	/**
	 * Example JSON of Request
	 */
	String jsonExampleData = "{" + "\"id\" : \"ff64acd5-1af4-4f1f-b9b7-7fa6c422373\","
			+ "\"name\" : \"DNS Sequencing\"," + "\"userId\" : \"94b90ab9-661a-4fca-ad7e-99fd7b51c1a7\","
			+ "\"containerId\" : \"520705b5-39ba-40a7-9b5b-32b0500315e2\","
			+ "\"datasetId\" : \"ab525811-6027-4fef-97e0-f7d062173e61\","
			+ "\"jobId\" : \"0f19b1da-6bc9-4149-a038-d7694bae6831\","
			+ "\"resultId\" : \"3a55a4b0-0dba-4065-b597-1e4f40366ed2\"," + "\"status\" : \"created\"," + "}";

	/*
	 * Instantiate system with example data
	 */
	public RequestBackend() {

		try (Jedis jedis = pool.getResource()) {
			jedis.set(oneuuid, jsonExampleData);
		}
	}

	public String createRequest(String request) throws IOException {
		String uuid = UUID.randomUUID().toString();
		Request entry = new Request(request);
		putRequestToRedis(uuid, entry.toJSON().toString());
		return uuid;
	}

	public void putRequestToRedis(String k, String v) throws IOException {
		try (Jedis jedis = pool.getResource()) {
			jedis.set(k, v);
		}

	}

	public String getRequest(String id) throws NotFoundException, JSONException, IOException {
		if (!isKeyInRedis(id))
			throw new NotFoundException();

		String entry = getRequestFromRedis(id);
		System.out.println("Get request format from redis by key: " + entry);
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

	public boolean isRequestInRedis(String uuid) {
		try (Jedis jedis = pool.getResource()) {
			return jedis.exists(uuid);
		}
	}

	/* Check if key exists in Redis */
	public boolean isKeyInRedis(String uuid) {
		try (Jedis jedis = pool.getResource()) {
			return jedis.exists(uuid);
		}
	}

	public String getRequests() throws IOException {
		JSONArray array = new JSONArray();
		Set<String> keys = getRequestIDs();

		Iterator<String> i = keys.iterator();
		while (i.hasNext()) {
			String uuid = i.next();

			try {
				String entry = getRequestFromRedis(uuid);
				System.out.println("Entry " + i + ": " + entry);
				Request r = new Request(entry);

				JSONObject href = new JSONObject();

				href.put("href", uuid);
				href.put("name", r.getName());
				array.put(href);

			} catch (NotFoundException nfe) {
				// serious error here
			}
		}

		JSONObject json = new JSONObject();

		json.put("requests", array);
		return json.toString(3); // indent 3 for nicer printing!
	}

	private Set<String> getRequestIDs() {
		try (Jedis jedis = pool.getResource()) {

			Set<String> uuids = jedis.keys("*");
			System.out.println("uuids: " + uuids);
			return uuids;
		}
	}

	public void updateRequest(String uuid, String input) throws IOException, NotFoundException {
		if (uuid == null || !isRequestInRedis(uuid)) {
			throw new NotFoundException();
		}

		putRequestToRedis(uuid, input);

	}

	public boolean deleteRequest(String id) throws NotFoundException {
		if (isRequestInRedis(id)) {

			try {
				String entry = getRequestFromRedis(id);
			} catch (NotFoundException | IOException e) {
				throw new NotFoundException();
			}
			Jedis jedis = pool.getResource();
			jedis.del(id);
			return true;
		} else {
			throw new NotFoundException();
		}
	}
	
}

