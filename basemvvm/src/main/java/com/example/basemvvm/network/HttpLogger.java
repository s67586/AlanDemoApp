package com.example.basemvvm.network;


import com.example.basemvvm.util.ALog;
import com.example.basemvvm.util.JsonUtil;

import okhttp3.logging.HttpLoggingInterceptor;

class HttpLogger implements HttpLoggingInterceptor.Logger {
    private StringBuilder mMessage = new StringBuilder();

    @Override
    public void log(String message) {
        if (message.startsWith("--> POST")) {
            mMessage.setLength(0);
        }
        if ((message.startsWith("{") && message.endsWith("}"))
                || (message.startsWith("[") && message.endsWith("]"))) {
            message = JsonUtil.formatJson(message);
        }
        mMessage.append(message.concat("\n"));
        if (message.startsWith("<-- END HTTP")) {
            ALog.INSTANCE.log(mMessage.toString());
        }
    }
}