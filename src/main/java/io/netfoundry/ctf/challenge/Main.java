package io.netfoundry.ctf.challenge;

import java.io.ByteArrayOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main {

    private static String challengeURL = "http://ctf.netfoundry.io/flag4";

    public static void main(String[] args) {

        try {
            URL url = new URL(challengeURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setDoInput(true);
            connection.setConnectTimeout(5000);

            if (connection.getResponseCode() != 200) {
                throw new Exception(connection.getResponseMessage());
            }

            ByteArrayOutputStream output = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];

            for ( ; ; ) {
                int read = connection.getInputStream().read(buf);
                if (read == -1) {
                    break;
                }

                output.write(buf, 0, read);
            }

            System.out.println("challenge flag4 >>> " + new String(output.toByteArray()));
        } catch (Exception e) {
            System.err.println("challenge failed: " + e.getLocalizedMessage());
        }
    }
}
