package net.sadovnikov.mbf4j.http.api.response;

public class AttachmentViewApiResponse {

    private String viewId;
    private int size;

    public static final String TYPE_ORIGINAL = "original";
    public static final String TYPE_THUMBNAIL = "thumbnail";

    public AttachmentViewApiResponse(String viewId, int size) {
        this.viewId = viewId;
        this.size = size;
    }

    public String viewId() {
        return viewId;
    }

    public int size() {
        return size;
    }
}
