package com.loeches.yugioh.Modelo.Global;

import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MultiClickListener implements View.OnClickListener {
    private final List<View.OnClickListener> listeners = new ArrayList<>();

    public void addListener(View.OnClickListener listener) {
        listeners.add(listener);
    }

    @Override
    public void onClick(View v) {
        for (View.OnClickListener listener : listeners) {
            listener.onClick(v);
        }
    }
}