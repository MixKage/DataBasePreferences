package com.example.databaseonpreferences

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    private lateinit var db: DataBasePreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = DataBasePreferences(this)
        createDB()
        val idTable = db.searchIdCardById("R01035")
        readInfoDB(idTable)
        editInfoDB(idTable)
        readInfoDB(idTable)
    }

    private fun createDB() {
        val jsonRoot = JSONObject(resources.openRawResource(R.raw.daily_json)
            .bufferedReader().use { it.readText() })
        val currency = jsonRoot.getJSONObject("Valute")
        val keys = currency.keys()
        for (index in 0 until currency.length())
            db.addJSONObject(currency.getJSONObject(keys.next()))
    }

    private fun editInfoDB(_id: Int?) {
        _id?.let {
            db.setCharCode("LP", _id)
            db.setName("@lonely_programmer")//Заметьте, я могу не указывать id явно, т.к. работаю с одной карточкой
            db.setNumCode("1234", _id)
        }
    }

    private fun readInfoDB(_id: Int?) {
        _id?.let {
            Log.d("checkDBCHARCODE: ", db.getCharCode(_id).toString())
            Log.d("checkDBNAME: ",db.getName(_id).toString())
            Log.d("checkDBNUMCODE: ",db.getNumCode(_id).toString())
        }
    }
}