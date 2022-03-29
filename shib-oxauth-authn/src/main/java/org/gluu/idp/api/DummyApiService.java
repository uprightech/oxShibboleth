package org.gluu.idp.api;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DummyApiService {

    private final Logger LOG = LoggerFactory.getLogger(DummyApiService.class);

    private String apiUrl;
    private ClientBuilder clientBuilder;

    public DummyApiService(String apiUrl, boolean sslVerify) {

        try {
            this.apiUrl = apiUrl;
            clientBuilder = ClientBuilder.newBuilder();
            configureSSL(clientBuilder,sslVerify);
        }catch(Exception e) {
            LOG.info("Could not initialize Dummy API Service",e);
            this.clientBuilder = null;
        }
    }

    public boolean isInitialized() {

        return this.clientBuilder != null;
    }

    public DummyUserInfo getUserInfo(String username) {

        String endpoint = String.format("%s/v1/userinfo/%s",apiUrl,username);
        WebTarget resource = clientBuilder.build().target(endpoint);
        try {
            LOG.info("Requesting user info from endpoint : {}",endpoint);
            return resource.request(MediaType.APPLICATION_JSON).get(DummyUserInfo.class);
        }catch(Exception e) {
            LOG.debug("Could not get user info from Rest API service",e);
            return null;
        }
    }

    private void configureSSL(ClientBuilder clientBuilder, boolean sslVerify) throws NoSuchAlgorithmException , KeyManagementException {

        SSLContext sslContext = SSLContext.getInstance("TLS");

        if(!sslVerify) {
            TrustManager  [] trustManager = new TrustManager [] {
                new X509TrustManager() {
                    public void checkClientTrusted(X509Certificate[] certs, String authType) throws CertificateException {}
                    public void checkServerTrusted(X509Certificate [] certs, String authType) throws CertificateException {}
                    public X509Certificate [] getAcceptedIssuers() { return  new X509Certificate[0];}
                }
            };

            sslContext.init(null,trustManager,new java.security.SecureRandom());
            clientBuilder.sslContext(sslContext).hostnameVerifier((s1,s2) -> true);
        }


    }
}
