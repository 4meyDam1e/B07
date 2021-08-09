package com.example.health;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.health.Classes.User;

public class dashboardFragmentAdapter extends FragmentStateAdapter {
    final int count = 3;
    dashboardFragment[] fr;

    public dashboardFragmentAdapter(@NonNull FragmentManager fragmentManager,
                                    @NonNull Lifecycle lifecycle,
                                    String role) {
        super(fragmentManager, lifecycle);
        fr = new dashboardFragment[count];
        if(role.equals("patient")) {
            fr[0] = new dashboardFirstFragment();
            fr[1] = new newAppointmentFragment();
            fr[2] = new profileFragment();
        } else {
            fr[0] = new dashboardFirstFragment();
            fr[1] = new doctorScheduleFragment();
            fr[2] = new profileDoctorFragment();
        }

    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fr[position];
    }

    @Override
    public int getItemCount() {
        return count;
    }

    public void update(User user) {
        for (dashboardFragment f : fr)
            f.update(user);
    }

    public void updateFocus(int position) {
        fr[position].updateFocus();
    }
}
