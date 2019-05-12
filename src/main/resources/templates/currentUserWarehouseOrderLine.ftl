<#import "parts/common.ftl" as common>
<#import "parts/menus.ftl" as menus>

<@common.page>
    <@menus.userNav/>
    <@menus.tableHead "Таблица линий заказов" "Складской заказ" "/warehouseOrderLine"/>
    <div class="table-responsive" style="text-align: center;">
        <table class="table table-hover table-bordered ">
            <thead>
            <tr>
                <th scope="row">#</th>
                <th>Складской заказ</th>
                <th>Выполняющий</th>
                <th>Груз</th>
                <th>Количество</th>
                <th>Откуда</th>
                <th>Куда</th>
                <th>Груз забран</th>
                <th>Груз доставлен</th>
            </tr>
            </thead>
            <tbody>

            <#list warehouseOrderLines as WarehouseOrderLine>
                <tr>
                    <th scope="row">${WarehouseOrderLine.id}</th>
                    <td>${WarehouseOrderLine.warehouseOrder.getId()}</td>
                    <td>${WarehouseOrderLine.user.getSurname()} ${WarehouseOrderLine.user.getPhone_number()}</td>
                    <td>${WarehouseOrderLine.invent.getName()}</td>
                    <td>${WarehouseOrderLine.qty}</td>
                    <td>${WarehouseOrderLine.startLocation.getId()}</td>
                    <td>${WarehouseOrderLine.finishLocation.getId()}</td>
                    <td><#list WarehouseOrderLine.takeStatus as TaskStatus>${TaskStatus}</#list></td>
                    <td><#list WarehouseOrderLine.putStatus as TaskStatus>${TaskStatus}</#list></td>
                </tr>
            <#else>
                <td colspan="9">Ничего не найдено</td>
            </#list>
            </tbody>
        </table>
    </div>
</@common.page>
