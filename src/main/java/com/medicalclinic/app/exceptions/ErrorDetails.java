package com.medicalclinic.app.exceptions;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;

import javax.validation.constraints.NotNull;

/**
 * Used for more details on errors, used for example for field error details validations.
 */
public class ErrorDetails   {
    @JsonProperty("target")
    private String target = null;

    @JsonProperty("code")
    private String code = null;

    @JsonProperty("message")
    private String message = null;

    public ErrorDetails target(String target) {
        this.target = target;
        return this;
    }

    /**
     * code descriptive of the operation / field
     * @return target
     **/
    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public ErrorDetails code(String code) {
        this.code = code;
        return this;
    }

    /**
     * well know key describing the error type
     * @return code
     **/
    @NotNull
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ErrorDetails message(String message) {
        this.message = message;
        return this;
    }

    /**
     * human readable explanation of the error
     * @return message
     **/
    @NotNull
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ErrorDetails errorDetails = (ErrorDetails) o;
        return Objects.equal(this.target, errorDetails.target) &&
                Objects.equal(this.code, errorDetails.code) &&
                Objects.equal(this.message, errorDetails.message);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(target, code, message);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ErrorDetails {\n");

        sb.append("    target: ").append(toIndentedString(target)).append("\n");
        sb.append("    code: ").append(toIndentedString(code)).append("\n");
        sb.append("    message: ").append(toIndentedString(message)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}