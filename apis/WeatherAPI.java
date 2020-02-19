package apis;

/** Weather API class
 * Class used to make calls to the WeatherAPI given data from the WeatherAdapter
 *
 * Last updated February 18 2020
 * @Author Arturo
 */

import apis.API_keys;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.*;

public class WeatherAPI {

    /** Method used to grab data from the weather API
     *
     * @param _zipCode zip code where the user is located
     * @param _country country where the user is located
     */
    public static void getWeather(String _zipCode, String _country) {

        // Create a HTTP Connection.
        String baseURL = "http://api.openweathermap.org";
        String callAction = "/data/2.5/weather?q=";

        //Build the URL
        String urlString = baseURL + callAction + _zipCode + "," + _country + "&appid=" + API_keys.openWeatherMapAPI();
        URL url;

        try {

            // Creating the URL and connection
            url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            //Prints the respone code
            int status = connection.getResponseCode();
            System.out.println("Response Code: " + status);

            BufferedReader inputStream = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();

            //Appends all of the data to the input line until there is no more data
            while ((inputLine = inputStream.readLine()) != null) {
                content.append(inputLine);
            }
            inputStream.close();
            connection.disconnect();

            //Outputing the data
            System.out.println("Output: " + content.toString());
            JSONObject obj = new JSONObject(content.toString());

        } catch (IOException | JSONException ex) {
            Logger.getLogger(WeatherAPI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}