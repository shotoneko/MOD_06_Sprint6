package modulo_06.sprint.views

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import modulo_06.sprint.repository.Repository
import modulo_06.sprint.room.Phone
import javax.inject.Inject


@HiltViewModel
class HomeViewVM @Inject constructor(

    private val repo: Repository

) : ViewModel() {

    private val _phoneList = MutableStateFlow<List<Phone>>(emptyList())
    val phoneList: StateFlow<List<Phone>> = _phoneList.asStateFlow()

    init {
        viewModelScope.launch {
            repo.getApiPhones().collect { phones ->
                _phoneList.value = phones
            }
        }
    }

    private val _isLoading: MutableStateFlow<Boolean> by lazy {
        MutableStateFlow<Boolean>(false)
    }
    val isLoading: Flow<Boolean> get() = _isLoading

    val phones: Flow<List<Phone>> by lazy {
        repo.getAllPhones()
    }

    fun deletePhone(phone: Phone) {
        viewModelScope.launch {
            repo.deletePhone(phone)
        }
    }
}