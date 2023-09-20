package com.instaquotes;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class zitatApi {

    // Hier wird die Quote-API abgerufen und eine HashMap mit Author, Zitat und
    // Kategorie zurückgegeben.
    public static Map<String, String> zitatApiAbfrage() {
        try {

            String apiURL = "https://api.api-ninjas.com/v1/quotes?category=happiness";

            URL url = new URI(apiURL).toURL();
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            // Mein API-Key wird hinterlegt, damit der Request angenommen wird
            connection.addRequestProperty("X-Api-Key", "Jpu9HpvsJOJfCZIgSA++HA==lWvaQ9QZZMAFklNR");
            connection.setRequestProperty("Accept", "application/json");

            // Die Rückmeldung wird als InputStream aufgenommen
            InputStream responseStream = connection.getInputStream();

            // Ein JsonNode wird erstellt, aus der zurückgegebenen Json
            ObjectMapper mapper = new ObjectMapper();
            JsonNode result = mapper.readTree(responseStream);

            // Aus der Json Node werden die drei Angaben gelesen und in Strings verpackt
            String quoteStr = result.findValue("quote").toString();
            String authorStr = result.findValue("author").toString();
            String categoryStr = result.findValue("category").toString();

            // Die Strings werden mit Bezeichnung in eine HashMap verpackt
            Map<String, String> zitatMap = new HashMap<>();
            zitatMap.put("Zitat", quoteStr);
            zitatMap.put("Author", authorStr);
            zitatMap.put("Kategorie", categoryStr);

            return zitatMap;

        } catch (Exception e) {
            e.printStackTrace();

            Map<String, String> zitatMap = new HashMap<>();
            zitatMap.put("Fehler", "Api Abruf wirft Exception");
            return zitatMap;
        }
    }   
}