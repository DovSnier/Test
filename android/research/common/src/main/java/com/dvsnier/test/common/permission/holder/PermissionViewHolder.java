package com.dvsnier.test.common.permission.holder;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.AdaptiveIconDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.VectorDrawable;
import android.os.Build;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.dvsnier.base.holder.BaseViewHolder;
import com.dvsnier.test.common.R2;
import com.dvsnier.test.common.permission.bean.PermissionBean;

import java.io.ByteArrayOutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * PermissionViewHolder
 * Created by dovsnier on 2020/7/27.
 */
public class PermissionViewHolder extends BaseViewHolder<PermissionBean> {

    @BindView(R2.id.tv_title)
    TextView tvTitle;
    @BindView(R2.id.ll_container)
    LinearLayout llContainer;
    @BindView(R2.id.tv_version_name)
    TextView tvVersionName;
    @BindView(R2.id.tv_version_code)
    TextView tvVersionCode;
    @BindView(R2.id.tv_content)
    TextView tvContent;
    @BindView(R2.id.iv_icon)
    ImageView ivIcon;

    public PermissionViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public PermissionViewHolder(@NonNull Context context, int LayoutId, ViewGroup parent) {
        super(context, LayoutId, parent);
//        super(context, R.layout.layout_item_permission, parent);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void onBindViewHolder(Context context, final int position, final PermissionBean bean) {
        super.onBindViewHolder(context, position, bean);
        if (null != bean) {
            int itemType = bean.getItemType();
            Drawable icon = bean.getIcon();
            String name = bean.getName();
            String versionName = bean.getVersionName();
            long versionCode = bean.getVersionCode();
            String packageName = bean.getPackageName();
            String msg = bean.getMsg();
            int flag = bean.getFlag();
            // https://github.com/bumptech/glide/issues/350
            if (icon instanceof BitmapDrawable) {
                showImage(((BitmapDrawable) icon).getBitmap());
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                // https://github.com/bumptech/glide/tree/v3.7.0/samples/svg
                if (icon instanceof VectorDrawable) {
                    // https://stackoverflow.com/questions/28215625/androidload-svg-file-from-web-and-show-it-on-image-view
                    // android.resource://package_name/type/name
//                    Uri uri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + packageName + "/"
//                            + R.raw.asc);
//                    GenericRequestBuilder<Uri, InputStream, SVG, PictureDrawable> requestBuilder = Glide.with(context)
//                            .using(Glide.buildStreamModelLoader(Uri.class, context), InputStream.class)
//                            .from(Uri.class)
//                            .as(SVG.class)
//                            .transcode(new SvgDrawableTranscoder(), PictureDrawable.class)
//                            .sourceEncoder(new StreamEncoder())
//                            .cacheDecoder(new FileToStreamDecoder<SVG>(new SvgDecoder()))
//                            .decoder(new SvgDecoder())
//                            .placeholder(android.R.mipmap.sym_def_app_icon)
//                            .error(android.R.mipmap.sym_def_app_icon)
//                            .animate(android.R.anim.fade_in)
//                            .listener(new SvgSoftwareLayerSetter<Uri>());
//                    requestBuilder
//                            .diskCacheStrategy(DiskCacheStrategy.NONE)
//                            // SVG cannot be serialized so it's not worth to cache it
//                            // and the getResources() should be fast enough when acquiring the InputStream
//                            .load(uri)
//                            // SVG cannot be serialized so it's not worth to cache it and network
//                            .into(ivIcon);

                    Bitmap bitmap = Bitmap.createBitmap(icon.getIntrinsicWidth(), icon.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
                    Canvas canvas = new Canvas(bitmap);
                    icon.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
                    icon.draw(canvas);
                    showImage(bitmap);
                } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    if (icon instanceof AdaptiveIconDrawable) {
                        Drawable[] drawables = new Drawable[2];
                        drawables[0] = ((AdaptiveIconDrawable) icon).getBackground();
                        drawables[1] = ((AdaptiveIconDrawable) icon).getForeground();
                        LayerDrawable layerDrawable = new LayerDrawable(drawables);
                        int width = layerDrawable.getIntrinsicWidth();
                        int height = layerDrawable.getIntrinsicHeight();
                        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
                        Canvas canvas = new Canvas(bitmap);
                        layerDrawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
                        layerDrawable.draw(canvas);
                        showImage(bitmap);
                    } else {
                        showImage(icon);
                    }
                }
            } else {
                showImage(icon);
            }
            tvTitle.setText(String.format("%s: %s", position, name));
            if (TextUtils.isEmpty(versionName) || versionCode <= 0) {
                llContainer.setVisibility(View.GONE);
            } else {
                llContainer.setVisibility(View.VISIBLE);
                tvVersionName.setText(String.format("%s: %s", "name", versionName));
                tvVersionCode.setText(String.format("%s: %s", "code", versionCode));
            }
            if (itemType == IType.TYPE_ITEM_DEFAULT) {
                tvContent.setText(String.format("%s", packageName));
                tvContent.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
                tvContent.setMaxLines(3);
                tvContent.setPadding(15, 20, 15, 20);
                if ((flag & ApplicationInfo.FLAG_SYSTEM) != 0) {
//                    itemView.setBackgroundColor(Color.parseColor("#69FF0000"));
                    itemView.setBackgroundColor(Color.parseColor("#FFFF4444"));
//                    itemView.setBackgroundColor(ContextCompat.getColor(getContext(), android.R.color.holo_red_light));
                } else {
                    itemView.setBackgroundColor(Color.WHITE);
                }
//                tvContent.setSingleLine(false);
//                tvContent.setInputType(EditorInfo.TYPE_TEXT_FLAG_MULTI_LINE);
//                tvContent.setEllipsize(TextUtils.TruncateAt.END);
            } else {
                tvContent.setText(String.format("%s", packageName));
            }

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null != getOnClickListener() && getOnClickListener() instanceof IOnItemClickListener) {
                        //noinspection unchecked
                        ((IOnItemClickListener<PermissionBean>) getOnClickListener()).onItemClick(v, position, bean);
                    }
                }
            });
        }
    }

    protected void showImage(Drawable drawable) {
        if (null != drawable) {
            Glide.with(context)
                    .load(drawable)
                    .placeholder(android.R.mipmap.sym_def_app_icon)
                    .error(android.R.mipmap.sym_def_app_icon)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .crossFade()
                    .centerCrop()
                    .into(ivIcon);
        }
    }

    protected void showImage(Bitmap bitmap) {
        if (null != bitmap) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 80, byteArrayOutputStream);
            byte[] bytes = byteArrayOutputStream.toByteArray();
            Glide.with(context)
                    .load(bytes)
                    .placeholder(android.R.mipmap.sym_def_app_icon)
                    .error(android.R.mipmap.sym_def_app_icon)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .crossFade()
                    .centerCrop()
                    .into(ivIcon);
        }
    }

    public int getValue(float value) {
        return Float.valueOf(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value,
                getContext().getResources().getDisplayMetrics())).intValue();
    }
}
