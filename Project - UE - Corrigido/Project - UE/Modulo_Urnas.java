/**
 * @author urName
 * Data: 03/06/2020
 * 
 * @version 0.1.0
 */
/* ---------------------- Imports ---------------------- */
import java.util.*;
import java.io.*;

/* ---------------------- Begin class ---------------------- */
class Modulo_Urnas 
{
    //----- urna informações básicas -----
    static String muni = null;
    static String zona = null;
    static String secao = null;

    //----- listas de candidatos e eleitores -----
    static CandidatosLista canList = null;
    static EleitoresLista eleiList = null;

    //----- listas de candidatos e eleitores -----
    static int quantDeJusti = 0;
    static String[] eleiJustificativa = new String[100000];

    /**
     * Função que configura a urna
     */
    public static void configuracoesUrna()throws IOException
    {
        Scanner readFileName = new Scanner(System.in);
        FileReader fr;
        BufferedReader bf;
        
        //----- inserir as informações dos candidatos em uma estrutura de dados -----
        System.out.println("Digite o nome do arquivo que contém os candidatos a prefeito e vereador desta região: ");
        String arqName = readFileName.nextLine();

        fr = new FileReader(arqName);
        bf = new BufferedReader(fr);

        String line = bf.readLine();//armazena uma linha do arquivo

        canList = new CandidatosLista();

        while(line != null)
        {
            String lineHasSplit[] = new String[4];
            lineHasSplit = line.split("; ");

            PartidoPolitico pp = new PartidoPolitico(null, lineHasSplit[2]);
            Candidatos object = new Candidatos(lineHasSplit[0], Integer.parseInt(lineHasSplit[1]), null, pp, lineHasSplit[3].charAt(0));

            canList.inserirFim(object);

            line = bf.readLine();
        }//end while

        fr.close();
        fr = null;
        bf = null;

        //----- inserir as informações dos eleitores em uma estrutura de dados -----
        System.out.println("Digite o nome do arquivo que contém os eleitores desta região: ");
        String arqName2 = readFileName.nextLine();

        fr = new FileReader(arqName2);
        bf = new BufferedReader(fr);

        String line2 = bf.readLine();//armazena uma linha do arquivo

        eleiList = new EleitoresLista();

        while(line2 != null)
        {
            Eleitores object2 = new Eleitores(null, line2, null, null, null);

            eleiList.inserirFim(object2);

            line2 = bf.readLine();
        }//end while

        fr.close();

        //----- define o nome, zona e seção da urna -----
        String urnaLocation[] = arqName.split("_");
        muni = urnaLocation[1];
        zona = urnaLocation[2];
        secao = urnaLocation[3];
    }//end configuracoesUrna()

    //-------------------------------------- metodos para a execução de votos ---------------------------------------------------//
    /**
     * Função que passa a máquina para função de voto
     */
    public static void voteMode()
    {
        //----- para entrada de opções -----
        Scanner readInputs = new Scanner(System.in);
        byte option = 0; 

        do
        {
            System.out.println("Bem Vindo à Urna " + muni + " | Zona Eleitoral: " + zona + " | Seção Eleitoral: " + secao + "\n" +
                                "Digite um número para selecionar as opções abaixo: \n" + 
                                "0 - Sair \n" +
                                "1 - Votar \n" +
                                "2 - Justificar voto \n");
            option = readInputs.nextByte();

            switch(option)
            {
                case 0:
                    break;
                case 1:
                    votar();
                    break;
                case 2:
                    justificarVoto();
                    break;
                default:
                    break;
            }//end switch
        }
        while(option > 0);//end dowhile
    }//end voteMode()

    /**
     * Metodo que executa a função de voto
     */
    public static void votar()
    {
        boolean isFinished = false; 

        //----- para entrada de opções -----
        Scanner readInputs = new Scanner(System.in);

        System.out.println("Digite o número do título de eleitor: ");

        do
        {
            String titulo = readInputs.nextLine();
            Eleitores inVote = eleiList.searchByTituloEleitor(titulo);

            if((inVote != null) && (inVote.getHasVoted() == false))
            {
                boolean vote = false;
                do
                {
                    System.out.println("Entre com o seu voto para prefeito: ");
                    int prefeito = readInputs.nextInt();
                    Candidatos p = canList.searchByNumber(prefeito);

                    System.out.println("Entre com o seu voto para vereador: ");
                    int vereador = readInputs.nextInt();
                    Candidatos v = canList.searchByNumber(vereador);

                    if((p != null) && (v != null))
                    {
                        int numP = canList.searchByNumber(prefeito).getQuantidadeDeVotosRecebidos();
                        int numV = canList.searchByNumber(vereador).getQuantidadeDeVotosRecebidos();

                        canList.searchByNumber(prefeito).setQuantidadeDeVotosRecebidos(numP + 1);
                        canList.searchByNumber(vereador).setQuantidadeDeVotosRecebidos(numV + 1);
                        vote = true;
                    }
                    else
                    { System.out.println("Digite o seu voto novamente!"); }
                }
                while(vote == false);

                eleiList.searchByTituloEleitor(titulo).setHasVoted(true);
                isFinished = true;
            }
            else
            {
                if(inVote == null)
                { System.out.println("Número do título de eleitor incorreto, digite novamente!"); }
                else
                { System.out.println("Você já votou!"); }
            }//end if
        }
        while(isFinished == false);//end dowhile
    }//end votar()

    /**
     * Metodo que executa a função de justificar voto
     */
    public static void justificarVoto()
    {
        //----- para entrada de opções -----
        Scanner readInputs = new Scanner(System.in);

        System.out.println("Digite o número do título de eleitor: ");
        String titulo = readInputs.nextLine();

        System.out.println("Justificar voto: ");
        String texto = readInputs.nextLine();

        eleiJustificativa[quantDeJusti] = titulo + "; " + texto;
        quantDeJusti++;

        System.out.println("Seu voto foi justificado!");
    }//end votar()
    //-------------------------------------- end metodos para a execução de votos ---------------------------------------------------//

    /**
     * Exporta os resultados das votações
     * 
     * Informações encontrada no arquivo de candidatos desta urna estão no segunte formato:
     * Nome; Número; Partido Sigla; Cargo; Quantidade de Votos
     * O nome do arquivo de candidatos será: Fim de Votação Urna_Municipio_Zona Eleitoral_Seção_Candidatos.txt
     * 
     * Informações encontrada no arquivo de eleitores desta urna
     * Os eleitores que votaram:
     * Número do Título de Eleitor; 1
     * Obs.: 1 representado com true.
     * 
     * Os eleitores que justificaram o seu voto estão do segunte formato:
     * Número do Título de Eleitor; Justificativa
     * O nome do arquivo de eleitores será: Fim de Votação Urna_Municipio_Zona Eleitoral_Seção_Eleitores.txt
     */
    public static void exportsResults() throws IOException
    {
        FileWriter arq = new FileWriter("Fim de Votação Urna_" + muni + "_" + zona + "_" + secao + "_Eleitores.txt");
        PrintWriter gravarArq = new PrintWriter(arq);

        Eleitores[] eleitor = eleiList.returnInArrayMode();

        for(int j = 0; j < eleitor.length; j++)
        {
            gravarArq.println("" + eleitor[j].getNumeroEleitoral() + "; 1");
        }//end for

        for(int j = 0; j < quantDeJusti; j++)
        {
            gravarArq.println(eleiJustificativa[j]);
        }//end for

        arq.close();

        FileWriter arq2 = new FileWriter("Fim de Votação Urna_" + muni + "_" + zona + "_" + secao + "_Candidatos.txt");
        PrintWriter gravarArq2 = new PrintWriter(arq2);

        Candidatos[] candidato = canList.returnInArrayMode();

        for(int j = 0; j < candidato.length; j++)
        {
            gravarArq2.println("" + candidato[j].getNome() + "; " +
                                    candidato[j].getNumero() + "; " +
                                    candidato[j].getPartidoPolitico().getSigla() + "; " +
                                    candidato[j].getCargo() + "; " +
                                    candidato[j].getQuantidadeDeVotosRecebidos());
        }//end for

        arq2.close();
    }//end exportsResults()
    
    public static void main(String[] args)throws IOException
    {
        //----- para entrada de opções -----
        Scanner readInputs = new Scanner(System.in);
        byte option = 0; 

        do
        {
            System.out.println("---------------------- Módulo Urna Eletrônica: Opções ----------------------\n" + 
                                "Digite um número para selecionar as opções abaixo: \n" + 
                                "0 - Sair \n" +
                                "1 - Configurar Urna Eletrônica \n" +
                                "2 - Modo de votação \n" +
                                "3 - Exportar Resultados \n");
            option = readInputs.nextByte();

            switch(option)
            {
                case 0:
                    readInputs.close();
                    System.exit(0);
                    break;
                case 1:
                    configuracoesUrna();
                    System.out.println("Configuração da urna " + muni + " | zona:" + zona + " | seção:" + secao);
                    break;
                case 2:
                    voteMode();
                    break;
                case 3:
                    exportsResults();
                    break;
                default:
                    break;
            }//end switch
        }
        while(option > 0);//end dowhile

        readInputs.close();
    }//end main()
}//end class