#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <mpi.h>
#include <math.h>


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

int getRank(MPI_Comm comm, int i, int j){
    int coords[2] = {i, j};
    int rank;
    MPI_Cart_rank(comm, coords, &rank);
    return rank;
}

#define dim 4
int main(int argc, char* argv[])
{
    int rank, np=16,tag=99;
    MPI_Init(&argc, &argv);
    MPI_Comm_rank(MPI_COMM_WORLD, &rank);
    MPI_Comm_size(MPI_COMM_WORLD, &np);


     int A[dim][dim] = {{1,2,3,4},
                        {5,6,7,8},
                      {7,8,9,5},
                      {1,2,2,1}};
                    
    int B[dim][dim] = {{4,4,55,25},
                      {6,3,1,12},
                      {13,1,0,9},
                      {3,4,13,58}};

    MPI_Comm comm;
    MPI_Status status; 
    int coords[2];
    int periods[2]={1,1};
    int dims[2]={dim,dim};
    MPI_Cart_create(MPI_COMM_WORLD, 2, dims, periods, 1, &comm);
    MPI_Cart_coords(comm, rank, 2, coords);

    int k,i=coords[0],j=coords[1];
    double M1=A[i][j],M2=B[i][j];

    /////////////////////////////aliniere initiala
    for (k = 1; k < i; ++k) {
        // trimit M1 catre p i j-1
        int vecin_stg=getRank(comm,i,j-1);
        MPI_Send(&M1, 1, MPI_INT, vecin_stg, tag, MPI_COMM_WORLD);
        //printf("%d trimis mesaj %d catre %d\n", rank, M1, vecin_stg);

        // primesc M1 de la p i j+1
        int vecin_drt=getRank(comm,i,j+1);
        MPI_Recv(&M1, 1, MPI_INT, vecin_drt, tag, MPI_COMM_WORLD, &status);
        //printf("%d primesc mesaj %d catre %d\n", rank, M1, vecin_drt);
        
    }
    for (k = 1; k < j; ++k) {
        // trimit M1 catre p i-1 j
        int vecin_sus=getRank(comm,i-1,j);
        MPI_Send(&M2, 1, MPI_INT, vecin_sus, tag, MPI_COMM_WORLD);
        //printf("%d trimis mesaj %d catre %d\n", rank, M1, vecin_sus);

        // primesc M1 de la p i+1 j
        int vecin_jos=getRank(comm,i+1,j);
        MPI_Recv(&M2, 1, MPI_INT, vecin_jos, tag, MPI_COMM_WORLD, &status);
        //printf("%d primesc mesaj %d catre %d\n", rank, M1, vecin_jos);
        
    }
    ////////////////////////////

    
    double M3= M1*M2 ;//inmultirematr
    for(k=0;k<3;++k){


        // trimit M1 catre p i j-1
        int vecin_stg=getRank(comm,i,j-1);
        MPI_Send(&M1, 1, MPI_INT, vecin_stg, tag, MPI_COMM_WORLD);
        //printf("%d trimis mesaj %d catre %d\n", rank, M1, vecin_stg);

        // primesc M1 de la p i j+1
        int vecin_drt=getRank(comm,i,j+1);
        MPI_Recv(&M1, 1, MPI_INT, vecin_drt, tag, MPI_COMM_WORLD, &status);
        //printf("%d primesc mesaj %d catre %d\n", rank, M1, vecin_drt);

         int vecin_sus=getRank(comm,i-1,j);
        MPI_Send(&M2, 1, MPI_INT, vecin_sus, tag, MPI_COMM_WORLD);
        //printf("%d trimis mesaj %d catre %d\n", rank, M1, vecin_sus);

        // primesc M1 de la p i+1 j
        int vecin_jos=getRank(comm,i+1,j);
        MPI_Recv(&M2, 1, MPI_INT, vecin_jos, tag, MPI_COMM_WORLD, &status);
        //printf("%d primesc mesaj %d catre %d\n", rank, M1, vecin_jos);
        M3+= M1*M2 ;
    }
    printf("rank %d are %lf\n", rank, M3);

    MPI_Finalize();

    return 0;
}