package com.bw.a2307a1.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bw.a2307a1.mvvm.base.BaseViewModel
import com.bw.a2307a1.mvvm.model.User
import com.bw.a2307a1.mvvm.repository.UserRepository
import com.bw.a2307a1.mvvm.utils.Resource

/**
 * 用户相关 ViewModel（示例）
 */
class UserViewModel : BaseViewModel() {

    private val repository = UserRepository()

    private val _userState = MutableLiveData<Resource<User>>()
    val userState: LiveData<Resource<User>> = _userState

    fun loadUserInfo(userId: Long = 0L) {
        launchOnViewModelScope {
            _userState.value = Resource.Loading()

            val result = repository.getUserInfo(userId)

            handleResult(
                result = result,
                onSuccess = { user ->
                    _userState.value = Resource.Success(user)
                },
                onError = { throwable ->
                    _userState.value = Resource.Error(
                        throwable.message ?: "加载失败",
                        throwable
                    )
                }
            )
        }
    }
}
