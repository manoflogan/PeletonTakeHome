package com.krishnanand.peleton.viewmodels

import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass

/**
 * An instance of this class binds a [ViewModel] class to its provider.
 *
 * The sample usage in a dagger module is as follows
 *
 * ```
 * @[ViewModelKey(MyViewViewModel::class) IntoMap Binds]
 * fun bindMyViewModel(myViewModel: MyViewModel): ViewModel
 * ```
 */
@[
    MapKey
    Target(
        AnnotationTarget.FUNCTION,
        AnnotationTarget.PROPERTY_SETTER
    )
    Retention(AnnotationRetention.RUNTIME)
]
annotation class ViewModelKey(
    val value: KClass<out ViewModel>
)