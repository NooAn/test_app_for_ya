package com.example.andre.mytestapp;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.test.suitebuilder.annotation.LargeTest;

import com.example.andre.mytestapp.fragment.ItemFragment;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;

/**
 * Created by Andre on 22.04.2016.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest

public class EspressoTest {


    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule(MainActivity.class);

    //offline
    @Test
    public void testView() {
        ItemFragment myFragment = startMyFragment();
        onView(ViewMatchers.withId(R.id.my_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(2, click()));
    }

    private ItemFragment startMyFragment() {
        FragmentActivity activity = (FragmentActivity) mActivityRule.getActivity();
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
        ItemFragment myFragment = new ItemFragment().newInstance(Cash.getInstance(mActivityRule.getActivity()).take());
        transaction.add(myFragment, "tag");
        transaction.commit();
        return myFragment;
    }
}
