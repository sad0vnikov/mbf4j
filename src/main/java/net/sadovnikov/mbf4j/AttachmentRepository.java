package net.sadovnikov.mbf4j;

import com.google.gson.Gson;
import net.sadovnikov.mbf4j.http.Conversation;
import net.sadovnikov.mbf4j.http.HttpException;
import net.sadovnikov.mbf4j.http.api.ApiRequestFactory;
import net.sadovnikov.mbf4j.http.api.Request;
import net.sadovnikov.mbf4j.http.api.ResponseParseException;
import net.sadovnikov.mbf4j.http.api.response.AttachmentApiResponse;
import net.sadovnikov.mbf4j.http.api.response.AttachmentViewApiResponse;
import net.sadovnikov.mbf4j.http.api.response.IdApiResponse;
import net.sadovnikov.mbf4j.util.FileBase64Encoder;

import java.util.Optional;

public class AttachmentRepository {

    private ApiRequestFactory apiRequestFactory;
    private Gson gson;

    public AttachmentRepository(ApiRequestFactory requestFactory) {
        this.apiRequestFactory = requestFactory;
        this.gson = new Gson();
    }

    public UploadedAttachment put(Channel channel, Conversation conversation, AttachmentToUpload upload) throws RepositoryException {
        FileBase64Encoder encoder = new FileBase64Encoder();
        String base64 = encoder.getBase64(upload.bytes());
        ApiAttachmentUpload apiObject = new ApiAttachmentUpload(upload.type().toString(), base64);
        if (upload.name().isPresent()) {
            apiObject.withName(upload.name().get());
        }

        if (upload.thumbnailBytes().isPresent()) {
            String thumbnailBase64 = encoder.getBase64(upload.thumbnailBytes().get());
            apiObject.withThumbnailBase64(thumbnailBase64);
        }

        String jsonToPost = gson.toJson(apiObject);
        Request request = apiRequestFactory.post(channel, "/conversations/" + conversation.id() + "/attachments", jsonToPost);
        try {
            IdApiResponse response = request.execute().getObject(IdApiResponse.class);

            return getById(channel, response.id());

        } catch (ApiException | HttpException | ResponseParseException e) {
            throw new RepositoryException(e);
        }

    }

    public UploadedAttachment getById(Channel channel, String id) throws RepositoryException {
        try {
            Request request = apiRequestFactory.get(channel, "/attachments/" + id);
            AttachmentApiResponse response = request.execute().getObject(AttachmentApiResponse.class);
            String attachmentViewUri = "/attachments/" + id + "/views/";

            Optional<AttachmentViewApiResponse> originalView = response.getView(AttachmentViewApiResponse.TYPE_ORIGINAL);
            if (!originalView.isPresent()) {
                throw new RepositoryException("cannot find original view for attachment " + id);
            }

            Request attachmentViewRequest = apiRequestFactory.get(channel, attachmentViewUri + originalView.get().viewId());

            UploadedAttachment attachment = new UploadedAttachment(response.type(), attachmentViewRequest.getEnformedUrl());
            Optional<AttachmentViewApiResponse> thumbnailView = response.getView(AttachmentViewApiResponse.TYPE_THUMBNAIL);
            if (thumbnailView.isPresent()) {
                Request thumbnailViewRequest = apiRequestFactory.get(channel, attachmentViewUri + originalView.get().viewId());
                attachment.withThumbnailUrl(thumbnailViewRequest.getEnformedUrl());
            }

            if (response.name().isPresent()) {
                attachment.withName(response.name().get());
            }

            return attachment;

        } catch (ApiException | HttpException | ResponseParseException e) {
            throw new RepositoryException(e);
        }

    }

}
