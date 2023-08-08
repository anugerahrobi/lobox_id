<?php
class user{}
$username = $_POST['username'];
$password = $_POST['password'];

$sql = "Select * from tb_user where username='$username' and password='$password'";
require_once('koneksi.php');
$r = mysqli_query($con,$sql);
$json = array();
while ($row = mysqli_fetch_assoc($r)){
    $json = $row;
    break;
}
if (mysqli_query($con,$sql)){
    $response = new user();
    $response -> success = 1;
    $response -> message = "Data berhasil di simpan";
    $response -> data = $json;
    die (json_encode($response));
}else {
    $response = new user();
    $response->success = 0;
    $response->message = "Data gagal di simpan";
    die (json_encode($response));
}

mysqli_close($con);

?>
