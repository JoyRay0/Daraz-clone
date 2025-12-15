<?php

require_once __DIR__ .'/JsonMessage.php';
require_once __DIR__ . '/Header.php';

// class
$jsonmessage = new JsonMessage();
$allHeader = new HeadersManager();

$allHeader->setAllHeaders();

$request = $_SERVER['REQUEST_METHOD'];

if($request !== 'POST'){

    $jsonmessage->errorMessage("failed", "method not allowed");

}

searchItem($jsonmessage);

function searchItem($jsonmessage) {

    

    $data = json_decode(file_get_contents("php://input"), true);

    if(empty($data)){

        $jsonmessage->errorMessage("error", "empty data");

    }

    $id = filter_var($data["id"] ?? 0 , FILTER_SANITIZE_NUMBER_INT);
    $title = htmlspecialchars($data["title"] ?? "", ENT_QUOTES, 'UTF-8');
    $sku = htmlspecialchars($data["sku"] ?? "", ENT_QUOTES, 'UTF-8');

    if(empty($id) || empty($title) || empty($sku)){

        $jsonmessage->errorMessage("failed", "some filed are missing");

    }

    //searchig in array for product

    $all_products = json_decode(file_get_contents("products.json"), true);
    $products = $all_products["products"] ?? [];

    $fullItem = [];

    foreach($products as $item){

        if(!isset($item["id"]) && !isset($item["title"]) && !isset($item["sku"])){

            continue;
        }

        if($item["id"] == $id && $item["title"] == $title && $item["sku"] == $sku){

            $fullItem[] = $item;

            break;
        }
    
    }

    if(!empty($fullItem)){

        $jsonmessage->successMessage("success", "product found", "products", $fullItem);

    }else{

        $jsonmessage->errorMessage("failed", "item not matched");
    }

}//function end
