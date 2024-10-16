#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "mpi.h"


int log2n(int n){
    int result=0;
    while(n !=1){
        n >>=1;
        result++;

    }

    return  result;
}

int main(int argc, char* argv[])
{
   
    int s; 
    int id_virtual; 
    int destinatie_virtuala;
    int sursa_virtuala;
   

    char M[100]="Mesaj";
    int d;
    int id;
    unsigned int mask=(1<<d)-1;
    int sursa;
    int destinatie;
    int rank, np;

    MPI_Status status; 
    MPI_Init(&argc, &argv);
    MPI_Comm_rank(MPI_COMM_WORLD, &rank);
    MPI_Comm_size(MPI_COMM_WORLD, &np);

    

    d = log2n(np); 
    mask = (np - 1); 
    id = rank;
    s = 5;
    id_virtual = id ^ s;
    int k; 

    for (k = d - 1; k >= 0; --k)
    {
        mask ^= (1 <<k);

        if ((id_virtual & mask) == 0)
        {
            if ((id_virtual & (1 <<k)) == 0)
            {
                destinatie_virtuala=id_virtual ^ (1 <<k);
                destinatie = destinatie_virtuala ^ s;
                MPI_Send(M, (int)(strlen(M) + 1), MPI_CHAR, destinatie, 99, MPI_COMM_WORLD);
                printf("%d trimis mesaj %s catre %d\n", rank, M, destinatie);
            }
            else
            {
                sursa_virtuala=id_virtual ^ (1 <<k);
                sursa =  sursa_virtuala ^ s;
                MPI_Recv(M, 100, MPI_CHAR, sursa, 99, MPI_COMM_WORLD, &status);
                printf("%d primit mesaj %s de la %d\n", rank, M, sursa);
            }
        }
    }

    MPI_Finalize();

    return 0;
}