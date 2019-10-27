package com.example.grab.threading

import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.Future

class BusinessExecutor{
    private var mBusinessPoolExecutor: ExecutorService
    private var mResourcePoolExecutor: ExecutorService
    private var mSessionResourcePoolExecutor: ExecutorService
    private val RESOURCE_THREAD_POOL_SIZE = 15

    init {
        mBusinessPoolExecutor = Executors.newSingleThreadExecutor()
        mResourcePoolExecutor = Executors.newFixedThreadPool(RESOURCE_THREAD_POOL_SIZE)
        mSessionResourcePoolExecutor = Executors.newSingleThreadExecutor()
    }

    companion object {
        fun getInstance() : BusinessExecutor {
            return BusinessExecutor()
        }
    }

    @Throws(NullPointerException::class)
    fun executeInBusinessThread(command: Runnable) {
        mBusinessPoolExecutor.execute(command)
    }

    @Throws(NullPointerException::class)
    fun executeInResourceThread(command: Runnable) {
        mResourcePoolExecutor.execute(command)
    }

    @Throws(NullPointerException::class)
    fun submitInResourceThread(command: Runnable): Future<*> {
        return mResourcePoolExecutor.submit(command)
    }

    @Throws(NullPointerException::class)
    fun submitInSessionResourceThread(command: Runnable): Future<*> {
        return mSessionResourcePoolExecutor.submit(command)
    }
}