package com.examble.task1.ui

sealed class Status
class SuccessRequest(): Status()
class LoadingRequest: Status()
class FailRequest(): Status()


