def crearMatriz(filas,columnas,valor):
    m=[]
    for i in range(filas):
        m.append(columnas*[valor])
    return m

def progDin(texto,patron):
    texto=" "+texto
    patron=" "+patron
    n=len(texto)
    m=len(patron)
    mat=crearMatriz(n,m,False)
    mat[0][0]=True
    #escribirMatriz(mat)
    for i in range(1,n):
        for j in range(1,m):
            #print(i,j)
            # caracteres iguales en posicion i y posicion j
            if texto[i]==patron[j]:
                if mat[i-1][j-1]:
                    mat[i][j]=True

            # estamos ante un "?"
            if patron[j]=='?':
                if mat[i][j-1] or mat[i-1][j-1]:
                    mat[i][j]=True

            # estamos ante un *
            if patron[j]=='*':
                if mat[i][j-1] or mat[i-1][j-1] or mat[i-1][j]:
                    mat[i][j]=True
    #escribirMatriz(mat)
    return mat[n-1][m-1]

def leerFichero(fich):
    f=open(fich,"r")
    cont=0
    patrones=[]
    resultados=[]
    #print("FICHERO A PROCESAR")
    for linea in f:
        linea=linea.strip("\n")
        #print(linea)
        if cont == 0:
            texto=linea
            cont=cont+1
        else:
            argumentos=linea.split(" ")
            patrones.append(argumentos[0])
            resultados.append(argumentos[1])
    m=[texto,patrones,resultados]
    f.close()
    return(m)

def main():
    res=leerFichero("test2.txt")
    texto=res[0]
    patrones=res[1]
    resultados=res[2]

    print("Resultados obtenidos del algoritmo de patrones:")
    for i in range(len(patrones)):
        print(progDin(texto,patrones[i]),"\tSolución:",resultados[i])

main()