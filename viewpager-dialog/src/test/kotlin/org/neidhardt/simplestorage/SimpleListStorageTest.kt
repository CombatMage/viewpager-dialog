package org.neidhardt.simplestorage

import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import kotlin.properties.Delegates

/**
 * Created by eric.neidhardt on 28.11.2016.
 */
@RunWith(RobolectricTestRunner::class)
class SimpleListStorageTest {

	private var unitUnderTest: SimpleListStorage<Int> by Delegates.notNull<SimpleListStorage<Int>>()

	@Before
	fun setUp() {
		this.unitUnderTest = SimpleListStorage(RuntimeEnvironment.application.applicationContext, Int::class.java)
		this.unitUnderTest.clear()
	}

	@Test
	fun precondition() {
		assertNotNull(this.unitUnderTest.storageKey)
		assertTrue(this.unitUnderTest.getAsync().blockingIterable().first().isEmpty())
	}

	@Test
	fun saveAndGet() {
		val data = listOf(1,2,3,4)
		this.unitUnderTest.saveAsync(data).blockingSubscribe()
		assertTrue(data.containsAll(this.unitUnderTest.getAsync().blockingIterable().first()))
	}

	@Test
	fun clear() {
		val data = listOf(1,2,3,4)
		this.unitUnderTest.saveAsync(data).blockingSubscribe()
		assertTrue(data.containsAll(this.unitUnderTest.getAsync().blockingIterable().first()))

		this.unitUnderTest.clear()
		assertTrue(this.unitUnderTest.getAsync().blockingIterable().first().isEmpty())
	}

	@Test
	fun saveNonPrimitive() {
		val testStorage = SimpleListStorage(RuntimeEnvironment.application, TestUser::class.java)

		val data = listOf(TestUser("user_1", 30), TestUser("user_2", 32))
		testStorage.saveAsync(data).blockingSubscribe()

		val retrievedData = testStorage.getAsync().blockingIterable().first()
		data.forEachIndexed { i, testUser ->
			assertEquals(testUser, retrievedData[i])
		}
		assertEquals(data.size, retrievedData.size)
	}

	private data class TestUser(val name: String, val age: Int)
}
