package ${package_name}.server.exception;

import java.util.Set;

import javax.validation.ConstraintViolation;

public class BusinessValidationException extends RuntimeException{

/**
*
*/
private static final long serialVersionUID = 1202206903538794942L;

private Set<ConstraintViolation<Object>> constraintViolations;

    public BusinessValidationException(
    Set<ConstraintViolation<Object>> constraintViolations) {
        super();
        this.constraintViolations = constraintViolations;
        }

        public Set<ConstraintViolation<Object>> getConstraintViolations() {
            return constraintViolations;
            }

            public void setConstraintViolations(
            Set<ConstraintViolation<Object>> constraintViolations) {
                this.constraintViolations = constraintViolations;
                }
                }
