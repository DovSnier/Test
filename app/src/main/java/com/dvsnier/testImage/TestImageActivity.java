package com.dvsnier.testImage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.dvsnier.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TestImageActivity extends AppCompatActivity {

    @Bind(R.id.test_image)
    ImageView testImage;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_image);
        ButterKnife.bind(this);
        initializedView();
    }

    protected void initializedView() {
        url = getString(R.string.image_url);
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

    @OnClick(R.id.test_image)
    public void onClick(View view) {
        Toast.makeText(TestImageActivity.this, "the current test url is " + url, Toast.LENGTH_SHORT).show();
    }
}
