<#import "parts/common.ftl" as common>
<#import "parts/menus.ftl" as menus>

<@common.page>
    <@menus.adminNav/>
    <@menus.tableHead "Наличие на складе" "ID груза" "/inventSum"/>
    <div class="table-responsive" style="text-align: center;">
        <table class="table table-hover table-bordered ">
            <thead>
            <tr>
                <th scope="row">#</th>
                <th>ID груза</th>
                <th>ID ячейки</th>
                <th>Количество</th>
            </tr>
            </thead>
            <tbody>

            <#list inventSums as InventSum>
                <tr>
                    <th scope="row">${InventSum.id}</th>
                    <td>${InventSum.invent.id}</td>
                    <td>${InventSum.cell.id}</td>
                    <td>${InventSum.qty}</td>
                </tr>
            <#else>
                <td colspan="5">Ничего не найдено</td>
            </#list>
            </tbody>
        </table>
    </div>
</@common.page>
