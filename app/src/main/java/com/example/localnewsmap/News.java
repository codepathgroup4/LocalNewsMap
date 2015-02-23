package com.example.localnewsmap;

import android.text.format.DateUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.text.ParseException;

/**
 * Created by daisych on 2/22/15.
 */
public class News {
    private String title;
    private String link;
    private String publishedAt;
    private String summary;
    private String content;
    private String publisher;
    private String imageUrl;
    private int imageHeight;
    private int imageWidth;


    // Deserialze json and convert to Tweet object
    public static News fromJSON (JSONObject json) {
        News news = new News();
// Log.e("DEBUG", json.toString());
        try {
            news.title = json.getString("title");
            news.imageUrl = json.getJSONObject("main_image").getString("original_url");
            news.publisher = json.getString("publisher");
            news.publishedAt = getRelativeTimeAgo(json.getString("ts_update"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return news;
    }
    public static ArrayList<News> fromJsonArray (JSONArray array) {
        ArrayList<News> newsList = new ArrayList<News>();
        for(int i=0; i<array.length(); i++) {
            News news;
            try {
                JSONObject json = array.getJSONObject(i);
                news = News.fromJSON(json);
                if(newsList != null) {
                    newsList.add(news);
                }
            } catch (JSONException e) {
                e.printStackTrace();
                continue;
            }
        }
        return newsList;
    }

    public static String getRelativeTimeAgo(String rawJsonDate) {
        String twitterFormat = "EEE MMM dd HH:mm:ss ZZZZZ yyyy";
        SimpleDateFormat sf = new SimpleDateFormat(twitterFormat, Locale.ENGLISH);
        sf.setLenient(true);
        String relativeDate = "";
        try {
            long dateMillis = sf.parse(rawJsonDate).getTime();
            relativeDate = DateUtils.getRelativeTimeSpanString(dateMillis,
                    System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS).toString();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return relativeDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String published_at) {
        this.publishedAt = published_at;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publiser) {
        this.publisher = publiser;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getImageHeight() {
        return imageHeight;
    }

    public void setImageHeight(int imageHeight) {
        this.imageHeight = imageHeight;
    }

    public int getImageWidth() {
        return imageWidth;
    }

    public void setImageWidth(int imageWidth) {
        this.imageWidth = imageWidth;
    }

}
