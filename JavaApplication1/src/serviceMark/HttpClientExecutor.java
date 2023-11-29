/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serviceMark;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import java.util.HashMap;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import models.StatusMessage;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
/**
 *
 * @author leone-
 */
public class HttpClientExecutor {
    
    public static CompletableFuture<StatusMessage> sendGetRequest(String path) {
        HttpClient httpClient = HttpClient.newHttpClient();

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(path))
                .build();

        return httpClient.sendAsync(httpRequest, HttpResponse.BodyHandlers.ofString())
                .thenApply(response -> {
                    StatusMessage statusMessage = new StatusMessage();
                    statusMessage.statuscode = response.statusCode();

                    // Parse the JSON string manually (replace with your preferred JSON library)
                    String jsonString = response.body();
                    HashMap<String, Object> htmlAttributes = parseJson(jsonString);

                    statusMessage.message = htmlAttributes.get("message").toString();
                    statusMessage.data = htmlAttributes.containsKey("data") ? htmlAttributes.get("data").toString() : "";

                    return statusMessage;
                });
    }
    
    
    
    public static CompletableFuture<StatusMessage> sendPostRequest(String url, String objeto) {
        HttpClient httpClient = HttpClient.newHttpClient();

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(objeto))
                .build();

        return httpClient.sendAsync(httpRequest, HttpResponse.BodyHandlers.ofString())
                .thenApply(response -> {
                    StatusMessage statusMessage = new StatusMessage();
                    statusMessage.statuscode = response.statusCode();

                    // Parse the JSON string manually (replace with your preferred JSON library)
                    String estado = response.body();
                    
                    // Use Gson for JSON deserialization
                    Gson gson = new Gson();
                    Type type = new TypeToken<HashMap<String, Object>>() {}.getType();
                    HashMap<String, Object> htmlAttributes = gson.fromJson(estado, type);
                   

                    statusMessage.message = htmlAttributes.get("message").toString();
                    statusMessage.data = htmlAttributes.containsKey("data") ? htmlAttributes.get("data").toString() : "";

                    return statusMessage;
                });
    }
    
    
    public static CompletableFuture<StatusMessage> sendGetRequest(String path, String value) {
        HttpClient httpClient = HttpClient.newHttpClient();

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(path))
                .header("value", value)
                .build();

        return httpClient.sendAsync(httpRequest, HttpResponse.BodyHandlers.ofString())
                .thenApply(response -> {
                    StatusMessage statusMessage = new StatusMessage();
                    statusMessage.statuscode = response.statusCode();

                    // Parse the JSON string manually (replace with your preferred JSON library)
                    String stringres = response.body();
                    HashMap<String, Object> htmlAttributes = parseJson(stringres);

                    statusMessage.message = htmlAttributes.get("message").toString();
                    statusMessage.data = htmlAttributes.containsKey("data") ? htmlAttributes.get("data").toString() : "";

                    return statusMessage;
                });
    }

    private static HashMap<String, Object> parseJson(String jsonString) {
        // Implement your JSON parsing logic here (e.g., using a JSON library like Jackson or Gson)
        // For simplicity, I'm just returning an empty map here
        return new HashMap<>();
    }
    
}
