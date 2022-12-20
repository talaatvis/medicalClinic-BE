package com.medicalclinic.app.exceptions;


import com.google.common.base.Objects;
import com.google.common.base.Strings;

/**
 * Generic business exception class
 *
 * @version $Id$
 *
 */
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 4610351811195369612L;

    private final String code;

    private static final String LOCAL_ERRORS_SYSTEM = "LOCAL";

    public BusinessException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public BusinessException(String code, String message) {
        super(message);
        this.code = code;
    }

    public BusinessException(String code, Throwable cause) {
        super(cause);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public String getSystem() {
        return LOCAL_ERRORS_SYSTEM;
    }

    public String getNativeCode() {
        return null;
    }

    public String getNativeMessage() {
        return getCause() != null ? getCause().getMessage() : null;
    }

    /**
     * Returns the complete exception code use internally by the application
     * @return Complete exception code
     */
    public String getExceptionCode() {
        return Strings.isNullOrEmpty(getNativeCode()) ? String.format("[%s - %s]", getCode(), getSystem()) : String.format("[%s - %s - %s]", getCode(), getSystem(), getNativeCode());
    }

    /**
     * Method that generates the exception information
     * @param extraInfo Returns extra information included in the message
     * @return String containing the message information
     */
    public String getExceptionInformation(boolean extraInfo) {
        if (!extraInfo) {
            return getMessage();
        }
        return Strings.isNullOrEmpty(getNativeMessage()) ? String.format("%s %s", getExceptionCode(), getMessage()) :  String.format("%s %s (%s)", getExceptionCode(), getMessage(), getNativeMessage());
    }



    @Override
    public String toString() {
        return "code:= " + getCode()
                + "message := "+ getMessage()
                + "system:= "+ getSystem()
                + "nativeCode" + getNativeCode();


    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}