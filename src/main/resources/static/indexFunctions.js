
function getSogne() {
    $.ajax( {
        url:"sogne",
        type:"GET",
        contentType:"application/JSON",
        success:function (data) {
            $.each(data, function(index, item) {
                let id = item.id;

                let datetoday = new Date().toISOString();
                let lukning;

                if (datetoday > item.startPaaNedlukning) {
                    lukning = "Nedlukket";
                } else if (datetoday < item.startPaaNedlukning){
                    lukning = "d. " + item.startPaaNedlukning;
                } else {
                    lukning = "Ikke nedlukket"
                }

                $("#sogneListe").append(
                    "<tr id='" + item.id + "'>" +
                    "<td>" + item.navn + "</td>" +
                    "<td>" + item.sognKode + "</td>" +
                    "<td>" + item.kommune.navn + "</td>" +
                    "<td>" + item.smittetryk + "</td>" +
                    "<td>" + lukning + "</td>" +
                    "<td>" + "<button value='" + id + "' id='deleteSogn'>DELETE</button>" + "</td>" +
                    "<td>" + "<button value='" + id + "' id='updateSogn'>UPDATE</button>" + "</td>" +
                    "</tr>"
                );
            })
        }
    });
}

function deleteSogne(id) {
    $.ajax( {
        url: "sogne/" + id,
        type: "DELETE",
        ContentType:"application/JSON",
        success:function() {
            $("tr#" + id).remove();
        },
        error:function(){
           //Hvis man ville give besked på error
        }
    });
}

function createSogn() {
    let createSognObject = {};
    createSognObject["navn"] = $("#name").val();
    createSognObject["kommune"] = $("#kommune").val();
    createSognObject["sognKode"] = parseInt($("#sognKode").val());
    createSognObject["smittetryk"] = parseFloat($("#smitteTryk").val()).toFixed(1);
    createSognObject["startPaaNedlukning"] = $("#nedLukning").val();

    $.ajax({
        url: "sogne",
        type: "POST",
        contentType: "application/JSON",
        data:JSON.stringify(createSognObject),
    });
}

function updateSogn(id) {
    $.ajax({
        url: "sogne/" + id,
        type: "GET",
        contentType: "application/JSON",
        success: function (data) {
            document.getElementById("updateName").setAttribute("value",data.navn);
            document.getElementById("updateKommune").setAttribute("value",data.kommune.navn);
            document.getElementById("updateSognKode").setAttribute("value",data.sognKode);
            document.getElementById("updateSmitteTryk").setAttribute("value",data.smittetryk);
            document.getElementById("updateNedLukning").setAttribute("value",data.startPaaNedlukning);
            document.getElementById("updateFormSubmit").setAttribute("value",data.id);
        }
    });
}

function updateSognSubmit(id) {
    let updateSognUpdate = {};
    updateSognUpdate["navn"] = $("#updateName").val();
    updateSognUpdate["kommune"] = $("#updateKommune").val();
    updateSognUpdate["sognKode"] = parseInt($("#updateSognKode").val());
    updateSognUpdate["smittetryk"] = parseFloat($("#updateSmitteTryk").val()).toFixed(1);
    updateSognUpdate["startPaaNedlukning"] = $("#updateNedLukning").val();

    $.ajax({
        url: "sogne/" + id,
        type: "PUT",
        contentType: "application/JSON",
        data:JSON.stringify(updateSognUpdate)
    });
}

function getSmittetryk() {
    let ids = new Map();
    let kommuneName = new Map();
    let indbyggertalIKommunen = new Map();

    $.ajax({
        url: "sogne",
        type: "GET",
        contentType: "application/JSON",
        success: function (data) {
            $.each(data, function(index, item) {
                if (ids.has(item.kommune.id)) {
                    ids.set(item.kommune.id, item.smittetryk + ids.get(item.kommune.id))
                } else {
                    ids.set(item.kommune.id, item.smittetryk)
                    kommuneName.set(item.kommune.id, item.kommune.navn)
                    indbyggertalIKommunen.set(item.kommune.id, item.kommune.indbyggertal);
                }
            });

            for (let i = 1; i <= ids.size; i++) {
                if (ids.get(i) != null) {
                    $("#kommuneSmitteTryk").append(
                        "<tr>" +
                        "<td>" + kommuneName.get(i) + "</td>" +
                        "<td>" + (ids.get(i) / indbyggertalIKommunen.get(i)).toFixed(5) + "</td>" +
                        "</tr>"
                    )}

            }
        }
    });
}


