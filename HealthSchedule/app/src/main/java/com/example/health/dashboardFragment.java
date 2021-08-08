package com.example.health;

import android.view.View;

import androidx.fragment.app.Fragment;

import com.example.health.Classes.User;

public abstract class dashboardFragment extends Fragment {
    View view;
    User tag;
    public abstract void update(User user);
    public void updateFocus() {
        if (view != null)
            view.requestFocusFromTouch();
    }
}
