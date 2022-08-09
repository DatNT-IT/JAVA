
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <script src="js/html5-qrcode.min.js"></script>
    <style>
        .result {
            background-color: green;
            color: #fff;
            padding: 20px;
        }

        .row {
            display: flex;
        }
    </style>
</head>

<body>
<h1 style="">QR code</h1>
<div class="row">
    <div class="col">
        <div style="width:500px;" id="reader"></div>
    </div>
</div>
<button><a href="login.jsp">back</a></button>

<script type="text/javascript">
    function onScanSuccess(qrCodeMessage) {
        let text = "bạn có muốn truy cập shop";
        if (confirm(text) == true) {
            location.href = "../QRquet?id=" + qrCodeMessage;
        } else {
            location.href = "../loginacc";
        }
    }


    function onScanError(errorMessage) {
        //handle scan error
    }

    var html5QrcodeScanner = new Html5QrcodeScanner(
        "reader", {
            fps: 10,
            qrbox: 250
        });
    html5QrcodeScanner.render(onScanSuccess, onScanError);
</script>
</body>

</html>