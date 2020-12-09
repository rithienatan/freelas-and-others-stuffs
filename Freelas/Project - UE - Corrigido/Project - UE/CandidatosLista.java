/**
 * @author urName
 * Data: 03/06/2020
 * 
 * @version 0.1.0
 */

/* ---------------------- Begin class ---------------------- */
//Classe para a construção de itens da lista flexivel duplamente encadeada
class CandidatosLista 
{
    private CandidatosCelula primeiro, ultimo;

	/**
	 * Construtor
	 */
	public CandidatosLista()
	{
		primeiro = new CandidatosCelula();
		ultimo = primeiro;
	}//end construtor()

	/**
	 * @return tamanho da lista
	 */
	public int tamanho()
	{
		int i = 0;

		for(CandidatosCelula j = primeiro; j.prox != ultimo.prox; j = j.prox, i++);

		return(i);
	}//end tamanho()

	/**
	 * Inserir no inicio da lista
	 * @param pp Recebe o ponteiro que contém o municipio a ser inserido
	 */
	public void inserirInicio(Candidatos pp)
	{
		CandidatosCelula tmp = new CandidatosCelula(pp);
		tmp.ant = primeiro;
		tmp.prox = primeiro.prox;
		primeiro.prox = tmp;
		if(primeiro == ultimo)
		{
			ultimo = tmp;
		}
		else
		{
			tmp.prox.ant = tmp;
		}//end if

		tmp = null;
	}//end inserirInicio()

	/**
	 * Inseri no fim da lista
	 * @param pp Recebe o ponteiro que contém o municipio a ser inserido
	 */
	public void inserirFim(Candidatos pp)
	{
		ultimo.prox = new CandidatosCelula(pp);
		ultimo.prox.ant = ultimo;
		ultimo = ultimo.prox;
	}//end inserirFim()

	/**
	 * Inseri em uma posição desejada
	 * @param pp Recebe o ponteiro que contém o municipio a ser inserido
	 * @param pos Recebe a posição que será inserida
	 */
	public void inserir(Candidatos pp, int pos)throws Exception
	{
		int tamanho = tamanho();

		if(pos < 0 || pos > tamanho)
		{
			throw new Exception("Erro!");
		}
		else if(pos == 0)
		{
			inserirInicio(pp);
		}
		else if(pos == tamanho)
		{
			inserirFim(pp);
		}
		else
		{
			CandidatosCelula i = primeiro;
			for(int j = 0; j < pos; j++, i = i.prox);
			CandidatosCelula tmp = new CandidatosCelula(pp);
			tmp.ant = i;
			tmp.prox = i.prox;
			tmp.ant.prox = tmp.prox.ant = tmp;
			tmp = i = null;
		}//end if
	}//end inserir()

	/**
	 * Remove elementos que estão no inicio da lista
	 * @return Retorna o ponteiro municipio que foi retirado da lista
	 */
	public Candidatos removerInicio()throws Exception
	{
		if(primeiro == ultimo)
		{
			throw new Exception("Erro!");
		}//end if

		CandidatosCelula tmp = primeiro;
		primeiro = primeiro.prox;
		Candidatos resp = primeiro.elemento;
		tmp.prox = primeiro.ant = null;
		tmp = null;

		return(resp);
	}//end removerInicio()

	/**
	 * Remove elementos que estão no fim da lista
	 * @return Retorna o ponteiro municipio que foi retirado da lista
	 */
	public Candidatos removerFim()throws Exception
	{

		if(primeiro == ultimo)
		{
			throw new Exception("Lista vazia!");
		}//end if

		Candidatos resp = ultimo.elemento;
		ultimo = ultimo.ant;
		ultimo.prox.ant = null;
		ultimo.prox = null;

		return(resp);
	}//end removerFim()

	/**
	 * Remove elementos que estão no inicio da lista
	 * @param pos Recebe a posição do elemento a ser removido
	 * @return Retorna o ponteiro municipio que foi retirado da lista
	 */
	public Candidatos remover(int pos)throws Exception
	{
		Candidatos resp;
		int tamanho = tamanho();
		if(primeiro == ultimo || pos < 0 || pos >= tamanho)
		{
			throw new Exception("Lista vazia ou posição incorreta!");
		}
		else if(pos == 0)
		{
			resp = removerInicio();
		}
		else if(pos == tamanho - 1)
		{
			resp = removerFim();
		}
		else
		{
			CandidatosCelula i = primeiro.prox;
			for(int j = 0; j < pos; j++, i = i.prox);
			i.ant.prox = i.prox;
			i.prox.ant = i.ant;
			resp = i.elemento;
			i.prox = i.ant = null;
			i = null;
		}//end if

		return(resp);
	}//end remover()

	/**
	 * Retorna a lista em um array
	 * @return
	 */
	public Candidatos[] returnInArrayMode()
	{
		Candidatos resp[] = new Candidatos[tamanho()];
		int count = 0;

		for(CandidatosCelula i = primeiro.prox; i != null; i = i.prox, count++)
		{
			resp[count] = i.elemento;
		}//end for

		return(resp);
	}//end returnInArrayMode()

	/**
	 * Retorna a lista em um array
	 * @return
	 */
	public Candidatos[] returnOnlyVereadorInArrayMode()
	{
		Candidatos resp[] = new Candidatos[tamanho()];
		int count = 0;

		for(CandidatosCelula i = primeiro.prox; i != null; i = i.prox)
		{
			if(i.elemento.getCargo() == 'V')
			{ 
				resp[count] = i.elemento; 
				count = count + 1;
			}//end if
		}//end for

		return(resp);
	}//end returnOnlyVereadorInArrayMode()

	/**
	 * Retorna a lista em um array
	 * @return
	 */
	public Candidatos[] returnOnlyPrefeitoInArrayMode()
	{
		Candidatos resp[] = new Candidatos[tamanho()];
		int count = 0;

		for(CandidatosCelula i = primeiro.prox; i != null; i = i.prox, count++)
		{
			if(i.elemento.getCargo() == 'P')
			{ 
				resp[count] = i.elemento; 
				count = count + 1;
			}//end if
		}//end for

		return(resp);
	}//end returnOnlyPrefeitoInArrayMode()

	/**
	 * Busca um candidato pelo seu número
	 * @param number Recebe o numero do candidato que será buscado
	 * @return objeto eleitor
	 */
	public Candidatos searchByNumber(int number)
	{
		Candidatos resp = null;

		for(CandidatosCelula j = primeiro.prox; j != null; j = j.prox)
		{
			if(number == j.elemento.getNumero())
			{
				resp = j.elemento;
				j = ultimo;
			}//end if
		}//end for

		return(resp);
	}//end tamanho()
    
    /**
     * Chamador recursivo do metodo mostrar
     */
    public void mostrar()
    { mostrar(primeiro.prox); }

	/**
	 * Mostra os elementos da lista
	 */
	public void mostrar(CandidatosCelula i)
	{
        if(i != null)
        {
            i.elemento.imprimir();
            mostrar(i.prox);
        }//end if
	}//end mostrar()
}//end class