/**
 * @author urName
 * Data: 03/06/2020
 * 
 * @version 0.1.0
 */

/* ---------------------- Begin class ---------------------- */
class UrnasEletronicas 
{
    private Municipios municipios;
    private String zonaEleitoral;
    private String secaoEleitoral;

    /**
     * Metodo construtor padrão
     */
    public UrnasEletronicas()
    {
        this(null, null, null);
    }//end constructor()

    /**
     * Metodo constructor alternativo
     */
    public UrnasEletronicas(Municipios municipios, String zonaEleitoral, String secaoEleitoral)
    {
        this.municipios = municipios;
        this.zonaEleitoral = zonaEleitoral;
        this.secaoEleitoral = secaoEleitoral;
    }//end alternative constructor()

    /* ---------------------- get(s) and set(s) ---------------------- */
    public Municipios getMunicipios()
    { return(this.municipios); }

    public void setMunicipios(Municipios municipios)
    { this.municipios = municipios; }

    public String getZonaEleitoral()
    { return(this.zonaEleitoral); }

    public void setZonaEleitoral(String zonaEleitoral)
    { this.zonaEleitoral = zonaEleitoral; }

    public String getSecaoEleitoral()
    { return(this.secaoEleitoral); }

    public void setSecaoEleitoral(String secaoEleitoral)
    { this.secaoEleitoral = secaoEleitoral; }
    /* ---------------------- end get(s) and set(s) ---------------------- */

    /**
     * Metodo que imprime os atributos das classes
     */
    public void imprimir()
    {
        System.out.println("" + getMunicipios().getNome() + "; " +
                                getZonaEleitoral() + "; " +
                                getSecaoEleitoral());
    }//end if
}//end class