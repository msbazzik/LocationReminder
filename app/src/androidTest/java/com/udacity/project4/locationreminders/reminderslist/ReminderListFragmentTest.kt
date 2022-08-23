package com.udacity.project4.locationreminders.reminderslist

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.udacity.project4.R
import com.udacity.project4.locationreminders.data.dto.ReminderDTO
import com.udacity.project4.locationreminders.data.local.AndroidFakeDataSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
//UI Testing
@MediumTest
class ReminderListFragmentTest {

    private lateinit var dataSource: AndroidFakeDataSource
    private lateinit var reminder1: ReminderDTO
    private lateinit var reminder2: ReminderDTO

    @Before
    fun setupViewModel() {
        dataSource = AndroidFakeDataSource()

        reminder1 = ReminderDTO(
            "title1",
            "description1",
            "location3",
            -30.0,
            150.0
        )
        reminder2 = ReminderDTO(
            "title2",
            "description2",
            "location3",
            -34.0,
            151.0
        )

        runBlockingTest {
            dataSource.saveReminder(reminder1)
            dataSource.saveReminder(reminder2)
        }

    }

//    TODO: test the navigation of the fragments.
//    TODO: test the displayed data on the UI.
//    TODO: add testing for the error messages.

    @Test
    fun displayReminderList_twoRemindersDisplayed() {
        // GIVEN two reminders

        // WHEN - ReminderList Fragment launched
        launchFragmentInContainer<ReminderListFragment>(Bundle(), R.style.AppTheme)
        Thread.sleep(2000)
        //Then two reminders are displayed

//        onView(withId(R.id.reminderssRecyclerView)).check(matches(isDisplayed()))
//        onView(withId(R.id.title)).check(
//            matches(
//                withText(
//                    "title1"
//                )
//            )
//        )


    }
}