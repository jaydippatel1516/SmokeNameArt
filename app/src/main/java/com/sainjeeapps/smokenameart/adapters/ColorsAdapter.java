package com.sainjeeapps.smokenameart.adapters;

import android.app.Activity;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.sainjeeapps.smokenameart.R;

public class ColorsAdapter extends RecyclerView.Adapter<ColorsAdapter.ColorsHolder> {
    private Activity mActivity;
    private TypedArray mColorsList;
    private ListClickListener mListClickListener;

    public interface ListClickListener {
        void onClick(int i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public long getItemId(int i) {
        return (long) i;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        return i;
    }

    public ColorsAdapter(ListClickListener listClickListener, Activity activity) {
        this.mListClickListener = listClickListener;
        this.mActivity = activity;
        this.mColorsList = activity.getResources().obtainTypedArray(R.array.colors);
        setHasStableIds(true);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ColorsHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ColorsHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.colors_sample, viewGroup, false));
    }

    public void onBindViewHolder(ColorsHolder colorsHolder, int i) {
        final int color = this.mColorsList.getColor(i, i);
        colorsHolder.cardView.setCardBackgroundColor(color);
        colorsHolder.cardView.setOnClickListener(new View.OnClickListener() {
            /* class com.sainjeeapps.smokenameart.adapters.ColorsAdapter.AnonymousClass1 */

            public void onClick(View view) {
                ColorsAdapter.this.mListClickListener.onClick(color);
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.mColorsList.length();
    }

    public class ColorsHolder extends RecyclerView.ViewHolder {
        CardView cardView;

        public ColorsHolder(View view) {
            super(view);
            this.cardView = (CardView) view.findViewById(R.id.color_card);
        }
    }
}
