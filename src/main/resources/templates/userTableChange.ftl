<#import "parts/common.ftl" as common>
<#import "parts/menus.ftl" as adminNav>

<@common.page>
    <@adminNav.adminNav/>
    <@adminNav.changePage "${tableName}" "${message}" "${action}">
        <input type="email" class="form-control" name="email" placeholder="Email" required="" autofocus=""
               style="margin-bottom: 3vh;">
        <input type="password" class="form-control" name="password" placeholder="Пароль" style="margin-bottom: 3vh;">
        <input class="form-control" name="name" placeholder="Имя" style="margin-bottom: 3vh;">
        <input class="form-control" name="surname" placeholder="Фамилия" style="margin-bottom: 3vh;">
        <input class="form-control" name="phone_number" placeholder="Номер телефона" style="margin-bottom: 3vh;">
        <select class="form-control" name="userRoles" placeholder="Роль" style="margin-bottom: 3vh;">
            <option>${userRoleU}</option>
            <option>${userRoleA}</option>
        </select>
        <input type="hidden" name="_csrf" value="${_csrf.token}"> <!--Для безопасности-->
        <button class="btn btn-md btn-primary col-6" type="submit" style="margin-top: 2vh">${crudName}</button>
        <a class="btn btn-md btn-outline-primary col-6" href="/login" role="button" style="margin-top: 2vh">Отмена</a>
    </@adminNav.changePage>
</@common.page>
