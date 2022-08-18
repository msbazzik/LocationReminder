package com.udacity.project4.locationreminders.savereminder

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.udacity.project4.locationreminders.data.FakeDataSource
import com.udacity.project4.locationreminders.reminderslist.ReminderDataItem
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.stopKoin

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class SaveReminderViewModelTest {

    private lateinit var saveReminderViewModel: SaveReminderViewModel
    private lateinit var dataSource: FakeDataSource
    private lateinit var reminderDataItem1: ReminderDataItem
    private lateinit var reminderDataItem2: ReminderDataItem

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setupViewModel() {
        dataSource = FakeDataSource()
        saveReminderViewModel =
            SaveReminderViewModel(ApplicationProvider.getApplicationContext(), dataSource)
        reminderDataItem1 = ReminderDataItem(
            "",
            "description",
            "location",
            -34.0,
            151.0
        )
        reminderDataItem2 = ReminderDataItem(
            "title",
            "description",
            "",
            -34.0,
            151.0
        )
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun validateEnteredData_titleIsNullOrEmpty() {
        // Given a reminderDataItem with empty title

        // When validate title is null or Empty
        val isTitleValid = saveReminderViewModel.validateEnteredData(reminderDataItem1)

        // Then there is false returned
        assertThat(isTitleValid, `is`(false))
    }

    @Test
    fun validateEnteredData_locationIsNullOrEmpty() {
        // Given a reminderDataItem with empty location

        // When validate title is null or Empty
        val isLocatonValid = saveReminderViewModel.validateEnteredData(reminderDataItem2)

        // Then there is false returned
        assertThat(isLocatonValid, `is`(false))
    }

}