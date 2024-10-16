#include <stdio.h>
#include "mpi.h"
#include <stdlib.h>
#include <string.h>

int main(int argc, char* argv[]){

    int rank;
    int p; 
    int sursa; 
    int destinatie; 
    int tag = 99; 
    char mesaj[50] = "Hello world!"; 
    MPI_Status status; 
    MPI_Comm graph;

    int radacina=4;

    int nrNoduri=9;
    int index[] = {1, 2, 5, 7, 10, 11, 14, 15, 16}; 
    int edges[] = {4, 6, 4, 7, 8, 4, 6, 0, 2, 3, 6, 1, 3, 5, 2, 2}; 

    MPI_Init(&argc,&argv);
    MPI_Comm_rank(MPI_COMM_WORLD, &rank);
    MPI_Comm_size(MPI_COMM_WORLD, &p);
    MPI_Graph_create(MPI_COMM_WORLD, nrNoduri, index, edges, 0, &graph);

    int nrVecini;
    MPI_Graph_neighbors_count(graph,rank,&nrVecini);
    int* vecini = (int*) malloc(nrVecini * sizeof(int));
    MPI_Graph_neighbors(graph,rank,nrVecini,vecini);


    int i;
    


    if(nrVecini==1){//frunza trimit in sus
        destinatie = vecini[0]; 
        MPI_Send(&rank, 1, MPI_INT, destinatie, 99, graph);
        printf("Nod %d trimit %d la %d.\n", rank, rank, destinatie);

    }
    else if(rank != radacina){//nod trimit la parinte
        int suma_locala=0; 
        int sumaVecini = 0;

        for (i = 0; i < nrVecini; ++i){
            sumaVecini += vecini[i];
        }
        for (i = 0; i < nrVecini-1; ++i) {
            MPI_Recv(&sursa, 1, MPI_INT, MPI_ANY_SOURCE, tag, graph, &status);
            sumaVecini -= status.MPI_SOURCE; //calculez din vecini care e parintele
            suma_locala+=sursa;
        }

       
        destinatie = sumaVecini; 
        suma_locala+=rank;
        MPI_Send(&suma_locala, 1, MPI_INT, destinatie, tag, graph);
        printf("Nod %d trimite suma_locala %d la %d.\n", rank, suma_locala, destinatie);

 
    }
    else{//radacina primesc

            int suma_totala = rank; 

            for (i = 0; i < nrVecini; ++i) {
                MPI_Recv(&sursa, 1, MPI_INT, vecini[i], 99, graph, &status);
                suma_totala += sursa;
            }

            printf("Nod %d are suma totala %d.\n", rank, suma_totala);
    
    }

    free(vecini);
    MPI_Comm_free(&graph);
    MPI_Finalize();
    return 0;
}
