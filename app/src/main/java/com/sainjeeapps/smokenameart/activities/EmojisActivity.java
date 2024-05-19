package com.sainjeeapps.smokenameart.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import com.sainjeeapps.smokenameart.R;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EmojisActivity extends AppCompatActivity {
    ImageView back_press;
    List<String> emojis = new ArrayList();
    
    RecyclerView recyclerView;
    private String[] stickerPath = {"emojis"};


    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, androidx.fragment.app.FragmentActivity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_emojis);
        this.back_press = (ImageView) findViewById(R.id.back_emojis);
        this.recyclerView = (RecyclerView) findViewById(R.id.rec_emojis);
        this.back_press.setOnClickListener(new View.OnClickListener() {
            /* class com.sainjeeapps.smokenameart.activities.EmojisActivity.AnonymousClass2 */

            public void onClick(View view) {
                EmojisActivity.this.onBackPressed();
            }
        });
        new onAssetFileLoad().execute(new Void[0]);
        this.recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        this.recyclerView.setAdapter(new EmojisAdapter(this.emojis));
    }

    private class onAssetFileLoad extends AsyncTask<Void, Void, Void> {
        private onAssetFileLoad() {
        }

        
        public Void doInBackground(Void... voidArr) {
            EmojisActivity emojisActivity = EmojisActivity.this;
            emojisActivity.populateStickersList(emojisActivity.emojis, EmojisActivity.this.stickerPath[0]);
            return null;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void populateStickersList(List<String> list, String str) {
        list.clear();
        try {
            String[] list2 = getAssets().list(str);
            for (String str2 : list2) {
                list.add(str + File.separator + str2);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public class EmojisAdapter extends RecyclerView.Adapter<EmojisAdapter.EmojisHolder> {
        List<String> emojisList;

        public EmojisAdapter(List<String> list) {
            this.emojisList = list;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public EmojisHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new EmojisHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.emojis_sample, viewGroup, false));
        }

        public void onBindViewHolder(EmojisHolder emojisHolder, int i) {
            final String str = "file:///android_asset/" + this.emojisList.get(i);
            Glide.with(EmojisActivity.this.getApplicationContext()).load(str).apply(new RequestOptions().override(200, 200)).into(emojisHolder.imageView);
            emojisHolder.itemView.setOnClickListener(new View.OnClickListener() {
                /* class com.sainjeeapps.smokenameart.activities.EmojisActivity.EmojisAdapter.AnonymousClass1 */

                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.putExtra("emojis", "" + str);
                    EmojisActivity.this.setResult(-1, intent);
                    EmojisActivity.this.finish();
                }
            });
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this.emojisList.size();
        }

        public class EmojisHolder extends RecyclerView.ViewHolder {
            ImageView imageView;

            public EmojisHolder(View view) {
                super(view);
                this.imageView = (ImageView) view.findViewById(R.id.img_emojis);
            }
        }
    }
}
