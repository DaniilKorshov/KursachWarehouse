<#import "parts/common.ftl" as common>
<#import "parts/menus.ftl" as menus>

<@common.page>
    <@menus.adminNav/>
    <@menus.tableHead "Таблица грузов" "Название" "/invent"/>
    <div class="table-responsive" style="text-align: center;">
        <table class="table table-hover table-bordered ">
            <thead>
            <tr>
                <th scope="row">#</th>
                <th>Название</th>
                <th>Тип груза</th>
                <th>Единица измерения</th>
                <th>Комплектация</th>
                <th colspan="2">Действия</th>
            </tr>
            </thead>
            <tbody>

            <#list invents as Invent>

                <tr>
                    <th scope="row">${Invent.id}</th>
                    <td>${Invent.name}</td>
                    <td><#list Invent.itemType as ItemType>${ItemType}</#list></td>
                    <td>${Invent.unit}</td>
                    <td>${Invent.dimgroup.id}</td>
                    <@menus.crudButtons "/addInvent" "/delInvent?id=${Invent.id}"/>
                </tr>
            <#else>
                <td colspan="5">Ничего не найдено</td>
                <@menus.crudButtons "/addInvent" "/invent"/>
            </#list>
            </tbody>
        </table>
    </div>
</@common.page>
