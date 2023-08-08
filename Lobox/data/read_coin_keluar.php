<?php
class user{}
$sql = "Select*from coin_keluar";
require_once('koneksi.php');
$r = mysqli_query($con,$sql);
$json = array();
while ($row = mysqli_fetch_assoc($r)){
    $json[] = $row;
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
