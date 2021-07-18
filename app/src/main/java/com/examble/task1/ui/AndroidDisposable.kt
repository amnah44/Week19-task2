package com.examble.task1.ui

import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.subjects.ReplaySubject

fun Disposable.add(compositeDisposable: CompositeDisposable) = compositeDisposable.add(this)

