package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) throws JSONException {
        JSONObject info = new JSONObject(json);
        JSONObject name = info.getJSONObject("name");
        String mainName = name.getString("mainName");
        JSONArray alsoKnownAsArray = name.getJSONArray("alsoKnownAs");
        List<String> alsoKnownAs = new ArrayList<>();
        for(int i=0;i<alsoKnownAsArray.length();i++){
            alsoKnownAs.add(alsoKnownAsArray.getString(i)); // iterate the JSONArray and extract the keys
        }
        String placeOfOrigin = info.getString("placeOfOrigin");
        String description = info.getString("description");
        String image = info.getString("image");
        JSONArray ingredientsArray = info.getJSONArray("ingredients");
        List<String> ingredients = new ArrayList<>();
        for(int i=0;i<ingredientsArray.length();i++){
            ingredients.add(ingredientsArray.getString(i)); // iterate the JSONArray and extract the keys
        }

        Sandwich sandwich = new Sandwich(mainName,alsoKnownAs,placeOfOrigin,description,image,ingredients);
        return sandwich;
    }
}
