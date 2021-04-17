package com.my.blog.spring.myblog.exeption;

public class TooManySymbolsException extends RuntimeException{
    public TooManySymbolsException(String message) {
        super(message);
    }
}
