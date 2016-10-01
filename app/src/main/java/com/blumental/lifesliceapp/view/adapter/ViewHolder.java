package com.blumental.lifesliceapp.view.adapter;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v4.util.LruCache;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.blumental.lifesliceapp.R;
import com.blumental.lifesliceapp.interactor.ImageDownloader;
import com.blumental.lifesliceapp.model.Record;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.functions.Action1;

import static android.support.v4.content.res.ResourcesCompat.getDrawable;
import static android.view.View.GONE;
import static android.view.View.VISIBLE;

class ViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.avatar)
    ImageView avatar;

    @BindView(R.id.avatarProgressBar)
    ProgressBar progressBar;

    @BindView(R.id.username)
    TextView username;

    ViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    void bind(Record record, LruCache<String, Bitmap> avatarCache) {
        itemView.setTag(record.getVideoUrl());
        setAvatar(record, avatarCache);
        username.setText(record.getUsername());
    }

    private void setAvatar(final Record record, final LruCache<String, Bitmap> avatarCache) {
        String avatarUrl = record.getAvatarUrl();

        Bitmap bitmap = avatarCache.get(avatarUrl);
        if (bitmap != null) {
            progressBar.setVisibility(GONE);
            avatar.setVisibility(VISIBLE);
            avatar.setImageBitmap(bitmap);
        } else {
            progressBar.setVisibility(VISIBLE);
            avatar.setVisibility(GONE);
            downloadImage(record, avatarCache);
        }
    }

    private void downloadImage(final Record record, final LruCache<String, Bitmap> avatarCache) {
        ImageDownloader.downloadImage(record.getAvatarUrl())
                .subscribe(new Action1<Bitmap>() {
                    @Override
                    public void call(Bitmap bitmap) {
                        setReceivedAvatar(bitmap, avatarCache, record);
                    }
                });
    }

    private void setReceivedAvatar(Bitmap bitmap, LruCache<String, Bitmap> avatarCache, Record record) {
        progressBar.setVisibility(GONE);
        avatar.setVisibility(VISIBLE);
        if (bitmap == null) {
            setNoAvatarImage();
        } else {
            avatar.setImageBitmap(bitmap);
            avatarCache.put(record.getAvatarUrl(), bitmap);
        }
    }

    private void setNoAvatarImage() {
        Resources resources = itemView.getResources();
        Drawable drawable = getDrawable(resources, R.drawable.no_avatar, null);
        avatar.setImageDrawable(drawable);
    }
}