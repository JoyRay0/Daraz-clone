<?php

header("Content-Type: application/json; charset=UTF-8");

$request = $_SERVER['REQUEST_METHOD'];

if($request !== 'POST'){

    emptyData("failed", "method not allowed");

}

searchItem();

function searchItem() {

    
    $data = json_decode(file_get_contents("php://input"), true);

    if(empty($data)){

        emptyData("error", "empty data");

    }

    $id = filter_var($data["id"] ?? "", FILTER_SANITIZE_NUMBER_INT);
    $title = htmlspecialchars($data["title"] ?? "", ENT_QUOTES, 'UTF-8');
    $sku = htmlspecialchars($data["sku"] ?? "", ENT_QUOTES, 'UTF-8');

    if(empty($id) || empty($title) || empty($sku)){

        emptyData("failed", "some filed are missing");

    }

}

function emptyData(string $status, string $message) {

    echo json_encode([
        "status"=> $status,
        "message" => $message
    ]);
    exit;

}
 





