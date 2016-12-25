package net.sadovnikov.mbf4j.http.api.response;

import java.util.Map;
import java.util.Optional;

public class AttachmentApiResponse {

    private String name = null;
    private String type;

    protected AttachmentViewApiResponse[] views;

    public AttachmentApiResponse(String type) {
        this.type = type;
    }

    public String type() {
        return type;
    }

    public Optional<String> name() {
        return Optional.ofNullable(name);
    }

    public AttachmentViewApiResponse[] views() {
        return views;
    }

    public Optional<AttachmentViewApiResponse> getView(String id) {
        //there will be no more then two views in array so, we can just iterate it to find the one we need
        AttachmentViewApiResponse viewToFind = null;
        for (AttachmentViewApiResponse view : views()) {
            if (view.viewId().equals(id)) {
                viewToFind = view;
                break;
            }
        }
        return Optional.ofNullable(viewToFind);
    }


    public AttachmentApiResponse withName(String name) {
        this.name = name;
        return this;
    }
}
