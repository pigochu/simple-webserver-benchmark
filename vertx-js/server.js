var vertx = require('vertx');

function _helloworld(req) {
	req.response.end("Hello World!");
}

function _sendfile1(req) {
	req.response.sendFile('../files/sendfile1.html');
}
function _sendfile2(req) {
	req.response.sendFile('../files/sendfile2.html');
}

function _arrayjson(req) {
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
	
	
	
	req.response.end(JSON.stringify(arr));
}


var routes = {
    '/helloworld' : _helloworld,
    '/sendfile1' : _sendfile1,
    '/sendfile2' : _sendfile2,
    '/arrayjson' : _arrayjson
};



vertx.createHttpServer().requestHandler(function(req) {
	var path = req.path();
	var fn = routes[path];
	if(typeof fn === 'function') {
		fn(req);
	} else {
		// no define route
		req.response.end();
	}
}).listen(8080, 'localhost');