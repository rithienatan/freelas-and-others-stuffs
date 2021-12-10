import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.lang.Math;

public class provasZ {
    private static final double PI = 3.14;

    public static void main(String[]args){
        //equalization_X(25);
        forier();

    }

    private static  void forier(){
        List<String> entrada = List.of("3","2", "0", "0", "X", "3");
        int numeroElementos = entrada.size();
        int posicao = 0 ;

        Map<String, Collection<String>> funcaoU = new HashMap<>();

        Map<String , String> valorConcatenado = new HashMap<>();
        Map<String, String> funcaoUConcat = new HashMap<>();


        for(int u = 0 ; u < numeroElementos ; u++){
            for (String s : entrada) {
                Double _doisPI = new BigDecimal(2*PI).setScale(2, RoundingMode.HALF_EVEN).doubleValue();
                Double funcaoSenCos = new BigDecimal(_doisPI * u *  posicao/numeroElementos).setScale(2, RoundingMode.HALF_EVEN).doubleValue();
                Double resultadoCos = new BigDecimal(Math.cos(funcaoSenCos)).setScale(2, RoundingMode.HALF_EVEN).doubleValue() ;
                Double resultadoSen = new BigDecimal(Math.sin(funcaoSenCos)).setScale(2, RoundingMode.HALF_EVEN).doubleValue();
                String funcao = "";
                if(s != "X"){
                    Integer valorInteiro = Integer.parseInt(s);
                    funcao = String.format("%d * [%.2f - i*%.2f]", valorInteiro, resultadoCos, resultadoSen );
                }else{
                    funcao = String.format("X* [%.2f - i*%.2f]", resultadoCos, resultadoSen);
                }

                if(valorConcatenado.get(s) != null){
                    valorConcatenado.put(s, valorConcatenado.get(s).concat(" + ").concat(funcao));
                }else{
                    valorConcatenado.put(s, funcao);
                }
                posicao++;
            }
            funcaoU.put(""+u , valorConcatenado.values());
            valorConcatenado = new HashMap<>();

        }


        for (Map.Entry<String, Collection<String>> entry : funcaoU.entrySet()) {
            String coiso = "";
            for (String s : entry.getValue()) {
                coiso = coiso.concat(s).concat("\n +");
            }
            funcaoUConcat.put(entry.getKey(), String.format("1/%d(%s)", numeroElementos, coiso) );

        }

        System.out.println("debug");
    }

    private static void equalization_X(Integer num_max){

        String[][] matrix = {
                {"2", "4", "6", "5", "2"},
                {"1", "5", "7", "5", "0"},
                {"5", "1", "3", "2", "5"},
                {"5", "3", "1", "6", "1"},
                {"7", "5", "2", "6", "3"}
        };

        Double divisao = num_max / (double) (matrix.length * matrix[0].length);
        
        NavigableMap<String,Integer> mapValorQuantidade = new TreeMap<>();

        for ( int i = 0 ; i <= matrix.length -1 ; i++){
            for ( int j = 0 ; j <= matrix.length - 1 ; j++) {
                if(matrix[i][j] != "X"){
                    if(mapValorQuantidade.get(matrix[i][j]) == null){
                        mapValorQuantidade.put(matrix[i][j], 1);
                    }else{
                        mapValorQuantidade.put(matrix[i][j] , mapValorQuantidade.get(matrix[i][j]) + 1);
                    }
                }
            }
        }

        NavigableMap<String, Integer> somatorio = new TreeMap<>();
        int contador = 0;

        for (Map.Entry<String, Integer> stringIntegerEntry : mapValorQuantidade.entrySet()) {
            if(contador == 0){
                somatorio.put(stringIntegerEntry.getKey(), stringIntegerEntry.getValue());
            }else if(contador == 1){
                Integer valorAnterior =  mapValorQuantidade.lowerEntry(stringIntegerEntry.getKey()).getValue();
                somatorio.put(stringIntegerEntry.getKey(), stringIntegerEntry.getValue() + valorAnterior);
            }else{
                String keyAnterior = String.valueOf(Integer.parseInt(stringIntegerEntry.getKey()) - 1);
                somatorio.put(stringIntegerEntry.getKey(), stringIntegerEntry.getValue() + somatorio.get(keyAnterior).intValue());
            }

            contador++;
        }


        Map<String , Double> somatorioMultiplicado = new HashMap<>();


        for (Map.Entry<String, Integer> value : somatorio.entrySet()) {
            Double valorMultiplicado = value.getValue() * divisao;
            somatorioMultiplicado.put(value.getKey(), valorMultiplicado);

        }

        System.out.println("###### MATRIZ ####### ");
        printMatrix(matrix);

        String[][] matrix_equalizada = matrix.clone();

        for ( int i = 0 ; i <= matrix_equalizada.length -1 ; i++){
            for ( int j = 0 ; j <= matrix_equalizada.length - 1 ; j++) {
                if(matrix_equalizada[i][j] != "X"){
                    matrix_equalizada[i][j] = somatorioMultiplicado.get(matrix_equalizada[i][j]).intValue() +"";

                }
            }
        }

        printValorQuantidade(mapValorQuantidade);
        printValorSomatorio(somatorio);
        System.out.println("###### MATRIZ EQUALIZADA ######");
        printMatrix(matrix_equalizada); //Equalizada

    }

    private static void printValorSomatorio(NavigableMap<String, Integer> somatorio) {
        System.out.println("########### SOMATORIO #############");
        for (Map.Entry<String, Integer> stringIntegerEntry : somatorio.entrySet()) {
            System.out.println(stringIntegerEntry.getKey().concat(" -> ").concat(stringIntegerEntry.getValue().toString()));
        }
        createNewLine(2);
    }

    private static void createNewLine(int i) {
        for (int i1 = 0; i1 < i; i1++) {
            System.out.println();
        }
    }

    private static void printMatrix(String[][] matrix) {
        for (int i = 0; i < matrix.length; i++) { //this equals to the row in our matrix.
            for (int j = 0; j < matrix[i].length; j++) { //this equals to the column in each row.
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println(); //change line on console as row comes to end in the matrix.
        }
        createNewLine(2);
    }

    private static void printValorQuantidade(NavigableMap<String, Integer> mapValorQuantidade) {
        System.out.println("########### QUANTIDADE #############");
        for (Map.Entry<String, Integer> stringIntegerEntry : mapValorQuantidade.entrySet()) {
            System.out.println(stringIntegerEntry.getKey().concat(" -> ").concat(stringIntegerEntry.getValue().toString()));
        }
        createNewLine(2);
    }
}