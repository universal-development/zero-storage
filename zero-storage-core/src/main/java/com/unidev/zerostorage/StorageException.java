package com.unidev.zerostorage;

import com.unidev.platform.common.exception.CommonRuntimeException;

/**
 * Storage exception,  thrown if something went wrong...
 */
public class StorageException extends CommonRuntimeException {

    public StorageException() {
    }

    public StorageException(String message) {
        super(message);
    }

    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }

    public StorageException(Throwable cause) {
        super(cause);
    }
}
