<?php

require_once __DIR__ . '/JsonMessage.php';

header('Content-Type: application/json');

//json message class
$jsonmessage = new JsonMessage();

$serachItem = isset($_GET['query']) ? strtolower($_GET['query']) : "";

if($serachItem === ""){

    $jsonmessage->errorMessage("error", "empty search query");

}else{

    $title = htmlspecialchars($serachItem, ENT_QUOTES, "UTF-8");

    $allProducts = json_decode(file_get_contents('products.json'), true);
    $products = $allProducts['products'] ?? [];

    $data = [];

    foreach($products as $item){

        if(isset($item["title"])){

            if(stripos($item["title"], $title) !==false){

                $data[] = $item;

            }

        }

    }

    $count = count($data);

    $status = $count > 0 ? "Success" : "Failed";
    $message = $count > 0 ? "Search item found" : "Query not found";

    $jsonmessage->successItemMessage($status, $message, "itemCount", $count, "data", $data);

}
