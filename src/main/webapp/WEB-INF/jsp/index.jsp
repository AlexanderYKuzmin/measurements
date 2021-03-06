<!DOCTYPE HTML>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Tower Measurements</title>

    <meta id="root" about="${pageContext.request.contextPath}">

    <link href="${pageContext.request.contextPath}/resources/bootstrap-5.0.1-dist/css/bootstrap.css" rel="stylesheet" type="text/css" charset="UTF-8">
    <link href="${pageContext.request.contextPath}/resources/bootstrap-5.0.1-dist/js/bootstrap.js" rel="stylesheet">
    <link href="https://code.jquery.com/jquery-3.6.0.min.js" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/style.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js">
    </script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/bootstrap-5.0.1-dist/js/bootstrap.js" charset="UTF-8">
    </script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/processing.js" charset="UTF-8">
    </script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/edition-maintable.js" charset="UTF-8">
    </script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/modal-win-handler.js" charset="UTF-8">
    </script>
</head>

<body onload="loadContent('${pageContext.request.contextPath}')" style="background: #c9a544; margin: 15px">


    <header class="main inline">
        <div class="container">
            <img src="${pageContext.request.contextPath}/resources/tower.png" width=173 height="155" alt="Picture">

            <div class="middle-block">
                <h1 class="title">Tower Measurements</h1>
                <!--reference row-->
                <nav class="header-menu" id="headerMenu">
                    <ul class="list-unstyled">
                        <li>
                            <a href="/about/" data-link="/about/">About</a>
                        </li>
                        <li>
                            <a href="/contacts/" data-link="/contacts/">Contacts</a>
                        </li>
                        <li>
                            <a href="/contacts/" data-link="/regulatory/">Regulatory</a>
                        </li>
                    </ul>
                </nav>
            </div>
            <!--Images-->
            <div style="display: inline; position: absolute; right: 320px">
                <img src="${pageContext.request.contextPath}/resources/IMG_5625.jpg" width=120 height="150" alt="Picture_1">
                <img src="${pageContext.request.contextPath}/resources/IMG_6615.jpg" width=120 height="150" alt="Picture_2">
                <img src="${pageContext.request.contextPath}/resources/IMG-20201115-WA0060.jpg" width=120 height="150" alt="Picture_3">
            </div>
            <div class="authorization-block">
                <form name="authorization-block" method="post" action="input.js">
                    <p style="height: 27px"><b>Email:</b><br>
                        <input type="text" size="30"></p>
                    <p style="height: 27px"><b>Password:</b><br>
                        <input type="text" size="30"></p>
                    <p style="padding-top: 5px">
                        <input type="submit" value="Log in">
                        <input type="button" value="Register"></p>
                </form>
            </div>
        </div>
    </header>

    <%--<div id="filterModal" class="modal fade" tabindex="-1">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Filter</h5>
                    <button type="button" class="close" data-dismiss="modal">x</button>
                </div>
                <div class="media-body">
                    <p>Вы хотите сохранить изменения в этом документе перед закрытием?</p>
                    <p class="text-secondary"><small>Если вы не сохраните, ваши изменения будут потеряны.</small></p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Закрыть</button>
                    <button type="button" class="btn btn-primary">Сохранить изменения</button>
                </div>
            </div>
        </div>
    </div>--%>

    <form style="display: inline">
        <div style="margin-top: 15px">
            <%--<button class="inButton" data-bs-toggle="modal" data-bs-target="#filterModal">Filter</button>--%>
            <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#filterModal">Filter</button>
            <button type="button"class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#creationModal">Create new</button>
        </div>


    </form>
    <table style="margin-top: 10px" class="table table-striped">
        <thead style="background: #2e2929">
        <tr cl>
            <th scope="col">#</th>
            <th scope="col">Name</th>
            <th scope="col">Address</th>
            <th scope="col">Type</th>
            <th scope="col">Config</th>
            <th scope="col">Levels</th>
            <th scope="col">Height</th>
            <th scope="col">Employee</th>
            <th scope="col">Reg Date</th>
            <th scope="col">Measure Date</th>

            <%--<th scope="col"></th>
            <th scope="col"></th>--%>
        </tr>
        </thead>
        <tbody id="mainTable" style="background: #DCDCDC"> <!-- #DCDCDC-->

        </tbody>
    </table>
    <div>
        <ul id="paging-bar" class="pagination"
            style="position: absolute; left: 50%; transform: translate(-50%, -50%)">
            <li><a href="#">1</a></li>
            <li><a href="#">2</a></li>
            <li><a href="#">3</a></li>
            <li><a href="#">4</a></li>
            <li><a href="#">5</a></li>
        </ul>
    </div>

<!-- Filter modal window -->
    <div id="filterModal" class="modal fade" tabindex="-1" aria-labelledby="filterModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <!-- Заголовок модального окна -->
                <div class="modal-header">
                    <h5 class="modal-title" id="filterModalLabel">Filter parameters</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <!-- Основное содержимое модального окна -->
                <div class="modal-body">
                    <div class="form-group">
                        <b style="display: table-cell; vertical-align: bottom">Object code:</b>
                        <input type="text" name="objectName" id="objectName" class="form-control input-lg" placeholder="Object Code">
                    </div>
                    <div class="form-group" style="margin-top: 10px">
                        <b style="display: table-cell; vertical-align: bottom">Address:</b>
                        <input type="text" name="address" id="address" class="form-control input-lg" placeholder="Address">
                    </div>
                    <form class="form-inline" style="margin-top: 10px">
                        <div class="form-group" style="display: inline-block">
                            <b style="display: table-cell; vertical-align: bottom">Sections min:</b>
                            <input type="number" name="levels-min" id="levels-min" class="form-control input-lg" placeholder="Levels min" size="8">
                        </div>
                        <div class="form-group" style="margin-left: 40px; display: inline-block">
                            <b style="display: table-cell; vertical-align: bottom">Height min:</b>
                            <input type="number" name="height" id="height" class="form-control input-lg" placeholder="Height" size="8">
                        </div>
                    </form>
                    <form class="form-inline" style="margin-top: 10px">
                        <div class="form-group" style="display: inline-block">
                            <b style="display: table-cell; vertical-align: bottom">Date from:</b>
                            <input type="date" name="date-from" id="date-from" class="form-control input-lg" placeholder="Date from" size="8">
                        </div>
                        <div class="form-group" style="margin-left: 55px; display: inline-block">
                            <b style="display: table-cell; vertical-align: bottom">Date to:</b>
                            <input type="date" name="date-to" id="date-to" class="form-control input-lg" placeholder="Date to" size="8">
                        </div>
                    </form>
                    <div class="form-group" style="margin-top: 10px">
                        <b style="display: table-cell; vertical-align: bottom">Employee:</b>
                        <input type="text" name="employee" id="employee" class="form-control input-lg" placeholder="Empolyee">
                    </div>
                </div>
                <!-- Футер модального окна -->
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" data-bs-dismiss="modal">Ok</button>
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                </div>
            </div>
        </div>
    </div>

    <!-- Creation object modal window -->
    <div id="creationModal" class="modal fade" tabindex="-1" aria-labelledby="creationModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <!-- Заголовок модального окна -->
                <div class="modal-header">
                    <h5 class="modal-title" id="creationModalLabel">Creating new object</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <!-- Основное содержимое модального окна -->
                <div class="modal-body">
                    <div class="form-group">
                        <b style="display: table-cell; vertical-align: bottom">Object code:</b>
                        <input type="text" name="objectName" id="cr-objectName" class="form-control input-lg" placeholder="Object Code">
                    </div>
                    <div class="form-group" style="margin-top: 10px">
                        <b style="display: table-cell; vertical-align: bottom">Address:</b>
                        <input type="text" name="address" id="cr-address" class="form-control input-lg" placeholder="Address">
                    </div>

                    <form class="form-inline" style="margin-top: 10px">
                        <div class="form-group" style="display: inline-block">
                            <b style="display: table-cell; vertical-align: bottom">Type:</b>
                            <select id="cr-type" class="form-select">
                                <option selected>Choose...</option>
                                <option value="Tower">Tower</option>
                                <option value="Mast">Mast</option>
                                <option value="Pole">Pole</option>
                            </select>
                        </div>
                        <div class="form-group" style="margin-left: 20px; display: inline-block">
                            <b style="display: table-cell; vertical-align: bottom">Configuration:</b>
                            <select id="cr-config" class="form-select">
                                <option selected>Choose...</option>
                                <option value="3">3</option>
                                <option value="4">4</option>
                                <option value="0">0</option>
                            </select>
                        </div>
                        <div class="form-group" style="display: inline-block; margin-left: 20px">
                            <b style="display: table-cell; vertical-align: bottom">Sections:</b>
                            <input type="number" name="sections" id="cr-sections" class="form-control input-lg"
                                   size="8" style="max-width: 80px">
                        </div>
                    </form>

                    <div class="form-group" style="margin-top: 10px">
                        <b style="display: table-cell; vertical-align: bottom">Employee:</b>
                        <input type="text" name="employee" id="cr-employee" class="form-control input-lg" placeholder="Employee">
                    </div>
                    <div class="form-group" style="margin-top: 10px">
                        <b style="display: table-cell; vertical-align: bottom">Registration date:</b>
                        <input type="date" name="registration" id="cr-registration" class="form-control input-lg" placeholder="Registration">
                    </div>
                </div>
                <!-- Футер модального окна -->
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" data-bs-dismiss="modal">Ok</button>
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                </div>
            </div>
        </div>
    </div>
    <!-- Creation sections modal window -->

</body>
</html>
    <%--<button style="margin-bottom: 15px" type="button" class="btn btn-info" onclick="clickCreate()">Create new</button>
    <form style="background-color: #E9ECEF; padding: 20px; border-radius: 10px; display: none" id="createButton">
        <div class="form-row">
            <div class="form-group col-md-6">
                <label for="inputNameNew">Name</label>
                <input type="text" class="form-control" id="inputNameNew" placeholder="Name">
            </div>
            <div class="form-group col-md-6">
                <label for="inputAddressNew">Address</label>
                <input type="text" class="form-control" id="inputAddressNew" placeholder="Address">
            </div>
        </div>
        <div class="form-row">

            <div class="form-group col-md-3" style="padding: 0px 10px">
                <label>Registration date</label>
                <div class="form-row">
                    <input type="date" class="form-control" id="inputRegistrationNew">
                </div>
            </div>
            <div class="form-group col-md-3" style="padding: 0px 10px">
                <label>Height</label>
                <div class="form-row">
                    <input type="number" min="0" step="1000" class="form-control" id="inputExperienceNew">
                </div>
            </div>
        </div>
        <div class="form-row">
            <div class="form-group col-md-3" style="padding: 0px 10px">
                <label for="inputTypeNew">Type</label>
                <select id="inputTypeNew" class="form-control">
                    <option selected>Tower</option>
                    <option>Mast</option>
                    <option>Pole</option>
                </select>
            </div>
            <div class="form-group col-md-3" style="padding: 0px 10px">
                <label for="inputConfigNew">Configuration</label>
                <select id="inputConfigNew" class="form-control">
                    <option selected>4</option>
                    <option>3</option>
                    <option>0</option>
                </select>
            </div>
        </div>

        <button type="button" onclick="processCreate('${pageContext.request.contextPath}')" class="btn btn-success">
            Create
        </button>
    </form>

</body>--%>

