<#import "parts/common.ftl" as common>
<#import "parts/menus.ftl" as adminNav>

<@common.page>
    <@adminNav.adminNav/>
    <div class="container col-7 text-center" style="padding-top: 10vh">
        <h4 style="padding-top: 3vh;padding-bottom: 5vh;">QR code</h4>
        <h6 style="padding-bottom: 5vh;color: red;">${text}</h6>

        <form class="form-signin align-middle" role="form"  action="/QRcode" method="post" >
            <input type="text" class="form-control" name="id" placeholder="ID груза" style="margin-bottom: 3vh;">
            <input type="text" class="form-control" name="qty" placeholder="Количество" style="margin-bottom: 3vh;">
            <input type="hidden" name="_csrf" value="${_csrf.token}" /> <!--Для безопасности-->
            <button class="btn btn-md btn-primary col-md-6" type="submit" style="margin-top: 2vh">Генерация</button>
        </form>

    </div>
</@common.page>
