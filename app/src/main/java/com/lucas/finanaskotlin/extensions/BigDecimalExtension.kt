package com.lucas.finanaskotlin.extensions

import java.math.BigDecimal
import java.text.DecimalFormat
import java.util.Locale

fun BigDecimal.formatoBrasileiro(): String {
    val formatoBrasileiro = DecimalFormat
        .getCurrencyInstance(Locale("pt", "br"))
    return formatoBrasileiro
        .format(this)
        .replace("R$", "R$ ")
        .replace("-R$", "R$ -")
}