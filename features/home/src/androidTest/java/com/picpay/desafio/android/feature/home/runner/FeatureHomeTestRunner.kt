package com.picpay.desafio.android.feature.home.runner

import android.app.Application
import android.content.Context
import com.karumi.shot.ShotTestRunner
import com.picpay.desafio.android.feature.home.FeatureHomeApplication

class FeatureHomeTestRunner : ShotTestRunner() {

    override fun newApplication(cl: ClassLoader?, className: String?, context: Context?): Application {
        return super.newApplication(cl, FeatureHomeApplication::class.java.name, context)
    }
}