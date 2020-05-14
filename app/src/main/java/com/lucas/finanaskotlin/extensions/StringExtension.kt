package com.lucas.finanaskotlin.extensions

import java.text.SimpleDateFormat
import java.util.*

fun String.limitaApartirDe(caracteres: Int): String {
    if (this.length > caracteres) {
        return "${this.substring(0, 14)}..."
    }
    return this
}

fun String.converteParaCalendar(): Calendar {
    val formatoBrasileiro = SimpleDateFormat("dd/MM/yyyy")
    val dataConvertida: Date = formatoBrasileiro.parse(this)
    val data = Calendar.getInstance()
    data.time = dataConvertida
    return data
}
