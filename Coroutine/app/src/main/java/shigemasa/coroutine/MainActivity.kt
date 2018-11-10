package shigemasa.coroutine

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import awaitStringResponse
import com.github.kittinunf.fuel.core.isSuccessful
import com.github.kittinunf.fuel.httpGet
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import java.util.*

class MainActivity : AppCompatActivity() {

    var i = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        button.setOnClickListener {
//            //値が大きくなると処理に時間がかかる
//            textView.text = fib(i++).toString()
//        }
//        button.setOnClickListener {
//            //ここが重要
//           GlobalScope.launch(Dispatchers.Main){
//                fib(i)
//               textView.text =fib(i++).toString()
//           }
//        }
//        button2.setOnClickListener {
//           // textView2.text = "動け! @${Calendar.getInstance().time.toString()}"
//            val keyword ="メガネ";
//            GlobalScope.launch(Dispatchers.Main) {
//             val (_,res,result)=   "https://ci.nii.ac.jp/books/opensearch/search".httpGet(listOf(("q" to keyword))).awaitStringResponse()
//                result.takeIf { res.isSuccessful }.let { textView2.text =it?.get()}
//                Log.d("Cinii",result.get());
//            }
//
//            }
        button2.setOnClickListener {
            GlobalScope.launch(Dispatchers.Main) {

                val request = async  {
                    val list = mutableListOf<String>();
                    repeat(3) {
                        val (_, _, result) = "https://us-central1-ikamanager-cbe31.cloudfunctions.net/generateunitID".httpGet().awaitStringResponse()
                        list.add(result.get())
                    }
                    list
                }
                val result =request.await()
                result.forEach { Log.d("ID",it)
                    textView2.text = textView2.text.toString()+"\n"+it}



            }
        }
    }


//    button2.setOnClickListener {
//        // textView2.text = "動け! @${Calendar.getInstance().time.toString()}"
//    }
//}

//    fun fib(x: Int): Int =
//            if (x <= 1) x else fib(x - 1) + fib(x - 2)
//    suspend fun fib(x: Int): Int = withContext(Dispatchers.Default) {
//        fibBlocking(x)
//    }
//
//    fun fibBlocking(x: Int): Int =
//            if (x <= 1) x else fibBlocking(x - 1) + fibBlocking(x - 2)
//
//

}

