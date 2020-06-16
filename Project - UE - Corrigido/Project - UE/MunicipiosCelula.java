/**
 * @author urName
 * Data: 03/06/2020
 * 
 * @version 0.1.0
 */

/* ---------------------- Begin class ---------------------- */
//Classe para a construção de itens da lista flexivel duplamente encadeada
class MunicipiosCelula
{
    public Municipios elemento;
    public MunicipiosCelula prox, ant;

    /**
     * Constructor padrão
     */
    public MunicipiosCelula()
    { this(null); }

    /**
     * Constructor alternativo
     */
    public MunicipiosCelula(Municipios pp)
    { 
        this.elemento = pp; 
        this.prox = this.ant = null;
    }//end constructor alternativo()
}//end class