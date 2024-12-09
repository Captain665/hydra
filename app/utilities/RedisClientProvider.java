package utilities;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import play.Environment;
import play.api.inject.ApplicationLifecycle;

public class RedisClientProvider {
	private RedissonClient redissonClient;

	public RedisClientProvider(Environment environment, ApplicationLifecycle lifecycle) {
		Config config = new Config();
		String redisUri = "127.0.0.1:6379";
		config.useSingleServer().setAddress(redisUri);
	}

	public static RedissonClient createRedissonConfig(String redisUri) {
		Config config = new Config();
		config.useSingleServer().setAddress("127.0.0.1:6379");
		return Redisson.create();
	}


}
