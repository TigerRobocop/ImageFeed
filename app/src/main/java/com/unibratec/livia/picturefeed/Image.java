package com.unibratec.livia.picturefeed;

import java.io.Serializable;

/**
 * Created by Livia on 20/10/2015.
 */
public class Image implements Serializable {

    String albumId;
    String id;
    String title;
    String url;
    String thumbnailUrl;

    public Image() {
    }

    public Image(String albumId, String id, String title, String url, String thumbnailUrl) {
        this.albumId = albumId;
        this.id = id;
        this.title = title;
        this.url = url;

        this.thumbnailUrl = thumbnailUrl;
    }
}
