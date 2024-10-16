#include <stdio.h>
#include <omp.h>
#include <stdlib.h>
#include <string.h>

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


void afisareMatrice(int **a, int l, int c)
{
    int i, j;
    for (i = 0; i < l; ++i)
    {
        for (j = 0; j < c; ++j)
        {
            printf(" %d ", a[i][j]);
        }
        printf("\n");
    }
    printf("\n");
}


void reducere(int **A,int j,int dim){
    int m=log2n(dim);
    int k, i;
    for (k = m - 1; k >= 0; --k){
#pragma omp parallel for

        for (i = (1<<k); i <= (1<<(k+1))-1; ++i){
            A[i-1][j] = A[2*i-1][j] + A[2*i][j];
        }
    }
}



int main(int argc, char* argv[]){
    int dim=4;
    int *A = (int*) malloc(dim * sizeof(int));
    int *P = (int*) malloc(dim * sizeof(int));
    int *aux = (int*) malloc(dim * sizeof(int));
    int **R = (int**) malloc((2 * dim - 1) * sizeof(int*));
    int i=0,j=0;

    for (i; i < 2 * dim - 1; ++i){
        R[i] = (int*) malloc(dim * sizeof(int));

        for (j = 0; j < dim; j++)
		{
			R[i][j] = 0;
		}
    }

    A[0] = 2; A[1] = 6; A[2] = 3; A[3] = 8;
    
	omp_set_num_threads(dim);
    afisareVect(A,dim);
//////////////////////////
#pragma omp parallel for private(j,i)
    for (j = 0; j <= dim - 1; ++j)
    {
        for (i = 0; i <= dim - 1; ++i)
        {
            if (A[i] < A[j]){
                R[i+dim-1][j] = 1;
            }
            else{
                R[i+dim-1][j] = 0;
            }
        }
    }

    afisareMatrice(R, 2 * dim - 1, dim);

	for (j = 0; j <= dim - 1; j++){
        reducere(R,j, dim);
        P[j] = R[0][j];

	}



    printf("Matrice redusa\n");
	afisareMatrice(R, 2 * dim - 1, dim);



#pragma omp parallel for
	for (j = 0; j <= dim - 1; j++)
	{
        aux[P[j]] = A[j];
	}

	afisareVect(aux,dim);



/////////////////////////
    

    return 0;
}