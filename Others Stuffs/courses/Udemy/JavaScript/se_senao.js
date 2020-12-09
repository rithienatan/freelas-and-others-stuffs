var valor, valor2, resultado, operacao;

function start()
{
    valor = prompt("Valor: ");
    operacao = prompt("Operação: ");
    valor2 = prompt("Valor2: ");

    if(operacao == "+")
    {
        resultado = parseInt(valor) + parseInt(valor2);
    }
    else if(operacao == "-")
    {
        resultado = parseInt(valor) - parseInt(valor2);
    }
    else if(operacao == "*")
    {
        resultado = parseInt(valor) * parseInt(valor2);
    }
    else if(operacao == "/")
    {
        resultado = parseInt(valor) / parseInt(valor2);
    }
    else
    {
        resultado = 0;
    }//end if

    document.getElementById("one").innerText = resultado;
}//end start()