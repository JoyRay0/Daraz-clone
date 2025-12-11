<?php

header("Content-Type: application/json; charset=UTF-8");


class JsonMessage{

    public function errorMessage(string $status, string $message){
      
        echo json_encode([
            "status"=> $status,
            "message"=> $message
        ]);

        exit;

    }

    public function successMessage(string $status, string $message, string $itemName ,array $data){

        echo json_encode([
            "status"=> $status,
            "message"=> $message,
            $itemName => $data
        ]);

        exit;

    }

}