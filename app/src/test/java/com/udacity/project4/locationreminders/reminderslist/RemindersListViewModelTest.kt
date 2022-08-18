package com.udacity.project4.locationreminders.reminderslist

import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.udacity.project4.locationreminders.data.FakeDataSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
class RemindersListViewModelTest {

    private lateinit var viewModelTest: RemindersListViewModel
    private lateinit var dataSource: FakeDataSource

    @Before
    fun setupViewModel() {
        dataSource = FakeDataSource()
        viewModelTest =
            RemindersListViewModel(ApplicationProvider.getApplicationContext(), dataSource)
    }

    //TODO: provide testing to the RemindersListViewModel and its live data objects

}