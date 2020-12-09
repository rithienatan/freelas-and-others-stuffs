/**
 * Questão 01
 * @version 0.1.0
 * 
 */

/*----- includes -----*/
#include <stdio.h>
#include <stdlib.h>
#include <iostream>
#include <string>

using namespace std;

/**
 * Retorna a soma dos n primeiros termos de uma sequencia
 * 
 * @param quantTermos recebe a quantidade de termos a ser mostrados
 * 
 * @return retorna um número inteiro
 */
int sequencia(int quantTermos)
{
    int resp = 0;
    int anterior = 0;

    int fibonnaci = 0;
    int numA = 1;
    int numB = 0;

    int primeiroTermo = 3;

    if(quantTermos == 1)
    { resp = 3; }
    else if(quantTermos == 2)
    { resp = 6; }
    else
    {
        if(quantTermos > 0)
        {
            resp = 6; anterior = 3; fibonnaci = 1;

            for(int i = 2; i < quantTermos; i++)
            {
                anterior = anterior + fibonnaci;
                resp = resp + anterior;
                fibonnaci = numA + numB;
                numB = numA;
                numA = fibonnaci;
            }//end for
        }//end if
    }//end if
    

    return(resp);
}//end sequencia()

int main()
{
    int quantTermos;
    
    cout << "Digite a quantidade de termos que serão somados: (Obs.: Deve ser um número >= 0)" << endl;
    cin >> quantTermos;

    cout << sequencia(quantTermos) << endl;

    return 0;
}//end main()