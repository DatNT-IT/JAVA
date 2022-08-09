<%--
  Created by IntelliJ IDEA.
  User: DAT IT
  Date: 3/14/2022
  Time: 2:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>login</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css"
          integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
    <title>Bootstrap 4 Login/Register Form</title>
   <style>

       #logreg-forms {
           width: 412px;
           margin: 10vh auto;
           background-color: #f3f3f3;
           box-shadow: 0 1px 3px rgba(0, 0, 0, 0.12), 0 1px 2px rgba(0, 0, 0, 0.24);
           transition: all 0.3s cubic-bezier(.25, .8, .25, 1);
       }

       #logreg-forms form {
           width: 100%;
           max-width: 410px;
           padding: 15px;
           margin: auto;
       }

       #logreg-forms .form-control {
           position: relative;
           box-sizing: border-box;
           height: auto;
           padding: 10px;
           font-size: 16px;
       }

       #logreg-forms .form-control:focus {
           z-index: 2;
       }

       #logreg-forms .form-signin input[type="email"] {
           margin-bottom: -1px;
           border-bottom-right-radius: 0;
           border-bottom-left-radius: 0;
       }

       #logreg-forms .form-signin input[type="password"] {
           border-top-left-radius: 0;
           border-top-right-radius: 0;
       }

       #logreg-forms .social-login {
           width: 390px;
           margin: 0 auto;
           margin-bottom: 14px;
       }

       #logreg-forms .social-btn {
           font-weight: 100;
           color: white;
           width: 190px;
           font-size: 0.9rem;
       }

       #logreg-forms a {
           display: block;
           padding-top: 10px;
           color: lightseagreen;
       }

       #logreg-form .lines {
           width: 200px;
           border: 1px solid red;
       }


       #logreg-forms button[type="submit"] {
           margin-top: 10px;
       }

       #logreg-forms .facebook-btn {
           background-color: #3C589C;
       }

       #logreg-forms .google-btn {
           background-color: #DF4B3B;
       }

       #logreg-forms .form-reset, #logreg-forms .form-signup {
           display: none;
       }

       #logreg-forms .form-signup .social-btn {
           width: 210px;
       }

       #logreg-forms .form-signup input {
           margin-bottom: 2px;
       }

       .form-signup .social-login {
           width: 210px !important;
           margin: 0 auto;
       }

       /* Mobile */

       @media screen and (max-width: 500px) {
           #logreg-forms {
               width: 300px;
           }

           #logreg-forms .social-login {
               width: 200px;
               margin: 0 auto;
               margin-bottom: 10px;
           }

           #logreg-forms .social-btn {
               font-size: 1.3rem;
               font-weight: 100;
               color: white;
               width: 200px;
               height: 56px;

           }

           #logreg-forms .social-btn:nth-child(1) {
               margin-bottom: 5px;
           }

           #logreg-forms .social-btn span {
               display: none;
           }

           #logreg-forms .facebook-btn:after {
               content: 'Facebook';
           }

           #logreg-forms .google-btn:after {
               content: 'Google+';
           }

       }
       .error {
           color: red;
       }
   </style>
    <%--    casi phaan trang cung the goi dao la loi--%>
</head>
<body>
<div id="logreg-forms">
    <form:form class="form-signin" action="/loginacc" method="post" modelAttribute="acc">
        <h1 class="h3 mb-3 font-weight-normal" style="text-align: center"> Sign in</h1>
        <div class="social-login">
            <button class="btn facebook-btn social-btn" type="button"><span><i class="fab fa-facebook-f"></i> Sign in with Facebook</span>
            </button>
            <button class="btn google-btn social-btn" type="button"><span><i class="fab fa-google-plus-g"></i> Sign in with Google+</span>
            </button>
        </div>
        <p style="text-align:center"> OR </p>
        <p class="text-danger">${mess}</p>
        <input type="text" path="email" value="${acc.getEmail()}" name="email" id="inputEmail" class="form-control" />
        <form:errors path="email" cssClass="error"/>
        <input type="password" name="pass" value="${acc.getPass()}" path="pass" class="form-control">
        <form:errors path="pass" cssClass="error"/>
        <div>
            <input name="remember" value="1" type="checkbox" class="form-check-input" id="exampleCheck1">
            <label class="form-check-label" for="exampleCheck1">Remember me</label>
        </div>
        <button class="btn btn-success btn-block" type="submit"><i class="fas fa-sign-in-alt"></i> Sign in</button>
        <a class="nav-link" href="../QR"><button>QR CODE</button></a>
        <a href="#" id="forgot_pswd">Forgot password?</a>
        <hr>
        <!-- <p>Don't have an account!</p>  -->
        <button class="btn btn-primary btn-block" type="button" id="btn-signup"><i class="fas fa-user-plus"></i> Sign up
            New Account
        </button>
    </form:form>

    <form action="/reset/password/" class="form-reset">
        <input type="email" id="resetEmail" class="form-control" placeholder="Email address" required="" autofocus="">
        <button class="btn btn-primary btn-block" type="submit">Reset Password</button>
        <a href="#" id="cancel_reset"><i class="fas fa-angle-left"></i> Back</a>
    </form>

    <form action="signup" class="form-signup" method="post" modelAttribute="acc">
        <div class="social-login">
            <button class="btn facebook-btn social-btn" type="button"><span><i class="fab fa-facebook-f"></i> Sign up with Facebook</span>
            </button>
        </div>
        <div class="social-login">
            <button class="btn google-btn social-btn" type="button"><span><i class="fab fa-google-plus-g"></i> Sign up with Google+</span>
            </button>
        </div>

        <p style="text-align:center">OR</p>

        EMAIL :
        <input type="text" name="email" id="user-email" class="form-control" path="email" value="${acc.getEmail()}"><br>
        PASSWORD :
        <input type="text" name="pass" id="user-pass" class="form-control" path="pass" value="${acc.getPass()}">
        <button class="btn btn-primary btn-block" type="submit"><i class="fas fa-user-plus"></i> Sign Up</button>
        <a href="#" id="cancel_signup"><i class="fas fa-angle-left"></i> Back</a>
    </form>
    <br>

</div>
<p style="text-align:center">
    <a href="http://bit.ly/2RjWFMfunction toggleResetPswd(e){
    e.preventDefault();
    $('#logreg-forms .form-signin').toggle() // display:block or none
    $('#logreg-forms .form-reset').toggle() // display:block or none
}

function toggleSignUp(e){
    e.preventDefault();
    $('#logreg-forms .form-signin').toggle(); // display:block or none
    $('#logreg-forms .form-signup').toggle(); // display:block or none
}

$(()=>{
    // Login Register Form
    $('#logreg-forms #forgot_pswd').click(toggleResetPswd);
    $('#logreg-forms #cancel_reset').click(toggleResetPswd);
    $('#logreg-forms #btn-signup').click(toggleSignUp);
    $('#logreg-forms #cancel_signup').click(toggleSignUp);
})g" target="_blank" style="color:black">By IT</a>
</p>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>
<script>
    function toggleResetPswd(e) {
        e.preventDefault();
        $('#logreg-forms .form-signin').toggle() // display:block or none
        $('#logreg-forms .form-reset').toggle() // display:block or none
    }

    function toggleSignUp(e) {
        e.preventDefault();
        $('#logreg-forms .form-signin').toggle(); // display:block or none
        $('#logreg-forms .form-signup').toggle(); // display:block or none
    }

    $(() => {
        // Login Register Form
        $('#logreg-forms #forgot_pswd').click(toggleResetPswd);
        $('#logreg-forms #cancel_reset').click(toggleResetPswd);
        $('#logreg-forms #btn-signup').click(toggleSignUp);
        $('#logreg-forms #cancel_signup').click(toggleSignUp);
    })
</script>
</body>
</html>
