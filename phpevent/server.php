<?php
	$LISTEN_PORT	= 8080;
	$LISTEN_IP		= "0.0.0.0";
	

	function _helloworld($req) {
		$req->getOutputBuffer()->add("Hello World!");
		$req->sendReply(200,"OK");
	}

	function _sendfile1($req) {
		$req->getOutputBuffer()->add(file_get_contents("../files/sendfile1.html"));
		$req->sendReply(200,"OK");
	}
	
	function _sendfile2($req) {
		$req->getOutputBuffer()->add(file_get_contents("../files/sendfile2.html"));
		$req->sendReply(200,"OK");
	}
	
	function _arrayjson($req) {
		
		$arr1 = array();
		$arr2 = array();
		$arr3 = array();
		for($i=0; $i<100;$i++) {
			$arr1[$i] = $i;
			$arr2[$i] = $i*(-1);
			$arr3[$i] = "s : " .$i;
		}
		$arr = array(
			'a' => $arr1,
			'b' => $arr2,
			'c' => $arr3,
		);
		
		
		$req->getOutputBuffer()->add(json_encode($arr));
		$req->sendReply(200,"OK");
	}
	

	function _default($req,$data) {
		$req->sendReply(200,"OK");
	}


	$base = new EventBase();
	$http = new EventHttp($base);

	

	$http->setCallback("/helloworld", "_helloworld");
	$http->setCallback("/sendfile1", "_sendfile1");
	$http->setCallback("/sendfile2", "_sendfile2");
	$http->setCallback("/arrayjson", "_arrayjson");
	$http->setDefaultCallback("_default", "custom data value");
	
	$http->bind($LISTEN_IP, $LISTEN_PORT);
	$base->loop();