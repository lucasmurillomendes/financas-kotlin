package com.lucas.finanaskotlin.ui.dialog

import android.content.Context
import android.view.ViewGroup
import com.lucas.finanaskotlin.R
import com.lucas.finanaskotlin.extensions.formatoBrasileiro
import com.lucas.finanaskotlin.model.Tipo
import com.lucas.finanaskotlin.model.Transacao

class AlteraTransacaoDialog(
    viewGroup: ViewGroup,
    private val context: Context
) : FormularioTransacaoDialog(context, viewGroup) {

    fun showDialog(transacao: Transacao, delegate: (transacao: Transacao) -> Unit) {

        val tipo = transacao.tipo
        super.showDialog(tipo, delegate)
        inicializaCampos(transacao)
    }

    private fun inicializaCampos(transacao: Transacao) {
        val tipo = transacao.tipo
        inicializaCampoValor(transacao)
        inicializaCampoData(transacao)
        inicializaCampoCategoria(tipo, transacao)
    }

    private fun inicializaCampoCategoria(tipo: Tipo,transacao: Transacao) {
        val categoriasRetornadas = context.resources.getStringArray(super.categoriaPor(tipo))
        val posicaocategoria = categoriasRetornadas.indexOf(transacao.categoria)
        campoCategoria.setSelection(posicaocategoria, true)
    }

    private fun inicializaCampoData(transacao: Transacao) {
        campoData.setText(transacao.data.formatoBrasileiro())
    }

    private fun inicializaCampoValor(transacao: Transacao) {
        campoValor.setText(transacao.valor.toString())
    }

    override val tituloBotaoPositivo: String
        get() = "Alterar"

    override fun tituloPor(tipo: Tipo): Int {
        return if (tipo == Tipo.RECEITA) {
            R.string.altera_receita
        } else {
            R.string.altera_despesa
        }
    }

}
