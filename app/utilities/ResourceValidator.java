package utilities;

import jakarta.validation.*;
import play.Logger;

import java.util.Set;


public class ResourceValidator<T> {
	private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	private final Validator validator = factory.getValidator();
	private final Logger.ALogger logger = Logger.of("utilities.Validation");

	public String resourcePreValidation(T resource) {
		logger.info("pre validation start ...");
		Set<ConstraintViolation<T>> violations = validator.validate(resource);
		if (!violations.isEmpty()) {
			final StringBuilder error = new StringBuilder();
			for (ConstraintViolation<T> violation : violations) {
				error.append(", ").append(violation.getMessage());
			}
			logger.info("error is found in the pre-validation");
			return error.toString();
		}
		logger.info("No error found in the pre-validation");
		return null;
	}
}
