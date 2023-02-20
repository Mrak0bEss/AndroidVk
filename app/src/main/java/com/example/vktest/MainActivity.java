package com.example.vktest;

import static android.app.PendingIntent.getActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.utils.widget.ImageFilterView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private void Alert()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("привет");
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    public interface FragmentChangeListener
    {
        public void replaceFragment(Fragment fragment);
    }
    public class HomeScreen extends FragmentActivity implements
            FragmentChangeListener {


        @Override
        public void replaceFragment(Fragment fragment) {
            FragmentManager fragmentManager = getSupportFragmentManager();;
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.imageFilterView2, fragment, fragment.toString());
            fragmentTransaction.addToBackStack(fragment.toString());
            fragmentTransaction.commit();
        }

    }


    boolean click_on_mic = false;
    boolean click_on_vid = false;
    boolean replace = false;
    private ImageButton microButton;
    private ImageButton videoButton;
    private androidx.constraintlayout.utils.widget.ImageFilterView imageFilterView2;
    private androidx.constraintlayout.utils.widget.ImageFilterView imageFilterView3;
    private ImageButton callButton;
    private ImageButton handButton;
    private ImageButton smsButton;
    private ImageButton shelfButton;
    private ImageButton contactButton;
    private TextView mic1;
    public void startNewActivity(View view)
    {
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        microButton = findViewById(R.id.microButton);
        videoButton = findViewById(R.id.videoButton);
        callButton = findViewById(R.id.callButton);
        handButton = findViewById(R.id.handButton);
        mic1 = findViewById(R.id.textView1);
        smsButton = findViewById(R.id.smsButton);
        shelfButton = findViewById(R.id.shelfButton);
        imageFilterView2=findViewById(R.id.imageFilterView2);
        imageFilterView3=findViewById(R.id.imageFilterView3);
        contactButton = findViewById(R.id.contactButton);
        imageFilterView2.setClipToOutline(true);

        microButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!click_on_mic) {
                    microButton.setImageResource(R.drawable.mic_off);
                    mic1.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.mic_off2  , 0);
                    click_on_mic = true;
                } else {
                    microButton.setImageResource(R.drawable.mic);
                    mic1.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.mic2, 0);
                    click_on_mic = false;
                }
            }
        });
        videoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!click_on_vid) {
                    click_on_vid = true;
                    videoButton.setImageResource(R.drawable.videocam_off);
                } else {
                    click_on_vid = false;
                    videoButton.setImageResource(R.drawable.videocam);
                }
            }
        });
        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAndRemoveTask();
            }
        });
        handButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Alert();
            }
            });
        smsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                sendIntent.setData(Uri.parse("sms:"));
                startActivity(sendIntent);
            }
        });
        shelfButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!replace) {
                    ConstraintLayout constraintLayout = findViewById(R.id.parentLayout);
                    ConstraintSet constraintSet = new ConstraintSet();
                    constraintSet.clone(constraintLayout);
                    constraintSet.connect(R.id.imageFilterView2, ConstraintSet.TOP, R.id.smsButton, ConstraintSet.BOTTOM, 1180);
                    // constraintSet.connect(R.id.imageFilterView3,ConstraintSet.TOP,R.id.imageFilterView2,ConstraintSet.BOTTOM,0);

                    constraintSet.applyTo(constraintLayout);
                    ConstraintSet constraintSet2 = new ConstraintSet();
                    constraintSet.clone(constraintLayout);
                    constraintSet.connect(R.id.imageFilterView3, ConstraintSet.BOTTOM, R.id.imageFilterView2, ConstraintSet.TOP, 175);
                    // constraintSet.connect(R.id.imageFilterView3,ConstraintSet.TOP,R.id.imageFilterView2,ConstraintSet.BOTTOM,0);

                    constraintSet.applyTo(constraintLayout);
                    replace = true;
                } else {
                    ConstraintLayout constraintLayout = findViewById(R.id.parentLayout);
                    ConstraintSet constraintSet = new ConstraintSet();
                    constraintSet.clone(constraintLayout);
                    constraintSet.connect(R.id.imageFilterView2, ConstraintSet.TOP, R.id.smsButton, ConstraintSet.BOTTOM, 128);
                    // constraintSet.connect(R.id.imageFilterView3,ConstraintSet.TOP,R.id.imageFilterView2,ConstraintSet.BOTTOM,0);

                    constraintSet.applyTo(constraintLayout);

                    constraintSet.clone(constraintLayout);
                    constraintSet.connect(R.id.imageFilterView3, ConstraintSet.BOTTOM, R.id.callButton, ConstraintSet.TOP, 256);
                    // constraintSet.connect(R.id.imageFilterView3,ConstraintSet.TOP,R.id.imageFilterView2,ConstraintSet.BOTTOM,0);

                    constraintSet.applyTo(constraintLayout);
                    replace = false;
                }
            }
        });
        contactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startNewActivity(view);
            }
        });
        }

}
