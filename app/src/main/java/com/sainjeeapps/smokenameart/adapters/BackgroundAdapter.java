package com.sainjeeapps.smokenameart.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.sainjeeapps.smokenameart.R;

public class BackgroundAdapter extends RecyclerView.Adapter<BackgroundAdapter.BackgroundHolder> {
    private final Activity mActivity;
    private final int[] mWallpaperList;
    private final OnItemClick onItemClick;

    public interface OnItemClick {
        void onItemClick(int i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public long getItemId(int i) {
        return (long) i;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        return i;
    }

    public BackgroundAdapter(OnItemClick onItemClick2, int[] iArr, Activity activity) {
        this.onItemClick = onItemClick2;
        this.mActivity = activity;
        this.mWallpaperList = iArr;
        setHasStableIds(true);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public BackgroundHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new BackgroundHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.background_sample, viewGroup, false));
    }

    public void onBindViewHolder(BackgroundHolder backgroundHolder, int i) {
        final int i2 = this.mWallpaperList[i];
        backgroundHolder.imageView.setImageResource(i2);
        backgroundHolder.cardView.setOnClickListener(new View.OnClickListener() {
            /* class com.sainjeeapps.smokenameart.adapters.BackgroundAdapter.AnonymousClass1 */

            public void onClick(View view) {
                BackgroundAdapter.this.onItemClick.onItemClick(i2);
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.mWallpaperList.length;
    }

    public static class BackgroundHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView imageView;

        public BackgroundHolder(View view) {
            super(view);
            this.imageView = (ImageView) view.findViewById(R.id.img_background);
            this.cardView = (CardView) view.findViewById(R.id.bg_card);
        }
    }
}
