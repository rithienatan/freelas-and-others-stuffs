//indexOf returns position on initial position of string search

//here will return 7
let text = "Please locate where 'locate' occurs!";
console.log(text.indexOf("locate"));

//here will return 21
let text2 = "Please locate where 'locate' occurs!";
console.log(text.lastIndexOf("locate"));

//here will return -1 because if NOT FOUND
let text3 = "Please locate where 'locate' occurs!";
console.log(text.lastIndexOf("John"));

//here will return index stating 15
let text4 = "Please locate where 'locate' occurs!";
console.log(text4.indexOf("locate", 15));

//here will return first string search finded
let text5 = "Please locate where 'locate' occurs!";
console.log(text5.search("locate"));

//here will return Banana
let text6 = "Apple, Banana, Kiwi";
console.log(text6.slice(7, 13));

//here will start the count from the end of string
let text7 = "Apple, Banana, Kiwi";
console.log(text7.slice(-12, -6));
//slice starting from 7
console.log(text7.slice(7));
//slice starting from end of string
console.log(text7.slice(-12));

//using substring
let text8 = "Apple, Banana, Kiwi";
console.log(text8.substring(7, 13));

//second parameter means lenght of stracted part
console.log(text8.substr(7, 6));
console.log(text8.substr(7));
console.log(text8.substr(-4));

//replace examples
let text9 = "Please visit Microsoft and Microsoft!";
console.log(text9.replace("Microsoft", "W3Schools"));
console.log(text9.replace("MICROSOFT", "W3Schools"));//replace is case sensitive
console.log(text9.replace(/MICROSOFT/i, "W3Schools"));
console.log(text9.replace(/Microsoft/g, "W3Schools"));

//uppercase and lowercase examples
let text10 = "Hello World!";       
console.log(text10.toUpperCase());
console.log(text10.toLowerCase());

//concat examples
let text11 = "Here";
let text12 = "ereH666"
console.log(text11.concat(" ", text12));

//trim examples
var text13 = "       Hello World!        ";
console.log(text13.trim());

//pad examples
let text14 = "5";
console.log(text14.padStart(4,0));
console.log(text14.padEnd(4,0));

//charAt examples
let text15 = "HELLO WORLD";
console.log(text15.charAt(0));
console.log(text15.charCodeAt(0));//returns index of UTF-16
console.log(text15[0]);

//split examples
let txt = "a,b,c,d,e";   // String
console.log(txt.split(","));          // Split on commas

