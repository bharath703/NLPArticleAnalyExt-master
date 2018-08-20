chrome.tabs.query({'active': true, 'lastFocusedWindow': true}, function (tabs) {
  url = tabs[0].url;
});

// chrome.storage.sync.set({ "ArticleLinks": "testDataLink" }, function(){
//     //  A data saved callback omg so fancy
//     console.log('Value is set to ' + "testDataLink");
// });

// chrome.storage.sync.get(/* String or Array */["ArticleLinks"], function(items){
//     //  items = [ { "yourBody": "myBody" } ]
//     console.log('Value currently is ' + items.ArticleLinks);
// });

//  LOCAL Storage
// Save data to storage locally, in just this browser...

// chrome.storage.local.set({ "phasersTo": "awesome" }, function(){
//     //  Data's been saved boys and girls, go on home
// });

// chrome.storage.local.get(/* String or Array */["phasersTo"], function(items){
//     //  items = [ { "phasersTo": "awesome" } ]
// });
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