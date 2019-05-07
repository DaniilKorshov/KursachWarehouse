<#import "parts/common.ftl" as common>
<#import "parts/menus.ftl" as menus>

<@common.page>
    <@menus.adminNav/>
    <@menus.tableHead "Таблица складов" "Название склада" "/warehouse"/>
    <div class="table-responsive" style="text-align: center;">
        <table class="table table-hover table-bordered ">
            <thead>
            <tr>
                <th scope="row">#</th>
                <th>Название</th>
                <th>Адрес</th>
                <th colspan="3">Действия</th>
            </tr>
            </thead>
            <tbody>
            <#list warehouses as Warehouse>
                <tr>
                    <th scope="row">${Warehouse.id}</th>
                    <td>${Warehouse.name}</td>
                    <td>${Warehouse.warehouseAddress}</td>
                    <@menus.crudButtons/>
                </tr>
            <#else>
                <td colspan="6">Ничего не найдено</td>
            </#list>
            </tbody>
        </table>
    </div>
</@common.page>
