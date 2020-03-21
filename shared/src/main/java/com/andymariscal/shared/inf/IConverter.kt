package com.andymariscal.shared.inf

interface IConverter<in S, out T> {
    fun convert(source: S): T
    fun convertList(list: List<S>): List<T> =
        list.map { convert(it) }
}