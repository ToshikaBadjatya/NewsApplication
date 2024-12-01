package com.example.newsapplication.domain.usecases

import com.example.newsapplication.domain.LocalUserManager
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

data class EntryUseCases(val read:ReadEntryUseCases,val write:WriteEntryUseCases)
class ReadEntryUseCases @Inject constructor(private val entryManager: LocalUserManager){
    fun getUserEntry(): Flow<Boolean>{
        return entryManager.getUserEntry()
    }


}
class WriteEntryUseCases @Inject constructor(private val entryManager: LocalUserManager) {
    suspend fun  setUserEntry(){
        entryManager.setUserEntry()
    }
}