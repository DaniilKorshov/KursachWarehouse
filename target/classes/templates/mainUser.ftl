<#import "parts/common.ftl" as common>
<#import "parts/menus.ftl" as adminNav>

<@common.page>
    <@adminNav.userNav/>

    <div class="container">
        <div class="row featurette">
            <div class="col-12">
                <h2 class="featurette-heading" style="padding-top: 15vh;text-align: center">Вы вошли как ${userNS}</h2>
                <h5 style="text-align: center;">Телефон: ${userPhone}</h5>
                <h5 style="text-align: center;">Email: ${userEmail}</h5>
            </div>
        </div>
    </div>
</@common.page>

