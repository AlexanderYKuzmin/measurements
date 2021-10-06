let root = "http://localhost:8080";

function loadContent(root) {
    let objects = JSON.parse(get(root + "/test3/buildings").responseText);
    //alert("loading body root = " + root);
    let table = document.getElementById("mainTable");
    table.innerHTML = "";

    for(let i = 0; i < objects.length; i++) {
        let tr = document.createElement("tr");
        let th = document.createElement("th");
        th.setAttribute("scope", "row");
        //th.setAttribute("style", "color: black");
        th.appendChild(document.createTextNode(objects[i].id));
        tr.appendChild(th);

        let td1 = document.createElement("td");
        //let clickableName = document.createElement("a");
            //);
        //clickableName.setAttribute("href", `javascript:showBuilding(${objects[i].id})`)
        //clickableName.addListener
        td1.appendChild(document.createTextNode(objects[i].name));
        //td1.addEventListener("click", function () {
          //  showBuilding(objects[i].id);
        //})
        //clickableName.appendChild(document.createTextNode(objects[i].name));
        //td1.appendChild(clickableName);
        tr.appendChild(td1);

        let td2 = document.createElement("td");
        td2.appendChild(document.createTextNode(objects[i].address));
        tr.appendChild(td2);

        let td3 = document.createElement("td");
        td3.appendChild(document.createTextNode(objects[i].type));
        tr.appendChild(td3);

        let td4 = document.createElement("td");
        td4.appendChild(document.createTextNode(objects[i].config));
        tr.appendChild(td4);

        let td5 = document.createElement("td");
        td5.appendChild(document.createTextNode(objects[i].numberOfSections));
        tr.appendChild(td5);

        let td6 = document.createElement("td");
        td6.appendChild(document.createTextNode(objects[i].height));
        tr.appendChild(td6);

        let td7 = document.createElement("td");
        td7.appendChild(document.createTextNode(objects[i].userName));
        tr.appendChild(td7);

        let td8 = document.createElement("td");
        let regDate = new Date();
        regDate.setTime(objects[i].registerDate)
        td8.appendChild(document.createTextNode(regDate.toDateString()));
        tr.appendChild(td8);

        let td9 = document.createElement("td");
        let measurements = objects[i].measurements;
        let mDate = new Date();
        mDate.setTime(measurements[0].date);
        td9.appendChild(document.createTextNode(mDate.toDateString()));
        tr.appendChild(td9);
        tr.setAttribute("class", "table-row");
        tr.setAttribute("id", objects[i].id);
        tr.setAttribute("onClick", `clickSelectedRowBuilding(${objects[i].id}, "http://localhost:8080");`);

        table.appendChild(tr);
    }


        /*if(i % 2 === 0) {
            tr.setAttribute("class", "table-secondary");
        } else {
            tr.setAttribute("class", "table-light");
        }*/


    window.scrollTo(500, 100);
}

function get(requestUrl) {
    let httpReq = new XMLHttpRequest();
    httpReq.open("get", requestUrl, false);
    httpReq.send(null);
    //httpReq.overrideMimeType("UTF-8");
    if(httpReq.status === 400) {
        /* $('#error-text').text("Not found get " + requestUrl);
         $('#myModal').modal("show");*/
        alert("Bad request GET " + requestUrl);
    }
    if(httpReq.status === 404) {
        alert("Not found GET " + requestUrl);
    }
    return httpReq;
}

function post(requestUrl, body) {
    let Httpreq = new XMLHttpRequest(); // a new request
    Httpreq.open("POST", requestUrl, false);
    Httpreq.setRequestHeader("Content-type", "application/json;charset=UTF-8");
    Httpreq.send(body);
    if (Httpreq.status === 400) {
        /*$('#error-text').text("Bad request to POST " + requestUrl);
        $('#myModal').modal('show');*/
        alert("Bad request for F!!");
    }
    if (Httpreq.status === 404) {
        /*$('#error-text').text("Not found POST " + requestUrl);
        $('#myModal').modal('show');*/
        alert("Bad request for F..ing POST!!");
    }
    return Httpreq;
}

function clickSelectedRowBuilding(id, root) {
    //alert("root path = " + root);
    let element = document.getElementById(id);
    let building = JSON.parse(get(root + "/test3/" + id).responseText);
    if (document.body.contains(document.getElementById("sections-head" + id))) {
        if(document.body.contains(document.getElementById("update_obj"+id))) {
            document.getElementById("update_obj"+id).remove();
        }
        if(document.body.contains(document.getElementById("sections-head" + id + "-update-" + 0))) {
            for (let i = 0; i < building.sections.length; i++) {
                document.getElementById("sections-head" + id + "-update-" + i).remove();
            }
        } else {
            for (let i = 0; i < building.sections.length; i++) {
                document.getElementById("sections-head" + id + "-" + i).remove();
            }
        }
        document.getElementById("sections-head" + id).remove();
        document.getElementById("sections-head" + id + "foot").remove();
        return;
    }

    let tr = fillSectionTableHead(id);
    let tbody = fillSectionTable(id, building.sections);
    let trFootButtons = fillSectionTableFoot(id);

    element.insertAdjacentElement("afterEnd", tr);
    tr.insertAdjacentElement("afterEnd", tbody[0]);
    for(let i = 1; i < tbody.length; i++) {
        tbody[i-1].insertAdjacentElement("afterEnd", tbody[i]);
    }

    tbody[tbody.length -1].insertAdjacentElement("afterEnd", trFootButtons);

}

function clickSelectedRowSection(trId) {
    alert(trId);
}

function clickCreate() {
    let elem = document.getElementById("createButton");
    alert("Entered  function");
    if (elem.style.display === "none") {
        alert("element style display === none");
        elem.style.display = "block";
    } else {
        elem.style.display = "none"
    }
}

function filterProcessing(name, address) {

}


function showFilterModal() {
    let modal = document.getElementById("filterModal");
    modal.modal('show');
    /*$(document).ready(function () {
            $("#filterModal").modal('show');
        });*/
}

function fillSectionTableHead(id) {
    let tr = document.createElement("tr");
    tr.setAttribute("id", "sections-head" + id);
    tr.setAttribute("style", "background: #9e8146");

    let th0 = document.createElement("th");
    th0.appendChild(document.createTextNode(""));
    th0.colSpan = 2;
    tr.appendChild(th0);

    let thSecNum = document.createElement("th");
    thSecNum.setAttribute("scope", "col");
    thSecNum.setAttribute("style", "text-align: right");
    thSecNum.appendChild(document.createTextNode("Section#"));
    tr.appendChild(thSecNum);

    let thSecBottom = document.createElement("th");
    thSecBottom.setAttribute("scope", "col");
    thSecBottom.appendChild(document.createTextNode("Wb, mm"));
    thSecBottom.colSpan = 2
    tr.appendChild(thSecBottom);

    let thSecTop = document.createElement("th");
    thSecTop.setAttribute("scope", "col");
    thSecTop.appendChild(document.createTextNode("Wt, mm"));
    thSecTop.colSpan = 2;
    tr.appendChild(thSecTop);

    let thSecHeight = document.createElement("th");
    thSecHeight.setAttribute("scope", "col");
    thSecHeight.appendChild(document.createTextNode("H, mm"));
    tr.appendChild(thSecHeight);

    let thRest = document.createElement("th");
    thRest.setAttribute("scope", "col");
    thRest.appendChild(document.createTextNode(""));
    thRest.colSpan = 2;
    tr.appendChild(thRest);

    return tr;
}

function fillSectionTable(id, sections) {
    let trs = [sections.length];
    let tds = [];
    let div = [];
    sections.sort((a, b) => a.number - b.number);

    for(let i = 0; i < sections.length; i++) {
        trs[i] = document.createElement("tr");
        let trId = `sections-head${id}-${i}`;
        console.log(trId);
        trs[i].setAttribute("id", trId);
        trs[i].setAttribute("style", "background: antiquewhite");
        trs[i].onmouseenter = function() { // курсор зашёл на элемент-родитель [mozilla.org]
            this.style.background = 'DarkGray';
        }
        trs[i].onmouseleave = function() { // курсор зашёл на элемент-родитель [mozilla.org]
            this.style.background = 'antiquewhite';
        }

        tds[i] = [];

        tds[i][0] = document.createElement("td");
        tds[i][0].appendChild(document.createTextNode(""));
        tds[i][0].colSpan = 2;
        trs[i].appendChild(tds[i][0]);

        tds[i][1] = document.createElement("th");
        div[i] = document.createElement("div");
        div[i].setAttribute("class", "section-number");
        div[i].appendChild(document.createTextNode(sections[i].number));
        //tds[i][1].appendChild(document.createTextNode(sections[i].number));
        tds[i][1].appendChild(div[i]);
        trs[i].appendChild(tds[i][1]);

        tds[i][2] = document.createElement("td");
        tds[i][2].appendChild(document.createTextNode(sections[i].widthBottom));
        tds[i][2].colSpan = 2;
        trs[i].appendChild(tds[i][2]);

        tds[i][3] = document.createElement("td");
        tds[i][3].appendChild(document.createTextNode(sections[i].widthTop));
        tds[i][3].colSpan = 2;
        trs[i].appendChild(tds[i][3]);

        tds[i][4] = document.createElement("td");
        tds[i][4].appendChild(document.createTextNode(sections[i].height));
        trs[i].appendChild(tds[i][4]);

        tds[i][5] = document.createElement("td");
        tds[i][5].appendChild(document.createTextNode(""));
        tds[i][5].colSpan = 2;
        trs[i].appendChild(tds[i][5]);

        //let trsAtrr = trs[i].getAtt
        trs[i].setAttribute("onClick", `clickSelectedRowSection(\"${trId}\");`);
    }
    return trs;
}

function fillSectionTableFoot(id) {
    let tr = document.createElement("tr");
    tr.setAttribute("id", "sections-head" + id + "foot");
    tr.setAttribute("style", "background: antiquewhite");

    let td0 = document.createElement("td");
    td0.colSpan = 2;

    let cloneButton = document.createElement("button");
    cloneButton.setAttribute("type", "button");
    cloneButton.setAttribute("class", "btn btn-outline-secondary btn-sm");
    cloneButton.addEventListener("click", function () {
        cloneBuilding(id)
    });
    cloneButton.appendChild(document.createTextNode("Clone"));

    let deleteButton = document.createElement("button");
    deleteButton.setAttribute("type", "button");
    deleteButton.setAttribute("class", "btn btn-danger btn-sm");
    deleteButton.addEventListener("click", function () {
        deleteBuilding(id)
    });
    deleteButton.appendChild(document.createTextNode("Delete"));

    td0.appendChild(cloneButton);
    td0.appendChild(document.createTextNode(" "));
    td0.appendChild(deleteButton);
    tr.appendChild(td0);

    let td1 = document.createElement("td");
    td1.setAttribute("style", "text-align: right");
    let editBuildingButton = document.createElement("button");
    editBuildingButton.setAttribute("type", "button");
    editBuildingButton.setAttribute("class", "btn btn-primary btn-sm");
    editBuildingButton.setAttribute("style", "width: 120px");
    editBuildingButton.addEventListener("click", function () {
        editBuildingCommonData(id)
    });
    editBuildingButton.appendChild(document.createTextNode("Edit Building"));

    let editSectionButton = document.createElement("button");
    editSectionButton.setAttribute("type", "button");
    editSectionButton.setAttribute("class", "btn btn-primary btn-sm");
    editSectionButton.setAttribute("style", "width: 120px");
    editSectionButton.addEventListener("click", function () {
        editSectionsData(id)
    });
    editSectionButton.appendChild(document.createTextNode("Edit Sections"));

    let saveButton = document.createElement("button");
    saveButton.setAttribute("type", "button");
    saveButton.setAttribute("class", "btn btn-success btn-sm");
    saveButton.setAttribute("style", "width: 120px");
    saveButton.addEventListener("click", function () {
        saveData(id)
    });
    saveButton.appendChild(document.createTextNode("Save"));
    td1.appendChild(editBuildingButton);
    td1.appendChild(document.createTextNode(" "));
    td1.appendChild(editSectionButton);
    td1.appendChild(document.createTextNode(" "));
    td1.appendChild(saveButton);
    tr.appendChild(td1);

    let td2 = document.createElement("td");

    tr.appendChild(td2);

    let td3 = document.createElement("td");
    td3.appendChild(document.createTextNode(""));
    td3.colSpan = 2;
    tr.appendChild(td3);

    let td4 = document.createElement("td");
    td4.appendChild(document.createTextNode(""));
    td4.colSpan = 2;
    tr.appendChild(td4);

    let td5 = document.createElement("td");
    let td5Div = document.createElement("div");
    td5Div.setAttribute("class", "d-grid gap-2 mx-auto");
    let journalButton = document.createElement("button");
    journalButton.setAttribute("type", "button");
    journalButton.setAttribute("class", "btn btn-info btn-sm");
    journalButton.addEventListener("click", function () {
        showJournal()
    });
    journalButton.appendChild(document.createTextNode("Journal"));
    td5Div.appendChild(journalButton);
    td5.appendChild(td5Div);
    tr.appendChild(td5);

    let td6 = document.createElement("td");
    let td6Div = document.createElement("div");
    td6Div.setAttribute("class", "d-grid gap-2 mx-auto");
    let reportButton = document.createElement("button");
    reportButton.setAttribute("type", "button");
    reportButton.setAttribute("class", "btn btn-info btn-sm");
    reportButton.addEventListener("click", function () {
        showReport()
    });
    reportButton.appendChild(document.createTextNode("Report"));
    td6Div.appendChild(reportButton);
    td6.appendChild(td6Div);
    tr.appendChild(td6);


    return tr;
}


