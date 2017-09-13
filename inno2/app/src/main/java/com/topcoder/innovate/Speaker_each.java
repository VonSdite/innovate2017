package com.topcoder.innovate;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

public class Speaker_each extends AppCompatActivity {

    private TextView name;
    private ImageView image;
    private TextView title;
    private TextView details_txt;
    private ScrollView details;
    private Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speaker_each);
        final Intent intent = getIntent();

        name = (TextView)findViewById(R.id.speaker_name_each);
        name.setText(intent.getStringExtra("name"));

        title = (TextView)findViewById(R.id.speaker_title_each);
        title.setText(intent.getStringExtra("title"));

        image = (ImageView)findViewById(R.id.speaker_pic_each);
        ApplicationInfo appInfo = getApplicationInfo();
        image.setImageResource(getResources().getIdentifier(intent.getStringExtra("image"), "drawable", appInfo.packageName));

        details = (ScrollView)findViewById(R.id.speaker_details_each);
        details_txt = (TextView)findViewById(R.id.speaker_details_text);
        details_txt.setText(intent.getStringExtra("details"));

        back = (Button)findViewById(R.id.back_button);
        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                finish();
            }
        });
    }
}

