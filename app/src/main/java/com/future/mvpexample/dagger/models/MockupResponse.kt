package com.future.mvpexample.dagger.models

import com.example.*

fun getFetchBookRespose(): BookRes = BookRes(items = arrayListOf(Item("1","1","1","1", VolumeInfo("blibli.com"), SaleInfo(), AccessInfo(),
    SearchInfo()
)))