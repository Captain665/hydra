package utilities;

import play.inject.ApplicationLifecycle;

import java.util.concurrent.CompletableFuture;

public class RedisModule {

	public RedisModule(ApplicationLifecycle lifecycle) {
		RedisClientUtilities.getClient();

		lifecycle.addStopHook(() -> {
			RedisClientUtilities.shutdown();
			return CompletableFuture.completedFuture(null);
		});
	}
}
