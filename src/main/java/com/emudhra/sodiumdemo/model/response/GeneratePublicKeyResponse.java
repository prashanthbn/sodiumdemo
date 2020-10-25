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
public class GeneratePublicKeyResponse {
    public final String publicKey;

    public GeneratePublicKeyResponse(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getPublicKey() {
        return publicKey;
    }
    
}
