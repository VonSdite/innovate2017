package com.topcoder.innovate.util;

/**
 * Created by Sdite on 2017/9/3.
 */

import android.app.Activity;
import android.content.pm.ApplicationInfo;

import com.topcoder.innovate.model.Locate;
import com.topcoder.innovate.model.Speaker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataRetriever {

    public List<Speaker> retrieveAllSpeakers(Activity activity, final String data) throws
            JSONException, IOException {

        JSONArray jsonArray = new JSONArray(data);
        List<Speaker> speakerArrayList = new ArrayList<Speaker>();
        Speaker speaker;
        for (int i = 0; i < jsonArray.length(); ++i) {
            JSONObject jsonObject = jsonArray.getJSONObject(i).getJSONObject("fields");
            speaker = new Speaker();
            speaker.setTitle(jsonObject.getString("title"));
            speaker.setName(jsonObject.getString("name"));
            speaker.setDetails(jsonObject.getString("details"));
            speaker.setPicture(jsonObject.getString("picture"));
            JSONArray JATmp = jsonObject.getJSONArray("sessions");

            ApplicationInfo appInfo = activity.getApplicationInfo();
            int picID = activity.getResources().getIdentifier(speaker.getPicture(), "drawable", appInfo.packageName);
            if (picID == 0){
                // 判断图片是否存在， 不存在则设置为默认的id
                speaker.setPicture("default_speaker");
                picID = activity.getResources().getIdentifier("default_speaker", "drawable", appInfo.packageName);
            }
            speaker.setPicId(picID);

            List<String> tmp = new ArrayList<String>();
            for(int j = 0; j < JATmp.length(); j++ ){
                tmp.add(JATmp.getString(j));
            }
            speaker.setSessionIds(tmp);
            speakerArrayList.add(speaker);
        }
//        foreach(JSONObject ...)
//        {
//            speake=new Speaker();
//            spreaker.set.....
//            speakerArrayList.Add
//        }
//
        return speakerArrayList;
    }

    public List<Locate> retrieveAllLocates(Activity activity, String data) throws JSONException {
//        String url = activity.getResources().getString(R.string.feeds_speakers);
//        HttpGet ...
//        HttpClient ....
//

        JSONArray jsonArray = new JSONArray(data);
        List<Locate> locateArrayList = new ArrayList<Locate>();
        Locate locate;
        for (int i = 0; i < jsonArray.length(); ++i) {
            JSONObject jsonObject = jsonArray.getJSONObject(i).getJSONObject("fields");
            locate = new Locate();

            locate.setLongitude(jsonObject.getDouble("longitude"));
            locate.setLatitude(jsonObject.getDouble("latitude"));
            locate.setAdress(jsonObject.getString("address"));
            locate.setName(jsonObject.getString("name"));
            locateArrayList.add(locate);
        }
//        foreach(JSONObject ...)
//        {
//            speake=new Speaker();
//            spreaker.set.....
//            speakerArrayList.Add
//        }
//
        return locateArrayList;
    }
}
