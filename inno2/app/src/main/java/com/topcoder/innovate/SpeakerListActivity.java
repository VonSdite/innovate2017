package com.topcoder.innovate;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.topcoder.innovate.model.Speaker;
import com.topcoder.innovate.model.SpeakerAdapter;
import com.topcoder.innovate.util.DataRetriever;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

public class SpeakerListActivity extends AppCompatActivity {
    List<Speaker> speakers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speaker_list);

        // 点击'i'图标进入baidu搜索页面
        ImageView info_button = (ImageView) findViewById(R.id.speaker_info);
        info_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent("com.topcpder.innovate.WebViewActivity.ACTION_START");
                startActivity(intent);
            }
        });

        // 点击‘小屋子’图标， 返回home页面
        ImageView home_button = (ImageView) findViewById(R.id.speaker_home);
        home_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent("com.topcpder.innovate.HomeActivity.ACTION_START");
                startActivity(intent);
            }
        });

        // 获取json数据到speakers 中
        Intent intent = getIntent();
        String json_data = intent.getStringExtra("json_data");
        try {
            speakers = new DataRetriever().retrieveAllSpeakers(this, json_data);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // ListView适配器
        final SpeakerAdapter adapter = new SpeakerAdapter(
                SpeakerListActivity.this, R.layout.speaker_item, speakers);
        ListView listView = (ListView) findViewById(R.id.speaker_list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                view.setBackgroundResource(R.color.colorOrange);
                // 设置选中对象， 对象高亮
                adapter.setSelectedPosition(i);
                adapter.notifyDataSetInvalidated();
                String strName = (String)speakers.get(i).getName();
                String strImage = (String)speakers.get(i).getPicture();
                String strTitle = (String)speakers.get(i).getTitle();
                String strDetails = (String)speakers.get(i).getDetails();
                Intent intent = new Intent(SpeakerListActivity.this, Speaker_each.class);
                intent.putExtra("name", strName);
                intent.putExtra("image", strImage);
                intent.putExtra("title", strTitle);
                intent.putExtra("details", strDetails);
                startActivity(intent);
            }
        });
    }
}
