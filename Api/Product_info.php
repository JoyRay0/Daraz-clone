<?php

require_once __DIR__ .'/JsonMessage.php';
require_once __DIR__ . '/Header.php';

// class
$jsonmessage = new JsonMessage();
$allHeader = new HeadersManager();

$allHeader->setAllHeaders();

$request = $_SERVER['REQUEST_METHOD'];
$res = $_GET["res"] ?? "";

if($request !== 'POST'){

    $jsonmessage->errorMessage("failed", "method not allowed");

}

$data = json_decode(file_get_contents("php://input"), true);

if(empty($data)){

    $jsonmessage->errorMessage("error", "empty data");

}

switch($res){

    case "full_info":

        searchItem($data);
        break;

    case "image":

        findImages($data);
        break;

    default: $jsonmessage->errorMessage("error", "wrong re method");

}


function searchItem($data) {

    global $jsonmessage;

    $id = filter_var($data["id"] ?? 0 , FILTER_SANITIZE_NUMBER_INT);
    $sku = htmlspecialchars($data["sku"] ?? "", ENT_QUOTES, 'UTF-8');

    if(empty($id) || empty($sku)){

        $jsonmessage->errorMessage("failed", "some filed are missing");

    }

    //searchig in array for product

    $all_products = json_decode(file_get_contents("products.json"), true);
    $products = $all_products["products"] ?? [];

    $fullItem = [];

    foreach($products as $item){

        if(!isset($item["id"]) && !isset($item["sku"])){

            continue;
        }

        if($item["id"] == $id && $item["sku"] == $sku){

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

function findImages($data){

    global $jsonmessage;

    $sku = trim(htmlspecialchars($data["sku"] ?? "", ENT_QUOTES, 'UTF-8'));

    if(empty($sku)){

        $jsonmessage->errorMessage("failed", "sku missing");

    }

    //searchig in array for product

    $all_products = json_decode(file_get_contents("products.json"), true);
    $products = $all_products["products"] ?? [];

    $fullItem = [];

    foreach($products as $item){

        if(!isset($item["sku"])){

            continue;
        }

        if($item["sku"] === $sku){

            $fullItem[] = $item;

            break;
        }
    
    }

    if(!empty($fullItem)){

        $jsonmessage->successMessage("success", "product found", "products", $fullItem);

    }else{

        $jsonmessage->errorMessage("failed", "item not matched");
    }

}//function
