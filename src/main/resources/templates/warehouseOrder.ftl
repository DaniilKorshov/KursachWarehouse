<#import "parts/common.ftl" as common>
<#import "parts/menus.ftl" as menus>

<@common.page>
    <@menus.adminNav/>
    <@menus.tableHead "Таблица складских заказов" "ID склада" "/warehouseOrders"/>
    <div class="table-responsive" style="text-align: center;">
        <table class="table table-hover table-bordered ">
            <thead>
            <tr>
                <th scope="row">#</th>
                <th>Тип</th>
                <th>Описание</th>
                <th colspan="3">Действия</th>
            </tr>
            </thead>
            <tbody>

            <#list warehouseOrders as WarehouseOrder>

                <tr>
                    <th scope="row">${WarehouseOrder.id}</th>
                    <td><#list WarehouseOrder.type as WarehouseOrderType>${WarehouseOrderType}</#list></td>
                    <td>${WarehouseOrder.description}</td>
                    <@menus.crudButtons/>
                </tr>
            <#else>
                <td colspan="9">Ничего не найдено</td>
            </#list>
            </tbody>
        </table>
    </div>
</@common.page>
