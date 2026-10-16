package com.assessment.sereneecommerce

import android.app.Application
import com.assessment.sereneecommerce.di.ServiceLocator

class SereneCommerceApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        ServiceLocator.init(this)
    }
}