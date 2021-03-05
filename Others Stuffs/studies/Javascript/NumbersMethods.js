//toString examples
let x = 123;
console.log(x.toString());       
console.log((123).toString());
console.log((100 + 23).toString());

//toExponential examples
let x = 9.656;
console.log(x.toExponential(2));     
console.log(x.toExponential(4));     
console.log(x.toExponential(6));

//toFixed examples
let x = 9.656;
console.log(x.toFixed(0));  
console.log(x.toFixed(2));          
console.log(x.toFixed(4));          
console.log(x.toFixed(6));

//toPrecision examples
var x = 9.656;
x.toPrecision();
x.toPrecision(2);
x.toPrecision(4);
x.toPrecision(6);

//valueOf examples
var x = 123;
x.valueOf();
(123).valueOf();
(100 + 23).valueOf();

//Number examples
Number(true);    
Number(false);   
Number("10");    
Number("  10");  
Number("10  ");  
Number(" 10  "); 
Number("10.33"); 
Number("10,33"); 
Number("10 33"); 
Number("John");  
Number(new Date("2017-09-30")); 

//parseInt examples
parseInt("10");      
parseInt("10.33");   
parseInt("10 20 30");
parseInt("10 years");
parseInt("years 10");

//parseFloat examples
parseFloat("10");      
parseFloat("10.33");   
parseFloat("10 20 30");
parseFloat("10 years");
parseFloat("years 10");

//Numbers properties examples
//obs.: these properties cannot use in variables
var x = Number.MAX_VALUE;
var x = Number.MIN_VALUE;
var x = Number.POSITIVE_INFINITY;
var x = 1 / 0;
var x = Number.NEGATIVE_INFINITY;
var x = -1 / 0;
var x = Number.NaN;
var x = 100 / "Apple";  // x will be NaN (Not a Number)