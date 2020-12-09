/**
 * Questão 03
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

int main()
{
    int tamanhoMatriz;
    
    cout << "Digite o tamanho da matriz: (Obs.: Deve ser um número > 0)" << endl;
    cin >> tamanhoMatriz;

    int **matriz;

    matriz = AlocarMatriz(tamanhoMatriz);

    cout << "Insira números inteiros na matriz: " << endl;
    for(int i = 0; i < tamanhoMatriz; i++)
    {
        for(int j = 0; j < tamanhoMatriz; j++)
        { cin >> matriz[i][j]; }
    }//end for

    cout << "Porcentagem de elementos ímpares na diagonal secundária: " << endl;
    double quantidade = 0;
    int k = tamanhoMatriz - 1;
    for(int i = 0; i < tamanhoMatriz && k >= 0; i++, k--)
    {
        if(matriz[i][k] % 2 == 1)
        { quantidade = quantidade + 1; }
    }//end for

    double tamMatriz = tamanhoMatriz;

    double porcentagem = (quantidade/tamMatriz) * 100;
    printf("%0.2lf", porcentagem);
    cout << "%" << endl;

    cout << "Maior elemento par abaixo da diagonal secundária: " << endl;
    int maiorElementoPar = 0;
    for(int i = tamanhoMatriz-1; i >= 1; i--)
    {
        for(int j = i-(i-1); j <= i; j++)
        { 
            if(matriz[i][j] % 2 == 0 && matriz[i][j] > maiorElementoPar)
            { maiorElementoPar = matriz[i][j]; }
        }//end for
    }//end for

    if(maiorElementoPar == 0)
    { cout << "Não existe elementos pares abaixo da diagonal secundária." << endl; }
    else
    { cout << maiorElementoPar << endl; }
    
    FreeMemoria(tamanhoMatriz, matriz);

    return 0;
}//end main()