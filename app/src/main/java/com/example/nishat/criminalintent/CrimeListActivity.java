package com.example.nishat.criminalintent;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;

/**
 * Created by Nishat on 12/30/2016.
 */

/*
    Now CrimeListFragmenthas a way to call methods on its hosting activity. It does not matter which
    activity is doing the hosting. As long as the activity implements CrimeListFragment.Callbacks,
    everything in CrimeListFragmentcan work the same.
 */

public class CrimeListActivity extends SingleFragmentActivity implements
        CrimeListFragment.Callbacks, CrimeFragment.Callbacks {

    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }

    @Override
    protected int getLayoutResId() {
        //return R.layout.activity_twopane;
        return R.layout.activity_masterdetail;
    }

    @Override
    public void onCrimeSelected(Crime crime) {
        if (findViewById(R.id.detail_fragment_container) == null) {
            Intent intent = CrimePagerActivity.newIntent(this, crime.getId());
            startActivity(intent);
        } else {
            Fragment newDetail = CrimeFragment.newInstance(crime.getId());
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.detail_fragment_container, newDetail)
                    .commit();
        }
    }

    public void onCrimeUpdated(Crime crime) {
        CrimeListFragment listFragment = (CrimeListFragment)
                getSupportFragmentManager()
                        .findFragmentById(R.id.fragment_container);
        listFragment.updateUI();
    }

    @Override
    public void onCrimeDeleted(Crime crime) {
        Fragment newDetail = CrimeFragment.newInstance(crime.getId());
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.detail_fragment_container, newDetail)
                .commit();
    }
}
