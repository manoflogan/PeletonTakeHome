package com.krishnanand.peleton.dagger

import android.app.Application
import com.krishnanand.peleton.PeletonApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        ApiModule::class
    ]
)
@Singleton
interface PeletonComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(app: Application): Builder

        fun build(): PeletonComponent
    }

    fun inject(peletonApp: PeletonApplication)
}