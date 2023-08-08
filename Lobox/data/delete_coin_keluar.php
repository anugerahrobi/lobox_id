<?php
class user{}
if ($_SERVER['REQUEST_METHOD']=='POST'){
    $id_coin = $_POST['id_coin'];
    $id_user = $_POST['id_user'];
    $saldo = $_POST['saldo'];
    $jenis_pembayaran = $_POST['jenis_pembayaran'];
    $waktu = $_POST['waktu'];
    $sql = "delete from tb_coin_keluar where id_data='$id_coin'";
    require_once('koneksi.php');
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
}
?>
