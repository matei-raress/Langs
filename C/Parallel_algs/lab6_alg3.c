#include <stdio.h>
#include <mpi/mpi.h>
#include <stdlib.h>
#include <string.h>

int main(int argc, char* argv[]){

    int np;
    int dim=16;
    int *A = (int*) malloc(dim * sizeof(int));
    int radacina=1;
    int rank; 
    MPI_Status status; 
    int copilStg, copilDrt;
    int destinatie; 
    int tag = 99; 
    int valoareCopilStang, valoareCopilDrept;
    
    MPI_Init(&argc, &argv);
    MPI_Comm_rank(MPI_COMM_WORLD, &rank);
    MPI_Comm_size(MPI_COMM_WORLD, &np);
    int i=0;
    
    for(i;i<dim/2;i++){
        A[i]=0;
    }
    A[8] = 3; A[9] = 7; A[10] = 8; A[11] = 3; A[12] = 9; A[13] = 2; A[14] = 3; A[15] = 1;


    int valoare;
    MPI_Scatter(A, 1, MPI_INT, &valoare, 1, MPI_INT, radacina, MPI_COMM_WORLD);

    if(rank >= np / 2) //frunze
    {
        destinatie = rank / 2; //parinte
        MPI_Send(&valoare, 1, MPI_INT, destinatie, tag, MPI_COMM_WORLD);
    }
    else if(rank >0 ){ //celelalte
        copilStg = 2 * rank; // copil stang
        copilDrt = 2 * rank + 1; // copil drept

        MPI_Recv(&valoareCopilStang, 1, MPI_INT, copilStg, tag, MPI_COMM_WORLD, &status);
        MPI_Recv(&valoareCopilDrept, 1, MPI_INT, copilDrt, tag, MPI_COMM_WORLD, &status);

        valoare = valoareCopilStang + valoareCopilDrept;
            
        destinatie = rank / 2; // parintele 
        MPI_Send(&valoare, 1, MPI_INT, destinatie, tag, MPI_COMM_WORLD);
    }


    MPI_Gather(&valoare, 1, MPI_INT, A, 1, MPI_INT, radacina, MPI_COMM_WORLD);

    if (rank == radacina) //radacina afiseaza
    {
        printf("A=[ ");
        for(i=0;i<dim;i++){
            printf("%d ",A[i]);
        }
        printf("]");
    }


    
    MPI_Finalize();
    return 0;
}