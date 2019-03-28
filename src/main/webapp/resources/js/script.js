function my() {
    var race = document.getElementById("race").value;
    var gender = document.getElementById("gender").value;
    var role = document.getElementById("role").value;

    document.getElementById("valRace").innerHTML = "Race: " + race;
    document.getElementById("valGen").innerHTML = "Gender: " + gender;
    document.getElementById("valRole").innerHTML = "Role: " + role;

    var b = document.getElementById("img");
    while (typeof b.childNodes[0] == "object") {
        b.removeChild(b.childNodes[0]);
    }

    if (race === "dwarf") {

        var img = document.createElement("img");
        img.src = "https://wow.zamimg.com/uploads/screenshots/normal/572806-simple.jpg";
        var src = document.getElementById("img");
        src.appendChild(img);
    } else if (race === "human") {

        var img1 = document.createElement("img");
        img1.src = "https://wow.zamimg.com/uploads/screenshots/normal/562425-hunter-human-male.jpg";
        var src1 = document.getElementById("img");
        src1.appendChild(img1);
    } else if (race === "orc") {

        var img2 = document.createElement("img");
        img2.src = "https://wow.zamimg.com/uploads/screenshots/normal/511437-sellsword.jpg";
        var src2 = document.getElementById("img");
        src2.appendChild(img2);
    } else if (race === "elf") {

        var img3 = document.createElement("img");
        img3.src = "https://wow.zamimg.com/uploads/screenshots/normal/759517-blood-elf-ranger.jpg";
        var src3 = document.getElementById("img");
        src3.appendChild(img3);
    } else if (race === "undead") {

        var img4 = document.createElement("img");
        img4.src = "https://wow.zamimg.com/uploads/screenshots/normal/566501-%D1%81%D0%B5%D1%82-1.jpg";
        var src4 = document.getElementById("img");
        src4.appendChild(img4);
    }
}