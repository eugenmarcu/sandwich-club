package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        Sandwich newSandwich = null;
        int i;
        try {
            JSONObject object = new JSONObject(json);
            JSONObject nameJSONObj = object.getJSONObject("name");
            String nameString = nameJSONObj.getString("mainName");
            List<String> alsoKnownAsStringArray = new ArrayList<>();
            JSONArray alsoKnowsAsJSONArray = nameJSONObj.getJSONArray("alsoKnownAs");
            for (i = 0; i < alsoKnowsAsJSONArray.length(); i++) {
                alsoKnownAsStringArray.add(alsoKnowsAsJSONArray.getString(i));
            }
            String placeOfOriginString = object.getString("placeOfOrigin");
            String descriptionSring = object.getString("description");
            String imageURLString = object.getString("image");
            List<String> ingredientsStringArray = new ArrayList<>();
            JSONArray ingredientsJSONArray = object.getJSONArray("ingredients");
            for (i = 0; i < ingredientsJSONArray.length(); i++) {
                ingredientsStringArray.add(ingredientsJSONArray.getString(i));
            }
            newSandwich = new Sandwich(nameString, alsoKnownAsStringArray, placeOfOriginString, descriptionSring, imageURLString, ingredientsStringArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return newSandwich;
    }
}
