package com.example.ex17_gps;

import android.Manifest;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.textview)
    TextView textView;

    double latitude, longitude;

    LocationManager manager; // 위치 정보를 받고 관리하는 클래스(여기에 리스너를 줌)
    LocationListener ll = new LocationListener() { // 위치가 변경되었거나 Provider가 변경되었을 때
        @Override
        public void onLocationChanged(Location location) { // 위치가 변경되었음을 감지
            latitude = location.getLatitude();
            longitude = location.getLongitude();
           // if(textView != null) {
                textView.setText("위도 : " + latitude + "\n경도 : " + longitude);
          // }
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {
        }

        @Override
        public void onProviderEnabled(String s) {
        }

        @Override
        public void onProviderDisabled(String s) {

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ButterKnife.bind(this);
        textView = findViewById(R.id.textview);

        PermissionListener permissionlistener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                Toast.makeText(MainActivity.this, "Permission Granted", Toast.LENGTH_SHORT).show();


                // gps 위경도를 받아서 textview에 출력
                manager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);

                String provider = LocationManager.GPS_PROVIDER;  // gps로 위치 확인하는 provider
                //              = LocationManager.NETWORK_PROVIDER; // 기지국으로 위치 확인하는 provider


                manager.requestLocationUpdates(provider, 1000, 1, ll);
                // provider : GPS로 위치를 확인하겠다! (LocationManager.GPS_PROVIDER)
                //            WIFI/기지국으로 위치를 확인하겠다! (LocationManager.NETWORK)
                // minTime : 위치를 확인할 주기 (ms)
                // minDistance : 위치가 변경되었다고 판단할 거리(m)
            }

            @Override
            public void onPermissionDenied(List<String> deniedPermissions) {
                Toast.makeText(MainActivity.this, "Permission Denied\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
                finish();
            }
        };
        TedPermission.with(this)
                .setPermissionListener(permissionlistener)
                .setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
                .setPermissions(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION)
                .check();


    } // onCreate()

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(manager != null){
            manager.removeUpdates(ll);
        }
    }
}
