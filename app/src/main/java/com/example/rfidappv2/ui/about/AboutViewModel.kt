package com.example.rfidappv2.ui.about

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class AboutViewModel : ViewModel() {

    public var _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    var text: LiveData<String> = _text

    fun insert() = viewModelScope.launch {
        _text.apply {
            value="Fuck off, This Makes sense now you absolute BOT"
        }
    }
}

// In Simple Terms the worflow is
// In our main acctivity instatiate this class
// Then we can call functions in this class which will update the fragment
// Just like a standard Model View Controller Design Pattern
// This is the model, THe aboutFragment is the view and the activity is the controller

