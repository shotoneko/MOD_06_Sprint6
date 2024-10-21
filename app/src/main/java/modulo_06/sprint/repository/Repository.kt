package modulo_06.sprint.repository

import android.provider.ContactsContract
import android.util.Log
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import modulo_06.sprint.retrofit.ApiDataSource
import modulo_06.sprint.retrofit.ApiPhone
import modulo_06.sprint.retrofit.ApiResponseProducts
import modulo_06.sprint.room.AppDao
import modulo_06.sprint.room.Phone
import retrofit2.Response
import javax.inject.Inject

interface Repository {
    fun getAllPhones(): Flow<List<Phone>>
    fun getApiPhones(): Flow<List<Phone>>
//    suspend fun getPhone(): Phone?
    suspend fun deletePhone(phone: Phone)
}

class RepositoryImp @Inject constructor(
    private val api: ApiDataSource,
    private val db: AppDao
) : Repository {

    override suspend fun deletePhone(phone: Phone) {
        withContext(Dispatchers.IO){
            db.delete(phone)
        }
    }

    //Recupera los fonos de la base de ROOM
    override fun getAllPhones(): Flow<List<Phone>> =db.getAll()

    //Recupera de la api y guarda en la BD de ROOM
    override fun getApiPhones(): Flow<List<Phone>> = flow {

        val phonesInDb = db.getAll().firstOrNull()
        if(phonesInDb.isNullOrEmpty()){
            val response = api.getAllPhonesApi()
            if (response.isSuccessful) {
                val phoneList = response.body()
                val dbPhoneList: List<Phone> = phoneList!!.map { apiPhone ->
                    Phone(
                        id = apiPhone.id,
                        name = apiPhone.name,
                        price = apiPhone.price,
                        image = apiPhone.image,
                        description = apiPhone.description,
                        lastPrice = apiPhone.lastPrice,
                        credit = apiPhone.credit
                    )
                }
                // Save data to database
                withContext(Dispatchers.IO){
                    db.insertAll(dbPhoneList)
                    // Emit data to ViewModel
                }
                emit(dbPhoneList)
            } else {
                // Handle error
            }
        } else{
            emit(phonesInDb)
        }
    }
}