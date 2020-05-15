package com.lucas.finanaskotlin.ui.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import com.lucas.finanaskotlin.R
import com.lucas.finanaskotlin.model.Tipo
import com.lucas.finanaskotlin.model.Transacao
import com.lucas.finanaskotlin.ui.ResumoView
import com.lucas.finanaskotlin.ui.adapter.ListaTransacoesAdapter
import com.lucas.finanaskotlin.ui.dialog.AdicionaTransacaoDialog
import com.lucas.finanaskotlin.ui.dialog.AlteraTransacaoDialog
import kotlinx.android.synthetic.main.activity_lista_transacoes.*

class ListaTransacoesActivity : AppCompatActivity() {

    private val transacoes: MutableList<Transacao> = mutableListOf()

    private val viewActivity by lazy {
        window.decorView
    }
    private val viewGroupActivity by lazy {
        viewActivity as ViewGroup
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)

        configuraResumo()
        configuraLista()
        configuraFAB()
    }

    override fun onContextItemSelected(item: MenuItem?): Boolean {
        val itemId = item?.itemId
        if (itemId == 1) {
            val adapterMenuInfo = item.menuInfo as AdapterView.AdapterContextMenuInfo
            val posicao = adapterMenuInfo.position
            removeTransacaoPor(posicao)
        }
        return super.onContextItemSelected(item)
    }

    private fun removeTransacaoPor(posicaoTransacao: Int) {
        transacoes.removeAt(posicaoTransacao)
        atualizaTransacoes()
    }


    private fun configuraLista() {
        with(lista_transacoes_listview) {
            adapter = ListaTransacoesAdapter(transacoes, this@ListaTransacoesActivity)
            setOnItemClickListener { _, _, position, _ ->
                val transacao = transacoes[position]
                chamaDialogAlteracao(transacao, position)
            }
            setOnCreateContextMenuListener { menu, _, _  ->
                menu.add(Menu.NONE, 1, Menu.NONE, "Remover")
            }
        }

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
        AdicionaTransacaoDialog(viewGroupActivity, this)
            .showDialog(tipo) { transacaoCriada ->
                adiciona(transacaoCriada)
                lista_transacoes_adiciona_menu.close(true)
            }

    }


    private fun adiciona(transacao: Transacao) {
        transacoes.add(transacao)
        atualizaTransacoes()
    }

    private fun atualizaTransacoes() {
        configuraLista()
        configuraResumo()
    }

    private fun configuraResumo() {
        val resumoView = ResumoView(this, viewGroupActivity, transacoes)
        resumoView.atualizaActivity()

    }

    private fun chamaDialogAlteracao(
        transacao: Transacao,
        position: Int
    ) {
        AlteraTransacaoDialog(viewGroupActivity, this)
            .showDialog(transacao) { transacaoAlterada ->
                altera(transacaoAlterada, position)
            }
    }

    private fun altera(transacao: Transacao, position: Int) {
        transacoes[position] = transacao
        atualizaTransacoes()
    }

}
