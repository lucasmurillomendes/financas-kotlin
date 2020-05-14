package com.lucas.finanaskotlin.delegate

import com.lucas.finanaskotlin.model.Transacao

interface TransacaoDelegate {
    fun delegate(transacao: Transacao)
}