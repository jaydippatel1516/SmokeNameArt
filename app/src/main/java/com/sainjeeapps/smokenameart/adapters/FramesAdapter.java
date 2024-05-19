package com.sainjeeapps.smokenameart.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.sainjeeapps.smokenameart.R;
import com.sainjeeapps.smokenameart.models.FramesModel;
import java.util.ArrayList;

public class FramesAdapter extends RecyclerView.Adapter<FramesAdapter.FramesHolder> {
    Context context;
    ArrayList<FramesModel> list;
    private final OnItemClick listener;

    public interface OnItemClick {
        void onItemClick(int i);
    }

    public FramesAdapter(Context context2, ArrayList<FramesModel> arrayList, OnItemClick onItemClick) {
        this.context = context2;
        this.list = arrayList;
        this.listener = onItemClick;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public FramesHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new FramesHolder(LayoutInflater.from(this.context).inflate(R.layout.frames_sample, viewGroup, false));
    }

    public void onBindViewHolder(FramesHolder framesHolder, int i) {
        FramesModel framesModel = this.list.get(framesHolder.getAdapterPosition());
        Glide.with(this.context).load(Integer.valueOf(framesModel.getFrames())).into(framesHolder.imageView);
        final int frames = framesModel.getFrames();
        framesHolder.itemView.setOnClickListener(new View.OnClickListener() {
            /* class com.sainjeeapps.smokenameart.adapters.FramesAdapter.AnonymousClass1 */

            public void onClick(View view) {
                FramesAdapter.this.listener.onItemClick(frames);
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.list.size();
    }

    public class FramesHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public FramesHolder(View view) {
            super(view);
            this.imageView = (ImageView) view.findViewById(R.id.img_frames);
        }
    }
}
