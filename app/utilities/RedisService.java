package utilities;

import common.user.resources.UserResource;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import play.Logger;

import java.util.concurrent.TimeUnit;

public class RedisService {
	private final RedissonClient redissonClient;
	private final Logger.ALogger logger = Logger.of("redisService");


	public RedisService() {
		this.redissonClient = RedisHelper.getClient();
	}

	public void setClient(String key, UserResource value) {
		RBucket<UserResource> bucket = redissonClient.getBucket(key);
		bucket.set(value, 5, TimeUnit.MINUTES);
	}

	public UserResource getClient(String key) {
		RBucket<UserResource> bucket = redissonClient.getBucket(key);
		logger.info("redis data " + bucket.get());
		return bucket.get();
	}
}
