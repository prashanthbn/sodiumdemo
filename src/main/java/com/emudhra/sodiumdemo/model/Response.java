/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emudhra.sodiumdemo.model;

/**
 *
 * @author prashanth
 * @param <T>
 */
public class Response<T> {

    public final int status;
    public final String errorMessage;
    public final T data;

    public Response(int status, String errorMessage, T data) {
        this.status = status;
        this.errorMessage = errorMessage;
        this.data = data;
    }

    public Response(int status, String errorMessage) {
        this.status = status;
        this.errorMessage = errorMessage;
        this.data = null;
    }

    public int getStatus() {
        return status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public T getData() {
        return data;
    }

}
