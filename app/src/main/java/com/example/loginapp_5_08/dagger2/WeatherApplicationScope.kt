package com.example.loginapp_5_08.dagger2

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import javax.inject.Scope

@Scope
@Retention(RetentionPolicy.CLASS)
annotation class WeatherApplicationScope {
}