/**
 * Questão 04
 * @version 0.1.0
 * 
 */

/*----- includes -----*/
#include <stdio.h>
#include <stdlib.h>
#include <iostream>
#include <string>
#include <iomanip>
#include <cmath>
#include <iostream>

using namespace std;

int **AlocarMatriz(int tamanho)
{
    int **matriz;

    //alocar linhas
    matriz = (int**) calloc (tamanho, sizeof(int*));

    //alocar colunas
    for(int j = 0; j < tamanho; j++)
    { matriz[j] = (int*) calloc (tamanho, sizeof(int)); }

    return(matriz);
}//end AlocarMatriz()

int **FreeMemoria(int tamanho, int **matriz)
{
    //liberar linhas
    for(int i = 0; i < tamanho; i++)
    { free(matriz[i]); }

    free(matriz);

    return(NULL);
}//end FreeMemoria()

int funcaoUm(int i)
{ return(i); }

void funcaoDois(int *array, int tamanho)
{
    cout << "Mostra valores: " << endl;
    for(int i = 0; i < tamanho; i++)
    { cout << array[i] << endl; }
}//end funcaoDois()

int **funcaoTres(int ordem, int **matriz)
{
    //preencher matriz
    for(int i = 0; i < ordem; i++)
    {
        for(int j = 0; j < ordem; j++)
        { matriz[i][j] = i+j; }
    }//end for

    return(matriz);
}//end funcaoTres()

int main()
{
    int numTermos;
    cout << "Digite o número de termos: " << endl;
    cin >> numTermos;
    cout << "Após chamada da primeira função, mostrar valores: " << endl;
    cout << funcaoUm(numTermos) << endl;

    int numElementos;
    cout << "Digite o número de elementos: " << endl;
    cin >> numElementos;
    int *array = (int*) malloc(numElementos * sizeof(int));
    for(int i = 0; i < numElementos; i++)
    { array[i] = i; }
    funcaoDois(array, numElementos);
    free(array);

    int ordem;
    cout << "Digite a ordem: " << endl;
    cin >> ordem;
    int **matriz = AlocarMatriz(ordem);
    matriz = funcaoTres(ordem, matriz);
    cout << "Mostra valores: " << endl;
    for(int i = 0; i < ordem; i++)
    {
        for(int j = 0; j < ordem; j++)
        { cout << matriz[i][j] << endl; }
    }//end for
    FreeMemoria(ordem, matriz);

    return 0;
}//end main()