package client.model;

import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;

import java.io.IOException;

public class SaveRequest implements Runnable {

    private boolean editMode;
    private String request;
    private String jsonDto;

    public SaveRequest(boolean editMode, String request, String jsonDto) {
        this.editMode = editMode;
        this.request = request;
        this.jsonDto = jsonDto;
    }

    @Override
    public void run() {
        try {
            if (editMode) {
                Request.Patch(request).bodyString(jsonDto, ContentType.APPLICATION_JSON).execute();
            } else {
                Request.Post(request).bodyString(jsonDto, ContentType.APPLICATION_JSON).execute();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}