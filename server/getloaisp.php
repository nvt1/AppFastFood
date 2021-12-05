 <?php
    include "connect.php";
    $query = "SELECT * FROM loaisanpham";
    $data = mysqli_query($conn, $query);
    $result = array();
    while($row = mysqli_fetch_assoc($data)){
        $result[] = ($row);
        //code...
           
    }
    //print_r($result);
if(!empty($result)){
	$arr = [
		'success' => true,
		'message' => "thanh cong",
		'result' => $result
	];
}else{
		$arr = [
		'success' => false,
		'message' => "khong thanh cong",
		'result' => $result
	];
}

print_r(json_encode($arr));



    // include "connect.php";
    // $query = "SELECT * FROM loaisanpham";
    // $data = mysqli_query($conn, $query);
    // $mangloaisp = array();
    // while($row = mysqli_fetch_assoc($data)){
    //     array_push($mangloaisp, new Loaisp(
    //         $row['id'],
    //         $row['tenloaisanpham'],
    //         $row['hinhanhloaisanpham']));
    // }
    // echo json_encode($mangloaisp);
    // class Loaisp{
    //     function __construct ($id, $tenloaisp, $hinhanhloaisp){
    //         $this->id = $id;
    //         $this->tenloaisp = $tenloaisp;
    //         $this->hinhanhloaisp = $hinhanhloaisp;
    //     }
    // }


?>
