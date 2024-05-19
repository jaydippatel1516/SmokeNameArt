package com.sainjeeapps.smokenameart.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.cardview.widget.CardView;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.sainjeeapps.smokenameart.R;
import com.sainjeeapps.smokenameart.models.SavedCreationsModel;
import com.squareup.picasso.Picasso;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SavedCreationsAdapter extends RecyclerView.Adapter<SavedCreationsAdapter.SavedCreationsHolder> {
    private Context context;
    private final List<SavedCreationsModel> list;
    LinearLayout no_connection;

    public SavedCreationsAdapter(List<SavedCreationsModel> list2, LinearLayout linearLayout) {
        this.list = list2;
        this.no_connection = linearLayout;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public SavedCreationsHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Context context2 = viewGroup.getContext();
        this.context = context2;
        return new SavedCreationsHolder(LayoutInflater.from(context2).inflate(R.layout.saved_creations_sample, viewGroup, false));
    }

    public void onBindViewHolder(final SavedCreationsHolder savedCreationsHolder, int i) {
        try {
            final SavedCreationsModel savedCreationsModel = this.list.get(savedCreationsHolder.getAdapterPosition());
            if (savedCreationsModel != null) {
                Glide.with(this.context).asBitmap().load(savedCreationsModel.getFile()).into(savedCreationsHolder.img_saved_creations);
                String name = savedCreationsModel.getName();
                String created_date = savedCreationsModel.getCreated_date();
                String file_size = savedCreationsModel.getFile_size();
                String valueOf = String.valueOf((int) (Float.parseFloat(created_date) * 1024.0f));
                savedCreationsHolder.name.setText(name);
                TextView textView = savedCreationsHolder.size;
                textView.setText("Size : " + valueOf + "kB");
                TextView textView2 = savedCreationsHolder.date;
                textView2.setText("Date :" + file_size);
                savedCreationsHolder.delete.setOnClickListener(new View.OnClickListener() {
                    /* class com.sainjeeapps.smokenameart.adapters.SavedCreationsAdapter.AnonymousClass1 */

                    public void onClick(View view) {
                        if (savedCreationsModel.getFile().delete()) {
                            SavedCreationsAdapter.this.list.remove(savedCreationsHolder.getAdapterPosition());
                            SavedCreationsAdapter.this.notifyDataSetChanged();
                            if (SavedCreationsAdapter.this.list.size() == 0) {
                                SavedCreationsAdapter.this.no_connection.setVisibility(0);
                            }
                            Toast.makeText(SavedCreationsAdapter.this.context, "File Deleted", 0).show();
                            return;
                        }
                        Toast.makeText(SavedCreationsAdapter.this.context, "Unable to Delete File", 0).show();
                    }
                });
                savedCreationsHolder.share.setOnClickListener(new View.OnClickListener() {
                    /* class com.sainjeeapps.smokenameart.adapters.SavedCreationsAdapter.AnonymousClass2 */

                    public void onClick(View view) {
                        ArrayList arrayList = new ArrayList();
                        arrayList.add(savedCreationsModel);
                        SavedCreationsAdapter savedCreationsAdapter = SavedCreationsAdapter.this;
                        savedCreationsAdapter.shareFiles(savedCreationsAdapter.context, arrayList);
                    }
                });
                savedCreationsHolder.img_saved_creations.setOnClickListener(new View.OnClickListener() {
                    /* class com.sainjeeapps.smokenameart.adapters.SavedCreationsAdapter.AnonymousClass3 */

                    public void onClick(View view) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(SavedCreationsAdapter.this.context);
                        View inflate = LayoutInflater.from(SavedCreationsAdapter.this.context).inflate(R.layout.full_screen_preview, (ViewGroup) null);
                        builder.setView(inflate);
                        Picasso.get().load(savedCreationsModel.getFile()).into((ImageView) inflate.findViewById(R.id.full_img_preview));
                        AlertDialog create = builder.create();
                        create.getWindow().getAttributes().windowAnimations = R.style.SlidingDialogAnimation;
                        create.requestWindowFeature(1);
                        create.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                        create.show();
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.list.size();
    }

    public class SavedCreationsHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView date;
        ImageView delete;
        ImageView img_saved_creations;
        TextView name;
        ImageView share;
        TextView size;

        public SavedCreationsHolder(View view) {
            super(view);
            this.cardView = (CardView) view.findViewById(R.id.card_saved_creations);
            this.share = (ImageView) view.findViewById(R.id.share);
            this.delete = (ImageView) view.findViewById(R.id.delete);
            this.img_saved_creations = (ImageView) view.findViewById(R.id.img_saved_creations);
            this.name = (TextView) view.findViewById(R.id.name);
            this.size = (TextView) view.findViewById(R.id.size);
            this.date = (TextView) view.findViewById(R.id.date);
        }
    }

    public void shareFiles(Context context2, List<SavedCreationsModel> list2) {
        ArrayList<Uri> arrayList = new ArrayList<>();
        try {
            Intent intent = new Intent();
            intent.setAction("android.intent.action.SEND_MULTIPLE");
            intent.setType("image/jpeg");
            if (Build.VERSION.SDK_INT >= 30) {
                intent.addFlags(1);
                for (SavedCreationsModel savedCreationsModel : list2) {
                    arrayList.add(FileProvider.getUriForFile(context2, "com.example.smokenameart", new File(savedCreationsModel.getFile().getAbsolutePath())));
                }
            } else {
                for (SavedCreationsModel savedCreationsModel2 : list2) {
                    arrayList.add(Uri.fromFile(new File(savedCreationsModel2.getFile().getAbsolutePath())));
                }
            }
            intent.putParcelableArrayListExtra("android.intent.extra.STREAM", arrayList);
            context2.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
