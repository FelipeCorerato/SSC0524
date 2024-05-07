function teste(resultado, esperado) {
  if (resultado === esperado) {
    console.log("Teste passou!");
  } else {
    console.log("Teste falhou!");
    console.log(`Esperado: ${esperado}; Resultado: ${resultado}`);
  }
}

function primeiraVersao(sequencia) {
  return 0;
}

function segundaVersao(sequencia) {
  return sequencia
    .split("")
    .map(Number)
    .reduce((acc, curr) => acc + curr, 0);
}

function retornaValorDeSimbolo(jogada) {
  switch (jogada) {
    case "X":
      return 10;
    case "-":
      return 0;
    default:
      return jogada;
  }
}

function retornaCenarioDaRodada(rodada) {
  const [primeiroLancamento, segundoLancamento] = rodada;

  if (primeiroLancamento === "X") {
    return "STRIKE";
  }

  if (segundoLancamento === "/") {
    return "SPARE";
  }

  if (primeiroLancamento === "-") {
    return "JOGADA NULA";
  }

  return "JOGADA NORMAL";
}

function computaPlacar(sequencia) {
  // Regex para separar a sequencia em rodadas: "8070X-" => ["80", "70", "X-"]
  let rodadas = sequencia.replace(/\s/g, "").match(/.{1,2}/g);
  // Operacao para juntar os lancamentos da ultima rodada
  rodadas[9] = `${rodadas[9]}${rodadas.pop()}`;

  const rodadasComResultados = rodadas.map((rodada, indice) => {
    const [primeiroLancamento, segundoLancamento] = rodada;
    const cenarioDaRodada = retornaCenarioDaRodada(rodada);

    const ehUltimaRodada = indice === rodadas.length - 1;
    if (ehUltimaRodada) {
      const terceiroLancamento = rodada[2];

      if (cenarioDaRodada === "STRIKE") {
        return {
          [`${primeiroLancamento}${segundoLancamento}${terceiroLancamento}`]:
            Number(retornaValorDeSimbolo(primeiroLancamento)) +
            Number(retornaValorDeSimbolo(segundoLancamento)) +
            Number(retornaValorDeSimbolo(terceiroLancamento)),
        };
      } else if (cenarioDaRodada === "SPARE") {
        return {
          [`${primeiroLancamento}${segundoLancamento}${terceiroLancamento}`]:
            10 + Number(retornaValorDeSimbolo(terceiroLancamento)),
        };
      }
    }

    let resultadoDaRodada = 0;
    if (cenarioDaRodada === "STRIKE") {
      const [
        primeiroLancamentoDaProximaRodada,
        segundoLancamentoDaProximaRodada,
      ] = rodadas[indice + 1];

      const proximoLancamento =
        retornaCenarioDaRodada(primeiroLancamentoDaProximaRodada) === "STRIKE"
          ? 10
          : Number(primeiroLancamentoDaProximaRodada);

      const ehPenultimaRodada = indice === rodadas.length - 2;

      const proximoDoProximoLancamento =
        retornaCenarioDaRodada(primeiroLancamentoDaProximaRodada) === "STRIKE"
          ? Number(
              retornaValorDeSimbolo(
                ehPenultimaRodada
                  ? segundoLancamentoDaProximaRodada
                  : rodadas[indice + 2][0]
              )
            )
          : Number(segundoLancamentoDaProximaRodada);

      resultadoDaRodada = 10 + proximoLancamento + proximoDoProximoLancamento;
    } else if (cenarioDaRodada === "SPARE") {
      const [primeiroLancamentoDaProximaRodada] = rodadas[indice + 1];

      const proximoLancamento =
        retornaCenarioDaRodada(primeiroLancamentoDaProximaRodada) === "STRIKE"
          ? 10
          : Number(primeiroLancamentoDaProximaRodada);

      resultadoDaRodada = 10 + proximoLancamento;
    } else if (cenarioDaRodada === "JOGADA NORMAL") {
      resultadoDaRodada = segundoLancamento
        ? Number(primeiroLancamento) + Number(segundoLancamento)
        : 0;
    }

    return { [rodada]: resultadoDaRodada };
  });

  const resultadoDoPlacar = rodadasComResultados.reduce(
    (acumulador, itemAtual) => acumulador + Object.values(itemAtual)[0],
    0
  );

  return resultadoDoPlacar;
}

// Caso mais simples
const casoMaisSimples = "000000000000000000000";
teste(primeiraVersao(casoMaisSimples), 0);

// Caso sem pontos especiais (strikes e spares)
const casoIntermediario = "123456789123456789123";
teste(segundaVersao(casoIntermediario), 96);

// Cases mistos (enunciado)
const casosFinais = ["8070539/9/X-80513/90-", "8/90447290X-X-80359/7"];
teste(computaPlacar(casosFinais[0]), 122);
teste(computaPlacar(casosFinais[1]), 133);

// Casos extremos
// Pontuacao maxima
teste(computaPlacar("X-X-X-X-X-X-X-X-X-XXX"), 300);

// Spare na ultima rodada
teste(computaPlacar("8/90447290X-X-80359/7"), 133);
