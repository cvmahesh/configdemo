package com.example.configdemo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spring.datasource.mariadb")
public class MariaDbProperties {
    private String url;
    private String username;
    private String password;

    private String sslCa;            // CA cert
    private String clientCert;       // Client certificate (p12 or jks)
    private String clientCertPassword; // Password for keystore

    // Getters and setters
    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getSslCa() { return sslCa; }
    public void setSslCa(String sslCa) { this.sslCa = sslCa; }

    public String getClientCert() { return clientCert; }
    public void setClientCert(String clientCert) { this.clientCert = clientCert; }

    public String getClientCertPassword() { return clientCertPassword; }
    public void setClientCertPassword(String clientCertPassword) { this.clientCertPassword = clientCertPassword; }
}

