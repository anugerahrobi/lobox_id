<?php
class user{}
if ($_SERVER['REQUEST_METHOD']=='POST'){
    $id_coin = $_POST['id_coin'];
    $id_user = $_POST['id_user'];
    $saldo = $_POST['saldo'];
    $jenis_pembayaran = $_POST['jenis_pembayaran'];
    $sql = "update coin_keluar set id_user='$id_user',saldo='$saldo', jenis_pembayaran='$jenis_pembayaran' where id_coin='$id_coin'";
    echo $sql;
    require_once('koneksi.php');
if (mysqli_query($con,$sql)){
    $response = new user();
    $response -> success = 1;
    $response -> message = "Data berhasil di simpan";
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
