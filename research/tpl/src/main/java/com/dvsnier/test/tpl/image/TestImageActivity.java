package com.dvsnier.test.tpl.image;

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
import com.dvsnier.test.tpl.R;
import com.dvsnier.test.tpl.R2;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TestImageActivity extends AppCompatActivity {

    @BindView(R2.id.test_image)
    ImageView testImage;
    @BindView(R2.id.image_url)
    TextView imageUrl;
    @BindView(R2.id.image_local)
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

    @OnClick({R2.id.image_url, R2.id.image_local, R2.id.test_image})
    public void onClick(View view) {
        int viewId = view.getId();
        if (viewId == R.id.image_url) {
            url = getString(R.string.image_url);
            loadImage(url);
        } else if (viewId == R.id.image_local) {
            url = "file:///android_asset/image/twqrs.jpg";
            loadImage(url);
        } else if (viewId == R.id.test_image) {
            Toast.makeText(TestImageActivity.this, "the current test url is " + url, Toast.LENGTH_SHORT).show();
        }
    }
}
