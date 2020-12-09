function start()
{
    var numero, fatorial = 1, i = 1;

    numero = prompt("Numero: ");

    do
    {
        fatorial = fatorial * i;
        i++;
    }
    while(i <= numero);//end do_while

    document.getElementById("one").innerText = fatorial;
}//end start()