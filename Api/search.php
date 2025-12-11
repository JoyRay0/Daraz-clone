<?php

require_once __DIR__ . '/JsonMessage.php';

header('Content-Type: application/json');

//json message class
$jsonmessage = new JsonMessage();

$serachItem = isset($_GET['query']) ? strtolower($_GET['query']) : "";

if($serachItem === ""){

    $jsonmessage->errorMessage("error", "empty search query");

}else{

    $allProducts = json_decode(file_get_contents('products.json'), true);
    $products = $allProducts['products'] ?? [];

    $item = array_filter($products, function ($p) use($serachItem) {

        if(!isset($p['title'])) return false;

        return stripos($p['title'], $serachItem) !== false;

    });

    $searchData = array_values($item);

    $data = array_map(function ($p) {

        if(isset($p['price'])) $p['price'] = (float)number_format($p['price'], 2, ".", "");
        if(isset($p['discountPercentage'])) $p['discountPercentage'] = (float)number_format($p['discountPercentage'], 2, ".", "");
        if(isset($p['rating'])) $p['rating'] = (float)number_format($p['rating'], 2, ".", "");

        return $p;

    }, $searchData);

    $count = count($searchData);

    $status = $count > 0 ? "Success" : "Failed";
    $message = $count > 0 ? "Search item found" : "Query not found";

    echo json_encode([

            "status" => $status,
            "message" => $message,
            "itemCount" => $count,
            "data" => $data

        ], JSON_PRESERVE_ZERO_FRACTION);

}
