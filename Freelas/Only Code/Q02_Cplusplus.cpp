/**
 * Questão 02
 * @version 0.1.0
 * 
 */

/*----- includes -----*/
#include <stdio.h>
#include <stdlib.h>
#include <iostream>
#include <string>

using namespace std;

int main()
{
    int tamanhoArray;
    
    cout << "Digite o tamanho do array: (Obs.: Deve ser um número >= 3 e <= 100)" << endl;
    cin >> tamanhoArray;

    int *array = (int*) malloc(tamanhoArray * sizeof(int));

    cout << "Digite todos os números inteiros que voçê gostaria de colocar no array: " << endl;
    for(int i = 0; i < tamanhoArray; i++)
    { cin >> array[i]; }

    //seleciona os três primeiros elementos do array
    int primeiro = array[0];
    int segundo = array[1];
    int terceiro = array[2];

    int aux = 0;

    //seleciona os 3 maiores elementos multiplos de 5
    for(int i = 0; i < tamanhoArray; i++)
    { 
        if(array[i] % 5 == 0)
        {
            if(aux < 3)
            {
                if(aux == 0)
                { primeiro = array[i]; }
                else if(aux == 1)
                { segundo = array[i]; }
                else
                { terceiro = array[i]; }
                
                aux = aux + 1;
            }
            else
            {
                if(array[i] > primeiro && array[i] > segundo && array[i] > terceiro)
                { primeiro = array[i]; }
                else if(array[i] > segundo && array[i] > terceiro)
                { segundo = array[i]; }
                else if(array[i] > terceiro)
                { terceiro = array[i]; }
            }//end if
        }//end if
    }//end if

    cout << "Os maiores números são: " << endl;
    cout << primeiro << endl;
    cout << segundo << endl;
    cout << terceiro << endl;

    free(array);

    return 0;
}//end main()