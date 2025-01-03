import Auxiliar

def prim(m):
    num_vertices = len(m) # O(1)
    verticesSeleccionados = [False] * num_vertices # O(m)
    verticesSeleccionados[0] = True # O(1)
    aristas = [] # O(1)
    nodosIniciales = [] # O(1)
    nodosFinales = [] # O(1)

    while(len(aristas) < num_vertices - 1): # O(m)
        peso_arista = float('inf') # O(1)
        nodoInicial = nodoFinal = None # O(1)

        # Buscamos la arista mínima que conecta un vértice seleccionado con uno no seleccionado
        for u in range(num_vertices): # O(m)
            if verticesSeleccionados[u]: # No hay un else, se ejecuta un numero cte de veces => O(1)
                for v in range(num_vertices): # O(m)
                    if(not verticesSeleccionados[v] and m[u][v] < peso_arista and m[u][v]!=0): # No hay un else, se ejecuta un numero cte de veces => O(1)
                        peso_arista = m[u][v] # O(1)
                        nodoInicial = u # O(1)
                        nodoFinal = v # O(1)
                    if(not verticesSeleccionados[v] and m[v][u] < peso_arista and m[v][u]!=0): # No hay un else, se ejecuta un numero cte de veces => O(1)
                        peso_arista = m[v][u] # O(1)
                        nodoInicial = u # O(1)
                        nodoFinal = v # O(1)
        # Guardamos el peso de la arista, el nodo inicial y el nodo final para luego imprimirlo en otra función
        verticesSeleccionados[nodoFinal] = True # O(1)
        aristas.append(peso_arista) # O(1)
        nodosIniciales.append(nodoInicial) # O(1)
        nodosFinales.append(nodoFinal) # O(1)
        #print("ARISTA NUMERO ",len(aristas)," : DESDE NODO=",nodoInicial," HASTA NODO=",nodoFinal," - *** Coste=",peso_arista)
    return aristas,nodosIniciales,nodosFinales # O(1)


# Función para imprimir los resultados
def printPrim(aristas,nodosIniciales,nodosFinales):
    costeTotal = 0 # O(1)
    for i in range(len(aristas)): # O(m)
        costeTotal += aristas[i] # O(1)
    print("COSTE TOTAL ÓPTIMO= ",costeTotal) # O(1)
    for i in range (len(nodosIniciales)): # O(m)
        print("ARISTA NUMERO ",i+1," : DESDE NODO=",nodosIniciales[i]," HASTA NODO=",nodosFinales[i]," - *** Coste=",aristas[i]) # O(1)


#nombre = str(input("Dame nombre del fichero: "))
#matrizTriangular = Auxiliar.matrizTriangularDesdeFichero(nombre+".txt")
#Auxiliar.escribirMatriz(matrizTriangular)

#aristas,nodosIniciales,nodosFinales=prim(matrizTriangular)
#printPrim(aristas,nodosIniciales,nodosFinales)


##import sys
##

##visitados = []
##for i in range(len(matrizTriangular)):
##    visitados.append(False)
##matrizDePrim = []

##for i in range(len(matrizTriangular)-1): # FILAS
##    indiceDelNodoFuente = i
##    indiceDelNodoDestino = 0
##    pesoMinimo = matrizTriangular[i][i+1]
##    for a in range(i+1): # NODOS
##        for j in range(i+1,len(matrizTriangular[0])): # COLUMNAS
##            peso = matrizTriangular[a][j]
##            if(peso < pesoMinimo and not(visitados[j])):
##                pesoMinimo = peso
##                indiceDelNodoDestino = j
##                print(indiceDelNodoFuente," ",indiceDelNodoDestino," ",pesoMinimo)
##    visitados[indiceDelNodoDestino] = True
##    matrizDePrim.append((indiceDelNodoFuente,indiceDelNodoDestino,pesoMinimo))

##for a in range(len(matrizTriangular)): # NODOS
##    print(a)
##    visitados[a] = True
##    indiceDelNodoFuente = a
##    indiceDelNodoDestino = 0
##    pesoAGuardar = 0
##    for i in range(a-1): # FILAS
##        #indiceDelNodoFuente = i
##        #indiceDelNodoDestino = 0
##        pesoMinimo = matrizTriangular[i][i+1]
##        for j in range(i+1,len(matrizTriangular[0])): # COLUMNAS
##            peso = matrizTriangular[a][j]
##            if(peso < pesoMinimo and not(visitados[j])):
##                pesoMinimo = peso
##                indiceDelNodoDestino = j
##                print(indiceDelNodoFuente," ",indiceDelNodoDestino," ",pesoMinimo)
##                pesoAGuardar = peso
##    visitados[indiceDelNodoDestino] = True
##    matrizDePrim.append((indiceDelNodoFuente,indiceDelNodoDestino,pesoAGuardar))

##for a in range(len(matrizTriangular)-1): # NODOS
##    print(a)
##    visitados[a] = True
##    indiceDelNodoFuente = a
##    indiceDelNodoDestino = 0
##    pesoAGuardar = 0
##    for i in range(a+1): # FILAS
##        #indiceDelNodoFuente = i
##        #indiceDelNodoDestino = 0
##        if(visitados[i]):
##            pesoMinimo = matrizTriangular[i][i+1]
##            for j in range(i+1,len(matrizTriangular[0])): # COLUMNAS
##                peso = matrizTriangular[a][j]
##                if(peso < pesoMinimo and not(visitados[j])):
##                    pesoMinimo = peso
##                    indiceDelNodoDestino = j
##                    print(indiceDelNodoFuente," ",indiceDelNodoDestino," ",pesoMinimo)
##                    pesoAGuardar = peso
##
##    visitados[indiceDelNodoDestino] = True
##    matrizDePrim.append((indiceDelNodoFuente,indiceDelNodoDestino,pesoAGuardar))

##def IsTodosVisitados(visitados):
##    for i in range(len(visitados)):
##        if(not visitados[i]):
##            return False
##    return True
##
##while(not IsTodosVisitados(visitados)): # NODOS
##    ##print(a)
##    visitados[a] = True
##    indiceDelNodoFuente = a
##    indiceDelNodoDestino = 0
##    pesoAGuardar = 0
##    for i in range(a+1): # FILAS
##        #indiceDelNodoFuente = i
##        #indiceDelNodoDestino = 0
##        if(visitados[i]):
##            pesoMinimo = matrizTriangular[i][i+1]
##            for j in range(i+1,len(matrizTriangular[0])): # COLUMNAS
##                peso = matrizTriangular[a][j]
##                if(peso < pesoMinimo and not(visitados[j])):
##                    pesoMinimo = peso
##                    indiceDelNodoDestino = j
##                    print(indiceDelNodoFuente," ",indiceDelNodoDestino," ",pesoMinimo)
##                    pesoAGuardar = peso
##
##    visitados[indiceDelNodoDestino] = True
##    matrizDePrim.append((indiceDelNodoFuente,indiceDelNodoDestino,pesoAGuardar))

##def BuscaNodosDestino(indiceNodoOrigen, visitados):
##    indiceNodoDestino = 0 ## el indice del nodo destino que retornaremos
##    peso = sys.maxsize ## el valor maximo posible para un entero
##
##    for i in range(indiceNodoOrigen,len(matrizTriangular)):
##        if(i != indiceNodoOrigen and matrizTriangular[indiceNodoOrigen][i] < peso and not visitados[i]):
##            indiceNodoDestino = i
##            peso = matrizTriangular[indiceNodoOrigen][i]
##
##    return (indiceNodoDestino, peso)
##
### ponemos a visitado el primer nodo
##visitados[0] = True
##nodosInicio = [] ## de len == len(matrizTriangular)
##
##for i in range(len(matrizTriangular)-1): # NODOS
##    indiceNodoOrigen = i
##    nodosInicio.append(indiceNodoOrigen)
##
##	## buscar el eje con origen el nodoOrigenDeLaIteracion y destino cualquiera accesible desde el nodo hablado
##    resultadoDeLaBusqueda = BuscaNodosDestino(indiceNodoOrigen, visitados)
##
##    indiceNodoDestino = resultadoDeLaBusqueda[0]
##    pesoObtenido = resultadoDeLaBusqueda[1]
##    matrizDePrim.append((indiceNodoOrigen, indiceNodoDestino, pesoObtenido))
##
##    visitados[indiceNodoDestino] = True
##
##    indiceNodoOrigen = indiceNodoDestino

##def ActualizarListaNodosOrigen(listaNodosOrigen, visitados):
##    for i in range(len(visitados)):
##        if(visitados[i]):
##            listaNodosOrigen.append(i) # agregamos el nodo visitado en la lista de nodos origen
##    return listaNodosOrigen
##
##def ActualizarListaNodosDestino(listaNodosDestino, visitados):
##    res = []
##    for i in range(len(visitados)):
##        if(not visitados[i]):
##            res.append(i)
##            ##listaNodosDestino.pop(i) # eliminamos el nodo visitado en la lista de nodos destino
##    return res
##    ##return listaNodosDestino
##
##def DevuelveAristaDePesoMinimo(resultadosRecogidos):
##    limite = sys.maxsize
##    nodoOrigenFinal = -1
##    nodoDestinoFinal = -1
##    for i in range(len(resultadosRecogidos)):
##        if(resultadosRecogidos[i][2] <= limite):
##            nodoOrigenFinal = resultadosRecogidos[i][0]
##            nodoDestinoFinal = resultadosRecogidos[i][1]
##            limite = resultadosRecogidos[i][2]
##    return (nodoOrigenFinal, nodoDestinoFinal, limite)
##
##
##listaNodosOrigen = []
##listaNodosDestino = []
##
##for i in range(len(matrizTriangular)):
##    listaNodosDestino.append(i)
##
##visitados[0] = True # ponemos como visitado el primer nodo
##listaNodosOrigen.append(0) # metemos el primer nodo en la lista de nodos origen
##listaNodosDestino.pop(0) # eliminamos el primer nodo origen de la lista de nodos destino
##
### Si hay n nodos => se seleccionan n-1 aristas
##for a in range(len(matrizTriangular)-1): # n-1 aristas
###while(len(listaNodosDestino)-1 > 0):
##    #NodosOrigen = ActualizarListaNodosOrigen(listaNodosOrigen, visitados)
##    #NodosDestino = ActualizarListaNodosDestino(listaNodosDestino, visitados)
##
##    resultadosRecogidos = []
##
##    listaNodosOrigen = ActualizarListaNodosOrigen(listaNodosOrigen, visitados)
##    listaNodosDestino = ActualizarListaNodosDestino(listaNodosDestino, visitados)
##
##    peso = sys.maxsize
##
##    for nodoOrigen in listaNodosOrigen:
##        # Buscamos el eje de menor coste entre el nodoOrigen y un nodoDestino (nodo que no esta visitado)
##        # los nodos a los que se llega desde el nodoOrigen vie
##        for nodoDestino in listaNodosDestino:
##
##            if(matrizTriangular[nodoOrigen][nodoDestino] < peso and matrizTriangular[nodoOrigen][nodoDestino] != 0):
##                peso = matrizTriangular[nodoOrigen][nodoDestino]
##                indiceNodoDestinoFinal = nodoDestino
##                indiceNodoOrigenFinal = nodoOrigen
##
##                resultadosRecogidos.append((indiceNodoOrigenFinal,indiceNodoDestinoFinal,peso))
##
##    resultadoMasBajo = DevuelveAristaDePesoMinimo(resultadosRecogidos)
##
##    visitados[resultadoMasBajo[1]] = True
##    matrizDePrim.append((resultadoMasBajo[0], resultadoMasBajo[1], resultadoMasBajo[2]))
##
##costeTotalOptimo = 0
##for i in range(len(matrizDePrim)):
##    costeTotalOptimo += matrizDePrim[i][2]
##
##print("COSTE TOTAL ÓPTIMO= ",costeTotalOptimo)
##print("*********************")
##print("ARISTAS SELECCIONADAS=")
##for i in range(len(matrizDePrim)):
##    print("ARISTA NUMERO ", i+1, " :\t", "DESDE NODO= ", matrizDePrim[i][0],
##    "\tHASTA NODO ", matrizDePrim[i][1], " *** COSTE= ", matrizDePrim[i][2])

