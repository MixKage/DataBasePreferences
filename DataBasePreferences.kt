package com.example.databaseonpreferences

import android.content.Context
import android.content.SharedPreferences
import org.json.JSONObject

class DataBasePreferences(var context: Context) {
    private var prefs: SharedPreferences
    private val prefsSetting = context.getSharedPreferences("databaseInfo", Context.MODE_PRIVATE)
    private var editor: SharedPreferences.Editor
    private val editorSettings = prefsSetting.edit()
    private var indexNow = 0

    init {
        prefs = context.getSharedPreferences(getSizeInt().toString(), Context.MODE_PRIVATE)
        editor = prefs.edit()
    }

    fun getSizeInt(): Int {
        return prefsSetting.getInt("size", 0)
    }

    private fun sizeUp() {
        editorSettings.putInt("size", getSizeInt() + 1).apply()
    }

    private fun sizeDown() {
        editorSettings.putInt("size", getSizeInt() - 1).apply()
    }

    private fun updatePrefs(index: Int) {
        if (indexNow != index) {
            indexNow = index
            prefs = context.getSharedPreferences(index.toString(), Context.MODE_PRIVATE)
            editor = prefs.edit()
        }
    }

    fun addJSONObject(_input: JSONObject) {
        sizeUp()
        updatePrefs(getSizeInt())
        setId(_input.getString("ID"))
        setNumCode(_input.getString("NumCode"))
        setCharCode(_input.getString("CharCode"))
        setNominal(_input.getString("Nominal"))
        setName(_input.getString("Name"))
        setValue(_input.getString("Value"))
        setPrevious(_input.getString("Previous"))
    }

    fun replaceJSONObjectByIdCard(_input: JSONObject, _id: Int) {
        updatePrefs(_id)
        setId(_input.getString("ID"))
        setNumCode(_input.getString("NumCode"))
        setCharCode(_input.getString("CharCode"))
        setNominal(_input.getString("Nominal"))
        setName(_input.getString("Name"))
        setValue(_input.getString("Value"))
        setPrevious(_input.getString("Previous"))
    }

    fun setId(_input: String, _id: Int? = -1) {
        _id?.let {
            if (_id != -1)
                updatePrefs(_id)
            editor.putString("ID", _input).apply()
        }
    }

    fun setNumCode(_input: String, _id: Int? = -1) {
        _id?.let {
            if (_id != -1)
                updatePrefs(_id)
            editor.putString("NumCode", _input).apply()
        }
    }

    fun setCharCode(_input: String, _id: Int? = -1) {
        _id?.let {
            if (_id != -1)
                updatePrefs(_id)
            editor.putString("CharCode", _input).apply()
        }
    }

    fun setNominal(_input: String, _id: Int? = -1) {
        _id?.let {
            if (_id != -1)
                updatePrefs(_id)
            editor.putString("Nominal", _input).apply()
        }
    }

    fun setName(_input: String, _id: Int? = -1) {
        _id?.let {
            if (_id != -1)
                updatePrefs(_id)
            editor.putString("Name", _input).apply()
        }
    }

    fun setValue(_input: String, _id: Int? = -1) {
        _id?.let {
            if (_id != -1)
                updatePrefs(_id)
            editor.putString("Value", _input).apply()
        }
    }

    fun setPrevious(_input: String, _id: Int? = -1) {
        _id?.let {
            if (_id != -1)
                updatePrefs(_id)
            editor.putString("Previous", _input).apply()
        }
    }

    fun getId(_id: Int? = -1): String? {
        _id?.let {
            if (_id != -1)
                updatePrefs(_id)
            return prefs.getString("ID", "").toString()
        }
        return null
    }

    fun getNumCode(_id: Int? = null): String? {
        _id?.let {
            if (_id != -1)
                updatePrefs(_id)
            return prefs.getString("NumCode", "").toString()
        }
        return null
    }

    fun getCharCode(_id: Int? = null): String? {
        _id?.let {
            if (_id != -1)
                updatePrefs(_id)
            return prefs.getString("CharCode", "").toString()
        }
        return null
    }

    fun getNominal(_id: Int? = null): String? {
        _id?.let {
            if (_id != -1)
                updatePrefs(_id)
            return prefs.getString("Nominal", "").toString()
        }
        return null
    }

    fun getName(_id: Int? = null): String? {
        _id?.let {
            if (_id != -1)
                updatePrefs(_id)
            return prefs.getString("Name", "").toString()
        }
        return null
    }

    fun getValue(_id: Int? = null): String? {
        _id?.let {
            if (_id != -1)
                updatePrefs(_id)
            return prefs.getString("Value", "").toString()
        }
        return null
    }

    fun getPrevious(_id: Int? = null): String? {
        _id?.let {
            if (_id != -1)
                updatePrefs(_id)
            return prefs.getString("Previous", "").toString()
        }
        return null
    }

    fun searchIdCardById(_input: String): Int? {
        for (index in 1..getSizeInt()) {
            updatePrefs(index)
            if (_input == getId()) {
                return index
            }
        }
        return null
    }

    fun searchIdCardByNumCode(_input: String): Int? {
        for (index in 1..getSizeInt()) {
            updatePrefs(index)
            if (_input == getNumCode()) {
                return index
            }
        }
        return null
    }

    fun searchIdCardByCharCode(_input: String): Int? {
        for (index in 1..getSizeInt()) {
            updatePrefs(index)
            if (_input == getCharCode()) {
                return index
            }
        }
        return null
    }

    fun searchIdCardByNominal(_input: String): Int? {
        for (index in 1..getSizeInt()) {
            updatePrefs(index)
            if (_input == getNominal()) {
                return index
            }
        }
        return null
    }

    fun searchIdCardByName(_input: String): Int? {
        for (index in 1..getSizeInt()) {
            updatePrefs(index)
            if (_input == getName()) {
                return index
            }
        }
        return null
    }

    fun searchIdCardByValue(_input: String): Int? {
        for (index in 1..getSizeInt()) {
            updatePrefs(index)
            if (_input == getValue()) {
                return index
            }
        }
        return null
    }

    fun searchIdCardByPrevious(_input: String): Int? {
        for (index in 1..getSizeInt()) {
            updatePrefs(index)
            if (_input == getPrevious()) {
                return index
            }
        }
        return null
    }

    fun clearAllDB() {
        var preferences: SharedPreferences
        val max = getSizeInt()
        for (index in 0..max) {
            preferences = context.getSharedPreferences(index.toString(), Context.MODE_PRIVATE)
            preferences.edit().clear().apply()
        }

        val prefs = context.getSharedPreferences("databaseInfo", Context.MODE_PRIVATE)
        prefs.edit().clear().apply()
        getSizeInt()
    }

    fun readDBEndToStart(): String {
        var strsum = ""
        for (index in 1..getSizeInt()) {
            updatePrefs(index)
            strsum += "\n" + getId() + "," + getNumCode() + "," + getCharCode() + "," + getNominal() + "," + getName() + "," + getValue() + "," + getPrevious() + ";$index"
        }
        return strsum
    }
}