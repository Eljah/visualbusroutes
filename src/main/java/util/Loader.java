package util;

import javax.net.ssl.HttpsURLConnection;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by eljah32 on 11/19/2017.
 */
public class Loader {
    public static void main(String[] args) {
        try {
            int i=0;
            while (i<10000) {
                URL obj = new URL("http://localhost:8080/load/findroutes");
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                con.setRequestMethod("GET");
                System.out.println(con.getResponseCode());
                con.disconnect();

                obj = new URL("http://localhost:8080/load/routenames");
                con = (HttpURLConnection) obj.openConnection();
                con.setRequestMethod("GET");
                System.out.println(con.getResponseCode());
                con.disconnect();

                obj = new URL("http://localhost:8080/load/stopnames");
                con = (HttpURLConnection) obj.openConnection();
                con.setRequestMethod("GET");
                System.out.println(con.getResponseCode());
                con.disconnect();

                obj = new URL("http://localhost:8080/load/routestops");
                con = (HttpURLConnection) obj.openConnection();
                con.setRequestMethod("GET");
                System.out.println(con.getResponseCode());
                con.disconnect();

                System.out.println(i);
                i++;
            }
        }
        catch (Exception e)
        {

        }

        //add request header

    }
}
