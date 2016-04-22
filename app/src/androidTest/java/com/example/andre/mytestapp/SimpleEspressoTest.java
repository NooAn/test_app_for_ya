package com.example.andre.mytestapp;

/**
 * Created by Andre on 22.04.2016.
 */

import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.matcher.ViewMatchers;
import android.test.ActivityInstrumentationTestCase2;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class SimpleEspressoTest extends ActivityInstrumentationTestCase2<MainActivity> {
    private MainActivity mActivity;

    public SimpleEspressoTest() {
        super(MainActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
        mActivity = getActivity();
    }

    public void testEnterName() throws Exception {
        Thread.sleep(5000);
//        onView(withId(R.id.progress)).check(matches(isDisplayed()));
//        onView(withId(R.id.count)).perform(click());
        onView(ViewMatchers.withId(R.id.my_recycler_view))
                .check(matches(withText("22 альбома")));
    }
}