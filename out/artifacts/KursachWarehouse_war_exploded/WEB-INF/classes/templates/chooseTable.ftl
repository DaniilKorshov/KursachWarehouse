<#import "parts/common.ftl" as common>
<#import "parts/menus.ftl" as adminNav>

<@common.page>
    <@adminNav.adminNav/>
    <div class="container" style="padding-top: 5vh;">
        <div class="card-deck mb-3 text-center">
            <@adminNav.tableBlock "Пользователи" "Информация о пользователях" "/users"/>
            <@adminNav.tableBlock "Складские заказы" "Информация о складских заказах" "/warehouseOrder"/>
            <@adminNav.tableBlock "Линии заказов" "Информация о линиях заказов" "/warehouseOrderLine"/>
        </div>
    </div>
    <div class="container" style="padding-top: 3vh;">
        <div class="card-deck mb-3 text-center">
            <@adminNav.tableBlock "Наличие" "Информация о количестве продукции и материалов" "/inventSum"/>
            <@adminNav.tableBlock "Грузы" "Информация о продукции и материалах" "/invent"/>
            <@adminNav.tableBlock "Комплектации" "Информация о комплектациях продукции" "/dimGroup"/>
        </div>
    </div>
    <div class="container" style="padding-top: 3vh;">
        <div class="card-deck mb-3 text-center">
            <@adminNav.tableBlock "Ячейки" "Информация о ячейках" "/cells"/>
            <@adminNav.tableBlock "Складские помещения" "Информация о складских помещениях" "/warehouse"/>
        </div>
    </div>
</@common.page>