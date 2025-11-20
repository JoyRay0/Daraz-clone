<?php 

class Pagination{

    public function fetch(int $page, int $limitData, string $tableName):array{

        //$_GET['page']

        $pages = (!empty($page) && (int)$page > 0) ? (int)$page : 1;

        $limit = $limitData;

        $dbHost = getenv('DB_HOST');
        $dbName = getenv('DB_NAME');
        $dbUserName = getenv('DB_USERNAME');
        $dbPassword = getenv('DB_PASSWORD');

        $connect = mysqli_connect($dbHost, $dbUserName, $dbPassword, $dbName);

        if(!$connect){
            die("connection failed".mysqli_connect_error());
        }


        $result = mysqli_query($connect, "SELECT COUNT(*) as total FROM $tableName");
        $row = mysqli_fetch_assoc($result);

        $totalItem = (int)$row['total'];

        $offset = ($pages - 1) *$limit;

        $totalPages = ceil($totalItem / $limit);


        $item = [
            "offset" => $offset,
            "totlaPages" => $totalPages,
            "page" => $pages,
            "totalItem" => $totalItem
        ];

        return $item;

    }

}
?>