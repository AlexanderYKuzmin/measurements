function editBuildingCommonData(building_id) {
    let element = document.getElementById(building_id);
    let building = JSON.parse(get(root + "/test3/" + building_id).responseText);
    if (document.body.contains(document.getElementById("update_obj" + building_id))) {
        document.getElementById("update_obj" + building_id).remove();
        return;
    }

    let tr = document.createElement("tr");
    tr.setAttribute("id", "update_obj" + building_id);
    tr.setAttribute("style", "background: #469e9e");

    let th = document.createElement("th");
    th.setAttribute("scope", "row");
    th.appendChild(document.createTextNode(""));
    tr.appendChild(th);

    let td1 = document.createElement("td");
    let td1Input = document.createElement("input");
    td1Input.setAttribute("type", "text");
    td1Input.setAttribute("class", "input-sm");
    //titleInput.setAttribute("size", "6");
    td1Input.setAttribute("style", "font-family:monospace");
    td1Input.setAttribute("id", "update_obj" + building_id + "name");
    td1Input.setAttribute("value", building.name);
    td1.appendChild(td1Input);
    tr.appendChild(td1);

    let td2 = document.createElement("td");
    let td2Input = document.createElement("input");
    td2Input.setAttribute("type", "text");
    td2Input.setAttribute("class", "input-sm");
    //td2Input.setAttribute("size", "6");
    td2Input.setAttribute("style", "font-family:monospace");
    td2Input.setAttribute("id", "update_obj" + building_id + "address");
    td2Input.setAttribute("value", building.address);
    td2.appendChild(td2Input);
    tr.appendChild(td2);

    let td3 = document.createElement("td");
    //let type = objects[i].type.charAt(0) + objects[i].charAt(1).slice(1).toLowerCase();
    td3.appendChild(document.createTextNode(building.type));
    tr.appendChild(td3);

    let td4 = document.createElement("td");
    td4.appendChild(document.createTextNode(building.config));
    tr.appendChild(td4);

    let td5 = document.createElement("td");
    td5.setAttribute("id", "update_obj" + building_id + "levels");
    td5.appendChild(document.createTextNode(building.numberOfSections));
    tr.appendChild(td5);

    let td6 = document.createElement("td");
    td6.appendChild(document.createTextNode(building.height));
    tr.appendChild(td6);

    let td7 = document.createElement("td");
    let td7Input = document.createElement("input");
    td7Input.setAttribute("type", "text");
    td7Input.setAttribute("class", "input-sm");
    //td2Input.setAttribute("size", "6");
    td7Input.setAttribute("style", "font-family:monospace");
    td7Input.setAttribute("id", "update_obj" + building_id + "employee");
    td7Input.setAttribute("value", building.userName);
    td7.appendChild(td7Input);
    tr.appendChild(td7);

    let td8 = document.createElement("td");
    let td8Input = document.createElement("input");
    td8Input.setAttribute("type", "date");
    td8Input.setAttribute("size", "4");
    td8Input.setAttribute("style", "font-family:monospace");
    td8Input.setAttribute("class", "input-sm");
    td8Input.setAttribute("id", "update_obj" + building.id + "regdate");
    td8Input.setAttribute("value", new Date(building.registerDate).toISOString().substring(0, 10));
    td8.appendChild(td8Input);
    tr.appendChild(td8);

    let td9 = document.createElement("td");
    let measurements = building.measurements;
    let mDate = new Date();
    mDate.setTime(measurements[0].date);
    td9.appendChild(document.createTextNode(mDate.toDateString()));
    tr.appendChild(td9);

    element.insertAdjacentElement("afterEnd", tr);
}

function editSectionsData(building_id) {
    let element = document.getElementById("sections-head" + building_id);
    let building = JSON.parse(get(root + "/test3/" + building_id).responseText);

    if (document.body.contains(document.getElementById("sections-head" + building_id + "-update-" + 0))) {
        for (let i = 0; i < building.sections.length; i++) {
            document.getElementById("sections-head" + building_id + "-update-" + i).remove();
        }
        let tbody = fillSectionTable(building_id, building.sections);
        element.insertAdjacentElement("afterEnd", tbody[0]);
        for(let i = 1; i < tbody.length; i++) {
            tbody[i - 1].insertAdjacentElement("afterEnd", tbody[i]);
        }
        return;
    }

    let tbodyEditable = fillSectionsTableEditable(building_id, building.sections);
    for (let i = 0; i < building.sections.length; i++) {
        document.getElementById("sections-head" + building_id + "-" + i).remove();
    }
    element.insertAdjacentElement("afterEnd", tbodyEditable[0]);
    for(let i = 1; i < tbodyEditable.length; i++) {
        tbodyEditable[i - 1].insertAdjacentElement("afterEnd", tbodyEditable[i]);
    }
}

function saveData(building_id) {
    let building_levels = parseInt(document.getElementById("update_obj" + building_id + "levels").innerHTML);
    let body = {};
    body.name = document.getElementById("update_obj" + building_id + "name").value;
    body.address = document.getElementById("update_obj" + building_id + "address").value;
    body.userName = document.getElementById("update_obj" + building_id + "employee").value.toUpperCase();
    body.registerDate = new Date(document.getElementById("update_obj" + building_id + "regdate").value).getTime();

    //let sections = [];
    body.sections = [];
    let summarySectionsHeight = 0;
    for(let i = 0; i < building_levels; i++) {
        console.log(document.getElementById("sections-head" + building_id + "-update-" + i + "-h").value);
        let height = parseInt(document.getElementById("sections-head" + building_id + "-update-" + i + "-h").value);
        body.sections[i] = {
            number: i + 1,
            widthBottom: parseInt(document.getElementById("sections-head" + building_id + "-update-" + i + "-wb").value),
            widthTop: parseInt(document.getElementById("sections-head" + building_id + "-update-" + i + "-wt").value),
            height: height
        }
        console.log(body.sections[i].number + " "
            + body.sections[i].widthBottom + " "
            + body.sections[i].widthTop + " "
            + body.sections[i].height);
        summarySectionsHeight += height;
    }
    body.height = summarySectionsHeight;
    post(root + "/test3/" + building_id, JSON.stringify(body));
    loadContent(root);
}

function fillSectionsTableEditable(building_id, sections) {
    let trsEditable = [];
    let tdsEditable = [];
    let tdsEditableInput = [];
    let div = [];
    sections.sort((a, b) => a.number - b.number);

    for(let i = 0; i < sections.length; i++) {
        trsEditable[i] = document.createElement("tr");
        let trId = `sections-head${building_id}-update-${i}`;
        trsEditable[i].setAttribute("id", trId);
        trsEditable[i].setAttribute("style", "background: #469e9e");
        /*trs[i].onmouseenter = function() { // курсор зашёл на элемент-родитель [mozilla.org]
            this.style.background = 'DarkGray';
        }
        trs[i].onmouseleave = function() { // курсор зашёл на элемент-родитель [mozilla.org]
            this.style.background = 'antiquewhite';
        }*/

        tdsEditable[i] = [];
        tdsEditableInput[i] = [];

        tdsEditable[i][0] = document.createElement("td");
        tdsEditable[i][0].appendChild(document.createTextNode(""));
        tdsEditable[i][0].colSpan = 2;
        trsEditable[i].appendChild(tdsEditable[i][0]);

        tdsEditable[i][1] = document.createElement("td");
        div[i] = document.createElement("div");
        div[i].setAttribute("class", "section-number");
        div[i].appendChild(document.createTextNode(sections[i].number));
        //tdsEditable[i][1].appendChild(document.createTextNode(sections[i].number));
        tdsEditable[i][1].appendChild(div[i]);
        trsEditable[i].appendChild(tdsEditable[i][1]);

        tdsEditable[i][2] = document.createElement("td");
        tdsEditableInput[i][2] = document.createElement("input")
        tdsEditableInput[i][2].setAttribute("type", "text");
        tdsEditableInput[i][2].setAttribute("class", "input-sm");
        tdsEditableInput[i][2].setAttribute("size", "6");
        tdsEditableInput[i][2].setAttribute("style", "font-family:monospace");
        tdsEditableInput[i][2].setAttribute("id", "sections-head" + building_id + "-update-" + i + "-wb");
        tdsEditableInput[i][2].setAttribute("value", sections[i].widthBottom);
        tdsEditable[i][2].appendChild(tdsEditableInput[i][2]);
        tdsEditable[i][2].colSpan = 2;
        trsEditable[i].appendChild(tdsEditable[i][2]);

        tdsEditable[i][3] = document.createElement("td");
        tdsEditableInput[i][3] = document.createElement("input");
        tdsEditableInput[i][3].setAttribute("type", "text");
        tdsEditableInput[i][3].setAttribute("class", "input-sm");
        tdsEditableInput[i][3].setAttribute("size", "6");
        tdsEditableInput[i][3].setAttribute("style", "font-family:monospace");
        tdsEditableInput[i][3].setAttribute("id", "sections-head" + building_id + "-update-" + i + "-wt");
        tdsEditableInput[i][3].setAttribute("value", sections[i].widthTop);
        tdsEditable[i][3].appendChild(tdsEditableInput[i][3]);
        tdsEditable[i][3].colSpan = 2;
        trsEditable[i].appendChild(tdsEditable[i][3]);

        tdsEditable[i][4] = document.createElement("td");
        tdsEditableInput[i][4] = document.createElement("input");
        tdsEditableInput[i][4].setAttribute("type", "text");
        tdsEditableInput[i][4].setAttribute("class", "input-sm");
        tdsEditableInput[i][4].setAttribute("size", "6");
        tdsEditableInput[i][4].setAttribute("style", "font-family:monospace");
        tdsEditableInput[i][4].setAttribute("id", "sections-head" + building_id + "-update-" + i + "-h");
        tdsEditableInput[i][4].setAttribute("value", sections[i].height);
        tdsEditable[i][4].appendChild(tdsEditableInput[i][4]);
        trsEditable[i].appendChild(tdsEditable[i][4]);

        tdsEditable[i][5] = document.createElement("td");
        tdsEditable[i][5].appendChild(document.createTextNode(""));
        tdsEditable[i][5].colSpan = 2;
        trsEditable[i].appendChild(tdsEditable[i][5]);

    }
    return trsEditable;
}