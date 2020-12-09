/**
 * @author urName
 * Data: 03/06/2020
 * 
 * @version 0.1.0
 */

/* ---------------------- Begin class ---------------------- */
//Classe para a construção de itens da lista flexivel duplamente encadeada
class UrnasEletronicasCelula 
{
    public UrnasEletronicas elemento;
    public UrnasEletronicasCelula prox, ant;

    /**
     * Constructor padrão
     */
    public UrnasEletronicasCelula()
    { this(null); }

    /**
     * Constructor alternativo
     */
    public UrnasEletronicasCelula(UrnasEletronicas pp)
    { 
        this.elemento = pp; 
        this.prox = this.ant = null;
    }//end constructor alternativo()
}//end class