package com.aulasandroid.gestaoestado.juros

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class JurosScreenViewModel {

    private val _capitalState = MutableLiveData <String>()
    var capital: LiveData<String> = _capitalState

    private val _taxaState = MutableLiveData<String>()
    var taxa: LiveData<String> = _taxaState

    private val _tempoState = MutableLiveData<String>()
    var tempo: LiveData<String> = _tempoState



    fun onCaptalChanged(novoCapital: String){
       _capitalState.value = novoCapital
    }
}