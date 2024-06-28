//CarmenSandiegoUruguay/src/main/java/com.ejemplo.carmenuy/exception/PistaException.java
package com.ejemplo.carmenuy.exception;

public class PistaException extends RuntimeException {
    public PistaException(String message) {
        super(message);
    }

    public PistaException(String message, Throwable cause) {
        super(message, cause);
    }
}
