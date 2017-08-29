package com.browser2345.imageloader;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * Created by fanzf on 2017/8/29.
 */

public class ImageCache {
    private LruCache<String, Bitmap> mImageCache;

    public ImageCache(){
        initImageCache();
    }

    private void initImageCache() {
        int maxMemory = (int) Runtime.getRuntime().maxMemory() / 1024;
        int cacheSize = maxMemory / 4;

        mImageCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight() / 1024;
            }
        };
    }


    public void put(String url, Bitmap bitmap) {
        mImageCache.put(url, bitmap);
    }

    public Bitmap get(String url){
        return mImageCache.get(url);
    }

}
