package com.weizhi.sg_psi.ui.map;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.weizhi.sg_psi.R;
import com.weizhi.sg_psi.SgPsiApplication;
import com.weizhi.sg_psi.data.RegionInfo;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;
import java.util.Locale;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback,
        MapContract.View,
        BottomNavigationView.OnNavigationItemSelectedListener{
    private static final int SHOW_PSI = 1;
    private static final int SHOW_POLLUTANT = 2;

    private GoogleMap mMap;
    private MapContract.ActionListener mPresenter;

    private FrameLayout mLoadingOverlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // find view references
        mLoadingOverlay = (FrameLayout) findViewById(R.id.loading_overlay);
        BottomNavigationView mBottomMenu = (BottomNavigationView) findViewById(R.id.bottom_navigation);

        // set view properties
        mBottomMenu.setOnNavigationItemSelectedListener(this);

        mPresenter = new MapPresenter(this, SgPsiApplication.getPsiRepository());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    protected void onStart(){
        super.onStart();
        mPresenter.onStart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_map, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_refresh:
                if(mLoadingOverlay.getVisibility() == View.GONE){
                    mPresenter.onRefreshClick();
                }
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setInfoWindowAdapter(new PsiInfoWindowAdapter(this));
        mMap.getUiSettings().setMapToolbarEnabled(false);

        // move the camera to singapore
        LatLng singapore = new LatLng(1.3521, 103.8198);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(singapore));
        mMap.moveCamera(CameraUpdateFactory.zoomTo(10));

        mPresenter.onMapReady();
    }

    @Override
    public void showLoading(boolean isLoading) {
        mLoadingOverlay.setVisibility(isLoading ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showPsiInfo(@NonNull List<RegionInfo> regionInfoList) {
        if(mMap != null){
            // move the camera to singapore
            LatLng singapore = new LatLng(1.3521, 103.8198);
            mMap.moveCamera(CameraUpdateFactory.newLatLng(singapore));
            mMap.moveCamera(CameraUpdateFactory.zoomTo(10));

            mMap.clear();

            for(RegionInfo regionInfo : regionInfoList){
                drawMarker(mMap, regionInfo, SHOW_PSI);
            }
        }
    }

    @Override
    public void showPollutantInfo(@NonNull List<RegionInfo> regionInfoList) {
        if(mMap != null){
            // move the camera to singapore
            LatLng singapore = new LatLng(1.3521, 103.8198);
            mMap.moveCamera(CameraUpdateFactory.newLatLng(singapore));
            mMap.moveCamera(CameraUpdateFactory.zoomTo(10));

            mMap.clear();

            for(RegionInfo regionInfo : regionInfoList){
                drawMarker(mMap, regionInfo, SHOW_POLLUTANT);
            }
        }
    }

    @Override
    public void showNetworkError() {
        Toast.makeText(this, "Network error. Please try again later", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showUnknownError() {
        Toast.makeText(this, "An error has occurred. Please try again later.",
                Toast.LENGTH_SHORT).show();
    }

    private void drawMarker(@NonNull GoogleMap googleMap,
                            @NonNull RegionInfo regionInfo,
                            int showType){
        if(regionInfo.lat != 0 && regionInfo.lng != 0){
            String snippet;
            switch(showType){
                case SHOW_PSI:
                    snippet = getString(R.string.data_item_psi, regionInfo.getPsiTwentyFourHourly());
                    break;

                case SHOW_POLLUTANT:
                    StringBuilder sb = new StringBuilder();
                    sb.append(getString(R.string.data_item_sulphur_dioxide, regionInfo.getSulphurDioxideTwentyFourHourly()))
                            .append("\n")
                            .append(getString(R.string.data_item_pm10, regionInfo.getPm10TwentyFourHourly()))
                            .append("\n")
                            .append(getString(R.string.data_item_nitrogen_dioxide, regionInfo.getNitrogenDioxideOneHourMax()))
                            .append("\n")
                            .append(getString(R.string.data_item_ozone, regionInfo.getOzoneEightHourMax()))
                            .append("\n")
                            .append(getString(R.string.data_item_carbon_monoxide, String.format(Locale.US, "%.2f", regionInfo.getCarbonMonoxideEightHourMax())))
                            .append("\n")
                            .append(getString(R.string.data_item_pm25, regionInfo.getPm25TwentyFourHourly()));
                    snippet = sb.toString();
                    break;

                default:
                    throw new IllegalArgumentException(
                            "showType must be either SHOW_PSI or SHOW_POLLUTANT");
            }


            MarkerOptions marker = new MarkerOptions()
                    .position(new LatLng(regionInfo.lat, regionInfo.lng))
                    .title(regionInfo.name.toUpperCase(Locale.US))
                    .snippet(snippet);

            googleMap.addMarker(marker);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_psi:
                mPresenter.onPsiSelect();
                return true;

            case R.id.action_pollutant:
                mPresenter.onPollutantSelect();
                return true;

            default: return false;
        }
    }
}
