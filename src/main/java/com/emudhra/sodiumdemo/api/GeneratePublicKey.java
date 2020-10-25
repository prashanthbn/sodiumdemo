/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emudhra.sodiumdemo.api;

import com.emudhra.sodiumdemo.model.response.GeneratePublicKeyResponse;
import com.emudhra.sodiumdemo.model.Response;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.muquit.libsodiumjna.SodiumKeyPair;
import com.muquit.libsodiumjna.SodiumLibrary;
import com.muquit.libsodiumjna.SodiumUtils;
import com.muquit.libsodiumjna.exceptions.SodiumLibraryException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author prashanth
 */
public class GeneratePublicKey extends HttpServlet {

    private static final Logger logger;
    private static final Gson gson;

    static {
        logger = Logger.getLogger(GeneratePublicKey.class.getName());
        gson = new GsonBuilder().create();
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            HttpSession session = request.getSession();
            String libraryPath = "C:/libsodium/libsodium.dll";
            logger.log(Level.INFO, "Library path in Windows: {0}", libraryPath);
            System.out.println("Hello World!");
            SodiumLibrary.setLibraryPath(libraryPath);
            String v = SodiumLibrary.libsodiumVersionString();
            logger.log(Level.INFO, "libsodium version: {0}", v);
            SodiumKeyPair kp = SodiumLibrary.cryptoBoxKeyPair();
            session.setAttribute("kp", kp);
            byte[] publicKey = kp.getPublicKey();
            byte[] privateKey = kp.getPrivateKey();
            String hexPublicKey = SodiumUtils.binary2Hex(publicKey);
            out.write(gson.toJson(new Response<>(1, null, new GeneratePublicKeyResponse(hexPublicKey))));
        } catch (SodiumLibraryException ex) {
            Logger.getLogger(GeneratePublicKey.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the
    // + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
