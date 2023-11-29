package com.google.authenticator.controller;

import com.google.authenticator.auth.GoogleAuthenticator;
import com.google.authenticator.comm.Response;
import com.google.authenticator.po.GoogleAuthPo;
import com.google.authenticator.service.GoogleAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/googleAuth/v1")
public class GoogleAuthController {

    @Autowired
    private GoogleAuthService googleAuthService;

    @GetMapping("/login")
    public Response login(@RequestParam(value = "userName") String userName,
                          @RequestParam(value = "password") String password,
                          @RequestParam(value = "secretKey") String secretKey) {
        return Response.success();
    }

    @PostMapping("/getQRBarcodeURL")
    public Response<String> getQRBarcodeURL(@RequestParam(value = "userId") Integer userId) {
        GoogleAuthPo googleAuthPo = googleAuthService.getUserById(userId);
        String qrBarcodeURL = GoogleAuthenticator.getQRBarcodeURL(googleAuthPo.getUserName(), "192.168.6.143", googleAuthPo.getSecretKey());
        return Response.success(qrBarcodeURL);
    }

    @PostMapping("/getQRBarcode")
    public Response<String> getQRBarcode(@RequestParam(value = "userId") Integer userId) {
        GoogleAuthPo googleAuthPo = googleAuthService.getUserById(userId);
        String qrBarcodeURL = GoogleAuthenticator.getQRBarcode(googleAuthPo.getUserName(), googleAuthPo.getSecretKey());
        return Response.success(qrBarcodeURL);
    }
}
