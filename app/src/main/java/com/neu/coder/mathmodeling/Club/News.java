package com.neu.coder.mathmodeling.Club;

/**
 * Created by Zihin on 1/12/2016.
 */
public class News {
    private String title;
    private String body;

    public News(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public News() {

    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }
}
