<?php 

require_once __DIR__ . "/JsonMessage.php";

header('Content-Type: application/json');

$jsonmessage = new JsonMessage();

$page = (!empty($_GET['page']) && (int)$_GET['page'] > 0 ) ? (int)$_GET['page']  : 1;
$limit = 20;

$file = 'products.json';

$allProducts = json_decode(file_get_contents($file), true);
$products = $allProducts['products'] ?? [];

$offset = ($page - 1) * $limit;

$paginate = array_slice($products, $offset, $limit);

$totalItem = count($products);
$totalPage = ceil($totalItem / $limit);


echo json_encode([

    "status" => "Success",
    "page" => $page,
    "totel_pages" => $totalPage,
    "data" => $paginate

]);


