/**
 * @author urName
 * Data: 03/06/2020
 * 
 * @version 0.1.0
 */

/* ---------------------- Begin class ---------------------- */
//Classe para a construção de itens da lista flexivel duplamente encadeada
class UrnasEletronicasLista 
{
    private UrnasEletronicasCelula primeiro, ultimo;

	/**
	 * Construtor
	 */
	public UrnasEletronicasLista()
	{
		primeiro = new UrnasEletronicasCelula();
		ultimo = primeiro;
	}//end construtor()

	/**
	 * @return tamanho da lista
	 */
	public int tamanho()
	{
		int i = 0;

		for(UrnasEletronicasCelula j = primeiro; j.prox != ultimo.prox; j = j.prox, i++);

		return(i);
	}//end tamanho()

	/**
	 * Inserir no inicio da lista
	 * @param pp Recebe o ponteiro que contém o municipio a ser inserido
	 */
	public void inserirInicio(UrnasEletronicas pp)
	{
		UrnasEletronicasCelula tmp = new UrnasEletronicasCelula(pp);
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
	public void inserirFim(UrnasEletronicas pp)
	{
		ultimo.prox = new UrnasEletronicasCelula(pp);
		ultimo.prox.ant = ultimo;
		ultimo = ultimo.prox;
	}//end inserirFim()

	/**
	 * Inseri em uma posição desejada
	 * @param pp Recebe o ponteiro que contém o municipio a ser inserido
	 * @param pos Recebe a posição que será inserida
	 */
	public void inserir(UrnasEletronicas pp, int pos)throws Exception
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
			UrnasEletronicasCelula i = primeiro;
			for(int j = 0; j < pos; j++, i = i.prox);
			UrnasEletronicasCelula tmp = new UrnasEletronicasCelula(pp);
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
	public UrnasEletronicas removerInicio()throws Exception
	{
		if(primeiro == ultimo)
		{
			throw new Exception("Erro!");
		}//end if

		UrnasEletronicasCelula tmp = primeiro;
		primeiro = primeiro.prox;
		UrnasEletronicas resp = primeiro.elemento;
		tmp.prox = primeiro.ant = null;
		tmp = null;

		return(resp);
	}//end removerInicio()

	/**
	 * Remove elementos que estão no fim da lista
	 * @return Retorna o ponteiro municipio que foi retirado da lista
	 */
	public UrnasEletronicas removerFim()throws Exception
	{

		if(primeiro == ultimo)
		{
			throw new Exception("Lista vazia!");
		}//end if

		UrnasEletronicas resp = ultimo.elemento;
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
	public UrnasEletronicas remover(int pos)throws Exception
	{
		UrnasEletronicas resp;
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
			UrnasEletronicasCelula i = primeiro.prox;
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
	public UrnasEletronicas[] returnInArrayMode()
	{
		UrnasEletronicas resp[] = new UrnasEletronicas[tamanho()];
		System.out.println(tamanho());
		int count = 0;

		for(UrnasEletronicasCelula i = primeiro.prox; i != null; i = i.prox, count++)
		{
			resp[count] = i.elemento;
		}//end for

		return(resp);
	}//end returnInArrayMode()
    
    /**
     * Chamador recursivo do metodo mostrar
     */
    public void mostrar()
    { mostrar(primeiro.prox); }

	/**
	 * Mostra os elementos da lista
	 */
	public void mostrar(UrnasEletronicasCelula i)
	{
        if(i != null)
        {
            i.elemento.imprimir();
            mostrar(i.prox);
        }//end if
	}//end mostrar()
}//end class