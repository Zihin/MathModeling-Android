package com.neu.coder.mathmodeling.MOOC;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by zxy on 15/12/26.
 */
public class MoocItemData {
    private String title;
    private String url;
    private String imageUrl;

    public String getTitle() {
        return title;
    }
    public String getImageUrl() {
        return imageUrl;
    }
    public String getUrl() {
        return url;
    }

    public MoocItemData(String title, String url, String imageUrl) {
        this.title = title;
        this.url = url;
        this.imageUrl = imageUrl;
    }

    public MoocItemData(JSONObject object) {

        try {
            title = (String) object.get("title");
            url = (String) object.get("url");
            imageUrl = (String) object.get("imageUrl");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String toString() {
        return("toString: title = " + title + "\nurl = " + url + "\nimageUrl = " + imageUrl);
    }
}
