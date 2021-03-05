let car = {
    type : "Fiat",
    model : "500",
    color : "white"
};

let person = {
    firstName : "John",
    lastName : "Doe",
    age : 50,
    eyeColor : "blue",
    fullName : function() { return(this.firstName + " " + this.lastName); }
};

console.log(person.fullName());