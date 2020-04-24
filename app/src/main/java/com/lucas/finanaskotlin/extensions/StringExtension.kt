package com.lucas.finanaskotlin.extensions

fun String.limitaApartirDe(caracteres: Int):String {
    if (this.length > caracteres) {
        return "${this.substring(0, 14)}..."
    }
    return this
}