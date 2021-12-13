// Avaliacao 03 - Modelo
// Matricula:
// Nome     :

/*
  Condicoes
  1a. dose - sem restricoes
  2a. dose - 30 dias da primeira
  3a. dose - 150 dias (05 meses) da segunda

  Quest�es
  1a - Quantos n�o poder�o tomar a primeira dose?
  2a - Quantos dever�o tomar a segunda  dose?
  3a - Quantos dever�o tomar a terceira dose?
  4a - Que dose da vacina tem a maior procura?
*/

#include <iostream>
#include <iomanip>
#include <fstream>
#include <string>

using namespace std;

struct s_data
{
    int dia;
    int mes;
    int ano;
};

struct s_list
{
    int cpf;
    s_data data[3];
};

class List
{
protected:
    int length;
    s_list *array;

public:
    List()
    {
        length = 0;
        array = nullptr;
    }

    List(int n)
    {
        if (n > 0)
        {
            length = n;
            array = new s_list[n];
            for (int k = 0; k < length; k = k + 1)
            {
                array[k].cpf = 0;
                for (int v = 0; v < 3; v = v + 1)
                {
                    array[k].data[v].dia = 0;
                    array[k].data[v].mes = 0;
                    array[k].data[v].ano = 0;
                }
            }
        }
    }

    void print(void)
    {
        std::cout << "\n";
        for (int k = 0; k < length; k = k + 1)
        {
            std::cout << (k + 1) << ". ("
                      << array[k].cpf;
            for (int v = 0; v < 3; v = v + 1)
            {
                std::cout << ", ";
                std::cout << array[k].data[v].dia << "/";
                std::cout << array[k].data[v].mes << "/";
                std::cout << array[k].data[v].ano;
            }
            std::cout << " )\n";
        }
    }

    void fprint(std::string filename)
    {
    }

    // _____  Metodos a serem desenvolvidos

    int flines(std::string filename);
    void fscan(std::string filename);
    void filter1(void);
    void filter2(void);
    void filter3(void);
    void filter(int option);

    // _____ Procedimento com testes

    void test(void)
    {
        List lista0;
        List *lista1 = new List();
        List *lista2 = new List(10);

        std::cout << "\nList tests\n\n";

        std::cout << "\nList #1\n"
                  << std::endl;
        lista1->fscan("input.txt");
        lista1->print();

        std::cout << "\nList #2\n"
                  << std::endl;
        lista2->print();
    }
}; // end class List

/**
   Funcao para obter o numero de linhas de arquivo.
   @result linhas no arquivo
   @param  filename = nome do arquivo a ser lido
 */
int List::flines(std::string filename)
{
    int fileLenght = 0;
    string linha;

    ifstream vacineFile(filename);

    if (vacineFile.is_open() == true)
    {
        while (!vacineFile.eof())
        {
            getline(vacineFile, linha);
            fileLenght = fileLenght + 1;
        }
        vacineFile.close();
    }
    else
    {
        cout << "ERROR: o arquivo não foi aberto ou não existe!" << endl;
    }

    return (fileLenght);
}
/**
   Procedimento para ler arquivo e guardar dados em lista.
   @param  filename = nome do arquivo a ser lido
 */
void List::fscan(std::string filename)
{
    int quantLine = flines(filename);
    int quantObjects = quantLine / 10;

    length = quantObjects;
    array = new s_list[quantObjects];

    ifstream vacineFile(filename);

    if (vacineFile.is_open() == true)
    {
        for (int i = 0; i < quantObjects; i++)
        {
            vacineFile >> array[i].cpf;

            for (int j = 0; j < 3; j++)
            {
                vacineFile >> array[i].data[j].dia;
                vacineFile >> array[i].data[j].mes;
                vacineFile >> array[i].data[j].ano;
            } // end for
        }     // end for

        vacineFile.close();
    }
    else
    {
        cout << "ERROR: o arquivo não foi aberto ou não existe!" << endl;
    }

    vacineFile.close();
}
/**
   Procedimento para filtrar dados em lista
   em relacao 'a primeira dose.
 */

void List::filter1(void)
{
    int quantObjects = length;

    if (array == nullptr || quantObjects <= 0)
    {
        cout << "\nERRO: Tamanho invalido ou falta de dados.\n"
             << endl;
    }
    else
    {
        for (int i = 0; i < quantObjects; i++)
        {
            if(array[i].cpf <= 0)
            {
                array[i].cpf = 0;
                for (int j = 0; j < 3; j++)
                {
                    array[i].data[j].dia = 0;
                    array[i].data[j].mes = 0;
                    array[i].data[j].ano = 0;
                } // end for
            }
            else
            {
                for (int j = 0; j < 3; j++)
                {
                    if (array[i].data[j].dia < 1 || array[i].data[j].dia > 30)
                    {
                        array[i].cpf = 0;
                        for (int j = 0; j < 3; j++)
                        {
                            array[i].data[j].dia = 0;
                            array[i].data[j].mes = 0;
                            array[i].data[j].ano = 0;
                        } // end for
                    }
                    else if(array[i].data[j].mes < 1 || array[i].data[j].mes > 12)
                    {
                        array[i].cpf = 0;
                        for (int j = 0; j < 3; j++)
                        {
                            array[i].data[j].dia = 0;
                            array[i].data[j].mes = 0;
                            array[i].data[j].ano = 0;
                        } // end for
                    }
                    else if(array[i].data[j].ano < 2019 || array[i].data[j].ano > 2022)
                    {
                        array[i].cpf = 0;
                        for (int j = 0; j < 3; j++)
                        {
                            array[i].data[j].dia = 0;
                            array[i].data[j].mes = 0;
                            array[i].data[j].ano = 0;
                        } // end for
                    }//end if
                }//end for
            }//end if
        }//end for
    } // end for
}
/**
   Procedimento para filtrar dados em lista
   em relacao 'a segunda dose.
 */
void List::filter2(void)
{
    int quantObjects = List::length;

    for (int i = 0; i < quantObjects; i++)
    {
        // verifica dia, mes e ano
        if (array[i].data[1].mes == 1)
        {
            if (array[i].data[1].dia != array[i].data[0].dia ||
                (array[i].data[1].ano - 1) != array[i].data[0].ano)
            {
                array[i].cpf = 0;
                for (int j = 0; j < 3; j++)
                {
                    array[i].data[j].dia = 0;
                    array[i].data[j].mes = 0;
                    array[i].data[j].ano = 0;
                } // end for
            }     // end if
        }
        else
        {
            if (array[i].data[1].dia != array[i].data[0].dia ||
                array[i].data[1].mes != (array[i].data[0].mes - 1) ||
                array[i].data[1].ano != array[i].data[0].ano)
            {
                array[i].cpf = 0;
                for (int j = 0; j < 3; j++)
                {
                    array[i].data[j].dia = 0;
                    array[i].data[j].mes = 0;
                    array[i].data[j].ano = 0;
                } // end for
            }     // end if
        }         // end if
    }             // end for
}
/**
   Procedimento para filtrar dados em lista
   em relacao 'a terceira dose.
 */
void List::filter3(void)
{
    int quantObjects = List::length;

    for (int i = 0; i < quantObjects; i++)
    {
        // verifica dia, mes e ano
        if (array[i].data[2].mes >= 6)
        {
            if (array[i].data[2].dia != array[i].data[1].dia ||
                (array[i].data[2].mes - 5) != array[i].data[1].mes ||
                array[i].data[2].ano != array[i].data[1].ano)
            {
                array[i].cpf = 0;
                for (int j = 0; j < 3; j++)
                {
                    array[i].data[j].dia = 0;
                    array[i].data[j].mes = 0;
                    array[i].data[j].ano = 0;
                } // end for
            }     // end if
        }
        else
        {
            if (array[i].data[2].dia != array[i].data[1].dia ||
                (array[i].data[2].mes + 12 - 5) != (array[i].data[1].mes) ||
                (array[i].data[2].ano - 1) != array[i].data[1].ano)
            {
                array[i].cpf = 0;
                for (int j = 0; j < 3; j++)
                {
                    array[i].data[j].dia = 0;
                    array[i].data[j].mes = 0;
                    array[i].data[j].ano = 0;
                } // end for
            }     // end if
        }         // end if
    }             // end for
}
/**
   Procedimento para filtrar dados validos em lista.
   option = 1 - primeira dose
            2 - segunda  dose
            3 - terceira dose
 */
void List::filter(int option)
{
    switch (option)
    {
    case 1:
        filter1();
        break;
    case 2:
        filter2();
        break;
    case 3:
        filter3();
        break;
    default:;
        break;
    }
}

/**
   Acao principal.
 */
int main(void)
{
    List a;
    a.test();
    return (0);
} // end main ( )

/*
  Testes e anotacoes

*/
