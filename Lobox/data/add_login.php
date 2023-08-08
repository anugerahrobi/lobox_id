<?php

class user {}

if($_SERVER['REQUEST_METHOD']=='POST'){

    $id_user = $_POST['id_user'];
    $username = $_POST['username'];
    $password = $_POST['password'];
    $email = $_POST['email'];
    $nama = $_POST['nama'];
    $no_hp = $_POST['no_hp'];
    $no_ktp = $_POST['no_ktp'];
    $alamat = $_POST['alamat'];
    $sql = "INSERT INTO tb_user (id_user,username,password,email,nama,no_hp,no_ktp,alamat) VALUES('$id_user','$username','$password','$email','$nama','$no_hp','$no_ktp','$alamat')";
    echo $sql;

    require_once('koneksi.php');

    if(mysqli_query($con,$sql)){
        $response = new user();
        $response->success = 1;
        $response->message = "Data berhasil di simpan";
        die (json_encode($response));
    }else{
        $response = new user();
        $response -> success = 0;
        $response -> message = "Data gagal di simpan";
        die(json_encode($response));
    }
    mysqli_close($con);
    
}
?>
