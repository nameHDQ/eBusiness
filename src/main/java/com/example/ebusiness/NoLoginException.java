package com.example.ebusiness;

/**
 * @author hdq
 * @create 2021-10-07 12:20
 */
public class NoLoginException extends Exception {

    private static final long serialVersionUID = 1L;
    public NoLoginException() {
        super();
    }

    public NoLoginException(String msg) {
        super(msg);
    }
}
