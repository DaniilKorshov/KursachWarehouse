<#import "parts/common.ftl" as common>
<#import "parts/menus.ftl" as adminNav>

<@common.page>
<@adminNav.adminNav/>
        <div class="container" style="padding-top: 5vh;">
            <div class="card-deck mb-3 text-center">
                <div class="card mb-4 box-shadow">
                    <div class="card-header">
                        <h4 class="my-0 font-weight-normal">Пользователи</h4>
                    </div>
                    <div class="card-body">
                        <p style="margin-top: 5vh">Информация о пользователях системы</p>
                        <form action="/users">
                            <button class="btn btn-lg btn-block btn-outline-primary" style="margin-top: 5vh">Просмотреть</button>
                        </form>
                    </div>
                </div>
                <div class="card mb-4 box-shadow">
                    <div class="card-header">
                        <h4 class="my-0 font-weight-normal">Складские заказы</h4>
                    </div>
                    <div class="card-body">
                        <p style="margin-top: 5vh">Информация о складских заказах</p>
                        <button type="button" class="btn btn-lg btn-block btn-outline-primary" style="margin-top: 5vh">Просмотреть</button>
                    </div>
                </div>
                <div class="card mb-4 box-shadow">
                    <div class="card-header">
                        <h4 class="my-0 font-weight-normal">Линии заказов</h4>
                    </div>
                    <div class="card-body">
                        <p style="margin-top: 5vh">Информация о линиях складских заказов</p>
                        <button type="button" class="btn btn-lg btn-block btn-outline-primary" style="margin-top: 5vh">Просмотреть</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="container" style="padding-top: 3vh;">
            <div class="card-deck mb-3 text-center">
                <div class="card mb-4 box-shadow">
                    <div class="card-header">
                        <h4 class="my-0 font-weight-normal">Грузы</h4>
                    </div>
                    <div class="card-body">
                        <p style="margin-top: 5vh">Информация о материалах и продукции</p>
                        <button type="button" class="btn btn-lg btn-block btn-outline-primary" style="margin-top: 5vh">Просмотреть</button>
                    </div>
                </div>

                <div class="card mb-4 box-shadow">
                    <div class="card-header">
                        <h4 class="my-0 font-weight-normal">Комплектации</h4>
                    </div>
                    <div class="card-body">
                        <p style="margin-top: 5vh">Информация о комплектациях</p>
                        <button type="button" class="btn btn-lg btn-block btn-outline-primary" style="margin-top: 5vh">Просмотреть</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="container" style="padding-top: 3vh;">
            <div class="card-deck mb-3 text-center">
                <div class="card mb-4 box-shadow">
                    <div class="card-header">
                        <h4 class="my-0 font-weight-normal">Ячейки</h4>
                    </div>
                    <div class="card-body">
                        <p style="margin-top: 5vh">Информация о ячейках</p>
                        <button type="button" class="btn btn-lg btn-block btn-outline-primary" style="margin-top: 5vh">Просмотреть</button>
                    </div>
                </div>
                <div class="card mb-4 box-shadow">
                    <div class="card-header">
                        <h4 class="my-0 font-weight-normal">Складские помещения</h4>
                    </div>
                    <div class="card-body">
                        <p style="margin-top: 5vh">Информация о складских помещениях</p>
                        <button type="button" class="btn btn-lg btn-block btn-outline-primary" style="margin-top: 5vh">Просмотреть</button>
                    </div>
                </div>
            </div>
        </div>
</@common.page>