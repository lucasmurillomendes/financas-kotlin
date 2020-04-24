package com.lucas.finanaskotlin.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lucas.finanaskotlin.R
import com.lucas.finanaskotlin.model.Tipo
import com.lucas.finanaskotlin.model.Transacao
import com.lucas.finanaskotlin.ui.ResumoView
import com.lucas.finanaskotlin.ui.adapter.ListaTransacoesAdapter
import kotlinx.android.synthetic.main.activity_lista_transacoes.*
import java.math.BigDecimal

class ListaTransacoesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)

        val transacoes = transactionsExample()

        configuraResumo(transacoes)

        listConfiguration(transacoes)
    }

    private fun configuraResumo(transacoes: List<Transacao>) {
        val view = window.decorView
        val resumoView = ResumoView(view, transacoes)
        resumoView.addReceita()
        resumoView.addDespesa()

    }

    private fun listConfiguration(transacoes: List<Transacao>) {
        lista_transacoes_listview.adapter = ListaTransacoesAdapter(
            transacoes,
            this@ListaTransacoesActivity
        )
    }

    private fun transactionsExample(): List<Transacao> {
        return listOf(
            Transacao(BigDecimal(20.5), "Comida de final de semana", Tipo.DESPESA),
            Transacao(BigDecimal(27.5), "Comida hoje", Tipo.DESPESA),
            Transacao(BigDecimal(28.5), "Economia", Tipo.RECEITA),
            Transacao(BigDecimal(38.5), "Economia", Tipo.RECEITA)
        )
    }
}
