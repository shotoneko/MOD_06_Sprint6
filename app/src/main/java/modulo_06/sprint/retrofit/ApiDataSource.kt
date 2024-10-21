package modulo_06.sprint.retrofit

import retrofit2.Response
import retrofit2.http.GET

interface ApiDataSource {

    companion object {
        const val ENDPOINT_PRODUCT = "Himuravidal/FakeAPIdata/products/"
        const val ENDPOINT_DETAIL = "Himuravidal/FakeAPIdata/details/"
    }

    @GET(ENDPOINT_DETAIL)
    suspend fun getAllPhonesApi(): Response<ApiResponseProducts>

    @GET(ENDPOINT_PRODUCT)
    suspend fun getNewPhone(): Response<ApiResponseProducts>




}