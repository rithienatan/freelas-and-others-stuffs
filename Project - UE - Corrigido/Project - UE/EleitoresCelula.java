/**
 * @author urName
 * Data: 03/06/2020
 * 
 * @version 0.1.0
 */

/* ---------------------- Begin class ---------------------- */
//Classe para a construção de itens da lista flexivel duplamente encadeada
class EleitoresCelula 
{
    public Eleitores elemento;
    public EleitoresCelula prox, ant;

    /**
     * Constructor padrão
     */
    public EleitoresCelula()
    { this(null); }

    /**
     * Constructor alternativo
     */
    public EleitoresCelula(Eleitores pp)
    { 
        this.elemento = pp; 
        this.prox = this.ant = null;
    }//end constructor alternativo()
}//end class