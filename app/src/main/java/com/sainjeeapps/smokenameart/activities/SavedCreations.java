package com.sainjeeapps.smokenameart.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.StrictMode;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sainjeeapps.smokenameart.R;
import com.sainjeeapps.smokenameart.adapters.SavedCreationsAdapter;
import com.sainjeeapps.smokenameart.models.SavedCreationsModel;
import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class SavedCreations extends AppCompatActivity {
    private SavedCreationsAdapter adapter;
    private final List<SavedCreationsModel> arrayList = new ArrayList();
    ImageView back_btn;
    private final Handler handler = new Handler();
    
    LinearLayout no_imageView;
    public BroadcastReceiver receiver_whatsapp = new BroadcastReceiver() {
        /* class com.sainjeeapps.smokenameart.activities.SavedCreations.AnonymousClass3 */

        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                SavedCreations.this.getFiles();
                SavedCreations.this.no_imageView.setVisibility(8);
            }
        }
    };
    RecyclerView recyclerView;

    
    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, androidx.fragment.app.FragmentActivity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_saved_creations);
        this.recyclerView = (RecyclerView) findViewById(R.id.rec_savedCreations);
        this.no_imageView = (LinearLayout) findViewById(R.id.no_photos);
        ImageView imageView = (ImageView) findViewById(R.id.back_savedCreations);
        this.back_btn = imageView;
        imageView.setOnClickListener(new View.OnClickListener() {
            /* class com.sainjeeapps.smokenameart.activities.SavedCreations.AnonymousClass2 */

            public void onClick(View view) {
                SavedCreations.this.onBackPressed();
            }
        });
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().build());
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        getFiles();
        LocalBroadcastManager.getInstance(this).registerReceiver(this.receiver_whatsapp, new IntentFilter("Whatsapp"));
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void getFiles() {
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "Smoke Name Art");
        if (file.exists()) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    File[] listFiles = file.listFiles();
                    arrayList.clear();
                    if (listFiles == null || listFiles.length <= 0) {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                no_imageView.setVisibility(0);
                            }
                        });
                        return;
                    }
                    try {
                        Collections.sort(Arrays.asList(listFiles), new Comparator<File>() {
                            /* class com.sainjeeapps.smokenameart.activities.SavedCreations.AnonymousClass4 */

                            public int compare(File file, File file2) {
                                return String.valueOf(file.lastModified()).compareTo(String.valueOf(file2.lastModified()));
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    for (File file2 : listFiles) {
                        arrayList.add(0, new SavedCreationsModel(file2, file2.getName(), file2.getAbsolutePath(), String.valueOf(Double.parseDouble(String.valueOf(new DecimalFormat("#.##").format(((double) (file2.length() / 1024)) / 1024.0d)))), new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault()).format(new Date())));
                    }
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            SavedCreationsAdapter savedCreationsAdapter = new SavedCreationsAdapter(arrayList, no_imageView);
                            adapter = savedCreationsAdapter;
                            recyclerView.setAdapter(savedCreationsAdapter);
                            adapter.notifyDataSetChanged();
                            adapter.notifyItemInserted(0);
                            recyclerView.smoothScrollToPosition(0);
                        }
                    });
                }
            }).start();
        } else {
            this.no_imageView.setVisibility(0);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getFiles$2$com-sainjeeapps-smokenameart-activities-SavedCreations  reason: not valid java name */
    public /* synthetic */ void m55xc997f291(File file) {
        File[] listFiles = file.listFiles();
        this.arrayList.clear();
        if (listFiles == null || listFiles.length <= 0) {
            this.handler.post(new Runnable() {
                @Override
                public void run() {
                    no_imageView.setVisibility(0);
                }
            });
            return;
        }
        try {
            Collections.sort(Arrays.asList(listFiles), new Comparator<File>() {
                /* class com.sainjeeapps.smokenameart.activities.SavedCreations.AnonymousClass4 */

                public int compare(File file, File file2) {
                    return String.valueOf(file.lastModified()).compareTo(String.valueOf(file2.lastModified()));
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (File file2 : listFiles) {
            this.arrayList.add(0, new SavedCreationsModel(file2, file2.getName(), file2.getAbsolutePath(), String.valueOf(Double.parseDouble(String.valueOf(new DecimalFormat("#.##").format(((double) (file2.length() / 1024)) / 1024.0d)))), new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault()).format(new Date())));
        }
        this.handler.post(new Runnable() {
            @Override
            public void run() {
                SavedCreationsAdapter savedCreationsAdapter = new SavedCreationsAdapter(arrayList, no_imageView);
                adapter = savedCreationsAdapter;
                recyclerView.setAdapter(savedCreationsAdapter);
                adapter.notifyDataSetChanged();
                adapter.notifyItemInserted(0);
                recyclerView.smoothScrollToPosition(0);
            }
        });
    }


    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getFiles$1$com-sainjeeapps-smokenameart-activities-SavedCreations  reason: not valid java name */
    public /* synthetic */ void m54xc8619fb2() {
        this.no_imageView.setVisibility(0);
    }
}
