/**
 * @author urName
 * Data: 03/06/2020
 * 
 * @version 0.1.0
 */

/* ---------------------- Begin class ---------------------- */
//Classe para a construção de itens da lista flexivel duplamente encadeada
class CandidatosCelula 
{
    public Candidatos elemento;
    public CandidatosCelula prox, ant;

    /**
     * Constructor padrão
     */
    public CandidatosCelula()
    { this(null); }

    /**
     * Constructor alternativo
     */
    public CandidatosCelula(Candidatos pp)
    { 
        this.elemento = pp; 
        this.prox = this.ant = null;
    }//end constructor alternativo()
}//end class