Login Spec
==========================
tags: LoginCases,AllTest

* Open the Yemeksepeti homepage
* Go to Yemek Sepeti City Page

Wrong Username and Password Login
-----------
tags:WrongMailAndPass
* Login with Wrong Username and Wrong Password Yemeksepeti
* Check "Hatalı giriş" for failed login

Correct username and wrong password login test
-----------
tags:CorrectUserWrongPass
* Login with Success Username and Wrong Password Yemeksepeti
* Check "Hatalı giriş" for failed login

Empty username and password login test
-----------
tags:Emptyuserandpass
* Login with Empty Username and Empty Password Yemeksepeti
* Check "Lütfen kullanıcı adınızı/e-postanızı giriniz" for "mail" null charachter login
* Check "Lütfen şifrenizi giriniz." for "pass" null charachter login

Wrong username and empty password login test
-----------
tags:WrongUserEmptyPass
* Login with Wrong Username and Empty Password Yemeksepeti
* Check "Lütfen şifrenizi giriniz." for "pass" null charachter login

Correct username and empty password login test
-----------
tags:CorrectUserEmptyPass
* Login with Success Username and Empty Password Yemeksepeti
* Check "Lütfen şifrenizi giriniz." for "pass" null charachter login

Empty username and wrong password login test
-----------
tags:EmptyUserWrongPass
*Login with Empty Username and Wrong Password Yemeksepeti
* Check "Lütfen kullanıcı adınızı/e-postanızı giriniz" for "mail" null charachter login

Successful Login
-----------
tags:SucLogin
* Login with Success Username and Success Password Yemeksepeti
* Check "Yemek Sepeti" After Login