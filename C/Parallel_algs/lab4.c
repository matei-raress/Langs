#include <stdio.h>
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


int main(int argc, char** argv){
    char mesaj[100]="Mesaj";
    int d; //dimensiune hipercub
    int id;
    unsigned int mask=(1<<d)-1;
    int sursa;
    int destinatie;
    int rank, np;

    MPI_Status status;
    MPI_Init(&argc,&argv);
    MPI_Comm_size(MPI_COMM_WORLD,&np);
    MPI_Comm_rank(MPI_COMM_WORLD, &rank);
    id=rank;
    d=log2n(np);
    int k;
    mask= (1 <<d)-1;
    for(k=d-1;k>=0;k--){
        mask= mask ^ (1 <<k);
        if( (id & mask) == 0){
            if((id & (1<<k)) ==0) {
                destinatie= id ^ (1<<k);
                //send mesaj la destinatie
                MPI_Send( mesaj ,(int)strlen(mesaj) , MPI_CHAR ,destinatie , 99, MPI_COMM_WORLD);
                printf("trimis %s\n",mesaj);

            }
            else{
                sursa = id ^ (1<<k);
                //recv mesaj de  la sursa
                MPI_Recv( mesaj ,(int)strlen(mesaj) ,MPI_CHAR , sursa , 99, MPI_COMM_WORLD , &status);
                printf("primit %s\n",mesaj);
            }

        }
    }


    MPI_Finalize();
    return 0;
}