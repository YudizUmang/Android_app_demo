import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import com.example.android_app_demo.mvvm.model.data.Product
import com.example.android_app_demo.mvvm.model.local.ProductDB
import com.example.android_app_demo.mvvm.model.remote.ProductApi
import kotlinx.coroutines.*

class DataRepository(
    private val apiService: ProductApi,
    private val db: ProductDB,
) {

    suspend fun displayProduct(
        context: Context
    ): MutableList<Product> {
        return withContext(Dispatchers.IO) {
            val productList: MutableList<Product> = mutableListOf()

            if (checkInternet(context)) {
                Log.d("Internet", "True")
                val response = apiService.getProduct()

                if (response.isSuccessful) {
                    productList.addAll(response.body() ?: emptyList())
                    Log.d("OnResponse", productList.toString())


                    productList.forEach { product ->
                        db.productDao().insert(product)
                    }
                } else {
                    Log.d("OnFailure", "Error: ${response.errorBody()}")
                  
                    productList.addAll(db.productDao().getAll())
                }

            } else {
                Log.d("Internet", "False")
                productList.addAll(db.productDao().getAll())
            }

            return@withContext productList
        }
    }

    private fun checkInternet(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val network = connectivityManager.activeNetwork ?: return false
        val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

        return when {
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            else -> false
        }
    }
}
