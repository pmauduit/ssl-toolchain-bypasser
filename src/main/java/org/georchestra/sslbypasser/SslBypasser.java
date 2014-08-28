package org.georchestra.sslbypasser;

import java.security.cert.X509Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class SslBypasser {
    private static final Log LOG = LogFactory.getLog(SslBypasser.class.getName());

    static {
        try {
            SslBypasser.disableCertificatesCheck();
            LOG.warn("SSL bypass in effect - Every SSL checks "
                    + "from this context WILL BE DISCARDED !");
        } catch (Exception e) {}
    }

    public static void main(String[] args) throws Exception {
        SslBypasser.disableCertificatesCheck();
    }

    public static void disableCertificatesCheck() throws Exception {
        TrustManager[] trustAllCerts = new TrustManager[]{
            new X509TrustManager() {
                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
                @Override
                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                    return;
                }
                @Override
                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                    return;
                }
            }
        };
        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, new java.security.SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
    }
}
