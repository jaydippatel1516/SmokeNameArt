package com.sainjeeapps.smokenameart.adapters;

import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.sainjeeapps.smokenameart.R;

public class GradientAdapter extends RecyclerView.Adapter<GradientAdapter.GradientHolder> {
    GradientDrawable[] gradients;
    private ListClickListener mListClickListener;

    public interface ListClickListener {
        void onClick(GradientDrawable[] gradientDrawableArr, int i);
    }

    public GradientAdapter(GradientDrawable[] gradientDrawableArr, ListClickListener listClickListener) {
        this.gradients = gradientDrawableArr;
        this.mListClickListener = listClickListener;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public GradientHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new GradientHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.gradient_sample, viewGroup, false));
    }

    public void onBindViewHolder(GradientHolder gradientHolder, final int i) {
        gradientHolder.rel_gradient.setBackground(this.gradients[i]);
        gradientHolder.itemView.setOnClickListener(new View.OnClickListener() {
            /* class com.sainjeeapps.smokenameart.adapters.GradientAdapter.AnonymousClass1 */

            public void onClick(View view) {
                GradientAdapter.this.mListClickListener.onClick(GradientAdapter.this.gradients, i);
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.gradients.length;
    }

    public class GradientHolder extends RecyclerView.ViewHolder {
        RelativeLayout rel_gradient;

        public GradientHolder(View view) {
            super(view);
            this.rel_gradient = (RelativeLayout) view.findViewById(R.id.gradient_bg_layout);
        }
    }
}
