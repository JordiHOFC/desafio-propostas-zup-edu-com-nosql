package br.com.zupedu.propostasComNoSql.validator;

import org.hibernate.validator.constraints.CompositionType;
import org.hibernate.validator.constraints.ConstraintComposition;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.FIELD;
import javax.validation.Constraint;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@CPF
@CNPJ
@ConstraintComposition(CompositionType.OR)
@Constraint(validatedBy = {})
@Target({ FIELD })
@ReportAsSingleViolation
public @interface CPForCNPJ {
    String message() default "O documento deve esta em um formato valido";
    Class<?>[] groups()  default {};
    Class<? extends Payload>[] payload() default {};

}
