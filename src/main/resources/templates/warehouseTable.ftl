<#import "parts/common.ftl" as common>
<#import "parts/menus.ftl" as menus>

<@common.page>
    <@menus.adminNav/>
    <@menus.tableHead "Таблица складов" "Адрес" "/warehouse"/>
    <div class="table-responsive" style="text-align: center;">
        <table class="table table-hover table-bordered ">
            <thead>
            <tr>
                <th scope="row">#</th>
                <th>Название</th>
                <th>Адрес</th>
                <th colspan="2">Действия</th>
            </tr>
            </thead>
            <tbody>
            <#list warehouses as Warehouse>
                <tr>
                    <th scope="row">${Warehouse.id}</th>
                    <td>${Warehouse.name}</td>
                    <td>${Warehouse.warehouseAddress}</td>
                    <@menus.crudButtons "/addWarehouse" "/delWarehouse?id=${Warehouse.id}"/>
                </tr>
            <#else>
                <td colspan="3">Ничего не найдено</td>
                <@menus.crudButtons "/addWarehouse" "/warehouse"/>
            </#list>
            </tbody>
        </table>
    </div>
</@common.page>
