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

void afisareVect(double arr[], int dim){
	int i;
    printf("[ ");
	for (i = 0; i < dim; i++){
		printf("%.2lf ", arr[i]);
	}

	printf(" ]\n");
}
void afisareMatrice(double a[3][3], int l, int c)
{
    int i, j;
    for (i = 0; i < l; ++i)
    {
        for (j = 0; j < c; ++j)
        {
            printf(" %.2lf ", a[i][j]);
        }
        printf("\n");
    }
    printf("\n");
}

int main(int argc, char* argv[])
{
   int np=3;
    int radacina=0; 
    int i;

    double A[3][3] = { 
		{1, -1, 1}, 
		{1, 1, 1}, 
		{-1, 1, 1} 
	};
    double B[3]={1,3,-3}; 
 
    int rank, tag=99;
    
    MPI_Status status; 
    MPI_Init(&argc, &argv);
    MPI_Comm_rank(MPI_COMM_WORLD, &rank);
    MPI_Comm_size(MPI_COMM_WORLD, &np);


    int k,j;
    for(k=0;k<np-1;++k){
        if(rank==k){
            for(j=k+1;j<np-1;++j){
                A[k][j]/=A[k][k];

            }

            B[k]/=A[k][k];
            A[k][k]=1;
            for(int i = k+1; i<np; i++){
                MPI_Send(A[k]+i, np, MPI_DOUBLE, i, 0, MPI_COMM_WORLD);
                MPI_Send(B+k, 1, MPI_DOUBLE, i, 0, MPI_COMM_WORLD);
            }
          
        }else if(rank > k){
            MPI_Recv(A[k], np, MPI_DOUBLE, k, 0, MPI_COMM_WORLD, &status);
            MPI_Recv(B+k, 1, MPI_DOUBLE, k, 0, MPI_COMM_WORLD, &status);
            for(int j = k+1; j<np;j++){
                A[rank][j] -= A[rank][k] * A[k][j];
            }
            B[rank] -= A[rank][k] * B[k];
            A[rank][k] = 0;
        }
      
    }
    
   
    if(rank == np-1){
        afisareMatrice(A,np,np);
        afisareVect(B,np);
    }

    MPI_Finalize();

    return 0;
}





