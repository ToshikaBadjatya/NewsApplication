package com.example.newsapplication.domain.usecases

import com.example.newsapplication.data.manager.UserEntryImpl
import com.example.newsapplication.domain.UserEntryManager
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

data class EntryUseCases(val read:ReadEntryUseCases,val write:WriteEntryUseCases)
class ReadEntryUseCases @Inject constructor(val entryManager: UserEntryImpl){
    fun getUserEntry(): Flow<Boolean>{
        return entryManager.getUserEntry()
    }


}
class WriteEntryUseCases @Inject constructor(val entryManager: UserEntryImpl) {
    suspend fun  setUserEntry(){
        entryManager.setUserEntry()
    }
}