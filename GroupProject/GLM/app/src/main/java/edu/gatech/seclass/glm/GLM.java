package edu.gatech.seclass.glm;

import android.app.Application;
import android.content.Context;

/**
 * Created by Bj on 10/2/16.
 */

public class GLM extends Application {
    private static GLM instance;

    public GLM() {
        instance = this;
    }

    public static Context getContext() {
        return instance;
    }
}
