package com.udacity.project4.locationreminders.reminderslist

import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.udacity.project4.locationreminders.data.FakeDataSource
import com.udacity.project4.locationreminders.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.stopKoin

@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
class RemindersListViewModelTest {

    private lateinit var remindersListViewModel: RemindersListViewModel
    private lateinit var dataSource: FakeDataSource

    @Before
    fun setupViewModel() {
        dataSource = FakeDataSource()
        remindersListViewModel =
            RemindersListViewModel(ApplicationProvider.getApplicationContext(), dataSource)
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun invalidateShowNoData_EmptyList() = runBlockingTest {
        // Given an empty remindersList and check if no data has to be shown
        dataSource.deleteAllReminders()
        remindersListViewModel.loadReminders()

        // Then there is false returned and snackBar is set to error message
        assertThat(remindersListViewModel.showNoData.getOrAwaitValue(), `is`(true))
    }

    @Test
    fun loadReminders_shouldReturnError() {
        // Make the repository return errors.
        dataSource.setReturnError(true)
        remindersListViewModel.loadReminders()

        //Then snackBar is updated
        assertThat(remindersListViewModel.showSnackBar.getOrAwaitValue(), `is`("Test exception"))
    }
}