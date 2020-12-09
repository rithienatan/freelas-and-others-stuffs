function start()
{
    var valor, valor2, resultado, operacao;

    valor = prompt("Valor: ");
    operacao = prompt("Operação: ");
    valor2 = prompt("Valor2: ");

    switch(operacao)
    {
        case "+":
            resultado = parseInt(valor) + parseInt(valor2);
            break;
        case "-":
            resultado = parseInt(valor) * parseInt(valor2);
            break;
        case "*":
            resultado = parseInt(valor) * parseInt(valor2);
            break;
        case "/":
            resultado = parseInt(valor) / parseInt(valor2);
            break;
    }//end switch

    document.getElementById("one").innerText = resultado;
}//end start()