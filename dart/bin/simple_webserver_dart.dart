import 'dart:io';
import 'dart:async';
import 'dart:convert';
import 'package:path/path.dart' as path;

void main() {

  
  
  
  
  Map<String,Function> route = new Map();
  
  route = {
           '/helloworld' : _helloworld,
           '/sendfile1'  : _sendfile1,
           '/sendfile2'  : _sendfile2,
           '/arrayjson'  : _arrayjson
  };

  HttpServer.bind("0.0.0.0",8080,backlog: 512).then((server) {
    server.idleTimeout = null;
    
    
    server.listen((HttpRequest request) {
      if(route.containsKey(request.uri.path)) {
        route[request.uri.path](request);
      } else {
        _default(request);
      }
    });
  });
  
  print("dart web server start.\n");
}

void _default(HttpRequest request) {
  request.response.write("URI : " + request.uri.path);
  request.response.close();
}

void _helloworld(HttpRequest request) {
  request.response.write("Hello World!");
  request.response.close();
}

void _sendfile1(HttpRequest request) {

  String d = path.dirname(Platform.script.toFilePath());
  File file = new File(d + "/../../files/sendfile1.html");
  Future<String> finishedReading = file.readAsString(encoding:  Encoding.getByName("ASCII"));
  finishedReading.then((text){
    request.response.write(text);
    request.response.close();
  });

}

void _sendfile2(HttpRequest request) {
  String d = path.dirname(Platform.script.toFilePath());
  File file = new File(d + "/../../files/sendfile2.html");
  Future<String> finishedReading = file.readAsString(encoding:  Encoding.getByName("ASCII"));
  finishedReading.then((text){
    request.response.write(text);
    request.response.close();
  });
}

void _arrayjson(HttpRequest request) {
  List<int> arr1 = new List(100);
  var arr2 = new List(100); // this is dynamic type , cool ~~~
  List<String> arr3 = new List(100);
  
  for(int i=0; i<100; i++) {
    arr1[i] = i;
    arr2[i] = i*(-1);
    arr3[i] = "s : " +i.toString();
  }
  
  Map<String,List> arr = {
    'a' : arr1 ,
    'b' : arr2 ,
    'c' : arr3
  };
  

  request.response.write(JSON.encode(arr));
  request.response.close();
}
