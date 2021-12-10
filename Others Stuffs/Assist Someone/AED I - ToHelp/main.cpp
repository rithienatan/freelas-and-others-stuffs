#include <iostream>
#include <cmath>

using namespace std;

void metodo01()
{}

void metodo02()
{}

void metodo03()
{}

void metodo04()
{}

//---------- Questão 05 ----------
/**
 * @brief Converte um número binário para inteiro
 * 
 * @param tamArray 
 * @return int
 */
int binToDecimal(int tamArray)
{
    int resp = 0, i = 0, entrada = 0;
    int arrayBin[tamArray];

    do
    {
        cout << "Entre com valores 0 ou 1:" << "\n";
        cin >> entrada;

        if(entrada == 0 || entrada == 1)
        { arrayBin[i] = entrada; i++; }
    }
    while(i < tamArray);

    for(int j = 0; j < tamArray; j++)
    { resp = resp + (arrayBin[j] * pow(2, tamArray - j - 1)); }

    return(resp);
}//end binToDecimal()

/**
 * @brief Resolve a questão 05
 */
void metodo05()
{
    //ler o tamanho do Array
    int tamArray;
    do
    {
        cout << "Digite o tamanho do Array binário: \n";
        cin >> tamArray;
    }
    while(tamArray < 0);

    int resposta = binToDecimal(tamArray);

    cout << "Valor em decimal: " << resposta << "\n";
}//end metodo05()
//---------- End Questão 05 ----------

//---------- Questão 06 ----------
/**
 * @brief Monta e mostra a matriz trigonal crescente
 * 
 * @param n matriz quadrada
 */
void mostrarMatriz06(int n)
{
    int matriz[n][n];

    // Preencher matriz com 0
    for(int i = 0; i < n; i++)
    {
        for(int j = 0; j < n; j++)
        { matriz[i][j] = 0; }
    }//end for

    if(n == 1)
    { matriz[0][0] = 1; }
    else if(n == 2)
    { matriz[0][0] = 1; matriz[1][1] = 2; }
    else
    {
        int valor = 1, colunaCount = 2, j;

        for(int i = 0; i < n; i++)
        {
            if(i == 0)
            { matriz[0][0] = 1; matriz[0][1] = 2; valor = 2; }
            else if(i == n-1)
            { matriz[i][i-1] = valor + 1; matriz[i][i] = valor + 2; }
            else
            { matriz[i][i-1] = valor + 1; matriz[i][i] = valor + 2; matriz[i][i+1] = valor + 3; valor = valor + 3; }
        }//end for
    }//end if

    // Mostrar matriz
    for(int i = 0; i < n; i++)
    {
        for(int j = 0; j < n; j++)
        { cout << matriz[i][j] << " "; }

        cout << "\n";
    }//end for
}//end mostrarMatriz06()

/**
 * @brief Resolve a questão 06
 */
void metodo06()
{
    int n;

    do
    {
        cout << "Digite o número para determinar o tamanho da matriz quadrada: " << "\n";
        cin >> n;
    }
    while(n < 0);

    mostrarMatriz06(n);
}//end metodo06()
//---------- End Questão 06 ----------

//---------- Questão 07 ----------
/**
 * @brief Monta e mostra a matriz trigonal decrescente
 * 
 * @param n matriz quadrada
 */
void mostrarMatriz07(int n)
{
    int matriz[n][n];
    int matrizDecrescente[n][n];

    // Preencher matriz com 0
    for(int i = 0; i < n; i++)
    {
        for(int j = 0; j < n; j++)
        { matriz[i][j] = 0; matrizDecrescente[i][j] = 0; }
    }//end for

    // inseri os valores na matriz
    if(n == 1)
    { matriz[0][0] = 1; }
    else if(n == 2)
    { matriz[0][0] = 1; matriz[1][1] = 2; }
    else
    {
        int valor = 1, colunaCount = 2, j;

        for(int i = 0; i < n; i++)
        {
            if(i == 0)
            { matriz[0][0] = 1; matriz[0][1] = 2; valor = 2; }
            else if(i == n-1)
            { matriz[i][i-1] = valor + 1; matriz[i][i] = valor + 2; }
            else
            { matriz[i][i-1] = valor + 1; matriz[i][i] = valor + 2; matriz[i][i+1] = valor + 3; valor = valor + 3; }
        }//end for
    }//end if

    // inverte a matriz
    for(int j = 0; j < n; j++)
    {
        for(int i = 0; i < n; i++)
        { matrizDecrescente[i][j] = matriz[n-j-1][n-i-1]; }
    }//end for

    // Mostrar matriz
    for(int i = 0; i < n; i++)
    {
        for(int j = 0; j < n; j++)
        { cout << matrizDecrescente[i][j] << " "; }

        cout << "\n";
    }//end for
}//end mostrarMatriz07()

/**
 * @brief Resolve a questão 07
 */
void metodo07()
{
    int n;

    do
    {
        cout << "Digite o número para determinar o tamanho da matriz quadrada: " << "\n";
        cin >> n;
    }
    while(n < 0);

    mostrarMatriz07(n);
}//end metodo07()
//---------- End Questão 07 ----------

//---------- Questão 08 ----------
/**
 * @brief Monta e mostra a matriz de potencias por linha
 * 
 * @param n matriz quadrada
 */
void mostrarMatriz08(int n)
{
    int matriz[n][n];

    //monta a matriz
    for(int i = 0; i < n; i++)
    {
        for(int j = 0; j < n; j++)
        { 
            if(i == 0)
            { matriz[i][j] = pow(i+1, i); }
            else
            { matriz[i][j] = pow(i+1, j); }
        }
    }//end for

    // Mostrar matriz
    for(int i = 0; i < n; i++)
    {
        for(int j = 0; j < n; j++)
        { cout << matriz[i][j] << " "; }

        cout << "\n";
    }//end for
}//end mostrarMatriz08()

/**
 * @brief Resolve a questão 08
 */
void metodo08()
{
    int n;

    do
    {
        cout << "Digite o número para determinar o tamanho da matriz quadrada: " << "\n";
        cin >> n;
    }
    while(n < 0);

    mostrarMatriz08(n);
}//end metodo08()
//---------- End Questão 08 ----------

//---------- Questão 09 ----------
/**
 * @brief Monta e mostra a matriz de potencias por coluna
 * 
 * @param n matriz quadrada
 */
void mostrarMatriz09(int n)
{
    int matriz[n][n];

    //monta a matriz
    for(int j = 0; j < n; j++)
    {
        for(int i = 0; i < n; i++)
        { 
            if(i == 0)
            { matriz[i][j] = pow(j, j); }
            else
            { matriz[i][j] = pow(j+1, i); }
        }
    }//end for

    // Mostrar matriz
    for(int i = 0; i < n; i++)
    {
        for(int j = 0; j < n; j++)
        { cout << matriz[i][j] << " "; }

        cout << "\n";
    }//end for
}//end mostrarMatriz09()

void metodo09()
{
    int n;

    do
    {
        cout << "Digite o número para determinar o tamanho da matriz quadrada: " << "\n";
        cin >> n;
    }
    while(n < 0);

    mostrarMatriz09(n);
}//end metodo09()
//---------- End Questão 09 ----------

//---------- Questão 10 ----------
class SuperMercado
{
    public:
        string nome;
        int codigo;
        double produtoValor; 
};//end class

/**
 * @brief Resolve questão 10
 */
void metodo10()
{
    int quantSuper;
    do
    {
        cout << "Digite o número de supermercados a serem avaliados: " << "\n";
        cin >> quantSuper;
    }
    while(quantSuper < 3);

    SuperMercado listSuper[quantSuper];
    for(int i = 0; i < quantSuper; i++)
    {
        cout << "Digite o nome do supermercado: " << "\n";
        cin >> listSuper[i].nome; 

        cout << "Digite o código do supermercado: " << "\n";
        cin >> listSuper[i].codigo;

        
        cout << "Digite o preço de um produto: " << "\n";
        cin >> listSuper[i].produtoValor;
    }//end for

    double precoTotal = 0;
    for(int i = 0; i < quantSuper; i++)
    { precoTotal = precoTotal + listSuper[i].produtoValor; }

    double precoMedio = precoTotal/quantSuper;

    for(int i = 0; i < quantSuper; i++)
    { 
        if(listSuper[i].produtoValor < precoMedio)
        {
            cout << "Supermercado: " << listSuper[i].nome << "\n";
            cout << "Tem o produto com o valor abaixo da média: " << listSuper[i].produtoValor << "\n";
        }//end if
    }//end for
}//end metodo10()
//---------- End Questão 10 ----------

/**
 * @brief Main execute
 * 
 * @return int 
 */
int main()
{
    int input;

    cout << "Insira o número da questão de 0 a 9: \n";
    cin >> input;

    switch(input)
    {
        case 0:
            metodo01();
            break;
        case 1:
            metodo02();
            break;
        case 2:
            metodo03();
            break;
        case 3:
            metodo04();
            break;
        case 4:
            metodo05();
            break;
        case 5:
            metodo06();
            break;
        case 6:
            metodo07();
            break;
        case 7:
            metodo08();
            break;
        case 8:
            metodo09();
            break;
        case 9:
            metodo10();
            break;
            
        default:
            cout << "Questão não existente!\n";
            break;
    }

    return(0);   
}//end main