'use strict'

let fibonacciArray = [];

const fibonacci = () => {
    let next = 0;
    let before = 0;

    while(next <= 350)
    {
        if(next == 0)
        {
            fibonacciArray[fibonacciArray.length] = next;
            next++;
        }
        else
        {
            before = fibonacciArray[fibonacciArray.length - 1];
            fibonacciArray[fibonacciArray.length] = next;
            next = next + before;
        }//end if
    }//end for

    return(fibonacciArray);
}//end fibonacci()

const isFibonnaci = (num) => {
    let resp = false;

    for(let i = 0; i < fibonacciArray.length; i++)
    {
        if(fibonacciArray[i] == num)
        {
            resp = true;
            i = fibonacciArray.length;
        }//end if
    }//end for

    return(resp);
}//end isFibonnaci()

module.exports = {
    fibonacci,
    isFibonnaci
}//end module.exports