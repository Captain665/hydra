package utilities;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import play.Logger;

import java.io.IOException;

public class RedisHelper {
	private final static Logger.ALogger logger = Logger.of("redisClientUtility");
	private static RedissonClient redissonClient;

	public static RedissonClient getHelperClient() {
		if (redissonClient == null) {
			synchronized (RedisHelper.class) {
				if (redissonClient == null) {
					try {
						Config config = Config.fromYAML(RedisHelper.class.getClassLoader().getResource("redisson.yaml"));
						redissonClient = Redisson.create(config);
						logger.info("Redisson client initialized successfully");
					} catch (IOException e) {
						logger.error("Failed to initialize Redisson client", e);
						throw new RuntimeException("Failed to initialize Redisson client", e);
					}
				}
			}
		}
		return redissonClient;
	}


	public static void shutdown() {
		if (redissonClient != null) {
			redissonClient.shutdown();
		}
	}

}
