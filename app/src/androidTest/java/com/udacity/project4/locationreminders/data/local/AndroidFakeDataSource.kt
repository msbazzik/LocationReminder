package com.udacity.project4.locationreminders.data.local

import com.udacity.project4.locationreminders.data.ReminderDataSource
import com.udacity.project4.locationreminders.data.dto.ReminderDTO
import com.udacity.project4.locationreminders.data.dto.Result

//Use AndroidFakeDataSource that acts as a test double to the LocalDataSource
class AndroidFakeDataSource(var reminders: MutableList<ReminderDTO>? = mutableListOf()) :
    ReminderDataSource {
    private var shouldReturnError = false

    fun setReturnError(value: Boolean) {
        shouldReturnError = value
    }

    override suspend fun getReminders(): Result<List<ReminderDTO>> {
        if (shouldReturnError) {
            return Result.Error("Test exception")
        }
        reminders?.let { return Result.Success(ArrayList(it)) }
        return Result.Error(
            "Reminders not found"
        )
    }

    override suspend fun saveReminder(reminder: ReminderDTO) {
        reminders?.add(reminder)
    }

    override suspend fun getReminder(id: String): Result<ReminderDTO> {
        val result = reminders?.find { e -> e.id == id }
        return when (result != null) {
            true -> Result.Success(result)
            false -> Result.Error(
                "Reminders not found"
            )
        }
    }

    override suspend fun deleteAllReminders() {
        reminders?.clear()
    }


}