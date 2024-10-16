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

void reducere(int* A,int dim){
    int n_frunze=dim/2;
    int m=log2n(n_frunze);
    int k, j;
    for (k = m - 1; k >= 0; --k){
#pragma omp parallel for
        for (j = (1 << k); j <= (1 << (k + 1)) - 1; ++j){
            A[j]=A[2*j] + A[2*j+1];
        }
    }


}

void prefixe(int* A, int* B, int dim)
{
    int m = log2n(dim/2);
    reducere(A, m);

    B[1] = A[1];
   
    int j,k;
    for (k = 1; k <= m; ++k)
    {
       
#pragma omp parallel for 

        for (j = (1 << k); j <= (1 << (k + 1)) - 1; ++j){
            if (j % 2 == 1){
                B[j] = B[(j-1)/2];
            }
            else{
                B[j] = B[j/2]-A[j+1];
            }
        }
    }
}

int main(int argc, char* argv[]){

 
    int dim=16;
    int *A = (int*) malloc(dim * sizeof(int));
    int i=0;
    for(i;i<dim/2;i++){
        A[i]=0;
    }
    A[8] = 3; A[9] = 7; A[10] = 8; A[11] = 3; A[12] = 9; A[13] = 2; A[14] = 3; A[15] = 1;
    int* B = (int*) malloc(dim * sizeof(int));

    
    prefixe(A, B, dim);

     printf("B=[ ");
    for(i=0;i<dim;i++){
        printf("%d ",B[i]);
    }
    printf("]");
    return 0;
}