# Marco 2 — Componentes Conexas

## 1. Caso particular do problema

Foi utilizado um caso com o máximo solicitado de:

```text
V = 6
E = 6
```

O grafo é simples e não direcionado.

As arestas escolhidas foram:

```text
(1,2)
(1,4)
(1,3)
(2,3)
(3,4)
(5,6)
```

O grafo possui duas componentes conexas.

### 1.1 Desenho do grafo

```text
      2
     / \
    /   \
   1-----3
    \   /
     \ /
      4

5 ----- 6
```

As conexões do primeiro componente são entre os vértices `1, 2, 3 e 4`.

O segundo componente é formado pelos vértices `5 e 6`.

---

## 2. Listas de adjacência

As listas de adjacência são:

```text
1 → 2, 4, 3
2 → 1, 3
3 → 2, 4, 1
4 → 1, 3
5 → 6
6 → 5
```

Como o grafo é não direcionado, cada aresta aparece nos dois sentidos.

Por exemplo:

```text
1 → 2
2 → 1
```

---

## 3. Excentricidade, raio e diâmetro

A excentricidade de um vértice é a maior distância desse vértice até qualquer outro vértice da mesma componente conexa.

O raio de uma componente é a menor excentricidade entre seus vértices.

O diâmetro é a maior excentricidade da componente.

### 3.1 Componente {1, 2, 3, 4}

As distâncias mínimas entre os vértices são:

| De/Para | 1 | 2 | 3 | 4 |
|---|---:|---:|---:|---:|
| **1** | 0 | 1 | 1 | 1 |
| **2** | 1 | 0 | 1 | 2 |
| **3** | 1 | 1 | 0 | 1 |
| **4** | 1 | 2 | 1 | 0 |

Assim:

- `ecc(1) = 1`
- `ecc(2) = 2`
- `ecc(3) = 1`
- `ecc(4) = 2`

Portanto:

```text
Raio = 1
Diâmetro = 2
```

Os vértices centrais são aqueles cuja excentricidade é igual ao raio:

```text
Vértices centrais: 1 e 3
```

Logo, o centro da componente é:

```text
Centro = {1, 3}
```

### 3.2 Componente {5, 6}

Como existe apenas uma aresta:

```text
5 ----- 6
```

as distâncias são:

```text
d(5,6) = 1
d(6,5) = 1
```

Assim:

- `ecc(5) = 1`
- `ecc(6) = 1`

Portanto:

```text
Raio = 1
Diâmetro = 1
```

Os dois vértices possuem a menor excentricidade da componente:

```text
Vértices centrais: 5 e 6
```

Logo:

```text
Centro = {5, 6}
```

---

## 4. Rastreamento do algoritmo de componentes conexas

O algoritmo utiliza **DFS recursiva**.

As principais estruturas utilizadas são:

```text
marked[v] → indica se o vértice já foi visitado
id[v]     → identifica a componente conexa à qual o vértice pertence
count     → quantidade de componentes encontradas
```

A lógica é:

1. percorrer todos os vértices;
2. quando encontrar um vértice ainda não visitado, iniciar uma nova DFS;
3. marcar todos os vértices alcançáveis a partir dele com o mesmo identificador de componente;
4. após a DFS terminar, incrementar a quantidade de componentes;
5. continuar até que todos os vértices sejam processados.

### 4.1 Estado inicial

Antes da busca:

```text
marked = [false, false, false, false, false, false]
id     = [0, 0, 0, 0, 0, 0]
count  = 0
```

### 4.2 Início da primeira componente

O primeiro vértice é o `1`.

Como ele ainda não foi visitado, inicia-se uma DFS:

```text
DFS(1)
count = 0
```

O vértice 1 é marcado e recebe o identificador `0`:

```text
marked = [true, false, false, false, false, false]
id     = [0, 0, 0, 0, 0, 0]
```

O algoritmo encontra os vizinhos de 1: `2`, `4` e `3`.

### 4.3 Visita do vértice 2

A DFS entra em `2`.

```text
marked = [true, true, false, false, false, false]
id     = [0, 0, 0, 0, 0, 0]
```

O vértice 2 possui os vizinhos `1` e `3`.

O vértice 1 já foi visitado.

O vértice 3 ainda não foi visitado, então a DFS continua para `3`.

### 4.4 Visita do vértice 3

Agora:

```text
marked = [true, true, true, false, false, false]
id     = [0, 0, 0, 0, 0, 0]
```

Os vizinhos de 3 são `2`, `4` e `1`.

Os vértices 2 e 1 já foram visitados.

O vértice 4 ainda não foi visitado, então a DFS continua para `4`.

### 4.5 Visita do vértice 4

Agora:

```text
marked = [true, true, true, true, false, false]
id     = [0, 0, 0, 0, 0, 0]
```

Os vizinhos de 4 são `1` e `3`, ambos já visitados.

A primeira DFS termina.

Nesse momento, os vértices:

```text
1, 2, 3, 4
```

pertencem à mesma componente.

Então:

```text
count = 1
```

### 4.6 Início da segunda componente

O próximo vértice ainda não visitado é o `5`.

É iniciada uma nova DFS:

```text
DFS(5)
```

O vértice 5 é marcado e recebe o identificador `1`:

```text
marked = [true, true, true, true, true, false]
id     = [0, 0, 0, 0, 1, 0]
```

O único vizinho de 5 é o vértice 6.

### 4.7 Visita do vértice 6

A DFS entra em 6:

```text
marked = [true, true, true, true, true, true]
id     = [0, 0, 0, 0, 1, 1]
```

Seu único vizinho é 5, que já foi visitado.

A segunda DFS termina.

Então:

```text
count = 2
```

### 4.8 Resultado

As componentes encontradas foram:

```text
Componente 0: {1, 2, 3, 4}
Componente 1: {5, 6}
```

Portanto, o algoritmo encontrou:

```text
2 componentes conexas
```

---

## 5. Consultas de conectividade

Depois que o algoritmo termina, o vetor `id[]` permite responder se dois vértices pertencem à mesma componente.

A consulta é realizada comparando seus identificadores:

```text
id[v] == id[w]
```

Se forem iguais, os vértices pertencem à mesma componente.

Exemplos:

```text
id[1] == id[3]
```

Logo:

```text
1 e 3 são conexos.
```

Já:

```text
id[1] != id[5]
```

Logo:

```text
1 e 5 não são conexos.
```

Depois do pré-processamento das componentes, cada consulta de conectividade possui custo:

```text
O(1)
```

---

## 6. Complexidade

Seja:

```text
V = número de vértices
E = número de arestas
```

A DFS percorre cada vértice e cada aresta um número constante de vezes.

Assim, o tempo total do algoritmo é:

```text
O(V + E)
```

A memória utilizada pelo vetor `marked`, pelo vetor `id` e pela pilha de chamadas da DFS é:

```text
O(V)
```

de memória auxiliar.

Considerando também a representação do grafo por listas de adjacência, a representação ocupa:

```text
O(V + E)
```

de memória.

As consultas de conectividade após o processamento possuem custo:

```text
O(1)
```

cada.

---

## 7. Conclusão

Para o caso com `V = 6` e `E = 6`, o algoritmo encontrou duas componentes conexas:

```text
{1, 2, 3, 4}
{5, 6}
```

Para a primeira componente:

```text
Raio = 1
Diâmetro = 2
Centro = {1, 3}
```

Para a segunda componente:

```text
Raio = 1
Diâmetro = 1
Centro = {5, 6}
```

A DFS recursiva identifica as componentes atribuindo o mesmo identificador
aos vértices alcançáveis durante cada busca.

O algoritmo possui complexidade `O(V + E)` e, após o pré-processamento,
as consultas de conectividade podem ser realizadas em `O(1)`.
