package com.codepath.apps.restclienttemplate.models;

import android.util.Log;

import com.codepath.apps.restclienttemplate.TimeFormatter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
public class Tweet {

    public String body;
    public String createdAt;
    public long id;
    public User user;


    public Tweet(){}


    public static Tweet fromJson(JSONObject jsonObject) throws JSONException {
        Tweet tweet = new Tweet();
        tweet.body = jsonObject.getString("text");
        tweet.createdAt = getFormattedTimeStamp(jsonObject.getString("created_at"));
        Log.i("Tweet- time stamp",tweet.createdAt);
        tweet.id = jsonObject.getLong("id");
        tweet.user = User.fromJson(jsonObject.getJSONObject("user"));

        return tweet;
    }

    public static List<Tweet> fromJsonArray(JSONArray jsonArray) throws JSONException {
        List<Tweet> tweets = new ArrayList<>();
        for(int i = 0; i < jsonArray.length();i++) {
            tweets.add(fromJson(jsonArray.getJSONObject(i)));
        }
        return  tweets;
    }

    public static String getFormattedTimeStamp(String time) throws JSONException {
        return TimeFormatter.getTimeDifference(time);
    }
}
