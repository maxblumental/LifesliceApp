package com.blumental.lifesliceapp.view.adapter;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.blumental.lifesliceapp.R;
import com.blumental.lifesliceapp.model.Record;

import java.util.List;

import static android.view.LayoutInflater.from;

public class VideoListAdapter extends RecyclerView.Adapter<ViewHolder> {

    private List<Record> records;

    private LruCache<String, Bitmap> avatarCache;

    public VideoListAdapter() {
        long totalRamSize = Runtime.getRuntime().maxMemory();
        avatarCache = new LruCache<String, Bitmap>((int) (totalRamSize / 8)) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getByteCount();
            }
        };
    }

    public void setRecords(List<Record> records) {
        this.records = records;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = from(parent.getContext()).inflate(R.layout.video_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(records.get(position), avatarCache);
    }

    @Override
    public int getItemCount() {
        return records == null ? 0 : records.size();
    }
}
