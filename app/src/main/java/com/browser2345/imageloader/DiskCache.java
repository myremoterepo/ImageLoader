package com.browser2345.imageloader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by fanzf on 2017/8/29.
 */

public class DiskCache {
    private static final String cache = "/sdcard/DCIM/Camera/";

    public DiskCache() {
    }

    public Bitmap get(String url) {
        return BitmapFactory.decodeFile(cache + "a.png");
    }

    public void put(String url, Bitmap bitmap) {
        try {
            saveBitmap(bitmap, "a.png");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveBitmap(Bitmap bitmap, String bitName) throws IOException {
        File file = new File(cache + bitName);
        if (file.exists()) {
            file.delete();
        }
        FileOutputStream out;
        try {
            out = new FileOutputStream(file);
            if (bitmap.compress(Bitmap.CompressFormat.PNG, 90, out)) {
                out.flush();
                out.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
