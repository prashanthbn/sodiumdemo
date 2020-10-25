/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emudhra.sodiumdemo.model.response;

/**
 *
 * @author prashanth
 */
public class DecryptResponse {

    public final String decrypted;

    public DecryptResponse(String decrypted) {
        this.decrypted = decrypted;
    }

    public String getDecrypted() {
        return decrypted;
    }
}
