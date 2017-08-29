package com.browser2345.imageloader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.webkit.URLUtil;
import android.widget.ImageView;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by fanzf on 2017/8/29.
 * 图片加载器
 */

public class ImageLoader {
    // 图片的缓存
    private ImageCache mImageCache;
    private DiskCache mDiskCache;
    private DoubleCache mDoubleCache;
    private ExecutorService mExecutorService;

    private boolean isUseDiskCache = false;
    private boolean isUseDoubleCache = false;

    public ImageLoader() {
        mImageCache = new ImageCache();
        mDiskCache = new DiskCache();
        mDoubleCache = new DoubleCache();
        mExecutorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    }

    public void displayImage(final String url, final ImageView imageView) {
        if (imageView != null && URLUtil.isValidUrl(url)) {
            Bitmap bitmap = null;

            if (isUseDoubleCache){
                bitmap = mDoubleCache.get(url);
            } else if (isUseDiskCache){
                bitmap = mDiskCache.get(url);
            } else {
                bitmap = mImageCache.get(url);
            }

            if(bitmap != null){
                imageView.setImageBitmap(bitmap);
            } else {
                imageView.setTag(url);
                mExecutorService.submit(new Runnable() {
                    @Override
                    public void run() {
                        final Bitmap bitmap = downloadImage(url);
                        if (bitmap == null) {
                            return;
                        }
                        if (imageView.getTag().equals(url)) {
                            imageView.post(new Runnable() {
                                @Override
                                public void run() {
                                    imageView.setImageBitmap(bitmap);
                                }
                            });
                        }
                        mDoubleCache.put(url, bitmap);

                    }
                });
            }
        }
    }

    private Bitmap downloadImage(String url) {
        Bitmap bitmap = null;

        try {
            URL imageUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) imageUrl.openConnection();
            bitmap = BitmapFactory.decodeStream(connection.getInputStream());
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bitmap;
    }

    public void setUseDiskCache(boolean useDiskCache) {
        isUseDiskCache = useDiskCache;
    }

    public void setUseDoubleCache(boolean useDoubleCache) {
        isUseDoubleCache = useDoubleCache;
    }
}
