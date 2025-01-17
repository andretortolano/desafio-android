package com.picpay.desafio.android.feature.home.interactor.user

import com.google.common.truth.Truth.assertThat
import com.picpay.desafio.android.feature.home.interactor.user.GetKnightListUseCase.GetKnightListError.NoInternetError
import com.picpay.desafio.android.feature.home.interactor.user.GetKnightListUseCase.GetKnightListError.ServerError
import com.picpay.desafio.android.feature.home.testing.MockKCoroutinesTest
import com.picpay.desafio.android.shared.domain.EntityResult
import com.picpay.desafio.android.shared.exception.NoInternetException
import com.picpay.desafio.android.shared.exception.UnknowServerException
import io.mockk.coEvery
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

@ExperimentalCoroutinesApi
class GetKnightListUseCaseTest : MockKCoroutinesTest() {

    @MockK
    private lateinit var userGateway: UserGateway

    @InjectMockKs
    private lateinit var useCase: GetKnightListUseCase

    @Test
    fun `useCase SHOULD return Success`() = runBlockingTest {
        // Given
        coEvery { userGateway.getUserList() } returns arrayListOf()
        // When
        val result = useCase()
        // Then
        assertThat(result).isInstanceOf(EntityResult.Success::class.java)
    }

    @Test
    fun `useCase SHOULD return Success WITH entity list from userGateway AND name prefix Sir`() = runBlockingTest {
        // Given
        val entityList = arrayListOf(
            UserEntity(1, "Spinel", "Andre", "url"),
            UserEntity(2, "CAFU", "Pedro", "url"),
        )
        val expectedList = arrayListOf(
            UserEntity(1, "Spinel", "Sir Andre", "url"),
            UserEntity(2, "CAFU", "Sir Pedro", "url"),
        )
        coEvery { userGateway.getUserList() } returns entityList
        // When
        val result = useCase()
        // Then
        (result as EntityResult.Success).run {
            assertThat(value).containsExactlyElementsIn(expectedList)
        }
    }

    @Test
    fun `useCase SHOULD return NoInternetError WHEN userGateway throws NoInternetException`() = runBlockingTest() {
        // Given
        coEvery { userGateway.getUserList() } throws NoInternetException()
        // When
        val result = useCase()
        // Then
        (result as EntityResult.Error).run {
            assertThat(value).isInstanceOf(NoInternetError::class.java)
        }
    }

    @Test
    fun `useCase SHOULD return ServerError WHEN userGateway throws UnknowServerException`() = runBlockingTest() {
        // Given
        coEvery { userGateway.getUserList() } throws UnknowServerException()
        // When
        val result = useCase()
        // Then
        (result as EntityResult.Error).run {
            assertThat(value).isInstanceOf(ServerError::class.java)
        }
    }
}