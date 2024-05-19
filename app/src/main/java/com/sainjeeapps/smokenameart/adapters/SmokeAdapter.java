package com.sainjeeapps.smokenameart.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.sainjeeapps.smokenameart.R;
import com.sainjeeapps.smokenameart.models.SmokeModel;
import java.util.ArrayList;

public class SmokeAdapter extends RecyclerView.Adapter<SmokeAdapter.SmokeHolder> {
    Context context;
    ArrayList<SmokeModel> list;
    private final OnItemClick listener;

    public interface OnItemClick {
        void onItemClick(int i);
    }

    public SmokeAdapter(Context context2, ArrayList<SmokeModel> arrayList, OnItemClick onItemClick) {
        this.context = context2;
        this.list = arrayList;
        this.listener = onItemClick;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public SmokeHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new SmokeHolder(LayoutInflater.from(this.context).inflate(R.layout.smoke_sample, viewGroup, false));
    }

    public void onBindViewHolder(SmokeHolder smokeHolder, int i) {
        SmokeModel smokeModel = this.list.get(smokeHolder.getAdapterPosition());
        Glide.with(this.context).load(Integer.valueOf(smokeModel.getSmoke_img())).into(smokeHolder.imageView);
        final int smoke_img = smokeModel.getSmoke_img();
        smokeHolder.itemView.setOnClickListener(new View.OnClickListener() {
            /* class com.sainjeeapps.smokenameart.adapters.SmokeAdapter.AnonymousClass1 */

            public void onClick(View view) {
                SmokeAdapter.this.listener.onItemClick(smoke_img);
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.list.size();
    }

    public class SmokeHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public SmokeHolder(View view) {
            super(view);
            this.imageView = (ImageView) view.findViewById(R.id.img_smoke);
        }
    }
}
