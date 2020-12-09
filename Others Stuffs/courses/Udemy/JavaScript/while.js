function start()
{
    var numero, fatorial = 1, i = 1;

    numero = prompt("Numero: ");

    while(i <= numero)
    {
        fatorial = fatorial * i;
        i++;
    }//end while

    document.getElementById("one").innerText = fatorial;
}//end start()