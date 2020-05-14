package com.lucas.finanaskotlin.ui.activity

import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.lucas.finanaskotlin.R
import com.lucas.finanaskotlin.delegate.TransacaoDelegate
import com.lucas.finanaskotlin.model.Tipo
import com.lucas.finanaskotlin.model.Transacao
import com.lucas.finanaskotlin.ui.ResumoView
import com.lucas.finanaskotlin.ui.adapter.ListaTransacoesAdapter
import com.lucas.finanaskotlin.ui.dialog.AdicionaTransacaoDialog
import kotlinx.android.synthetic.main.activity_lista_transacoes.*

class ListaTransacoesActivity : AppCompatActivity() {

    private val transacoes: MutableList<Transacao> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)

        configuraResumo()
        configuraLista()
        configuraFAB()

    }

    private fun configuraFAB() {
        lista_transacoes_adiciona_receita
            .setOnClickListener {
                chamaDialogAdicao(Tipo.RECEITA)
            }

        lista_transacoes_adiciona_despesa
            .setOnClickListener {
                chamaDialogAdicao(Tipo.DESPESA)
            }
    }

    private fun chamaDialogAdicao(tipo: Tipo) {
        AdicionaTransacaoDialog(window.decorView as ViewGroup, this)
            .showDialog(tipo, object : TransacaoDelegate {
                override fun delegate(transacao: Transacao) {
                    atualizaTransacoes(transacao)
                    lista_transacoes_adiciona_menu.close(true)
                }

            })
    }

    private fun atualizaTransacoes(transacao: Transacao) {
        transacoes.add(transacao)
        configuraLista()
        configuraResumo()
    }

    private fun configuraResumo() {
        val view = window.decorView
        val resumoView = ResumoView(this, view, transacoes)
        resumoView.atualizaActivity()

    }

    private fun configuraLista() {
        lista_transacoes_listview.adapter = ListaTransacoesAdapter(
            transacoes,
            this@ListaTransacoesActivity
        )

    }

}
