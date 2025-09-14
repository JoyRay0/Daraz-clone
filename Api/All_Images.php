<?php

header("Content-Type: application/json; charset=UTF-8");
header("Access-Control-Allow-Methods: GET, POST, PUT, DELETE");
header("Cache-Control: public, max-age=3600");


$method = $_SERVER['REQUEST_METHOD'];

$resource = $_GET['resource'];

if ($method !== 'GET'){

   die(json_encode([

       'status' => 'failed',
       'message' => 'Method not allowed'

   ]));
}

//checking the res method for proper images
switch ($resource) {

    //item images----------------------------------------------------------------------
    case 'item':

        $images = [

            [
                'id' => '1',
                'image' => 'https://shorturl.at/cUd1K',
                'text' => '9.9 Sale'
            ],
            [
                'id' => '2',
                'image' => 'https://shorturl.at/ZPGcN',
                'text' => 'Buy Any 4'
            ],
            [
                'id' => '3',
                'image' => 'https://shorturl.at/Qb1Eg',
                'text' => 'Free Delivery'
            ],
            [
                'id' => '4',
                'image' => 'https://thumbs.dreamstime.com/b/mobile-payment-illustration-dollar-coin-381118117.jpg',
                'text' => 'Quick Recharge'
            ],
            [
                'id' => '5',
                'image' => 'https://thumbs.dreamstime.com/b/buy-more-save-more-sale-banner-design-template-mega-discount-tag-app-icon-vector-illustration-buy-more-save-more-sale-banner-148154723.jpg',
                'text' => 'Buy More Save More'
            ],
            [
                'id' => '6',
                'image' => 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQfCCENTP2oY_daSO6c-nfT_J56K715qns-RQ&s',
                'text' => 'Beauty'
            ],
            [
                'id' => '7',
                'image' => 'https://rb.gy/0pi91j',
                'text' => 'New Arrivals'
            ],
            [
                'id' => '8',
                'image' => 'https://outsourcingtrainingbd.com/wp-content/uploads/2023/12/Affiliate-Marketing-1.webp',
                'text' => 'Affiliate Program'
            ],
            [
                'id' => '9',
                'image' => 'https://cdn-icons-png.flaticon.com/512/12427/12427936.png',
                'text' => 'More'
            ]


        ];
        break;

    //viewpager images----------------------------------------------------------------------
    case 'viewpager':
        $images = [

            [
                'id' => '1',
                'vp_image' => 'https://shorturl.at/jtLbj',
                'text' => '9.9 Sale'

            ],
            [
                'id' => '2',
                'vp_image' => 'https://img.lazcdn.com/us/lazada_mars_image/0e05a4524b07d65669f8b4cacf38c17d.jpg',
                'text' => '60 % of coin '
            ],
            [
                'id' => '3',
                'vp_image' => 'https://img.lazcdn.com/us/lazada_mars_image/27dfe0967413cfe5cf4cc3943c4e1c96.png_2200x2200q80.png',
                'text' => 'Free Delivery'
            ],
            [
                'id' => '4',
                'vp_image' => 'https://storage.googleapis.com/pickaboo-prod/media/dcastalia_hybridslider/image/Ac_App_banner_.jpg',
                'text' => '30 % of AC'
            ]

        ];
        break;

    //category_image images----------------------------------------------------------------------
    case 'category_image':
        $images = [

            [
                'id' => '1',
                'cate_image' => '',
                'text' => ''
            ],
            [
                'id' => '2',
                'cate_image' => '',
                'text' => ''
            ],
            [
                'id' => '3',
                'cate_image' => '',
                'text' => ''
            ]

        ];
        break;
    //other_images images----------------------------------------------------------------------
    case 'other_images':
        $images = [

            [
                'id' => '1',
                'oth_image' => '',
                'text' => ''
            ],
            [
                'id' => '2',
                'oth_image' => '',
                'text' => ''
            ],
            [
                'id' => '3',
                'oth_image' => '',
                'text' => ''
            ]

        ];
        break;

    default:
        die(json_encode([

            'status' => 'failed',
            'message' => 'Resource not found'

        ]));
}
echo json_encode([

    'status' => 'successful',
    'images' => $images

]);
