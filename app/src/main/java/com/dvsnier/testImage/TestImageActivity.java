package com.dvsnier.testImage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.dvsnier.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TestImageActivity extends AppCompatActivity {

    @BindView(R.id.test_image)
    ImageView testImage;
    @BindView(R.id.image_url)
    TextView imageUrl;
    @BindView(R.id.image_local)
    TextView imageLocal;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_image);
        ButterKnife.bind(this);
    }

    protected void loadImage(String url) {
        if (null == url) return;
        Glide.with(this).load(url).crossFade().diskCacheStrategy(DiskCacheStrategy.SOURCE).listener(new RequestListener<String, GlideDrawable>() {
            @Override
            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                Toast.makeText(TestImageActivity.this, getString(R.string.image_error_tips_prefix) + model, Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                return false;
            }
        }).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(testImage);
    }

    @OnClick({R.id.image_url, R.id.image_local, R.id.test_image})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.image_url:
                url = getString(R.string.image_url);
                loadImage(url);
                break;
            case R.id.image_local:
                url = "file:///android_asset/twqrs.jpg";
                loadImage(url);
                break;
            case R.id.test_image:
                Toast.makeText(TestImageActivity.this, "the current test url is " + url, Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
