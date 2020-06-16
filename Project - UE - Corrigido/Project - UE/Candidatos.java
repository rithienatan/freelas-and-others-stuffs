/**
 * @author urName
 * Data: 03/06/2020
 * 
 * @version 0.1.0
 */

/* ---------------------- Begin class ---------------------- */
class Candidatos 
{
    private String nome;
    private int numero;
    private Municipios municipios;
    private PartidoPolitico pp;
    private char cargo;
    private int quantVotosRecebidos = 0;

    /**
     * Metodo construtor padrão
     */
    public Candidatos()
    {
        this(null, 0, null, null, ' ');
    }//end constructor()

    /**
     * Metodo constructor alternativo
     */
    public Candidatos(String nome, int numero, Municipios municipios, PartidoPolitico pp, char cargo)
    {
        this.nome = nome;
        this.numero = numero;
        this.municipios = municipios;
        this.pp = pp;
        this.cargo = cargo;
    }//end alternative constructor()

    /* ---------------------- get(s) and set(s) ---------------------- */
    public String getNome()
    { return(this.nome); }

    public void setNome(String nome)
    { this.nome = nome; }

    public int getNumero()
    { return(this.numero); }

    public void setNumero(int numero)
    { this.numero = numero; }

    public Municipios getMunicipios()
    { return(this.municipios); }

    public void setMunicipios(Municipios municipios)
    { this.municipios = municipios; }

    public PartidoPolitico getPartidoPolitico()
    { return(this.pp); }

    public void setPartidoPolitico(PartidoPolitico pp)
    { this.pp = pp; }

    public char getCargo()
    { return(this.cargo); }

    public void setCargo(char cargo)
    { this.cargo = cargo; }

    public int getQuantidadeDeVotosRecebidos()
    { return(this.quantVotosRecebidos); }

    public void setQuantidadeDeVotosRecebidos(int quantVotosRecebidos)
    { this.quantVotosRecebidos = quantVotosRecebidos; }
    /* ---------------------- end get(s) and set(s) ---------------------- */

    /**
     * Metodo que imprime os atributos das classes
     */
    public void imprimir()
    {
        System.out.println("" + getNome() + "; " +
                                getNumero() + "; " +
                                getMunicipios().getNome() + "; " +
                                getPartidoPolitico().getSigla() + "; " +
                                getCargo());
    }//end if
}//end class