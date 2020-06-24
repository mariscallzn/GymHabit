package com.andymariscal.shared.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.andymariscal.shared.inf.ItemDelegateAdapter

fun ViewGroup.inflate(@LayoutRes resource: Int, attachToRoot: Boolean = false): View =
    LayoutInflater.from(context).inflate(resource, this, attachToRoot)

fun RecyclerView.getAdapterAsDelegate(): ItemDelegateAdapter? =
    if (adapter is ItemDelegateAdapter) adapter as ItemDelegateAdapter else null
