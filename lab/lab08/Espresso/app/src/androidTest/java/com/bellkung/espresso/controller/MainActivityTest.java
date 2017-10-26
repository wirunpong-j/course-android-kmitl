package com.bellkung.espresso.controller;


import android.os.SystemClock;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.TextView;

import com.bellkung.espresso.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    private final String[] allName = {"Ying", "Ladarat", "Somkait", "Prayoch", "Prayoch"};
    private final int[] allAge = {20, 20, 80, 60, 50};

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void mainActivityTest() {
        // click ADDED without filling Name and Age field,
        // Expected "Please Enter user info" popup
        onView(withId(R.id.buttonAdded)).perform(click());
        onView(withId(android.R.id.message)).check(matches(withText("Please Enter user info")));
    }

    @Test
    public void unitTest2() {
        // click ADDED without filling Name and fill Age field with 20,
        // Expected "Please Enter user info" popup
        onView(withId(R.id.editTextAge)).perform(typeText("20"));
        closeSoftKeyboard();
        SystemClock.sleep(500);
        onView(withId(R.id.buttonAdded)).perform(click());
        onView(withId(android.R.id.message)).check(matches(withText("Please Enter user info")));

    }

    @Test
    public void unitTest3() {
        //click ADDED without filling Name and Age field,
        //Expected "Not Found" textview
        onView(withId(R.id.buttonGotoList)).perform(click());
        onView(withId(R.id.textNotFound)).check(matches(withText("Not Found")));

    }

    @Test
    public void unitTest4() {
        //click ADDED without filling Age and fill Name field with Ying,
        //Expected "Please Enter user info" popup
        onView(withId(R.id.editTExtName)).perform(typeText("Ying"));
        closeSoftKeyboard();
        SystemClock.sleep(500);
        onView(withId(R.id.buttonAdded)).perform(click());
        onView(withId(android.R.id.message)).check(matches(withText("Please Enter user info")));

    }

    @Test
    public void unitTest5() {
        //click ADDED fill Age with 20 and fill Name field with Ying,
        //and click go to list. Expected "Ying Age 20" at first.
        unitTest5to9(0);
        onView(withId(R.id.buttonGotoList)).perform(click());
        onView(withRecyclerView(R.id.list).atPositionOnView(0, R.id.textName))
                .check(matches(withText("Ying")));
        onView(withRecyclerView(R.id.list).atPositionOnView(0, R.id.textAge))
                .check(matches(withText("20")));

    }

    @Test
    public void unitTest6() {
        //click ADDED fill Age with 20 and fill Name field with Ladarat,
        //and click go to list. Expected "Ladarat Age 20" at second.
        unitTest5to9(1);
        onView(withId(R.id.buttonGotoList)).perform(click());
        onView(withRecyclerView(R.id.list).atPositionOnView(1, R.id.textName))
                .check(matches(withText("Ladarat")));
        onView(withRecyclerView(R.id.list).atPositionOnView(1, R.id.textAge))
                .check(matches(withText("20")));

    }

    @Test
    public void unitTest7() {
        //click ADDED fill Age with 80 and fill Name field with Somkait,
        //and click go to list. Expected "Somkait Age 80" third.
        unitTest5to9(2);
        onView(withId(R.id.buttonGotoList)).perform(click());
        onView(withRecyclerView(R.id.list).atPositionOnView(2, R.id.textName))
                .check(matches(withText("Somkait")));
        onView(withRecyclerView(R.id.list).atPositionOnView(2, R.id.textAge))
                .check(matches(withText("80")));

    }

    @Test
    public void unitTest8() {
        //click ADDED fill Age with 60 and fill Name field with Prayoch,
        //and click go to list. Expected "Prayoch Age 60" fourth.
        unitTest5to9(3);
        onView(withId(R.id.buttonGotoList)).perform(click());
        onView(withRecyclerView(R.id.list).atPositionOnView(3, R.id.textName))
                .check(matches(withText("Prayoch")));
        onView(withRecyclerView(R.id.list).atPositionOnView(3, R.id.textAge))
                .check(matches(withText("60")));

    }

    @Test
    public void unitTest9() {
        //click ADDED fill Age with 50 and fill Name field with Prayoch,
        //and click go to list. Expected "Prayoch Age 50" fifth.
        unitTest5to9(4);
        onView(withId(R.id.buttonGotoList)).perform(click());
        onView(withRecyclerView(R.id.list).atPositionOnView(4, R.id.textName))
                .check(matches(withText("Prayoch")));
        onView(withRecyclerView(R.id.list).atPositionOnView(4, R.id.textAge))
                .check(matches(withText("50")));

    }

    private void unitTest5to9(int childPos) {
        for (int i = 0; i <= childPos; i++) {
            onView(withId(R.id.editTExtName)).perform(clearText());
            onView(withId(R.id.editTExtName)).perform(typeText(allName[i]));
            onView(withId(R.id.editTextAge)).perform(clearText());
            onView(withId(R.id.editTextAge)).perform(typeText(String.valueOf(allAge[i])));
            closeSoftKeyboard();
            SystemClock.sleep(500);
            onView(withId(R.id.buttonAdded)).perform(click());
            SystemClock.sleep(500);
        }
    }


    private static RecyclerViewMatcher withRecyclerView(final int recyclerViewId) {
        return new RecyclerViewMatcher(recyclerViewId);
    }

}