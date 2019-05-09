<#import "parts/common.ftl" as common>
<#import "parts/menus.ftl" as adminNav>

<@common.page>
    <@adminNav.adminNav/>
    <div class="container col-7 text-center" style="padding-top: 10vh">
        <h4 style="padding-top:1vh;padding-bottom:2vh;text-align: center">REGISTRATION</h4>
        <h6 style="padding-top:2vh;padding-bottom:2vh;text-align: center"><#if message??>${message}<#else>Впишите данные</#if></h6>

        <form class="form-signin align-middle" role="form" action="/registration" method="post" >
            <input type="email" class="form-control" name="email" placeholder="Email" required="" autofocus=""
                   style="margin-bottom: 3vh;">
            <input type="password" class="form-control" name="password" placeholder="Пароль" style="margin-bottom: 3vh;">
            <input class="form-control" name="name" placeholder="Имя" style="margin-bottom: 3vh;">
            <input class="form-control" name="surname" placeholder="Фамилия" style="margin-bottom: 3vh;">
            <input class="form-control" name="phone_number" placeholder="Номер телефона" style="margin-bottom: 3vh;">
            <input type="hidden" name="_csrf" value="${_csrf.token}"> <!--Для безопасности-->
            <button class="btn btn-md btn-primary col-6" type="submit" style="margin-top: 2vh">Регистрация</button>
            <a class="btn btn-md btn-outline-primary col-6" href="/login" role="button" style="margin-top: 2vh">Авторизация</a>
        </form>
    </div>
</@common.page>
