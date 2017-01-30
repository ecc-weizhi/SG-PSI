package com.example.weizhi.sg_psi.ui.map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.weizhi.sg_psi.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

/**
 * @author Lin Weizhi (ecc.weizhi@gmail.com)
 */

public class PsiInfoWindowAdapter implements GoogleMap.InfoWindowAdapter{
    private Context mContext;

    public PsiInfoWindowAdapter(Context context){
        mContext = context;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        // We are using the default window. Return null here.
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.view_map_info_content, null, false);
        TextView titleTextView = (TextView)v.findViewById(R.id.title_text);
        TextView snippetTextView = (TextView)v.findViewById(R.id.snippet_text);

        titleTextView.setText(marker.getTitle());
        snippetTextView.setText(marker.getSnippet());

        return v;
    }
}
