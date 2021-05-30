package com.example.data.local


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.data.source.local.PhonesDataBase
import com.example.domain.model.ResultsItem
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.core.IsNull.notNullValue
import org.junit.*
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class PhonesDaoTes {
    @get:Rule
    var instantExcuteRule = InstantTaskExecutorRule()

    private lateinit var database: PhonesDataBase

    @Before
    fun initDb() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            PhonesDataBase::class.java
        ).build()
    }

    @After
    fun closeDb() = database.close()

    @Test
    fun insertTasksAndGetByIds() = runBlocking {
        //Given - insert phone
        val phone = ResultsItem(
            1, 20, "", 200, 300, "test",
            "123", "test beand", 3.0f
        )
        database.phonesDao().insert(phone)
        // When - get the phone by id from data base
        val loaded = database.phonesDao().getPhoneById(phone.id)

        //Then - the loaded data contains the expected values
        Assert.assertThat(loaded as ResultsItem, notNullValue())
        Assert.assertEquals(phone.id,loaded.id)
        Assert.assertEquals(phone.brand,loaded.brand)
        Assert.assertEquals(phone.image,loaded.image)
        Assert.assertEquals(phone.max_saving_percentage,loaded.max_saving_percentage)
        Assert.assertEquals(phone.price,loaded.price)
        Assert.assertEquals(phone.sku,loaded.sku)
        Assert.assertEquals(phone.rating_average,loaded.rating_average)
        Assert.assertEquals(phone.special_price,loaded.special_price)


    }


}