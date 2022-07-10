# language: pt

Funcionalidade: Propondo lances ao leilao

  Cenario: Propondo um unico lance valido
    Dado um lance valido
    Quando propoe ao leilao
    Entao o lance eh aceito

  Cenario: Propondo varios lances validos
    Dado um lance de 10 reais do usuario "fulano"
    E um lance de 15 reais do usuario "beltrano"
    Quando propoe varios lances ao leilao
    Entao os lances sao aceitos

  Esquema do Cenario: Cenario: Propondo um lance invalido de <valor> reais
    Dado um lance de <valor> reais e do usuario '<usuario>'
    Quando propoe ao leilao
    Entao o lance nao eh aceito
    Exemplos:
      | valor | usuario  |
      | 0     | beltrano |
      | -1    | cigano   |

  Cenario: Propondo uma sequencia de lances
    Dado dois lances
      | valor | usuario  |
      | 10.0  | beltrano |
      | 15.0  | beltrano |
    Quando propoe varios lances ao leilao
    Entao o segundo lance nao eh aceito