//Pure JS code with jQuery implementation

var url = null;

document.addEventListener('DOMContentLoaded', function() {
  var Button = document.getElementById('checkArticle');
  Button.addEventListener('click', function() {
        var params = "url=" + url + "&";
        for (var i = 1; i < 7; i++) {
          var companyName = document.getElementById('company' + i).value;
          if (companyName) {
            params += ("company=" + companyName + "&");
          }
        }

        // alert("URL:" + url);
        fetch("http://localhost:8080/analyze?" + params, {
          method: 'GET',
          headers: {
            "Accept": "application/json",
          }
        })
        	.then((response) => response.json())
          .then(function(data) {
            // alert(JSON.stringify(data));
            if(data.success)
            {
              var companyObj = data.companiesPresent;
              alert("The portfolio companies are present in this article. The companies present are " + companyObj);
              chrome.storage.sync.set({ "ArticleLinks": url }, function(){
                
            });



            }
            else
            {
              alert("The portfolio companies are not present this article");
            }
            // alert(obj[11]+obj[12]+obj[13]+obj[14]+obj[15]);
          })
        	.catch(error => alert("Error: " + error));
    });
  }, false);

chrome.tabs.query({'active': true, 'lastFocusedWindow': true}, function (tabs) {
  url = tabs[0].url;
});

document.addEventListener('DOMContentLoaded', function() {
  var Button = document.getElementById('savedData');
  
  }, false);



