// server.js
var express = require('express');
var path = require('path');
var serveStatic = require('serve-static');
app = express();
app.use(serveStatic(__dirname + "/dist"));
var port = process.env.PORT || 8080;
var hostname = 'localhost';
var http = require('http');
var requestify = require('requestify');
var bodyParser = require('body-parser')


// parse application/json
app.use(bodyParser.json())

app.listen(port, () => {
  console.log(`Server running at http://${hostname}:${port}/`);
});


app.post("/api/product/vote",function(req, res){
  requestify.post('http://statistics_service_api/product/vote',req.body,{
    headers:{
      cookie:req.headers.cookie
    }
  }).then(function(response) {
      console.log(response.body);
      return res.send(response.body);

    }
  );
});

