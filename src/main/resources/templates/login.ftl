<#import "parts/common.ftl" as common>

<@common.page>
    <div class="container col-7 text-center" style="padding-top: 10vh">
        <h2 class="form-signin-heading " style="padding-top: 5vh;padding-bottom: 5vh;">WAREHOUSE</h2>

        <form class="form-signin align-middle" role="form" action="/login" method="post" >
            <input type="email" class="form-control" name="email" placeholder="Email"
                   style="margin-bottom: 3vh;">
            <input type="password" class="form-control" name="password" placeholder="Пароль" style="margin-bottom: 3vh;">
            <input type="hidden" name="_csrf" value="${_csrf.token}" /> <!--Для безопасности-->
            <button class="btn btn-md btn-primary col-md-6" type="submit" style="margin-top: 2vh">Авторизация</button>
            <a class="btn btn-md btn-outline-primary col-6" href="/registration" role="button" style="margin-top: 2vh">Регистрация</a>
        </form>

    </div>
</@common.page>
