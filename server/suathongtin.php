 <?php
    include "connect.php";
    $username = $_POST['username'];
    $mobile = $_POST['mobile'];
    $id = $_POST['id'];
      

 if($_SERVER['REQUEST_METHOD'] == "POST" ){
      $query = "UPDATE `user` SET `username`= '$username', `mobile`='$mobile' WHERE `id` = '$id' ";
         $data = mysqli_query($conn, $query);

         if($data ==  true){
            $arr = [
               'success' => true,
               'message' => "thanh cong"
            
            ];
         }else{
               $arr = [
               'success' => false,
               'message' => "khong thanh cong"
               
            ];
         }
            //echo $numrow;
   
   }else{
         
                     $arr = [
         'success' => false,
         'message' => "Email đã tồn tại"
      
            ];
}

print_r(json_encode($arr));


?>


