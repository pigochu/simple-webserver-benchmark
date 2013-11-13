var http = require('http');
var path = require('path');
var fileSystem = require('fs');
var url = require('url');


function _helloworld(res) {
	res.end("Hello World!");
}

function _sendfile1(res) {
	var filePath = path.join(__dirname, '../files/sendfile1.html');
	var stat = fileSystem.statSync(filePath);
    var readStream = fileSystem.createReadStream(filePath);
    readStream.pipe(res);
}
function _sendfile2(res) {
	var filePath = path.join(__dirname, '../files/sendfile2.html');
	var stat = fileSystem.statSync(filePath);
    var readStream = fileSystem.createReadStream(filePath);
    readStream.pipe(res);
}

function _arrayjson(res) {
	var arr1 = [] , arr2 = [], arr3 = [];
	for(var i=0; i<100; i++) {
		arr1[i] = i;
		arr2[i] = i*(-1);
		arr3[i] = "s : " +i;
	}
	var arr = {
		a: arr1,
		b: arr2,
		c: arr3
	};
	
	
	res.end(JSON.stringify(arr));
}



var routes = {
    '/helloworld' : _helloworld,
    '/sendfile1' : _sendfile1,
    '/sendfile2' : _sendfile2,
	'/arrayjson' : _arrayjson
};

http.createServer(function (req, res) {

	var path = url.parse(req.url).pathname
	var fn = routes[path];
	if(typeof fn === 'function') {
		fn(res);
	} else {
		// no define route
		res.end('');
	}


}).listen(8080, '0.0.0.0');

