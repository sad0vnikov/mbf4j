package net.sadovnikov.mbf4j;

import javax.activation.MimeType;
import java.io.*;
import java.util.Optional;

public class AttachmentToUpload {

    protected byte[] file;
    protected byte[] thumbnailFile = null;
    protected String name = null;
    protected MimeType type;



    public AttachmentToUpload(byte[] bytes, MimeType type) {
        this.file = bytes;
        this.type = type;
    }


    public AttachmentToUpload withThumbnail(byte[] thumbnailFile) {
        this.thumbnailFile = thumbnailFile;
        return this;
    }

    public Optional<String> name() {
        return Optional.ofNullable(name);
    }

    public AttachmentToUpload withName(String name) {
        this.name = name;
        return this;
    }

    public byte[] bytes() {
        return file;
    }

    public MimeType type() {
        return type;
    }

    public Optional<byte[]> thumbnailBytes() {
        return Optional.ofNullable(thumbnailFile);
    }
}
