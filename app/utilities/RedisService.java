package utilities;

import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import play.Logger;

import java.util.concurrent.TimeUnit;

public class RedisService {
	private final RedissonClient redissonClient;
	private final Logger.ALogger logger = Logger.of("redisService");


	public RedisService() {
		this.redissonClient = RedisClientUtilities.getClient();
	}

	public void setClient(String key, String value) {
		RBucket<String> bucket = redissonClient.getBucket(key);
		bucket.set(value, 5, TimeUnit.MINUTES);
	}

	public String getClient(String key) {
		RBucket<String> bucket = redissonClient.getBucket(key);
		return bucket.get();
	}
}
