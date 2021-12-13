// Avaliacao 03 - Modelo
// Matricula:
// Nome     :

#include <iostream>
#include <iomanip>
#include <fstream>
#include <string>

struct s_data
{
   int         dia;
   int         mes;
   int         ano;
};

struct s_list
{
   int         cpf ;
   s_data data [3];
};

class List
{
  protected:
    int     length;
    s_list *array ;
  public:
    List ( )
    { length = 0; array = nullptr; }

    List ( int n )
    {
      if ( n>0 )
      { length = n; array = new s_list [n];
        for ( int k=0; k<length; k=k+1 )
        {
            array [k].cpf = 0;
            for ( int v=0; v<3; v=v+1 )
            {
                array [k].data[v].dia = 0;
                array [k].data[v].mes = 0;
                array [k].data[v].ano = 0;
            }
        }
      }
    }

    void print ( void )
    {
      std::cout << "\n";
      for ( int k=0; k<length; k=k+1 )
      {
        std::cout << (k+1) << ". ("
                  << array [k].cpf;
        for ( int v=0; v<3; v=v+1 )
        {
            std::cout << ", ";
            std::cout << array [k].data[v].dia << "/";
            std::cout << array [k].data[v].mes << "/";
            std::cout << array [k].data[v].ano;
        }
        std::cout << " )\n";  }
    }

    void fprint ( std::string filename )
    { }

// _____  Metodos a serem desenvolvidos

    int  flines ( std::string filename );
    void fscan  ( std::string filename );
    void filter1    ( void );
    void filter2    ( void );
    void filter3    ( void );
    void filter     ( int option );

// _____ Procedimento com testes

    void test ( void )
    {
      List  lista0;
      List *lista1 = new List (  );
      List *lista2 = new List (10);

      std::cout << "\nList tests\n\n";

      std::cout << "\nList #1\n" << std::endl;
      lista1->fscan   ( "input.txt" );
      lista1->print   ( );

      std::cout << "\nList #2\n" << std::endl;
      lista2->print   ( );
    }
}; // end class List

/**
   Funcao para obter o numero de linhas de arquivo.
   @result linhas no arquivo
   @param  filename = nome do arquivo a ser lido
 */
int  List::flines ( std::string filename )
    {      return ( 0 );       }
/**
   Procedimento para ler arquivo e guardar dados em lista.
   @param  filename = nome do arquivo a ser lido
 */
void List::fscan ( std::string filename )
{                          }
/**
   Procedimento para filtrar dados em lista
   em relacao 'a primeira dose.
 */
void List::filter1 ( void )
{                          }
/**
   Procedimento para filtrar dados em lista
   em relacao 'a segunda dose.
 */
void List::filter2 ( void )
{                          }
/**
   Procedimento para filtrar dados em lista
   em relacao 'a terceira dose.
 */
void List::filter3 ( void )
{                          }
/**
   Procedimento para filtrar dados validos em lista.
   option = 1 - primeira dose
            2 - segunda  dose
            3 - terceira dose
 */
void List::filter  ( int option )
{
   switch ( option )
   {
     case 1 : filter1 ( ); break;
     case 2 : filter2 ( ); break;
     case 3 : filter3 ( ); break;
     default:            ; break;
   }
}

/**
   Acao principal.
 */
int main ( void )
{
    List a;
    a.test( );
    return ( 0 );
} // end main ( )

/*
  Condicoes
  1a. sem restricoes
  2a. 30 dias da primeira
  3a. 05 meses da segunda
  
  Questoes
  
*/
/*
  Testes e anotacoes

*/
