import Auxiliar

def prim(m):
    num_nodos = len(m) # O(1) | hay tantos nodos como longitud tenga la matriz triangular pasada por parámetro

    visitados = [False] * num_nodos # O(m) | lista de visitados para marcar los nodos origen que vamos encontrando
    visitados[0] = True # O(1) | ponemos como visitado el primer nodo porque es el nodo del que partimos

    aristas = [] # O(1) | lista de aristas del árbol recubridor de coste mínimo
    nodosOrigen = [] # O(1) | lista de nodos origen
    nodosDestino = [] # O(1) | lista de nodos destino

    while(len(aristas) < num_nodos - 1): # O(m) | si hay n nodos => hay n-1 aristas [afirmacion para un arbol]
        peso_arista = float('inf') # O(1) | inicializamos con el infinito para ir guardando el peso minimo
        nodoOrigen = nodoDestino = None # O(1)

        # Elegimos la mejor componente en función de un determinado heurístico...
        # Buscamos la arista mínima que conecta un vértice seleccionado con uno no seleccionado

        for u in range(num_nodos): # O(m) | recorremos las filas de la matriz triangular

            if visitados[u]: # No hay un else, se ejecuta un numero cte de veces => O(1) | si el nodo del que partimos ya está visitado...
                for v in range(num_nodos): # O(m) | recorremos las columnas de la matriz triangular

                    if not visitados[v]: # No hay un else, se ejecuta un numero cte de veces => O(1) | si el nodo al que llegamos no está visitado...

                        if(m[u][v] < peso_arista and m[u][v] != 0): # No hay un else, se ejecuta un numero cte de veces => O(1)
                            peso_arista = m[u][v] # O(1)
                            nodoOrigen = u # O(1)
                            nodoDestino = v # O(1)

                        if(m[v][u] < peso_arista and m[v][u] != 0): # No hay un else, se ejecuta un numero cte de veces => O(1)
                            peso_arista = m[v][u] # O(1)
                            nodoOrigen = u # O(1)
                            nodoDestino = v # O(1)


        visitados[nodoDestino] = True # O(1) | marcamos como visitado el nodo al que hemos llegado
        aristas.append(peso_arista) # O(1) | guardamos el peso minimo obtenido

        nodosOrigen.append(nodoOrigen) # O(1) | guardamos el nodo del que partimos en la arista encontrada
        nodosDestino.append(nodoDestino) # O(1) | guardamos el nodo al que llegamos en la arista encontrada

    return aristas, nodosOrigen, nodosDestino # O(1)

def printPrim(aristas, nodosOrigen, nodosDestino):
    costeTotal = 0 # O(1)

    for i in range(len(aristas)): # O(m)
        costeTotal += aristas[i] # O(1)

    print("COSTE TOTAL ÓPTIMO= ",costeTotal) # O(1)
    for i in range (len(nodosOrigen)): # O(m)
        print("ARISTA NUMERO ", i+1, " : DESDE NODO=", nodosOrigen[i], " HASTA NODO=", nodosDestino[i]," - *** Coste=",aristas[i]) # O(1)

def main():
    nombre = str(input("Dame nombre del fichero: "))
    matrizTriangular = Auxiliar.matrizTriangularDesdeFichero(nombre+".txt")
    Auxiliar.escribirMatriz(matrizTriangular)

    aristas, nodosOrigen, nodosDestino = prim(matrizTriangular)
    printPrim(aristas, nodosOrigen, nodosDestino)

#main()