/**
 * @author urName
 * Data: 03/06/2020
 * 
 * @version 0.1.0
 */

/* ---------------------- Begin class ---------------------- */
class Eleitores 
{
    private String nome;
    private String numeroEleitoral;
    private Municipios municipios;
    private String zonaEleitoral;
    private String secaoEleitoral;
    private boolean hasVoted = false;
    private String justificativa = null;

    /**
     * Metodo construtor padrão
     */
    public Eleitores()
    {
        this(null, null, null, null, null);
    }//end constructor()

    /**
     * Metodo constructor alternativo
     */
    public Eleitores(String nome, String numeroEleitoral, Municipios municipios, String zonaEleitoral, String secaoEleitoral)
    {
        this.nome = nome;
        this.numeroEleitoral = numeroEleitoral;
        this.municipios = municipios;
        this.zonaEleitoral = zonaEleitoral;
        this.secaoEleitoral = secaoEleitoral;
    }//end alternative constructor()

    /* ---------------------- get(s) and set(s) ---------------------- */
    public String getNome()
    { return(this.nome); }

    public void setNome(String nome)
    { this.nome = nome; }

    public String getNumeroEleitoral()
    { return(this.numeroEleitoral); }

    public void setNumeroEleitoral(String numeroEleitoral)
    { this.numeroEleitoral = numeroEleitoral; }

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

    public boolean getHasVoted()
    { return(this.hasVoted); }

    public void setHasVoted(Boolean hasVoted)
    { this.hasVoted = hasVoted; }

    public String getJustificativa()
    { return(this.justificativa); }

    public void setJustificativa(String justificativa)
    { this.justificativa = justificativa; }
    /* ---------------------- end get(s) and set(s) ---------------------- */

    /**
     * Metodo que imprime os atributos das classes
     */
    public void imprimir()
    {
        System.out.println("" + getNome() + "; " +
                                getNumeroEleitoral() + "; " +
                                getMunicipios().getNome() + "; " +
                                getZonaEleitoral() + "; " +
                                getSecaoEleitoral());
    }//end if
}//end class