package com.example.andre.mytestapp;

import android.support.test.espresso.contrib.RecyclerViewActions;
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
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

/**
 * Created by Andre on 22.04.2016.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest

public class EspressoTest {


    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule(MainActivity.class);

    @Test
    public void listGoesOverTheFold() {
//        onView(withId(R.id.card_view)).perform(RecyclerViewActions.actionOnItemAtPosition(2, click()));
        //  onView(withId(R.id.image)).check(matches(isDisplayed()));
    }
    @Test
    public void testView() {
        ItemFragment myFragment = startMyFragment();
        onView(withId(R.id.my_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(2, click()));
        onView(withId(R.id.count_albums)).check(matches(withText("152 альбома")) );
        //(is(instanceOf(ItemFragment.class)));
    }
    private ItemFragment startMyFragment() {
        FragmentActivity activity = (FragmentActivity) mActivityRule.getActivity();
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
        ItemFragment myFragment = new ItemFragment().newInstance(Cash.getInstance(mActivityRule.getActivity()).take());;
        transaction.add(myFragment, "myfrag");
        transaction.commit();
        return myFragment;
    }
}
