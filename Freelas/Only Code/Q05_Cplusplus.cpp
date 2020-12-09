/**
 * Questão 05
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
#include <array>
//#include <vector>

using namespace std;

//----- class Funcionario -----//
class Funcionario
{
    private:
        int nInscricao;
        string nome;
        int horasNormais;
        int horasExtras;
        int idade;
        int classe;

    public:
        /**
         * Construtor
         */
        Funcionario(int nInscricao, string nome, int horasNormais, int horasExtras, int idade, int classe)
        {
            this->nInscricao = nInscricao;
            this->nome = nome;
            this->horasNormais = horasNormais;
            this->horasExtras = horasExtras;
            this->idade = idade;
            this->classe = classe;
        }//end construtor

        //----- get(s) and set(s) -----//
        int getnInscricao()
        { return(this->nInscricao); }
        void setnInscricao(int nInscricao)
        { this->nInscricao = nInscricao; }

        string getnome()
        { return(this->nome); }
        void setnome(string nome)
        { this->nome = nome; }

        int gethorasNormais()
        { return(this->horasNormais); }
        void sethorasNormais(int horasNormais)
        { this->horasNormais =  horasNormais; }

        int gethorasExtras()
        { return(this->horasExtras); }
        void sethorasExtras(int horasExtras)
        { this->horasExtras = horasExtras; }

        int getidade()
        { return(this->idade); }
        void setidade(int idade)
        { this->idade = idade; }

        int getclasse()
        { return(this->classe); }
        void setclasse(int classe)
        { this->classe = classe; }
};//end class

//----- class Industria -----//
class Industria
{
    public:
        //----- Cria uma lista de funcionários -----//
        array <Funcionario*, 10> listaFuncionarios;

        /**
         * Calcula o valor do INSS
         * 
         * @param salario Recebe o salário do funcionário
         * 
         * @return Retorna o valor do INSS
         */
        float inss(float salario)
        {
            float valor;

            if(salario <= 1045.00)
            { valor = salario*0.075; }
            else if(salario > 1045.00 && salario <= 2089.60)
            { valor = salario*0.09; }
            else if(salario > 2089.60 && salario <= 3134.40)
            { valor = salario*0.12; }
            else if(salario > 3134.40 && salario <= 6101.06)
            { valor = salario*0.14; }
            else
            { valor = salario*0.16; }

            return(valor);
        }//end inss()

        /**
         * Calcula o valor do IPRPF
         * 
         * @param salario Recebe o salário do funcionário
         * 
         * @return Retorna o valor do IPRPG
         */
        float iprpf(float salario)
        {
            float valor;

            if(salario < 2826.66)
            { valor = 0.0; }
            else if(salario >= 2826.66 && salario < 3751.06)
            { valor = salario*0.15; }
            else if(salario >= 3751.06 && salario <= 4664.68)
            { valor = salario*0.225; }
            else
            { valor = salario*0.275; }

            return(valor);
        }//end iprpf()

        /**
         * Define o plano de saúde
         * 
         * @param idade Recebe a idade do funcionário
         * 
         * @return Retorna o valor do plano de saude
         */
        int planoSaude(int idade)
        {
            int plano;

            if(idade < 18)
            { plano = 60; }
            else if(idade >= 18 && idade < 40)
            { plano = 80; }
            else if(idade >= 40 && idade < 60)
            { plano = 120; }
            else
            { plano = 300; }

            return(plano);
        }//end planoSaude()

        /**
         * Imprime o contra cheque dos funcionarios
         * 
         * @param salarioReferencia Recebe o salario referência determinado anteriormente
         */
        void contracheque(float salarioReferencia)
        {
            for(int i = 0; i < listaFuncionarios.size(); i++)
            {
                float salario = 0;

                //----- calcular salário com base na classe do funcionário -----//
                if(listaFuncionarios[i]->getclasse() == 1)
                { salario = salarioReferencia * 1.3; }
                else if(listaFuncionarios[i]->getclasse() == 2)
                { salario = salarioReferencia * 1.9; }
                else if(listaFuncionarios[i]->getclasse() == 3)
                { salario = salarioReferencia * 2.2; }
                else if(listaFuncionarios[i]->getclasse() == 4)
                { salario = salarioReferencia * 2.7; }

                float salarioExtra = (salario/listaFuncionarios[i]->gethorasNormais()) * 1.3 * listaFuncionarios[i]->gethorasExtras();
                float salarioBruto = salario;
                float planoDeSaudeValor = planoSaude(listaFuncionarios[i]->getidade());
                float inssValor = inss(salarioBruto);
                float iprpfValor = iprpf(salarioBruto);

                //----- imprimir informações -----//
                cout << "Número de inscrição: ";
                cout << listaFuncionarios[i]->getnInscricao() << "\n";

                cout << "Nome: ";
                cout << listaFuncionarios[i]->getnome() << "\n";

                cout << "Salário horas normais: ";
                cout << "R$ ";
                printf("%.2f por hora\n", salario/listaFuncionarios[i]->gethorasNormais());

                cout << "Salário horas extras: ";
                cout << "R$ ";
                printf("%.2f \n", salarioExtra);

                cout << "Dedução INSS: ";
                cout << "R$ ";
                printf("%.2f \n", inssValor);

                cout << "Salário líquido: ";
                cout << "R$ ";
                printf("%.2f \n", salarioBruto + salarioExtra - planoDeSaudeValor - iprpfValor - inssValor);

                cout << "Salário bruto: ";
                cout << "R$ ";
                printf("%.2f \n", salarioBruto);

                cout << "Iprpf: ";
                cout << "R$ ";
                printf("%.2f \n", iprpfValor);

                cout << "Plano de Saúde: ";
                cout << "R$ ";
                printf("%.2f \n", planoDeSaudeValor);

                cout << "-----------------------------------------------------" << endl;
            }//end for
        }//end contracheque()
};//end class

/**
 * Metodo main
 */
int main()
{
    //----- leitura do salário referência -----//
    float salarioReferencia;

    cout << "Digite o salário de referência: " << endl;
    cin >> salarioReferencia;

    while(salarioReferencia < 0)
    {
        cout << "O salário de referência não pode ser negativo, digite novamente: " << endl;
        cin >> salarioReferencia;
    }//end while

    //----- leitura dos dados de cada funcionário -----//
    Industria industria;

    int nInscricao;
    string nome;
    int horasNormais;
    int horasExtras;
    int idade;
    int classe;

    for(int i = 0; i < industria.listaFuncionarios.size(); i++)
    {
        cout << "Entre com os dados do " << i+1 << "º funcionário." << endl;

        cout << "Entre com número de inscrição: " << endl;
        cin >> nInscricao;
        while(nInscricao < 0)
        {
            cout << "O número de inscrição deve ser um número maior que 0! Digite novamente: " << endl;
            cin >> nInscricao;
        }//end while

        cout << "Digite o nome: " << endl;
        cin >> nome;

        cout << "Entre com as horas normais trabalhadas: " << endl;
        cin >> horasNormais;
        while(horasNormais < 0)
        {
            cout << "Horas devem ser maior ou igual a 0! Digite novamente: " << endl;
            cin >> horasNormais;
        }//end while
        
        cout << "Entre com as horas extras: " << endl;
        cin >> horasExtras;
        while(horasExtras < 0)
        {
            cout << "Horas extras devem ser maior ou igual a 0! Digite novamente: " << endl;
            cin >> horasExtras;
        }//end while

        cout << "Digite a idade: " << endl;
        cin >> idade;
        while(idade < 0)
        {
            cout << "Idade negativa! Digite novamente: " << endl;
            cin >> idade;
        }//end while

        cout << "Entre com a classe: " << endl;
        cin >> classe;
        while(classe < 1 || classe > 4)
        {
            cout << "A classe deve ser um número entre entre 1 e 4: " << endl;
            cin >> classe;
        }//end while

        industria.listaFuncionarios[i] = new Funcionario(nInscricao, nome, horasNormais, horasExtras, idade, classe);
    }//end for

    cout << "----- Mostrar contra-cheque de todos os funcionários -----" << endl;
    industria.contracheque(salarioReferencia);

    return(0);
}//end main