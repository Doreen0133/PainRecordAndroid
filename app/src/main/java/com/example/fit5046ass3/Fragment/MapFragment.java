package com.example.fit5046ass3.Fragment;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.fit5046ass3.R;
import com.example.fit5046ass3.databinding.MapFragmentBinding;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;
import com.mapbox.mapboxsdk.plugins.annotation.SymbolManager;
import com.mapbox.mapboxsdk.plugins.annotation.SymbolOptions;
import com.mapbox.mapboxsdk.utils.BitmapUtils;


import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.mapbox.mapboxsdk.Mapbox.getApplicationContext;

public class MapFragment extends Fragment {
    private static final String ID_ICON_LOCATION = "location";
    private MapFragmentBinding binding;
    private MapView mapView;

    public MapFragment(){}

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        String token = getResources().getString(R.string.mapbox_access_token);
        Mapbox.getInstance(requireActivity(),token);
        // Inflate the View for this fragment
        binding =  MapFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        mapView = (MapView) view.findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);

        //method to get latitude and longitude
        binding.search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final LatLng latLng = getLatLangFromAddress(binding.inputdata.getText().toString());
                mapView.getMapAsync(new OnMapReadyCallback() {

                    @Override
                    public void onMapReady(@NonNull final MapboxMap mapboxMap) {
                        mapboxMap.setStyle(Style.MAPBOX_STREETS, new Style.OnStyleLoaded() {
                            @Override
                            public void onStyleLoaded(@NonNull Style style) {
                                addIconImageToStyle(style);
                                // Create a SymbolManager.
                                SymbolManager symbolManager = new SymbolManager(mapView, mapboxMap, style);

                        // Set non-data-driven properties.
                                symbolManager.setIconAllowOverlap(true);
                                symbolManager.setTextAllowOverlap(true);

                                // Create a symbol at the specified location.
                                SymbolOptions symbolOptions = new SymbolOptions()
                                        .withLatLng(latLng)
                                        .withIconImage(ID_ICON_LOCATION)
                                        .withIconSize(0.2f);

                                // Use the manager to draw the symbol.
                                symbolManager.create(symbolOptions);

                                CameraPosition position = new CameraPosition.Builder()
                                        .target(latLng)
                                        .zoom(13)
                                        .build();
                                mapboxMap.setCameraPosition(position);
                            }
                        });
                    }
                });
            }
        });
        return view;
    };

    private void addIconImageToStyle(Style style) {
        style.addImage(ID_ICON_LOCATION,
                BitmapUtils.getBitmapFromDrawable(getResources().getDrawable(R.drawable.icon4)),
                true);
    }

    //Retreiving some parts of code from https://stackoverflow.com/questions/3574644/how-can-i-find-the-latitude-and-longitude-from-address
    //Authors:Praveen
    ExecutorService executor = Executors.newSingleThreadExecutor();
        public static synchronized LatLng getLatLangFromAddress(String strAddress){
            Address lc = new Address(Locale.getDefault());
            Geocoder gc = new Geocoder(getApplicationContext(), Locale.getDefault());
            List<Address> addresses;
            try {
                addresses = gc.getFromLocationName(strAddress,1);
                if (addresses == null) {
                    System.out.println("null");
                    //return new LatLng(-10000, -10000);
                }
                Address ad = addresses.get(0);
                return new LatLng(ad.getLatitude(), ad.getLongitude());
            } catch (IOException e) {
                System.out.println("ERROR");
                return null;
            }
        }


    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }
    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }
    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }
    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }
    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}