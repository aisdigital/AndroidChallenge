package br.com.aisdigital.androidchallenge.helpers

enum class Status {
    Success,
    Error,
    Loading
}

data class Resources<out T>(val status: Status, val data: T?, val message: String?) {

    companion object {

        fun <T> loading(data: T): Resources<T> {
            return Resources(
                status = Status.Loading,
                data = data,
                message = null
            )
        }

        fun <T> success(data: T): Resources<T> {
            return Resources(
                status = Status.Success,
                data = data,
                message = null
            )
        }

        fun <T> error(data: T, message: String?): Resources<T> {
            return Resources(
                status = Status.Error,
                data = data,
                message = message
            )
        }
    }
}