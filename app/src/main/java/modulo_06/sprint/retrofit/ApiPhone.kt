package modulo_06.sprint.retrofit


data class ApiPhone(
    val credit: Boolean,
    val description: String,
    val id: Int,
    val image: String,
    val lastPrice: Int,
    val name: String,
    val price: Int
)