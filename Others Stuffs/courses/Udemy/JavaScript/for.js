function start()
{
    var numero, fatorial = 1;

    numero = prompt("Numero: ");

    for(var i = 1; i <= numero; i++)
    {
        fatorial = fatorial * i;
    }//end for

    document.getElementById("one").innerText = fatorial;
}//end start()