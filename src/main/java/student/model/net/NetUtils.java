package student.model.net;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * A series of classes to help with pulling data from https://www.freetogame.com.
 * 
 * You can read more about the API at https://www.freetogame.com/api.
 */
public class NetUtils {
    /**
     * Base URL for the API request.
     */
    private static final String BASE_URL = "https://www.freetogame.com/api";

    /**
     * Retrieves a list of all free-to-play games from the FreeToGame API.
     *
     * @return a JSON string containing the list of all free-to-play games
     * @throws Exception if an error occurs during the API request
     */
    public static String getGamesList() throws Exception {
        // Create the URL for the API request
        URL url = new URL(BASE_URL + "/games");

        // Open a connection to the API URL
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET"); // Set the request method to GET

        // Create a BufferedReader to read the response from the API
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();

        // Read the response line by line and append to the StringBuilder
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }

        // Close the BufferReader
        in.close();

        // Disconnect the HTTP connection
        conn.disconnect();

        // Return the response content as a string
        return content.toString();
    }

    /**
     * Prevent instantiation.
     */
    private NetUtils() {
        // Prevent instantiation
    }

    /**
     * Main method for testing the list obtained from the api.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            String gamesList = getGamesList();
            System.out.println("Games List: ");
            System.out.println(gamesList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
