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
void afisareVect(int arr[], int dim){
	int i;
    printf("[ ");
	for (i = 0; i < dim; i++){
		printf("%d ", arr[i]);
	}

	printf(" ]\n");
}

int main(int argc, char* argv[])
{
    int rank, np=16;
    int A[]={10, 20, 5, 9, 3, 8, 12, 14, 90, 0, 60, 40, 23, 35, 95, 1};

    MPI_Status status; 
    MPI_Init(&argc, &argv);
    MPI_Comm_rank(MPI_COMM_WORLD, &rank);
    MPI_Comm_size(MPI_COMM_WORLD, &np);

    int curr = A[rank];
    int d = log2n(np);
    //i determina etapa, j det subetapa
    for(int i = 0; i<d; i++){
        for(int j=i; j>=0; j--){
            int v;
            MPI_Send(&curr,1,MPI_INT, rank^(1<<j), 0, MPI_COMM_WORLD);
            
           // printf("Nod %d trimit %d la %d.\n", rank, curr, rank^(1<<j));
            
            MPI_Recv(&v, 1, MPI_INT, rank^(1<<j), 0, MPI_COMM_WORLD, &status);
            
            //printf("Nod %d primesc %d de la %d.\n", rank, curr, rank^(1<<j));

            if((rank>>(i+1))&1){//max sus
                if((rank>>j)&1){
                    if(curr>v){
                        curr=v;
                    }
                }else{
                    if(curr<v){
                        curr=v;
                    }
                }
            }else{ //min sus
                if((rank>>j)&1){
                  if(curr<v){
                        curr=v;
                    }
                }else{
                    if(curr>v){
                        curr=v;
                    }
                }
            }
        }
    }
    printf("rank %d val %d \n",rank,curr);
    MPI_Finalize();

    return 0;
}