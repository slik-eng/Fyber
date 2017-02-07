package com.sliksoft.fybertest;

import android.os.Bundle;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.v4.app.FragmentTransaction;

import com.sliksoft.fybertest.views.activities.MainActivity;
import com.sliksoft.fybertest.views.fragments.ErrorFragment;

import org.junit.Rule;
import org.junit.Test;

/**
 * Created by Slik.Eng on 05/02/2017.
 */

public class ErrorFragmentTest {
    private static final String ERROR_INVALID_APPID = "ERROR_INVALID_TIMESTAMP";
    private static final String ERROR_MESSAGE = "An invalid or expired timestamp was given as a parameter in the request.";
    @Rule
    public final ActivityRule<MainActivity> main = new ActivityRule<>(MainActivity.class);

    private ErrorFragment startFragment() {
        FragmentTransaction startFragment = main.get().getSupportFragmentManager().beginTransaction();
        ErrorFragment.newInstance(ERROR_INVALID_APPID + "\n" + ERROR_MESSAGE);
        ErrorFragment errorFragment = new ErrorFragment();
        startFragment.addToBackStack("ErrorFragment");
        startFragment.replace(R.id.container_frame_layout, errorFragment);
        startFragment.commit();

        return errorFragment;
    }

    @Test
    public void testErrorFragment() {
        startFragment();
    }
}
