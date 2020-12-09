'use strict'

/*--------------------------- Imports and requires ---------------------------*/
const productsJSON = require('./data/products.json');

/*--------------------------- Main program ---------------------------*/
const promotions = ['SINGLE LOOK', 'DOUBLE LOOK', 'TRIPLE LOOK', 'FULL LOOK'];

/**
 * @param {number[]} ids 
 * @param {productsJSON} productsList 
 */
function getShoppingCart(ids, productsList) {
	/*----------------------------- set item that was choosen -----------------------------*/
	let listCart = [];
	
	for(let i = 0; i < ids.length; i++)
	{
		listCart[listCart.length] = productsJSON.products.find( ({ id }) => id === ids[i]);
	}//end for

	/*----------------------------- set names and categories -----------------------------*/
	let nameAndCategory = listCart.map(obj => {
		let rObj = {name: obj.name, category: obj.category};
		return(rObj);
	});

	/*----------------------------- define promotion -----------------------------*/
	let promotion = (promotions) => {
		let isDiferent = 0;

		//splits categories
		let cat = nameAndCategory.map(obj => {
			let rObj = obj.category;
			return(rObj);
		});

		//order by name
		cat.sort(function(a, b) {
			if (a < b) {
			  return -1;
			}//end if

			if (a > b) {
			  return 1;
			}//end if

			return 0;
		});

		//all categories without repeat
		const tipo = [];

		let allType = cat.reduce(function(atual, next){
			if(tipo.length === 0)
			{
				tipo.push(atual);
				if(atual !== next)
				{
					tipo.push(next);
				}//end if
			}
			else if(atual !== next)
			{
				tipo.push(next);
			}//end if
			return next;
		});

		return(promotions[tipo.length - 1]);
	};

	/*----------------------------- define (total price & discount value & discount) -----------------------------*/
	let discountValue = 0;
	let discount = 0;

	let totalPrice = (promotion) => {

		//splits regularPrice
		let rP = listCart.map(obj => {
			let rObj = obj.regularPrice;
			return(rObj);
		});

		//splits promotions
		let promo = listCart.map(obj => {
			let rObj = null;

			let out = 0;
			for(let i = 0; i < obj.promotions.length; i++)
			{
				for(let j = 0; j < obj.promotions[i].looks.length; j++)
				{
					if(obj.promotions[i].looks[j] === promotion)
					{
						rObj = obj.promotions[i];
						j = obj.promotions[i].looks.length;
						out = 1;
					}//end if
				}//end for

				if(out === 1)
				{ i = obj.promotions.length; }
			}//end for

			if(rObj === null)
			{ rObj = { looks: [], price: obj.regularPrice }; }

			return(rObj);
		});

		//set value of totalPrice
		let totalPrice = 0;

		for(let i = 0; i < promo.length; i++)
		{ totalPrice = totalPrice + promo[i].price; }

		//set discountValue
		discountValue = rP.reduce((acc, cur) => acc+cur)-totalPrice;

		//set discount %
		discount = 100-(100*(totalPrice/rP.reduce((acc, cur) => acc+cur)));

		return(+totalPrice.toFixed(2));
	};

	/*----------------------------- answer -----------------------------*/
	let answer = {
		products: nameAndCategory,
		promotion: promotion(promotions),
		totalPrice: ""+totalPrice(promotion(promotions)),
		discountValue: discountValue.toFixed(2),
		discount: discount.toFixed(2) + '%'
	};

	console.log(answer);

	return answer;
}//end getShoppingCart()

//1 - getShoppingCart([120, 230, 310, 490], productsJSON);
getShoppingCart([130, 140, 230, 260], productsJSON);
//4 - getShoppingCart([110, 130, 140, 230, 310, 330], products);

/*--------------------------- Exports ---------------------------*/
module.exports = { getShoppingCart };
