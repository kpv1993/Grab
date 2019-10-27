package com.example.grab;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class InstrumentedTest {
    @Rule
    public ActivityTestRule mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void checkScroll() {
        RecyclerView recyclerView = mActivityRule.getActivity().findViewById(R.id.rView);
        int itemCount = recyclerView.getAdapter().getItemCount();
        onView(withId(R.id.rView)).perform(RecyclerViewActions.<RecyclerView.ViewHolder>scrollToPosition(itemCount-1));
    }

}