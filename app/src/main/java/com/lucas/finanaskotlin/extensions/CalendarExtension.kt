package com.lucas.finanaskotlin.extensions

import java.text.SimpleDateFormat
import java.util.*

/**
 * Extension Function de Calendar para formatar para brasileiro
 */
fun Calendar.formatoBrasileiro(): String{
    val formatoBrasileiro = "dd/MM/yyyy"
    val format = SimpleDateFormat(formatoBrasileiro)
    return format.format(time)
}