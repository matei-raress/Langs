#include <stdio.h>
#include <mpi/mpi.h>
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


    if(rank==radacina){// daca e radcina trimitem mesaj la toti copii/vecini
        int i;
     
        for (i = 0; i < nrVecini; ++i)  
        {
            destinatie = vecini[i];
            MPI_Send(mesaj, (int)(strlen(mesaj) + 1), MPI_CHAR, destinatie, 99, graph);
            printf("Radacina trimite %s la %d\n", mesaj, destinatie);
        }

    }
    else{
        MPI_Recv( mesaj ,(int)(strlen(mesaj)+1) ,MPI_CHAR , MPI_ANY_SOURCE , 99, graph , &status);
        if(nrVecini>1){ // daca nu e frunza trimitem la vecini
            int i;
            for (i = 0; i < nrVecini; ++i) 
            {
                destinatie = vecini[i];
                MPI_Send(mesaj, (int)(strlen(mesaj) + 1), MPI_CHAR, destinatie, 99, graph);
                printf("Nodul  %d trimite %s la %d\n", rank, mesaj, destinatie);
            
            }   
        }

    }

    free(vecini);
    MPI_Comm_free(&graph);
    MPI_Finalize();
    return 0;
}
