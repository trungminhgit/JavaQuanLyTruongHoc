/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.doannganh.quanlytruonghoc.configs;

import com.cloudinary.Cloudinary;
import java.util.HashMap;
import java.util.Map;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author ACER
 */
@Configuration
public class CloudinaryConfig {

    private final String CLOUD_NAME = "dchkh7d18";
    private final String API_KEY = "894713387132774";
    private final String API_SECRET = "W4kybsCw_SFDKj0rESDLiDlgh1Y";

    @Bean
    public Cloudinary cloudinary() {
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", CLOUD_NAME);
        config.put("api_key", API_KEY);
        config.put("api_secret", API_SECRET);

        return new Cloudinary(config);
    }
}
