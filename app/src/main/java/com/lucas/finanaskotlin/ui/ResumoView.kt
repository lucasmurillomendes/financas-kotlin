package com.lucas.finanaskotlin.ui

import android.content.Context
import android.view.View
import androidx.core.content.ContextCompat
import com.lucas.finanaskotlin.R
import com.lucas.finanaskotlin.extensions.formatoBrasileiro
import com.lucas.finanaskotlin.model.Resumo
import com.lucas.finanaskotlin.model.Transacao
import kotlinx.android.synthetic.main.resumo_card.view.*
import java.math.BigDecimal

class ResumoView(
    private val context: Context,
    private val view: View,
    transacoes: List<Transacao>
) {

    private val resumo: Resumo = Resumo(transacoes)
    private val corReceita = ContextCompat.getColor(context, R.color.receita)
    private val corDespesa = ContextCompat.getColor(context, R.color.despesa)

    private fun addReceita() {
        val totalReceita = resumo.receita
        with(view.resumo_card_receita) {
            setTextColor(corReceita)
            text = totalReceita.formatoBrasileiro()
        }
    }

    private fun addDespesa() {
        val totalDespesa = resumo.despesa
        with(view.resumo_card_despesa) {
            setTextColor(corDespesa)
            text = totalDespesa.formatoBrasileiro()
        }

    }

    private fun addTotal() {
        val total = resumo.total
        val cor = corPor(total)
        with(view.resumo_card_total) {
            setTextColor(cor)
            text = total.formatoBrasileiro()

        }
    }

    fun atualizaActivity() {
        addReceita()
        addDespesa()
        addTotal()
    }

    private fun corPor(valor: BigDecimal): Int {
        return if (valor >= BigDecimal.ZERO) {
            corReceita
        } else {
            corDespesa
        }
    }

}