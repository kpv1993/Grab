package com.example.grab

import com.example.grab.utils.CommonUtils
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun dateTest() {
        assertEquals("27-10-2019", CommonUtils.getDate("2019-10-27T06:54:07Z"))
    }
}
