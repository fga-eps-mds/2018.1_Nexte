package com.nexte.nexte;


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

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class CommentsTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void commentsTest() {
        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(700);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.navigationLogin), withText("Navegar pelo NEXTE!"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        appCompatButton.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(700);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatImageButton = onView(
                allOf(withId(R.id.commentsButton),
                        childAtPosition(
                                allOf(withId(R.id.feedActivity),
                                        childAtPosition(
                                                withId(R.id.feedRecyclerView),
                                                1)),
                                15),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.commentEditText),
                        childAtPosition(
                                allOf(withId(R.id.mainLayout),
                                        childAtPosition(
                                                withId(R.id.main_frame_layout),
                                                1)),
                                0),
                        isDisplayed()));
        appCompatEditText.perform(replaceText("oi"), closeSoftKeyboard());

        pressBack();

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.commentEditText), withText("oi"),
                        childAtPosition(
                                allOf(withId(R.id.mainLayout),
                                        childAtPosition(
                                                withId(R.id.main_frame_layout),
                                                1)),
                                0),
                        isDisplayed()));
        appCompatEditText2.perform(replaceText("Oi\n"));

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.commentEditText), withText("Oi\n"),
                        childAtPosition(
                                allOf(withId(R.id.mainLayout),
                                        childAtPosition(
                                                withId(R.id.main_frame_layout),
                                                1)),
                                0),
                        isDisplayed()));
        appCompatEditText3.perform(closeSoftKeyboard());

        ViewInteraction appCompatImageButton2 = onView(
                allOf(withId(R.id.checkButton),
                        childAtPosition(
                                allOf(withId(R.id.mainLayout),
                                        childAtPosition(
                                                withId(R.id.main_frame_layout),
                                                1)),
                                4),
                        isDisplayed()));
        appCompatImageButton2.perform(click());

        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.commentEditText), withText("Oi\n"),
                        childAtPosition(
                                allOf(withId(R.id.mainLayout),
                                        childAtPosition(
                                                withId(R.id.main_frame_layout),
                                                1)),
                                0),
                        isDisplayed()));
        appCompatEditText4.perform(replaceText(""));

        ViewInteraction appCompatEditText5 = onView(
                allOf(withId(R.id.commentEditText),
                        childAtPosition(
                                allOf(withId(R.id.mainLayout),
                                        childAtPosition(
                                                withId(R.id.main_frame_layout),
                                                1)),
                                0),
                        isDisplayed()));
        appCompatEditText5.perform(closeSoftKeyboard());

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
