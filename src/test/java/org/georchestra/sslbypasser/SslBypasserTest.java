package org.georchestra.sslbypasser;

import static org.junit.Assert.assertTrue;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLHandshakeException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.common.io.CharStreams;

public class SslBypasserTest {


    private void enableBypasser() throws Exception {
        SslBypasser.disableCertificatesCheck();
    }

    private void disableBypasser() throws Exception {
        SslBypasser.enableCertificatesCheck();
    }

    @Before
    public void setUp() throws Exception {
        Class.forName(SslBypasser.class.getName());
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testWithout() throws Exception {
        disableBypasser();
        System.out.println("self-signed certificate with bypass disabled");

        // without the SslBypasser, this should fire an exception.
        HttpsURLConnection htc = (HttpsURLConnection) new URL("https://sdi.georchestra.org").openConnection();
        boolean handshakeExRaised = false;
        try {
            htc.connect();
            String returnedPage = CharStreams.toString(new InputStreamReader(htc.getInputStream(), "UTF-8"));
        } catch (SSLHandshakeException e) {
            handshakeExRaised = true;
        } finally {
            htc.disconnect();
        }
        assertTrue("SSLHandshakeException not caught", handshakeExRaised);

    }

    @Test
    public void test() throws Exception {
        enableBypasser();
        System.out.println("self-signed certificate with bypass activated");
        // This server provides a self-signed certificate.
        HttpsURLConnection htc = (HttpsURLConnection) new URL("https://sdi.georchestra.org").openConnection();
        try {
            htc.connect();
            String returnedPage = CharStreams.toString(new InputStreamReader(htc.getInputStream(), "UTF-8"));
            assert (htc.getResponseCode() == HttpURLConnection.HTTP_OK);
        } finally {
            htc.disconnect();
        }
    }



}
