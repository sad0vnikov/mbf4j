package net.sadovnikov.mbf4j;

import java.util.Optional;

public class ApiAttachmentUpload {

    private String type;
    private String originalBase64;
    private String name = null;
    private String thumbnailBase64 = null;

    public ApiAttachmentUpload(String type, String originalBase64) {
        this.type = type;
        this.originalBase64 = originalBase64;
    }

    public String type() {
        return type;
    }

    public Optional<String> name() {
        return Optional.ofNullable(name);
    }

    public String originalBase64() {
        return originalBase64;
    }

    public Optional<String> thumbnailBase64() {
        return Optional.ofNullable(thumbnailBase64);
    }

    public ApiAttachmentUpload withName(String name) {
        this.name = name;
        return this;
    }

    public ApiAttachmentUpload withThumbnailBase64(String thumbnailBase64) {
        this.thumbnailBase64 = thumbnailBase64;
        return this;
    }
}
