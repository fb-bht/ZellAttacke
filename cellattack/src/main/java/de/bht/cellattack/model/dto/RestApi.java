package de.bht.cellattack.model.dto;

import java.text.MessageFormat;

import de.bht.cellattack.application.Main;
import de.bht.cellattack.controller.LoginController;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.UnirestException;
import kong.unirest.json.JSONObject;

import unirest.shaded.com.google.gson.Gson;

/**
 * UserApi
 * 
 * @author Fredi Benko
 */
public class RestApi {

    public static ApiResponse sentNewUserToApi(String email, String password) {
        try {
            HttpResponse<JsonNode> response = Unirest
                    .post(MessageFormat.format("{0}/signup", Main.SERVER_URL))
                    .header("accept", "application/json")
                    .header("content-type", "application/json")
                    .body(new JSONObject()
                            .put("password", password)
                            .put("email", email)
                            .toString())
                    .asJson();
            ApiResponse registerMessage = new Gson().fromJson(response.getBody().toString(), ApiResponse.class);
            return registerMessage;
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static UserLoginResponse getLoginToken(String email, String password) {
        try {
            HttpResponse<JsonNode> response = Unirest.post(MessageFormat.format("{0}/signin", Main.SERVER_URL))
                    .header("accept", "application/json")
                    .header("content-type", "application/json")
                    .body(new JSONObject()
                            .put("password", password)
                            .put("usernameOrEmail", email)
                            .toString())
                    .asJson();
            UserLoginResponse jwtResponse = new Gson().fromJson(response.getBody().toString(), UserLoginResponse.class);
            return jwtResponse;
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static UserApiResponse getUserFromAPI() {
        try {
            HttpResponse<JsonNode> response = Unirest.get(MessageFormat.format("{0}/user", Main.SERVER_URL))
                    .header("Authorization", MessageFormat.format("Bearer {0}", LoginController.token))
                    .asJson();
            UserApiResponse userResponse = new Gson().fromJson(response.getBody().getObject().toString(),
                    UserApiResponse.class);
            return userResponse;
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static String sentScoreToAPI(int score) {
        try {
            HttpResponse<JsonNode> response = Unirest.post(MessageFormat.format("{0}/score", Main.SERVER_URL))
                    .header("Authorization", MessageFormat.format("Bearer {0}", LoginController.token))
                    .header("accept", "application/json")
                    .header("content-type", "application/json")
                    .body(new JSONObject()
                            .put("score", score)
                            .toString())
                    .asJson();
            ApiResponse apiResponse = new Gson().fromJson(response.getBody().getObject().toString(), ApiResponse.class);
            return apiResponse.getMessage();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return null;
    }
    

    public static HighscoreApiResponse getHighscoreFormAPI() {
        try {
            HttpResponse<JsonNode> response = Unirest.get(MessageFormat.format("{0}/highscore", Main.SERVER_URL))
                    .asJson();
            HighscoreApiResponse highscoreApiResponse = new Gson().fromJson(response.getBody().getObject().toString(),
                    HighscoreApiResponse.class);
            return highscoreApiResponse;
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return null;
    }

}
