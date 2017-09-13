package com.topcoder.innovate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.topcoder.innovate.model.Locate;
import com.topcoder.innovate.util.DataRetriever;

import org.json.JSONException;

import java.util.List;

public class MapActivity extends AppCompatActivity {
    private MapView mMapView = null;
    private BaiduMap mBaiduMap;
    private LatLng center;
    private List<Locate> locates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        //注意该方法要再setContentView方法之前实现
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_map);

        //获取地图控件引用
        mMapView = (MapView) findViewById(R.id.Map);
        mBaiduMap = mMapView.getMap();

        //普通地图
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);

        // 地图中心位置
        center = new LatLng(37.783753, -122.401192);
        MapStatus mMapStatus = new MapStatus.Builder().target(center).zoom(18).build();
        //定义MapStatusUpdate对象，以便描述地图状态将要发生的变化
        MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
        //改变地图状态
        mBaiduMap.setMapStatus(mMapStatusUpdate);



        // 获取json数据到locates 中
        Intent intent = getIntent();
        String map_data = intent.getStringExtra("json_data");
        try {
            locates = new DataRetriever().retrieveAllLocates(this, map_data);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //定义Marker坐标点图标
        BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromResource(R.drawable.u);

        for(int i = 0; i < locates.size(); ++i){
            // 设置Marker坐标点
//            Log.i("onCreate: ", locates.get(i).getLongitude()+","+locates.get(i).getLatitude());
            LatLng point = new LatLng(locates.get(i).getLatitude(), locates.get(i).getLongitude());
            OverlayOptions option = new MarkerOptions()
                .position(point)
                .icon(bitmap);
            Marker marker = (Marker) (mBaiduMap.addOverlay(option));
            Bundle bundle = new Bundle();
            bundle.putSerializable("info", locates.get(i).getName()+"; "+locates.get(i).getAdress());
            marker.setExtraInfo(bundle);
        }
        mBaiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Toast.makeText(getApplicationContext(), ""+marker.getExtraInfo().get("info"),
                        Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
    }
    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }
}

