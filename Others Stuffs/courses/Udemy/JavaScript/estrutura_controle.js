var nome, nota , nota2, media, passou = false;

nome = prompt("Nome: " + nome);
nota = prompt("Nota 1: " + nota);
nota2 = prompt("Nota 2: " + nota2);

media = (parseInt(nota) + parseInt(nota2)) / 2;

if(media >= 60)
{passou = true;}

if(passou && media >= 60)
{
    alert("Aprovado! " + nome);
}
else
{
    alert("Reprovado! " + nome);
}//end if