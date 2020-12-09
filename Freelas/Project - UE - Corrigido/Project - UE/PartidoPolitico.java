/**
 * @author urName
 * Data: 03/06/2020
 * 
 * @version 0.1.0
 */

/* ---------------------- Begin class ---------------------- */
class PartidoPolitico 
{
    private String nome;
    private String sigla;

    /**
     * Metodo construtor padrão
     */
    public PartidoPolitico()
    {
        this(null, null);
    }//end constructor()

    /**
     * Metodo constructor alternativo
     */
    public PartidoPolitico(String nome, String sigla)
    {
        this.nome = nome;
        this.sigla = sigla;
    }//end alternative constructor()

    /* ---------------------- get(s) and set(s) ---------------------- */
    public String getNome()
    { return(this.nome); }

    public void setNome(String nome)
    { this.nome = nome; }

    public String getSigla()
    { return(this.sigla); }

    public void setSigla(String sigla)
    { this.sigla = sigla; }
    /* ---------------------- end get(s) and set(s) ---------------------- */

    /**
     * Metodo que imprime os atributos das classes
     */
    public void imprimir()
    {
        System.out.println("" + getNome() + "; " + getSigla());
    }//end if
}//end class