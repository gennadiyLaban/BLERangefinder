package com.dev.laban.blerangefinder.utils;

import android.content.Context;

import com.dev.laban.blerangefinder.R;
import com.dev.laban.blerangefinder.errorrs.exceptions.Error;

public class MessageHelper {
    private Context context;

    public MessageHelper(Context context) {
        this.context = context;
    }

    public String getErrorMessage(Error error) {
        return getErrorMessage(error, context);
    }

    public String getSuccessMessage() {
        return getSuccessMessage(context);
    }

    public static String getErrorMessage(Error error, Context context) {
        String message;
        try {
            throw error;
        } catch (Error e) {
            message = context.getString(R.string.default_error_message);
        }
        return message;
    }

    public static String getSuccessMessage(Context context) {
        return context.getString(R.string.success_message);
    }


}
