/**
 * @author urName
 * Data: 03/06/2020
 * 
 * @version 0.1.0
 */

/* ---------------------- Begin class ---------------------- */
//Classe para a construção de itens da lista flexivel duplamente encadeada
class PartidoPoliticoCelula 
{
    public PartidoPolitico elemento;
    public PartidoPoliticoCelula prox, ant;

    /**
     * Constructor padrão
     */
    public PartidoPoliticoCelula()
    { this(null); }

    /**
     * Constructor alternativo
     */
    public PartidoPoliticoCelula(PartidoPolitico pp)
    { 
        this.elemento = pp; 
        this.prox = this.ant = null;
    }//end constructor alternativo()
}//end class