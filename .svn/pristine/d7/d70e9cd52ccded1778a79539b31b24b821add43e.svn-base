<html>
<head>
<style type="text/css">
	body {margin:0;padding:0;}
	#wrapper {background-color: #FFFFFF; width: 100%; height: 100%;}
</style>
<?php
	$array_var = array();
	$array_var_alias = array();
	$array_operation = array();
	$array_function = array();
	$array_function_alias = array();

	function set_variable($name, $val, $type = null) {
		global $array_var;
		$array_var[$name] = array("val" => $val, 
									"type" => $type);
	}

	function set_variable_alias($name, $alias) {
		global $array_var_alias;
		$array_var_alias[$name] = $alias;
	}

	function set_operation($name, $val) {
		global $array_operation;
		$array_operation[$name] = $val;
	}

	function set_function($name, $param_count, $process, $is_declare = true) {
		global $array_function;
		$array_function[$name] = array("param_count" => $param_count, 
										"process" => $process,
										"is_declare" => $is_declare);
	}

	function set_function_alias($name, $alias) {
		global $array_function_alias;
		$array_function_alias[$name] = "f_" . $alias;
	}

	function get_variable_alias($name) {
		global $array_var_alias;
		return $array_var_alias[$name];
	}

	function get_operation($name) {
		global $array_operation;
		return $array_operation[$name];
	}

	function get_function_alias($name) {
		global $array_function_alias;
		return $array_function_alias[$name];
	}

	function has_function_alias($name) {
		global $array_function_alias;
		return array_key_exists($name, $array_function_alias);
	}

	function get_function($name, $param = array()) {
		global $array_function;
		if(has_function_alias($name)) {
			return get_function_alias($name) . "(" . implode(",", $param) . ")";
		} else {
			$properties = $array_function[$name];
			return $properties["process"]($param);
		}
	}

	function get_type_to_string($val, $type = null) {
		if($type == null) {
			$type = gettype($val);
		}
		if($type == "string") {
			return "\"" . $val . "\"";
		} else {
			return $val;
		}
	}

	set_variable('variable_1', 1);
	set_variable('variable_2', 5);
	//set_variable('txt1', "document.getElementById(\"txt1\")", "object");
	//set_variable('txt2', "document.getElementById(\"txt2\")", "object");
	//set_variable('txt3', "document.getElementById(\"txt3\")", "object");
	/*set_variable('total', "document.getElementById(\"total\")", "object");
	set_variable('vat_percent', "document.getElementById(\"vat_percent\")", "object");
	set_variable('vat_amt', "document.getElementById(\"vat_amt\")", "object");
	set_variable('grand_total', "document.getElementById(\"grand_total\")", "object");*/
	/*set_variable('qty', "document.getElementById(\"qty\")", "object");
	set_variable('price', "document.getElementById(\"price\")", "object");
	set_variable('total', "document.getElementById(\"total\")", "object");*/
	/*set_variable('product_list', "[]", "object");*/

	set_variable('body', "document.body", "object");

	set_operation('add', '+');
	set_operation('sub', '-');
	set_operation('multiply', '*');
	set_operation('divide', '/');
	set_operation('return', 'return');

	set_function('add', 2, function($param_array) {
		return get_operation('return') . " parseFloat(" . $param_array[0] . ")" . get_operation('add') . "parseFloat(" . $param_array[1] . ")";
	});

	set_function('sub', 2, function($param_array) {
		return get_operation('return') . " parseFloat(" . $param_array[0] . ")" . get_operation('sub') . "parseFloat(" . $param_array[1] . ")";
	});

	set_function('multiply', 2, function($param_array) {
		return get_operation('return') . " parseFloat(" . $param_array[0] . ")" . get_operation('multiply') . "parseFloat(" . $param_array[1] . ")";
	});

	set_function('divide', 2, function($param_array) {
		return get_operation('return') . " parseFloat(" . $param_array[0] . ")" . get_operation('divide') . "parseFloat(" . $param_array[1] . ")";
	});

	set_function('assign', 2, function($param_array) {
		return $param_array[0].".value ? " . $param_array[0].".value" . "=" . $param_array[1] . ": ". $param_array[0] . "=" . $param_array[1];
	}, false);

	set_function('append', 2, function($param_array) {
		return $param_array[0].".innerHTML" . "=" . $param_array[0].".innerHTML+" . $param_array[1];
	});

	set_function('calc', 3, function($param_array) {
		$result = get_function("add", array($param_array[0].".value", $param_array[1].".value"));
		return get_function("assign", array($param_array[2], $result));
	});

	set_function('calc_total', 3, function($param_array) {
		$result = get_function("multiply", array($param_array[0].".value", $param_array[1].".value"));
		return get_function("assign", array($param_array[2], $result));
	});

	/*set_function('calc_grand_total_include_vat', 0, function() {
		$include_vat = get_function("add", array(get_variable_alias("vat_percent").".value", 100));
		$result = get_function("divide", array(get_variable_alias("total").".value", $include_vat));
		$result = get_function("multiply", array($result, 100));
		$vat_percent = get_function("divide", array(get_variable_alias("vat_percent").".value", 100));
		$vat_amt = get_function("multiply", array($result, $vat_percent));
		$grand_total = get_function("add", array(get_variable_alias("total").".value", $vat_amt));
		return get_function("assign", array(get_variable_alias("vat_amt"), $vat_amt)) . ";" .
				get_function("assign", array(get_variable_alias("grand_total"), get_variable_alias("total").".value"));
	});

	set_function('calc_grand_total_exclude_vat', 0, function() {
		$result = "";
		$vat_percent = get_function("divide", array(get_variable_alias("vat_percent").".value", 100));
		$vat_amt = get_function("multiply", array(get_variable_alias("total").".value", $vat_percent));
		$result .= get_function("assign", array(get_variable_alias("vat_amt"), $vat_amt)) . ";";
		$grand_total = get_function("add", array(get_variable_alias("total").".value", get_variable_alias("vat_amt").".value"));
		$result .= get_function("assign", array(get_variable_alias("grand_total"), $grand_total));
		return $result;
	});*/

	set_function('refer', 0, function() {
		return "window.open('')";
	});

	/*set_function('init', 0, function() {
		return "document.write('<input type=\"text\" id=\"txt1\" /> " . 
				"+ " .
				"<input type=\"text\" id=\"txt2\" /> " . 
				"= " .
				"<input type=\"text\" id=\"txt3\" /> " . 
				"<input type=\"button\" value=\"calculate\" onclick=\"" . get_function("calc", array(get_variable_alias("txt1"), get_variable_alias("txt2"), get_variable_alias("txt3"))) . "\" />');";
	});*/

	/*set_function('init_grand_total_form', 0, function() {
		return "document.body.innerHTML = 'Total <input type=\"text\" id=\"total\" /><br />" . 
				"VAT(%) " . 
				"<input type=\"text\" id=\"vat_percent\" /> " . 
				"<input type=\"text\" id=\"vat_amt\" /><br />" . 
				"Grand Total <input type=\"text\" id=\"grand_total\" /> " . 
				"<input type=\"button\" value=\"calculate\" onclick=\"" . get_function("calc_grand_total_exclude_vat") . "\" />'";
	});*/

	/*set_function('init_sample_form', 0, function() {
		return "document.body.innerHTML = 'Item <input type=\"text\" id=\"item_code\" /> " . 
				"<input type=\"button\" value=\"...\" onclick=\"" . get_function("refer") . "\" /> " .
				"<input type=\"text\" id=\"item_name\" /><br />" . 
				"Qty <input type=\"text\" id=\"qty\" /><br />" . 
				"Price <input type=\"text\" id=\"price\" /><br />" .
				"Total <input type=\"text\" id=\"total\" /><br />" .
				"<input type=\"button\" value=\"calculate\" onclick=\"" . get_function("calc_total", array(get_variable_alias("qty"), get_variable_alias("price"), get_variable_alias("total"))) . "\" />'";
	});*/

	/*set_function("product_list", 0, function() {
		$product_array = array(
			array("sku" => "K200D", "name" => "Pentax K200D", "price" => "499.00"), 
			array("sku" => "K20D", "name" => "Pentax K20D", "price" => "599.00"), 
			array("sku" => "AF540FGZ", "name" => "AF 540FGZ Flash", "price" => "299.00"), 
			array("sku" => "BG2", "name" => "Battery Grip BG2", "price" => "130.00"), 
			array("sku" => "PTX21790", "name" => "PENTAX DA* 55mm f/1.4", "price" => "399.00")
		);
		return get_operation('return') . " '" .json_encode($product_array) . "'";
	});

	set_function("decode_json", 1, function($param_array) {
		return  get_operation('return') . " eval(\"(\" + " . $param_array[0] . " + \")\")";
	});

	set_function("grid_product_item", 1, function($param_array) {
		return get_operation("return") . " '<div style=\"float: left; border:1px solid;\">" . 
				"<p>'+" . $param_array[0] . ".sku+'</p>" .
				"<p>'+" . $param_array[0] . ".name+'</p>" .
				"<p>'+" . $param_array[0] . ".price+'</p>" .
				"</div>'";
	});

	set_function("foreach", 2, function($param_array) {
		return "var result = ''; " .
				"for(var i in " . $param_array[0] . "){" . 
					"result += " . $param_array[1] . "(" . $param_array[0]."[i]);" .
				"} return result";
	});

	set_function("init_grid_product", 0, function() {
		$product_list = get_function("product_list");
		$result = get_function("decode_json", array($product_list));
		$result = get_function("assign", array(get_variable_alias("product_list"), $result));
		$product_list_html = get_function("foreach", array(get_variable_alias("product_list"), get_function_alias("grid_product_item")));
		$result .= ";" . get_function("append", array(get_variable_alias("body"), $product_list_html));
		return $result;
	});*/

	set_function("init_template", 0, function() {
		$result = get_function("append", array(get_variable_alias("body"), "'<div id=\"wrapper\"></div>'"));
		return $result;
	});

	//var_dump($array_var);
	$char_array = array('a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z');
	$count_var = 0;
	$declare_var = "";
	$assign_var = "";
	
	echo "<script type=\"text/javascript\">";

	// declare variable
	foreach($array_var as $key => $properties) {
		$val = $properties["val"];
		$type = $properties["type"];
		$alias = $char_array[$count_var];
		$declare_var .= "var " . $alias . ";";
		$assign_var  .= $alias . "=" . get_type_to_string($val, $type) . ";";
		set_variable_alias($key, $alias);
		$count_var++;
	}

	// declare function
	$count_fnc = 0;
	foreach($array_function as $key => $properties) {
		$param = array();
		for($i=0; $i<$properties["param_count"]; $i++) {
			$alias = $char_array[$i];
			array_push($param, $alias);
		}
		$is_declare = $properties["is_declare"];

		if( $is_declare ) {
			$alias = $char_array[$count_fnc];
			set_function_alias($key, $alias);
			$count_fnc++;
			$process = "function " . get_function_alias($key) . "(" . implode(",", $param) . "){" . $properties["process"]($param) . ";};";

			echo $process;
		}
	}

	echo $declare_var . "function init(){" . $assign_var . get_function("init_template") . "}";

	echo "</script>";
?>
</head>
<body onload="init()">
</body>
</html>