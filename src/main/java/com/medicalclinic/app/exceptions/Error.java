package com.medicalclinic.app.exceptions;


import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;


public class Error {
    /**
     * one of the values accepted on enum related
     */
    public enum CodeEnum {
        ERROR("error"),
        EXCEPTION("exception"),
        ;
        private String value;

        CodeEnum(String value) {
            this.value = value;
        }

        @Override
        @JsonValue
        public String toString() {
            return String.valueOf(value);
        }

        @JsonCreator
        public static CodeEnum fromValue(String text) {
            for (CodeEnum b : CodeEnum.values()) {
                if (String.valueOf(b.value).equals(text)) {
                    return b;
                }
            }
            return null;
        }
    }

    public enum NativeCodeEnum {

        PATIENT_NOT_FOUND(9000, "PATIENT_NOT_FOUND"),
        NO_APPOINTMENTS_EXIST(9001, "NO_APPOINTMENTS_EXIST"),
        PHYSICIAN_NOT_FOUND(9003, "PATIENT_NOT_FOUND"),
        APPOINTMENT_FOUND(9002,"APPOINTMENT_FOUND");
        private Integer value;
        private String code;

        NativeCodeEnum(Integer value, String code) {
            this.value = value;
            this.code = code;
        }
        @Override
        @JsonValue
        public String toString() {
            return String.valueOf(value);
        }

        @JsonCreator
        public static NativeCodeEnum fromCode(String text) {
            for (NativeCodeEnum b : NativeCodeEnum.values()) {
                if (String.valueOf(b.code).equals(text)) {
                    return b;
                }
            }
            return null;
        }

        public Integer getValue() {
            return value;
        }

        public String getCode() {
            return code;
        }
    }



    @JsonProperty("code")
    private CodeEnum code = CodeEnum.ERROR;

    @JsonProperty("nativeCode")
    private Integer nativeCode = null;

    @JsonProperty("message")
    private String message = null;

    @JsonProperty("reference")
    private String reference = null;


    @JsonProperty("target")
    private String target = null;

    @JsonProperty("details")
    private List<ErrorDetails> details = new ArrayList<ErrorDetails>();

    public Error code(CodeEnum code) {
        this.code = code;
        return this;
    }

    public Error() {
        super();
    }
    /**
     * one of the values accepted on enum related
     * @return code
     **/
    @NotNull
    public CodeEnum getCode() {
        return code;
    }

    public void setCode(CodeEnum code) {
        this.code = code;
    }

    public Error message(String message) {
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

    public Error reference(String reference) {
        this.reference = reference;
        return this;
    }

    /**
     * uniqueid key used as reference between this instance error and the user
     * @return reference
     **/
    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }







    public Error target(String target) {
        this.target = target;
        return this;
    }

    /**
     * code descriptive of the operation
     * @return target
     **/
    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public Error details(List<ErrorDetails> details) {
        this.details = details;
        return this;
    }

    public Error addDetailsItem(ErrorDetails detailsItem) {
        this.details.add(detailsItem);
        return this;
    }

    /**
     * Get details
     * @return details
     **/
    public List<ErrorDetails> getDetails() {
        return details;
    }

    public void setDetails(List<ErrorDetails> details) {
        this.details = details;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Error error = (Error) o;
        return Objects.equal(this.code, error.code) &&
                Objects.equal(this.message, error.message) &&
                Objects.equal(this.reference, error.reference) &&
                Objects.equal(this.target, error.target) &&
                Objects.equal(this.details, error.details)&&
                Objects.equal(this.nativeCode, error.nativeCode);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(code, message, reference, target, details, nativeCode);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(Error.class).omitNullValues()
                .add("code", code)
                .add("message", message)
                .add("reference", reference)
                .add("target", target)
                .add("details", details)
                .add("nativeCode", nativeCode)
                .toString();
    }

    public Integer getNativeCode() {
        return nativeCode;
    }

    public void setNativeCode(Integer nativeCode) {
        this.nativeCode = nativeCode;
    }
}