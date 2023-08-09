package question2.validation;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class EntityValidator {
    private static final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    public static final Validator validator = validatorFactory.getValidator();
}
