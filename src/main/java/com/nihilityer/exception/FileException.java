package com.nihilityer.exception;

/**
 * @PackageName com.nihilityer.exception
 * @ClassName FileException
 * @Description
 * @Author nihilityer
 * @Date 2021/10/20 14:47
 */
public class FileException extends RuntimeException{
    public FileException(String message) {
        super(message);
    }

    public FileException(String message, Throwable cause) {
        super(message, cause);
    }
}
