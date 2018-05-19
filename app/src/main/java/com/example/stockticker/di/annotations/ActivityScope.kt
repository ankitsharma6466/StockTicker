package com.example.stockticker.di.annotations

import javax.inject.Scope

/**
 * Defines Activity level scope, any field or method annotated
 * with this will have only a single instance for particular activity lifecycle
 *
 * Created by ankitsharma on 19/05/18.
 */
@Scope
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
annotation class ActivityScope