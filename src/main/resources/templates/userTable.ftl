<#import "parts/common.ftl" as common>
<#import "parts/menus.ftl" as menus>

<@common.page>
    <@menus.adminNav/>
    <@menus.tableHead "Таблица пользователей" "Фамилия" "/users"/>
    <div class="table-responsive" style="text-align: center;">
        <table class="table table-hover table-bordered ">
            <thead>
            <tr>
                <th scope="row">#</th>
                <th>Имя</th>
                <th>Фамилия</th>
                <th>Email</th>
                <th>Телефон</th>
                <th>Пароль</th>
                <th>Доступ</th>
                <th colspan="3">Действия</th>
            </tr>
            </thead>
            <tbody>

            <#list users as User>
                <tr>
                    <th scope="row">${User.id}</th>
                    <td>${User.name}</td>
                    <td>${User.surname}</td>
                    <td>${User.email}</td>
                    <td>${User.phone_number}</td>
                    <td>${User.password}</td>
                    <td><#list User.userRoles as UserRole>${UserRole}</#list></td>
                    <@menus.crudButtons "" ""/>
                </tr>
            <#else>
                <td colspan="12">Ничего не найдено</td>
            </#list>
            </tbody>
        </table>
    </div>
</@common.page>
