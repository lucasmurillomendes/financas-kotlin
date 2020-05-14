package com.lucas.finanaskotlin.ui.dialog

import android.app.DatePickerDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.lucas.finanaskotlin.R
import com.lucas.finanaskotlin.delegate.TransacaoDelegate
import com.lucas.finanaskotlin.extensions.converteParaCalendar
import com.lucas.finanaskotlin.extensions.formatoBrasileiro
import com.lucas.finanaskotlin.model.Tipo
import com.lucas.finanaskotlin.model.Transacao
import kotlinx.android.synthetic.main.form_transacao.view.*
import java.math.BigDecimal
import java.util.*

class AdicionaTransacaoDialog(
    private val viewGroup: ViewGroup,
    private val context: Context
) {
    private val viewCriada = criaLayout()
    private val campoValor = viewCriada.form_transacao_valor
    private val campoData = viewCriada.form_transacao_data
    private val campoCategoria = viewCriada.form_transacao_categoria

    fun showDialog(tipo: Tipo, transacaoDelegate: TransacaoDelegate) {

        configuraCampoData()
        configuraCampoCategoria(tipo)
        configuraFormulario(transacaoDelegate, tipo)
    }

    private fun configuraFormulario(transacaoDelegate: TransacaoDelegate, tipo: Tipo) {

        val titulo = tituloPor(tipo)

        AlertDialog.Builder(context)
            .setTitle(titulo)
            .setView(viewCriada)
            .setPositiveButton(
                "Adicionar"
            ) { _, _ ->
                val valorEmtexto = campoValor.text.toString()
                val dataEmTexto = campoData.text.toString()
                val categoriaEmTexto = campoCategoria.selectedItem.toString()

                var valor = converteCampoValor(valorEmtexto)

                val data = dataEmTexto.converteParaCalendar()

                val transacaoCriada = Transacao(
                    tipo = tipo,
                    valor = valor,
                    data = data,
                    categoria = categoriaEmTexto
                )

                transacaoDelegate.delegate(transacaoCriada)
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }

    private fun converteCampoValor(valorEmtexto: String): BigDecimal {
        return try {
            BigDecimal(valorEmtexto)
        } catch (exception: NumberFormatException) {
            Toast.makeText(
                context, "Falha na conversão de valor",
                Toast.LENGTH_LONG
            ).show()
            BigDecimal.ZERO
        }
    }

    private fun configuraCampoCategoria(tipo: Tipo) {

        val categorias = categoriaPor(tipo)

        val adapter = ArrayAdapter
            .createFromResource(
                context,
                categorias,
                android.R.layout.simple_spinner_dropdown_item
            )
        campoCategoria.adapter = adapter
    }

    private fun configuraCampoData() {
        val dataAtual = Calendar.getInstance()

        val ano = dataAtual.get(Calendar.YEAR)
        val mes = dataAtual.get(Calendar.MONTH)
        val dia = dataAtual.get(Calendar.DAY_OF_MONTH)

        campoData.setText(dataAtual.formatoBrasileiro())

        campoData.setOnClickListener {
                DatePickerDialog(
                    context,
                    DatePickerDialog.OnDateSetListener { _, ano, mes, dia ->
                        val dataSelecionada = Calendar.getInstance()
                        dataSelecionada.set(ano, mes, dia)
                        campoData
                            .setText(dataSelecionada.formatoBrasileiro())
                    }, ano, mes, dia
                )
                    .show()
            }
    }

    private fun criaLayout(): View {
        return LayoutInflater.from(context)
            .inflate(
                R.layout.form_transacao,
                viewGroup,
                false
            )
    }

    private fun tituloPor(tipo: Tipo): Int {
        return if (tipo == Tipo.RECEITA) {
            R.string.adiciona_receita
        } else {
            R.string.adiciona_despesa
        }
    }

    private fun categoriaPor(tipo: Tipo): Int {
        return if (tipo == Tipo.RECEITA) {
            R.array.categorias_de_receita
        } else {
            R.array.categorias_de_despesa
        }
    }


}