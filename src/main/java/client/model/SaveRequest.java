package client.model;

import org.apache.http.client.fluent.Request;
import java.io.IOException;

public class SaveRequest implements Runnable {

    private boolean editMode;
    private String request;

    public SaveRequest(boolean editMode, String request) {
        this.editMode = editMode;
        this.request = request;
    }

    @Override
    public void run() {
        try {
            if (editMode) {
                Request.Patch(request).execute();
            } else {
                Request.Post(request).execute();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}