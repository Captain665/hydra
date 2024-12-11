package utilities;

import common.user.resources.UserResource;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import play.Logger;

import java.util.concurrent.TimeUnit;

public class RedisService<T> {
	private final RedissonClient redissonClient;
	private final Logger.ALogger logger = Logger.of("redisService");


	public RedisService() {
		this.redissonClient = RedisHelper.getClient();
	}

	public void setClient(String key, T value) {
		logger.info("redis data updating for " + key + " and value -> " + value);
		RBucket<T> bucket = redissonClient.getBucket(key);
		bucket.set(value, 10, TimeUnit.MINUTES);
	}

	public T getClient(String key) {
		RBucket<T> bucket = redissonClient.getBucket(key);
		logger.info("redis data found for " + key + " value -> " + bucket.get());
		return bucket.get();
	}
}
