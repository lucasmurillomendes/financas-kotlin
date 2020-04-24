package com.lucas.finanaskotlin.ui

import android.view.View
import com.lucas.finanaskotlin.extensions.formatoBrasileiro
import com.lucas.finanaskotlin.model.Resumo
import com.lucas.finanaskotlin.model.Tipo
import com.lucas.finanaskotlin.model.Transacao
import kotlinx.android.synthetic.main.resumo_card.view.*
import java.math.BigDecimal

class ResumoView(
    private val view: View,
    private var transacoes: List<Transacao>
) {

    fun addReceita() {
        var totalReceita = Resumo().receita(transacoes)
        view.resumo_card_receita.text = totalReceita.formatoBrasileiro()
    }

    fun addDespesa() {
        var totalDespesa = Resumo().despesa(transacoes)
        view.resumo_card_despesa.text = totalDespesa.formatoBrasileiro()
    }

    fun addTotal() {

    }

}