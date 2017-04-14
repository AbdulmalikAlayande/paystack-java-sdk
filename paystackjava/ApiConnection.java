package me.iyanuadelekan.paystackjava.core

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by Iyanu on 17/07/2016.
 */
public class ApiConnection {

    private  String url;
    private String apiKey;

    /**
     * @param url - Paystack API URL
     */
    public ApiConnection(String url) {
        this.url = url;
        Keys keys = new Keys();

        try {
            keys.initKeys();
        } catch (FileNotFoundException e) {
            System.out.print("Required Keys.json file could not be found.");
            e.printStackTrace();
        }

        this.apiKey = keys.KEY_IN_USE;
    }

    /**
     * Connects to and queries Paystack API with POST
     * @param query - APIQuery containing parameters to send
     * @return - JSONObject containing API response
     */
    public JSONObject connectAndQuery(ApiQuery query) {
        try {
            HttpResponse<JsonNode> queryForResponse = Unirest.post(url)
                    .header("Accept","application/json")
                    .header("Authorization","Bearer "+apiKey)
                    .fields(query.getParams())
                    .asJson();
            return queryForResponse.getBody().getObject();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * Connects to and queries API with POST
     * @param query - HashMap containing parameters to send
     * @return - JSONObject containing API response
     */
    public JSONObject connectAndQuery(HashMap<String,Object> query) {
        try {
            HttpResponse<JsonNode> queryForResponse = Unirest.post(url)
                    .header("Accept","application/json")
                    .header("Authorization","Bearer "+apiKey)
                    .fields(query)
                    .asJson();
            return queryForResponse.getBody().getObject();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Used to send a GET request to the Paystack API
     * @return - JSONObject containing the API response
     */
    public JSONObject connectAndQueryWithGet(){
        try {
            HttpResponse<JsonNode> queryForResponse = Unirest.get(url)
                    .header("Accept","application/json")
                    .header("Authorization","Bearer "+apiKey)
                    .asJson();
            return queryForResponse.getBody().getObject();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Used to send a GET request to the Paystack API
     * @param query - APIQuery containing parameters to send
     * @return - JSONObject containing API response
     */
    public JSONObject connectAndQueryWithGet(ApiQuery query){
        try {
            HttpResponse<JsonNode> queryForResponse = Unirest.get(url)
                    .header("Accept","application/json")
                    .header("Authorization","Bearer "+apiKey)
                    .queryString(query.getParams())
                    .asJson();
            return queryForResponse.getBody().getObject();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Used to send a GET request to the Paystack API
     * @param query - HashMap containing parameters to send
     * @return - JSONObject containing API response
     */
    public JSONObject connectAndQueryWithGet(HashMap<String,Object> query) {
        try {
            HttpResponse<JsonNode> queryForResponse = Unirest.get(url)
                    .header("Accept","application/json")
                    .header("Authorization","Bearer "+apiKey)
                    .queryString(query)
                    .asJson();
            return queryForResponse.getBody().getObject();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Used to send a PUT request to the Paystack API
     * @param query - APIQuery containing parameters to send
     * @return - JSONObject containing API response
     */
    public JSONObject connectAndQueryWithPut(ApiQuery query) {
        try {
            HttpResponse<JsonNode> queryForResponse = Unirest.put(url)
                    .header("Accept","application/json")
                    .header("Authorization","Bearer "+apiKey)
                    .fields(query.getParams())
                    .asJson();
            return queryForResponse.getBody().getObject();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Used to send a PUT request to the Paystack API
     * @param query - HashMap containing parameters to send
     * @return - JSONObject containing API response
     */
    public JSONObject connectAndQueryWithPut(HashMap<String,Object> query) {
        try {
            HttpResponse<JsonNode> queryForResponse = Unirest.get(url)
                    .header("Accept","application/json")
                    .header("Authorization","Bearer "+apiKey)
                    .queryString(query)
                    .asJson();
            return queryForResponse.getBody().getObject();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Called to shut down the background event loop
     */
    public static void shutDown() {
        try {
            Unirest.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
