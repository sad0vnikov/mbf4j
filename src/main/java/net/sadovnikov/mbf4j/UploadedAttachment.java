package net.sadovnikov.mbf4j;

import java.util.Optional;

public class UploadedAttachment {

    protected String contentType;
    protected String name = null;
    protected String contentUrl;
    protected String thumbnailUrl = null;

    public UploadedAttachment(String type, String url) {
        this.contentType = type;
        this.contentUrl = url;
        this.thumbnailUrl = url;
    }

    public UploadedAttachment withName(String name) {
        this.name = name;
        return this;
    }

    public UploadedAttachment withThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
        return this;
    }


    public String type() {
        return contentType;
    }

    public String url() {
        return contentUrl;
    }

    public Optional<String> name() {
        return Optional.ofNullable(name);
    }

    public Optional<String> thumbnailUrl() {
        return Optional.ofNullable(thumbnailUrl);
    }
}
