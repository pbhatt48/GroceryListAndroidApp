package edu.gatech.seclass.glm.activities;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import edu.gatech.seclass.glm.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isNotChecked;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityCheckAllTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class, false, false);

    @Test
    public void mainActivityCheckAllTest() {
        mActivityTestRule.launchActivity(null);
        ViewInteraction linearLayout = onView(
                allOf(childAtPosition(
                        allOf(withId(android.R.id.list),
                                withParent(withId(R.id.content_main))),
                        0),
                        isDisplayed()));
        linearLayout.perform(click());

        ViewInteraction floatingActionButton = onView(
                allOf(withId(R.id.toggle), isDisplayed()));
        floatingActionButton.perform(click());

        ViewInteraction checkBox = onView(
                allOf(withId(R.id.checkbox),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.list),
                                        1),
                                0),
                        isDisplayed()));
        checkBox.check(matches(isNotChecked()));

        ViewInteraction checkBox2 = onView(
                allOf(withId(R.id.checkbox),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.list),
                                        3),
                                0),
                        isDisplayed()));
        checkBox2.check(matches(isNotChecked()));

        ViewInteraction checkBox3 = onView(
                allOf(withId(R.id.checkbox),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.list),
                                        4),
                                0),
                        isDisplayed()));
        checkBox3.check(matches(isNotChecked()));

        ViewInteraction checkBox4 = onView(
                allOf(withId(R.id.checkbox),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.list),
                                        5),
                                0),
                        isDisplayed()));
        checkBox4.check(matches(isNotChecked()));

        ViewInteraction checkBox5 = onView(
                allOf(withId(R.id.checkbox),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.list),
                                        6),
                                0),
                        isDisplayed()));
        checkBox5.check(matches(isNotChecked()));

        ViewInteraction checkBox6 = onView(
                allOf(withId(R.id.checkbox),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.list),
                                        6),
                                0),
                        isDisplayed()));
        checkBox6.check(matches(isNotChecked()));

    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
