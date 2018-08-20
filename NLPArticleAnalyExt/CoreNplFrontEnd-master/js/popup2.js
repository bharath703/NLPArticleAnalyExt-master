document.addEventListener('DOMContentLoaded', function() {
	var addressLinks = document.getElementById('linkData');
chrome.storage.sync.get(/* String or Array */["ArticleLinks"], function(items){
    //  items = [ { "yourBody": "myBody" } ]
    // console.log('Value currently is ' + items.ArticleLinks);
    // var str = items.ArticleLinks;
    // var result = str.link(items.ArticleLinks);
    // addressLinks.innerHTML = result;
    addressLinks.innerHTML = items.ArticleLinks;
    document.write("<a href='"+items.ArticleLinks+"'>"+items.ArticleLinks+"</a>")
});

// var testArray=["test", "teste", "testes"];

// chrome.storage.sync.set({
//     list:testArray
// }, function() {
//     console.log("added to list");
// });
// var testArray = document.getElementById('testArray');

	 }, false);

