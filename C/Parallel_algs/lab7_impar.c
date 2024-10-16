#include <stdio.h>
#include <mpi/mpi.h>
#include <stdlib.h>
#include <string.h>

void afisareVect(int arr[], int dim){
	int i;
    printf("[ ");
	for (i = 0; i < dim; i++){
		printf("%d ", arr[i]);
	}

	printf(" ]\n");
}


int max(int x, int y){ return x > y ? x: y ;}
int min(int x, int y){ return x > y ? y: x; }

int main(int argc, char* argv[]){
    int np=8;
    int *A = (int*) malloc(np * sizeof(int));
    int radacina=0;
    int rank; 
    MPI_Status status; 
    int destinatie,sursa; 
    int tag = 99; 
    int r,v;
    
    MPI_Init(&argc, &argv);
    MPI_Comm_rank(MPI_COMM_WORLD, &rank);
    MPI_Comm_size(MPI_COMM_WORLD, &np);
    int i=0;

    A[0] = 3; A[1] = 7; A[2] = 8; A[3] = 3; A[4] = 9; A[5] = 2; A[6] = 3; A[7] = 1;

    MPI_Scatter(A, 1, MPI_INT, &r, 1, MPI_INT, radacina, MPI_COMM_WORLD);


    int faza;
    for(faza=1; faza<=np;++faza){
         // i = rank
        printf("Faza %d \n",faza);
        if (faza % 2 == 1 && rank >= 0 && rank <= 2 * (np / 2) - 1){// faza este impara;
            if (rank % 2 == 0) {
                // trimit r la pi+1
                destinatie = rank + 1;
                MPI_Send(&r, 1, MPI_INT, destinatie, tag, MPI_COMM_WORLD);
                printf("rank %d trimit %d la %d.\n", rank, r, destinatie);

                //primesc in v de la pi+1
                sursa = rank + 1;
                MPI_Recv(&v, 1, MPI_INT, sursa, tag, MPI_COMM_WORLD, &status);
                printf("rank %d primit %d de la %d.\n", rank, v, sursa);
            
                r=min(r,v);
            }
            else{
                // trimit r la pi-1
                destinatie = rank - 1;
                MPI_Send(&r, 1, MPI_INT, destinatie, tag, MPI_COMM_WORLD);
                printf("rank %d trimit %d la %d.\n", rank, r, destinatie);
                // primesc v de la pi-1  
                sursa = rank - 1;
                MPI_Recv(&v, 1, MPI_INT, sursa, tag, MPI_COMM_WORLD, &status);
                printf("rank %d primit %d de la %d.\n", rank, v, sursa);
             
                r=max(r,v);
            }
        }

        if (faza % 2 == 0 && rank >= 1 && rank <= 2 * ((np - 1) / 2)){// faza este para;
            if (rank % 2 == 1){
                // trimit r catre pi+1
                destinatie = rank + 1;
                MPI_Send(&r, 1, MPI_INT, destinatie, tag, MPI_COMM_WORLD);
                printf("rank %d trimit %d la %d.\n", rank, r, destinatie);

                // primesc v de la pi+1  
                sursa = rank + 1;
                MPI_Recv(&v, 1, MPI_INT, sursa, tag, MPI_COMM_WORLD, &status);
                printf("rank %d primit %d de la %d.\n", rank, v, sursa);

                r=min(r,v);
            }
            else{
                // trimit r catre pi-1
                destinatie = rank - 1;
                MPI_Send(&r, 1, MPI_INT, destinatie, tag, MPI_COMM_WORLD);
                printf("rank %d trimit %d la %d.\n", rank, r, destinatie);

                // primesc v de la pi-1  
                sursa = rank - 1;
                MPI_Recv(&v, 1, MPI_INT, sursa, tag, MPI_COMM_WORLD, &status);
                printf("rank %d primit %d de la %d.\n", rank, v, sursa);

                r=max(r,v);
            }
        }
       
    }

    MPI_Gather(&r,1, MPI_INT, A, 1, MPI_INT, radacina, MPI_COMM_WORLD);

    if (rank == radacina) 
    {
        printf("Sortat sper:\n");
        afisareVect(A, np);
    }
    
    MPI_Finalize();
    return 0;
}