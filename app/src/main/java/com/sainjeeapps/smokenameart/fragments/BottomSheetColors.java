package com.sainjeeapps.smokenameart.fragments;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import androidx.core.internal.view.SupportMenu;
import androidx.core.view.InputDeviceCompat;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.sainjeeapps.smokenameart.R;
import com.sainjeeapps.smokenameart.adapters.ColorsAdapter;
import com.sainjeeapps.smokenameart.adapters.GradientAdapter;

public class BottomSheetColors extends BottomSheetDialogFragment implements AdapterView.OnItemSelectedListener {
    String[] colorsName = {"Solid Color", "Gradient"};
    GradientDrawable[] gradients = new GradientDrawable[16];
    private ColorsListener mColorsListener;
    private GradientListner mGradientListener;
    private ResetListener mResetListener;
    RecyclerView recyclerView;
    RecyclerView recyclerView_gra;
    int resetColor = ViewCompat.MEASURED_STATE_MASK;
    View rootView;
    Spinner spinner;

    public interface ColorsListener {
        void onColorClick(int i);
    }

    public interface GradientListner {
        void onGradientClick(GradientDrawable[] gradientDrawableArr, int i);
    }

    public interface ResetListener {
        void onResetClick(int i);
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    public BottomSheetColors(ColorsListener colorsListener, ResetListener resetListener, GradientListner gradientListner) {
        this.mColorsListener = colorsListener;
        this.mResetListener = resetListener;
        this.mGradientListener = gradientListner;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.rootView = layoutInflater.inflate(R.layout.fragment_bottom_sheet_colors, viewGroup, false);
        this.gradients[0] = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{SupportMenu.CATEGORY_MASK, InputDeviceCompat.SOURCE_ANY});
        this.gradients[1] = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{-16776961, -16711936});
        this.gradients[2] = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{-65281, -16711681});
        this.gradients[3] = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{-12303292, -3355444});
        this.gradients[4] = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{-1, ViewCompat.MEASURED_STATE_MASK});
        this.gradients[5] = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{InputDeviceCompat.SOURCE_ANY, -16711936});
        this.gradients[6] = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{-65281, SupportMenu.CATEGORY_MASK});
        this.gradients[7] = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{-16711936, -16711681});
        this.gradients[8] = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{SupportMenu.CATEGORY_MASK, -16711936});
        this.gradients[9] = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{-7829368, ViewCompat.MEASURED_STATE_MASK});
        this.gradients[10] = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{-3355444, InputDeviceCompat.SOURCE_ANY});
        this.gradients[11] = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{-16776961, -16711681});
        this.gradients[12] = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{-65281, InputDeviceCompat.SOURCE_ANY});
        this.gradients[13] = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{InputDeviceCompat.SOURCE_ANY, ViewCompat.MEASURED_STATE_MASK});
        this.gradients[14] = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{-7829368, -16711936});
        this.gradients[15] = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{InputDeviceCompat.SOURCE_ANY, -16776961});
        this.recyclerView = (RecyclerView) this.rootView.findViewById(R.id.rec_color);
        this.recyclerView_gra = (RecyclerView) this.rootView.findViewById(R.id.rec_gradient_color);
        Spinner spinner2 = (Spinner) this.rootView.findViewById(R.id.spinner);
        this.spinner = spinner2;
        spinner2.setOnItemSelectedListener(this);
        ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(), 17367048, this.colorsName);
        arrayAdapter.setDropDownViewResource(17367049);
        this.spinner.setAdapter((SpinnerAdapter) arrayAdapter);
        ColorsAdapter colorsAdapter = new ColorsAdapter(new ColorsAdapter.ListClickListener() {
            /* class com.sainjeeapps.smokenameart.fragments.BottomSheetColors.AnonymousClass1 */

            @Override // com.sainjeeapps.smokenameart.adapters.ColorsAdapter.ListClickListener
            public void onClick(int i) {
                BottomSheetColors.this.mColorsListener.onColorClick(i);
                BottomSheetColors.this.dismiss();
            }
        }, getActivity());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 6);
        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.setLayoutManager(gridLayoutManager);
        this.recyclerView.setAdapter(colorsAdapter);
        GradientAdapter gradientAdapter = new GradientAdapter(this.gradients, new GradientAdapter.ListClickListener() {
            /* class com.sainjeeapps.smokenameart.fragments.BottomSheetColors.AnonymousClass2 */

            @Override // com.sainjeeapps.smokenameart.adapters.GradientAdapter.ListClickListener
            public void onClick(GradientDrawable[] gradientDrawableArr, int i) {
                BottomSheetColors.this.mGradientListener.onGradientClick(BottomSheetColors.this.gradients, i);
                BottomSheetColors.this.dismiss();
            }
        });
        GridLayoutManager gridLayoutManager2 = new GridLayoutManager(getContext(), 4);
        this.recyclerView_gra.setHasFixedSize(true);
        this.recyclerView_gra.setLayoutManager(gridLayoutManager2);
        this.recyclerView_gra.setAdapter(gradientAdapter);
        return this.rootView;
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
        if (i == 0) {
            this.recyclerView.setVisibility(0);
            this.recyclerView_gra.setVisibility(8);
        } else if (i == 1) {
            this.recyclerView_gra.setVisibility(0);
            this.recyclerView.setVisibility(8);
        }
    }
}
