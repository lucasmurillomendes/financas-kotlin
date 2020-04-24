package com.lucas.finanaskotlin.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.core.content.ContextCompat
import com.lucas.finanaskotlin.R
import com.lucas.finanaskotlin.extensions.formatoBrasileiro
import com.lucas.finanaskotlin.extensions.limitaApartirDe
import com.lucas.finanaskotlin.model.Tipo
import com.lucas.finanaskotlin.model.Transacao
import kotlinx.android.synthetic.main.transacao_item.view.*

class ListaTransacoesAdapter(
    private val transacoes: List<Transacao>,
    private val context: Context
) : BaseAdapter() {

    override fun getView(position: Int, view: View?, parent: ViewGroup?): View {
        val createView =
            LayoutInflater.from(context).inflate(R.layout.transacao_item, parent, false)
        val transacao = transacoes[position]

        setupColor(transacao, createView)

        setupIcon(transacao, createView)

        setupFields(createView, transacao)

        return createView
    }


    override fun getItem(position: Int): Transacao {
        return transacoes[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return transacoes.size
    }

    private fun setupColor(
        transacao: Transacao,
        createView: View
    ) {
        when (transacao.tipo) {
            Tipo.RECEITA -> createView.transacao_valor.setTextColor(
                ContextCompat.getColor(
                    context,
                    R.color.receita
                )
            )
            Tipo.DESPESA -> createView.transacao_valor.setTextColor(
                ContextCompat.getColor(
                    context,
                    R.color.despesa
                )
            )
        }


    }

    private fun setupIcon(
        transacao: Transacao,
        createView: View
    ) {
        when (transacao.tipo) {
            Tipo.RECEITA -> createView.transacao_icone.setBackgroundResource(R.drawable.icone_transacao_item_receita)
            Tipo.DESPESA -> createView.transacao_icone.setBackgroundResource(R.drawable.icone_transacao_item_despesa)
        }
    }

    private fun setupFields(
        createView: View,
        transacao: Transacao
    ) {
        createView.transacao_valor.text = transacao.valor.formatoBrasileiro()
        createView.transacao_categoria.text = transacao.categoria.limitaApartirDe(14)
        createView.transacao_data.text = transacao.data.formatoBrasileiro()
    }

}