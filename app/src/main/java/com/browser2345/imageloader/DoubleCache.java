package com.browser2345.imageloader;

import android.graphics.Bitmap;

/**
 * Created by fanzf on 2017/8/29.
 */

public class DoubleCache {
    ImageCache mImageCache;
    DiskCache mDiskCache;

    public DoubleCache(){
        mImageCache = new ImageCache();
        mDiskCache = new DiskCache();
    }

    public Bitmap get(String url){
        Bitmap bitmap = null;

        try {
            bitmap = mImageCache.get(url);
            if (bitmap == null){
                bitmap = mDiskCache.get(url);

            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return bitmap;
    }

    public void put(String url, Bitmap bitmap){
        mImageCache.put(url, bitmap);
        mDiskCache.put(url, bitmap);
    }
}
