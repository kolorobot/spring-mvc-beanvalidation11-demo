package pl.codeleak.demo.custom;

import org.apache.commons.beanutils.BeanUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class OneOfValidator implements ConstraintValidator<OneOf, Object> {

	private String[] fields;

	@Override
	public void initialize(OneOf constraintAnnotation) {
		fields = constraintAnnotation.fields();
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		if (fields == null || fields.length == 0)
			return true;
		try {
			int notNullFieldCount = 0;
			for (String field : fields) {
				Object fieldObj = BeanUtils.getProperty(value, field);
				if (fieldObj != null) {
					if(fieldObj instanceof String) {
						if(!isNullOrEmpty((String)fieldObj)) {
							notNullFieldCount++;
						}
					} else {
						notNullFieldCount++;
					}
				}
			}
			return notNullFieldCount == 1;
		} catch (Exception e) {
			// ignore
		}
		return false;
	}

	private boolean isNullOrEmpty(String str) {
		return str == null || str.isEmpty();
	}

}
