package com.example.taher.maak_x_alseka.UI.Adapter;

/**
 * Created by taher on 25/11/16.
 */
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.taher.maak_x_alseka.Data.Data;
import com.example.taher.maak_x_alseka.UI.Fragment.Confirms;
import com.example.taher.maak_x_alseka.UI.Fragment.Profile;
import com.example.taher.maak_x_alseka.UI.Fragment.Requests;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                Profile profile = new Profile();
                profile.setUser(Data.user);
                Profile tab1 = profile;
                return tab1;
            case 1:
                Requests tab2 = new Requests();
                return tab2;
            case 2:
                Confirms tab3 = new Confirms();
                return tab3;
            default:
                return new Profile();
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}