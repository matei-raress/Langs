#include <stdio.h>
#include <mpi/mpi.h>
#include <stdlib.h>
#include <string.h>
//9
int main(int argc, char* argv[]){

    int rank;
    int p; 
    int tag = 99; 
    MPI_Status status; 
    MPI_Comm graph;
    int d=3;//diametru maxim

    int nrNoduri=9;
    int edges[] = {1,4,5,6,0,6,4,7,8,5,6,8,0,2,7,0,3,7,0,1,3,2,8,4,5,2,3,7};
    int index[] = {4,6,9,12,15,18,21,25,28}; 
    // 0: 1,4,5,6 // 1: 0,6 // 2: 4,7,8// 3:5,6,8 // 4: 0,2,7// 5:0,3,7//6:0,1,3//7:2,4,5,8//8:2,3,7
    // 4;             6;           9;      12;          15;        18;      21,     25,     28

    
    MPI_Init(&argc,&argv);
    MPI_Comm_rank(MPI_COMM_WORLD, &rank);
    MPI_Comm_size(MPI_COMM_WORLD, &p);
    MPI_Graph_create(MPI_COMM_WORLD, nrNoduri, index, edges, 0, &graph);

    int nrVecini;
    MPI_Graph_neighbors_count(graph,rank,&nrVecini);
    int* vecini = (int*) malloc(nrVecini * sizeof(int));
    MPI_Graph_neighbors(graph,rank,nrVecini,vecini);
    int max=rank;
    int val;
    for(int i=0; i<d;i++){
        for(int j=0; j<nrVecini;j++){
            MPI_Send(&max,1,MPI_INT,vecini[j],0,graph);
        }
        for(int j=0; j<nrVecini;j++){
            MPI_Recv(&val, 1,MPI_INT,vecini[j],0,graph,&status);
       
            if (max < val){
                max = val;
            }
        }
      
    }
       if(max==rank)
        {
            printf("Lider  %d\n", rank);
        }
     

 
    free(vecini);
    MPI_Comm_free(&graph);
    MPI_Finalize();
    return 0;
}
