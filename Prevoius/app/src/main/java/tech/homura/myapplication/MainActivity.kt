package tech.homura.myapplication
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.github.kittinunf.fuel.gson.responseObject
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.result.Result
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener {
                                    text.text ="何かを書く"
                                    say(editText.text.trimEnd().toString())}

    }
    //https://api.a3rt.recruit-tech.co.jp/talk/v1/smalltalk&apikey=DZZfxAPFVACsqJ9hDjvE6yFvEGLjRbIB&query=こんにちは
    fun say(message:String){
        " https://api.a3rt.recruit-tech.co.jp/talk/v1/smalltalk"
                .httpPost(listOf("apikey" to "DZZfxAPFVACsqJ9hDjvE6yFvEGLjRbIB",
                        "query" to message)).responseObject<TalkApi> { request, response, result ->
                    when(result){
                        is Result.Failure->{
                            println("取得失敗")
                            println("Response:\n"+response.responseMessage)
                        }
                        is Result.Success ->{
                            Log.d("response",response.responseMessage)
                            Log.d("response","responseData:"+result.value)

                            Log.d("Reply",result.value.results[0].reply)
                            text.text =result.value.results[0].reply;

                        }
                    }
                }
    }
    data class TalkApi(val status:String,val message:String,val results:Array<Reply>)
    data class Reply(val perplexity:Double,val reply:String)

    /* 3
         override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
         button.setOnClickListener {
                                    text.text ="何かを書く"
    }
        }
     */
    /* 2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("Output", "OnCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.d("Output", "OnStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("Output", "OnResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("Output", "OnPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("Output", "OnStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Output", "OnDestroy")
    }
    */

    /* 1 出力
      override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("Output", "OnCreate")
    }
       println("Hello Logcat")//改行(line-next?)しないと表示されない
            Log.d("Output", "Hello Logcat")//こっちでも行ける
     */

}
