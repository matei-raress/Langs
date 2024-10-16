#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <mpi.h>
#include <math.h>


void afisareVect(int arr[], int dim){
	int i;
    printf("[ ");
	for (i = 0; i < dim; i++){
		printf("%d ", arr[i]);
	}

	printf(" ]\n");
}

#define NONLIDER 0
#define LIDER 1
int main(int argc, char* argv[])
{
   int np=8;
    int radacina=0; 
    int statut=NONLIDER;
    int rank, tag=99;
    MPI_Status status; 
    MPI_Init(&argc, &argv);
    MPI_Comm_rank(MPI_COMM_WORLD, &rank);
    MPI_Comm_size(MPI_COMM_WORLD, &np);

    int id=rank;
    int lider_id;
    int dreapta=(rank+1)%np, stanga=(rank-1)%np;
    int recv_id;
    

    MPI_Send(&id, 1 , MPI_INT, dreapta ,NONLIDER, MPI_COMM_WORLD);

    
    while(1){
        MPI_Recv( &recv_id , 1 , MPI_INT, stanga , MPI_ANY_TAG , MPI_COMM_WORLD, &status);
      if(status.MPI_TAG == NONLIDER){

            if(recv_id==rank){
                statut=LIDER;
                lider_id=rank;
                id=rank;
                MPI_Send(&rank, 1 , MPI_INT, dreapta ,LIDER, MPI_COMM_WORLD);
            }
            if(recv_id>rank){ 
                lider_id=recv_id;
                MPI_Send(&recv_id, 1 , MPI_INT, dreapta ,NONLIDER, MPI_COMM_WORLD);
                }
            }
    if(status.MPI_TAG==LIDER){
            lider_id=recv_id;
            MPI_Send(&lider_id, 1 , MPI_INT, dreapta ,LIDER, MPI_COMM_WORLD);
            break;
        }
    }
    
    printf("Lider  %d",lider_id);
    MPI_Finalize();

    return 0;
}



