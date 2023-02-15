package ru.chuikov.spartacusdice

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class MainViewModel(app:Application): AndroidViewModel(app) {

    var blueCubes:MutableLiveData<Int> = MutableLiveData(3)
    var blackCubes:MutableLiveData<Int> = MutableLiveData(3)
    var redCubes:MutableLiveData<Int> = MutableLiveData(3)

}