//package com.nexte.nexte;
//
//
//import android.support.test.espresso.ViewInteraction;
//import android.support.test.rule.ActivityTestRule;
//import android.support.test.runner.AndroidJUnit4;
//import android.test.suitebuilder.annotation.LargeTest;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.ViewParent;
//
//import org.hamcrest.Description;
//import org.hamcrest.Matcher;
//import org.hamcrest.TypeSafeMatcher;
//import org.hamcrest.core.IsInstanceOf;
//import org.junit.Rule;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//
//import static android.support.test.espresso.Espresso.onView;
//import static android.support.test.espresso.action.ViewActions.click;
//import static android.support.test.espresso.assertion.ViewAssertions.matches;
//import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
//import static android.support.test.espresso.matcher.ViewMatchers.withId;
//import static android.support.test.espresso.matcher.ViewMatchers.withText;
//import static org.hamcrest.Matchers.allOf;
//
//@LargeTest
//@RunWith(AndroidJUnit4.class)
//public class MainActivityTest3 {
//
//    @Rule
//    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);
//
//    @Test
//    public void mainActivityTest3() {
//        // Added a sleep statement to match the app's execution delay.
//        // The recommended way to handle such scenarios is to use Espresso idling resources:
//        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
//        try {
//            Thread.sleep(700);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        ViewInteraction appCompatButton = onView(
//                allOf(withId(R.id.navigationLogin), withText("Navegar pelo NEXTE!"),
//                        childAtPosition(
//                                childAtPosition(
//                                        withId(android.R.id.content),
//                                        0),
//                                3),
//                        isDisplayed()));
//        appCompatButton.perform(click());
//
//        // Added a sleep statement to match the app's execution delay.
//        // The recommended way to handle such scenarios is to use Espresso idling resources:
//        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
//        try {
//            Thread.sleep(700);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        ViewInteraction recyclerView = onView(
//                allOf(withId(R.id.feedRecyclerView),
//                        childAtPosition(
//                                childAtPosition(
//                                        withId(R.id.main_frame_layout),
//                                        0),
//                                0),
//                        isDisplayed()));
//        recyclerView.check(matches(isDisplayed()));
//
//        ViewInteraction view = onView(
//                allOf(withId(R.id.feedActivity),
//                        childAtPosition(
//                                allOf(withId(R.id.feedRecyclerView),
//                                        childAtPosition(
//                                                IsInstanceOf.<View>instanceOf(android.view.View.class),
//                                                0)),
//                                2),
//                        isDisplayed()));
//        view.check(matches(isDisplayed()));
//
//        ViewInteraction view2 = onView(
//                allOf(withId(R.id.feedActivity),
//                        childAtPosition(
//                                allOf(withId(R.id.feedRecyclerView),
//                                        childAtPosition(
//                                                IsInstanceOf.<View>instanceOf(android.view.View.class),
//                                                0)),
//                                1),
//                        isDisplayed()));
//        view2.check(matches(isDisplayed()));
//
//        ViewInteraction view3 = onView(
//                allOf(withId(R.id.feedActivity),
//                        childAtPosition(
//                                allOf(withId(R.id.feedRecyclerView),
//                                        childAtPosition(
//                                                IsInstanceOf.<View>instanceOf(android.view.View.class),
//                                                0)),
//                                0),
//                        isDisplayed()));
//        view3.check(matches(isDisplayed()));
//
//        ViewInteraction view4 = onView(
//                allOf(withId(R.id.feedActivity),
//                        childAtPosition(
//                                allOf(withId(R.id.feedRecyclerView),
//                                        childAtPosition(
//                                                IsInstanceOf.<View>instanceOf(android.view.View.class),
//                                                0)),
//                                0),
//                        isDisplayed()));
//        view4.check(matches(isDisplayed()));
//
//        ViewInteraction view5 = onView(
//                allOf(withId(R.id.feedActivity),
//                        childAtPosition(
//                                allOf(withId(R.id.feedRecyclerView),
//                                        childAtPosition(
//                                                IsInstanceOf.<View>instanceOf(android.view.View.class),
//                                                0)),
//                                0),
//                        isDisplayed()));
//        view5.check(matches(isDisplayed()));
//
//    }
//
//    private static Matcher<View> childAtPosition(
//            final Matcher<View> parentMatcher, final int position) {
//
//        return new TypeSafeMatcher<View>() {
//            @Override
//            public void describeTo(Description description) {
//                description.appendText("Child at position " + position + " in parent ");
//                parentMatcher.describeTo(description);
//            }
//
//            @Override
//            public boolean matchesSafely(View view) {
//                ViewParent parent = view.getParent();
//                return parent instanceof ViewGroup && parentMatcher.matches(parent)
//                        && view.equals(((ViewGroup) parent).getChildAt(position));
//            }
//        };
//    }
//}
