<?php

require_once __DIR__ . '/JsonMessage.php';
require_once __DIR__ . '/Header.php';

// class
$jsonmessage = new JsonMessage();
$allHeader = new HeadersManager();

$allHeader->setAllHeaders();


$serachItem = isset($_GET['category']) ? strtolower($_GET['category']) : "";

if($serachItem === ""){

    $jsonmessage->errorMessage("error", "empty category");

}else{

    $allProducts = json_decode(file_get_contents('products.json'), true);
    $products = $allProducts['products'] ?? [];

    $item = array_filter($products, function ($p) use($serachItem) {

        if(!isset($p['category'])) return false;

        return stripos($p['category'], $serachItem) !== false;

    });

    $searchData = array_values($item);

    $data = array_map(function ($p) {

        return [ "title" => $p['title'], "category" => $p['category'] ];

    }, $searchData);

    $count = count($searchData);

    $status = $count > 0 ? "Success" : "Failed";
    $message = $count > 0 ? "Search item found" : "Query not found";

    $jsonmessage->successItemMessage($status, $message, "itemCount", $count, "data", $data);

}

