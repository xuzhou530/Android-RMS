package me.biubiubiu.rms;

import android.util.*;
import android.widget.*;
import android.view.*;
import android.content.*;
import android.app.*;
import android.os.*;
import android.database.*;
import android.net.*;
import android.opengl.*;
import android.graphics.*;
import android.view.animation.*;

import java.util.*;
import org.json.*;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

public class BaseFragment extends Fragment {

    public BaseFragment(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return null;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    protected void startFragment(Fragment f) {
        Activity a = getActivity();
        if (a != null) {
            FragmentActivity act = (FragmentActivity)a;
            FragmentManager fragmentManager = act.getSupportFragmentManager();
            fragmentManager.beginTransaction()
                .replace(R.id.content_frame, f)
                .addToBackStack(null)
                .commit();
        }
    }
}