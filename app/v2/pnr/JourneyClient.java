package v2.pnr;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.asynchttpclient.AsyncHttpClient;
import play.Logger;
import play.mvc.Http;
import play.mvc.Result;

import java.time.Duration;
import java.util.Optional;
import java.util.concurrent.CompletionStage;

import static java.util.concurrent.CompletableFuture.supplyAsync;
import static org.asynchttpclient.Dsl.asyncHttpClient;
import static org.asynchttpclient.Dsl.config;

public class JourneyClient {
	private final AsyncHttpClient httpClient = asyncHttpClient(config().setRequestTimeout(Duration.ofSeconds(5000)));
	private final ObjectMapper objectMapper = new ObjectMapper();
	private final Logger.ALogger logger = Logger.of("application.journeyClient");
	private final String JOURNEY_URL = "https://stage-ecatering.ipsator.com/api/v1/pnr/vendor?pnr=";
	private final String AUTHORIZATION = "e90221a5-9b45-4564-80a0-9ba49a97c13e";

	public CompletionStage<Optional<JourneyClientResource>> getJourneyInfoByPNR(Long pnr, Http.Request request) {
		return httpClient.prepareGet(JOURNEY_URL + pnr).addHeader("AUTHORIZATION", AUTHORIZATION)
				.execute().toCompletableFuture().exceptionally(throwable -> {
					logger.error("exception when getting pnr info for PNR: " + pnr + throwable);
					return null;
				}).thenApplyAsync(response -> {
					if (response.getStatusCode() >= 400) {
						logger.error("[" + request.id() + "] " + "response for client " + response.getResponseBody());
						return Optional.empty();
					}
					try {
						logger.info("[" + request.id() + "] " + "response for client " + response.getResponseBody());
						return Optional.of(objectMapper.readValue(response.getResponseBody(), JourneyClientResource.class));
					} catch (Exception e) {
						logger.error("[" + request.id() + "] " + "response for client " + response.getResponseBody());
						return Optional.empty();
					}
				});
	}


}
