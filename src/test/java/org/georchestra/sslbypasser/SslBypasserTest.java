package org.georchestra.sslbypasser;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.common.io.CharStreams;

public class SslBypasserTest {

    @Before
    public void setUp() throws Exception {
        // the main question is on how to avoid this call
        // in the code, and ensures that it is initialized when
        // the class is loaded.
        Class.forName(SslBypasser.class.getName());
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void test() throws Exception {
        // This server provides a self-signed certificate.
        // without the SslBypasser, this should fire an exception.
        HttpsURLConnection htc = (HttpsURLConnection) new URL("https://sdi.georchestra.org").openConnection();
        try {
            htc.connect();
            String returnedPage = CharStreams.toString(new InputStreamReader(htc.getInputStream(), "UTF-8"));
            System.out.println(returnedPage);
            assert (htc.getResponseCode() == HttpURLConnection.HTTP_OK);
        } finally {
            htc.disconnect();
        }
    }
}
