package com.example.clerkieandroidchallenge.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.clerkieandroidchallenge.R
import com.example.clerkieandroidchallenge.model.TextWithImageAdapter
import com.example.clerkieandroidchallenge.model.TextWithImageModel
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.Charset

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textWithImageList: ArrayList<TextWithImageModel> = ArrayList()

        try {
            val obj = JSONObject(getJSONFromAssets()!!)
            val textWithImageArray = obj.getJSONArray("TextWithImage")

            for (i in 0 until textWithImageArray.length()) {

                val content = textWithImageArray.getJSONObject(i)

                val title = content.getJSONObject("title")
                val titleText = title.getString("text")
                val titleFontSize = title.getString("font_size")
                //val titleColor = title.getString("color")

                val subtitle = content.getJSONObject("subtitle")
                val subtitleText = subtitle.getString("text")
                val subtitleFontSize = subtitle.getString("font_size")
                //val subtitleColor = subtitle.getString("color")

                //val image = content.getJSONObject("image")
                //val imageSrc = image.getString("src")

                //val clickAction = content.getString("click_action")


                val textWithImageDetails =
                    TextWithImageModel(titleText ,titleFontSize , subtitleText, subtitleFontSize )

                textWithImageList.add(textWithImageDetails)
            }
        } catch (e: JSONException) {

            e.printStackTrace()
        }

        var recycleView = findViewById<RecyclerView>(R.id.recycleView)
        recycleView.layoutManager = LinearLayoutManager(this)
        val itemAdapter = TextWithImageAdapter(this, textWithImageList)
        recycleView.adapter = itemAdapter

    }


    private fun getJSONFromAssets(): String? {

        var json: String? = null
        val charset: Charset = Charsets.UTF_8
        try {
            val dataJSONFile = assets.open("data.json")
            val size = dataJSONFile.available()
            val buffer = ByteArray(size)
            dataJSONFile.read(buffer)
            dataJSONFile.close()
            json = String(buffer, charset)
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
        return json
    }
}