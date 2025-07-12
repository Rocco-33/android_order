var express = require('express')
var serveIndex = require('serve-index')
var serveStatic = require('serve-static')
var multiparty = require('multiparty')
var finalhandler = require('finalhandler')
var util = require('util')

var LOCAL_BIND_PORT = 3000
var app = express()

var serve = serveStatic('./')
app.use('/', serveIndex('./', {'icons': true}))

app.get('/*', function(req, res) {
    serve(req, res, finalhandler(req, res))
});

console.log(`Start static file server at ::${LOCAL_BIND_PORT}, Press ^ + C to exit`)
app.listen(LOCAL_BIND_PORT)
