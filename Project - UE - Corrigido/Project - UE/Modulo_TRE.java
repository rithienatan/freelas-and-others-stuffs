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
class Modulo_TRE 
{
    /**
     * Faz a leitura do arquivo 'Anexo A.txt'(template) e inseri os objetos em uma lista duplamente encadeada
     * @param arqName Nome do arquivo a ser lido
     * @param list Ponteiro que recebe uma lista nula
     * @throws IOException
     */
    public static PartidoPoliticoLista cadastroPartidoPolitico(String arqName, PartidoPoliticoLista list) throws IOException
    {
        FileReader fr = new FileReader(arqName);
        BufferedReader bf = new BufferedReader(fr);

        String line = bf.readLine();//armazena uma linha do arquivo

        list = new PartidoPoliticoLista();

        while(line != null)
        {
            String lineHasSplit[] = new String[2];
            lineHasSplit = line.split("; ");

            PartidoPolitico object = new PartidoPolitico(lineHasSplit[0], lineHasSplit[1]);

            list.inserirFim(object);

            line = bf.readLine();
        }//end while

        fr.close();

        return(list);
    }//end cadastroPartidoPolitico()

    /**
     * Faz a leitura do arquivo 'Anexo B.txt'(template) e inseri os objetos em uma lista duplamente encadeada
     * @param arqName Nome do arquivo a ser lido
     * @param list Ponteiro que recebe uma lista nula
     * @throws IOException
     */
    public static MunicipiosLista cadastroMunicipios(String arqName, MunicipiosLista list) throws IOException
    {
        FileReader fr = new FileReader(arqName);
        BufferedReader bf = new BufferedReader(fr);

        String line = bf.readLine();//armazena uma linha do arquivo

        list = new MunicipiosLista();

        while(line != null)
        {
            String lineHasSplit[] = new String[4];
            lineHasSplit = line.split("; ");

            int populacao = Integer.parseInt(lineHasSplit[2]);
            int vagas = Integer.parseInt(lineHasSplit[3]);

            Municipios object = new Municipios(lineHasSplit[0], lineHasSplit[1], populacao, vagas);

            list.inserirFim(object);

            line = bf.readLine();
        }//end while

        fr.close();

        return(list);
    }//end cadastroMunicipios()

    /**
     * Faz a leitura do arquivo 'Anexo C.txt'(template) e inseri os objetos em uma lista duplamente encadeada
     * @param arqName Nome do arquivo a ser lido
     * @param list Ponteiro que recebe uma lista nula
     * @param pL recebe uma lista de partidos
     * @param mL recebe uma lista de municipios
     * @throws IOException
     */
    public static CandidatosLista cadastroCandidatos(String arqName, CandidatosLista list, PartidoPoliticoLista pL, MunicipiosLista mL) throws IOException
    {
        FileReader fr = new FileReader(arqName);
        BufferedReader bf = new BufferedReader(fr);

        String line = bf.readLine();//armazena uma linha do arquivo

        list = new CandidatosLista();

        while(line != null)
        {
            String lineHasSplit[] = new String[5];
            lineHasSplit = line.split("; ");

            int number = Integer.parseInt(lineHasSplit[1]);

            Candidatos object = new Candidatos(lineHasSplit[0], number, mL.searchByName(lineHasSplit[2]), pL.searchBySigla(lineHasSplit[3]), lineHasSplit[4].charAt(0));

            list.inserirFim(object);

            line = bf.readLine();
        }//end while

        fr.close();

        return(list);
    }//end cadastroCandidatos()

    /**
     * Faz a leitura do arquivo 'Anexo D.txt'(template) e inseri os objetos em uma lista duplamente encadeada
     * @param arqName Nome do arquivo a ser lido
     * @param list Ponteiro que recebe uma lista nula
     * @param mL recebe uma lista de municipios
     * @throws IOException
     */
    public static EleitoresLista cadastroEleitores(String arqName, EleitoresLista list, MunicipiosLista mL) throws IOException
    {
        FileReader fr = new FileReader(arqName);
        BufferedReader bf = new BufferedReader(fr);

        String line = bf.readLine();//armazena uma linha do arquivo

        list = new EleitoresLista();

        while(line != null)
        {
            String lineHasSplit[] = new String[5];
            lineHasSplit = line.split("; ");

            Eleitores object = new Eleitores(lineHasSplit[0], lineHasSplit[1], mL.searchByName(lineHasSplit[2]), lineHasSplit[3], lineHasSplit[4]);

            list.inserirFim(object);

            line = bf.readLine();
        }//end while

        fr.close();

        return(list);
    }//end cadastroEleitores()

    /**
     * Faz a leitura do arquivo 'Anexo E.txt'(template) e inseri os objetos em uma lista duplamente encadeada
     * @param arqName Nome do arquivo a ser lido
     * @param list Ponteiro que recebe uma lista nula
     * @param mL recebe uma lista de municipios
     * @throws IOException
     */
    public static UrnasEletronicasLista cadastroUrnas(String arqName, UrnasEletronicasLista list, MunicipiosLista mL) throws IOException
    {
        FileReader fr = new FileReader(arqName);
        BufferedReader bf = new BufferedReader(fr);

        String line = bf.readLine();//armazena uma linha do arquivo

        list = new UrnasEletronicasLista();

        while(line != null)
        {
            String lineHasSplit[] = new String[3];
            lineHasSplit = line.split("; ");

            UrnasEletronicas object = new UrnasEletronicas(mL.searchByName(lineHasSplit[0]), lineHasSplit[1], lineHasSplit[2]);

            list.inserirFim(object);

            line = bf.readLine();
        }//end while

        fr.close();

        return(list);
    }//end cadastroEleitores()

    /**
     * Metodo no qual exporta os dados para as urnas cadastradas
     * A saída são arquivos de textos...
     * Um referente aos eleitores que deverão votar na seção eleitoral relacionada a urna
     * O outro sobre as informações dos prefeitos e vereadores do municipio que a urna será utilizada
     * 
     * O nome do arquivo de eleitores de cada urna é composto pela sequência:
     * Urna_Nome do Municipio_zona eleitoral_seção eleitoral_Eleitores.txt
     * Dentro do arquivo de eleitores de cada urna contém somente os números de título de eleitor de cada eleitor
     * 
     * O nome do arquivo de candidatos de cada urna é composto pela sequência:
     * Urna_Nome do Municipio_zona eleitoral_seção eleitoral_Candidatos.txt
     * Dentro do arquivo de candidatos contém o formato:
     * nome; número; sigla do partido; cargo que está candidatando('P' ou 'V')
     * 
     * @param urnaList Lista de cadastro de todas as urnas
     * @param eleList Lista de cadastro de todas os eleitores
     * @param canList Lista de cadastro de todas os candidatos
     */
    public static void exportsData(UrnasEletronicasLista urnaList, EleitoresLista eleList, CandidatosLista canList) throws IOException
    {
        UrnasEletronicas arrayUrna[] = urnaList.returnInArrayMode();
        Eleitores arrayEle[] = eleList.returnInArrayMode();

        for(int i = 0; i < arrayUrna.length; i++)
        {
            String nomeMunicipio = arrayUrna[i].getMunicipios().getNome();
            String zonaEleitoral = arrayUrna[i].getZonaEleitoral();
            String secaoEleitoral = arrayUrna[i].getSecaoEleitoral();
            FileWriter arq = new FileWriter("Urna_" + nomeMunicipio +
                                            "_" + zonaEleitoral +
                                            "_" + secaoEleitoral + "_Eleitores.txt");
            PrintWriter gravarArq = new PrintWriter(arq);

            for(int j = 0; j < arrayEle.length; j++)
            {
                String eleitorMuni = arrayEle[j].getMunicipios().getNome();
                String eleitorZona = arrayEle[j].getZonaEleitoral();
                String eleitorSecao = arrayEle[j].getSecaoEleitoral();

                if((nomeMunicipio.compareToIgnoreCase(eleitorMuni) == 0) &&
                   (zonaEleitoral.compareToIgnoreCase(eleitorZona) == 0) &&
                   (secaoEleitoral.compareToIgnoreCase(eleitorSecao) == 0))
                {
                    gravarArq.println("" + arrayEle[j].getNumeroEleitoral());
                }//end if
            }//end for

            arq.close();

            //----- criar arquivo de informação para cadidatos a prefeitos e vereadores de um determinado municipio ------
            Candidatos arrayCan[] = canList.returnInArrayMode();

            String nomeMunicipio2 = arrayUrna[i].getMunicipios().getNome();
            String zonaEleitoral2 = arrayUrna[i].getZonaEleitoral();
            String secaoEleitoral2 = arrayUrna[i].getSecaoEleitoral();
            FileWriter arq2 = new FileWriter("Urna_" + nomeMunicipio2 +
                                            "_" + zonaEleitoral2 +
                                            "_" + secaoEleitoral2 + "_Candidatos.txt");
            PrintWriter gravarArq2 = new PrintWriter(arq2);

            for(int j = 0; j < arrayCan.length; j++)
            {
                if(nomeMunicipio.compareToIgnoreCase(arrayCan[j].getMunicipios().getNome()) == 0)
                {
                    gravarArq2.println("" + arrayCan[j].getNome() + "; " +
                                      arrayCan[j].getNumero() + "; " +
                                      arrayCan[j].getPartidoPolitico().getSigla() + "; " +
                                      arrayCan[j].getCargo());
                }//end if
            }//end for

            arq2.close();
        }//end for
    }//end exportsData()

    /**
     * Computa os arquivos de resultados da eleição dos eleitores
     * 
     * @param arqName Recebe nome do arquivo
     * @param eL Recebe lista de eleitores
     * @return Retorna a lista de eleitores
     */
    public static EleitoresLista resultadosEleitores(String arqName, EleitoresLista eL)throws IOException
    {
        FileReader fr = new FileReader(arqName);
        BufferedReader bf = new BufferedReader(fr);

        String line = bf.readLine();//armazena uma linha do arquivo

        while(line != null)
        {
            String lineHasSplit[] = new String[2];
            lineHasSplit = line.split("; ");

            if(lineHasSplit[1].charAt(0) == '1')
            { eL.searchByTituloEleitor(lineHasSplit[0]).setHasVoted(true); }
            else
            { eL.searchByTituloEleitor(lineHasSplit[0]).setJustificativa(lineHasSplit[1]); }//end if

            line = bf.readLine();
        }//end while

        fr.close();

        return(eL);
    }//end resultadosEleitores()

    /**
     * Computa os arquivos de resultados dos candidatos
     * 
     * @param arqName Recebe nome do arquivo
     * @param eL Recebe lista de eleitores
     * @return Retorna a lista de eleitores
     */
    public static CandidatosLista resultadosCandidatos(String arqName, CandidatosLista cL)throws IOException
    {
        FileReader fr = new FileReader(arqName);
        BufferedReader bf = new BufferedReader(fr);

        String line = bf.readLine();//armazena uma linha do arquivo

        while(line != null)
        {
            String lineHasSplit[] = new String[5];
            lineHasSplit = line.split("; ");

            cL.searchByNumber(Integer.parseInt(lineHasSplit[1])).setQuantidadeDeVotosRecebidos(cL.searchByNumber(Integer.parseInt(lineHasSplit[1])).getQuantidadeDeVotosRecebidos() + Integer.parseInt(lineHasSplit[4]));

            line = bf.readLine();
        }//end while

        fr.close();

        return(cL);
    }//end resultadosEleitores()

    /**
     * Exibe o resultado de eleição para prefeito
     * 
     * @param mL recebe lista de municipios
     * @param cL recebe lista de candidatos
     */
    public static void resultadosPrefeitos(MunicipiosLista mL, CandidatosLista cL)
    {
        Municipios[] mun = mL.returnInArrayMode();
        Candidatos[] can = cL.returnOnlyPrefeitoInArrayMode();

        can = quicksort(can);

        for(int i = 0; i < mun.length; i++)
        {
            System.out.println("Prefeitos eleitos do municipio " + mun[i].getNome() + "do estado " + mun[i].getEstado());

            int quantTotalVotos = 0;
            for(int j = 0; j < can.length; j++)
            { quantTotalVotos = quantTotalVotos + can[j].getQuantidadeDeVotosRecebidos();}//end for

            if((mun[i].getPopulacaoTotal() < 200000) || (can[can.length-1].getQuantidadeDeVotosRecebidos() > quantTotalVotos/2))
            { System.out.println(can[can.length-1].getNome() + " " + can[can.length-1].getQuantidadeDeVotosRecebidos()); }
            else
            {
                System.out.println("1º " + can[can.length-1].getNome() + " " + can[can.length-1].getQuantidadeDeVotosRecebidos());
                System.out.println("2º " + can[can.length-2].getNome() + " " + can[can.length-2].getQuantidadeDeVotosRecebidos());
            }//end if
        }//end for
    }//end resultadosPrefeitos()

    /**
     * Exibe o resultado de eleição para vereador
     * 
     * @param mL recebe lista de municipios
     * @param cL recebe lista de candidatos
     */
    public static void resultadosVereadores(MunicipiosLista mL, CandidatosLista cL)
    {
        Municipios[] mun = mL.returnInArrayMode();
        Candidatos[] can = cL.returnOnlyVereadorInArrayMode();

        can = quicksort(can);

        for(int i = 0; i < mun.length; i++)
        {
            System.out.println("Vereadores eleitos do municipio " + mun[i].getNome() + "do estado " + mun[i].getEstado());

            for(int j = 1; j <= mun[i].getQuantidadeVagas(); j++)
            {
                System.out.println(can[j].getNome() + " " + can[j].getQuantidadeDeVotosRecebidos());
            }//end for
        }//end for
    }//end resultadosPrefeitos()

    /**
     * Chamador recursivo
     * 
     * @param cL recebe lista de candidatos
     * 
     * @return retorna lista de candidatos
     */
    public static Candidatos[] quicksort(Candidatos[] cL)
    {
        int n = cL.length;
        return(quicksort(0, n-1, cL));
    }//end quicksort()

    /**
     * Metodo de ordenacão por quantidade total de votos com critério
     * 
     * @param esq Posicao onde inicia a pesquisa
     * @param dir Posicao onde termina a pesquisa
     * @param cL recebe lista de candidatos
     * 
     * @return retorna lista de candidatos
     */
    public static Candidatos[] quicksort(int esq, int dir, Candidatos[] cL)
    {
        int i = esq, j = dir, meio = ((dir + esq)/2) - 1;

        while(i <= j)
        {
            while(cL[i].getQuantidadeDeVotosRecebidos() <= cL[meio].getQuantidadeDeVotosRecebidos())
            {
                i++;
            }//end while
            while(cL[j].getQuantidadeDeVotosRecebidos() > cL[meio].getQuantidadeDeVotosRecebidos())
            {
                j--;
            }//end while

            if(i <= j)
            {
                //----- swap -----
                Candidatos aux = cL[i];
                cL[i] = cL[j];
                cL[j] = aux;

                aux = null;

                i++; j--;
            }//end if
        }//end while

        if(esq < j)
        {
            quicksort(esq, j, cL);
        }//end if
        if(i < dir)
        {
            quicksort(i, dir, cL);
        }//end if

        return(cL);
    }//end quicksort()
        
    public static void main(String[] args) throws IOException
    {
        //----- para entrada de opções -----
        Scanner readInputs = new Scanner(System.in);
        byte option = 0; 
        
        //----- listas -----
        PartidoPoliticoLista partidosList = null;
        MunicipiosLista municipiosList = null;
        CandidatosLista candidatosList = null;
        EleitoresLista eleitoresList = null;
        UrnasEletronicasLista urnasEletronicasList = null;

        do
        {
            System.out.println("---------------------- Módulo TRE: Opções ----------------------\n" + 
                               "Digite um número para selecionar as opções abaixo: \n" + 
                               "0 - Sair \n" +
                               "1 - Cadastro de Partidos Políticos \n" +
                               "2 - Cadastro de Municípios \n" +
                               "3 - Cadastro de Candidatos \n" +
                               "4 - Cadastro de Eleitores \n" + 
                               "5 - Cadastro de Urnas Eletrônicas \n" + 
                               "6 - Exportar dados para as urnas eletrônicas\n" + 
                               "7 - Exportar resultados\n" +
                               "8 - Listar prefeitos eleitos\n" +
                               "9 - Listar vereadores eleitos");

            option = readInputs.nextByte();

            switch(option)
            {
                case 0:
                    readInputs.close();
                    System.exit(0);
                    break;
                case 1:
                    System.out.println("Digite o nome do arquivo que contém os partidos políticos: ");
                    Scanner readFileName1 = new Scanner(System.in);
                    String arqName1 = readFileName1.nextLine();
                    partidosList = cadastroPartidoPolitico(arqName1, partidosList);
                    System.out.println("Cadastro de partidos concluídos com sucesso!");
                    partidosList.mostrar();
                    break;
                case 2:
                    System.out.println("Digite o nome do arquivo que contém os municípios: ");
                    Scanner readFileName2 = new Scanner(System.in);
                    String arqName2 = readFileName2.nextLine();
                    municipiosList = cadastroMunicipios(arqName2, municipiosList);
                    System.out.println("Cadastro de municipios concluídos com sucesso!");
                    municipiosList.mostrar();
                    break;
                case 3:
                    System.out.println("Digite o nome do arquivo que contém os candidatos a prefeito e vereador: ");
                    Scanner readFileName3 = new Scanner(System.in);
                    String arqName3 = readFileName3.nextLine();
                    candidatosList = cadastroCandidatos(arqName3, candidatosList, partidosList, municipiosList);
                    System.out.println("Cadastro de candidatos concluídos com sucesso!");
                    candidatosList.mostrar();
                    break;
                case 4:
                    System.out.println("Digite o nome do arquivo que contém os eleitores: ");
                    Scanner readFileName4 = new Scanner(System.in);
                    String arqName4 = readFileName4.nextLine();
                    eleitoresList = cadastroEleitores(arqName4, eleitoresList, municipiosList);
                    System.out.println("Cadastro de eleitores concluídos com sucesso!");
                    eleitoresList.mostrar();
                    break;
                case 5:
                    System.out.println("Digite o nome do arquivo que contém as informações de cadastro das Urnas: ");
                    Scanner readFileName5 = new Scanner(System.in);
                    String arqName5 = readFileName5.nextLine();
                    urnasEletronicasList = cadastroUrnas(arqName5, urnasEletronicasList, municipiosList);
                    System.out.println("Cadastro de urnas eletrônicas concluídos com sucesso!");
                    urnasEletronicasList.mostrar();
                    break;
                case 6:
                    exportsData(urnasEletronicasList, eleitoresList, candidatosList);
                    System.out.println("Dados exportados para arquivos de urnas prontos!");
                    break;
                case 7:
                    System.out.println("Insira os arquivos de cada urna, um por vez!");

                    System.out.println("Digite o nome do arquivo que contém resultados dos eleitores: ");
                    Scanner readFileName6 = new Scanner(System.in);
                    String arqName6 = readFileName6.nextLine();
                    eleitoresList = resultadosEleitores(arqName6, eleitoresList);
                    System.out.println("Arquivo de resultado dos eleitores importados com sucesso!");

                    System.out.println("Digite o nome do arquivo que contém resultados dos candidatos: ");
                    String arqName7 = readFileName6.nextLine();
                    candidatosList = resultadosCandidatos(arqName7, candidatosList);
                    System.out.println("Arquivo de resultado dos eleitores importados com sucesso!");
                    break;
                case 8:
                    resultadosPrefeitos(municipiosList, candidatosList);
                    break;
                case 9:
                    resultadosVereadores(municipiosList, candidatosList);
                    break;
                default:
                    break;
            }//end switch
        }
        while(option > 0);//end dowhile

        readInputs.close();
    }//end main()
}//end class