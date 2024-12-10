package utilities;

import com.fasterxml.jackson.databind.ObjectMapper;
import play.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RedisSerialize<T> {

	private final Class<T> type;

	public RedisSerialize(Class<T> type) {
		this.type = type;
	}

	public T deserialize(String redisResponse) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		String validJson = convertToValidJson(redisResponse);
		return mapper.readValue(validJson, type);
	}


	private String convertToValidJson(String json) {
		String validJson = json
				.replace("UserResource{", "{")
				.replace("}", "")
				.replace("=", ":")
				.replace("'", "\"");

		validJson = validJson.replaceAll("([a-zA-Z0-9_]+):", "\"$1\":");

		Pattern permissionsPattern = Pattern.compile("\\[([^\\]]+)\\]");
		Matcher matcher = permissionsPattern.matcher(validJson);

		StringBuffer sb = new StringBuffer();
		while (matcher.find()) {
			String permissions = matcher.group(1);
			String quotedPermissions = permissions.replaceAll("([A-Za-z_]+)", "\"$1\"");
			matcher.appendReplacement(sb, "[" + quotedPermissions + "]");
		}
		matcher.appendTail(sb);
		sb.append("}");
		return sb.toString();
	}


}
