from time import time
import Prim
import Auxiliar

def main():
    print ("TIEMPO= MILISEGUNDOS")
    t1=0
    t2=0
    n=256
    while(True):
        m = Auxiliar.matrizTriangularEnterosAleatorios(n,100,999)
        t1=time()
        Prim.prim(m)
        t2=time()
        print("n= ",n,"**tiempo=",int(1000*(t2-t1)))
        n=n*2


main()