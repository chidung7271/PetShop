package qltc.BussinessLogicLayer;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

import org.json.JSONObject;

public class test {
    private final static String id = "your_id_here";
    private final static String API_KEY = "your_api_key_here";

    public void getQR(int value) throws Exception{

        URL url = new URL("https://api.vietqr.io/v2/generate");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("x-client-id", id);
        conn.setRequestProperty("x-api-key", API_KEY);
        conn.setDoOutput(true);

        String jsonInputString = String.format("""
                {
                    "accountNo": "dungtapcode",
                    "accountName": "DOAN CHI DUNG",
                    "acqId": 970436,
                    "amount": %d,
                    "template": "compact2"
                }
                    """,value);

        try (DataOutputStream dos = new DataOutputStream(conn.getOutputStream())) {
            dos.writeBytes(jsonInputString);
        }
        ;
        System.out.println(conn.getResponseCode());
        try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());

            }

            JSONObject jsonResponse = new JSONObject(response.toString());

            JSONObject data = jsonResponse.getJSONObject("data");

            // Get the qrDataURL from the data object
            String qrDataURL = data.getString("qrDataURL");

            String base64Image = qrDataURL.split(",")[1];

            byte[] imageBytes = Base64.getDecoder().decode(base64Image);
            Files.write(Paths.get("src\\main\\java\\qltc\\2.png"), imageBytes);
        }
    }
}