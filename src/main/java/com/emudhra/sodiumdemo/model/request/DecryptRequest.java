/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emudhra.sodiumdemo.model.request;

/**
 *
 * @author prashanth
 */
public class DecryptRequest {

    public final String cipherText;

    public DecryptRequest(String cipherText) {
        this.cipherText = cipherText;
    }

    public String getCipherText() {
        return cipherText;
    }

}
