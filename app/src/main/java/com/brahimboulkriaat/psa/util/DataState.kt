package com.brahimboulkriaat.psa.util

sealed class DataState<out R> {
    class Success<out T>(val data: T): DataState<T>()
    class Error(val error: Exception): DataState<Nothing>()
    object Loading : DataState<Nothing>()
    object None : DataState<Nothing>()
}