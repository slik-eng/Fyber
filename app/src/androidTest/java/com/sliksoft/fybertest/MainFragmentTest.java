package com.sliksoft.fybertest;

import android.support.test.runner.AndroidJUnit4;
import android.support.v4.app.FragmentTransaction;

import com.sliksoft.fybertest.views.activities.MainActivity;
import com.sliksoft.fybertest.views.fragments.MainFragment;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isChecked;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.isNotChecked;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by Slik.Eng on 05/02/2017.
 */

@RunWith(AndroidJUnit4.class)
public class MainFragmentTest {

    @Rule
    public ActivityRule<MainActivity> mActivityRule = new ActivityRule<>(MainActivity.class);

    @Test
    public void testUiContentAppearance() {
        startMainFragment();
        testButton();
        testStaticTexts();
        testCheckboxesCanBeChecked();
        testSpinner();
        testEditText();
    }

    public MainFragment startMainFragment() {
        FragmentTransaction startFragment = mActivityRule.get().getSupportFragmentManager().beginTransaction();
        MainFragment mainFragment = new MainFragment();
        startFragment.addToBackStack("MainFragment");
        startFragment.replace(R.id.container_frame_layout, mainFragment);
        startFragment.commit();

        return mainFragment;
    }

    public void testButton() {
        onView(withId(R.id.request_button)).check(matches(isDisplayed())).check(matches(isClickable())).check(matches(isEnabled()));
    }

    public void testStaticTexts() {
        onView(withText(R.string.format_ui)).perform(scrollTo()).check(matches(isDisplayed()));
        onView(withText(R.string.json_ui)).perform(scrollTo()).check(matches(isDisplayed()));
        onView(withText(R.string.application_id_ui)).perform(scrollTo()).check(matches(isDisplayed()));
        onView(withText(R.string.user_id_ui)).perform(scrollTo()).check(matches(isDisplayed()));
        onView(withText(R.string.locale_ui)).perform(scrollTo()).check(matches(isDisplayed()));
        onView(withText(R.string.os_version_ui)).perform(scrollTo()).check(matches(isDisplayed()));
        onView(withText(R.string.google_ad_id_ui)).perform(scrollTo()).check(matches(isDisplayed()));
        onView(withText(R.string.limited_tracking_enabled_ui)).perform(scrollTo()).check(matches(isDisplayed()));

        onView(withId(R.id.optional_title_text_view)).perform(scrollTo()).check(matches(isDisplayed()));
        onView(withText(R.string.ip_ui)).perform(scrollTo()).check(matches(isDisplayed()));
        onView(withText(R.string.custom_parameters_ui)).perform(scrollTo()).check(matches(isDisplayed()));
        onView(withText(R.string.page_ui)).perform(scrollTo()).check(matches(isDisplayed()));
        onView(withText(R.string.offer_types_ui)).perform(scrollTo()).check(matches(isDisplayed()));
        onView(withText(R.string.ps_time_ui)).perform(scrollTo()).check(matches(isDisplayed()));
        onView(withText(R.string.device_ui)).perform(scrollTo()).check(matches(isDisplayed()));
    }

    public void testCheckboxesCanBeChecked() {
        onView(withId(R.id.google_ad_id_limited_tracking_enabled_check_box)).perform(scrollTo()).check(matches(isDisplayed())).check(matches(isChecked())).perform(click()).check(matches(isNotChecked()));
        onView(withId(R.id.ip_check_box)).perform(scrollTo()).check(matches(isDisplayed())).check(matches(isNotChecked())).perform(click()).check(matches(isChecked()));
        onView(withId(R.id.pub0_check_box)).perform(scrollTo()).check(matches(isDisplayed())).check(matches(isNotChecked())).perform(click()).check(matches(isChecked()));
        onView(withId(R.id.page_check_box)).perform(scrollTo()).check(matches(isDisplayed())).check(matches(isNotChecked())).perform(click()).check(matches(isChecked()));
        onView(withId(R.id.offer_types_check_box)).perform(scrollTo()).check(matches(isDisplayed())).check(matches(isNotChecked())).perform(click()).check(matches(isChecked()));
        onView(withId(R.id.ps_time_check_box)).perform(scrollTo()).check(matches(isDisplayed())).check(matches(isNotChecked())).perform(click()).check(matches(isChecked()));
    }

    public void testSpinner() {
        onView(withId(R.id.locale_spinner)).check(matches(isDisplayed())).check(matches(isEnabled()));
    }

    public void testEditText() {
        onView(withId(R.id.appid_edit_text)).perform(scrollTo()).check(matches(isDisplayed())).perform(clearText()).perform(typeText("2020")).check(matches(withText("2020")));
        onView(withId(R.id.uid_edit_text)).perform(scrollTo()).check(matches(isDisplayed())).perform(clearText()).perform(typeText("user id")).check(matches(withText("user id")));
    }

    @Test
    public void testMandatoryFilled() {
        startMainFragment();
        onView(withId(R.id.os_version_text_view)).perform(scrollTo()).check(matches(isDisplayed()));
        onView(withId(R.id.google_ad_id_text_view)).perform(scrollTo()).check(matches(isDisplayed()));
        submitRequest();
    }

    @Test
    public void testNonMandatoryParameters() {
        startMainFragment();
        submitRequest();
    }

    public void submitRequest() {
        onView(withId(R.id.request_button)).perform(click());
    }

}
