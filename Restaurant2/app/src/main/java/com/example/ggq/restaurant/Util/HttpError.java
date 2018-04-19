package com.example.ggq.restaurant.Util;

/**
 * Created by ggq on 2017/4/20.
 */

public interface HttpError {
    public void onFinsh(String einf);
    public void onError(String einf);
    public void netError(String einf);
}
