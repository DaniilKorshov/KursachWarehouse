<#import "parts/common.ftl" as common>
<#import "parts/menus.ftl" as adminNav>

<@common.page>
    <@adminNav.adminNav/>

    <div class="container">
        <div class="row featurette">
            <div class="col-12">
                <h2 class="featurette-heading" style="padding-top: 15vh;text-align: center">Вы вошли как ${userNS}</h2>
            </div>
        </div>
    </div>
</@common.page>

