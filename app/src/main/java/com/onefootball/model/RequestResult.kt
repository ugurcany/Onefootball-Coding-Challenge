package com.onefootball.model

data class RequestResult<D>(
    val uiState: UIState = UIState.DEFAULT,
    val data: D? = null,
    val error: Throwable? = null
) {

    fun isLoading(): Boolean {
        return uiState == UIState.LOADING
    }

    fun isSuccessful(): Boolean {
        return uiState == UIState.SUCCESS
    }

    fun isError(): Boolean {
        return uiState == UIState.ERROR
    }


    companion object {
        fun <D> default(): RequestResult<D> {
            return RequestResult()
        }

        fun <D> loading(): RequestResult<D> {
            return RequestResult(UIState.LOADING, null, null)
        }

        fun <D> success(data: D): RequestResult<D> {
            return RequestResult(UIState.SUCCESS, data, null)
        }

        fun <D> error(error: Throwable): RequestResult<D> {
            return RequestResult(
                UIState.ERROR, null, error
            )
        }
    }
}

enum class UIState {
    DEFAULT,
    LOADING,
    SUCCESS,
    ERROR
}