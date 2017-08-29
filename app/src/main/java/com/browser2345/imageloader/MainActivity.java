package com.browser2345.imageloader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    private static final String IMAGE_URL = "http://ovffruj49.bkt.clouddn" +
            ".com/6HM8GM$%5BYU%7BT8RXD0%28Y%5DQ@P.png";
    @BindView(R.id.main_tv)
    TextView mHello;
    @BindView(R.id.main_image)
    ImageView mBitmap;

    private ImageLoader mImageLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mHello.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mImageLoader.displayImage(IMAGE_URL, mBitmap);
            }
        });

        mImageLoader = new ImageLoader();
//        mImageLoader.setUseDoubleCache(true);
        mImageLoader.setUseDiskCache(true);
    }
}
