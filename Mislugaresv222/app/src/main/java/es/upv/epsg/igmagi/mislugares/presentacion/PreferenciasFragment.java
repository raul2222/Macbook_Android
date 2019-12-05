package es.upv.epsg.igmagi.mislugares.presentacion;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import es.upv.epsg.igmagi.mislugares.R;

public class PreferenciasFragment extends PreferenceFragment {
    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferencias);
    }
}