package com.udacity.project4.locationreminders.savereminder

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.PointOfInterest
import com.udacity.project4.R
import com.udacity.project4.locationreminders.data.FakeDataSource
import com.udacity.project4.locationreminders.getOrAwaitValue
import com.udacity.project4.locationreminders.reminderslist.ReminderDataItem
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers
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

        // Then there is false returned and snackBar is set to error message
        assertThat(isTitleValid, `is`(false))

        val value = saveReminderViewModel.showSnackBarInt.getOrAwaitValue()
        assertThat(value, `is`(R.string.err_enter_title))
    }

    @Test
    fun validateEnteredData_locationIsNullOrEmpty() {
        // Given a reminderDataItem with empty location

        // When validate title is null or Empty
        val isLocatonValid = saveReminderViewModel.validateEnteredData(reminderDataItem2)

        // Then there is false returned and snackBar is set to error message
        assertThat(isLocatonValid, `is`(false))

        val value = saveReminderViewModel.showSnackBarInt.getOrAwaitValue()
        assertThat(value, `is`(R.string.err_select_location))
    }

    @Test
    fun onClear() {
        // Given a Point of Interest to save in the viewModel livedata
        val poi =  PointOfInterest(
            LatLng(37.4222359923114, -122.08400387119623),
            "0002",
            "googleplex"
        )
        saveReminderViewModel.savePoi(poi)

        // When clear the livedata
        saveReminderViewModel.onClear()

        // Then viewModel's livedata is all null
        assertThat(saveReminderViewModel.selectedPOI.getOrAwaitValue(), `is`(Matchers.nullValue()))
        assertThat(saveReminderViewModel.reminderSelectedLocationStr.getOrAwaitValue(), `is`(Matchers.nullValue()))
        assertThat(saveReminderViewModel.reminderTitle.getOrAwaitValue(), `is`(Matchers.nullValue()))
        assertThat(saveReminderViewModel.latitude.getOrAwaitValue(), `is`(Matchers.nullValue()))
    }

    @Test
    fun savePoi() {
        // Given a Point of Interest
        val poi =  PointOfInterest(
            LatLng(-34.0,
                151.0),
            "0001",
            "sydney"
        )

        // When save it to the viewModel properties
        saveReminderViewModel.savePoi(poi)

        // Then viewModel's livedata is updated
        assertThat(saveReminderViewModel.selectedPOI.getOrAwaitValue(), `is`(poi))
    }
}