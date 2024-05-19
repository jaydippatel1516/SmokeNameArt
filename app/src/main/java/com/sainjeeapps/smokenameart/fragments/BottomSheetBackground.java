package com.sainjeeapps.smokenameart.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.sainjeeapps.smokenameart.R;
import com.sainjeeapps.smokenameart.adapters.BackgroundAdapter;
import com.sainjeeapps.smokenameart.models.BackgroundModel;

public class BottomSheetBackground extends BottomSheetDialogFragment {
    private WallpaperListener mWallpaperListener;
    RecyclerView recyclerView;
    private View rootView;

    public interface WallpaperListener {
        void onWallpaperClick(int i);
    }

    public BottomSheetBackground(WallpaperListener wallpaperListener) {
        this.mWallpaperListener = wallpaperListener;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.rootView = layoutInflater.inflate(R.layout.fragment_bottom_sheet_background, viewGroup, false);
        initControls();
        return this.rootView;
    }

    private void initControls() {
        this.recyclerView = (RecyclerView) this.rootView.findViewById(R.id.rec_background);
        BackgroundAdapter backgroundAdapter = new BackgroundAdapter(new BackgroundAdapter.OnItemClick() {
            /* class com.sainjeeapps.smokenameart.fragments.BottomSheetBackground.AnonymousClass1 */

            @Override // com.sainjeeapps.smokenameart.adapters.BackgroundAdapter.OnItemClick
            public void onItemClick(int i) {
                BottomSheetBackground.this.mWallpaperListener.onWallpaperClick(i);
                BottomSheetBackground.this.dismiss();
            }
        }, BackgroundModel.wallpaperList(), getActivity());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 6);
        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.setLayoutManager(gridLayoutManager);
        this.recyclerView.setAdapter(backgroundAdapter);
    }
}
