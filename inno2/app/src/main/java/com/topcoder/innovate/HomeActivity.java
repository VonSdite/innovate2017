package com.topcoder.innovate;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.ImageView;


import java.io.InputStream;
import java.io.UnsupportedEncodingException;


public class HomeActivity extends AppCompatActivity {

    public Handler handler;
    private static final int MAX_PROGRESS=100;
    private static final int PRO=10;
    private int progress=10;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ImageView info_button = (ImageView) findViewById(R.id.Info);
        info_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 显示跳转
//                Intent intent = new Intent(HomeActivity.this, WebViewActivity.class);
                // 隐式跳转
                Intent intent = new Intent("com.topcpder.innovate.WebViewActivity.ACTION_START");
                startActivity(intent);
            }
        });

        final ImageView speaker_button = (ImageView) findViewById(R.id.Speaker);
        speaker_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.topcpder.innovate.SpeakerListActivity" +
                        ".ACTION_START");

                String data = "";
                try {
                    data = readFromAsset("speakers.txt");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                intent.putExtra("json_data", data);
                startActivity(intent);
            }
        });

        ImageView map_show = (ImageView) findViewById(R.id.Map);
        map_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, MapActivity.class);
                String data = "";
                try {
                    data = readFromAsset("bling.txt");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                intent.putExtra("json_data", data);
                startActivity(intent);
            }
        });
    }

    @SuppressWarnings("deprecation")
    public void openDialog(View v) {
        handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case PRO:
                        if(progress>=MAX_PROGRESS){
                            //重新设置
                            progress=0;
                            progressDialog.dismiss();//销毁对话框
                        }else{
                            progress++;
                            progressDialog.incrementProgressBy(1);
                            //延迟发送消息
                            handler.sendEmptyMessageDelayed(PRO, 100);
                        }
                        break;

                    default:
                        break;
                }

            }
        };

        progressDialog=new ProgressDialog(this);
        progressDialog.setIcon(R.mipmap.ic_launcher);
        progressDialog.setTitle("Loading...");
        progressDialog.setMessage("Please wait...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);//设置进度条对话框//样式（水平，旋转）

        //进度最大值
        progressDialog.setMax(MAX_PROGRESS);
        progressDialog.setButton("暂停",new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                //删除消息队列
                handler.removeMessages(PRO);

            }
        });

        progressDialog.setButton2("取消",new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                //删除消息队列
                handler.removeMessages(PRO);
                //恢复进度条初始值
                progress=0;
                progressDialog.setProgress(progress);
            }
        });

        //显示
        progressDialog.show();
        //必须设置到show之后
        progress=(progress>0)?progress:0;
        progressDialog.setProgress(progress);
        //线程
        handler.sendEmptyMessage(PRO);

    }

    public String readFromAsset(String fileName) throws UnsupportedEncodingException {
        String res = "";
        try {
            InputStream in = getResources().getAssets().open(fileName);
            int length = in.available();
            byte[] buffer = new byte[length];
            in.read(buffer);
            res = new String(buffer, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        res = res.replaceAll("\n", "");
        return res;
    }

}
