
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
<!--    <link rel="icon" href="img/favicon.ico">-->

    <title>Signin Template for Bootstrap</title>

<#--    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">-->
    <link href="../assets/css/bootstrap.min.css" rel="stylesheet">
    <link type="text/css" rel="stylesheet" href="../assets/css/style.css"/>
</head>

<body class="text-center">
    <form class="form-signin" action="/login" method="POST">
        <svg width="30%" height="30%" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px" viewBox="0 0 1000 1000" enable-background="new 0 0 1000 1000" xml:space="preserve">
<g><path d="M500,10C231.5,10,10,231.5,10,500s221.5,490,490,490s490-221.5,490-490S768.5,10,500,10z M795.3,835.6c0-134.2-100.7-248.4-221.5-281.9c73.8-26.8,120.8-100.7,120.8-181.2c0-107.4-87.3-194.7-194.7-194.7s-194.7,87.3-194.7,194.7c0,80.5,53.7,154.4,120.8,181.2c-127.5,33.6-221.5,147.7-221.5,281.9C110.7,755.1,50.3,634.2,50.3,500C50.3,251.6,251.6,50.3,500,50.3c248.4,0,449.7,201.4,449.7,449.7C949.7,634.2,889.3,755.1,795.3,835.6z"/></g>
</svg>
<#--        <img class="mb-4" src="https://getbootstrap.com/assets/brand/bootstrap-solid.svg" alt="" width="72" height="72">-->
        <h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>
        <h2 style="color: red"><#if message??>${message}</#if></h2>
        <label for="inputEmail" class="sr-only">Email address</label>
        <input name="email" type="email" id="inputEmail" class="form-control" value="roman@gmail.com" placeholder="Email address" required autofocus>
        <label for="inputPassword" class="sr-only">Password</label>
        <input name="password" type="password" id="inputPassword" class="form-control" value="1" placeholder="Password" required>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
        <p class="mt-5 mb-3 text-muted">&copy; Tinder 2021</p>
    </form>
</body>
</html>