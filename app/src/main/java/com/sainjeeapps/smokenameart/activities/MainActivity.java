package com.sainjeeapps.smokenameart.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.sainjeeapps.smokenameart.R;

public class MainActivity extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 732;
    ImageView create_btn;
    ImageView creation_btn;
    ImageView gallery_btn;
    

    ImageView menu;

    
    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, androidx.fragment.app.FragmentActivity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_main);

        this.create_btn = (ImageView) findViewById(R.id.create);
        this.creation_btn = (ImageView) findViewById(R.id.creations);
        this.gallery_btn = (ImageView) findViewById(R.id.gallery);
        this.menu = (ImageView) findViewById(R.id.menu);
        this.create_btn.setOnClickListener(new View.OnClickListener() {
            /* class com.sainjeeapps.smokenameart.activities.MainActivity.AnonymousClass2 */

            public void onClick(View view) {
                MainActivity.this.startActivity(new Intent(MainActivity.this, SmokeArtEditor.class));
            }
        });
        this.creation_btn.setOnClickListener(new View.OnClickListener() {
            /* class com.sainjeeapps.smokenameart.activities.MainActivity.AnonymousClass3 */

            public void onClick(View view) {

                MainActivity.this.startActivity(new Intent(MainActivity.this, SavedCreations.class));
            }
        });
        this.gallery_btn.setOnClickListener(new View.OnClickListener() {
            /* class com.sainjeeapps.smokenameart.activities.MainActivity.AnonymousClass4 */

            public void onClick(View view) {
                MainActivity.this.startActivityForResult(new Intent("android.intent.action.PICK", MediaStore.Images.Media.EXTERNAL_CONTENT_URI), MainActivity.PICK_IMAGE_REQUEST);
            }
        });
        this.menu.setOnClickListener(new View.OnClickListener() {
            /* class com.sainjeeapps.smokenameart.activities.MainActivity.AnonymousClass5 */

            public void onClick(View view) {
                MainActivity mainActivity = MainActivity.this;
                PopupMenu popupMenu = new PopupMenu(mainActivity, mainActivity.menu);
                popupMenu.getMenuInflater().inflate(R.menu.menu, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    /* class com.sainjeeapps.smokenameart.activities.MainActivity.AnonymousClass5.AnonymousClass1 */

                    public boolean onMenuItemClick(MenuItem menuItem) {
                        int itemId = menuItem.getItemId();
                        if (itemId == R.id.privacy) {
                            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("https://sainjeeapps.blogspot.com/2022/06/privacy-policy-by-downloading-or-using.html"));
                            if (intent.resolveActivity(MainActivity.this.getPackageManager()) != null) {
                                MainActivity.this.startActivity(intent);
                            } else {
                                Toast.makeText(MainActivity.this, "Unable to Open Privacy Policy", 0).show();
                            }
                        } else if (itemId == R.id.rate_us) {
                            MainActivity mainActivity = MainActivity.this;
                            mainActivity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=" + MainActivity.this.getPackageName())));
                        } else if (itemId == R.id.share) {
                            Intent intent2 = new Intent();
                            intent2.setAction("android.intent.action.SEND");
                            intent2.putExtra("android.intent.extra.TEXT", "https://play.google.com/store/apps/details?id=" + MainActivity.this.getPackageName());
                            intent2.setType("text/plain");
                            MainActivity.this.startActivity(Intent.createChooser(intent2, null));
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });
    }

    
    @Override // androidx.activity.ComponentActivity, androidx.fragment.app.FragmentActivity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == PICK_IMAGE_REQUEST && i2 == -1 && intent != null && intent.getData() != null) {
            try {
                Uri data = intent.getData();
                Intent intent2 = new Intent(this, SmokeArtEditor.class);
                intent2.putExtra("gallery", data.toString());
                intent2.putExtra("img", "list");
                startActivity(intent2);
            } catch (StackOverflowError unused) {
                System.err.println("error!");
            }
        }
    }



    @Override // androidx.activity.ComponentActivity
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
