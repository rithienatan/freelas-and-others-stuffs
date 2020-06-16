/**
 * @author urName
 * Data: 03/06/2020
 * 
 * @version 0.1.0
 */

/* ---------------------- Begin class ---------------------- */
class Municipios 
{
    private String nome;
    private String estado;
    private int populacaoTotal;
    private int quantidadeVagas;

    /**
     * Metodo construtor padrão
     */
    public Municipios()
    {
        this(null, null, 0, 0);
    }//end constructor()

    /**
     * Metodo constructor alternativo
     */
    public Municipios(String nome, String estado, int populacaoTotal, int quantidadeVagas)
    {
        this.nome = nome;
        this.estado = estado;
        this.populacaoTotal = populacaoTotal;
        this.quantidadeVagas = quantidadeVagas;
    }//end alternative constructor()

    /* ---------------------- get(s) and set(s) ---------------------- */
    public String getNome()
    { return(this.nome); }

    public void setNome(String nome)
    { this.nome = nome; }

    public String getEstado()
    { return(this.estado); }

    public void setEstado(String estado)
    { this.estado = estado; }

    public int getPopulacaoTotal()
    { return(this.populacaoTotal); }

    public void setPopulacaoTotal(int populacaoTotal)
    { this.populacaoTotal = populacaoTotal; }

    public int getQuantidadeVagas()
    { return(this.quantidadeVagas); }

    public void setQuantidadeVagas(int quantidadeVagas)
    { this.quantidadeVagas = quantidadeVagas; }
    /* ---------------------- end get(s) and set(s) ---------------------- */

    /**
     * Metodo que imprime os atributos das classes
     */
    public void imprimir()
    {
        System.out.println("" + getNome() + "; " +
                                getEstado() + "; " +
                                getPopulacaoTotal() + "; " +
                                getQuantidadeVagas());
    }//end if
}//end class