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
                'cate_image' => 'https://cdn-icons-png.flaticon.com/128/1628/1628388.png',
                'text' => 'For you'
            ],
            [
                'id' => '2',
                'cate_image' => 'https://cdn-icons-png.flaticon.com/128/3534/3534312.png',
                'text' => 'Women & Girls Fashion'
            ],
            [
                'id' => '3',
                'cate_image' => 'https://cdn-icons-png.flaticon.com/128/343/343418.png',
                'text' => 'Men & Boys Fashion'
            ],
            [
                'id' => '4',
                'cate_image' => 'https://cdn-icons-png.flaticon.com/128/4319/4319109.png',
                'text' => 'Electronic Accessories'
            ],
            [
                'id' => '5',
                'cate_image' => 'https://cdn-icons-png.flaticon.com/128/4352/4352957.png',
                'text' => 'TV & Home Appliances'
            ],
            [
                'id' => '6',
                'cate_image' => 'https://cdn-icons-png.flaticon.com/128/639/639371.png',
                'text' => 'Electronic Devices'
            ],
            [
                'id' => '7',
                'cate_image' => 'https://cdn-icons-png.flaticon.com/128/1746/1746356.png',
                'text' => 'Mother & Baby'
            ],
            [
                'id' => '8',
                'cate_image' => 'https://cdn-icons-png.flaticon.com/128/3097/3097182.png',
                'text' => 'Automotive & Motorbike'
            ],
            [
                'id' => '9',
                'cate_image' => 'https://cdn-icons-png.flaticon.com/128/18899/18899933.png',
                'text' => 'Sports & outdoors'
            ],
            [
                'id' => '10',
                'cate_image' => 'https://cdn-icons-png.flaticon.com/128/17441/17441258.png',
                'text' => 'Home & Lifestyle'
            ],
            [
                'id' => '11',
                'cate_image' => 'https://cdn-icons-png.flaticon.com/128/859/859270.png',
                'text' => 'Groceries'
            ]

        ];
        break;
    //other_images images----------------------------------------------------------------------
    case 'other_images':
        $images = [

            [
                'id' => '1',
                'oth_image' => 'https://blog.daraz.com.bd/wp-content/uploads/2020/08/New-User-Voucher-of-Daraz.jpg',
                'text' => 'Coupon image'
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

    //flash sale images----------------------------------------------------------------------
    case 'flash_sale_images':
        $images = [

            [
                'id' => '1',
                'sale_image' => 'https://images.rawpixel.com/image_800/cHJpdmF0ZS9sci9pbWFnZXMvd2Vic2l0ZS8yMDIyLTExL3JtMzYyLTAxYS1tb2NrdXAuanBn.jpg',
                'text' => 'Only 10 left',
                'price' => '৳200',
                'discount' => '-50%'
            ],
            [
                'id' => '2',
                'sale_image' => 'https://plus.unsplash.com/premium_photo-1664392147011-2a720f214e01?fm=jpg&q=60&w=3000&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MXx8cHJvZHVjdHxlbnwwfHwwfHx8MA%3D%3D',
                'text' => '300 Sold',
                'price' => '৳500',
                'discount' => '-30%'
            ],
            [
                'id' => '3',
                'sale_image' => 'https://off.com.ph/_next/image?url=https%3A%2F%2Fedge.sitecorecloud.io%2Fscjohnsonana080-dart-production-40df%2Fmedia%2Fproject%2Fdart%2Foff%2Fphilippines%2Fhomepage%2Fcategory-images%2Foff_ph_4x3_720x540_category_card-overtime.png%3Fh%3D540%26iar%3D0%26w%3D720&w=3840&q=75',
                'text' => 'Only 2 left',
                'price' => '৳100',
                'discount' => '-30%'
            ]

        ];
        break;

    //message----------------------------------------------------------
    case 'messages':

        $images = [

            [
            'id' => '1',
            'title' =>  'MEGA DEALS শেষ সুযোগ🛍️🔥',
            'date' => '12/9/2025',
            'image' => 'https://icms-image.slatic.net/images/ims-web/899c7769-8408-4ccf-a9af-85ab4e42e063.png',
            'offer_message' => 'DOUBLE VOUCHER, FREE DELIVERY, 9 delas এই সব একসাথে পেতে Order Now',
            'item' => 'activities'
            ],
            [
            'id' => '2',
            'title' =>  '৫০% পর্যন্ত ছাড়🤯',
            'date' => '7/9/2025',
            'image' => 'https://icms-image.slatic.net/images/ims-web/b4b529e4-9c5d-472e-a511-1a8f7e1ffa22.jpg',
            'offer_message' => 'খুব কম সময়ের জন্য',
            'item' => 'promos'
            ],
            [
            'id' => '3',
            'title' =>  'Pre sale offer✨',
            'date' => '20/9/2025',
            'image' => 'https://www.tbsnews.net/sites/default/files/styles/big_2/public/images/2020/11/05/pr_banner_pre_sale.png',
            'offer_message' => 'এবার সব পাবেন বেশি বেশি',
            'item' => 'activities'
            ],
            [
            'id' => '4',
            'title' =>  '9.9 Sale',
            'date' => '19/9/2025',
            'image' => 'https://ecdn.dhakatribune.net/contents/cache/images/1200x630x1xxxxx1/uploads/media/2024/09/08/Celebrate-save-more-with-Daraz-9.9-Anniversary-Sale-2f8449884697883890564d40c840e1b0.jpg',
            'offer_message' => 'আজই দেখুন',
            'item' => 'promos'
            ],
            [
            'id' => '5',
            'title' =>  'Big Sale',
            'date' => '15/9/2025',
            'image' => 'https://brandpractitioners.com/wp-content/uploads/2023/05/Daraz-Launched-Monthly-Savings-Campaign-Daraz-er-Cherag-1.png',
            'offer_message' => 'কিনুন আর জিতুন',
            'item' => 'activities'
            ],
            [
            'id' => '6',
            'title' =>  'সহজ সমাধান',
            'date' => '17/9/2025',
            'image' => 'https://ecdn.dhakatribune.net/contents/cache/images/1200x630x1xxxxx1/uploads/dten/2022/04/05/daraz-logo.png',
            'offer_message' => 'সব কিছু এখন এক জায়গায়',
            'item' => 'activities'
            ],
           
            
        ];
        
        break;
        
     //GIF images----------------------------------------------------------------------
    case 'gifs':
        $images = [

            [
                'id' => '1',
                'gif' => 'https://rashad-stack-daraz.netlify.app/images/icons/261876cd-28b2-4dbc-b441-3bd8d054a571.gif',
                'gif_title' => 'Daraz Shopping',
                'gif_information' => 'Check and enjoy Shipping Fee',
                'gif_button' => 'Collect',
                
            ],
            [
                'id' => '2',
                'gif' => 'https://i.tribune.com.pk/media/images/Black-Friday-GIF-2/Black-Friday-GIF-2.gif',
                'gif_title' => 'Black Friday Offer',
                'gif_information' => 'Check out Black Friday Discount',
                'gif_button' => 'Check',
                
            ],
            

        ];
        break;  
        
     //item images----------------------------------------------------------------------
    case 'profile_item_images':
        $images = [

            [
                'id' => '1',
                'image' => 'https://cdn-icons-png.flaticon.com/128/16566/16566050.png',
                'text' => 'My Messages',
                'item' => 'message',
                
            ],
            [
                'id' => '2',
                'image' => 'https://cdn-icons-png.flaticon.com/128/16771/16771803.png',
                'text' => 'Buy Any 4',
                'item' => 'buy',
                
            ],
            [
                'id' => '3',
                'image' => 'https://cdn-icons-png.flaticon.com/128/2682/2682165.png',
                'text' => 'Daraz Look',
                'item' => 'look',
                
            ],
            [
                'id' => '4',
                'image' => 'https://cdn-icons-png.flaticon.com/128/10213/10213241.png',
                'text' => 'My Affiliates',
                'item' => 'affiliates',
                
            ],
            [
                'id' => '5',
                'image' => 'https://cdn-icons-png.flaticon.com/128/8073/8073476.png',
                'text' => 'Help Center',
                'item' => 'help',
                
            ],
            [
                'id' => '6',
                'image' => 'https://cdn-icons-png.flaticon.com/128/4753/4753415.png',
                'text' => 'Contact Customer Care',
                'item' => 'customer_care',
                
            ],
            [
                'id' => '7',
                'image' => 'https://cdn-icons-png.flaticon.com/128/1031/1031795.png',
                'text' => 'My Reviews',
                'item' => 'ratings',
                
            ],
            [
                'id' => '8',
                'image' => 'https://cdn-icons-png.flaticon.com/128/8983/8983163.png',
                'text' => 'Payment Options',
                'item' => 'payment',
                
            ],
            
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
