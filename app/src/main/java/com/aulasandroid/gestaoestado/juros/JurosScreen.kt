package com.aulasandroid.gestaoestado.juros

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aulasandroid.gestaoestado.calculos.calcularJuros
import com.aulasandroid.gestaoestado.calculos.calcularMontante
import com.aulasandroid.gestaoestado.components.CaixaDeEntrada
import com.aulasandroid.gestaoestado.components.CardResultado

@Composable
fun JurosScreen(
    modifier: Modifier = Modifier,
    jurosScreenViewModel: JurosScreenViewModel
) {
    val corTema = Color(136, 38, 199, 255)

//    var capital by remember {
//        mutableStateOf("")
//    }
    val capital by jurosScreenViewModel.captal.observeAsState(initial = "")


    var taxa by remember {
        mutableStateOf("")
    }
    var tempo by remember {
        mutableStateOf("")
    }
    var juros by remember {
        mutableDoubleStateOf(0.0)
    }
    var montante by remember {
        mutableDoubleStateOf(0.0)
    }
    Column (
        modifier = modifier.fillMaxSize(),
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier.fillMaxWidth()
                    .height(120.dp)
                    .background(color = corTema)
                    .padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Calculadora Juros Simples",
                    fontSize = 24.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }

            Column(
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 32.dp)
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(y = (-30).dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFF9F6F6)
                    ),
                    elevation = CardDefaults.cardElevation(4.dp),
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp)
                        ,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Text(
                            text = "Dados do investimento",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )


                        CaixaDeEntrada(
                            label = "Valor investimento",
                            placeholder = "Quanto deseja investir?",
                            keyboardType = KeyboardType.Decimal,
                            modifier = Modifier.fillMaxWidth(),
                            corTema = corTema,
                            value = capital,
                            atualizarValor = {
                                jurosScreenViewModel.onCaptalChanged(it)
                            }
                        )

                        CaixaDeEntrada(
                            label = "Taxa de juros mensais",
                            placeholder = "Qual é a taxa de juro mensal?",
                            keyboardType = KeyboardType.Decimal,
                            modifier = Modifier.fillMaxWidth(),
                            corTema = corTema,
                            value = taxa,
                            atualizarValor = {
                                taxa = it
                            }
                        )

                        CaixaDeEntrada(
                            label = "Período em meses",
                            placeholder = "Qual o tempo em meses?",
                            keyboardType = KeyboardType.Decimal,
                            modifier = Modifier.fillMaxWidth(),
                            corTema = corTema,
                            value = tempo,
                            atualizarValor = {
                                tempo = it
                            }
                        )

                        Button (
                            onClick = {
                                juros = calcularJuros(
                                    capital = capital.toDouble(),
                                    taxa = taxa.toDouble(),
                                    tempo = tempo.toDouble()
                                )

                                montante = calcularMontante(
                                    capital = capital.toDouble(),
                                    juros = juros
                                )
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(144, 80, 187, 190)
                            ),
                            modifier = Modifier.fillMaxWidth()
                                .height(48.dp),

                            ) {
                            Text(
                                text = "CALCULAR",
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                fontSize = 14.sp
                            )
                        }
                    }
                }

                CardResultado(juros = juros, montante = montante)
                //card tava aqui
            }
        }
    }
}