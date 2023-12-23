package me.nexipl.bettermc.events;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import static org.bukkit.Bukkit.getLogger;

public class JoinPlayer implements Listener {
    @EventHandler
    public void onJoinPlayer(PlayerJoinEvent event) throws IOException, InterruptedException {
        String name = event.getPlayer().getName();
        String apiName = "https://api.mojang.com/users/profiles/minecraft/" + name;
        String jsonRes = fetchDataFromApi(apiName);
        JsonNode jsonNode = parseJson(jsonRes);
        String getApiName = jsonNode.get("name").asText();
        if (getApiName.equals(name)) {
            getLogger().info("Premium");
        }
    }
    private static String fetchDataFromApi(String apiUrl) throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(apiUrl)).GET().build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
    private static JsonNode parseJson(String jsonString) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readTree(jsonString);
    }
}