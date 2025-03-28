package com.example.amphibians.Rules

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description

@OptIn(ExperimentalCoroutinesApi::class)
class TestDispatcherRule(
    private val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()
):TestWatcher() {

    /**
     * Invoked when a test is about to start
     */

    override fun starting(description: Description?) {
        Dispatchers.setMain(testDispatcher)
    }

    /**
     * Invoked when a test method finishes (whether passing or failing)
     */
    override fun finished(description: Description?) {
        Dispatchers.resetMain()
    }
}