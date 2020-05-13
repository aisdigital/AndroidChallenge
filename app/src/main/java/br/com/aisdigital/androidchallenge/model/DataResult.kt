package br.com.aisdigital.androidchallenge.model

import br.com.aisdigital.androidchallenge.internal.RequestStatus

class DataResult<T>(
    val status: RequestStatus,
    val data: T? = null,
    val errorMessage: String? = null
)
