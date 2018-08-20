document.addEventListener('DOMContentLoaded', function() {
	var addressLinks = document.getElementById('linkData');
chrome.storage.sync.get(/* String or Array */["ArticleLinks"], function(items){
    //  items = [ { "yourBody": "myBody" } ]
    // console.log('Value currently is ' + items.ArticleLinks);
    addressLinks.innerHTML = items.ArticleLinks;
});
	 }, false);

debugger;
var testArray=["test", "teste", "testes"];

chrome.storage.sync.set({
    list:testArray
}, function() {
    console.log("added to list");
});

chrome.storage.sync.get({
    list:[]; //put defaultvalues if any
},
function(data) {
   console.log(data.list);
   update(data.list); //storing the storage value in a variable and passing to update function
}
);  

function update(array)
   {
    array.push("testAdd");
    //then call the set to update with modified value
    chrome.storage.sync.set({
        list:array
    }, function() {
        console.log("added to list with new values");
    });
    }