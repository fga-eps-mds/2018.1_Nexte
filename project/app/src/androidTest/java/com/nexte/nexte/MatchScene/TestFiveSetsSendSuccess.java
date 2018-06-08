package com.nexte.nexte.MatchScene;


import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.nexte.nexte.MainActivity;
import com.nexte.nexte.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class TestFiveSetsSendSuccess {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testFiveSetsSendSuccess() {
        ViewInteraction bottomNavigationItemView = onView(
                allOf(ViewMatchers.withId(R.id.challenge),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.bottom_nav_view),
                                        0),
                                1),
                        isDisplayed()));
        bottomNavigationItemView.perform(click());

        ViewInteraction appCompatImageView = onView(
                allOf(withId(R.id.userPicture), withContentDescription("User picture"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.challengeRecyclerView),
                                        2),
                                0),
                        isDisplayed()));
        appCompatImageView.perform(click());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.sendChallengeButton), withText("Enviar Desafio"),
                        childAtPosition(
                                withParent(withId(R.id.challengerviewpager)),
                                5),
                        isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(android.R.id.button1), withText("Ok"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        appCompatButton2.perform(scrollTo(), click());

        ViewInteraction radioRealButton = onView(
                allOf(withId(R.id.buttonFive), withText("5"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.buttonGroup),
                                        2),
                                2),
                        isDisplayed()));
        radioRealButton.perform(click());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.challengerResult),
                        childAtPosition(
                                allOf(withId(R.id.matchActivity),
                                        childAtPosition(
                                                withId(R.id.matchRecyclerView),
                                                1)),
                                5),
                        isDisplayed()));
        appCompatEditText.perform(replaceText("6"), closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.challengedResult),
                        childAtPosition(
                                allOf(withId(R.id.matchActivity),
                                        childAtPosition(
                                                withId(R.id.matchRecyclerView),
                                                1)),
                                3),
                        isDisplayed()));
        appCompatEditText2.perform(replaceText("7"), closeSoftKeyboard());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.challengerResult),
                        childAtPosition(
                                allOf(withId(R.id.matchActivity),
                                        childAtPosition(
                                                withId(R.id.matchRecyclerView),
                                                1)),
                                5),
                        isDisplayed()));
        appCompatEditText3.perform(replaceText("7"), closeSoftKeyboard());

        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.challengedResult),
                        childAtPosition(
                                allOf(withId(R.id.matchActivity),
                                        childAtPosition(
                                                withId(R.id.matchRecyclerView),
                                                1)),
                                3),
                        isDisplayed()));
        appCompatEditText4.perform(replaceText("6"), closeSoftKeyboard());

        ViewInteraction appCompatEditText5 = onView(
                allOf(withId(R.id.challengerResult),
                        childAtPosition(
                                allOf(withId(R.id.matchActivity),
                                        childAtPosition(
                                                withId(R.id.matchRecyclerView),
                                                1)),
                                5),
                        isDisplayed()));
        appCompatEditText5.perform(replaceText("6"), closeSoftKeyboard());

        ViewInteraction appCompatEditText6 = onView(
                allOf(withId(R.id.challengedResult),
                        childAtPosition(
                                allOf(withId(R.id.matchActivity),
                                        childAtPosition(
                                                withId(R.id.matchRecyclerView),
                                                1)),
                                3),
                        isDisplayed()));
        appCompatEditText6.perform(replaceText("7"), closeSoftKeyboard());

        ViewInteraction appCompatEditText7 = onView(
                allOf(withId(R.id.challengerResult),
                        childAtPosition(
                                allOf(withId(R.id.matchActivity),
                                        childAtPosition(
                                                withId(R.id.matchRecyclerView),
                                                1)),
                                5),
                        isDisplayed()));
        appCompatEditText7.perform(replaceText("7"), closeSoftKeyboard());

        ViewInteraction appCompatEditText8 = onView(
                allOf(withId(R.id.challengedResult),
                        childAtPosition(
                                allOf(withId(R.id.matchActivity),
                                        childAtPosition(
                                                withId(R.id.matchRecyclerView),
                                                1)),
                                3),
                        isDisplayed()));
        appCompatEditText8.perform(replaceText("6"), closeSoftKeyboard());

        ViewInteraction appCompatEditText9 = onView(
                allOf(withId(R.id.challengerResult),
                        childAtPosition(
                                allOf(withId(R.id.matchActivity),
                                        childAtPosition(
                                                withId(R.id.matchRecyclerView),
                                                1)),
                                5),
                        isDisplayed()));
        appCompatEditText9.perform(replaceText("10"), closeSoftKeyboard());

        ViewInteraction appCompatEditText10 = onView(
                allOf(withId(R.id.challengedResult),
                        childAtPosition(
                                allOf(withId(R.id.matchActivity),
                                        childAtPosition(
                                                withId(R.id.matchRecyclerView),
                                                1)),
                                3),
                        isDisplayed()));
        appCompatEditText10.perform(replaceText("9"), closeSoftKeyboard());

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.sendButton), withText("Send  Challenge"),
                        childAtPosition(
                                withParent(withId(R.id.challengerviewpager)),
                                1),
                        isDisplayed()));
        appCompatButton3.perform(click());

        ViewInteraction appCompatButton4 = onView(
                allOf(withId(android.R.id.button1), withText("Ok"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        appCompatButton4.perform(scrollTo(), click());

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
