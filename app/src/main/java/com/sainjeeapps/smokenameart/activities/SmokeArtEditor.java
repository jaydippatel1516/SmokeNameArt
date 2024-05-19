package com.sainjeeapps.smokenameart.activities;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.text.Editable;
import android.text.Layout;
import android.text.TextWatcher;
import android.text.method.ScrollingMovementMethod;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.github.dhaval2404.colorpicker.ColorPickerDialog;
import com.github.dhaval2404.colorpicker.listener.ColorListener;
import com.github.dhaval2404.colorpicker.model.ColorShape;

import com.google.android.material.button.MaterialButton;
import com.sainjeeapps.smokenameart.R;
import com.sainjeeapps.smokenameart.adapters.FramesAdapter;
import com.sainjeeapps.smokenameart.adapters.SmokeAdapter;
import com.sainjeeapps.smokenameart.fragments.BottomSheetBackground;
import com.sainjeeapps.smokenameart.fragments.BottomSheetColors;
import com.sainjeeapps.smokenameart.models.FramesModel;
import com.sainjeeapps.smokenameart.models.SmokeModel;
import com.xiaopo.flying.sticker.DrawableSticker;
import com.xiaopo.flying.sticker.StickerView;
import com.xiaopo.flying.sticker.TextSticker;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class SmokeArtEditor extends AppCompatActivity {
    private static final int EMOJIS_REQ_CODE = 53;
    ImageView add_text;
    ImageView back_press;
    ImageView background;
    int check_act = 0;
    ImageView colors;
    int counter = 0;
    String ed_string;
    ImageView emojis;
    ImageView frame;
    FrameLayout frameLayout;
    ImageView frame_img;
    ImageView frames;
    Handler handler;
    LinearLayout linear_frames;
    LinearLayout linear_smoke;
    
   
    int myColor = ViewCompat.MEASURED_STATE_MASK;
    RecyclerView rec_frames;
    RecyclerView rec_smoke;
    Runnable runnable;
    ImageView save;
    ImageView smoke;
    StickerView stickerView;
    String[] style = {"font1.ttf", "font2.ttf", "font3.ttf", "font4.ttf", "font5.ttf", "font6.ttf", "font7.ttf", "font8.ttf", "font9.ttf", "font10.ttf", "font11.ttf", "font12.ttf", "font13.ttf", "font14.ttf", "font15.ttf", "font16.ttf", "font17.ttf", "font18.ttf", "font19.ttf", "font20.ttf"};
    TextSticker txtsticker;

    
    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, androidx.fragment.app.FragmentActivity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_smoke_art_editor);
        this.back_press = (ImageView) findViewById(R.id.back_editor);
        this.frame_img = (ImageView) findViewById(R.id.image_frame);
        this.save = (ImageView) findViewById(R.id.save);
        this.frameLayout = (FrameLayout) findViewById(R.id.frameLayout);
        this.background = (ImageView) findViewById(R.id.background);
        this.frames = (ImageView) findViewById(R.id.frames);
        this.add_text = (ImageView) findViewById(R.id.add_text);
        this.smoke = (ImageView) findViewById(R.id.smoke);
        this.emojis = (ImageView) findViewById(R.id.emojis);
        this.colors = (ImageView) findViewById(R.id.colors);
        this.stickerView = (StickerView) findViewById(R.id.sticker_view);
        this.linear_smoke = (LinearLayout) findViewById(R.id.linear_smoke);
        this.linear_frames = (LinearLayout) findViewById(R.id.linear_frames);
        this.rec_smoke = (RecyclerView) findViewById(R.id.rec_smoke);
        this.rec_frames = (RecyclerView) findViewById(R.id.rec_frames);
        this.frame = (ImageView) findViewById(R.id.frame);
        this.back_press.setOnClickListener(new View.OnClickListener() {
            /* class com.sainjeeapps.smokenameart.activities.SmokeArtEditor.AnonymousClass2 */

            public void onClick(View view) {
                SmokeArtEditor.this.onBackPressed();
            }
        });
        this.rec_smoke.setLayoutManager(new LinearLayoutManager(this, 0, false));
        ArrayList arrayList = new ArrayList();
        for (int i = 1; i <= 35; i++) {
            Resources resources = getResources();
            arrayList.add(new SmokeModel(resources.getIdentifier("st_" + i, "drawable", getPackageName())));
        }
        this.rec_smoke.setAdapter(new SmokeAdapter(this, arrayList, new SmokeAdapter.OnItemClick() {
            /* class com.sainjeeapps.smokenameart.activities.SmokeArtEditor.AnonymousClass3 */

            @Override // com.sainjeeapps.smokenameart.adapters.SmokeAdapter.OnItemClick
            public void onItemClick(int i) {
                SmokeArtEditor.this.frame_img.setImageResource(i);
            }
        }));
        this.rec_frames.setLayoutManager(new LinearLayoutManager(this, 0, false));
        ArrayList arrayList2 = new ArrayList();
        for (int i2 = 1; i2 <= 41; i2++) {
            Resources resources2 = getResources();
            arrayList2.add(new FramesModel(resources2.getIdentifier("f_" + i2, "drawable", getPackageName())));
        }
        this.rec_frames.setAdapter(new FramesAdapter(this, arrayList2, new FramesAdapter.OnItemClick() {
            /* class com.sainjeeapps.smokenameart.activities.SmokeArtEditor.AnonymousClass4 */

            @Override // com.sainjeeapps.smokenameart.adapters.FramesAdapter.OnItemClick
            public void onItemClick(int i) {
                SmokeArtEditor.this.frame.setImageResource(i);
            }
        }));
        Intent intent = getIntent();
        if (Objects.equals(intent.getStringExtra("img"), "list")) {
            this.frame_img.setImageURI(Uri.parse(intent.getStringExtra("gallery")));
        }
        this.stickerView.setOnTouchListener(new View.OnTouchListener() {
            /* class com.sainjeeapps.smokenameart.activities.SmokeArtEditor.AnonymousClass5 */

            public boolean onTouch(View view, MotionEvent motionEvent) {
                SmokeArtEditor.this.stickerView.setLocked(false);
                return false;
            }
        });
        this.emojis.setOnClickListener(new View.OnClickListener() {
            /* class com.sainjeeapps.smokenameart.activities.SmokeArtEditor.AnonymousClass6 */

            public void onClick(View view) {
                if (SmokeArtEditor.this.linear_frames.getVisibility() == 0) {
                    SmokeArtEditor.this.linear_frames.setVisibility(8);
                }
                if (SmokeArtEditor.this.linear_smoke.getVisibility() == 0) {
                    SmokeArtEditor.this.linear_smoke.setVisibility(8);
                }
                SmokeArtEditor.this.check_act = 1;
                SmokeArtEditor.this.handler.postDelayed(SmokeArtEditor.this.runnable, 0);
            }
        });
        this.smoke.setOnClickListener(new View.OnClickListener() {
            /* class com.sainjeeapps.smokenameart.activities.SmokeArtEditor.AnonymousClass7 */

            public void onClick(View view) {
                if (SmokeArtEditor.this.linear_smoke.getVisibility() == 0) {
                    SmokeArtEditor.this.linear_smoke.setVisibility(8);
                } else {
                    SmokeArtEditor.this.linear_smoke.setVisibility(0);
                }
                SmokeArtEditor.this.linear_frames.setVisibility(8);
            }
        });
        this.frames.setOnClickListener(new View.OnClickListener() {
            /* class com.sainjeeapps.smokenameart.activities.SmokeArtEditor.AnonymousClass8 */

            public void onClick(View view) {
                if (SmokeArtEditor.this.linear_frames.getVisibility() == 0) {
                    SmokeArtEditor.this.linear_frames.setVisibility(8);
                } else {
                    SmokeArtEditor.this.linear_frames.setVisibility(0);
                }
                SmokeArtEditor.this.linear_smoke.setVisibility(8);
            }
        });
        this.background.setOnClickListener(new View.OnClickListener() {
            /* class com.sainjeeapps.smokenameart.activities.SmokeArtEditor.AnonymousClass9 */

            public void onClick(View view) {
                if (SmokeArtEditor.this.linear_frames.getVisibility() == 0) {
                    SmokeArtEditor.this.linear_frames.setVisibility(8);
                }
                if (SmokeArtEditor.this.linear_smoke.getVisibility() == 0) {
                    SmokeArtEditor.this.linear_smoke.setVisibility(8);
                }
                BottomSheetBackground bottomSheetBackground = new BottomSheetBackground(new BottomSheetBackground.WallpaperListener() {
                    /* class com.sainjeeapps.smokenameart.activities.SmokeArtEditor.AnonymousClass9.AnonymousClass1 */

                    @Override // com.sainjeeapps.smokenameart.fragments.BottomSheetBackground.WallpaperListener
                    public void onWallpaperClick(int i) {
                        SmokeArtEditor.this.frameLayout.setBackgroundResource(i);
                    }
                });
                bottomSheetBackground.show(SmokeArtEditor.this.getSupportFragmentManager(), bottomSheetBackground.getTag());
            }
        });
        this.colors.setOnClickListener(new View.OnClickListener() {
            /* class com.sainjeeapps.smokenameart.activities.SmokeArtEditor.AnonymousClass10 */

            public void onClick(View view) {
                if (SmokeArtEditor.this.linear_frames.getVisibility() == 0) {
                    SmokeArtEditor.this.linear_frames.setVisibility(8);
                }
                if (SmokeArtEditor.this.linear_smoke.getVisibility() == 0) {
                    SmokeArtEditor.this.linear_smoke.setVisibility(8);
                }
                BottomSheetColors bottomSheetColors = new BottomSheetColors(new BottomSheetColors.ColorsListener() {
                    /* class com.sainjeeapps.smokenameart.activities.SmokeArtEditor.AnonymousClass10.AnonymousClass1 */

                    @Override // com.sainjeeapps.smokenameart.fragments.BottomSheetColors.ColorsListener
                    public void onColorClick(int i) {
                        SmokeArtEditor.this.frameLayout.setBackgroundColor(i);
                    }
                }, new BottomSheetColors.ResetListener() {
                    /* class com.sainjeeapps.smokenameart.activities.SmokeArtEditor.AnonymousClass10.AnonymousClass2 */

                    @Override // com.sainjeeapps.smokenameart.fragments.BottomSheetColors.ResetListener
                    public void onResetClick(int i) {
                        SmokeArtEditor.this.frameLayout.setBackgroundColor(-1);
                    }
                }, new BottomSheetColors.GradientListner() {
                    /* class com.sainjeeapps.smokenameart.activities.SmokeArtEditor.AnonymousClass10.AnonymousClass3 */

                    @Override // com.sainjeeapps.smokenameart.fragments.BottomSheetColors.GradientListner
                    public void onGradientClick(GradientDrawable[] gradientDrawableArr, int i) {
                        SmokeArtEditor.this.frameLayout.setBackground(gradientDrawableArr[i]);
                    }
                });
                bottomSheetColors.show(SmokeArtEditor.this.getSupportFragmentManager(), bottomSheetColors.getTag());
            }
        });
        this.add_text.setOnClickListener(new View.OnClickListener() {
            /* class com.sainjeeapps.smokenameart.activities.SmokeArtEditor.AnonymousClass11 */

            public void onClick(View view) {
                if (SmokeArtEditor.this.linear_frames.getVisibility() == 0) {
                    SmokeArtEditor.this.linear_frames.setVisibility(8);
                }
                if (SmokeArtEditor.this.linear_smoke.getVisibility() == 0) {
                    SmokeArtEditor.this.linear_smoke.setVisibility(8);
                }
                final Dialog dialog = new Dialog(SmokeArtEditor.this);
                dialog.setContentView(R.layout.add_txt_dialog);
                Window window = dialog.getWindow();
                window.setLayout(-1, -2);
                window.setBackgroundDrawable(new ColorDrawable(0));
                final EditText editText = (EditText) dialog.findViewById(R.id.et_view);
                Spinner spinner = (Spinner) dialog.findViewById(R.id.spinner_dialog);
                final TextView textView = (TextView) dialog.findViewById(R.id.dialog_txt);
                textView.setMovementMethod(new ScrollingMovementMethod());
                editText.addTextChangedListener(new TextWatcher() {
                    /* class com.sainjeeapps.smokenameart.activities.SmokeArtEditor.AnonymousClass11.AnonymousClass1 */

                    public void afterTextChanged(Editable editable) {
                    }

                    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                    }

                    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                        SmokeArtEditor.this.ed_string = charSequence.toString();
                        textView.setText(SmokeArtEditor.this.ed_string);
                    }
                });
                SmokeArtEditor smokeArtEditor = SmokeArtEditor.this;
                spinner.setAdapter((SpinnerAdapter) new TextAdapter(smokeArtEditor, R.layout.spinner_row, SmokeArtEditor.this.style));
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    /* class com.sainjeeapps.smokenameart.activities.SmokeArtEditor.AnonymousClass11.AnonymousClass2 */

                    @Override // android.widget.AdapterView.OnItemSelectedListener
                    public void onNothingSelected(AdapterView<?> adapterView) {
                    }

                    @Override // android.widget.AdapterView.OnItemSelectedListener
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                        textView.setTypeface(Typeface.createFromAsset(SmokeArtEditor.this.getAssets(), SmokeArtEditor.this.style[i]));
                    }
                });
                ((ImageView) dialog.findViewById(R.id.color_picker)).setOnClickListener(new View.OnClickListener() {
                    /* class com.sainjeeapps.smokenameart.activities.SmokeArtEditor.AnonymousClass11.AnonymousClass3 */

                    public void onClick(View view) {
                        colorPicker();
                    }

                    private void colorPicker() {
                        new ColorPickerDialog.Builder(SmokeArtEditor.this).setTitle("Pick Text Color").setColorShape(ColorShape.SQAURE).setDefaultColor(R.color.purple_50).setColorListener(new ColorListener() {
                            /* class com.sainjeeapps.smokenameart.activities.SmokeArtEditor.AnonymousClass11.AnonymousClass3.AnonymousClass1 */

                            @Override // com.github.dhaval2404.colorpicker.listener.ColorListener
                            public void onColorSelected(int i, String str) {
                                SmokeArtEditor.this.myColor = i;
                                textView.setTextColor(SmokeArtEditor.this.myColor);
                            }
                        }).show();
                    }
                });
                ((MaterialButton) dialog.findViewById(R.id.btn_ok)).setOnClickListener(new View.OnClickListener() {
                    /* class com.sainjeeapps.smokenameart.activities.SmokeArtEditor.AnonymousClass11.AnonymousClass4 */

                    public void onClick(View view) {
                        if (editText.getText().toString().length() > 0) {
                            SmokeArtEditor.this.txtsticker = new TextSticker(SmokeArtEditor.this);
                            SmokeArtEditor.this.txtsticker.setText(SmokeArtEditor.this.ed_string);
                            SmokeArtEditor.this.txtsticker.setTextColor(SmokeArtEditor.this.myColor);
                            SmokeArtEditor.this.txtsticker.setTypeface(textView.getTypeface());
                            SmokeArtEditor.this.txtsticker.setTextAlign(Layout.Alignment.ALIGN_CENTER);
                            SmokeArtEditor.this.txtsticker.resizeText();
                            SmokeArtEditor.this.stickerView.addSticker(SmokeArtEditor.this.txtsticker);
                        } else {
                            Toast.makeText(SmokeArtEditor.this, "Please type something first", 0).show();
                        }
                        dialog.dismiss();
                    }
                });
                ((MaterialButton) dialog.findViewById(R.id.btn_cancel)).setOnClickListener(new View.OnClickListener() {
                    /* class com.sainjeeapps.smokenameart.activities.SmokeArtEditor.AnonymousClass11.AnonymousClass5 */

                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
        this.save.setOnClickListener(new View.OnClickListener() {
            /* class com.sainjeeapps.smokenameart.activities.SmokeArtEditor.AnonymousClass12 */

            public void onClick(View view) {
                if (SmokeArtEditor.this.linear_frames.getVisibility() == 0) {
                    SmokeArtEditor.this.linear_frames.setVisibility(8);
                }
                if (SmokeArtEditor.this.linear_smoke.getVisibility() == 0) {
                    SmokeArtEditor.this.linear_smoke.setVisibility(8);
                }
                SmokeArtEditor.this.stickerView.setLocked(true);
                if (Build.VERSION.SDK_INT >= 33) {
                    SmokeArtEditor.this.SaveImage(SmokeArtEditor.getBitmapFromView(SmokeArtEditor.this.frameLayout));
                    Toast.makeText(SmokeArtEditor.this.getApplication(), "Saved to Storage/Pictures/Smoke Name Art/", 0).show();
                } else if (ContextCompat.checkSelfPermission(SmokeArtEditor.this, "android.permission.WRITE_EXTERNAL_STORAGE") == -1) {
                    ActivityCompat.requestPermissions(SmokeArtEditor.this, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_EXTERNAL_STORAGE"}, 302);
                    Toast.makeText(SmokeArtEditor.this, "Please Allow the Storage Permission", 0).show();
                } else {
                    SmokeArtEditor.this.SaveImage(SmokeArtEditor.getBitmapFromView(SmokeArtEditor.this.frameLayout));
                    Toast.makeText(SmokeArtEditor.this.getApplication(), "Saved to Storage/Pictures/Smoke Name Art/", 0).show();
                }
            }
        });
        this.handler = new Handler();
        this.runnable = new Runnable() {
            /* class com.sainjeeapps.smokenameart.activities.SmokeArtEditor.AnonymousClass13 */

            public void run() {
                SmokeArtEditor.this.handler.postDelayed(this, 10);
                SmokeArtEditor.this.counter++;
                if (SmokeArtEditor.this.counter == 12) {
                    SmokeArtEditor.this.handler.removeCallbacks(SmokeArtEditor.this.runnable);
                    SmokeArtEditor.this.counter = 0;
                    if (SmokeArtEditor.this.check_act == 1) {
                        Intent intent = new Intent(SmokeArtEditor.this, EmojisActivity.class);
                        intent.putExtra("data", "emojis");
                        SmokeArtEditor.this.startActivityForResult(intent, 53);
                    }
                    SmokeArtEditor.this.check_act = 0;
                }
            }
        };
    }

    public static Bitmap getBitmapFromView(View view) {
        Bitmap createBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Drawable background2 = view.getBackground();
        if (background2 != null) {
            background2.draw(canvas);
        } else {
            canvas.drawColor(0);
        }
        view.draw(canvas);
        return createBitmap;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void SaveImage(Bitmap bitmap) {
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "Smoke Name Art");
        if (!file.exists()) {
            file.mkdirs();
        }
        int nextInt = new Random().nextInt(10000);
        File file2 = new File(file, "Smoke_name" + nextInt + ".png");
        if (file2.exists()) {
            file2.delete();
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file2);
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            MediaScannerConnection.scanFile(this, new String[]{file2.getAbsolutePath()}, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    @Override // androidx.activity.ComponentActivity, androidx.fragment.app.FragmentActivity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 53 && i2 == -1) {
            Glide.with((FragmentActivity) this).load(intent.getExtras().getString("emojis")).into(new SimpleTarget<Drawable>() {
                /* class com.sainjeeapps.smokenameart.activities.SmokeArtEditor.AnonymousClass14 */

                public void onResourceReady(Drawable drawable, Transition<? super Drawable> transition) {
                    SmokeArtEditor.this.stickerView.addSticker(new DrawableSticker(drawable), 1);
                }
            });
        }
    }

    public class TextAdapter extends ArrayAdapter<String> {
        public TextAdapter(Context context, int i, String[] strArr) {
            super(context, i, strArr);
        }

        public View getDropDownView(int i, View view, ViewGroup viewGroup) {
            return getCustomView(i, view, viewGroup);
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            return getCustomView(i, view, viewGroup);
        }

        public View getCustomView(int i, View view, ViewGroup viewGroup) {
            View inflate = SmokeArtEditor.this.getLayoutInflater().inflate(R.layout.spinner_row, viewGroup, false);
            TextView textView = (TextView) inflate.findViewById(R.id.textView1);
            textView.setText("Fonts");
            textView.setTypeface(Typeface.createFromAsset(SmokeArtEditor.this.getAssets(), SmokeArtEditor.this.style[i]));
            return inflate;
        }
    }

}
